package pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SubscriptionPlan extends Common{

	public SubscriptionPlan(WebDriver driver) {
		super(driver);
	}
	
	private static final String PRICING_PAGE_URL = "https://birdeatsbug.com/pricing#compare";
	
	private final String editPaymentDetailsButton = "//button[text()='Edit payment details']";
	private final String cancelPlanButton = "//button[text()='Cancel plan']";
	
	private final String upgradeButtonBusiness = "//button[contains(text(),'Upgrade')]";
	private final String getInTouchButton = "//button[contains(text(),'Get in touch')]";
	private final String currentPlanBusiness = "//article[1]//p[contains(text(),'Current Plan')]";
	private final String learnMoreLink = "//a[contains(text(),'Learn more about our plans')]";
	
	private final String paddleIframe = "//iframe[@id='pf_14958']";
	private final String paddleUpgradeModal = "//div[@data-testid='protrudingHeader-title']";
	private final String getStartedForFreeButtonPricingPage = "(//a/div[text()='Get started for free'])[2]";
	
	private final String contactUsCutomPlanModal = "//h1[contains(text(),'Subject: Custom plan')]";

	public void clickUpgradeBusinessPlan() {
		clickElement(driver, upgradeButtonBusiness);
	}
	
	public void clickGetInTouchCustomPlan() {
		clickElement(driver, getInTouchButton);
	}
	
	public void clickLearnMoreLink() {
		clickElement(driver, learnMoreLink);
	}
	
	public void verifyManagePlanInvisible() {
		Assert.assertFalse(isElementVisible(driver, editPaymentDetailsButton), "Edit Payment Details button is not invisible!");
		Assert.assertFalse(isElementVisible(driver, cancelPlanButton), "Cancel Plan button is not invisible!");
	}
	
	public void verifyCurrentPlanIsBusiness() {
		Assert.assertTrue(isElementDisplayed(driver, currentPlanBusiness), "Current plan is not business!");
	}
	
	public void verifyGetInTouchButtonInvisible() {
		Assert.assertFalse(isElementVisible(driver, getInTouchButton), "Get in touch button is not invisible!");
	}
	
	public void verifyUpgradeBusinessButtonVisible() {
		Assert.assertTrue(isElementVisible(driver, upgradeButtonBusiness), "Upgrade button of business plan is not visible!");
	}
	
	public void verifyPaddleUpgradeModalDisplay() {
		switchToIframe(driver, paddleIframe);
		waitInSeconds(2);
		Assert.assertTrue(isElementDisplayed(driver, paddleUpgradeModal), "Paddle Upgrade Modal is not visible!");
		switchToDefaultContent(driver);
	}
	
	public void verifyContactUsCustomPlanModalDisplay() {
		Assert.assertTrue(isElementDisplayed(driver, contactUsCutomPlanModal), "Contact Us Modal is not visible!");
	}
	
	public void verifyPricingPageDisplay() {
		Assert.assertEquals(getCurrentUrl(driver), PRICING_PAGE_URL);
		Assert.assertTrue(isElementDisplayed(driver, getStartedForFreeButtonPricingPage), "Get started for free button is not visible!");
	}
	
	public void goBackToSubscriptionFromPaddleUpgrade() {
		goBack(driver);
	}
	

	
	
	
}