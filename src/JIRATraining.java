import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.Assert;
public class JIRATraining {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String newcomment="new Compare comments";
		String mycomment="";
		SessionFilter session=new SessionFilter();
		RestAssured.baseURI="http://localhost:8080/";
		
		//login
		given().log().all().header("Content-Type", "application/json")
		.body("{ \"username\": \"thulasinathan\", \"password\": \"Thulasi71@\" }")
		.when().filter(session).post("rest/auth/1/session").then().assertThat().statusCode(200).extract().response().asString();
		
		//Add comment
		String addcomment=given().log().all().pathParam("key", "RTRTRAIL-2").header("Content-Type", "application/json")
		.body("{\n" + 
				"    \"body\": \""+newcomment+"\",\n" + 
				"    \"visibility\": {\n" + 
				"        \"type\": \"role\",\n" + 
				"        \"value\": \"Administrators\"\n" + 
				"    }\n" + 
				"}").when().filter(session).post("rest/api/2/issue/{key}/comment").then().log().all()
		.assertThat().statusCode(201).extract().response().asString();
		
		JsonPath jd=new JsonPath(addcomment);
		String commentid=jd.get("id");
		
		//adding attachment
		/*given().log().all().filter(session).pathParam("key", "RTRTRAIL-2").header("X-Atlassian-Token", "no-check")
		.header("Content-Type", "multipart/form-data")
		.multiPart("file", new File("/Users/thulasinathan/Desktop/TNSTC_.pdf")).when().post("rest/api/2/issue/{key}/attachments")
		.then().assertThat().statusCode(200);*/
		
		//Get the issue
		
		String Getissue=given().log().all().filter(session).pathParam("key", "RTRTRAIL-2").queryParam("fields", "comment").when().get("rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= new JsonPath(Getissue);
		
		int commentsize=js.getInt("fields.comment.comments.size");
		
		System.out.println(commentsize);
		
		for (int i=0; i<commentsize; i++)
		{
			String id=js.get("fields.comment.comments["+i+"].id");
			System.out.println(id);
			
			if (id.equalsIgnoreCase(commentid))
			{
				
				mycomment=js.get("fields.comment.comments["+i+"].body");
				System.out.println(mycomment);
				Assert.assertEquals(newcomment, mycomment);
				break;
			}
		}
		//assertEquals(newcomment, mycomment);
	}

}
