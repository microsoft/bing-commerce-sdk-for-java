package com.microsoft.bing.commerce.samples;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import com.microsoft.bing.commerce.search.BingCommerceSearch;
import com.microsoft.bing.commerce.search.implementation.BingCommerceSearchImpl;
import com.microsoft.bing.commerce.search.models.*;
import com.microsoft.bing.commerce.search.util.AccessTokenInterceptor;

public class ProductSearchSample {
    private final static String TENANT_ID = System.getenv("TENANT_ID");
    private final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private final static String indexId = "INDEX_ID";
    public static void main( String[] args ) throws JsonProcessingException {
        
        BingCommerceSearch searchClient = createSearchClient();

        long getMatches = postSearch(searchClient);
        System.out.format("found [%d] matches with get.\n", getMatches);
        
    }

    private static long getSearch(BingCommerceSearch client) {
        String q="headphones";
        String tenant=TENANT_ID;
        String index=indexId; 
        String mkt=null;
        String setlang=null;
        String select="title,brand";
        String orderby=null;
        Integer top=20;
        Integer skip=10;
        Boolean discoverfacets=false;
        Boolean alteration=true;
        Boolean debug = false; 
        String searchinstanceid=null;

        CommerceSearchResponse response=client.searchs().get(q, tenant, index, mkt, setlang, select, orderby, top, skip, discoverfacets, alteration, debug, searchinstanceid);
        return response.items().totalEstimatedMatches();
    }

    private static long postSearch(BingCommerceSearch client) {
        String matchAll="clothing";
        List<String> selectItem= Arrays.asList("_itemId", "title");

        RequestQuery query= new RequestQuery().withMatchAll(matchAll);

        CommerceSearchPostRequest request= new CommerceSearchPostRequest().withQuery(query)
        .withItems(new RequestItems().withSelect(selectItem));

        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
    }

    private static long Search_MatchingSpecificFields(BingCommerceSearch client) {
       List<String> includeList= Arrays.asList("Material");
        String icludeValue="suede";
        List<String> selectItem= Arrays.asList("_itemId", "title", "description");

        RequestQuery query= new RequestQuery().withValue(
            new RequestBingMatchStreams().withInclude(includeList).withValue(icludeValue));

        CommerceSearchPostRequest request= new CommerceSearchPostRequest().withQuery(query)
        .withItems(new RequestItems().withSelect(selectItem));

        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
    }

    private static long Filter(BingCommerceSearch client) {
        String matchAll="clothing";
        String field="brand";
        String value="Microsoft";
        
        List<String> selectItem= Arrays.asList("_itemId", "brand", "title");

        ConditionBase filter = new StringCondition().withValue(value)
        .withOperator(EquivalenceOperator.NE)
        .withField(field);


        RequestQuery query= new RequestQuery().withMatchAll(matchAll).withFilter(filter);
 
        CommerceSearchPostRequest request= new CommerceSearchPostRequest().withQuery(query)
         .withItems(new RequestItems().withSelect(selectItem));
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long FilterConditionBlock(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId", "title", "shortDescription");
       
        List<ConditionBase> conditions=Arrays.asList(
            new StringCondition().withValue("HP").withField("brand"),
            new StringCondition().withValue("Acer").withField("brand")
        );

        ConditionBlock filter=new ConditionBlock().withConditions(conditions).withOperator(LogicalOperator.OR);
     
        RequestQuery query= new RequestQuery().withMatchAll(matchAll).withFilter(filter);
 
        CommerceSearchPostRequest request= new CommerceSearchPostRequest().withQuery(query)
         .withItems(new RequestItems().withSelect(selectItem));
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long Faceting(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId", "title", "shortDescription");
        List<RequestAggregationBase> aggregations=Arrays.asList(
            new RequestFacet().withField("field").withName("name")
        );
       
        RequestQuery query= new RequestQuery().withMatchAll(matchAll);
 
        CommerceSearchPostRequest request= new CommerceSearchPostRequest().withQuery(query)
         .withItems(new RequestItems().withSelect(selectItem).withTop(5)).withAggregations(aggregations);
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long Faceting_WithRageInterval(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId", "title", "shortDescription");

        List<RequestAggregationBase> aggregations=Arrays.asList(
            new RequestRangeFacet().withInterval(500)
            .withField("Name of field on which faceting will be perormend")
            .withName("name")
        );
                
        RequestQuery query= new RequestQuery().withMatchAll(matchAll);
 
        CommerceSearchPostRequest request= new CommerceSearchPostRequest().withQuery(query)
         .withItems(new RequestItems().withSelect(selectItem).withTop(5)).withAggregations(aggregations);
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long Boosting(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId", "_score", "brand", "title");

        List<BoostExpression> boosts = Arrays.asList(
            new BoostExpression().withCondition(new StringCondition().withValue("Microsoft").withField("brand")).withBoost(100.0)
            );
             
        RequestQuery query= new RequestQuery().withMatchAll(matchAll).withBoosts(boosts);

        CommerceSearchPostRequest request=new CommerceSearchPostRequest().withQuery(query)
        .withItems(new RequestItems().withSelect(selectItem).withTop(5));
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long Burying(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId", "_score", "brand", "title");

        List<BoostExpression> boosts = Arrays.asList(
            new BoostExpression().withCondition(new StringCondition().withValue("Microsoft").withField("brand")).withBoost(-100.0)
            );
             
        RequestQuery query= new RequestQuery().withMatchAll(matchAll).withBoosts(boosts);

        CommerceSearchPostRequest request=new CommerceSearchPostRequest().withQuery(query)
        .withItems(new RequestItems().withSelect(selectItem).withTop(5));
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long Sorting(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId","title", "shortDescription");
   
        RequestQuery query= new RequestQuery().withMatchAll(matchAll);

        CommerceSearchPostRequest request=new CommerceSearchPostRequest().withQuery(query)
        .withItems(new RequestItems().withSelect(selectItem).withOrderBy("rating desc,baseRate"));
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }

     private static long Paging(BingCommerceSearch client) {
        String matchAll="laptop";
        
        List<String> selectItem= Arrays.asList("_itemId","title", "shortDescription");
   
        RequestQuery query= new RequestQuery().withMatchAll(matchAll);

        CommerceSearchPostRequest request=new CommerceSearchPostRequest().withQuery(query)
        .withItems(new RequestItems().withSelect(selectItem).withTop(10).withSkip(10));
 
        CommerceSearchResponse response = client.searchs().post(request, TENANT_ID, indexId);
        return response.items().totalEstimatedMatches();
     }
     

    private static BingCommerceSearch createSearchClient() {
        System.out.format("Creating the search client with access token: %s.\n", ACCESS_TOKEN);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor( new SimpleAccessTokenProvider(ACCESS_TOKEN) ));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingCommerceSearchImpl(httpClient, retrofit);
    }
    
}