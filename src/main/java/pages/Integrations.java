package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Integrations extends Common {

	public Integrations(WebDriver driver) {
		super(driver);
	}

	private final String upgradeButton = "//button[text()='Upgrade']";
	private final String integrationsPageHeader = "//h1[text()='Integrations']";
	
	
	public void clickUpgradeButton() {
		clickElement(driver, upgradeButton);
	}
	
	public void verifyIntegrationsPageHeaderDisplay() {
		Assert.assertTrue(isElementDisplayed(driver, integrationsPageHeader), "Integration Header is not displayed!");
	}

}
