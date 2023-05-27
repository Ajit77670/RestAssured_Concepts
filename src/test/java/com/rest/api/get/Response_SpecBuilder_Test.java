package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


public class Response_SpecBuilder_Test {
	
	
	// Create the object for ResponseSpecBuilder object and defined your expected response. its very helpful in validationg the common response.
	
	ResponseSpecBuilder resSpec = new ResponseSpecBuilder();
	
	
		ResponseSpecification resspec_200_OK = resSpec
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectHeader("Server", "cloudflare")
				.build();
				
			 		
		ResponseSpecification resspec_400_BAD_REQUEST = resSpec
				.expectStatusCode(400)
				.expectHeader("Server", "cloudflare")
				.build();	
		
		
		ResponseSpecification resspec_401_AUTH_FAIL= resSpec
				.expectStatusCode(401)
				.expectHeader("Server", "cloudflare")
				.build();	
		
		
	
	@Test
	public void ResponseSpecTest() {
		
		RestAssured.baseURI ="https://gorest.co.in/";
		given()
				.header("Authorization", "97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
		.when()
				.get("/public-api/users")
		.then()
				.assertThat()
				.spec(resspec_200_OK);
		
		
		
	}
	
	
	@Test
	public void Auth_Fail_Test() {
		
		RestAssured.baseURI ="https://gorest.co.in/";
		given()
				.header("Authorization", "97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac600000")
		.when()
				.get("/public-api/users")
		.then()
				.assertThat()
				.spec(resspec_401_AUTH_FAIL);
		
		
		
	}
		
		
		
	
}
