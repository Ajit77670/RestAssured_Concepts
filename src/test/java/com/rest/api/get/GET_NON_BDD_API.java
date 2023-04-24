package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_NON_BDD_API {

	//preapre your request
	//hit the API
	// get the response
	// fetch the value from the response
	
	
	
	@Test
	public void getUser_NON_BDD_APIS() {
		
		RestAssured.baseURI="https://gorest.co.in";  // These steps we are praeparing our request
		RequestSpecification request =	RestAssured.given();
		request.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6");
		
		Response response =request.get("/public-api/users");  // we are hitting the APIS
		
		
		System.out.println(response.statusCode());  // We are fetching the value from response
		System.out.println(response.prettyPeek());
		System.out.println(response.getHeader("Server"));
		
	}
		
		@Test
		public void getUser_NON_BDD_with_QueryParamsAPIS() {
			
			RestAssured.baseURI="https://gorest.co.in";  // These steps we are praeparing our request
			RequestSpecification request =	RestAssured.given();
			request.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6");
			
			request.queryParam("first_name", "John");
			request.queryParam("gender", "male");
			
			Response response =request.get("/public-api/users");  // we are hitting the APIS
			
			
			System.out.println(response.statusCode());  // We are fetching the value from response
			System.out.println(response.prettyPeek());
			System.out.println(response.getHeader("Server"));
			
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(response.getHeader("Server"), "cloudflare");
			
			
			//RestAssured provide its own Json Parser methos --> Jsonpath(), with this we can fetch the response from body.
			JsonPath js = response.jsonPath();
			System.out.println(js.getString("meta.data.sucess")); // fetching values from body
			Assert.assertEquals(js.getString("meta.data.sucess"), true);
			
			
		
		}
	
	
		@Test
		public void getUser_NON_BDD_with_HashMAp_QueryParamsAPIS() {
			
			RestAssured.baseURI="https://gorest.co.in";  // These steps we are praeparing our request
			RequestSpecification request =	RestAssured.given();
			request.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6");
			
			Map<String,String> params = new HashMap<String,String>(); // this is called Top Casting, child class object can be refered by parent interfce reference variable.
			params.put("first_name", "John");
			params.put("gender", "male");
			
			request.queryParams(params);  //After creating HashMap for query parameter we can pass this into request queryParams() method.
			
			Response response =request.get("/public-api/users");  // we are hitting the APIS
			
			
			System.out.println(response.statusCode());  // We are fetching the value from response
			System.out.println(response.prettyPeek());
			System.out.println(response.getHeader("Server"));
			System.out.println(response.getContentType());
			System.out.println(response.getSessionId());
			System.out.println(response.getTime());
			
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(response.getHeader("Server"), "cloudflare");
			
			
		
		}
	
	


}


