package testscripts.lifecycle;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEditTaskPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_018 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LIFECYCLE_018() {
		testParameters.setCurrentTestDescription("1. Verify that user is unable to edit the life cycle name of a file through edit properties option"
				+ "<br>2. Verify that user is unable to edit the life cycle state of a file through edit properties option");
		
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();

		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.deletedocument(fileName);

		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		myFiles.createFile(fileDetails);
		docDetailsPg.backToFolderOrDocumentPage("");
		
		sitesPage.clickOnMoreSetting(fileName);
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);

		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
		String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
		sitesPage.commonMethodForPerformBrowseOption(fileName, browseOption);

		docDetailsPg.clickAllProperties();
		AlfrescoEditTaskPage editTaskPage = new AlfrescoEditTaskPage(scriptHelper);
		editTaskPage.verifyEditable(editTaskPage.lifecycleNameXpath);
		editTaskPage.verifyEditable(editTaskPage.lifecycleStateXpath);
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}