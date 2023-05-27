package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class POST_API_WITH_POJO {
	
	//Create a USer
	//POST URL
	//REQUEST JSON BODY
	//USER JAVA CLASS (POJO)-->JSON OBJECT
	//Encapsulation--> private variables  -->public(getter and setter method)
	//POJO --> Plain Old JAVA OBJECT---> JAVA Class -->  private variables  -->public(getter and setter method)
	
	
	
	@Test
	public void create_User_POJO_Test() {      
		
		
		User_POJO_Template user = new User_POJO_Template("Ajit","Kumar","male","06-12-1989","ajit1@gmail.com","90444444118","https://www.ajit.com"
				,"123,Bangalore,Singsandra","active");	
		
		
	//convert POJO to JSON --> Serialization--->JACKSON API	
		
		ObjectMapper mapper = new ObjectMapper();
		String  userjson =null;
		
		try {
		   userjson= mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(userjson);
		
		
		// Now will hit the AOI with help of RestAssured simple!!!!
		
		RestAssured.baseURI ="https://gorest.co.in";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
			.body(userjson) //Passing the POJO
		.when().log().all()
			.post("/public-api/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
			.and()
				.body("result.first_name", equalTo(user.getFirst_name()))  // Here we are calling getter methods instead of Hardcore values.
				.body("result.last_name", equalTo(user.getLast_name()))
				.body("result.status", equalTo(user.getStatus()));
		
				
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
