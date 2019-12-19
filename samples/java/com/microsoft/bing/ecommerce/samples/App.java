package com.microsoft.bing.ecommerce.samples;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.bing.ecommerce.search.models.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.microsoft.bing.ecommerce.ingestion.BingECommerceIngestion;
import com.microsoft.bing.ecommerce.ingestion.implementation.BingECommerceIngestionImpl;
import com.microsoft.bing.ecommerce.ingestion.models.*;
import com.microsoft.bing.ecommerce.ingestion.util.AppIdCredentialsInterceptor;

import com.microsoft.bing.ecommerce.search.BingECommerceSearch;
import com.microsoft.bing.ecommerce.search.implementation.BingECommerceSearchImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    private final static String TENANT_ID = System.getenv("TENANT_ID");
    private final static String APPID = System.getenv("APPID");
    private final static String INDEX_NAME = "SampleIndex";

    public static void main( String[] args ) throws JsonProcessingException {
        BingECommerceIngestion ingestionClient = createIngestionClient();
        BingECommerceSearch searchClient = createSearchClient();

        String indexId = ensureIndex(ingestionClient);
        System.out.format("Working with index: %s.\n", indexId);

        // Push some data
        System.out.println("Pushing JSONArray data.");
        List<MyProduct> products = Arrays.asList(
            new MyProduct("1", "My First Product", "The first product I have", 100.0, "random text", 52.4),
            new MyProduct("2", "My Second Product", "The second product I have", 10.0, "another random text", 88.8)
        );
        pushData(ingestionClient, indexId, createJSON(products));

        System.out.println("Pushing ND-JSON data.");
        List<String> productJSONs = Arrays.asList(
            createJSON(products.get(0)),
            createJSON(products.get(1))
        );
        pushData(ingestionClient, indexId, String.join("\n", productJSONs.get(0), productJSONs.get(1)));

        System.out.println("Pushing CSV data.");
        pushData(ingestionClient, indexId, createCSV(products));

        // Search
        long getMatches = getSearch(searchClient, indexId);
        System.out.format("found [%d] matches with get.\n", getMatches);
        long postMatches = postSearch(searchClient, indexId);
        System.out.format("found [%d] matches with post.\n", postMatches);
    }

    private static BingECommerceIngestion createIngestionClient() {
        System.out.format("Creating the ingestion client with appid: %s.\n", APPID);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AppIdCredentialsInterceptor( APPID ));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingECommerceIngestionImpl(httpClient, retrofit);
    }

    private static BingECommerceSearch createSearchClient() {
        System.out.format("Creating the search client with appid: %s.\n", APPID);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AppIdCredentialsInterceptor( APPID ));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingECommerceSearchImpl(httpClient, retrofit);
    }

    private static String ensureIndex(BingECommerceIngestion client) {
        System.out.format("Trying to find the index with the name : %s.\n", INDEX_NAME);
        IndexResponse allIndexes = client.getAllIndexes(TENANT_ID);
        for(ResponseIndex index : allIndexes.indexes()) {
            if (index.name().compareTo(INDEX_NAME) == 0) {
                return index.id();
            }
        }

        System.out.println("Index not found, now creating one");

        // Prepare the index fields
        IndexField idField = new IndexField()
                .withName("ProductId")
                .withType(IndexFieldType.PRODUCT_ID)
                .withFilterable(true)
                .withRetrievable(true);
        IndexField titleField = new IndexField()
                .withName("ProductTitle")
                .withType(IndexFieldType.TITLE)
                .withRetrievable(true)
                .withSearchable(true);
        IndexField descriptionField = new IndexField()
                .withName("ProductDescription")
                .withType(IndexFieldType.DESCRIPTION)
                .withRetrievable(true)
                .withSearchable(true);
        IndexField priceField = new IndexField()
                .withName("ProductPrice")
                .withType(IndexFieldType.PRICE)
                .withRetrievable(true)
                .withSortable(true);
        IndexField urlField = new IndexField()
                .withName("ProductDetailsUrl")
                .withType(IndexFieldType.URL)
                .withRetrievable(true);
        IndexField arbitraryTextField = new IndexField()
                .withName("ArbitraryText")
                .withType(IndexFieldType.STRING)
                .withSearchable(true);
        IndexField arbitraryNumberField = new IndexField()
                .withName("ArbitraryNumber")
                .withType(IndexFieldType.NUMBER)
                .withFacetable(true);

        // Prepare the Index using the prepared fields
        Index newIndexReq = new Index()
                .withName(INDEX_NAME)
                .withDescription("Created with java sdk unit testing")
                .withFields(Arrays.asList(idField, titleField, descriptionField, priceField, urlField, arbitraryTextField, arbitraryNumberField));

        // Create the index
        IndexResponse createResponse = client.createIndex(TENANT_ID, null,newIndexReq);
        return createResponse.indexes().get(0).id();
    }

    private static String pushData(BingECommerceIngestion client, String indexId, String content) {

        PushDataUpdateResponse pushResponse = client.pushDataUpdate(content, TENANT_ID, indexId);

        return pushResponse.updateId();

    }

    private static <T> String createJSON(T product) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(product);
    }

    private static String createCSV(List<MyProduct> products) throws JsonProcessingException {
        List<String> lines = new ArrayList<String>();
        for (MyProduct product : products) {
            String line = product.ProductId + "," + product.ProductTitle + ","+ product.ProductDescription + ","+ product.ProductPrice.toString() + "," + product.arbitraryText + "," + product.arbitraryNumber.toString();
            lines.add(line);
        }

        return String.join("\n", lines);
    }

    private static long postSearch(BingECommerceSearch client, String indexId) {

        RequestQuery query = new RequestQuery().withFilter(
                new StringSetCondition()
                        .withValues(Arrays.asList("1", "2"))
                        .withField("ProductId")
        );

        ECommerceSearchPostRequest request = new ECommerceSearchPostRequest()
                .withQuery(query)
                .withItems(new RequestItems().withSelect( Arrays.asList("*") ))
                .withAggregations(Arrays.asList(new RequestDiscoverFacets().withName("discovered facets")));

        ECommerceSearchResponse response = client.searchs().post(TENANT_ID, indexId, request);

        return response.items().totalEstimatedMatches();
    }

    private static long getSearch(BingECommerceSearch client, String indexId) {

        ECommerceSearchResponse response = client.searchs().get(TENANT_ID, indexId, null, null,"first", null, null, null, null, null, null, null, null);

        return response.items().totalEstimatedMatches();
    }

}
