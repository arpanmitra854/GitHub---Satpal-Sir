package shopper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenshot {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.youtube.com/");
		//Downcast the WebDriver ref to TakesScreenshot Interface
		TakesScreenshot ts = (TakesScreenshot) driver;
		Thread.sleep(10000);
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("D:\\Capgemini Training\\API Testing\\Api Testing\\RestAssured\\target\\scrnsht2.png");
		FileHandler.copy(source, target);
		driver.quit();
	}
}
