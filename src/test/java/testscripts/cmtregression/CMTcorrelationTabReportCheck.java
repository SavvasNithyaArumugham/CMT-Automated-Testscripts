package testscripts.cmtregression;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTcorrelationTabReportCheck extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Ensure correlation  report is generated");
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
		String showListXpath =" //div[contains(@class,'correlation-report-btn')]//input[@value='SHOW LIST']";
	
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickCorrelations();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Correlations Reporting Dashboard", "Correlations Reporting Dashboard page displayed", Status.PASS);
			//Scroll to end of page so that "SHOW LIST" is visible
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			WebElement showList = driver.findElement(By.xpath(showListXpath));
			UIHelper.scrollToAnElement(showList);
			UIHelper.waitFor(driver);
			showList.click();
			System.out.println("Clicked on showlist");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String test =driver.findElement(By.xpath("//*[@id='correlationsDatatablesId']/tbody/tr[1]/td[1]")).getText();
			if (test.contains("AutoamtionTest"))
			{
			
		report.updateTestLog("Correlations Reporting ", "Correlations Report generated", Status.PASS);
								
			} 
			else
			{
		report.updateTestLog("Correlation report", "Correlation report generation not successful", Status.FAIL);
			}
				
		}
		catch (Exception e) 
		{
			report.updateTestLog("CMT Correlation Page", "In Correlation Page,the Correlation report generation encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	
	}

	@Override
	public void tearDown() {

	}
}
