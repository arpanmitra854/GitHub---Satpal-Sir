package basePackage;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.response.Response;
//import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

public class ShopperCart extends BaseClass{
	int productId;
	int quantity;
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
		productId = productIds.get(0);
		List<Integer> quantityIds = res.jsonPath().getList("data.quantity");
		quantity = quantityIds.get(0);
		System.out.println(productId);
//		res.prettyPrint();
	}
	
	@Test(dependsOnMethods = "fetchAllProducts")
	public void addToCart() {
		AddToCartPojo add=new AddToCartPojo(productId, quantity);
		Response res = given()
				.relaxedHTTPSValidation()
				.auth().oauth2(jwtToken)
				.contentType("application/json")
				.body(add)
				.pathParam("shopperId", shopperId)
				.when()
				.post("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts");
		
		res.then().log().all();
	}
	
	@Test(dependsOnMethods = "addToCart")
	public void removeFromCart() {
		Response res = given()
				.relaxedHTTPSValidation()
				.auth().oauth2(jwtToken)
				.contentType("application/json")
				.pathParam("shopperId", shopperId)
				.pathParam("productId", productId)
				
				.when()
				.delete("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts/{productId}");
		
		res.then().log().all();
		
		System.out.println("this is my second commit");
		System.out.println("main");
	}
}