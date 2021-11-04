package sampleTests;

import org.testng.annotations.Test;

import commons.AbstractTest;
import objectts.Accounts;
import pages.RegisterSignIn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class SignIn extends AbstractTest{
	
	public WebDriver driver;
	private RegisterSignIn registerSignIn;
	private Accounts account;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(@Optional(browserParam) String browser) {
		driver = setUp(browser);
		registerSignIn = new RegisterSignIn(driver);
		account = Accounts.PAID_ADMIN;
		
	}

	@Test
	public void signInWithGithub() {
		registerSignIn.signInWithGithub(account.getUsername(), account.getPassword());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
