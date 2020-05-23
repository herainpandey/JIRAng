package CookieGen;

public class Cookie{
	
	public static String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		String s="JSESSIONID=";
		this.cookie = s+cookie;
	} 
	
	
	
	
}
