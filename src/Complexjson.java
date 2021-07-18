import Payloads.AddPlace;
import io.restassured.path.json.JsonPath;

public class Complexjson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js=new JsonPath(AddPlace.courses());
		
		//1. Print No of courses returned by API
		
	int count=	js.getInt("courses.size");
	
	//System.out.println(count);
		//2.Print Purchase Amount
	
	//System.out.println(js.getInt("dashboard.purchaseAmount"));
	
	 //Print Title of the first course
	
	//System.out.println(js.get("courses[0].title").toString());
	
	// Print All course titles and their respective Prices
	
	for (int i=0; i<count; i++)
	{
		System.out.println(js.get("courses["+i+"].title").toString());
		System.out.println(js.getInt("courses["+i+"].price"));
	}
	
	//Print no of copies sold by RPA Course
	
	for (int j=0; j<count; j++)
	{
		String getTitle=js.get("courses["+j+"].title");
		if(getTitle.equalsIgnoreCase("RPA"))
		{
		System.out.println(js.getInt("courses["+j+"].copies"));
		break;
		}
	}
	
	//Verify if Sum of all Course prices matches with Purchase Amount
	
	int totalamount=js.getInt("dashboard.purchaseAmount");
	int l=0;
	for(int k=0; k<count; k++)
	{
	int courseprice=js.getInt("courses["+k+"].price");
	int copies = js.getInt("courses["+k+"].copies");
	int saleamount=courseprice*copies;
	l=l+saleamount;
	
	}
	
	if (l==totalamount)
	{
		System.out.println("prices matches");
	}
	else
	{
		System.out.println("price didnt match");
	}
		
	}

}
