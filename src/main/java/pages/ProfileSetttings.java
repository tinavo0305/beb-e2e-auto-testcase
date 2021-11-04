package pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProfileSetttings extends Common{

	public ProfileSetttings(WebDriver driver) {
		super(driver);
	}
	
	private final String profileHeader = "//h1[text()='Profile']";
	
	public void clickEditDisplayName(String displayName){
		clickElement(driver, String.format("//span[@title='%s']", displayName));
		
	}
	
	public void enterNewDisplayName(String name) {
		typeValueIntoTextbox(driver, name);
	}
	
	public void verifyProfileHeaderDisplay() {
		Assert.assertTrue(isElementDisplayed(driver, profileHeader), "Profile header is not displayed!");
	}
	
	public void verifyDisplayName(String name) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//span[@title='%s']", name)), "Display Name is not correct!");
	}
	
	public void verifyEmailProfile(String email){
		Assert.assertTrue(isElementDisplayed(driver, String.format("//p[text()='%s']", email)), "Email is not correct!");
	}

	
	
	
}