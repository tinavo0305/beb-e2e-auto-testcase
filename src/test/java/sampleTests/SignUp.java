package sampleTests;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class SignUp {
	public WebDriver driver;
	public WebDriverWait wait;
	public String randomAlias;
	public String githubUsername;
	public String githubPass;
	public JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		githubUsername = "birdeatsbugtester1";
		githubPass = "birdeatsbug123";
		

	}

	@Test
	public void signUp() throws InterruptedException {
		driver.get("http://staging.app.birdeatsbug.com/");
		
		
		//Login using github
		driver.findElement(By.xpath("//a[@data-provider='github']")).click();
		driver.findElement(By.id("login_field")).sendKeys(githubUsername);
		driver.findElement(By.id("password")).sendKeys(githubPass);
		driver.findElement(By.name("commit")).click();
		
		
	}

	@AfterClass
	public void afterClass() {
	}
	
	/**
	 * Select an item from a drop-down that the Select method does not support It
	 * will click on the drop-down, wait until items visible, scroll to the item and
	 * click on the item using js click
	 * 
	 * @param driver
	 * @param parentsXpath  xPath of the drop-down
	 * @param childrenXpath xPath of the items in the drop-down
	 * @throws InterruptedException 
	 */
	public void selectItemFromCustomDropdown(WebDriver driver, String parentsXpath, String childrenXpath,
			String selectedItem) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath(parentsXpath));
		wait = new WebDriverWait(driver, 15);
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(1000);
		List<WebElement> elements = driver.findElements(By.xpath(childrenXpath));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childrenXpath)));
		for (WebElement childElement : elements) {
			if (childElement.getText().equals(selectedItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					Thread.sleep(1000);
					js.executeScript("arguments[0].click();", element);
				}
				Thread.sleep(1000);
				break;
			}
		}

	}

}
