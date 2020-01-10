package com.microsoft.bing.commerce.search;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

import com.microsoft.bing.commerce.search.implementation.BingCommerceSearchImpl;
import com.microsoft.bing.commerce.search.models.CommerceSearchPostRequest;
import com.microsoft.bing.commerce.search.models.CommerceSearchResponse;
import com.microsoft.bing.commerce.search.models.RequestQuery;
import com.microsoft.bing.commerce.search.models.ResponseDiscoveredFacets;
import com.microsoft.bing.commerce.search.models.ResponseItems;
import com.microsoft.bing.commerce.search.models.RequestItems;
import com.microsoft.bing.commerce.search.models.RequestDiscoverFacets;
import com.microsoft.bing.commerce.search.util.AppIdCredentialsInterceptor;
/**
* Unit test for simple App.
*/
public class AppTest extends TestCase {

   private final static String TenantId = System.getenv("SEARCH_TENANT");
   private final static String IndexId = System.getenv("SEARCH_INDEX");
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception
    {
    	OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
    			.addInterceptor(new AppIdCredentialsInterceptor( System.getenv("SEARCH_APPID") ))
    			.addInterceptor(new TestTrafficInterceptor(System.out));
    	Retrofit.Builder retrofit = new Retrofit.Builder();

        BingCommerceSearch client = new BingCommerceSearchImpl(httpClient, retrofit);
        
        CommerceSearchPostRequest request = new CommerceSearchPostRequest()
        	.withQuery(new RequestQuery().withMatchAll("diamond"))
        	.withItems(new RequestItems().withSelect(Arrays.asList("_itemId", "name")))
        	.withAggregations(Arrays.asList(new RequestDiscoverFacets().withName("discovered facets")));

        CommerceSearchResponse response = client.searchs().post(request, TenantId, IndexId);
        assertNotNull("Expected non-null response", response);
        
        System.out.println("sending the message");
        ResponseItems items = (ResponseItems)response.items();
        assertNotNull("Expected non-null items response", items);
        assertNotNull("Expected non-null items.value", items.value());
        
        assertNotNull("Expected non-null aggregations in the response", response.aggregations());
        assertEquals("Expected 1 aggregation in the response", 1, response.aggregations().size());
        
        ResponseDiscoveredFacets discoveredFacets = (ResponseDiscoveredFacets) response.aggregations().get(0);
        assertNotNull("Expected non-null discovered facets in the response", discoveredFacets);
        assertEquals("Expected facet response name to match the request", discoveredFacets.name(), request.aggregations().get(0).name());
        
        assertFalse("Expected at least 1 discovered facet", 0 == discoveredFacets.aggregations().size());
    }
    
    public class TestTrafficInterceptor implements Interceptor
    {
    	PrintStream _cw;
    	TestTrafficInterceptor(PrintStream cw) 
    	{
    		this._cw = cw;
    	}
    	@Override
    	public Response intercept(Interceptor.Chain chain) throws IOException {
    		Request request = chain.request();
    		
    		HttpUrl modifiedUrl = request.url().newBuilder()
    			.addQueryParameter("traffictype", "test")
    			.build();
    				
    		Request modifiedRequest = request.newBuilder()
				.url(modifiedUrl)
				.build();
    		
    		return chain.proceed(modifiedRequest);    		
    	}
    }
}
