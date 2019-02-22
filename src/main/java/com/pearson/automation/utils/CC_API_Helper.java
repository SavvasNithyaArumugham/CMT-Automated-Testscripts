package com.pearson.automation.utils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class CC_API_Helper extends RESTServiceBase {

	public static RESTServiceBase webCredentials_rest = new RESTServiceBase();

	LinkedHashMap<String, LinkedHashMap<String, String>> hashMap = null;
	LinkedHashMap<String, String> data_glossary = new LinkedHashMap<String, String>();

	public static String getPropertyValue(String propertyName) {
		Properties prop = new Properties();
		InputStream input = null;
		String property = null;
		try {
			input = new FileInputStream("API.properties");
			prop.load(input);
			property = prop.getProperty(propertyName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return property;
	}
	
	
	public static String getGlobalPropertyValue(String propertyName) {
		Properties prop = new Properties();
		InputStream input = null;
		String property = null;
		try {
			input = new FileInputStream("Global Settings.properties");
			prop.load(input);
			property = prop.getProperty(propertyName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return property;
	}

	public static Response generate_PI_AuthToken() {
	
		try {

			Map<String, String> headers = new HashMap<String, String>();

			String loginURL = getPropertyValue("login");

			String apiKeyName = getPropertyValue("login-apikey-name");
			String apiKeyValue = getPropertyValue("login-apikey-value");

			String apiUserHeaderName = getPropertyValue("login-api-username");
			String apiUserHeaderValue = getPropertyValue("login-usernameUniversal");

			String apiPasswordHeaderName = getPropertyValue("login-api-passowrd");
			String apiPasswordHeaderValue = getPropertyValue("login-passwordUniversal");

			headers.put(apiKeyName, apiKeyValue);
			headers.put(apiUserHeaderName, apiUserHeaderValue);
			headers.put(apiPasswordHeaderName, apiPasswordHeaderValue);

			Response response = webCredentials_rest.PostCallWithHeaderParam(
					headers, loginURL);
			response.then().assertThat().statusCode(HttpStatus.SC_OK);
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Response getManifestationReturnresponse(String auth_token,
			String manifestValue) {
		try {

			String serverURL = getPropertyValue("server-url");
			String database = getPropertyValue("database");

			String endPoint = serverURL + "/" + manifestValue + database;

			String apiKeyName = getPropertyValue("manifest-apikey-name");
			String apiKeyValue = getPropertyValue("manifest-apikey-value");

			String sessionTokenName = getPropertyValue("manifest-session-token-name");

			String rolesHeaderName = getPropertyValue("manifest-Roles-name");
			String rolesHeaderValue = getPropertyValue("manifest-Roles-value");

			String acceptHeaderName = getPropertyValue("manifest-accept-name");
			String acceptHeaderValue = getPropertyValue("manifest-accept-value");

			String xAuthorizationHeaderName = getPropertyValue("manifest-xauthorization-name");
			String xAuthorizationHeaderValue = getPropertyValue("manifest-xauthorization-value");

			String authorizationHeaderName = getPropertyValue("manifest-authorization-name");
			String authorizationHeaderValue = getPropertyValue("manifest-authorization-value");

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(apiKeyName, apiKeyValue);
			headers.put(sessionTokenName, auth_token);
			headers.put(rolesHeaderName, rolesHeaderValue);
			headers.put(xAuthorizationHeaderName, xAuthorizationHeaderValue);
			headers.put(acceptHeaderName, acceptHeaderValue);
			headers.put(authorizationHeaderName, authorizationHeaderValue);
			Response response1 = webCredentials_rest.getCallWithHeaderParam(
					headers, endPoint);
		
			return response1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String retriveValuefromJson(String jsonpath, String content) {
		String valuefromJson = null;
		try {
			JsonPath json = JsonPath.compile(jsonpath);
			List<Object> value = json.read(content);
			for (Object o : value) {
				valuefromJson = o.toString();
			}
			return valuefromJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Map<String, Object> jsonString2Map( String jsonString ){
        Map<String, Object> keys = new HashMap<String, Object>(); 

        try {
			org.json.JSONObject jsonObject = new org.json.JSONObject( jsonString ); // HashMap
			Iterator<?> keyset = jsonObject.keys(); // HM

			while (keyset.hasNext()) {
			    String key =  (String) keyset.next();
			    Object value = jsonObject.get(key);
			  //  System.out.print("\n Key : "+key);
			    if ( value instanceof org.json.JSONObject ) {
			      //  System.out.println("Incomin value is of JSONObject : ");
			        keys.put( key, jsonString2Map( value.toString() ));
			    }else if ( value instanceof org.json.JSONArray) {
			        org.json.JSONArray jsonArray = jsonObject.getJSONArray(key);
			        //JSONArray jsonArray = new JSONArray(value.toString());
			        keys.put( key, jsonArray2List( jsonArray ));
			    } else {
			     //   keyNode( value);
			        keys.put( key, value );
			    }
			}
			return keys;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	
	public static List<Object> jsonArray2List( JSONArray arrayOFKeys ) {
       // System.out.println("Incoming value is of JSONArray : =========");
        List<Object> array2List = new ArrayList<Object>();
        try {
			for ( int i = 0; i < arrayOFKeys.length(); i++ )  {
			    if ( arrayOFKeys.opt(i) instanceof JSONObject ) {
			        Map<String, Object> subObj2Map = jsonString2Map(arrayOFKeys.opt(i).toString());
			        array2List.add(subObj2Map);
			    }else if ( arrayOFKeys.opt(i) instanceof JSONArray ) {
			        List<Object> subarray2List = jsonArray2List((JSONArray) arrayOFKeys.opt(i));
			        array2List.add(subarray2List);
			    }else {
			    //    keyNode( arrayOFKeys.opt(i) );
			        array2List.add( arrayOFKeys.opt(i) );
			    }
			}
			return array2List;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}      
    }

	public static Response getManifestationresponse(String manifestValue) {
		try {

			String applicationURL = getGlobalPropertyValue("ApplicationUrl")
					.replace("https://", "");
			String serverURL = getPropertyValue("Marklogic-url").replace(
					"CRAFT", applicationURL).replace("REPLACE", manifestValue);

			String authorizationHeaderName = getPropertyValue("manifest-authorization-name");
			String authorizationHeaderValue = getPropertyValue("manifest-authorization-value");

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(authorizationHeaderName, authorizationHeaderValue);
			Response response1 = webCredentials_rest.PostCallWithHeaderParam(
					headers, serverURL);

			return response1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Response getNegativeManifestationresponse(String manifestValue) {
		try {

			String applicationURL = getGlobalPropertyValue("ApplicationUrl")
					.replace("https://", "");
			String serverURL = getPropertyValue("Marklogic-url").replace(
					"CRAFT", applicationURL).replace("REPLACE", manifestValue);
			
			System.out.println(applicationURL);
			System.out.println(serverURL);

			String authorizationHeaderName = getPropertyValue("manifest-invalid-authorization-name");
			String authorizationHeaderValue = getPropertyValue("manifest-invalid-authorization-value");

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(authorizationHeaderName, authorizationHeaderValue);
			Response response1 = webCredentials_rest.PostCallWithHeaderParam(
					headers, serverURL);

			return response1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static String readJsonFile(String file) { 
		@SuppressWarnings("deprecation")
		JSONParser parser = new JSONParser(); 
		String requiredCellVal="";
		try { 

			Object object = parser
                    .parse(new FileReader(file));
            
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;
            requiredCellVal = (String) jsonObject.get("Contrib Source");
            					
		/* FileReader fileReader = new FileReader(file); 
		JSONObject json = (JSONObject) parser.parse(fileReader);
		requiredCellVal = (String) json.get("Contrib Source"); */
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requiredCellVal;
	}
	
	}