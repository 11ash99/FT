package com.ft.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.logging.LogLevel;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.Azure.Authenticated;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;



public class FTServiceImpl implements FTService {
	
	int responseCode = 0;
	String tenantId = "df518e78-ba34-443a-8738-572278ee65f9";
	String username = "Ashwini.Gupta@enquero.com";
	String password = "Ashwini1#1#";
	String clientId = "223ca77c-f383-44dc-8ce0-6e34dcbad537";
	String subscriptionId = "70c963c7-6882-4d09-a42a-b8217809f3b5";
	public String publishData(){
		AuthenticationContext authContext = null;
		AuthenticationResult authResult = null;
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(1);
		    String url = "https://login.microsoftonline.com/" + tenantId + "/oauth2/authorize";
		    authContext = new AuthenticationContext(url,
		                                            false,
		                                            service);
		    Future<AuthenticationResult> future = authContext.acquireToken("https://management.azure.com/",
		                                                               clientId,
		                                                               username,
		                                                               password,
		                                                               null);
		    authResult = future.get();
		    System.out.println("accessToken: "+authResult.getAccessToken());
		    //String sfUrl = "https://management.azure.com/subscriptions/"+ subscriptionId +"/providers/Microsoft.HDInsight/clusters?api-version=2016-11-01";
			//callService(sfUrl, "", "GET", authResult.getAccessToken());
		    ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(clientId, 
		    		tenantId,
		    		authResult.getAccessToken(), 
			        AzureEnvironment.AZURE);

		    Authenticated azure = Azure
		            .authenticate(credentials);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    service.shutdown();
		}
		System.out.println("Deployed Successfully");
		return "Success";
		
	}
	
	private String callService(String sfUrl, String request, String methodType,
    		String access_token) throws IOException {
		System.out.println("sfUrl: " + sfUrl);
		String responseStr = null;
		BufferedReader br = null;
		HttpURLConnection conn = null;
		URL url = new URL(sfUrl);
		//String authToken = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(authString.getBytes());
		conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);
		conn.setReadTimeout(3000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod(methodType);
		conn.setRequestProperty("Content-Type", "application/json");
		if(access_token!=null && access_token!=""){
			conn.setRequestProperty("Authorization", "Bearer " + access_token);
		}
		//conn.setRequestProperty("Accept", "application/json");
		//conn.setRequestProperty("Authorization", authToken);
		if (!methodType.equalsIgnoreCase("GET")) {
			OutputStream os = conn.getOutputStream();
			os.write(request.getBytes());
			os.flush();
			os.close();
		}
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode..." + responseCode);
		br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String output;
		while ((output = br.readLine()) != null) {
			responseStr = output;
		}
		conn.disconnect();
		return responseStr;
	}

}
