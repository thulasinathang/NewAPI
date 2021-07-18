import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.Api;
import POJO.GetCourses;
import POJO.webAutomation;

public class AuthToken {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		String [] webcources= {"Selenium Webdriver Java", "Cypress","Protractor"};
		
		String URL="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgqZ2UPPsjZUny14Mju982hos4pO0qo5ev4bDtID9BB3sHSZ0-lecmpIc9fqOvQFQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		String partialURL=URL.split("code=")[1];
		String code=partialURL.split("&scope")[0];
		
		
		
		String	accesstokenresponse=given().urlEncodingEnabled(false).queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=new JsonPath(accesstokenresponse);
		String accessToken=js.get("access_token");
		
		//actual request
		RestAssured.baseURI="https://rahulshettyacademy.com";
		GetCourses gc=given().urlEncodingEnabled(false).log().all().queryParam("access_token", accessToken).expect()
				.defaultParser(Parser.JSON)
		.when().get("getCourse.php").as(GetCourses.class);
		//System.out.println(response);
		
		System.out.println(gc.getUrl());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getCourses().getWebAutomation().get(1).getCourseTitle());
		
		List<Api> apicources=gc.getCourses().getApi();
		
		for (int i=0; i<apicources.size(); i++)
		{
			if(apicources.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apicources.get(i).getPrice());
			}
		}
		
		ArrayList<String> actual=new ArrayList <String>();
		List<webAutomation> WebAutomationCources=gc.getCourses().getWebAutomation();
		
		
		for (int i=0; i<WebAutomationCources.size(); i++)
		{
		actual.add(WebAutomationCources.get(i).getCourseTitle());
		}
		
		List<String> expectedList=Arrays.asList(webcources);
		
		Assert.assertTrue(expectedList.equals(actual));
		
	}
}
