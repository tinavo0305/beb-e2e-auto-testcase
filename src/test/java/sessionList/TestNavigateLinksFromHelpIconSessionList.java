package sessionList;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.RegisterSignIn;
import pages.SessionList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestNavigateLinksFromHelpIconSessionList extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SessionList sessionList;
	private Accounts account;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		sessionList = new SessionList(driver);
		account = Accounts.FREE_ADMIN;
		
	}

	@Test
	public void testNavigateLinksFromHelpIconSessionList() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		sessionList.clickHelpIcon();
		sessionList.clickJoinOurSlack();
		sessionList.verifySlackCommunityUrl();
		
		sessionList.clickHelpIcon();
		sessionList.clickSuggestAFeature();
		sessionList.verifyFeatureRequestPageDisplay();
		
		sessionList.clickHelpIcon();
		sessionList.clickAboutLinkAndVerifyAboutPageDisplays();
//		sessionList.verifyAboutPageDisplay();
		
		sessionList.clickHelpIcon();
		sessionList.clickGetHelp();
		sessionList.verifyContactUsModalDisplay();
		
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
