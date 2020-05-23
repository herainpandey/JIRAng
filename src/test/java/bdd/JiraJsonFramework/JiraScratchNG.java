package bdd.JiraJsonFramework;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class JiraScratchNG {
   SessionFilter session = new SessionFilter();
	String id,key;
	
	
	@Test(priority = 0)
	public void loginJira()
	{
		RestAssured.baseURI="http://localhost:8080";
		String response = given().header("Content-Type", "application/json")
		.body("{\n    \"username\": \"himanshu\",\n    \"password\": \"pandey\"\n}")
		.filter(session).when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
	
	@Test(priority = 1)
	public void createIssue()
	{
		RestAssured.baseURI="http://localhost:8080";
		String response = given().header("Content-Type", "application/json")
		.body("{\r\n" + 
				"    \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"HPTES\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"This is my second issue\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"        },\r\n" + 
				"        \"reporter\": {\r\n" + 
				"            \"name\": \"Himanshu\"\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"  \r\n" + 
				"}")
		.filter(session).when().post("/rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
	
    JsonPath js = new JsonPath(response);
	id = js.getString("id");
	key = js.getString("key");
	
	}

	@Test(priority = 2)
	public void AddComment()
	{
		RestAssured.baseURI="http://localhost:8080";
		given().header("Content-Type", "application/json")
		.body("{\r\n" + 
				"    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.filter(session).when().post("/rest/api/2/issue/"+key+"/comment")
		.then().log().all().assertThat().statusCode(201);
	}
	
	@Test(priority = 3)
	public void GetComment()
	{
		RestAssured.baseURI="http://localhost:8080";
		 String comment=given().header("Content-Type", "application/json")
		.filter(session).when().get("/rest/api/2/issue/"+key+"/comment")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		 
		 JsonPath js= new JsonPath(comment);
		 System.out.println("Comment in Issue ID " +key+ " is " +js.getString("comments.body"));
	}

}
