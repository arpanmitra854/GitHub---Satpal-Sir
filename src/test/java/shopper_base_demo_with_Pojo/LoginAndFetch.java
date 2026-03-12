package shopper_base_demo_with_Pojo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LoginAndFetch extends BaseClass{
	@Test
	public void loginTest() {
		ShopperLoginPojo data = new ShopperLoginPojo("msdgoat@gmail.com", "Msd@1234", "SHOPPER");
		Response res = given()
				.contentType("application/json")
				.relaxedHTTPSValidation()
				.body(data)
				
				.when()
				.post("https://www.shoppersstack.com/shopping/users/login");
				System.out.println(res.prettyPrint());
				
//				shopperId = res.jsonPath().getString("data.userId");
//				System.out.println(shopperId);
//				jwtToken = res.jsonPath().getString("data.jwtToken");
//				System.out.println(jwtToken);
				
				res.then().log().all();
				System.out.println(shopperId+" "+jwtToken);
	}
}
