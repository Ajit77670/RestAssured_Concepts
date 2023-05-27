package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;



public class JSON_Schema_XML_Schema_Validator {
	
	
	//JSON SCHEMA VALIDATING
	@Test
	public void JSON_Schema_Validator() {
		
		given()
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Booking.json")); //Booking file in json format
			
}
	
	
	//XML SCHEMA VALIDATING
	
	@Test
	public void XML_Schema_Validator() {
		
		given()
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("Booking.xsd"));  //Booking file in Xsd format 
			
}
	
	
	

}
