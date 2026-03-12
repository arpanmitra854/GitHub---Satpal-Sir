package basePackage;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ProductViewActions extends BaseClass{
	@Test
	public void fetchAllProducts() {
		Response res=given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/products/alpha");
//		productId=res.jsonPath().getInt("data[1].productId");
		List<Integer> productIds = res.jsonPath().getList("data.productId");
		int productId = productIds.get(1);
		System.out.println(productId);
//		res.prettyPrint();
	}
}