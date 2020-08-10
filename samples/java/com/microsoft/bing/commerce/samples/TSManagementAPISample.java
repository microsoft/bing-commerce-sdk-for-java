package com.microsoft.bing.commerce.samples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.microsoft.bing.commerce.ingestion.BingCommerceIngestion;
import com.microsoft.bing.commerce.ingestion.implementation.BingCommerceIngestionImpl;
import com.microsoft.bing.commerce.ingestion.models.TransformationConfigResponse;
import com.microsoft.bing.commerce.ingestion.models.TransformationTryoutResponse;
import com.microsoft.bing.commerce.search.util.AccessTokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class TSManagementAPISample {
    public final static String TENANT_ID = System.getenv("TENANT_ID");
    public final static String SUBSCRIPTION_ID = System.getenv("SUBSCRIPTION_ID");
    public final static String INDEX_NAME = System.getenv("SAMPLE_INDEX");

    public static void main(String[] args) throws IOException {
        BingCommerceIngestion ingestionClient = createIngestionClient();

    }

    private static void createScript(BingCommerceIngestion ingestionClient) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Path to the content index on your local file system"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        String content = stringBuilder.toString();
        
        TransformationConfigResponse createScriptResponse = ingestionClient
        .createOrUpdateTransformationConfig(content, TENANT_ID, INDEX_NAME);
    }

    private static void retrieveScript(BingCommerceIngestion ingestionClient) {
        TransformationConfigResponse readScriptResponse = ingestionClient.getTransformationConfig(TENANT_ID,
                INDEX_NAME);
    }

    private static void deleteScrip(BingCommerceIngestion ingestionClient) {
        TransformationConfigResponse readScriptResponse = ingestionClient.deleteTransformationConfig(TENANT_ID,
                INDEX_NAME);
    }

    private static void testTryoutTransfromationApi(BingCommerceIngestion ingestionClient) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Path to the content index on your local file system"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        String script = stringBuilder.toString();

        BufferedReader bufferReader = new BufferedReader(new FileReader("Path to the content index on your local file system"));
        StringBuilder stringBuilderObj = new StringBuilder();
        String lines = null;
        String lineSeapartor = System.getProperty("line.separator");
        
        while ((lines = bufferReader.readLine()) != null)
        {
            stringBuilderObj.append(lines);
            stringBuilderObj.append(lineSeapartor);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        bufferReader.close();
        String data = stringBuilder.toString();

        TransformationConfigResponse createScriptResponse = ingestionClient.uploadTryOutConfig(script);
        TransformationTryoutResponse executeResponse = ingestionClient.executeTryOutConfig(data,
                createScriptResponse.tryOutId());
    }

    private static BingCommerceIngestion createIngestionClient() {
        System.out.format("Creating the ingestion client with access token %s.\n", SUBSCRIPTION_ID);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor(new SimpleAccessTokenProvider(SUBSCRIPTION_ID)));
        Retrofit.Builder retrofit = new Retrofit.Builder();

        return new BingCommerceIngestionImpl(httpClient, retrofit);
    }
}