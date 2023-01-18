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

public class CMTcorrelationReverseReportRerun extends TestCase 
{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Ensure the rerun button is enabled in the Correlation report(Product-to-standard)");
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
		String successXpath =".//*[@id='confirmationPopup'][@data-ng-show='successMessage']";
		String showListXpath ="//input[@data-ng-show='!showListFlag'][@value='SHOW LIST']";
		String rerunXpath ="//*[@id='correlationsDatatablesId']/tbody/tr[1]/td[9]/span[2]";
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickCorrelations();
			UIHelper.waitFor(driver);
		
			report.updateTestLog("Correlations Reporting Dashboard", "Correlations Reporting Dashboard page displayed", Status.PASS);
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
			WebElement reRunButton = driver.findElement(By.xpath(rerunXpath));
		//Scroll to the rerun button
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
			UIHelper.waitFor(driver);
			
			UIHelper.highlightElement(driver, reRunButton);
			UIHelper.waitFor(driver);
			
			driver.findElement(By.xpath(rerunXpath)).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String successMessage=driver.findElement(By.xpath(successXpath)).getText();
			System.out.println(successMessage);
			if (successMessage.contains("We will notify you shortly") )
			{
			report.updateTestLog("Correlation report generation(Product-to-standard)-Rerun", "Correlation report generation-Rerun was successful" + "||" +successMessage, Status.PASS);
			}
			else
			{
			report.updateTestLog("Correlation report generation(Product-to-standard)-Rerun", "Correlation report generation-Rerun was not successful", Status.FAIL);
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
