package settings;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.Integrations;
import pages.Members;
import pages.RegisterSignIn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestAdminClickInviteMemberIntegrations extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private Members members;
	private Integrations integrations;
	private Accounts account;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		members = new Members(driver);
		integrations = new Integrations(driver);
		account = Accounts.PAID_ADMIN;
		
	}

	@Test
	public void testAdminClickInviteMemberIntegrations() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		members.clickInviteMemberLinkLeftMenu();
		members.verifySendInviteButtonDisplay();
		members.goBackToSessionListFromSettings();
		integrations.clickWorkspaceDropdown(account.getworkspaceName());
		integrations.clickIntegrationsLinkLeftMenu();
		integrations.verifyIntegrationsPageHeaderDisplay();
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
