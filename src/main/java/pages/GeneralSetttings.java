package pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GeneralSetttings extends Common{

	public GeneralSetttings(WebDriver driver) {
		super(driver);
	}
	
	private final String birdStatusToggle = "//button[@id='label-switch']";
	private final String devToolToggle = "//button[@id='network-switch']";
	
	public void clickEditWorkspaceName(String workspaceName){
		clickElement(driver, String.format("(//span[@title='%s']/parent::button)[3]", workspaceName));
	}
	
	public void enterNewWorkspaceName(String name) {
		typeValueIntoTextbox(driver, name);
	}
	
	public void verifyWorkspaceName(String name) {
		Assert.assertTrue(isElementDisplayed(driver, String.format("//span[@title='%s']", name)), "Workspace Name is not correct!");
	}
	
	public void verifyWorkspaceNameNotEditable(String workspaceName){
		Assert.assertFalse(isElementVisible(driver, String.format("//span[@title='%s']/following-sibling::*[local-name()='svg' and @class='base-icon ml-8 flex-shrink-0']", workspaceName)), "Edit button is visible!");//incomplete
	}
	
	public void toggleBirdStatus() {
		clickElement(driver, birdStatusToggle);
	}
	
	public void verifyBirdStatusToggleOn() {
		Assert.assertTrue(getAttribute(driver, birdStatusToggle, "class").contains("enabled"), "Bird Toggle is not on, class attribute does not contains enabled!");
		Assert.assertTrue(getAttribute(driver, birdStatusToggle, "value").equalsIgnoreCase("true"), "Bird Toggle is not on, value is not correct!");
		Assert.assertTrue(getAttribute(driver, birdStatusToggle, "aria-checked").equalsIgnoreCase("true"), "Bird Toggle is not on, aria-checked is not correct!");
	}
	
	public void verifyBirdStatusToggleOff() {
		Assert.assertFalse(getAttribute(driver, birdStatusToggle, "class").contains("enabled"), "Bird Toggle is not off, class attribute is not correct!");
	}

	public void verifyDevToolToggleVisible() {
		Assert.assertTrue(isElementDisplayed(driver, devToolToggle), "Dev Tool toggle is not visible");
	}
	
	
}