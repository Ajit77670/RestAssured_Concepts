package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.github.scribejava.core.model.Response;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class OAuth_1 {
	
	
	@Test
	public void OAuth_1_Token_Creation() {
	
		RequestSpecification request =
		RestAssured.
		given()
			.auth()
				.oauth("VaHJdvycNGQT7A0Jt2GzcCfu6",
						"WTPvxi5JAfjFut3qQamdubTjSiPViny20PRtqVV3IBPaJrfbBf",
						"1102632849595863044-OJLxhn8eYSoKKEMTdJjqgZ7d9ca74O", 
						"I4a0Fqm3JxpHlcTCOc7Q33NTrPCBXvpxT668V4h3ibCSB");
			
		
		io.restassured.response.Response response =  request.post("https://api.twitter.com/2/tweets:?This is My Tweet from RestAuusred");
		
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
		

}
