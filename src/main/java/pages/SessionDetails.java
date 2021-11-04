package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SessionDetails extends SessionList{

	public SessionDetails(WebDriver driver) {
		super(driver);
	}
	
	//Top menu section
	private final String birdStatusButton = "//button[contains(@class,'button-base button-toggle')]";
	private final String removeLabelButton = "//button[contains(text(),'Remove label')]";
	private final String goBackToSessionList = "//a[@href='/']";
	private final String lockIcon = "(//button[@class='button-icon'])[3]";
	private final String lockIconTooltip = "//div[@class='base-tooltip' and contains(text(),'Make session')]";
	
	//Title & description section
	private final String editTitleDescriptionButton = "//button[@class='mt-1 button-icon']";
	private final String titleTextbox = "//textarea[@class='textarea title']";
	private final String descriptionTextbox = "//textarea[@class='textarea description']";
	private final String saveButton = "//button[text()='Save']";
	private final String cancelButton = "//button[text()='Save']/preceding-sibling::button";
	
	//Technical data section
	private final String consoleTab = "//button[contains(text(),'Console')]";
	private final String networkTab = "//button/span[text()='Network']";
	private final String networkSettingIcon = "//*[local-name()='svg' and @class='base-icon link-white']";
	private final String systemInfoTab = "//button/span[text()='System Info']";
	
	
	
	public void verifyBirdStatusOfSessionDetails(String status) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//button[contains(text(),'%s')]", status)));
	}
	
	public void verifyNoBirdStatusOfSessionDetails() {
		Assert.assertTrue(isElementDisplayed(driver, "//button/span[contains(text(),'Label')]"));
	}
	
	public void changeBirdStatusOfSessionDetails(String status) {
		clickElement(driver, birdStatusButton);
		clickElement(driver, String.format("//button[contains(text(),'%s')]", status));
	}
	
	public void removeStatusLabel() {
		clickElement(driver, birdStatusButton);
		clickElement(driver, removeLabelButton);
	}
	
	public void goBackToSessionList() {
		clickElement(driver, goBackToSessionList);
	}

	public void verifyBirdStatusInvisibleSessionDetails() {
		waitInSeconds(2);
		Assert.assertFalse(isElementVisible(driver, "//button[contains(@class,'no-label-selected')]"));
		Assert.assertFalse(isElementVisible(driver, "//button[contains(@class,'label-selected')]"));
	}
	
	public void clickEditTitleDescription() {
		clickElement(driver, editTitleDescriptionButton);
	}
	
	public void inputTitleAndDescription(String title, String description) {
		selectAllTextboxAndClear(driver);
		sendKeysToElement(driver, titleTextbox, title);
		sendKeyboardToElement(driver, titleTextbox, Keys.ENTER);
		
		selectAllTextboxAndClear(driver);
		sendKeysToElement(driver, descriptionTextbox, description);
	}
	
	public void saveTitleDescription() {
		clickElement(driver, saveButton);
	}
	
	public void cancelTitleDescription() {
		clickElement(driver, cancelButton);
	}
	
	public void verifyTitleAndDescription(String title, String description) {
		Assert.assertTrue(getAttribute(driver, titleTextbox, "value").equalsIgnoreCase(title));
		Assert.assertTrue(getAttribute(driver, descriptionTextbox, "value").equalsIgnoreCase(description));
	}
	
	public void cancelLosingChangesAlert() {
		cancelAlert(driver);
	}
	
	public void acceptLosingChangesAlert() {
		acceptAlert(driver);
	}
	
	public void clickConsoleTab() {
		clickElement(driver, consoleTab);
	}
	
	public void clickNetworkTab() {
		clickElement(driver, networkTab);
	}
	
	public void clickNetworkSettingIcon() {
		clickElement(driver, networkSettingIcon);
	}
	
	public void clickSystemInfoTab() {
		clickElement(driver, systemInfoTab);
	}
	
	
	
}