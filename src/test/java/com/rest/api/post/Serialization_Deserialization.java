package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;


public class Serialization_Deserialization {
	
	
	// Converting POJO TO JSON (Serialization)
	
	@Test
	public void POJO_to_JSON() throws JsonProcessingException {
		
		
		Student_POJO stu = new Student_POJO();
		
		stu.setName("Scott");
		stu.setEmail("scott@123@gmail.com");
		stu.setGeneder("male");
		stu.setStatus("active");
		stu.setId("1");
		stu.setPhone("9000000000");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsondata =	mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stu);
		
		System.out.println(jsondata);
		
		
	}
	
	

	// Converting JSON TO POJO (De-Serialization)
	
	@Test
	public void JSON_to_POJO() throws JsonProcessingException {
		
		
		String jsonData ="{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"email\" : \"scott@123@gmail.com\",\r\n"
				+ "  \"geneder\" : \"male\",\r\n"
				+ "  \"status\" : \"active\",\r\n"
				+ "  \"phone\" : \"9000000000\",\r\n"
				+ "  \"id\" : \"1\"\r\n"
				+ "}";
		
		
		

		ObjectMapper mapper = new ObjectMapper();
		
		Student_POJO stu =	mapper.readValue(jsonData, Student_POJO.class); 
		
		System.out.println("email" +stu.getEmail());
		System.out.println("id is "+stu.getId());
		System.out.println("name is "+stu.getName());
		System.out.println("status is "+stu.getStatus());
		
		
		
	}
	
	

}
