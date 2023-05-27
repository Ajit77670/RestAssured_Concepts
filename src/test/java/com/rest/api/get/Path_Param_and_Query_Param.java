package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class Path_Param_and_Query_Param {
	
	
	// https://reqres.in/api/users?page=2&id=5      -----> smample URL for Path and QueryParam         
	
	
	@Test
	public void Path_Param_and_Query_Param_Concept() {
		
		given()
			.pathParam("MyPath", "users")     // we set "users" as "MyPath" as a Path Parameter 
			.queryParam("page", 2)  // In Query Parameter will assign the same value as given in URL form--> key and value format.
			.queryParam("id", 5)  // In Query Parameter will assign the same value as given in URL form--> key and value format.
		
			.when()
				.get("https://reqres.in/api/{MyPath}")
			.then()
				.statusCode(200)
				.log().all();
		
		
	}
	
	

}
