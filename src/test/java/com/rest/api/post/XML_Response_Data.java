package com.rest.api.post;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XML_Response_Data {

	
	//Approach 1
	@Test
	public void test_XML_normal_without_usingResponse() {
		
		
		given()
			
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler/?page=1")
		.then()
			.statusCode(200)
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))
			.log().body();
	}
	
	
	//Approach 2
	@Test
	public void test_xml_using_Response() {
		
	Response res =	given()
						.when()
							.get("http://restapi.adequateshop.com/api/Traveler/?page=1");
		
	
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo =res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String travelName =res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelName, "Developer");
		
		
	}
	
	
	//Approach 3  
		@Test
		public void test_xml_response_using_XMLPath_object_class() {
			
		Response res =	given()
							.when()
								.get("http://restapi.adequateshop.com/api/Traveler/?page=1");
			
		
			XmlPath xmlobj = new XmlPath(res.toString());
			
			//verify total no of Travellers
			List<String> travelleers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
			Assert.assertEquals(travelleers.size(), 10); // on per page we are expecting 10 traveller information.
			
			
			//verify all Travellers name
			List<String> travelernames = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			boolean status = false;
			
			for(String t : travelleers ) {
			if( travelernames.equals("Developer")) {
				
				status = true;
				break;
				
			}
			
		}
			Assert.assertEquals(status, true);
			
		
		}
}
