package com.rest.api.get;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GET_BDD_API {
	
	
	//RestAssured BDD:
	/*given()
	 * when()
	 * then()
	 * and()
	 */
	
	//1st way of writting the Test using RestAssured BDD 
	
	@Test
	public void getAPI_Circuit_Test_1() {
		
		given().log().all()
			.when().log().all()
			.	get("http://ergast.com/api/f1/2017/circuits.json")
			.then().log().all()
				.assertThat()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)); //hasSize() method comes from hamcrest library.
	}
	
	//2nd way of writting the Test using RestAssured BDD 
	
	@Test
	public void getAPI_Circuit_Test_2() {
		
		Response response =
		given().log().all()
			.when().log().all()
			.get("http://ergast.com/api/f1/2017/circuits.json");
			
			
	int statusCode = response.getStatusCode();
	System.out.println("api status code is " +statusCode);
	Assert.assertEquals(statusCode, 200);
		
	}
	
	
	//3rd and most effective way of writting the Test using RestAssured BDD 
	
	
	@Test
	public void getAPI_Circuit_Test_3() {
		
		RestAssured.baseURI = "http://ergast.com";
		given().log().all()
		.when().log().all()
		.get("/api/f1/2017/circuits.json")
		
		.then().log().all()
			.assertThat()
				.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.header("Content-Length", equalTo("4552"));  // this equalsTo() method comes from hamcrest
	}
	
	
	
	
	// Query Parameter Test
	@Test
	public void getJson_API_Verify_MD5Value() {
		
		String paramValue = "test";
		String expectedMD5Value ="098f6bcd4621d373cade4e832627b4f6";
		
		given().log().all()
		.param("text", paramValue)
			.when().log().all()
				.get("http://md5.jsontest.com")
		.then().log().all()
			.assertThat()
				.body("md5", equalTo(expectedMD5Value));
		
	}
	
	
	
//------------DATA PROVIDERS APPROACH---------------------	
	
// Using --> http://ergast.com (Formula1 site)	
	
//The below are the no. of circuits available for the respective years.	
//2017=20
//2016=21
//2015=19
//1966=9
	
	
	
@DataProvider(name="getCircuiYearData")
public Object[][] getCircuit_Year_Info(){
	
	return new Object[][] {
		
		{"2017",20},
		{"2016",21},
		{"2015",19},
		{"1966",9},
	};
}
	

@Test(dataProvider= "getCircuiYearData")
public void nameofCircuitsYearTest(String seasonYear ,int circuitNumber ) {
	
	
	given().log().all()
	.pathParam("raceseason", seasonYear)
		.when().log().all()
			.get("http://ergast.com/api/f1/{raceseason}/circuits.json")
	.then().log().all()
		.assertThat()
			.body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitNumber));
	
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
