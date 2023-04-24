package com.rest.api.put;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User_POJO_Template;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

//for PUT CALL SEQUENCE BE LIKE ----->POST-PUT-GET

public class PUT_API_TEST {

	@Test
	public void Update_User_PUT_API_Test() {
		
		User_POJO_Template user = new User_POJO_Template("Sumit","Kumar","male","06-12-1989","sumit@gmail.com","90444444118","https://www.ajit.com"
			,"123,Bangalore,Singsandra","active");
		
		
		ObjectMapper mapper = new ObjectMapper();  // Serialization using JACKSON API
		
		String useJson =null;
		
		try {
			useJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
			System.out.println(useJson);
		
	
	//write POST call
			
			RestAssured.baseURI="https://gorest.co.in";
			
			String userID = 
			given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
					.body(useJson)	
			.when().log().all()
				.post("/public-api/users")
			.then().log().all()
				.assertThat()
					.contentType(ContentType.JSON)
					.extract().path("result.id");  // We need this id for updating the put call, so we need to use 	
												  // extract() method to get theid from response body.
			
			
			System.out.println("user id is " +userID);
			 
			
	//Call the PUT API:
			
			String UpdatedUserJSON =null;
			
			try {
				UpdatedUserJSON = mapper.writeValueAsString(user);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			System.out.println(UpdatedUserJSON);
			
			
	user.setEmail("sumit.kumar@gmail.com");  // These are the values which we want to update with PUT Call
	user.setLast_name("Srivastava");
	user.setPhone("989898777777");
	
			given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
				.body(UpdatedUserJSON)
			.when().log().all()
				.put("public-api/users/"+userID)
			.then().log().all()
				.assertThat()
				    .contentType(ContentType.JSON)
			.and()
					.body("rest.email", equalTo(user.getEmail()))
			.and()
					.body("result.id", equalTo(user.getFirst_name()));
						
				
				
		
		
	}
	
	
	
	
	
	
	
	
}
