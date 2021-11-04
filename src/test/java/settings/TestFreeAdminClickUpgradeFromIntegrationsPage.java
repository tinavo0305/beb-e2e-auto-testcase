package settings;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.Integrations;
import pages.RegisterSignIn;
import pages.SubscriptionPlan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestFreeAdminClickUpgradeFromIntegrationsPage extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private Integrations integrations;
	private SubscriptionPlan subscription;
	private Accounts account;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		integrations = new Integrations(driver);
		subscription = new SubscriptionPlan(driver);
		account = Accounts.FREE_ADMIN;
		
	}

	@Test
	public void testFreeAdminClickUpgradeFromIntegrationsPage() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		integrations.clickSettingsMenu();
		integrations.clickIntegrationsMenu();
		integrations.clickUpgradeButton();
		subscription.verifyUpgradeBusinessButtonVisible();
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
