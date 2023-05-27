package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;


public class JSONObject_Response {
	
	
	//Approach extracting response simple way
	
	@Test
	public void getResponse() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/books")
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("isbn[0]", equalTo("9781593279509"))
			.header("Date", "Thu, 11 May 2023 05:21:52 GMT");
			
			
		}
	
	//----------------------------------------------------------------------------------------------------- 
	//RestAssured provide its own Json Parser methos --> Jsonpath(), with this we can fetch the response from body.
	
	//Response response= 
	//		given()
	//		.when()
	//		 .get("http://localhost:3000/books");

	
	//JsonPath js = response.jsonPath();
	//System.out.println(js.getString("meta.data.sucess")); // fetching values from body
  //Assert.assertEquals(js.getString("meta.data.sucess"), true);
	
//--------------------------------------------------------------------------------------------------------------	
	
	
	
	//Approach 2 for fetching the response using extract() method.
	
	@Test
	public void getingResponse() {
		
	String path =	given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/books")
		.then()
			.statusCode(200)
			.extract().path("Cache-Control", "no-cache").toString();
			
		System.out.println(path);
		
	}
	
	
	

	//Approach 3 for fetching the response from body using Response when we have long/big response body
	
	@Test
	public void ParsingJSON_ResponseData() {
	
		Response res= (Response) given().contentType(ContentType.JSON)
						.when()
						 .get("http://localhost:3000/books");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals("Conten-Type", "Conten-Type");
		
		
		JSONObject jo = new JSONObject(res.toString());  // added JSONObject Dependency in POM. 
								//To use JSONObject always convert the response into toString and pass in the JSONOBject as parameter.
		
		
		//Book title validation example
		
		boolean status = false; // Just keep flag as false initially for validation from JSONObject Body.
		
		for(int i =0; i<jo.getJSONArray("book").length();i++) {
			
		String bookTitle =	jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			// The full response of the body is an Object (i.e jo)
			//In which we have book as JSONArray
			// in the book JSONArray we have multiple JSONObjects(different books).
			//Note: Simple esko yaad rakhna hain bas then we can use JSONObject easily.
			
			System.out.println(bookTitle);
			
			
			// Now we can verify the Response body with the TestNG Assertion
			
				if(bookTitle.equals("The Lord of Rings")) {
				
				status = true;
				break;
			}
		}
		
			Assert.assertEquals(status, true);
		
			
			
			// Total price of book validation
		
				double totalprice =0;
				for(int i =0; i<jo.getJSONArray("book").length();i++) {
				
				String price =	jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			
				totalprice =totalprice +Double.parseDouble(price);
			}
				
				System.out.println("total price of the book is " +totalprice);
				Assert.assertEquals(totalprice, 53.92);
	
	}	

}
