package sessionDetails;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import objectts.Sessions;
import pages.RegisterSignIn;
import pages.SessionDetails;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestSessionPermission extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SessionDetails sessionDetails;
	private Accounts account;
	private Sessions session;


	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		sessionDetails = new SessionDetails(driver);
		account = Accounts.FREE_ADMIN;
		session = Sessions.SESSION_PERMISSION;
		
	}

	@Test
	public void testSessionPermission() {
		
		//Sign in and go to a private session
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		sessionDetails.clickOnASession(session.getTitle());


	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
