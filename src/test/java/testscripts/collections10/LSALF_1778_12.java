package testscripts.collections10;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1778_12 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_1766_1() {
		testParameters.setCurrentTestDescription(
			"Verify Level incrementing json file is available in the Repository> Data Dictionary>Controlled_List_Constraints"
				);
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
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		try {
		//Click Repository-->Data Dictionary-->Controlled_List_Constraints-->cpnals_levelIncrementingList.json
		
		UIHelper.click(driver, "//*[@id=\"HEADER_REPOSITORY_text\"]/a");
		UIHelper.waitFor(driver);
		UIHelper.waitForLong(driver);
		myFiles.openCreatedFolder("Data Dictionary");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Controlled_List_Constraints");
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting("cpnals_levelIncrementingList.json");
		collectionPg.commonMethodForClickOnMoreSettingsOption("cpnals_levelIncrementingList.json", "Download");
		UIHelper.waitFor(driver);
		report.updateTestLog("Verify Level incrementing json file is available in the Repository> Data Dictionary>Controlled_List_Constraints", "File:cpnals_levelIncrementingList.json " + "is displayed", Status.PASS);
		}catch(Exception e) {
			report.updateTestLog("Verify Level incrementing json file is available in the Repository> Data Dictionary>Controlled_List_Constraints", "File:cpnals_levelIncrementingList.json " + " failed to display", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
