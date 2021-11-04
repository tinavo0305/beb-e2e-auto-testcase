package settings;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.RegisterSignIn;
import pages.SubscriptionPlan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestFreeAdminSubscription extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SubscriptionPlan subscription;
	private Accounts account;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		subscription = new SubscriptionPlan(driver);
		account = Accounts.FREE_ADMIN;
		
	}

	@Test
	public void testFreeAdminSubscription() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		subscription.clickSettingsMenu();
		subscription.clickSubscriptionPlanMenu(1);
		subscription.verifyUpgradeBusinessButtonVisible();
		subscription.clickUpgradeBusinessPlan();
		subscription.verifyPaddleUpgradeModalDisplay();
		subscription.goBackToSubscriptionFromPaddleUpgrade();
		subscription.clickGetInTouchCustomPlan();
		subscription.verifyContactUsCustomPlanModalDisplay();
		subscription.closeContactUsModal();
		subscription.clickLearnMoreLink();
		subscription.verifyPricingPageDisplay();
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
