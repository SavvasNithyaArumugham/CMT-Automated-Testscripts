package com.pearson.automation.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class APIUtil {
	
	private CloseableHttpClient httpClient;
	
	public APIUtil(){
		
	}
	
	public String post(String scheme, String host, String path, String entity,
			HashMap<String, String> header){
		
		URIBuilder authenticationURLBuilder = new URIBuilder().setScheme(scheme)
				.setHost(host).setPath(path);
		URI uri = null;
		String response = null;
		try {
			uri = authenticationURLBuilder.build();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		HttpPost myPost = new HttpPost(uri);
		for (String key : header.keySet()) {
			myPost.setHeader(key, header.get(key));
		}
		
		if(!entity.equals("") && entity != null){
			InputStream inputStream = new ByteArrayInputStream(entity.getBytes());
			InputStreamEntity inStreamEntity = new InputStreamEntity(inputStream);
			myPost.setEntity(inStreamEntity);
		}
		
		httpClient = HttpClients.createDefault();
		HttpResponse resp = null;
		
		try {
			resp = httpClient.execute(myPost);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity respEntity = resp.getEntity();
			try {
				response = EntityUtils.toString(respEntity);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("RESPONSE : " + response);
		}else{
			System.out.println("RESPONSE CODE : "+ resp.getStatusLine().getStatusCode());
		}
		
		return response;
	}
	
	public String get(String scheme, String host, String path,
			HashMap<String, String> header){
		
		URIBuilder authenticationURLBuilder = new URIBuilder().setScheme(scheme)
				.setHost(host).setPath(path);
		URI uri = null;
		String response = null;
		try {
			uri = authenticationURLBuilder.build();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		HttpGet myGet = new HttpGet(uri);
		
		for (String key : header.keySet()) {
			myGet.setHeader(key, header.get(key));
		}
		
		httpClient = HttpClients.createDefault();
		HttpResponse resp = null;
		
		try {
			resp = httpClient.execute(myGet);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity respEntity = resp.getEntity();
			
			try {
				response = EntityUtils.toString(respEntity);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("RESPONSE : " + response);
		}else{
			System.out.println("RESPONSE CODE : "+ resp.getStatusLine().getStatusCode());
		}
		
		return response;
	}
}
