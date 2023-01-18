package testscripts.cmtregression;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class CMTcorrelationReverseReportDownload extends TestCase 
{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"User is able to download Reverse (Product-to-standard) correlation report in the showlist");
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
		String downloadXpath ="//*[@id='correlationsDatatablesId']/tbody/tr[1]/td[8]/a[1]/img";
		String fileName = dataTable.getData("MyFiles", "FileName");
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
			
		report.updateTestLog("Correlations Reporting(Product-to-standard) ", "Correlations Report generated", Status.PASS);
								
			} 
		// Click on Rerun button
			WebElement downloadButton = driver.findElement(By.xpath(downloadXpath));
			//Scroll to the download button
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, downloadButton);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(downloadXpath)).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			String downloadFilePath = properties.getProperty("DefaultDownloadPath");
			File downloadedFile = null;
			
			// CSVfile presence and filename
			downloadedFile = new File(downloadFilePath + "/" + fileName);
			String downloadFilePathName = downloadFilePath+fileName ;
			System.out.println(downloadedFile);
			System.out.println(downloadFilePathName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if (downloadedFile.exists()&& !fileName.equalsIgnoreCase("File Not Found"))
			{
				report.updateTestLog("Verify download file", "Correlation report(Product-to-standard):File: " + fileName
						+ " downloaded sucessfully", Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify download file", "Correlation report(Product-to-standard):File: " + fileName
						+ "file not downloaded ", Status.FAIL);	
			}
			
			ExcelUtil excel = new ExcelUtil();
			String filePath = properties.getProperty("DefaultDownloadPath");
			String contentTitle = ExcelUtil.readdatafromExcel(filePath,fileName);
			System.out.println(contentTitle);
			//Check product with populated Content Title values including intermediary details in the excel file
			if (contentTitle.contains("CMT Correlation Report")) 
			{
			report.updateTestLog("Verify download file", "Correlation report:Title Value " + contentTitle, Status.PASS);
				
			}
			else
			{
			report.updateTestLog("Verify download file", "Correlation report:Title Value " + contentTitle, Status.FAIL);	
			}
		}
		catch (Exception e) 
		{
			report.updateTestLog("CMT Correlation Page", "In Correlation Page,downloading the Correlation report encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	
	}

	@Override
	public void tearDown() {

	}
}
