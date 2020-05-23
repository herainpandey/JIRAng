package bdd.JiraJsonFramework;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class JiraScratchNG {
   SessionFilter session = new SessionFilter();
	String newsession;
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
		//System.out.println(newsession);
		RestAssured.baseURI="http://localhost:8080";
		given().header("Content-Type", "application/json")
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
		.then().log().all().assertThat().statusCode(201);
	}

	@Test(priority = 3)
	public void AddComment()
	{
		//System.out.println(newsession);
		RestAssured.baseURI="http://localhost:8080";
		given().header("Content-Type", "application/json")
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
		.filter(session).when().post("/rest/api/2/issue/{{bug}}/comment")
		.then().log().all().assertThat().statusCode(201);
	}

}
