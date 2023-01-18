package testscripts.cmt;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTManageUploadTOCValidation extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Upload new TOC file, product tab validation ");
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
		String option = dataTable.getData("MyFiles", "FileName");
		String discipline = dataTable.getData("MyFiles", "CreateFolder");
		String program = dataTable.getData("MyFiles", "Version");
		String product = dataTable.getData("MyFiles", "CreateFileDetails");
		String course = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickManage();
			UIHelper.waitFor(driver);			
			managePage.uploadNewTOC(option, discipline, program, course, product);
			// Click on Export for Re-Ingestion
			driver.findElement(By.xpath("//div[contains(@class,'product-download-container')]//div[@data-ng-click='exportProductSheet()']")).click();
			UIHelper.waitFor(driver);
			String successMessage=driver.findElement(By.xpath(".//*[@id='confirmationPopup'][@data-ng-show='successMessage']")).getText();
			System.out.println(successMessage);
			if (successMessage.contains("We will notify you shortly") )
			{
				report.updateTestLog("Clicked on Export for Re-Ingestion ", "Clicked on Export for Re-Ingestion and mail sent message displayed", Status.PASS);
			}
			else
			{
				report.updateTestLog("Clicked on Export for Re-Ingestion ", "Clicked on Export for Re-Ingestion and mail sent message failed", Status.FAIL);
			}
			UIHelper.waitFor(driver);			 						
			} catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,the New Curriculum Draft creation encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
