package pages;


import org.openqa.selenium.WebDriver;

import commons.Selenium;

public class RegisterSignIn extends Selenium{
	protected WebDriver driver;

	public RegisterSignIn(WebDriver driver) {
		this.driver = driver;
	}
	
	private static final String continueWithGithub = "//a[@data-provider='github']";
	
	private static final String githubUserNameTextbox = "//input[@id='login_field']";
	private static final String githubUserNamePassword = "//input[@id='password']";
	private static final String githubSignInButton = "//input[@name='commit']";
	
	public void signInWithGithub(String username, String password) {
		clickElement(driver, continueWithGithub);
		sendKeysToElement(driver, githubUserNameTextbox, username);
		sendKeysToElement(driver, githubUserNamePassword, password);
		clickElement(driver, githubSignInButton);
	}
	
	
	
}