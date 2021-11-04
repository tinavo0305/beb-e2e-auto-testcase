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

public class TestMemberClickInviteMemberAndIntegrations extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private Members members;
	private Integrations integrations;
	private Accounts admin;
	private Accounts member;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		members = new Members(driver);
		integrations = new Integrations(driver);
		member = Accounts.PAID_MEMBER;
		admin = Accounts.PAID_ADMIN;
		
	}

	@Test
	public void testMemberClickInviteMemberAndIntegrations() {
		registerSignIn.signInWithGithub(member.getUsername(), member.getPassword());
		members.clickWorkspaceDropdown(member.getworkspaceName());
		members.selectWorkspaceFromDropdown(admin.getworkspaceName());
		members.clickInviteMemberLinkLeftMenu();
		members.verifySendInviteButtonDisplay();
		members.goBackToSessionListFromSettings();
		
		integrations.clickIntegrationsLinkLeftMenu();
		integrations.verifyIntegrationsPageHeaderDisplay();
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
