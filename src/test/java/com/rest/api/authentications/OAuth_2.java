package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth_2 {
	
	
	@Test
	public void check_Oauth_2_APITest() {
		
		
		RequestSpecification request =
		RestAssured 
		.given()
			.auth()
				.oauth2("Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6");
		
		
	Response response = request.post("https://gorest.co.in/");
	
	System.out.println(response.getStatusCode());
	System.out.println(response.prettyPeek());
	
	}
	
	
	//1	Generate a token at the run time by using token API
	//2 String tokenID from the response
	//3 hit the next api with this tokenID
	
	@Test
	public void generate_oAuth_2_Token_APIS() {
		
		RequestSpecification request =
				RestAssured.given()
					.formParam("client_id", "NovAPIApp")
					.formParam("client_secret", "24cfdf665sgfdresh31334777jshggdj")
					.formParam("grant_type", "client_credentials");
				
		
	Response response =	request.post("http://coop.apps.symfonycats.com/token"); // This post url we get it from the site to generate token.
	
			System.out.println(response.getStatusCode());
			System.out.println(response.prettyPrint());
			
			
			String tokenID =response.jsonPath().getString("access_token"); // this acces_token is the generated token 
																			// we have store in the form of String variable.	
			
			System.out.println("API token id is: " +tokenID);
			
			
	
	
		//Now once we have generated the token id we can hit the APIS---Hurray
			
			RequestSpecification request_1 =
					RestAssured 
					.given()
						.auth()
							.oauth2(tokenID);
					
					
				Response response_1 = request_1.post("https://gorest.co.in/");
			
				System.out.println(response_1.getStatusCode());
				System.out.println(response_1.prettyPeek());   
				
				
			
			
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
