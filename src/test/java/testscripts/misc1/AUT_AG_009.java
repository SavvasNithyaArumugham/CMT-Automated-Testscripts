package testscripts.misc1;

import java.util.ArrayList;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_009 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_009()
	{
		testParameters.setCurrentTestDescription("1. Verfiy user is able to 'add relationship' between file and a site from Document Actions Menu"
				+ "<br>2. To check if the user is able to see a secondary link in the locations section when a relationship is added to an asset(Note : Secondary link should not be created in the section mentioned)");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(addRelationSite);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		ArrayList<String> uploadedFileDetails = myFilesTestObj.getUploadedFileDetails(fileName);
		
		if(uploadedFileDetails!=null && uploadedFileDetails.size()>2)
		{
		
			myFiles.uploadFile(filePath, uploadedFileDetails.get(0));
			myFiles.openAFile(uploadedFileDetails.get(0));
	        docDetailsPage.commonMethodForPerformDocAction(docActionVal);
					
			String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
			String relationShipName1="", relationShipName2="";
			if(relationshipName.contains(","))
			{
				String splittedRelationshipNames[] = relationshipName.split(",");
				if(splittedRelationshipNames!=null)
				{
					relationShipName1 = splittedRelationshipNames[0];
					relationShipName2 = splittedRelationshipNames[1];
				}
			}
			else
			{
				relationShipName1 = "Next Version";
				relationShipName2 = "Association";
			}
			
			sitesPage.addRelationshipBtwAssetAndSite(relationShipName1, uploadedFileDetails.get(0), addRelationSite);
					
			if(docDetailsPage.isRelationshipAddedForSite()){
				report.updateTestLog("Verify Relationship Added between File and Site",
						"Relationship Added successfully"
								+ "</br><b>File Name:</b> " + uploadedFileDetails.get(0)
								+ "</br><b>Site Name:</b> " + addRelationSite,
						Status.PASS);
			}else{
				report.updateTestLog("Verify Relationship Added between File and Site",
						"Relationship not Added"
								+ "</br><b>File Name:</b> " + uploadedFileDetails.get(0)
								+ "</br><b>Site Name:</b> " + addRelationSite,
						Status.FAIL);
			}
			
			sitesPage.enterIntoDocumentLibrary();
			docLibPage.deleteAllFilesAndFolders();
			
			myFiles.uploadFile(filePath, uploadedFileDetails.get(1));
			myFiles.uploadFile(filePath, uploadedFileDetails.get(2));
		
			sitesPage.clickOnMoreSetting(uploadedFileDetails.get(1));
			
			sitesPage.clickOnMoreOptionLink(uploadedFileDetails.get(1));
			
			sitesPage.addRelationship(relationShipName2, uploadedFileDetails.get(2));
					
			myFiles.openAFile(uploadedFileDetails.get(1));
			
			docDetailsPageTest.verifyAddedRelationshipData(uploadedFileDetails.get(2));
		
			if(docDetailsPage.openAndVerifyRelationshipSite()){
				report.updateTestLog("Navigate to Target site Dashdoard",
						"Navigated successfully"
								+ "</br><b>Target Site Name:</b> " + siteNameValue
								+ "</br><b>Target File Name:</b> " + uploadedFileDetails.get(2),
						Status.PASS);
			}else{
				report.updateTestLog("Navigate to Target site Dashdoard",
						"Navigation Failed", Status.FAIL);
			}
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}