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

public class TestAdminEditSharedWorkspaceName extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private GeneralSetttings general;
	private Accounts account;
	private String newWSName = "Free WS";

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		general = new GeneralSetttings(driver);
		account = Accounts.FREE_ADMIN;
		
	}

	@Test
	public void testAdminEditSharedWorkspaceName() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		general.clickSettingsMenu();
		general.verifyWorkspaceName(account.getworkspaceName());
		general.clickEditWorkspaceName(account.getworkspaceName());
		general.enterNewWorkspaceName(newWSName);
		general.verifyWorkspaceName(newWSName);
		general.goBackToSessionListFromSettings();
		general.verifyWorkSpaceNameInLeftMenu(newWSName);
		
		//Clean up
		general.clickSettingsMenu();
		general.clickEditWorkspaceName(newWSName);
		general.enterNewWorkspaceName(account.getworkspaceName());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
