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

import com.fasterxml.jackson.databind.ObjectMapper;

public class AlertAPISample {
  private final static String TENANT_ID = System.getenv("TENANT_ID");
  private final static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
  private final static String INDEX_NAME = System.getenv("Sample_Index");
  private final static String Alert_ID = System.getenv("Alert_ID");
    public static void main(String[] args) {
        
    }

    public static void GetAlertOption()
    {
        try {
            String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/alerts/options";
      
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

    public static void GetAlertGroup()
    {
        try {
            String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/alerts/group";
      
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

    public static void CreateOrUpdateAlertGroup() throws IOException {
        try {
           
          Dictionary POST_PARAMS = new Hashtable();
          POST_PARAMS.put("enabled", true);
          POST_PARAMS.put("emailaddress", "user@domain.com");
          
          String json = new ObjectMapper().writeValueAsString(POST_PARAMS);
          System.out.println(json);
    
          String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/alerts/group";
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
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    
    public static void DeleteAlertGroup() throws IOException {
        try {
         
          String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/alerts/group";
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
    
      public static void GetAllAlert()
      {
          try {
              String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/indexes/"+INDEX_NAME+"/alerts";
        
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
  
      public static void GetAlert()
      {
          try {
              String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/indexes/"+INDEX_NAME+"/alerts/"+Alert_ID;
        
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

      public static void DeleteAlert() throws IOException {
        try {
         
          String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/indexes/"+INDEX_NAME+"/alerts/"+Alert_ID;
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

      public static void CreateOrUpdateAlert() throws IOException {
        try {
           
          Dictionary POST_PARAMS = new Hashtable();
          POST_PARAMS.put("enabled", true);
          POST_PARAMS.put("AggregationDurationInMinutes", 5);
          POST_PARAMS.put("FrequencyInMinutes", 5);
          POST_PARAMS.put("TimeWindowInMinutes", 5);
          POST_PARAMS.put("SeverityLevel", "Sev3");
          POST_PARAMS.put("AlertOperator", "GreaterThan");
          POST_PARAMS.put("AlertThreshold", 0);
          POST_PARAMS.put("TriggerBasis", "Total");
          POST_PARAMS.put("TriggerOperator", "GreaterThan");
          POST_PARAMS.put("TriggerThreshold", 0);
          
          String json = new ObjectMapper().writeValueAsString(POST_PARAMS);
          System.out.println(json);
    
          String customURL = "https://commerce.bing.com/api/devops/v1/"+TENANT_ID+"/indexes/"+INDEX_NAME+"/alerts/"+Alert_ID;
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
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      //Refresh Token API Code
      public static void RefreshTokenAPIRequest() throws IOException {
        try {
           
          Dictionary POST_PARAMS = new Hashtable();
          POST_PARAMS.put("refresh_token", "REFRESH TOKEN");
        
          String json = new ObjectMapper().writeValueAsString(POST_PARAMS);
          System.out.println(json);
    
          String customURL = "https://commerce.bing.com/api/admin/v1/tenants/"+TENANT_ID+"/refresh-token";
          URL obj = new URL(customURL);
    
          HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
          postConnection.setRequestMethod("POST");
          postConnection.setRequestProperty("Authorization", ACCESS_TOKEN);
          postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    
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
  
}