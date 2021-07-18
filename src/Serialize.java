import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Locate;

public class Serialize {

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
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
	String response=	given().queryParam("key", "qaclick123")
		.body(ad).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();		

	System.out.println(response);
	




	
		
	}

}
