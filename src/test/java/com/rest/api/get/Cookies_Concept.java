package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import com.github.scribejava.core.model.Response;

import io.restassured.RestAssured;


public class Cookies_Concept {
	
	
	
	@Test(priority=1)
	public void verifying_cookies_without_using_respone() {  //Note, Test case can be failed as cookies will be new everytime on execution.
		
		
		given()
			.when()
				.get("https://www.google.com/")
		.then()
			.cookie("1P_JAR","2023-05-06-05")
		.and()	
			.cookie("AEC","AUEFqZdCZ53sxbQYzOsnCL_OUU-9uN9zb2e1MR9MHuBhJKf4d9dGvzPvdP4")
		.and()
			.cookie("NID","511%3DaYMxA4N0qJa7bDNLge-x4yPxFrX-bXUmAhGoo-bml0rYXQtwleGduOI7Nd-lulCigfAqDD3MJn_ZFVtBEcMYaoP4cSFW7xFb2kABUFa2UTmvxF3WyA71XAAAv0QIwNuPPqqkiHkiKpLQeKGtgKOTmhJxBszUn_G5WE3Hy5IBJ3E");
			
			
	}	
	
	

	
	@Test(priority=2)
	public void Printing_Single_Cookies_from_response_body() {
		
		io.restassured.response.Response res =	given()
							.when()
								.get("https://www.google.com/");
		
		
		String cookie =res.getCookie("AEC");
		
		
		System.out.println("AEC cookie value is " +cookie);
		
	}
	
	
	
	@Test(priority=3)
	public void Printing_All_cookies_from_response_body() {
		
		io.restassured.response.Response res =	given()  // 1st step -- store in the form of response.
				.when()
					.get("https://www.google.com/");

		
		
		Map<String,String> Allcookies =res.getCookies();  // then use response.getCookies() method which will return Map<>;
		
		for(String k : Allcookies.keySet()) {  // Now use for each loop to get the all cookies details
			
			
		String cookies_values =	res.getCookie(k);
		
		System.out.println("All cokkies values are" +cookies_values);
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
		
}
