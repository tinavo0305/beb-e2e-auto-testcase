package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractTest {
    protected WebDriver driver;
    protected Selenium selenium;
    protected WebDriverWait wdw;
    protected Actions actions;
    protected final String browserParam = "chrome";
	
	
	public WebDriver setUp(String browserName)
	{
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "/src/test/resources/edgedriver.exe");
			driver = new EdgeDriver();
		}
		
        wdw = new WebDriverWait(driver, 30);
        actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://release.app.birdeatsbug.com/");
		
		return driver;
	}
	
	public void closeBrowser()
	{
		if (driver != null)
		{
			driver.quit();
		}
	}
	

}
