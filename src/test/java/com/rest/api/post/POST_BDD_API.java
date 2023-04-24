package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_BDD_API {

		
	
	// We can use this below approach when we have less JSON payload data, just copy the paylod and paste in body. 
	@Test
	public void tokenPOST_BDD_API_JSONSTRING_TEST() {
		
		RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		given().log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n"	+ "    \"username\" : \"admin\",\r\n"+ "    \"password\" : \"password123\"\r\n"+ "}")
		.when().log().all()
			.post("/auth")
		.then().log().all()
		.	assertThat()
			.statusCode(200);
	
	}
	
	
	// We can use this below approach when we have more JSON payload data, we can store these data in a file under the project and import it.
		@Test
		public void tokenPOST_BDD_API_FILE_TEST() {
			
			RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		String tokenID =	given().log().all()
				.contentType(ContentType.JSON)
				.body(new File ("C:\\Users\\Ajith Kumar\\eclipse-workspace\\REST_ASSURED_FRAMEWORK\\src\\test\\java\\Data_File\\credentials.json"))
			.when().log().all()
				.post("/auth")
			.then().log().all()
			.extract().path("token"); // Instead of craeting object of response , we can use extract() method to fetch the responses.
									//with extract we can extract only one response.		
		System.out.println(tokenID);
		Assert.assertNotNull(tokenID);  // we are verifying the token generated should not be NULL.

		}	
		
		
		
		
		// We can use this below approach when we have less JSON payload data, just copy the paylod and paste in body. 
		//This is a simple second demonstartion of POST_CALL  with lesss JSON data.
		
		@Test
		public void createUser_POST_API_JSON_STRING_TEST_2() {
			
			RestAssured.baseURI="https://gorest.co.in";
			
			given()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
				.body("{\r\n"
						+ "        \"name\": \"HOO LALA\",\r\n"
						+ "        \"email\": \"ajit456@gmail.com\",\r\n"
						+ "        \"gender\": \"male\",\r\n"
						+ "        \"status\": \"active\"\r\n"
						+ "    }")
			.when()
				.post("/public-api/users")
			.then()
				.assertThat()
					.body("meta.success", equalTo(true))  // with body() method  we can extract multiple response from body.
				.and()
					.body("meta.data", equalTo(true));
				
				
			
		}
		
		
		
		
		// We can use this below approach when we have more JSON payload data, we can store these data in a file under the project and import it.
		@Test
		public void createUser_POST_API_JSON_FILE_TEST_2() {
			
			RestAssured.baseURI="https://gorest.co.in";
			
			given()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
				.body(new File ("C:\\Users\\Ajith Kumar\\eclipse-workspace\\REST_ASSURED_FRAMEWORK\\src\\test\\java\\Data_File\\User.json"))
			.when()
				.post("/public-api/users")
			.then()
				.assertThat()
					.body("meta.success", equalTo(true))  // with body() method  we can extract multiple response from body.
				.and()
					.body("meta.data", equalTo(true));
				
				
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
