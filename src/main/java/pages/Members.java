package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Members extends Common {

	public Members(WebDriver driver) {
		super(driver);
	}

	private final String sendInviteButton = "//button/span[contains(text(),'Send invite')]";
	
	
	public void verifySendInviteButtonDisplay() {
		Assert.assertTrue(isElementDisplayed(driver, sendInviteButton), "Send invite button is not visible!");
	}

}
