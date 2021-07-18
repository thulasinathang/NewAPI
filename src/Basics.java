import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Payloads.AddPlace;
import Payloads.ReusableMethod;



//Given - all input details
//when - HTTP method and resources
//Then- validate the response

public class Basics {

	public void apiTest() throws IOException
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("/Users/thulasinathan/Desktop/Training/API Testing/Addplace.json")))).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		String newAddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\n" + 
				"\"place_id\":\""+placeId+"\",\n" + 
				"\"address\":\""+newAddress+"\",\n" + 
				"\"key\":\"qaclick123\"\n" + 
				"}\n" + 
				"")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo ("Address successfully updated"));
		
		String Getresponse=given().log().all().queryParams("key", "qaclick123", "place_id", placeId).header("Content-Type","application/json")
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().asString();
		
		JsonPath jp=ReusableMethod.rawToJson(Getresponse);
		
		String add=jp.getString("address");
		Assert.assertEquals(add, newAddress);
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Basics nm=new Basics();
		nm.apiTest();
		
		
	}

}
