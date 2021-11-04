package settings;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.ProfileSetttings;
import pages.RegisterSignIn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestEditProfileDetails extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private ProfileSetttings profile;
	private Accounts account;
	private String newDisplayName = "BEB Tester 1";

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		profile = new ProfileSetttings(driver);
		account = Accounts.FREE_ADMIN;
		
	}

	@Test
	public void testEditProfileDetails() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		profile.clickSettingsMenu();
		profile.clickProfileMenu();
		profile.verifyEmailProfile(account.getEmail());
		profile.clickEditDisplayName(account.getDisplayName());
		profile.enterNewDisplayName(newDisplayName);
		profile.clickEditDisplayName(newDisplayName);
		profile.enterNewDisplayName(account.getDisplayName());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
