package specBuilder;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
	
	public static ResponseSpecification loginResspec()
	{
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
	
	}
	
	public static ResponseSpecification createResspec()
	{
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(201).build();
	
	}
	

}
