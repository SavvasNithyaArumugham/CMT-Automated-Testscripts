package testscripts.xmp;

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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_020 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_020()
	{
		testParameters.setCurrentTestDescription("Verify the Tag name for already existed file on uploading newer version with different XMP Keyword Property value.");
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
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocPreviewPage docPrevPage = new AlfrescoDocPreviewPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String updateFileName = dataTable.getData("Document_Details", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String tagName = dataTable.getData("MyFiles", "TagName");
		String editTagName = dataTable.getData("MyFiles", "EditTagName");
 		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileName);
				
		if(docPrevPage.isTagCreated(fileName, tagName, false)){
			report.updateTestLog("Verify the Tag Hyperlink details",
					"Verified successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the Tag Hyperlink details",
					"Hyperlink not available", Status.FAIL);
		}
		
		docLibPage.openAFile(fileName);
		docDetailsPg.commonMethodForUploadNewVersionFile(updateFileName);
		sitesPage.enterIntoDocumentLibrary();
		
		if(docPrevPage.isTagCreated(fileName, editTagName, false)){
			report.updateTestLog("Verify the Tag updated for New Version uploded file",
					"Tag Update successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the Tag updated for New Version uploded file",
					"Tag not updated", Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
