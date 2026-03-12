package shopper;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ShopperLoginTest {

//	@Test
//	public void loginTest() {
////		RestAssured.
//		given()
//		.contentType("application/json")
//		.relaxedHTTPSValidation()
//		.body("{\r\n"
//				+ "  \"email\": \"msdgoat@gmail.com\",\r\n"
//				+ "  \"password\": \"Msd@1234\",\r\n"
//				+ "  \"role\": \"SHOPPER\"\r\n"
//				+ "}")
//		
//		.when()
//		.post("https://www.shoppersstack.com/shopping/users/login")
//		
//		.then()
//		.assertThat().statusCode(200)
//		.log().all();
//	}
	
	String shopperId, jwtToken;
	
	@Test
	public void loginTest() {
//		RestAssured.
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", "msdgoat@gmail.com");
		map.put("password", "Msd@1234");
		map.put("role", "SHOPPER");
		Response res = given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body(map)
		
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login");
		
		shopperId = res.jsonPath().getString("data.userId");
		System.out.println(shopperId);
		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println(jwtToken);
//		.then()
//		.assertThat().statusCode(200)
//		.log().all();
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void fetchData() {
		Response res = given()
		.pathParam("shopperID", "363316")
		.contentType("application/json")
		.auth().oauth2(jwtToken)
		.relaxedHTTPSValidation()
		
		.baseUri("https://www.shoppersstack.com/shopping")
		.when().get("/shoppers/{shopperID}");
		
		res.then()
//		.assertThat().statusCode(200)
		.log().all();
	}
	
	

}
