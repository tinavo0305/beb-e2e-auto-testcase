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

public class TestNavigateLinksFromHelpIconSessionDetails extends AbstractTest{
	
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
		session = Sessions.TEST_SCREENSHOT_1;
		
	}

	@Test
	public void testNavigateLinksFromHelpIconSessionDetails() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		sessionDetails.clickOnASession(session.getTitle());
		
		sessionDetails.clickHelpIcon();
		sessionDetails.clickJoinOurSlack();
		sessionDetails.verifySlackCommunityUrl();
		
		sessionDetails.clickHelpIcon();
		sessionDetails.clickSuggestAFeature();
		sessionDetails.verifyFeatureRequestPageDisplay();
		
		sessionDetails.clickHelpIcon();
		sessionDetails.clickAboutLinkAndVerifyAboutPageDisplays();
//		sessionDetails.verifyAboutPageDisplay();
		
		sessionDetails.clickHelpIcon();
		sessionDetails.clickGetHelp();
		sessionDetails.verifyContactUsModalDisplay();
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
