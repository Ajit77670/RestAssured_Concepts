package com.rest.api.get;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import com.github.scribejava.core.model.Response;


public class Header_Concept {

	
	@Test(priority=1)
	public void Header_Verify_without_Response() {
		
		given()
			.when()
				.get("https://www.google.com/")
			.then()	
				.header("Content-Type", "text/html; charset=ISO-8859-1")
				.header("Cache-Control", "private, max-age=0")
				.header("X-Frame-Options", "SAMEORIGIN");
		
			
	}
	
	
	
	@Test(priority=2)
	public void Verifying_Headers_Using_Response() {
		
	Response res =	(Response) given()
				.when()
					.get("https://www.google.com/");
	
	
			Map<String,String> headers =	res.getHeaders();
			
			for(String h:headers.keySet()) {
				
			String AllHeaders =	res.getHeader(h);
			
			System.out.println("All headers values are " +AllHeaders);
			}
		
	}
	
	
	
}
