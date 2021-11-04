package settings;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import objectts.Sessions;
import pages.GeneralSetttings;
import pages.RegisterSignIn;
import pages.SessionDetails;
import pages.SessionList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestAdminChangeBirdStatusSetting extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private GeneralSetttings generalSettings;
//	private SessionList sessionList;
	private SessionDetails sessionDetails;
	private Accounts account;
	private Sessions session;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		generalSettings = new GeneralSetttings(driver);
//		sessionList = new SessionList(driver);
		sessionDetails = new SessionDetails(driver);
		account = Accounts.FREE_ADMIN;
		session = Sessions.TEST_SCREENSHOT_1;
		session.setStatus("To do");
		
	}

	@Test
	public void testAdminChangeBirdStatusSetting() {
		
		//Sign in
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
		
		//Go to Settings and verify Toggle is on by default
		generalSettings.clickSettingsMenu();
		generalSettings.verifyBirdStatusToggleOn();
		
		//Go back to session list and verify the label is available
		generalSettings.goBackToSessionListFromSettings();
		sessionDetails.hoverMouseToASession(session.getTitle());
		sessionDetails.verifyNoBirdStatusOfSession(session.getTitle());
		
		//Set status of "Test screenshot 1" session is "To do" and verify status displays
		sessionDetails.setBirdStatusForSession(session.getTitle(), session.getStatus());
		sessionDetails.verifyBirdStatusOfSession(session.getTitle(), session.getStatus());
		
		//Go to session details and verify status is updated
		sessionDetails.clickOnASession(session.getTitle());
		sessionDetails.verifyBirdStatusOfSessionDetails(session.getStatus());
		
		//Remove status of session and verify status is "Label"
		sessionDetails.removeStatusLabel();
		sessionDetails.verifyNoBirdStatusOfSessionDetails();
		
		//Go to Settings and toggle off bird status
		sessionDetails.goBackToSessionList();
		sessionDetails.clickSettingsMenu();
		generalSettings.toggleBirdStatus();
		generalSettings.verifyBirdStatusToggleOff();
		
		//Go back to session list and verify bird status is not visible
		generalSettings.goBackToSessionListFromSettings();
		sessionDetails.verifyBirdStatusInvisible();
		
		
		//Go to a session and veriy bird status is not visible
		sessionDetails.clickOnASession(session.getTitle());
		sessionDetails.verifyBirdStatusInvisibleSessionDetails();
		
		//Reset data: Go to settings and toggle on bird status
		sessionDetails.goBackToSessionList();
		sessionDetails.clickSettingsMenu();
		generalSettings.toggleBirdStatus();
		generalSettings.verifyBirdStatusToggleOn();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
