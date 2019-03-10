package com.examen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.examen.domain.Customer;
import com.examen.domain.Customers;
import com.google.gson.Gson;

public class ClientPostCustomer {
	
	private URL url;
	private HttpURLConnection conn;
	private OutputStream os;
	
	private List<Customer> customersGlobal;
	
	public ClientPostCustomer()
	{
		
		try {
			
			final String POST_PARAMS_CUSTOMERS = "{\n" + "\"type\":\"GET_CUSTOMERS_INFO\"\r\n}";

			this.url = new URL("https://fn61sjllf9.execute-api.us-east-1.amazonaws.com/Test");
			this.conn = (HttpURLConnection) url.openConnection();
			this.conn.setRequestMethod("POST");
			this.conn.setRequestProperty("Content-Type", "application/json");
			this.conn.setReadTimeout(15*1000);
			//this.conn.connect();
			this.conn.setDoOutput(true);
			this.os = this.conn.getOutputStream();
			this.os.write(POST_PARAMS_CUSTOMERS.getBytes());
			this.os.flush();
			this.os.close();

			 int responseCode = this.conn.getResponseCode();
			 
			    if (responseCode == HttpURLConnection.HTTP_OK) { 
			        BufferedReader in = new BufferedReader(new InputStreamReader(
			        		this.conn.getInputStream()));
			        String inputLine;
			        StringBuffer response = new StringBuffer();
			        while ((inputLine = in .readLine()) != null) {
			            response.append(inputLine);
			        } in .close();
			        Customers customers1 = new Gson().fromJson(response.toString(), Customers.class);
			        this.customersGlobal=customers1.getCustomers();


			    } else {
			        System.out.println("POST NOT WORKED");
			    }

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public List<Customer> getCustomers()
	{
		
		return this.customersGlobal;
	}
	
	
	
	

}
