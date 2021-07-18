import org.testng.annotations.Test;

import Payloads.AddPlace;
import Payloads.ReusableMethod;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Library {

	
	@Test
	
	public void Addbook()
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type", "application/json")
		.body(AddPlace.Addbook("ujskd", "343")).when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	JsonPath js=ReusableMethod.rawToJson(response);
	
	System.out.println(js.get("ID").toString());
		
	}
	
}
