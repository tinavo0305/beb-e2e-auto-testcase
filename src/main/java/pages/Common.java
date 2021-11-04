package pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.Selenium;

public class Common extends Selenium{
	protected WebDriver driver;

	public Common(WebDriver driver) {
		this.driver = driver;
	}
	
	private final String settingsMenu = "//button[text()='Settings']";
	private final String profileMenu = "//button[text()='Profile']";
	private final String generalMenu = "//button[text()='General']";
	private final String membersMenu = "//button[text()='Members']";
	private final String subscriptionPlanMenu = "(//button[contains(text(),'Subscription Plan')])[%d]";
	private final String integrationMennu = "//button[text()='Integrations']";
	private final String invieMemberLink = "//button/span[contains(text(),'Invite Members')]";
	private final String integrationsLink = "//button/span[contains(text(),'Integrations')]";
	private final String cancelContactUsButton = "//button[text()='Cancel']";
	private final String goBackToSessionListFromSettings = "//button/span[contains(text(),'Settings')]";
	
	public void clickWorkspaceDropdown(String workspaceName) {
		clickElement(driver, String.format("//button[@class='popover-trigger']/span[@title='%s']", workspaceName));
	}
	
	public void selectWorkspaceFromDropdown(String workspaceName) {
		clickElement(driver, String.format("//button[@class='popover-link']/span[@title='%s']", workspaceName));
	}
	
	public void clickSettingsMenu() {
		clickElement(driver, settingsMenu);
	}
	
	public void goBackToSessionListFromSettings() {
		clickElement(driver, goBackToSessionListFromSettings);
	}
	
	public void clickProfileMenu() {
		clickElement(driver, profileMenu);
	}
	
	public void clickGeneralMenu() {
		clickElement(driver, generalMenu);
	}
	
	public void clickMembersMenu() {
		clickElement(driver, membersMenu);
	}
	
	public void clickSubscriptionPlanMenu(int id) {
		clickElementByJS(driver, String.format(subscriptionPlanMenu, id));//workspace number
	}
	
	public void clickIntegrationsMenu() {
		clickElement(driver, integrationMennu);
	}
	
	public void clickInviteMemberLinkLeftMenu() {
		clickElement(driver, invieMemberLink);
	}
	
	public void clickIntegrationsLinkLeftMenu() {
		clickElement(driver, integrationsLink);
	}
	
	public void closeContactUsModal() {
		clickElement(driver, cancelContactUsButton);
	}
	
	public void verifyWorkSpaceNameInLeftMenu(String name) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//button[@class='popover-trigger']/span[@title='%s']", name)), "Workspace Name is not correct!");
	}
	
	
	
	
	

	
	
	
}