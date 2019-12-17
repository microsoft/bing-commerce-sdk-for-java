package com.microsoft.bing.ecommerce.ingestion;

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

import com.microsoft.bing.ecommerce.ingestion.BingECommerceIngestion;
import com.microsoft.bing.ecommerce.ingestion.implementation.BingECommerceIngestionImpl;
import com.microsoft.bing.ecommerce.ingestion.models.*;
import com.microsoft.bing.ecommerce.ingestion.util.AppIdCredentialsInterceptor;

/**
 * Unit test for bing industry ingestion sdk
 */
public class IngestionTest extends TestCase
{
    private final static String TENANT_ID = System.getenv("INGEST_TENANT");
    private final static String TEST_INDEX_NAME = "testIndex01234";

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public IngestionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( IngestionTest.class );
    }

    @Override
    public void tearDown()
    {
        BingECommerceIngestion client = createClient();
        IndexResponse allIndexes = client.getAllIndexes(TENANT_ID);

        for(ResponseIndex index : allIndexes.indexes())
        {
            if (index.name().compareTo(TEST_INDEX_NAME) == 0)
            {
                client.deleteIndex(TENANT_ID, index.id());
            }
        }
    }

    /**
     * Rigourous Test :-)
     */
    public void testIndexes() throws Exception
    {
        tearDown();
        BingECommerceIngestion client = createClient();

        // Create an index
        IndexField field = new IndexField()
                .withName("testField01234")
                .withType(IndexFieldType.PRODUCT_ID);
        Index newIndexReq = new Index()
                .withName(TEST_INDEX_NAME)
                .withDescription("Created with java sdk unit testing")
                .withFields(Arrays.asList(field));

        IndexResponse createResponse = client.createIndex(TENANT_ID, null, newIndexReq);
        assertNotNull("Expected non-null response", createResponse);
        assertNotNull("Expected non-null indexes response", createResponse.indexes());
        assertEquals(1, createResponse.indexes().size());

        // Get All Indexes
        IndexResponse allIndexes = client.getAllIndexes(TENANT_ID);
        
        assertNotNull("Expected non-null response", allIndexes);
        assertNotNull("Expected non-null indexes response", allIndexes.indexes());
        assertFalse(allIndexes.indexes().size() == 0);

        // Get Specific Index
        IndexResponse getIndexResponse = client.getIndex(TENANT_ID, createResponse.indexes().get(0).id());
        assertNotNull("Expected non-null response", getIndexResponse);
        assertNotNull("Expected non-null indexes response", getIndexResponse.indexes());
        assertEquals(1, getIndexResponse.indexes().size());

        // Update Index
        IndexField extraField = new IndexField()
                .withName("extraTestField01234")
                .withType(IndexFieldType.NUMBER);
        newIndexReq.withFields(Arrays.asList(field, extraField));
        IndexResponse updateResponse = client.updateIndex(TENANT_ID, createResponse.indexes().get(0).id(), null, newIndexReq);
        assertNotNull("Expected non-null response", updateResponse);
        assertNotNull("Expected non-null indexes response", updateResponse.indexes());
        assertEquals(1, updateResponse.indexes().size());
        assertEquals(2, updateResponse.indexes().get(0).fields().size());

        // Delete Index
        IndexResponse deleteResponse = client.deleteIndex(TENANT_ID, createResponse.indexes().get(0).id());
        assertNotNull("Expected non-null response", deleteResponse);
        assertNotNull("Expected non-null indexes response", deleteResponse.indexes());
        assertEquals(1, deleteResponse.indexes().size());
    }

    public void testPush() throws Exception
    {
        BingECommerceIngestion client = createClient();

        String indexId = EnsureTestIndex(client);

        PushDataUpdateResponse pushResponse = client.pushDataUpdate("Test Content", TENANT_ID, indexId);
        assertNotNull("Expected non-null response", pushResponse);
        assertNotNull("Expected non-null update id", pushResponse.updateId());

        PushUpdateStatusResponse statusResponse = client.pushDataStatus(TENANT_ID, indexId, pushResponse.updateId());
        assertNotNull("Expected non-null response", statusResponse);
        assertNotNull("Expected non-null update id", statusResponse.status());
    }

    private BingECommerceIngestion createClient()
    {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AppIdCredentialsInterceptor( System.getenv("INGEST_APPID") ))
                .addInterceptor(new TestTrafficInterceptor(System.out));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingECommerceIngestionImpl(httpClient, retrofit);
    }

    private String EnsureTestIndex(BingECommerceIngestion client)
    {
        IndexResponse allIndexes = client.getAllIndexes(TENANT_ID);

        for(ResponseIndex index : allIndexes.indexes())
        {
            if (index.name().compareTo(TEST_INDEX_NAME) == 0)
            {
                return index.id();
            }
        }

        IndexField field = new IndexField()
                .withName("testField01234")
                .withType(IndexFieldType.PRODUCT_ID);
        Index newIndexReq = new Index()
                .withName(TEST_INDEX_NAME)
                .withDescription("Created with java sdk unit testing")
                .withFields(Arrays.asList(field));

        IndexResponse createResponse = client.createIndex(TENANT_ID, null,newIndexReq);
        return createResponse.indexes().get(0).id();
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

            Response r = chain.proceed(modifiedRequest);
            return r;
        }
    }
}
