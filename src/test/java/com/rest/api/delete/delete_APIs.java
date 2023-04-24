package com.rest.api.delete;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


//Delete sequence---->POST -->delete-->get
// we should not authorize to delete someone data/orApis, we can delete the data/APIs which we have created through our tokenid/authorization.
//Therefore we should first create/post user and then apply the delete call.


public class delete_APIs {
	
	
	@Test
	public void delete_user_APIs() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 97c922ddb2f847e958faa42492218860e4dd19e5e49d29609257670988039ac6")
			
		.when().log().all()
			.delete("/public-api/users/958735")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
			.and()
				.body("result", equalTo(null));
			
			
		
		
	}
	

}
