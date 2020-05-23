package specBuilder;

import CookieGen.Cookie;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec{
	
	public static RequestSpecification loginrReqSpec()
	{
		return new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri("http://localhost:8080/").build();
	
	}
	
	public static RequestSpecification createReq(String s)
	{
		
		return new RequestSpecBuilder().addHeader("Set-Cookie", s).addHeader("X-Atlassian-Token", "no-check").setContentType(ContentType.JSON)
				.setBaseUri("http://localhost:8080/").build();
	
	}
}

