package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ADD_Place;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils {
	
	static RequestSpecification reqSpe;
	static ResponseSpecification responseSpec;
	static Response resp;

	ADD_Place responseWeGet;

	TestDataBuild data = new TestDataBuild();
	
	public static	String firstVal;
	public static	String secondVal;


static String place_id;
ADD_Place plObj = new ADD_Place();
	
	@Given("Place Payload with input details {string}")
	public void place_Payload_with_input_details(String data_input) {
		try {
	
			
			
			Map<String, JSONObject> mapObj = new HashMap<String, JSONObject>();
	String [] dataValue = data_input.split("_");
	
 String spl= "addPlaceAPI.ADDPOSTReq";
 char [] cha= data_input.toCharArray();
 for (char c : cha) 
 	{
	 int ascii = (int) c;
	 if (ascii==46) 
	 	{
		 firstVal = data_input.substring(0, spl.indexOf(c));
		 secondVal = data_input.substring(spl.indexOf(c)+1,spl.length());
	 	}
	 }
 		
 			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> carMap = mapper.readValue(new File(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\"+firstVal+".json"), new TypeReference<Map<String, Object>>(){});
			String payLoadJSON = (String)carMap.get(secondVal);
			
			ADD_Place placeObj = mapper.readValue(payLoadJSON, ADD_Place.class);
			plObj = placeObj;
			responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			reqSpe = given().spec(requestSpecification())
					.body(plObj);
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		
}

	@When("user calls AddPlaceAPI with POST http request")
	public void user_calls_AddPlaceAPI_with_POST_http_request() {
		
		try {

			resp = reqSpe.when().post("maps/api/place/add/json").then().spec(responseSpec).extract().response();
		
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Then("the API call us success with status code {int}")
	public void the_API_call_us_success_with_status_code(Integer expectedResponse) {
		try {
			
		//	assertEquals(resp.statusCode(), expectedResponse);
			JsonPath js = new JsonPath(resp.asString());

			Map<String, Object> mapp = resp.as(Map.class);
			place_id = (String) mapp.get("place_id");
					
				System.out.println("The POST REQUEST response is as follows:"); 
				  System.out.println("mapp place_id uis "+mapp.get("place_id"));
				  System.out.println("mapp scope is "+mapp.get("scope"));
				  System.out.println("mapp  reference  is "+mapp.get("reference"));
				  System.out.println("mapp iD is "+mapp.get("id"));
				 
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Then("status in response body is {string}")
	public void status_in_response_body_is(String string) {
		
		try {

			System.out.println("The place Id is Then 2 " + place_id);
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	

	
	
	// GETPLACE API

	@Given("Here we are going to View the details of the recently created API.")
	public void here_we_are_going_to_view_the_details_of_the_recently_created_api() throws IOException {

		try {
			reqSpe = given().spec(requestSpecification().queryParam("place_id", place_id));
		} 
		catch (Exception e) {
			
			System.err.println(e);
		}

	}

	@When("user calls GETPlaceAPI using the GET http request")
	public void user_calls_get_place_api_using_the_get_http_request() {

		try {
			resp = reqSpe.when().get("maps/api/place/get/json").then().spec(responseSpec).extract().response();
		} 
		catch (Exception e) {
			
			System.err.println(e);
		}
	}

	@Then("Verify the Response status code is {int}")
	public void verify_the_response_status_code_is(int expectedResponse) {
		try {

			System.out.println("the responseeeee is " + resp.asString());

			assertEquals(resp.statusCode(), expectedResponse);
			Map<String, Object> mappGETVal = resp.as(Map.class);
		//	System.out.println("THE location  value uis "+mappGETVal.get("location"));
			Map<String, Object> locationRespData= (Map<String, Object>) mappGETVal.get("location");
			
			
		assertEquals( Double.toString(plObj.getLocation().getLat()), locationRespData.get("latitude"));
		assertEquals( Double.toString(plObj.getLocation().getLng()), locationRespData.get("longitude"));
		assertEquals(plObj.getName(), mappGETVal.get("name"));
		assertEquals(plObj.getAddress(), mappGETVal.get("address"));
		assertEquals(plObj.getLanguage(), mappGETVal.get("language"));
		
		assertEquals(plObj.getTypes(), mappGETVal.get("types"));
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

		@Then("Verify the Name Phone Number in response body")
		public void verify_the_name_phone_number_in_response_body() {
	
	try {
		
		
		
	} catch (Exception e) {
		
		System.err.println(e);
	}
		}
		
		
		// DELETE the recently created ADDPlace_API:

		@Given("Here we are going to delete the recently created API.")
		public void here_we_are_going_to_delete_the_recently_created_api() throws IOException {
			try {
				reqSpe = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
			} 
			catch (Exception e) {
				
				System.err.println(e);
			}
			
		}

		@When("user calls DeleteAPI request with DELETE http request")
		public void user_calls_delete_api_request_with_delete_http_request() {
			try {
				resp = reqSpe.when().delete("maps/api/place/delete/json").then().spec(responseSpec).extract().response();
			} 
			catch (Exception e) {
				
				System.err.println(e);
			}

			
		}

		@Then("Verify Response status code is {int}")
		public void verify_response_status_code_is(int expetedValue) {
			try {
				
				assertEquals(resp.statusCode(), expetedValue);
				System.out.println("the recently deleted Place Id " + place_id);
			} 
			catch (Exception e) {
				
				System.err.println(e);
			}
			
		}

		@Then("status in response body is OK")
		public void status_in_response_body_is_ok() {
			try {
				assertEquals(resp.getStatusLine(), "HTTP/1.1 200 OK");
			} 
			catch (Exception e) {
				
				System.err.println(e);
			}
			

		}
	
}
