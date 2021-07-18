import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Payloads.AddPlace;
import Payloads.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider="BookData")
	
	public void AddbookDy(String isbn, String aisle)
{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type", "application/json")
		.body(AddPlace.Addbook(isbn, aisle)).when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	JsonPath js=ReusableMethod.rawToJson(response);
	
	String ID=js.get("ID");
	
	//delete
	
	given().header("Content-Type", "application/json")
	.body(AddPlace.Deletebook(ID)).when().post("/Library/DeleteBook.php").
	then().log().all().assertThat().statusCode(200);
	}
	
@DataProvider(name="BookData")

public Object[][] GetData()
{
	return new Object [][] {{"isbn1", "0999"},{"isbn2", "0888"},{"isbn3", "07777"}};
}
	
}
