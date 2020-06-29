package com.microsoft.bing.commerce.samples;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DevOpsAPI {
  private final static String TENANT_ID = System.getenv("TENANT_ID");
  private final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
  private final static String INDEX_NAME = System.getenv("Sample_Index");
    public static void main(String[] args) throws IOException {
        CompareCommits();
    }

    public static void getCommits() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(
                "https://commerce.bing.com/api/devops/v1/{tenantId}/indexes/{indexId}/scm?")
                .method("GET", null)
                .addHeader("Authorization",
                        ACCESS_TOKEN)
                .addHeader("Content-Type", "application/zip")
                .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7").build();
        Response response = client.newCall(request).execute();
    }

    public static void CompareCommits() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(
                "https://commerce.bing.com/api/ingestion/v1/{tenantId}/indexes/{indexId}/scm/{commitId1}/diff/{commitId2}")
                .method("GET", null)
                .addHeader("Authorization",
                        ACCESS_TOKEN)
                .addHeader("Content-Type", "application/zip")
                .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7").build();
        Response response = client.newCall(request).execute();
    }

    public static void GetDeployments() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(
                "https://commerce.bing.com/api/ingestion/v1/{tenantId}/indexes/{indexId}/deployments")
                .method("GET", null)
                .addHeader("Authorization",
                        ACCESS_TOKEN)
                .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7").build();
        Response response = client.newCall(request).execute();
    }

    public static void GetDeploymentbyID() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(
                "https://commerce.bing.com/api/ingestion/v1/{tenantId}/indexes/{indexId}/deployments/{deploymentId}")
                .method("GET", null)
                .addHeader("Authorization",
                        ACCESS_TOKEN)
                .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7").build();
        Response response = client.newCall(request).execute();
    }

    public static void createCommit() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/zip");
        RequestBody body = RequestBody.create(mediaType, "<file contents here>");
Request request = new Request.Builder()
  .url("https://commerce.bing.com/api/devops/v1/{tenantId}/indexes/{indexId}/scm")
  .method("POST", body)
  .addHeader("x-ms-author", "Sergey V")
  .addHeader("x-ms-author-message", "New index")
  .addHeader("Authorization", ACCESS_TOKEN)
  .addHeader("Content-Type", "application/zip")
  .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7")
  .build();
Response response = client.newCall(request).execute();
    }

    public static void DeployCommit() throws IOException
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "");
Request request = new Request.Builder()
  .url("https://commerce.bing.com/api/ingestion/v1/{tenantId}/indexes/{indexId}/deployments/{commitId}")
  .method("POST", body)
  .addHeader("Authorization", ACCESS_TOKEN)
  .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7")
  .build();
Response response = client.newCall(request).execute();
    }

    public static void ReDeployCommit() throws IOException
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "");
Request request = new Request.Builder()
  .url("https://commerce.bing.com/api/devops/v1/{tenantId}/indexes/{indexId}/deployments/{deploymentId}")
  .method("PUT", body)
  .addHeader("Authorization", ACCESS_TOKEN)
  .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7")
  .build();
Response response = client.newCall(request).execute();
    }

    public static void ExportIndexVersion() throws IOException
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
Request request = new Request.Builder()
  .url("https://commerce.bing.com/api/ingestion/v1/{tenantId}/indexes/{indexId}/scm/{commitId}/zip")
  .method("GET", null)
  .addHeader("Authorization", ACCESS_TOKEN)
  .addHeader("Content-Type", "application/zip")
  .addHeader("Cookie", "MUIDB=05EB1B523F3063030EFE15843E9C62E7")
  .build();
Response response = client.newCall(request).execute();
    }
}