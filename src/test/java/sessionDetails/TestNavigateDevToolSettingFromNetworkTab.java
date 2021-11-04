package sessionDetails;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import objectts.Sessions;
import pages.GeneralSetttings;
import pages.RegisterSignIn;
import pages.SessionDetails;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestNavigateDevToolSettingFromNetworkTab extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SessionDetails sessionDetails;
	private GeneralSetttings general;
	private Accounts account;
	private Sessions session;


	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		sessionDetails = new SessionDetails(driver);
		general = new GeneralSetttings(driver);
		account = Accounts.FREE_ADMIN;
		session = Sessions.TEST_SCREENSHOT_1;
		
	}

	@Test
	public void testNavigateDevToolSettingFromNetworkTab() {
		
		//Sign in
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		//Go to a session
		sessionDetails.clickOnASession(session.getTitle());
		
		//Click on gear icon from Network tab
		sessionDetails.clickNetworkTab();
		sessionDetails.clickNetworkSettingIcon();
		
		//Verify the Dev tool setting displays
		general.verifyDevToolToggleVisible();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
