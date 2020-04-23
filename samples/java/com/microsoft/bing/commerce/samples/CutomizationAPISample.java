package com.microsoft.bing.commerce.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.CycleDetectingLockFactory.Policies;
import com.microsoft.bing.commerce.search.models.BoostExpression;

import retrofit2.http.POST;

public class CutomizationAPISample {
  private final static String TENANT_ID = System.getenv("TENANT_ID");
  private final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
  private final static String INDEX_NAME = System.getenv("Sample_Index");

  public static void main(String[] args) throws IOException {

  }

  public static void getSearchInstance() {
    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/searchinstance/"
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

  public static void createSearchInstance() throws IOException {
    try {
      Dictionary POST_PARAMS = new Hashtable();
      POST_PARAMS.put("searchinstanceId", "BlackFridaySettings");
      String json = new ObjectMapper().writeValueAsString(POST_PARAMS);

      String customURL = "https://commerce.bing.com/api/customization/v1/searchinstance/"
      + TENANT_ID + "/indexes/"+ INDEX_NAME;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void deleteSearchInstance() throws IOException {
    String instanceId = "BlackFridaySettings";
    String customURL = "https://commerce.bing.com/api/customization/v1/searchinstance/"
    + TENANT_ID + "/indexes/"
    + INDEX_NAME + "?searchinstanceid="
    + instanceId;

    try {
      URL url = new URL(customURL);
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

  public static void getAllRule() {
    String instanceId = "BlackFridaySettings";
    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rules/" + TENANT_ID + "/indexes/"
          + INDEX_NAME + "?searchinstanceid=" + instanceId;
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

  public static void getRule() {
    String instanceId = "BlackFridaySettings";
    String rule = "ruleclothing";
    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rule/" + TENANT_ID + "/indexes/"
          + INDEX_NAME + "?rule=" + rule + "&searchinstanceid=" + instanceId;
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

  public static void deleteRule() throws IOException {
    String instanceId = "BlackFridaySettings";
    String rule = "ruleclothing";
    String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rule/" + TENANT_ID + "/indexes/"
        + INDEX_NAME + "?rule=" + rule + "&searchinstanceid=" + instanceId;

    try {
      URL url = new URL(customURL);
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

  public static void addSynonyms() throws IOException {
    try {
      Dictionary POST_PARAMS = new Hashtable();
      POST_PARAMS.put("searchInstanceId", "BlackFridaySettings");
      POST_PARAMS.put("synonymId", "outerwear");
      POST_PARAMS.put("synonyms", Arrays.asList("coat", "jacket", "suit"));

      String json = new ObjectMapper().writeValueAsString(POST_PARAMS);
      System.out.println(json);

      String customURL = "https://commerce.bing.com/api/customization/v1/synonym/" + TENANT_ID + "/indexes/"
          + INDEX_NAME;
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
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void getSynonyms() {
    String instanceId = "BlackFridaySettings";

    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/synonym/" + TENANT_ID + "/indexes/"
          + INDEX_NAME + "?searchinstanceid=" + instanceId;
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

  public static void deleteSynonym() throws IOException {
    String instanceId = "BlackFridaySettings";
    String synonymId = "outerwear";
    String customURL = "https://commerce.bing.com/api/customization/v1/synonym/" + TENANT_ID + "/indexes/" + INDEX_NAME
        + "?synonymid=" + synonymId + "&searchinstanceid=" + instanceId;

    try {
      URL url = new URL(customURL);
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

  public static void updateRedirects() throws IOException {

    Dictionary Search_Condition = new Hashtable();
    Search_Condition.put("_type", "StringCondition");
    Search_Condition.put("field", "query");
    Search_Condition.put("value", "men shirts");

    Dictionary POST_PARAMS = new Hashtable();
    POST_PARAMS.put("searchInstanceId", "BlackFridaySettingMSFT");
    POST_PARAMS.put("RedirectId", "ClothingRedirect");
    POST_PARAMS.put("SearchRequestCondition", Search_Condition);
    POST_PARAMS.put("RedirectUrl", "https://www.contoso.com/menshirts");

    String json = new ObjectMapper().writeValueAsString(POST_PARAMS);

    String customURL = "https://commerce.bing.com/api/customization/v1/redirect/" + TENANT_ID + "/indexes/"
        + INDEX_NAME;

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

  public static void getRedirect() {
    String instanceId = "BlackFridaySettings";

    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/redirect/" + TENANT_ID + "/indexes/"
          + INDEX_NAME + "?searchinstanceid=" + instanceId;
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

  public static void deleteRedirect() throws IOException {
    String instanceId = "BlackFridaySettings";
    String redirectId = "ClothingRedirect";

    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/redirect/" + TENANT_ID + "/indexes/"
          + INDEX_NAME + "?redirectId=" + redirectId + "&searchinstanceid=" + instanceId;
      URL url = new URL(customURL);
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

  public static void createUpdateARulePost() throws IOException {
    Dictionary Search_Request = new Hashtable();
    Search_Request.put("_type", "StringSetCondition");
    Search_Request.put("field", "query");
    Search_Request.put("values", Arrays.asList("shirts", "coats", "*"));

    Dictionary banner = new Hashtable();
    banner.put("type", "PlainText");
    banner.put("value", "Get 15% off Black Friday deals");

    Dictionary boostCondition = new Hashtable();
    boostCondition.put("_type", "StringCondition");
    boostCondition.put("field", "brand");
    boostCondition.put("value", "Fabrikam");

    Dictionary boost = new Hashtable();
    boost.put("boost", 500);
    boost.put("condition", boostCondition);

    Dictionary filterCondition = new Hashtable();
    filterCondition.put("_type", "StringCondition");
    filterCondition.put("field", "brand");
    filterCondition.put("operator", "Ne");
    filterCondition.put("value", "Contoso");

    Dictionary filter = new Hashtable();
    filter.put("_type", "ConditionBlock");
    filter.put("conditions", Arrays.asList(filterCondition));

    Dictionary POST_PARAMS = new Hashtable();
    POST_PARAMS.put("searchInstanceId", "BlackFridaySetting");
    POST_PARAMS.put("Rule", "ruleclothingMSFT");
    POST_PARAMS.put("Enabled", true);
    POST_PARAMS.put("description", "Black Friday clothing rule");
    POST_PARAMS.put("SearchRequestCondition", Search_Request);
    POST_PARAMS.put("Banner", banner);
    POST_PARAMS.put("Boosts", Arrays.asList(boost));
    POST_PARAMS.put("Filter", filter);
    POST_PARAMS.put("StartTimeUtc", "20200101040000");
    POST_PARAMS.put("EndTimeUtc", "20201231180000");

    String json = new ObjectMapper().writeValueAsString(POST_PARAMS);
    String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rule/" + TENANT_ID + "/indexes/"
        + INDEX_NAME;
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
}