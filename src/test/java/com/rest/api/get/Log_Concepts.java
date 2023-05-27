package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class Log_Concepts {
	
	@Test
	public void Log_All_Concepts_in_RESTASSURED() {
		
		given()
			.when()
			.get("https://www.google.com/")
				.then()
					// .log().body()   ---> it will generate log only  for body
					// .log().cookies()  ---> it will generate  log for cookies
				   //.log().headers()  ---> it will generate  log for headers
					.log().all();   //---> it will generate  log for all the page including body,header,request evrything.
		
		
	}
	

}
