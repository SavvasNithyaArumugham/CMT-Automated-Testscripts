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
public class AUT_AG_116 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_116()
	{
		testParameters.setCurrentTestDescription("Verify if we remove watched content aspect on folder,then the watched content aspect for all its files and subfolders within that folder will get removed automatically");
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
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.createFolder(folderDetails);
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();	
		sitesPage.clickOnMoreSetting(folderName);
		if (sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])) {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
			sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
					moreSetOptions[0]);
			UIHelper.waitFor(driver);			
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[0]
					+ "' aspect for empty folder under 'More' option",
					"Not able to Verify", Status.FAIL);
		}	
		
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName,
				moreSetOptions[2]);
		UIHelper.waitFor(driver);	
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);		
		if (docLibPage
				.isCategoryTagAvailable(folderName, "Watched Content")) {
			report.updateTestLog("Verify '" + moreSetOptions[2]
					+ "' aspect applied for empty folder",
					"Applied Successfully", Status.FAIL);
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[2]
					+ "' aspect applied for empty folder",
					"Not able to Apply", Status.PASS);
		}
		sitesPage.clickOnMoreSetting(folderName);
		sitesPage.clickOnMoreLinkOptions(folderName, moreSetOptions[1]);
		docDetailsPage.clickAllProperties();
		if (docDetailsPage.getValueInEditPropertiesInputBox(lableName)
				.contains(valueInEditPropInputBox)) {

			report.updateTestLog("Verify " + lableName + " value",
					"Actual value contains the expected value", Status.FAIL);

		} else {
			report.updateTestLog("Verify " + lableName + " value",
					"Actual value is not contains the expected value",
					Status.PASS);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);	
		
		if (docLibPage
				.isCategoryTagAvailable(fileName, "Watched Content")) {
			report.updateTestLog("Verify '" + moreSetOptions[2]
					+ "' aspect applied for empty folder",
					"Applied Successfully", Status.FAIL);
		} else {
			report.updateTestLog("Verify '" + moreSetOptions[2]
					+ "' aspect applied for empty folder",
					"Not able to Apply", Status.PASS);
		}
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, moreSetOptions[1]);
		docDetailsPage.clickAllProperties();
		if (docDetailsPage.getValueInEditPropertiesInputBox(lableName)
				.contains(valueInEditPropInputBox)) {

			report.updateTestLog("Verify " + lableName + " value",
					"Actual value contains the expected value", Status.FAIL);

		} else {
			report.updateTestLog("Verify " + lableName + " value",
					"Actual value is not contains the expected value",
					Status.PASS);
		}


	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}