package com.microsoft.bing.commerce.samples;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.sym.Name;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.microsoft.bing.commerce.ingestion.BingCommerceIngestion;
import com.microsoft.bing.commerce.ingestion.implementation.BingCommerceIngestionImpl;
import com.microsoft.bing.commerce.ingestion.models.*;

import com.microsoft.bing.commerce.search.BingCommerceSearch;
import com.microsoft.bing.commerce.search.implementation.BingCommerceSearchImpl;
import com.microsoft.bing.commerce.search.models.*;
import com.microsoft.bing.commerce.search.util.AccessTokenInterceptor;
import com.microsoft.bing.commerce.search.util.AccessTokenProvider;

public class IngestionSampleCode {
    private final static String TENANT_ID = System.getenv("TENANT_ID");
    private final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private final static String INDEX_NAME = "SampleIndex";
    public static void main( String[] args ) throws JsonProcessingException {
        BingCommerceIngestion ingestionClient = createIngestionClient();

        AddIndexes(ingestionClient);
       
       
    }

    private static void AddIndexes(BingCommerceIngestion client)
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
                .withFields(Arrays.asList(idField, titleField, descriptionField, priceField, urlField, arbitraryTextField, arbitraryNumberField));
                // Create the index
        IndexResponse createResponse = client.createIndex(TENANT_ID, null,newIndexReq);
        createResponse.indexes().get(0).id();

    }
    
    private static void CreateIndex(BingCommerceIngestion client)
    {
        List<Region> region= new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field= new IndexField().withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true).withSortable(false);
        

        Index newIndexReq= new Index().withName("Contoso").withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail").withRegions(region).withFields(Arrays.asList(field));

        IndexResponse createResponse =client.createIndex(TENANT_ID, ACCESS_TOKEN, newIndexReq);
  
    }

    private static void UpdateAnIndex(BingCommerceIngestion client)
    {
        List<Region> region= new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field= new IndexField().withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true).withSortable(false);
        

        Index newIndexReq= new Index().withName("Contoso").withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail").withRegions(region).withFields(Arrays.asList(field));

        IndexResponse createResponse =client.createIndex(TENANT_ID, ACCESS_TOKEN, newIndexReq);

        newIndexReq.withFields(Arrays.asList(field));

        IndexResponse updateResponse=client.updateIndex(TENANT_ID, createResponse.indexes().get(0).id(), ACCESS_TOKEN, newIndexReq);
  
    }


    private static void DeleteIndex(BingCommerceIngestion client)
    {
        List<Region> region= new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field= new IndexField().withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true).withSortable(false);
        

        Index newIndexReq= new Index().withName("Contoso").withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail").withRegions(region).withFields(Arrays.asList(field));

        IndexResponse createResponse =client.createIndex(TENANT_ID, ACCESS_TOKEN, newIndexReq);

        IndexResponse deleteResponse=client.deleteIndex(TENANT_ID, createResponse.indexes().get(0).id());
    }

    private static void GetIndexDefinitionById(BingCommerceIngestion client)
    {
        List<Region> region= new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field= new IndexField().withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true).withSortable(false);
        

        Index newIndexReq= new Index().withName("Contoso").withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail").withRegions(region).withFields(Arrays.asList(field));

        IndexResponse createResponse =client.createIndex(TENANT_ID, ACCESS_TOKEN, newIndexReq);

        IndexResponse getIndexResponse=client.getIndex(TENANT_ID, createResponse.indexes().get(0).id());
    }

    private static void GetListOfIndexDefinition(BingCommerceIngestion client)
    {
        List<Region> region= new ArrayList<Region>();
        region.add(Region.NORTH_CENTRAL_US);
        region.add(Region.WEST_US);
        region.add(Region.EAST_US);

        IndexField field= new IndexField().withName("productId")
        .withType(IndexFieldType.PRODUCT_ID)
        .withFacetable(false)
        .withFilterable(false)
        .withRetrievable(true)
        .withSearchable(true).withSortable(false);
        

        Index newIndexReq= new Index().withName("Contoso").withDescription("Index definition for Contoso.com")
        .withSearchScenario("Retail").withRegions(region).withFields(Arrays.asList(field));

        IndexResponse createResponse =client.createIndex(TENANT_ID, ACCESS_TOKEN, newIndexReq);

        IndexResponse allIndexes=client.getAllIndexes(TENANT_ID);
    }

    private static void GetIngestionStatus(BingCommerceIngestion client)
    {
       
        PushDataUpdateResponse pushResponse= client.pushDataUpdate("The body of the POST is an Index object", "Your Tenant Id", "Index Id");
        PushUpdateStatusResponse response= client.pushDataStatus(TENANT_ID, "IndexID", pushResponse.updateId());
    }


    private static BingCommerceIngestion createIngestionClient() {
        System.out.format("Creating the ingestion client with access token %s.\n", ACCESS_TOKEN);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor( new SimpleAccessTokenProvider(ACCESS_TOKEN) ));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingCommerceIngestionImpl(httpClient, retrofit);
    }

}
