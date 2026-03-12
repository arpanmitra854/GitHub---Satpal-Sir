package shopper;

import java.nio.file.FileAlreadyExistsException;

import org.testng.annotations.Test;

public class TestNGMethods {
	@Test(priority = 2)
	public void cTest() {
		System.out.println("Test 1");
	}

	@Test
//	@Test(dependsOnMethods = "aTest", invocationCount = 3, enabled = true)
//	@Test(priority = 3)
	public void bTest() {
		System.out.println("Test 2");
	}
	
	@Test(priority = 1)
	public void aTest() {
		System.out.println("Test 3");
	}
}
