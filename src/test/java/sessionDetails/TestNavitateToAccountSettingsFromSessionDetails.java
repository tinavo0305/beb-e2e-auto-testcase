package sessionDetails;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import objectts.Sessions;
import pages.ProfileSetttings;
import pages.RegisterSignIn;
import pages.SessionDetails;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestNavitateToAccountSettingsFromSessionDetails extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SessionDetails sessionDetails;
	private Accounts account;
	private ProfileSetttings profile;
	private Sessions session;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		sessionDetails = new SessionDetails(driver);
		profile = new ProfileSetttings(driver);
		account = Accounts.FREE_ADMIN;
		session = Sessions.TEST_SCREENSHOT_1;
	}

	@Test
	public void testNavitateToAccountSettingsFromSessionDetails() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		sessionDetails.clickOnASession(session.getTitle());
		sessionDetails.goToAccountSettings();
		profile.verifyProfileHeaderDisplay();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
