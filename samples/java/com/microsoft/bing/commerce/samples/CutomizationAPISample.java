package com.microsoft.bing.commerce.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CutomizationAPISample {
  private static String tenantId = "Tenant ID";
  private static String indexId = "Index ID";
  private static String access_token = "Access Token";

  public static void main(String[] args) throws IOException {
  }

  public static void GetSearchInstanceAsync() {
    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/searchinstance/"
                         + tenantId + "/indexes/"+ indexId;
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

  public static void CreateSearchInstanceAsync() throws IOException {
    
    final String POST_PARAMS = "{\n" + "    \"searchinstanceId\": \"BlackFridaySetting\",\r\n" + "\n}";
    
    String customURL = "https://commerce.bing.com/api/customization/v1/searchinstance/"
                         + tenantId + "/indexes/"+ indexId;

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

  public static void DeleteAsync() throws IOException {
    String instanceId = "BlackFridaySettings";
    String customURL = "https://commerce.bing.com/api/customization/v1/searchinstance/"
                         + tenantId + "/indexes/"+ indexId + "?searchinstanceid=" + instanceId;

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

  public static void GetAllRuleAsync() {
    String instanceId = "BlackFridaySettings";
    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rules/"
                        + tenantId + "/indexes/"+ indexId + "?searchinstanceid=" + instanceId;
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

  public static void GetRuleAsync() {
    String instanceId = "BlackFridaySettings";
    String rule = "ruleclothing";
    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rule/"
                        +tenantId+"/indexes/"+indexId+"?rule="+rule+"&searchinstanceid="+instanceId;
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

  public static void DeleteRule_Async() throws IOException {
    String instanceId = "BlackFridaySettings";
    String rule = "ruleclothing";
    String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rule/"+
    tenantId+"/indexes/"+indexId+"?rule="+rule+"&searchinstanceid="+instanceId;

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

  public static void AddSynonyms_Async() throws IOException {
    final String POST_PARAMS = "{\n" + "    \"searchinstanceId\": \"BlackFridaySettings\",\r\n"
        + "    \"synonymId\": \"outerwear\",\r\n" + "    \"synonyms\": [\"coat\",\"jacket\",\"suit\"]\r\n" + "\n}";

    String customURL = "https://commerce.bing.com/api/customization/v1/synonym/" + tenantId + "/indexes/" + indexId;

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

  public static void GetSynonyms_Async() {
    String instanceId = "BlackFridaySettings";

    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/synonym/"
                         + tenantId + "/indexes/" + indexId
                          + "?searchinstanceid=" + instanceId;
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

  public static void DeleteSynAsync() throws IOException {
    String instanceId = "BlackFridaySettings";
    String synonymId = "outerwear";
    String customURL = "https://commerce.bing.com/api/customization/v1/synonym/"
                      + tenantId + "/indexes/" + indexId
                          + "?synonymid=" + synonymId + "&searchinstanceid=" + instanceId;

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

  public static void UpdateRedirects_Async() throws IOException {

    final String POST_PARAMS = "{\n" + "    \"searchinstanceId\": \"BlackFridaySettings\",\r\n"
        + "    \"RedirectId\": \"ClothingRedirect\",\r\n" + "    \"SearchRequestCondition\": {\n"
        + "    \"_type\": \"StringCondition\",\r\n" + "    \"field\": \"query\",\r\n"
        + "    \"value\": \"men shirts\"\r\n" + "\n},\n" +

        "    \"RedirectUrl\": \"https://www.contoso.com/menshirts\"\r\n}";

    String customURL = "https://commerce.bing.com/api/customization/v1/redirect/" + tenantId +
                       "/indexes/" + indexId;

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

  public static void GetRedirectAsync() {
    String instanceId = "BlackFridaySettings";

    try {
      String customURL = "https://commerce.bing.com/api/customization/v1/redirect/" 
                        + tenantId + "/indexes/" + indexId
                            + "?searchinstanceid=" + instanceId;

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

  public static void DeleteRedirect() throws IOException {
    String instanceId = "BlackFridaySettings";
    String redirectId = "ClothingRedirect";
    String customURL = "https://commerce.bing.com/api/customization/v1/redirect/"
                        + tenantId + "/indexes/" + indexId
                            + "?redirectId=" + redirectId + "&searchinstanceid=" + instanceId;

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


public static void CreateUpdateARulePostAsync() throws IOException {

    final String POST_PARAMS = "{\n" + "    \"SearchInstanceId\": \"BlackFridaySettings\",\r\n"
        + "    \"Rule\": \"ruleclothing\",\r\n"  
        + "    \"Enabled\": true,\r\n" 
        + "    \"description\": \"Black Friday clothing rule\",\r\n" +
        "    \"SearchRequestCondition\": {\n"
        + "    \"_type\": \"StringSetCondition\",\r\n" + "    \"field\": \"query\",\r\n"
        + "    \"values\": [\"shirts\",\"coats\",\"*\"] \r\n" + "\n},\n"
        +"     \"Banner\":{\"type\":\"PlainText\",\"value\":\"Get 15% off Black Friday deals\"},\r\n"
        +"     \"Boosts\":[{\"boost\":500,\"condition\":{\"_type\":\"StringCondition\",\r\n\"field\":\"brand\",\r\n\"value\":\"Fabrikam\"} }],\r\n"
        +"     \"Filter\":{\"_type\":\"ConditionBlock\",\"conditions\":[{\"_type\":\"StringCondition\",\r\n\"field\":\"brand\",\r\n\"operator\":\"Ne\",\r\n\"value\":\"Contoso\"} ]},\r\n"
        +"    \"StartTimeUtc\": \"20200101040000\",\r\n"
        +"    \"EndTimeUtc\": \"20201231180000\"\r\n}";

    String customURL = "https://commerce.bing.com/api/customization/v1/businessrules/rule/"+tenantId+"/indexes/"+indexId;
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
}