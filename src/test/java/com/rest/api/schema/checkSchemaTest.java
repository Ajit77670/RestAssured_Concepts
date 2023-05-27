package com.rest.api.schema;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class checkSchemaTest {
	
	@Test
	public void booking_Schema_Test() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("C:\\Users\\Ajith Kumar\\eclipse-workspace\\REST_ASSURED_FRAMEWORK\\src\\test\\java\\com\\rest\\api\\schema\\Booking.json"))	
		.when().log().all()
				.post("/booking")
		.then().log().all()
			.assertThat()                                       
				.statusCode(200)
		.and()
			.body(matchesJsonSchemaInClasspath("C:\\Users\\Ajith Kumar\\eclipse-workspace\\"
					+ "REST_ASSURED_FRAMEWORK\\src\\test\\resources\\BookingSchema.json"));
	}
	
	
	@Test
	public void gorest_Schema_Test() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
				.when().log().all()
				.get("/public-api/users?first_name=Abhaya Iyer&gender=female&status=inactive")
		.then().log().all()
			.assertThat()
				.statusCode(200)
		.and()
			.body(matchesJsonSchemaInClasspath("C:\\Users\\Ajith Kumar\\eclipse-workspace\\REST_ASSURED_FRAMEWORK\\src\\test\\resources\\gorestSchema.json"));
	}
	
	

}
