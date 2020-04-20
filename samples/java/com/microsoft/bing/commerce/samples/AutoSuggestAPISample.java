package com.microsoft.bing.commerce.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AutoSuggestAPISample {
    private static String indexId = "Index Id";
    private static String tenantId = "Tenant Id";
    private static String access_token = "access_token";
public static void main(String[] args) throws IOException {
}

public static void Request() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggest/v1/"+tenantId+"/indexes/"+indexId+"/?q=Contoso&count=2&source=logs";
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", access_token);

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (MalformedURLException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }
  }

public static void IndexRequests() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId;
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", access_token);

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (MalformedURLException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }
  }

public static void GetIndex() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId;
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", access_token);

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (MalformedURLException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }
  }

public static void Add_UpdateIndexAsync() throws IOException {
    
    final String POST_PARAMS = "{\n" + "    \"enabled\": true,\r\n"
                                    + "    \"customFilters\":[\"brand\",\"model\"]\n}";
    
    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId;
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("PUT");
    postConnection.setRequestProperty("Authorization", access_token);
    postConnection.setRequestProperty("Content-Type", "application/json");

    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

  }

public static void DeleteIndex() throws IOException {
    
    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId;

    try {
      URL url = new URL(customURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("DELETE");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", access_token);

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (MalformedURLException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

public static void GetBlockedSuggestions() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId+"/blocking";
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", access_token);

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (MalformedURLException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }
  }

public static void CreateBlockedSuggestions() throws IOException {
    
    final String POST_PARAMS = "{\n" 
                                 + "\"BlockedSuggestions\":\n[\n{\"query\":\"example\",\n\r\"matchingtype\":\"Exact\"},\n"
                                 + "{\"query\":\"blocked suggestion\",\r\n\"matchingtype\":\"Contains\"}    \n]\n\r}";
    
    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId+"/blocking";
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("Authorization", access_token);
    postConnection.setRequestProperty("Content-Type", "application/json");

    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

  }

public static void UpdateBlockedSuggestions() throws IOException {
    
    final String POST_PARAMS = "{\n" + "    \"BlockedSuggestions\":\n "
                                +"  [\n    {\n\r    \"id\":\"85e1d36d-c044-408e-9bc4-1696c3460f57\","
                                    +"\n    \"query\":\"blocked suggestion\","
                                    +"\n    \"matchingType\":\"Contains\"\n    }\n  ]\n}";
    
    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"+tenantId+"/indexes/"+indexId+"/blocking";    
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("PUT");
    postConnection.setRequestProperty("Authorization", access_token);
    postConnection.setRequestProperty("Content-Type", "application/json");

    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

  }

public static void DeleteBlockedSuggestions() throws IOException {
   
    final String POST_PARAMS = "{\n" + "    \"ids\":\n "
    +"  [\n     \"87e1d36d-c944-404e-9bc4-1695c3460h57\"\n  ]  \n}";

String customURL = "https://www.bingapis.com/api/v1/retail/autosuggestauthoring/"+tenantId+"/indexes/"+indexId+"/blocking";
URL obj = new URL(customURL);

HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
postConnection.setRequestMethod("DELETE");
postConnection.setRequestProperty("Authorization", access_token);
postConnection.setRequestProperty("Content-Type", "application/json");

postConnection.setDoOutput(true);
OutputStream os = postConnection.getOutputStream();
os.write(POST_PARAMS.getBytes());
os.flush();
os.close();

int responseCode = postConnection.getResponseCode();
System.out.println("POST Response Code :  " + responseCode);
System.out.println("POST Response Message : " + postConnection.getResponseMessage());
  }
}