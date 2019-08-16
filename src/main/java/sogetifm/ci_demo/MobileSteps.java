package sogetifm.ci_demo;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;


/**
 * Hello world!
 *
 */
public class MobileSteps 
{
	private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	
	private WebDriver driver;
	private WebDriverWait wait;
	private DesiredCapabilities caps;
	
	public MobileSteps() throws MalformedURLException {
		caps = new DesiredCapabilities();
		
		// caps.setCapability("deviceName", "73d3be57");
		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("platformName", "Android");
		
		caps.setCapability("appPackage", "com.android.calculator2");
		caps.setCapability("appActivity", ".Calculator");
	}
	
	@Given("^I open Calculator app on Android$")
	public void i_open_Calculator_app_on_Android() throws Throwable {
		driver = new AndroidDriver<WebElement>(new URL(APPIUM_URL), caps);
				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, 20);
	}

	@When("^I sum numbers \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_sum_numbers_and(String num1, String num2) throws Throwable {
		String id1 = "com.android.calculator2:id/digit_" + num1;
		String id2 = "com.android.calculator2:id/digit_" + num2;
		String sumid = "com.android.calculator2:id/op_add";
		String eqid = "com.android.calculator2:id/eq";

		driver.findElement(By.id(id1)).click();
		driver.findElement(By.id(sumid)).click();
		driver.findElement(By.id(id2)).click();
		
		takeSnapshot("captura-suma.png");
		driver.findElement(By.id(eqid)).click();
	}

	@Then("^I get the result \"(\\d)\"$")
	public void i_get_the_result(int expected) throws Throwable {
	    String resultid = "com.android.calculator2:id/result";
	    
	    int result = Integer.parseInt(driver.findElement(By.id(resultid)).getText());

	    
	    assertTrue(result == expected);
	}
	
	@Then("^I close the app$")
	public void i_close_the_app() throws Throwable {
		if (driver != null) {
			driver.quit();
		}
	}
	
	public void takeSnapshot(String filename) {
		if (driver == null) return; // No driver no snapshot...
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			File file = new File(String.format("target/screenshot/%s", filename));
			FileUtils.copyFile(scrFile, file);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}