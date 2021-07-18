import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Locate;

public class SpecBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	AddPlace ad= new AddPlace();	
	Locate lo=new Locate();
		
	

	ad.setAccuracy(50);
	ad.setName("Thulasinathan");
	ad.setPhone_number("+1 905-598-4263");
	ad.setAddress("10 Lisa Street");
	List<String> type  = new ArrayList<String>();
	type.add("shoe shop");
	type.add("bata");
	ad.setTypes(type);
	ad.setWebsite("www.thulasi.com");
	ad.setLanguage("En");
	lo.setLat(-38.383494);
	lo.setLng(33.427362);
	ad.setLocation(lo);
	
		
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
	.setContentType(ContentType.JSON).build();
	ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		
	
	RequestSpecification request=given().spec(req).body(ad);
		String response=request.when().post("/maps/api/place/add/json")
		.then().spec(res).extract().response().asString();		

	System.out.println(response);
	




	
		
	}

}
