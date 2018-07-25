package testscripts.admin;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocPreviewPage;
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

public class AUT_AG_050 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_048()
	{
		testParameters.setCurrentTestDescription("Verfiy Non admin is not able to see 'Delete Relationship' Option for folders from Document Library or from Document Actions or Preview Page");
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
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocPreviewPage docPrePage = new AlfrescoDocPreviewPage(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSettingsOptionNames = moreSettingsOptionName.split(",");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		myFiles.uploadFile(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(folderName);
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSettingsOptionNames[0]);
		
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.addRelationship(relationshipName, fileName);
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnMoreSetting(folderName);
		if(sitesPage.checkMoreSettingsOption(folderName, moreSettingsOptionNames[1])){
			report.updateTestLog("Verify the 'Delete Relationship' option in more settings",
					"Option available", Status.FAIL);
		}else{
			report.updateTestLog("Verify the 'Delete Relationship' option in more settings",
					"Option not available", Status.PASS);
		}
		
		sitesPage.clickOnViewDetails(folderName);
		if(docDetailsPage.isDocActionMenuAvailable(moreSettingsOptionNames[1])){
			report.updateTestLog("Verify the 'Delete Relationship' option in Document Actions Menu",
					"Option available", Status.FAIL);
		}else{
			report.updateTestLog("Verify the 'Delete Relationship' option in Document Actions Menu",
					"Option not available", Status.PASS);
		}
		UIHelper.waitFor(driver);
		
		if(docPrePage.isDeleteRelatioshipAvailableInPreview(fileName)){
			report.updateTestLog("Verify the 'Delete Relationship' option in Document Preview Page",
					"Option available", Status.FAIL);
		}else{
			report.updateTestLog("Verify the 'Delete Relationship' option in Document Preview Page",
					"Option not available", Status.PASS);
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
