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

public class TestFullMemberCanOnlyViewSubscription extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SubscriptionPlan subscription ;
	private Accounts paidAdmin;
	private Accounts paidMember;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		subscription = new SubscriptionPlan(driver);
		paidAdmin = Accounts.PAID_ADMIN;
		paidMember = Accounts.PAID_MEMBER;
		
	}

	@Test
	public void testFullMemberCanOnlyViewSubscription() {
		registerSignIn.signInWithGithub(paidMember.getUsername(), paidMember.getPassword());
		subscription.clickWorkspaceDropdown(paidMember.getworkspaceName());
		subscription.selectWorkspaceFromDropdown(paidAdmin.getworkspaceName());
		subscription.clickSettingsMenu();
		subscription.clickSubscriptionPlanMenu(2);
		subscription.verifyManagePlanInvisible();
		subscription.verifyCurrentPlanIsBusiness();
		subscription.verifyGetInTouchButtonInvisible();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
