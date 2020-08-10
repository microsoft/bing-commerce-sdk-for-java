package com.microsoft.bing.commerce.samples;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import com.microsoft.bing.commerce.ingestion.BingCommerceIngestion;
import com.microsoft.bing.commerce.ingestion.implementation.BingCommerceIngestionImpl;
import com.microsoft.bing.commerce.ingestion.models.*;

import com.microsoft.bing.commerce.search.util.AccessTokenInterceptor;

public class IngestionSampleCode {
    private final static String TENANT_ID = System.getenv("TENANT_ID");
    private final static String SUBSCRIPTION_ID = System.getenv("SUBSCRIPTION_ID");
    private final static String INDEX_NAME = System.getenv("SAMPLE_INDEX");

    public static void main(String[] args ) throws JsonProcessingException {
        BingCommerceIngestion ingestionClient = createIngestionClient();      
    }

    private static void addIndexes(BingCommerceIngestion client)
    {
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
                .withFields(
                    Arrays.asList(
                        idField,
                        titleField,
                        descriptionField,
                        priceField,
                        urlField,
                        arbitraryTextField,
                        arbitraryNumberField));
                
        // Create the index
        IndexResponse createResponse = client.createIndex(TENANT_ID, SUBSCRIPTION_ID, newIndexReq);
        createResponse.indexes().get(0).id();
    }
    
    private static void createIndex(BingCommerceIngestion client)
    {
        List<Region> regions = Arrays.asList(Region.NORTH_CENTRAL_US,Region.WEST_US,Region.EAST_US);

        IndexField field = new IndexField()
        .withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true)
        .withSortable(false);

        Index newIndexReq = new Index()
        .withName("Contoso")
        .withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail")
        .withRegions(regions)
        .withFields(Arrays.asList(field));

        IndexResponse createRespon=client.createIndex(TENANT_ID, SUBSCRIPTION_ID, newIndexReq);
    }

    private static void updateAnIndex(BingCommerceIngestion client)
    {
        List<Region> region = new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);
        
        IndexField field = new IndexField()
        .withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true)
        .withSortable(false);
        
        Index newIndexReq = new Index()
        .withName("Contoso")
        .withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail")
        .withRegions(region)
        .withFields(Arrays.asList(field));
        
        IndexResponse createResponse = client.createIndex(TENANT_ID, SUBSCRIPTION_ID, newIndexReq);
        newIndexReq.withFields(Arrays.asList(field));
        IndexResponse updateResponse = client.updateIndex(
            TENANT_ID, createResponse.indexes().get(0).id(), SUBSCRIPTION_ID, newIndexReq);
        }
        
    private static void deleteIndex(BingCommerceIngestion client)
    {
        List<Region> regions = Arrays.asList(Region.NORTH_CENTRAL_US,Region.WEST_US,Region.EAST_US);

        IndexField field = new IndexField()
        .withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true)
        .withSortable(false);

        Index newIndexReq = new Index()
        .withName("Contoso")
        .withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail")
        .withRegions(regions)
        .withFields(Arrays.asList(field));
        
        IndexResponse createResponse = client.createIndex(TENANT_ID, SUBSCRIPTION_ID, newIndexReq);
        IndexResponse deleteResponse = client.deleteIndex(TENANT_ID, createResponse.indexes().get(0).id());
    }

    private static void getIndexDefinitionById(BingCommerceIngestion client)
    {
        List<Region> region = new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field = new IndexField()
        .withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true)
        .withSortable(false);

        Index newIndexReq = new Index()
        .withName("Contoso")
        .withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail")
        .withRegions(region)
        .withFields(Arrays.asList(field));

        IndexResponse createResponse = client.createIndex(TENANT_ID, SUBSCRIPTION_ID, newIndexReq);
        IndexResponse getIndexResponse = client.getIndex(TENANT_ID, createResponse.indexes().get(0).id());
    }

    private static void getListOfIndexDefinition(BingCommerceIngestion client)
    {
        List<Region> region = new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field = new IndexField()
        .withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true)
        .withSortable(false);

        Index newIndexReq = new Index()
        .withName("Contoso")
        .withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail")
        .withRegions(region)
        .withFields(Arrays.asList(field));

        IndexResponse createResponse = client.createIndex(TENANT_ID, SUBSCRIPTION_ID, newIndexReq);
        IndexResponse allIndexes = client.getAllIndexes(TENANT_ID);
    }

    private static void getIngestionStatus(BingCommerceIngestion client)
    {
        PushDataUpdateResponse pushResponse = client.pushDataUpdate("The body of the POST is an Index object", "Your Tenant Id", "Index Id");
        PushUpdateStatusResponse response = client.pushDataStatus(TENANT_ID, "IndexID", pushResponse.updateId());
    }

    private static BingCommerceIngestion createIngestionClient() {
        System.out.format("Creating the ingestion client with access token %s.\n", SUBSCRIPTION_ID);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor( new SimpleAccessTokenProvider(SUBSCRIPTION_ID) ));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingCommerceIngestionImpl(httpClient, retrofit);
    }

}
