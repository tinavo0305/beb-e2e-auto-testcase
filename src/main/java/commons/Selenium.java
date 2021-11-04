package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Selenium {
	private WebDriverWait explicitWait;
	private Select select;
	private JavascriptExecutor js;
	private WebElement element;
	private List<WebElement> elements;
	private Actions actions;

    
	/**
	 * Wait until element visible in UI, if not throw exception
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementVisible(WebDriver driver, String xpath) {
		explicitWait = new WebDriverWait(driver, 30);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (ElementNotVisibleException e) {
			System.out.println("Element is not visible!");
		}

	}
    
	/**
	 * Capture element by xpath and return WebElement object
	 * 
	 * @param driver
	 * @param xpath
	 * @return WebElement
	 */
	public WebElement findElementByXpath(WebDriver driver, String xpath) {
		try {
			return driver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found!");
			return null;
		}

	}
	
	/**
	 * Capture web elements by xpath and return list of WebElement objects
	 * 
	 * @param driver
	 * @param xpath
	 * @return List<WebElement> A list of web elements having the same xpath
	 */
	public List<WebElement> findElementsByXpath(WebDriver driver, String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	
	/**
	 * Click on a web element after waiting for it visible
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickElement(WebDriver driver, String xpath) {
		waitForElementVisible(driver, xpath);
		findElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Input value to a textbox after clearing the old value
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysToElement(WebDriver driver, String xpath, String value) {
		waitForElementVisible(driver, xpath);
		element = findElementByXpath(driver, xpath);
		element.clear();
		element.sendKeys(value);
	}
	
	public void typeValueIntoTextbox(WebDriver driver, String value) {
		actions = new Actions(driver);
		actions.sendKeys(value).perform();
		actions.sendKeys(Keys.ENTER).perform();
	}
	
	/**
	 * Select an item from drop-down using Select library
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void selectValueInDropDown(WebDriver driver, String xpath, String value) {
		select = new Select(findElementByXpath(driver, xpath));
		select.selectByVisibleText(value);
	}

	/**
	 * Get text of 1st selected item in a drop-down
	 * 
	 * @param driver
	 * @param xpath  xPath of the item in drop-down
	 * @return String Text of 1st selected item in dropdown
	 */
	public String getSelectItemInDropDown(WebDriver driver, String xpath) {
		select = new Select(findElementByXpath(driver, xpath));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Select an item from a drop-down that the Select method does not support It
	 * will click on the drop-down, wait until items visible, scroll to the item and
	 * click on the item using js click
	 * 
	 * @param driver
	 * @param parentsXpath  xPath of the drop-down
	 * @param childrenXpath xPath of the items in the drop-down
	 */
	public void selectItemFromCustomDropdown(WebDriver driver, String parentsXpath, String childrenXpath,
			String selectedItem) {
		element = findElementByXpath(driver, parentsXpath);
		js = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
		js.executeScript("arguments[0].click();", element);
		waitInSeconds(1);
		elements = findElementsByXpath(driver, childrenXpath);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childrenXpath)));
		for (WebElement childElement : elements) {
			if (childElement.getText().equals(selectedItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					waitInSeconds(1);
					js.executeScript("arguments[0].click();", element);
				}
				waitInSeconds(1);
				break;
			}
		}

	}
	
	/**
	 * Check if a web element is displayed in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementDisplayed(WebDriver driver, String xpath) {
		try {
			return findElementByXpath(driver, xpath).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed!");
			return false;
		}
	}
	
	/**
	 * Move mouse to element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void hoverMouseToElement(WebDriver driver, String xpath) {
		actions = new Actions(driver);
		actions.moveToElement(findElementByXpath(driver, xpath)).perform();
	}
	
	/**
	 * Execute javascript using Javascript Executor
	 * 
	 * @param driver
	 * @param javaScript
	 * @return Object depends on the returned value from javascript
	 */
	public Object executeJavaScript(WebDriver driver, String javaScript) {
		js = (JavascriptExecutor) driver;
		return js.executeScript(javaScript);
	}
	
	/**
	 * Click on element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickElementByJS(WebDriver driver, String xpath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", findElementByXpath(driver, xpath));
	}
	
	/**
	 * Scroll to element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void ScrollToElement(WebDriver driver, String xpath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, xpath));
	}
	
	/**
	 * Send keys to element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysByJS(WebDriver driver, String xpath, String value) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(driver, xpath));
	}
	
	/**
	 * Check if element visible by counting the size of element's xpath
	 * 
	 * @param driver
	 * @param xpath
	 * @return boolean
	 */
	public boolean isElementVisible(WebDriver driver, String xpath) {
		overrideGlobalTimeout(driver);
		int size = findElementsByXpath(driver, xpath).size();
		if (size != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Override global timeout to save waiting time when checking element invisible
	 * 
	 * @param driver
	 */
	public void overrideGlobalTimeout(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void switchTabByIndex(WebDriver driver, int index){
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}
	
	public void closeTab(WebDriver driver){
		driver.close();
	}
	
	public void goBack(WebDriver driver) {
		driver.navigate().back();
	}
	
	/**
	 * Switch to iframe using xpath
	 * 
	 * @param driver
	 * @param iframeXpath
	 */
	public void switchToIframe(WebDriver driver, String iframeXpath) {
		driver.switchTo().frame(findElementByXpath(driver, iframeXpath));
	}
	
	/**
	 * Switching back to default window from a tab or an iframe
	 * 
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * Get attribute value of a web element
	 * 
	 * @param driver
	 * @param xpath
	 * @return String
	 */
	public String getAttribute(WebDriver driver, String xpath, String attribute) {
		return findElementByXpath(driver, xpath).getAttribute(attribute);
	}
	
	/**
	 * Send specific key from keyboard to element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeyboardToElement(WebDriver driver, String xpath, Keys key) {
		actions = new Actions(driver);
		actions.sendKeys(findElementByXpath(driver, xpath), key).perform();
	}
	
	/**
	 * Select all texts of a textbox using ctrl + a and clear the text with backspace key
	 * 
	 * @param driver
	 */
	public void selectAllTextboxAndClear(WebDriver driver) {
		actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("a").build().perform();
		actions.keyUp(Keys.CONTROL).perform();
		waitInSeconds(1);
		actions.sendKeys(Keys.BACK_SPACE).perform();
	}
	
	/**
	 * Accept browser alert
	 * 
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * Dismiss the browser alert
	 * 
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * Hard wait in seconds
	 * 
	 * @param seconds
	 */
	public void waitInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	
    
    
    
    

}
