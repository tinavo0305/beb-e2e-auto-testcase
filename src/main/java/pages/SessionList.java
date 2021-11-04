package pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SessionList extends Common{

	public SessionList(WebDriver driver) {
		super(driver);
	}
	
	private final String SLACK_COMMUINITY_URL = "https://birdeatsbugcommunity.slack.com/join/shared_invite/"
			+ "enQtODIyMzMzNzI1MTY5LTE3OTY1OTgxYTcyNzRkMGQxMmRkYjJlN2Q1ZmFkZmQ1OTgyNGUzNDEwODI0YjBmMmVlZWVmNzczMWFiNTMwMWI#/shared-invite/email";	
	private final String FEATURE_REQUEST_URL = "https://birdeatsbug.kampsite.co/";
	private final String ABOUT_URL_QUERY_STRING = "?settings=about";
	
	private final String accountAvatarDropdown = "//button/img[@class='gravatar']";
	private final String accountSettingsLink = "//button[text()='Account settings']";
	private final String helpButton = "//button[@class='help-button']";
	private final String joinOurSlackLink = "//a[contains(text(),'Join our Slack')]";
	private final String suggestAFeatureLink = "//a[contains(text(),'Suggest a feature')]";
	private final String aboutLink = "//a[contains(text(),'About')]";
	private final String getHelpLink = "//button[text()='Get help']";
	private final String contactUsModal = "//div[@class='base-overlay-content']";
//	private final String birdSttusLabel = "//button[contains(@class,'button-base button-toggle')]";
	
	public void goToAccountSettings() {
		clickElement(driver, accountAvatarDropdown);
		clickElement(driver, accountSettingsLink);
	}
	
	public void clickHelpIcon() {
		clickElement(driver, helpButton);
	}
	
	public void clickJoinOurSlack() {
		clickElement(driver, joinOurSlackLink);
	}
	
	public void clickSuggestAFeature() {
		clickElement(driver, suggestAFeatureLink);
	}
	
	public void clickAboutLinkAndVerifyAboutPageDisplays() {
		String currentUrl = getCurrentUrl(driver);
		clickElement(driver, aboutLink);
		Assert.assertEquals(getCurrentUrl(driver), currentUrl + ABOUT_URL_QUERY_STRING);
		
	}
	
	public void clickGetHelp() {
		clickElement(driver, getHelpLink);
	}
	
	public void verifySlackCommunityUrl() {
		switchTabByIndex(driver, 1);
		Assert.assertEquals(getCurrentUrl(driver), SLACK_COMMUINITY_URL);
		closeTab(driver);
		switchTabByIndex(driver, 0);
	}
	
	public void verifyFeatureRequestPageDisplay() {
		switchTabByIndex(driver, 1);
		Assert.assertEquals(getCurrentUrl(driver), FEATURE_REQUEST_URL);
		closeTab(driver);
		switchTabByIndex(driver, 0);
	}
	
	public void verifyContactUsModalDisplay() {
		Assert.assertTrue(isElementDisplayed(driver, contactUsModal));
	}
	
	public void hoverMouseToASession(String title) {
		hoverMouseToElement(driver, String.format("//div[@title='%s']", title));
	}
	
	public void clickOnASession(String title) {
		clickElementByJS(driver, String.format("//div[@title='%s']/ancestor::div/a", title));
	}
	
	public void setBirdStatusForSession(String sessionTitle, String status) {
		clickElement(driver, String.format("//div[@title='%s']//button[contains(@class,'button-base button-toggle')]", sessionTitle));
		clickElement(driver, String.format("//div[@title='%s']//button[contains(text(),'%s')]", sessionTitle, status));
	}
	
	public void verifyNoBirdStatusOfSession(String sessionTitle) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//div[@title='%s']//button/span[contains(text(),'Label')]", sessionTitle)));
	}
	
	public void verifyBirdStatusOfSession(String sessionTitle, String status) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//div[@title='%s']//button[contains(text(),'%s')]", sessionTitle, status)));
	}
	
	public void verifyBirdStatusInvisible() {
		waitInSeconds(2);
		Assert.assertFalse(isElementVisible(driver, "//button[contains(@class,'no-label-selected')]"));
		Assert.assertFalse(isElementVisible(driver, "//button[contains(@class,'label-selected')]"));
	}
	
	public void verifySessionTitle(String title) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//div[@title='%s']", title)), 
				"No session with title " + title + "displays!");
	}
		
	
	
}