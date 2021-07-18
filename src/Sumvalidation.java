import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import Payloads.AddPlace;
import io.restassured.path.json.JsonPath;

public class Sumvalidation {

	@Test
	public void sumofcources()
	{
		JsonPath js=new JsonPath(AddPlace.courses());
		
		int count=	js.getInt("courses.size");
		int totalamount=js.getInt("dashboard.purchaseAmount");
		int l=0;
		for(int k=0; k<count; k++)
		{
		int courseprice=js.getInt("courses["+k+"].price");
		int copies = js.getInt("courses["+k+"].copies");
		int saleamount=courseprice*copies;
		l=l+saleamount;
		
		}
		
		Assert.assertEquals(totalamount, l);
	}
	
	
}
