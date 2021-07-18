package Payloads;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {

	public static JsonPath rawToJson(String reponse)
	{
		
		JsonPath jp=new JsonPath(reponse);
		return jp;
	}
	
}
