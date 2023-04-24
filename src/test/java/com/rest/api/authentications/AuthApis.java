package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;


public class AuthApis {
	
	
	//Total 6 types of Authentication:
		
	//1.Basic Authentication-->Username Password
		
	//2.preemtive-->sometimes basic authentication fails gives 401 from server side even the username and password provided by you is correct.
	//In this case we use premptive authentication along with basic authentication. preemmtive will enforce server 
	//to beleive the authentication provided by basic authorization is correct and you no need to verfy it again.
	
	//3.Oauth1.0-->CleintID, Client-Secret,API Key ,API Secret
		
	//4.Oauth2.0 -->Bearer Token, Token-id(Generating with help of GrantType, Client Key, Client token)
		
	//5.digest-->In digest Authentication along with username and password enternally one Hashcode will be sent to the server
	//so that over differnt network layer these two username and password will always be secure.
		
	//6.form
		
	
	
	//Basic Authentication
	//Username and password
		
		@Test
		public void BasicAuth() {
			
			given().log().all()
				.auth()
					.basic("admin", "admin")
			.when().log().all()
					.get("https://the-internet.herokuapp.com/basic_auth")
			.then().log().all()
					.assertThat()
						.statusCode(200);
		}
		
	
	
	//preemptive Authentication
	//Username and password
	
	@Test
	public void preemptiveAuth() {
		
		given().log().all()
		.auth()
		.preemptive()
				.basic("admin", "admin")
			.when().log().all()
				.get("https://the-internet.herokuapp.com/basic_auth")
			.then().log().all()
				.assertThat()
					.statusCode(200);
	}
	
	
	//digest Authentication
		//Username and password
			
			@Test
			public void digestAuth() {
				
				given().log().all()
					.auth()
						.digest("admin", "admin")
				.when().log().all()
						.get("https://the-internet.herokuapp.com/basic_auth")
				.then().log().all()
						.assertThat()
							.statusCode(200);
			}
			
			
			
			
			//form based Authentication
				
				@Test
				public void formbased() {
					
					given().log().all()
						.auth()
							.form("ajit@gmail.com", "Test1234567", new FormAuthConfig("https://classic.crmpro.com/system/authenticate.cfm",
									"username","password"))
					.when().log().all()
							.get("https://classic.crmpro.com/login.cfm")
					.then().log().all()
							.assertThat()
								.statusCode(200);
				}
				
	
	
	
	// OAth 2.0
	//if you are using:
	//with header:append your token with Bearer Keyword
	//with auth2() method: no need to add Bearer, just pass the token value.
	
	@Test
	public void OAuth2_API_Test() {
		
		given().log().all()
			.auth()
				.oauth2("97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
		.when().log().all()
			.get("https://gorest.co.in/public-api/users?first_name=Devika Patil&status=inactive")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	//Oauth 2.0 , another way
	
	
	@Test
	public void Oauth_API_Test_with_AuthHeader() {
		
		given()
			.contentType("application/json")
			.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
		.when()
			.get("https://gorest.co.in/public-api/users?first_name=Devika Patil&status=inactive")
		.then()
			.statusCode(200)
			.and()
			.header("Server", "cloudflare");
	}
	
	
	@Test
	public void Oauth_API_With_2_Query_Paramater() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
			.contentType("application/json")
			.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
			.queryParam("name", "Aaryan Varrier")
			.queryParam("gender", "female")
		.when()
			.get("public-api/users")
		.then()
			.statusCode(200)
			.and()
			.header("Connection", "keep-alive");
		
	}

}
