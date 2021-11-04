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

public class TestConfirmationLeavingWithoutSavingTitleDescription extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private SessionDetails sessionDetails;
	private Accounts account;
	private Sessions session;
	private String newTitle, newDesc;


	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		sessionDetails = new SessionDetails(driver);
		account = Accounts.FREE_ADMIN;
		session = Sessions.TITLE_AND_DESCRIPTION;
		newTitle = session.getTitle() + " new";
		newDesc = session.getDescription() + " new";
		
	}

	@Test
	public void testConfirmationLeavingWithoutSavingTitleDescription() {
		
		//Sign in
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		//Go to a session
		sessionDetails.clickOnASession(session.getTitle());
		
		//Edit title and description and leave the session without saving
		sessionDetails.clickEditTitleDescription();
		sessionDetails.inputTitleAndDescription(newTitle, newDesc);
		sessionDetails.goBackToSessionList();
		
		//Cancel alert and verify session still displays with unsaved data
		sessionDetails.cancelLosingChangesAlert();
		sessionDetails.verifyTitleAndDescription(newTitle, newDesc);
		
		//Leave page, accept alert and verify data is not saved
		sessionDetails.goBackToSessionList();
		sessionDetails.acceptLosingChangesAlert();
		sessionDetails.verifySessionTitle(session.getTitle());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
