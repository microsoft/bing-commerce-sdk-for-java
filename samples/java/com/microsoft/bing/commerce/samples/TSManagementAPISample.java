package com.microsoft.bing.commerce.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.microsoft.bing.commerce.ingestion.BingCommerceIngestion;
import com.microsoft.bing.commerce.ingestion.implementation.BingCommerceIngestionImpl;
import com.microsoft.bing.commerce.ingestion.models.*;

import com.microsoft.bing.commerce.search.BingCommerceSearch;
import com.microsoft.bing.commerce.search.implementation.BingCommerceSearchImpl;
import com.microsoft.bing.commerce.search.models.*;
import com.microsoft.bing.commerce.search.util.AccessTokenInterceptor;
import com.microsoft.bing.commerce.search.util.AccessTokenProvider;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class TSManagementAPISample {
    public final static String TENANT_ID = System.getenv("TENANT_ID");
    public final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    public final static String INDEX_NAME = "SampleIndex";

    public static void main(String[] args) throws IOException {
    BingCommerceIngestion ingestionClient = createIngestionClient();
    
}

private static void CreateScript(BingCommerceIngestion ingestionClient)throws IOException
{
    File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt"); 
    BufferedReader br = new BufferedReader(new FileReader(file)); 
    
    String script= br.readLine();
    br.close();
    TransformationConfigResponse createScriptResponse=ingestionClient.createOrUpdateTransformationConfig(script, TENANT_ID, INDEX_NAME);
}

private static void RetrieveScript(BingCommerceIngestion ingestionClient)
{
      TransformationConfigResponse readScriptResponse= ingestionClient.getTransformationConfig(TENANT_ID, INDEX_NAME);
}

private static void DeleteScrip(BingCommerceIngestion ingestionClient)
{
      TransformationConfigResponse readScriptResponse= ingestionClient.deleteTransformationConfig(TENANT_ID, INDEX_NAME);
}

private static void TestTryoutTransfromationApi(BingCommerceIngestion ingestionClient)throws IOException
{
    File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt"); 
    BufferedReader br = new BufferedReader(new FileReader(file)); 
    
    String script= br.readLine();
    br.close();

    File datafile = new File("C:\\Users\\pankaj\\Desktop\\test.txt"); 
    BufferedReader brData = new BufferedReader(new FileReader(datafile)); 
    
    String data= brData.readLine();
    brData.close();

    TransformationConfigResponse createScriptResponse=ingestionClient.uploadTryOutConfig(script);
    TransformationTryoutResponse executeResponse=ingestionClient.executeTryOutConfig(data, createScriptResponse.tryOutId());
}

private static BingCommerceIngestion createIngestionClient() {
    System.out.format("Creating the ingestion client with access token %s.\n", ACCESS_TOKEN);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(new AccessTokenInterceptor( new SimpleAccessTokenProvider(ACCESS_TOKEN) ));
    Retrofit.Builder retrofit = new Retrofit.Builder();

    return new BingCommerceIngestionImpl(httpClient, retrofit);
}

}