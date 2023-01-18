package testscripts.cmtregression;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.ExcelUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTcorrelationReverseReportDelete extends TestCase 
{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"User is able to delete Reverse correlation report in the showlist");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		CmtLoginPage signOnPage = new CmtLoginPage(scriptHelper);
		CmtManagePage managePage = new CmtManagePage(scriptHelper);
		String showListXpath ="//input[@data-ng-show='!showListFlag'][@value='SHOW LIST']";
		String deleteXpath ="//*[@id='correlationsDatatablesId']/tbody/tr[1]/td[9]/span[1]";
		String clickOk = "//input[@class='cmButton cmButtonFull-green']";
		String successXpath =".//*[@id='confirmationPopup'][@data-ng-show='successMessage']";
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickCorrelations();
			UIHelper.waitFor(driver);
		
			report.updateTestLog("Correlations Reporting Dashboard", "Correlations Reporting Dashboard page displayed", Status.PASS);
			//Scroll to the SHOW LIST button
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath(showListXpath)).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
						
			String test =driver.findElement(By.xpath("//*[@id='correlationsDatatablesId']/tbody/tr[1]/td[1]")).getText();
			if (test.contains("ReverseAutoamtionTest"))
			{
			
		report.updateTestLog("Correlations Reporting ", "Correlations Report generated", Status.PASS);
								
			} 
		// Click on Rerun button
			WebElement downloadButton = driver.findElement(By.xpath(deleteXpath));
			//Scroll to the download button
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, downloadButton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(deleteXpath)).click();
			UIHelper.waitFor(driver);
						
			Actions action = new Actions(driver);
		    WebElement delete = driver.findElement(By.xpath(clickOk));
			action.moveToElement(delete).click().build().perform();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String successMessage=driver.findElement(By.xpath(successXpath)).getText();
			System.out.println(successMessage);
			if (successMessage.contains("Report has been successfully deleted") )
			{
			report.updateTestLog("Reverse Correlation report generation delete", "Correlation report delete was successful" + "||" +successMessage, Status.PASS);
			}
			else
			{
			report.updateTestLog("Reverse Correlation report generation delete ", "Correlation report delete was not successful", Status.FAIL);
			}
			
		}
		catch (Exception e) 
		{
			report.updateTestLog("CMT Correlation Page", "In Correlation Page,deleting the Correlation report encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	
	}

	@Override
	public void tearDown() {

	}
}
