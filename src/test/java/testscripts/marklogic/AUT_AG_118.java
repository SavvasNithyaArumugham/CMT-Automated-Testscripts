package testscripts.marklogic;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Naresh Kumar Salla
 */
public class AUT_AG_118 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_118()
	{
		testParameters.setCurrentTestDescription("Verify the option 'Add to watched content' or 'Remove from watched content' is not present for files");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String lableName = dataTable.getData("MyFiles", "TagName");
		String valueInEditPropInputBox = dataTable.getData("MyFiles",
				"EditTagName");
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();	
		myFiles.uploadFile(filePath, fileName);	
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName);
		if (sitesPage.checkMoreSettingsOption(fileName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[0]+"is displayed", Status.FAIL);
						
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[0]+"is not displayed", Status.PASS);
		}	
		
		if (sitesPage.checkMoreSettingsOption(fileName, moreSetOptions[1])) {
			report.updateTestLog("Verify '" + moreSetOptions[1]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[1]+"is not displayed", Status.FAIL);
						
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[1]
					+ "' aspect for empty folder under 'More' option",
					moreSetOptions[1]+"is not displayed", Status.PASS);
		}	
	


	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}