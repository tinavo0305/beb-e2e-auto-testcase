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

public class TestEditTitleDescription extends AbstractTest{
	
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
	public void testEditTitleDescription() {
		
		//Sign in
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		//Go to a session
		sessionDetails.clickOnASession(session.getTitle());
		
		//Edit title and description but cancel
		sessionDetails.clickEditTitleDescription();
		sessionDetails.inputTitleAndDescription(newTitle, newDesc);
		sessionDetails.cancelTitleDescription();
		sessionDetails.verifyTitleAndDescription(session.getTitle(), session.getDescription());
		
		//Edit title and description and save
		sessionDetails.clickEditTitleDescription();
		sessionDetails.inputTitleAndDescription(newTitle, newDesc);
		sessionDetails.saveTitleDescription();
		sessionDetails.verifyTitleAndDescription(newTitle, newDesc);
		
		//Go back to session list and verify title is updated
		sessionDetails.goBackToSessionList();
		sessionDetails.verifySessionTitle(newTitle);
		
		//Reset data to original
		sessionDetails.clickOnASession(newTitle);
		sessionDetails.clickEditTitleDescription();
		sessionDetails.inputTitleAndDescription(session.getTitle(), session.getDescription());
		sessionDetails.saveTitleDescription();

	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
