package settings;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.GeneralSetttings;
import pages.RegisterSignIn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestFullMemberCantEditSharedWorkspace extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private GeneralSetttings general;
	private Accounts paidAdmin;
	private Accounts paidMember;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		general = new GeneralSetttings(driver);
		paidAdmin = Accounts.PAID_ADMIN;
		paidMember = Accounts.PAID_MEMBER;
		
	}

	@Test
	public void testFullMemberCantEditSharedWorkspace() {
		registerSignIn.signInWithGithub(paidMember.getUsername(), paidMember.getPassword());
		general.clickWorkspaceDropdown(paidMember.getworkspaceName());
		general.selectWorkspaceFromDropdown(paidAdmin.getworkspaceName());
		general.clickSettingsMenu();
		general.verifyWorkspaceNameNotEditable(paidAdmin.getworkspaceName());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
