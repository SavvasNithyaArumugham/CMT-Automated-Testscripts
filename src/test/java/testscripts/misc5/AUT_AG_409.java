package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with valid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_409 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void copyToScreenCheckAdvSearch() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
				+ "folders or files in search result page");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	    AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);	
        AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
        String buttonName=dataTable.getData("Search","Query");
        String location=dataTable.getData("MyFiles","Sort Options");
        homePage.navigateToMyFilesTab();
        docLibPage.deleteAllFilesAndFolders();
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		myFiles.createFile(fileDetails);
		homePageObj.navigateToAdvSearch();
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		appSearchPg.performMoveCopyActionBtn();
		UIHelper.waitForLong(driver);
		docLibPage.isRequiredButtonNotAvailable(buttonName,location);
		
		
		
		}
		
		
	@Override
	public void tearDown() {
		// Nothing to do
	}
}
