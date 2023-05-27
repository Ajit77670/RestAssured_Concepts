package com.rest.api.post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

// There are typically 4 ways to craete request body
	//1. Using HashMap
	//2. Using org.JSON (JSONObject class)
	//3. using POJO(Plain old Java Object)
	//4.using external json files.

public class Ways_to_Create_POST_Request_body {
	
	
	//1st way : USing HashMap (we use this generally when we have less payload data)
	
	@Test
	public void PostUsingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("location", "France");
		data.put("phone", "1232112");
		data.put("name", "Scott");
		
		String courseArr[]= {"C#","C++"};
		data.put("Subject", courseArr);
		
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1232112"))  
			.body("Subject[0]", equalTo("C#"))
			.body("Subject[1]",equalTo ("C++"))
			.header("Content-type", "application/json")
			.log().all();
						
		
		}

	
	
	//2nd Way : Using JSONObject
	
	@Test
	public void PostUsing_JSONObject() {
		
		JSONObject data = new JSONObject();  //jsonObject takes data in just like Map using Key and value so diont be confuse.
											// only thing to rememeber convert Json data into toString() format.
		
		data.put("location", "France");
		data.put("phone", "1232112");
		data.put("name", "Scott");
		
		String courseArr[]= {"C#","C++"};
		data.put("Subject", courseArr);
		
		
		given()
			.contentType(ContentType.JSON)
			.body(data.toString())				// only thing to rememeber convert Json data into toString() format.
			
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1232112"))  
			.body("Subject[0]", equalTo("C#"))
			.body("Subject[1]",equalTo ("C++"))
			.header("Content-type", "application/json")
			.log().all();
						
		
		}
	
	//3rd Way : USing POJO  --> Have created POJO_Students template
	
	@Test
	public void PostUsing_POJO() {
		
		POJO_Student data = new POJO_Student(); 
		
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("1234567");
		
		String courseArr[]= {"C#","C++"};
		data.setCourses( courseArr);
		
		
		given()
			.contentType(ContentType.JSON)
			.body(data)				
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1232112"))  
			.body("Subject[0]", equalTo("C#"))
			.body("Subject[1]",equalTo ("C++"))
			.header("Content-type", "application/json")
			.log().all();
						
		
		}
	
	//4th Way : USing external Json file  --> Refereing Booking.json file from the current directory/project. 
	
		@Test
		public void PostUsing_Json_externalfile() throws FileNotFoundException {
			
			File f = new File("C:\\Users\\Ajith Kumar\\eclipse-workspace\\REST_ASSURED_CONCEPTS\\src\\test\\java\\Data_File\\Booking.json");
			
			FileReader fr = new FileReader(f);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data = new JSONObject(jt);
			
			given()
				.contentType(ContentType.JSON)
				.body(data.toString())
				
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Scott"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("1232112"))  
				.body("Subject[0]", equalTo("C#"))
				.body("Subject[1]",equalTo ("C++"))
				.header("Content-type", "application/json")
				.log().all();
							
			
			}
	

}
