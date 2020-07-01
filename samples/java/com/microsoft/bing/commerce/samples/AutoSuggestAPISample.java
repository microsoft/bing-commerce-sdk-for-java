package com.microsoft.bing.commerce.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AutoSuggestAPISample {
  private final static String TENANT_ID = System.getenv("TENANT_ID");
  private final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
  private final static String INDEX_NAME = System.getenv("Sample_Index");

  public static void main(String[] args) throws IOException {
  }

  public static void request() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggest/v1/"
      + TENANT_ID + "/indexes/" + INDEX_NAME + "/?q=Contoso&count=2&source=logs";

      URL url = new URL(customURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", ACCESS_TOKEN);

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

  public static void indexRequests() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
      + TENANT_ID + "/indexes/"+ INDEX_NAME;
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", ACCESS_TOKEN);

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

  public static void getIndex() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
      + TENANT_ID + "/indexes/"+ INDEX_NAME;
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", ACCESS_TOKEN);

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Faile : HTTP error code : " + conn.getResponseCode());
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

  public static void addUpdateIndexAsync() throws IOException {
    Dictionary POST_PARAMS = new Hashtable();
    POST_PARAMS.put("enabled", true);
    POST_PARAMS.put("customFilters", Arrays.asList("Locale", "AverageRatingCountOverall","BasePrice"));
    POST_PARAMS.put("customPostFilters", Arrays.asList("Locale", "AverageRatingCountOverall","BasePrice","IsMasterProduct","ProductReleaseDate"));

    String json = new ObjectMapper().writeValueAsString(POST_PARAMS);
    
    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
    + TENANT_ID + "/indexes/" + INDEX_NAME;
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("PUT");
    postConnection.setRequestProperty("Authorization", ACCESS_TOKEN);
    postConnection.setRequestProperty("Content-Type", "application/json");
    postConnection.setDoOutput(true);

    OutputStream os = postConnection.getOutputStream();
    os.write(json.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
  }

  public static void deleteIndex() throws IOException {
    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
    + TENANT_ID + "/indexes/" + INDEX_NAME;
    URL url = new URL(customURL);

    try {
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("DELETE");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", ACCESS_TOKEN);

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

  public static void getBlockedSuggestions() {
    try {
      String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
      + TENANT_ID + "/indexes/" + INDEX_NAME + "/blocking";
      URL url = new URL(customURL);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.setRequestProperty("Authorization", ACCESS_TOKEN);

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

  public static void createBlockedSuggestions() throws IOException {
    Dictionary blockedSuggestion1 = new Hashtable();
    blockedSuggestion1.put("query", "example");
    blockedSuggestion1.put("matchingtype", "Exact");

    Dictionary blockedSuggestion2 = new Hashtable();
    blockedSuggestion2.put("query", "blocked suggestion");
    blockedSuggestion2.put("matchingtype", "Contains");

    Dictionary POST_PARAMS = new Hashtable();
    POST_PARAMS.put("BlockedSuggestions", Arrays.asList(blockedSuggestion1, blockedSuggestion2));

    String json = new ObjectMapper().writeValueAsString(POST_PARAMS);

    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
    + TENANT_ID + "/indexes/" + INDEX_NAME + "/blocking";
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("Authorization", ACCESS_TOKEN);
    postConnection.setRequestProperty("Content-Type", "application/json");
    postConnection.setDoOutput(true);

    OutputStream os = postConnection.getOutputStream();
    os.write(json.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
  }

  public static void updateBlockedSuggestions() throws IOException {
    Dictionary bs1 = new Hashtable();
    bs1.put("id", "85e1d36d-c044-408e-9bc4-1696c3460f57");
    bs1.put("query", "blocked suggestion");
    bs1.put("matchingType", "Contains");

    Dictionary POST_PARAMS = new Hashtable();
    POST_PARAMS.put("BlockedSuggestions", Arrays.asList(bs1));

    String json = new ObjectMapper().writeValueAsString(POST_PARAMS);

    String customURL = "https://commerce.bing.com/api/autosuggestauthoring/v1/"
    + TENANT_ID + "/indexes/" + INDEX_NAME + "/blocking";
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("PUT");
    postConnection.setRequestProperty("Authorization", ACCESS_TOKEN);
    postConnection.setRequestProperty("Content-Type", "application/json");
    postConnection.setDoOutput(true);

    OutputStream os = postConnection.getOutputStream();
    os.write(json.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
  }

  public static void deleteBlockedSuggestions() throws IOException {
    Dictionary POST_PARAMS = new Hashtable();
    POST_PARAMS.put("ids", Arrays.asList("87e1d36d-c944-404e-9bc4-1695c3460h57"));

    String json = new ObjectMapper().writeValueAsString(POST_PARAMS);

    String customURL = "https://www.bingapis.com/api/v1/retail/autosuggestauthoring/"
    + TENANT_ID + "/indexes/"+ INDEX_NAME + "/blocking";
    URL obj = new URL(customURL);

    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("DELETE");
    postConnection.setRequestProperty("Authorization", ACCESS_TOKEN);
    postConnection.setRequestProperty("Content-Type", "application/json");
    postConnection.setDoOutput(true);

    OutputStream os = postConnection.getOutputStream();
    os.write(json.getBytes());
    os.flush();
    os.close();

    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
  }
}
