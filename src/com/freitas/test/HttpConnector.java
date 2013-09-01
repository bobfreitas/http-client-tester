package com.freitas.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpConnector {
	
	private String hostname = "localhost";
	private Integer port = 9100;
	private String protocal = "http";
	private String servlet = "/your_servlet";
	
	private HttpHost host;
	private HttpClient client;
	private HttpPost post;
	private CookieStore cookieStore;
	private HttpContext localContext;
	
	public HttpConnector() {
		cookieStore = new BasicCookieStore();
		localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}
	
	public HttpConnector(String hostname, Integer port, String protocal, String servlet) {
		this.hostname = hostname;
		this.port = port;
		this.protocal = protocal;
		this.servlet = servlet;
		cookieStore = new BasicCookieStore();
		localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}
	
	public List<String> connect(String cmd) throws Exception {

		List<String> results = new ArrayList<String>(1024);
		
		// setup the request
		host = new HttpHost(hostname, port, protocal);
		client = new DefaultHttpClient();
		post = new HttpPost(servlet);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("cmd", cmd));
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
		post.setEntity(entity);
		// make the request
		HttpResponse response = client.execute(host, post, localContext);
		// handle the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			results.add(line);
		}
		return results;
	}
	
	public void close(){
		post.releaseConnection();
		client = null;
	}

}