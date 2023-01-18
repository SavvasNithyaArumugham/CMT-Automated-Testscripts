package testscripts.cmtregression;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtHomePage;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTselectAndUnAlign extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription("Validate the new buttons in the screen 'Select All' and 'UnAlign'");
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
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickIntermediaryAlign();
			UIHelper.waitFor(driver);
			// Check if Page contains Curriculum standard and Product Tab
			managePage.alignmentCurriculumStandard(Discipline, Country, Curriculum, Grade);
			managePage.Align();
			managePage.selectAllAndUnAlign();
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
