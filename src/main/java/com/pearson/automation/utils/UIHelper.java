package com.pearson.automation.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.pearson.framework.Status;

/**
 * UIHelper - This Class contains common methods for performing actions on
 * browser .
 * 
 * @author Automation Team
 * 
 */

public class UIHelper {
	/**
	 * . MINPOLLINGTIME
	 */
	public static final int MINPOLLINGTIME = 100;
	/**
	 * . MINPOLLINGTIMEX2
	 */
	public static final int MINPOLLINGTIMEX2 = 300;
	/**
	 * . POLLINGEVERYSECTIME
	 */
	public static final int POLLINGEVERYSECTIME = 3;
	/**
	 * . MAXPOLLINGTIME
	 */
	public static final int MAXPOLLINGTIME = 180;
	/**
	 * . MAXPOLLINGTIMELARGE
	 */
	public static final int MAXPOLLINGTIMELARGE = 9000000;
	/**
	 * . HIGHLIGHTERINT
	 */
	public static final int HIGHLIGHTERINT = 5;
	/**
	 * . HIGHLIGHTERINTMAX
	 */
	public static final int HIGHLIGHTERINTMAX = 10;
	/**
	 * . WAITTIMEOUT
	 */
	public static final int WAITTIMEOUT = 2000;
	/**
	 * . TOTALATTEMPT
	 */
	public static final int TOTALATTEMPT = 100000;

	public static final int driverWaitTime = 3000;
	public static final int driverWaitTimeForLong = 45000;

	public final long DEFAULT_WAIT_4_PAGE = 10;

	private static boolean isEqualTwolists;

	/*
	 * Comments by - Duvvuru Naveen Function - Wait for Page to Load
	 */

	/**
	 * .
	 * 
	 * @param driver
	 * 
	 */
	public static void waitForPageToLoad(final WebDriver driver) {
		try {
			(new WebDriverWait(driver, MINPOLLINGTIME)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver d) {
					return (((org.openqa.selenium.JavascriptExecutor) driver)
							.executeScript("return document.readyState").equals("complete"));
				}
			});
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Select from Dropdown Listbox
	 * multiple times on a condition Purpose - Select multiple values from
	 * Dropdown Listbox list box
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param element
	 * 
	 * @param someText
	 * 
	 * @param clickThisElement
	 * 
	 */
	public static void selectMultipleOnCondition(final WebDriver driver, final WebElement element,
			final ArrayList<String> someText, final WebElement clickThisElement) {

		ArrayList<String> someTextList = new ArrayList<String>();

		try {

			Select clickThis = new Select(element);

			List<WebElement> selectOptions = clickThis.getOptions();

			for (WebElement temp : selectOptions) {

				for (String some : someText) {

					if (temp.getText().equalsIgnoreCase(some.toString().trim())) {

						someTextList.add(temp.getText().toString().trim());

					}

				}
			}

			for (String some : someTextList) {

				if (some.length() > 0) {

					clickThis.selectByVisibleText(some);

				}
				highlightElement(driver, clickThisElement);
				clickThisElement.click();
			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Select from Dropdown Listbox
	 * multiple times Purpose - Select multiple values from Dropdown Listbox
	 * list box
	 */

	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param element
	 * 
	 * @param someText
	 * 
	 * @param clickThisElement
	 * 
	 */

	public static void selectMultipleFromDropdownList(final WebDriver driver, final WebElement element,
			final ArrayList<String> someText, final WebElement clickThisElement) {

		try {

			Select clickThis = new Select(element);
			clickThis.deselectAll();
			for (String some : someText) {
				if (some.length() > 0) {

					clickThis.selectByVisibleText(some);

				}
				highlightElement(driver, clickThisElement);
				//clickThisElement.click();

			}
		} catch (Exception e) {

		}

	}

	/**
	 * .
	 * 
	 * @param myELT
	 * 
	 * @param getAllOptions
	 * 
	 */

	public static void getAllAvailableOptions(final WebElement myELT, final ArrayList<String> getAllOptions) {

		try {
			Select selectBox = new Select(myELT);

			List<WebElement> selectOptions = selectBox.getOptions();

			for (WebElement temp : selectOptions) {

				getAllOptions.add(temp.getText());

			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Add Elements Purpose - Add
	 * Element's getText to ArrayList for Iteration
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 * 
	 * @param someStringSet
	 * 
	 */
	public static void findandAddElementsToaList(final WebDriver driver, final String xpath,
			final ArrayList<String> someStringSet) {

		try {

			List<WebElement> getArrayMembers = driver.findElements(By.xpath(xpath));

			for (WebElement listData : getArrayMembers) {
				someStringSet.add(listData.getText());
			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Scroll Purpose - Advanced User
	 * Interactions, SCroll to an element
	 */

	/**
	 * .
	 * 
	 * @param element
	 * 
	 */
	public static void scrollToAnElement(final WebElement element) {

		try {

			Coordinates coordinate = ((Locatable) element).getCoordinates();
			coordinate.onPage();
			coordinate.inViewPort();
		} catch (Exception e) {

		}
	}

	public static void javascriptScrollToAnElement( final WebDriver driver, WebElement element) {

		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Comments by - Duvvuru Naveen Function - Highlighter Purpose - Used for
	 * highlighting an element in UI Can be useful and gives a lot of clarity to
	 * the Users
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param element
	 * 
	 */
	public static void highlightElement(final WebDriver driver, final WebElement element) {

		try {

			for (int i = 0; i < HIGHLIGHTERINT; i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: #800000; border: 2px solid blue;");
			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Compare Values and return True or
	 * False
	 */

	/**
	 * .
	 * 
	 * @param element
	 * 
	 * @param expectedVal
	 * 
	 * @return
	 */
	public static boolean compareListValue(final WebElement element, final String expectedVal) {
		try {
			boolean flagSection = false;
			Select optionSection = new Select(element);
			List<WebElement> viewSection = optionSection.getOptions();
			for (WebElement ele : viewSection) {
				if (ele.getText().equals(expectedVal)) {
					new Select(element).selectByVisibleText(expectedVal);
					Select section = new Select(element);
					if (section.getFirstSelectedOption().getText().equalsIgnoreCase(expectedVal)) {
						flagSection = true;
						break;
					}
				}
			}
			return flagSection;
		} catch (Exception e) {

			// e.printStackTrace();
			return false;
		}
	}

	public static void waitForAnElement(final WebDriver driver, final WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, MAXPOLLINGTIME);
			wait.until(ExpectedConditions.visibilityOfElementLocated((By) ele));
		} catch (Exception e) {

		}
	}

	public static boolean checkForAnWebElement(final WebElement myElement) {
		try {
			return myElement.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Handle Alerts Purpose - click OK
	 * in Alerts
	 */

	/**
	 * .
	 * 
	 * @param driver
	 * 
	 */
	public static void processalert(final WebDriver driver) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, HIGHLIGHTERINT);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Process Alert for Import
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @return
	 */
	public static boolean isDisplayedAlertMessage(final WebDriver driver) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, HIGHLIGHTERINTMAX);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			driver.switchTo().defaultContent();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Wait
	 */

	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param ajaxElementxpath
	 * 
	 */
	public static void waitForInvisibilityOfAjaxImgByXpath(final WebDriver driver, final String ajaxElementxpath) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, MINPOLLINGTIMEX2);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ajaxElementxpath)));
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Naveen Duvvuru
	 * 
	 * Function - Fluent Wait method for wait for visibility of elements
	 */
	public static void waitForVisibilityOfEleByXpath(final WebDriver driver, String elementXpath) {

		FluentWait<By> fluentWait = new FluentWait<By>(By.xpath(elementXpath));
		fluentWait.pollingEvery(10, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(15, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(final By by) {
				try {

					return driver.findElement(by).isDisplayed();
				} catch (Exception ex) {

					return false;
				}
			}
		});
	}

	/*
	 * Comments by - Naveen Duvvuru
	 * 
	 * Function - Fluent Wait method for wait for visibility of elements
	 */
	public static void waitForVisibilityOfEleByXpathForUpload(final WebDriver driver, String elementXpath) {

		FluentWait<By> fluentWait = new FluentWait<By>(By.xpath(elementXpath));
		fluentWait.pollingEvery(10, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(30, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(final By by) {
				try {

					return driver.findElement(by).isDisplayed();
				} catch (Exception ex) {

					return false;
				}
			}
		});
	}

	/*
	 * Comments by - Naveen Duvvuru
	 * 
	 * Function - Fluent Wait method for wait for visibility of elements
	 */
	public static void waitForVisibilityOfEleByXpathForLongTime(final WebDriver driver, String elementXpath) {

		FluentWait<By> fluentWait = new FluentWait<By>(By.xpath(elementXpath));
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(90, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(final By by) {
				try {

					return driver.findElement(by).isDisplayed();
				} catch (Exception ex) {

					return false;
				}
			}
		});
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Hover an Element Purpose - Can be
	 * used to hover and highlight the presence of an element
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param element1
	 * 
	 */
	public static void mouseOveranElement(final WebDriver driver, final WebElement element1) {

		try {

			Actions action = new Actions(driver);
			UIHelper.highlightElement(driver, element1);

			action.moveToElement(element1).build().perform();

		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Hover and Click Purpose - Hover
	 * an Element and Click on an Element using Action Class
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param element1
	 * 
	 */
	public static void mouseOverandclickanElement(final WebDriver driver, final WebElement element1) {

		try {
			if (element1.isDisplayed()) {
				Actions action = new Actions(driver);
				action.moveToElement(element1).click().build().perform();
			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Duvvuru Naveen Function - Double Click Purpose - Can be
	 * used to hover and double click an element
	 */

	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param element1
	 * 
	 */
	public static void mouseOverandElementdoubleClick(final WebDriver driver, final WebElement element1) {

		try {

			Actions action = new Actions(driver);
			UIHelper.highlightElement(driver, element1);

			action.moveToElement(element1).doubleClick().build().perform();

		} catch (Exception e) {

		}
	}

	// Common method for click a web element
	public static void click(final WebDriver driver, final String eleXpath) {
		try {
			driver.findElement(By.xpath(eleXpath)).click();

		} catch (Exception e) {

		}
	}

	public static void clickanElement(WebElement myElement) {

		try {

			if (myElement.isDisplayed()) {
				myElement.click();
			}

		} catch (Exception E) {
		}
	}

	// Common method for type the value int web element fields
	public static void sendKeysToAnElementByXpath(final WebDriver driver, final String eleXpath,
			final String fieldValue) {
		try {
			driver.findElement(By.xpath(eleXpath)).clear();
			driver.findElement(By.xpath(eleXpath)).sendKeys(fieldValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Common method for type the value int web element fields
	public static void sendKeysToAnWebElement(final WebDriver driver, final WebElement element, final String text) {
		try {
			element.clear();
			element.sendKeys(text);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Common method for get text from web element
	public static String getTextFromWebElement(final WebDriver driver, final String eleXpath) {
		String webElementText = "";
		try {
			webElementText = driver.findElement(By.xpath(eleXpath)).getText();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return webElementText;
	}

	// Common method for get webelement
	public static WebElement findAnElementbyXpath(final WebDriver driver, final String eleXpath) {
		return driver.findElement(By.xpath(eleXpath));
	}

	// Select value from dropdown
	public static void selectValueFromDropdown(final WebDriver driver, final String eleXpath,
			final String dropdownValue) {
		try {
			Select dropdown = new Select(driver.findElement(By.xpath(eleXpath)));
			dropdown.selectByValue(dropdownValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitFor(final WebDriver driver) {

		try {
			Thread.sleep(driverWaitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitForLong(final WebDriver driver) {

		try {
			Thread.sleep(driverWaitTimeForLong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void keepPollingforAnElement(String someXpath, final WebDriver driver) {

		FluentWait<By> fluentWait = new FluentWait<By>(By.xpath(someXpath));
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
		fluentWait.withTimeout(300, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {

					return driver.findElement(by).isDisplayed();
				} catch (Exception ex) {
					return false;
				}
			}
		});
	}

	public static void addWebElementsToaList(List<WebElement> toGetData, WebElement... data) {

		try {

			for (WebElement datainWebPage : data) {

				toGetData.add(datainWebPage);

			}

		} catch (NoSuchElementException | NullPointerException e) {

		}

	}

	public static List<WebElement> findListOfElementsbyXpath(String myElement, WebDriver driver) {

		return driver.findElements(By.xpath(myElement));
	}

	public static boolean chkForThisElementList(List<WebElement> myElements) {

		for (WebElement myElement : myElements) {
			try {

				return myElement.isDisplayed();

			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public static boolean isWebElementDisplayed(WebElement element) {
		boolean status = false;
		try {
			status = element.isDisplayed();

		} catch (Exception e) {
			status = false;
		}
		return status;

	}

	public boolean waitForJavaScriptCondition(WebDriver driver, final String javaScript, int timeOutInSeconds) {
		boolean jscondition = false;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
																			// implicitlyWait()
			new WebDriverWait(driver, timeOutInSeconds) {
			}.until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return (Boolean) ((JavascriptExecutor) driver).executeScript(javaScript);
				}
			});
			jscondition = (Boolean) ((JavascriptExecutor) driver).executeScript(javaScript);
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); // reset
																								// implicitlyWait
			return jscondition;
		} catch (Exception e) {

		}
		return false;
	}

	public static void findAnElementlistDriver(WebDriver driver, List<WebElement> myElement, String Xpath,
			List<String> allText) {

		try {
			myElement = driver.findElements(By.xpath(Xpath));

			for (WebElement findText : myElement) {
				allText.add(findText.getText());

			}
		} catch (NoSuchElementException E) {

		} catch (NullPointerException E) {

		}

	}

	public static void selectDropdownList(WebDriver driver, WebElement element, String someText) {

		try {

			UIHelper.highlightElement(driver, element);

			Select clickThis = new Select(element);

			clickThis.selectByVisibleText(someText.toString().trim());

		} catch (Exception E) {

		}

	}

	public static void deselectAll(WebDriver driver, String someXpath) {

		Select selectBox = new Select(driver.findElement(By.xpath(someXpath)));

		selectBox.deselectAll();

	}

	public static void deselectByIndex(WebDriver driver, String someXpath, int indexID) {

		Select selectBox = new Select(driver.findElement(By.xpath(someXpath)));

		selectBox.deselectByIndex(indexID);

	}

	public static void deselectByIndex(WebDriver driver, String someXpath, String EltName) {

		Select selectBox = new Select(driver.findElement(By.xpath(someXpath)));

		selectBox.deselectByValue(EltName);

	}

	public static void selectStringValue(WebDriver driver, String someXpath, String EltName) {

		Select selectBox = new Select(driver.findElement(By.xpath(someXpath)));

		selectBox.selectByValue(EltName);

	}

	public static void selectbyVisibleText(WebDriver driver, String someXpath, String EltName) {

		Select selectBox = new Select(driver.findElement(By.xpath(someXpath)));

		selectBox.selectByVisibleText(EltName);

	}

	public static void selectIntergerValue(WebDriver driver, String someXpath, int indexID) {

		Select selectBox = new Select(driver.findElement(By.xpath(someXpath)));

		selectBox.selectByIndex(indexID);

	}

	public static void getAllSelectedOptions(WebDriver driver, String fromThisXpath) {
		Select selectBox = new Select(driver.findElement(By.xpath(fromThisXpath)));

		List<WebElement> selectOptions = selectBox.getAllSelectedOptions();

		for (WebElement temp : selectOptions)

		{
			System.out.println("Printing the selected values in the Selectbox" + temp.getText());

		}

	}

	public static void getAllSelectedOptions(WebDriver driver, String fromThisXpath,
			ArrayList<String> selectedOptList) {
		Select selectBox = new Select(driver.findElement(By.xpath(fromThisXpath)));
		try {
			List<WebElement> selectOptions = selectBox.getAllSelectedOptions();

			for (WebElement temp : selectOptions) {
				selectedOptList.add(temp.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getfirstSelectedOptions(WebDriver driver, String fromThisXpath,
			ArrayList<String> getfirstSelectedOptions) {
		Select selectBox = new Select(driver.findElement(By.xpath(fromThisXpath)));

		WebElement selectOptions = selectBox.getFirstSelectedOption();

		getfirstSelectedOptions.add(selectOptions.getText());
	}

	public static void isSelectBoxMultiple(WebDriver driver, String fromThisXpath) {

		Select selectBox = new Select(driver.findElement(By.xpath(fromThisXpath)));
		System.out.println(selectBox.isMultiple()); // Prints True or False

	}

	public static void draggable(WebDriver driver, String thisXpath, int i, int j)

	{

		// "http://jqueryui.com/demos/draggable/" // For Example
		try {
			WebElement draggable = driver.findElement(By.xpath(thisXpath));

			new Actions(driver).dragAndDropBy(draggable, i, j).build().perform();
		} catch (NoSuchElementException E) {

		} catch (NullPointerException E) {

		}

	}

	public static void dragAndDrop(WebDriver driver, String fromthisXpath, String toThisXpath) {

		try {
			WebElement draggable = UIHelper.findAnElementbyXpath(driver, fromthisXpath);
			UIHelper.highlightElement(driver, draggable);

			WebElement droppable = UIHelper.findAnElementbyXpath(driver, toThisXpath);
			UIHelper.highlightElement(driver, droppable);
			UIHelper.waitFor(driver);
			new Actions(driver).dragAndDrop(draggable, droppable).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitforAllElementsinNextpage(WebDriver driver, String someElement) {

		try {

			Wait<WebDriver> wait = new WebDriverWait(driver, 100000);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(someElement)));

		} catch (Exception E) {

		}

	}

	public static void switchToFrame(WebDriver driver, String frameXpath) {

		try {

			driver.switchTo().frame(driver.findElement(By.xpath(frameXpath)));

		} catch (NoSuchElementException E) {

		}

		catch (NullPointerException E) {

		}

	}

	public static boolean chkForThisStringinUI(WebDriver driver, String... getText) {

		boolean boolEval = false;
		try {

			for (String g : getText) {

				if (driver.getPageSource().contains(g)) {
					boolEval = true;
					break;

				}
			}

			return boolEval;

		} catch (Exception e) {

			return false;
		}

	}

	public static void switchToDefContent(WebDriver driver) {

		try {

			driver.switchTo().defaultContent();

		} catch (NoSuchElementException E) {

		}

		catch (NullPointerException E) {

		}

	}

	public static void findanElementaddToArrayList(WebDriver driver, WebElement element, String Xpath,
			ArrayList<String> elementsinArray) {
		try {

			element = driver.findElement(By.xpath(Xpath));

			elementsinArray.add(element.getText());

		} catch (NoSuchElementException E) {

		}

		catch (NullPointerException E) {

		}

	}

	public static void findanElementListaddToArrayList(WebDriver driver, List<WebElement> element, String Xpath,
			ArrayList<String> elementsinArray) {
		try {

			element = driver.findElements(By.xpath(Xpath));

			for (WebElement myElement : element) {

				if (myElement.getText().toString().trim().contains(",")) {

					myElement.getText().toString().trim().replace(",", "").toString().trim();
				}

				elementsinArray.add(myElement.getText().toString().trim());
			}

		} catch (NoSuchElementException E) {

		}

		catch (NullPointerException E) {

		}

	}

	public static void findAndAddElementsToaList(WebDriver driver, String Xpath, ArrayList<String> someStringSet) {

		try {

			List<WebElement> getArrayMembers = driver.findElements(By.xpath(Xpath));

			for (WebElement listData : getArrayMembers) {
				someStringSet.add(listData.getText());
			}

		} catch (NoSuchElementException E) {

		}

		catch (NullPointerException E) {

		}

	}
	
	
	public static void findAndAddElementsAttributeValueToaList(WebDriver driver, String Xpath, ArrayList<String> someStringSet, String attributeValue) {

		try {

			List<WebElement> getArrayMembers = driver.findElements(By.xpath(Xpath));

			for (WebElement listData : getArrayMembers) {
				someStringSet.add(listData.getAttribute(attributeValue));
			}

		} catch (NoSuchElementException E) {

		}

		catch (NullPointerException E) {

		}

	}


	public static void waitforthisElement(WebDriver driver, WebElement someElement) {

		try {

			Wait<WebDriver> wait = new WebDriverWait(driver, 900000);
			wait.until(ExpectedConditions.visibilityOf(someElement));

		} catch (Exception E) {

		}

	}

	public static void waitforPresenceOfAllElements(WebDriver driver, String someElement) {

		try {

			Wait<WebDriver> wait = new WebDriverWait(driver, 100000);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(someElement)));

		} catch (NoSuchElementException | NullPointerException E) {

		}

	}

	public static void uncheckanElement(WebElement myElement) {

		try {

			if (myElement.isSelected() == true) {
				myElement.click();
			}

		} catch (Exception E) {
		}
	}

	public static boolean isWebElementEnabled(WebElement element) {

		try {
			return element.isEnabled();

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkDropdownListValuesWithExpectedValue(WebElement element, String expectedVal) {
		try {
			boolean flagSection = false;
			Select optionSection = new Select(element);
			List<WebElement> viewSection = optionSection.getOptions();
			for (WebElement ele : viewSection) {
				if (ele.getText().equals(expectedVal)) {
					new Select(element).selectByVisibleText(expectedVal);
					Select section = new Select(element);
					if (section.getFirstSelectedOption().getText().equalsIgnoreCase(expectedVal)) {
						flagSection = true;
						break;
					} else {
					}
				} else {

				}
			}
			return flagSection;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	public static void highlightElement(WebDriver driver, String xPath) {

		try {
			WebElement element = driver.findElement(By.xpath(xPath));

			for (int i = 0; i < HIGHLIGHTERINT; i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: #800000; border: 2px solid blue;");
			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Lokesh Santhanam Function - Add Elements Purpose - Add
	 * Element's getText to ArrayList for Iteration and check for WebElement is
	 * available
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 * 
	 * @param someStringSet
	 * 
	 * @param listxpath
	 */
	public static void findandAddElementsToaListforMap(final WebDriver driver, final ArrayList<String> name,
			final String xpath, final ArrayList<String> someStringSet) {

		try {

			/*
			 * List<WebElement> getArrayMembers = driver.findElements(By
			 * .xpath(listxpath));
			 */

			for (String listData : name) {

				String finalxpath = xpath.replace("CRAFT", listData);

				if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, finalxpath)) == true) {
					someStringSet.add(UIHelper.findAnElementbyXpath(driver, finalxpath).getText());

				} else {
					someStringSet.add(null);
				}
			}
		} catch (Exception e) {
			someStringSet.add(null);

		}
	}

	/*
	 * Comments by - Lokesh Santhanam Function - Add Elements Purpose - Add
	 * Element's getText to ArrayList for Iteration and check for WebElement is
	 * available
	 */
	/**
	 * .
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 * 
	 * @param someStringSet
	 * 
	 * @param listxpath
	 */
	public static void findandAddElementsToaListforBulk(final WebDriver driver, final String listxpath,
			final ArrayList<String> someStringSet) {

		try {

			List<WebElement> getArrayMembers = driver.findElements(By.xpath(listxpath));

			for (WebElement listData : getArrayMembers) {

				// String finalxpath =
				// xpath.replace("CRAFT",listData.getText());

				if (UIHelper.checkForAnWebElement(listData) == true) {
					someStringSet.add(listData.getText());

				} else {
					someStringSet.add(null);
				}
			}
		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Lokesh Santhanam Function - Literate list - assert value -
	 * Click on actual value URL
	 */
	/**
	 * 
	 * @param driver
	 * @param someStringSet
	 * @param expectedVal
	 * @param xpath
	 */
	public static void iterateListandhighlightele(WebDriver driver, final ArrayList<String> someStringSet,
			final String expectedVal, final String xpath) {
		try {

			for (String actualValue : someStringSet) {
				if (actualValue.equalsIgnoreCase(expectedVal)) {
					UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, xpath));
				}
			}

		} catch (Exception e) {

		}
	}

	// Compare Two Lists
	public static boolean compareTwoSimilarLists(ArrayList<String> list1, ArrayList<String> list2) {
		Set<String> hs1 = new HashSet<>();
		hs1.addAll(list1);
		list1.clear();
		list1.addAll(hs1);

		Set<String> hs2 = new HashSet<>();
		hs2.addAll(list2);
		list2.clear();
		list2.addAll(hs2);

		Collections.sort(list1);
		Collections.sort(list2);

		if (list1 != null && list2 != null && list1.size() == list2.size()) {
			for (String val1 : list1) {
				for (String val2 : list2) {
					if (val2.trim().equalsIgnoreCase(val1.trim()) || val2.trim().contains(val1.trim())
							|| val1.trim().contains(val2.trim())) {
						isEqualTwolists = true;
						break;
					} else {
						isEqualTwolists = false;
					}
				}
			}
		} else {
			isEqualTwolists = false;
		}
		return isEqualTwolists;
	}

	// Compare Two differnt size of Lists
	public static boolean compareTwoDiffSizeOfLists(ArrayList<String> list1, ArrayList<String> list2) {
		Set<String> hs1 = new HashSet<>();
		hs1.addAll(list1);
		list1.clear();
		list1.addAll(hs1);

		Set<String> hs2 = new HashSet<>();
		hs2.addAll(list2);
		list2.clear();
		list2.addAll(hs2);

		Collections.sort(list1);
		Collections.sort(list2);

		if (list1 != null && list2 != null) {
			for (String val1 : list1) {
				for (String val2 : list2) {
					if (val2.trim().equalsIgnoreCase(val1.trim()) || val2.trim().contains(val1.trim())
							|| val1.trim().contains(val2.trim())) {
						isEqualTwolists = true;
						break;
					} else {
						isEqualTwolists = false;
					}
				}
			}
		} else {
			isEqualTwolists = false;
		}
		return isEqualTwolists;
	}

	public static boolean checkForAnElementbyXpath(final WebDriver driver, final String expectedVal) {
		try {

			return UIHelper.findAnElementbyXpath(driver, expectedVal).isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	/*
	 * Comments by - Lokesh Santhanam Function - Literate list - assert value -
	 * Click on actual value URL
	 */
	/**
	 * 
	 * @param driver
	 * @param someStringSet
	 * @param expectedVal
	 * @param xpath
	 */
	public static void literateListandhighlightele(WebDriver driver, final ArrayList<String> someStringSet,
			final String expectedVal, final String xpath) {
		try {

			for (String actualValue : someStringSet) {
				if (actualValue.equalsIgnoreCase(expectedVal)) {
					UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, xpath));
				}
			}

		} catch (Exception e) {

		}
	}

	/*
	 * Comments by - Lokesh Santhanam Function - To check Arraylist is sorted -
	 * Click on actual value URL
	 */
	public static boolean isSortedList(ArrayList<String> list) {
		if (list == null || list.isEmpty())
			return false;

		if (list.size() == 1)
			return true;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0)
				return false;
		}

		return true;
	}

	/*
	 * Comments by - Lokesh Santhanam Function - To check list is sorted - Click
	 * on actual value URL
	 */
	public static boolean isSortedList(List<String> list) {
		if (list == null || list.isEmpty())
			return false;

		if (list.size() == 1)
			return true;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).trim().compareTo(list.get(i).trim()) > 0)
				return false;
		}

		return true;
	}

	// Wait for Element
	public static void waitForVisibilityOfElementLocated(final WebDriver driver, String elementXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pageRefresh(final WebDriver driver) {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void javascriptClick(final WebDriver driver, WebElement element) {
		try {

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 /*
	 * Comments by - Manoj,  Function - To check Element is Selected
	 */

	public static Boolean isSelected(WebElement el) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try
		{
		if(el.isSelected())
		   flag=true;
		}
		catch (Exception e) {
		return false;
		}
		return flag;
	}

	// Vijay - Code added to verify all Checkboxes are selected

	public static boolean verifycheckboxselected(List<WebElement> Listdata) {
		boolean flag = true;

		for (WebElement temp : Listdata) {
			try {
				if (!temp.isSelected()) {
					flag = false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return flag;
	}

	public static void selectallcheckbox(List<WebElement> Listdata) {
		for (WebElement temp : Listdata) {

			try {
				UIHelper.clickanElement(temp);
			} catch (Exception e) {

			}
		}

	}

	public static boolean checkReferenceID(List<WebElement> Listdata1, List<WebElement> Listdata2, String S1) {

		Iterator<WebElement> iter = Listdata1.iterator();
		Iterator<WebElement> iter2 = Listdata2.iterator();

		while (iter.hasNext()) {
			WebElement Title = iter.next();
			WebElement ReferenceID = iter2.next();

			if (Title.getText().equals(S1)) {
				if ((ReferenceID.getText().equals(S1))) {
					System.out.println("Title and Reference ID matches");
					return true;
				}
			}

		}
		return false;
	}

	// Common method for get attribute from web element
	public static String getAttributeFromWebElement(final WebDriver driver, final String eleXpath,
			final String attribute) {
		String webElementText = "";
		try {
			webElementText = driver.findElement(By.xpath(eleXpath)).getAttribute(attribute);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return webElementText;
	}
	    /*
		 * Comments by - Manoj,  Function - To switchTab 
		 */ 
	public static void switchtab(int tab, final WebDriver driver) {
		try {

			waitFor(driver);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tab));
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 /*
	 * Comments by - Manoj,  Function - Clear the Input Field
	 */ 
	 
	public static void clearInputField(final WebDriver driver, final String eleXpath) {
		try {
			driver.findElement(By.xpath(eleXpath)).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   /*
	    * Comments by - Manoj, 
		*close the current Window
		*/
		public static void closeCurrentWindow(final WebDriver driver)
		{
			try {
			driver.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
		}
	}
		/*
		 * Comments by - Manoj
		 * Function - Double Click  and Send Values Purpose - Can be
		 used to hover and double click an element and pass values
		 */

		/**
		 * .
		 * 
		 * @param driver
		 * 
		 * @param element1
		 * 
		 * @param value
		 */
		public static void mouseOverandElementdoubleClickSendKeys(final WebDriver driver, final WebElement element1,String value) {

			try {

				Actions action = new Actions(driver);
				UIHelper.highlightElement(driver, element1);
				action.moveToElement(element1).doubleClick().sendKeys(value,Keys.ENTER).build().perform();
				

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		public static void clearList(ArrayList<String> list) {
			try
			{
			if (list != null && list.size()>0)
				list.clear();
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
            }

}
