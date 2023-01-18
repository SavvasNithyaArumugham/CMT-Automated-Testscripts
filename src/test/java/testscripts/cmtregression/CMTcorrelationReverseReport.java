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

public class CMTcorrelationReverseReport extends TestCase 
{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Reverse report (Product-to-standard) should be enabled to download in the showlist");
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
		String Discipline = dataTable.getData("MyFiles", "CreateFileDetails");
		String Country = dataTable.getData("MyFiles", "CreateFolder");
		String Curriculum = dataTable.getData("MyFiles", "Version");
		String Grade=dataTable.getData("MyFiles", "CreateChildFolder");
		String Discipline1 = dataTable.getData("MyFiles", "Sort Options");
		String Program = dataTable.getData("MyFiles", "AccessToken");
		String Course = dataTable.getData("MyFiles", "PopUpMsg");
		String Grade1=null;
		String Product = null;
		String reportName="//span[@class='posRelative']//input[@type='text']";
		String Save ="//div[@class='correlation-save-container posRelative']//input[@type='button']";
		String successXpath =".//*[@id='confirmationPopup'][@data-ng-show='successMessage']";
				
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickCorrelations();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Correlations Reporting Dashboard", "Correlations Reporting Dashboard page displayed", Status.PASS);
						
			managePage.leftProduct(Discipline1,Program,Course,Grade1,Product);
			managePage.rightCurriculumStandard(Discipline,Country,Curriculum,Grade);
			// Provide a report name, save and generate the report
			driver.findElement(By.xpath(reportName)).sendKeys("ReverseAutoamtionTest");
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(Save)).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
					
			// Success message Check		
			String successMessage=driver.findElement(By.xpath(successXpath)).getText();
			System.out.println(successMessage);
			if (successMessage.contains("We will notify you shortly") )
			{
			report.updateTestLog("Correlation report generation(Product-to-standard)", "Correlation report(Product-to-standard) generation was successful" + "||" +successMessage, Status.PASS);
			}
			else
			{
			report.updateTestLog("Correlation report generation(Product-to-standard) ", "Correlation report(Product-to-standard) generation was not successful", Status.FAIL);
			}
		}
		catch (Exception e) 
		{
			report.updateTestLog("CMT Correlation Page", "In Correlation Page,generating the Correlation report(Product-to-standard) encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	
	}

	@Override
	public void tearDown() {

	}
}
