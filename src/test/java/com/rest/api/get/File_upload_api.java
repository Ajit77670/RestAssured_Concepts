package com.rest.api.get;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.response.Response;

public class File_upload_api {

	@Test
	public void single_file_upload() {
		
		File myfile = new File("C:\\AutomationPractice\\Test1.txt");
		
		given()
			.multiPart("file","myfile")  //file and  myfile response body input for validation
			.contentType("multipart/form-data")  // for file upload along with multipart we need to pass content type as multipart/form-data.
		
		.when()
			.post("http://localhost:8080/uploadfile")  // file upload API 
		
		.then()
			.statusCode(200)
			.body("filename", equalTo("Test1.txt"))
			.log().all();
			
		
	}
	
	
	
	@Test
	public void multi_file_upload() {
				
		File myfile1 = new File("C:\\AutomationPractice\\Test1.txt");
		File myfile2 = new File("C:\\AutomationPractice\\Test2.txt");
		
		given()
			.multiPart("files","myfile1")  //file and  myfile response body input for validation
			.multiPart("files","myfile2") 
			.contentType("multipart/form-data")  // for file upload along with multipart we need to pass content type as multipart/form-data.
		
		.when()
			.post("http://localhost:8080/uploadfile")  // file upload API 
		
		.then()
			.statusCode(200)
			.body("[0].filename", equalTo("Test1.txt"))
			.body("[1].filename", equalTo("Test2.txt"))
			.log().all();
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


