package sessionList;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.ProfileSetttings;
import pages.RegisterSignIn;
import pages.SessionList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestNavitateToAccountSettingsFromSessionList extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SessionList sessionList;
	private Accounts account;
	private ProfileSetttings profile;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		sessionList = new SessionList(driver);
		profile = new ProfileSetttings(driver);
		account = Accounts.FREE_ADMIN;
		
	}

	@Test
	public void testNavitateToAccountSettingsFromSessionList() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		sessionList.goToAccountSettings();
		profile.verifyProfileHeaderDisplay();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
