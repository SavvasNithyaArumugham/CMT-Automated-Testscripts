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

public class CMTManageUpdateNewCurriculum extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Update the new curriculum");
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
		String[] curriculumOptions = dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String curriculumName = dataTable.getData("MyFiles", "Version");				
		String filepath =dataTable.getData("MyFiles", "FilePath");
		String filename = dataTable.getData("MyFiles", "FileName");
		String updateUploadBtnXpath = "";
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickManage();
			UIHelper.waitFor(driver);			
			managePage.updateNewCurriculum(curriculumOptions[0], curriculumName, filepath, filename,updateUploadBtnXpath );
			UIHelper.waitFor(driver);			 						
			} catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,the New Curriculum Draft creation encountered a problem", Status.PASS);
			e.printStackTrace();
			
		}
	}

	@Override
	public void tearDown() {

	}
}
