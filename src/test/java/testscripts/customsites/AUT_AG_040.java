package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_040 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_023()
	{
		testParameters.setCurrentTestDescription("1. Verify the Site Relationships Dashlet is added to Site by default"
				+ "<br>2. Verfiy User is navigated to Target site Dashboard on clicking target site on Relationship Widget");
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
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(
				scriptHelper);
		
		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		sitesPage.openSiteFromRecentSites(addRelationSite);
		//sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		//docLibPage.deleteAllFilesAndFolders();
		
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		String siteName = dataTable.getData("Sites", "SiteName");
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		
		if(sitesDashboardPg.isSiteRelationshipDashletAdded()){	
			report.updateTestLog("Verify '"+dashletNmetoAddTest+"' Dashlet added by default", "Dashlet added by default succesfully"
					+ "<br/><b>Dashlet Name : </b>"
					+ dashletNmetoAddTest,
					Status.PASS);
		}else{
			report.updateTestLog("Verify '"+dashletNmetoAddTest+"' Dashlet added by default", "Dashlet not added by default"
					+ "<br/><b>Dashlet Name : </b>"
					+ dashletNmetoAddTest,
					Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileName);
		myFiles.openAFile(fileName);
        docDetailsPage.commonMethodForPerformDocAction(docActionVal);
				
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.addRelationshipBtwAssetAndSite(relationshipName, fileName, addRelationSite);
				
		docDetailsPageTest.verifyAddedRelationshipData(addRelationSite.toLowerCase());
		
		if(docDetailsPage.openAndVerifyRelationshipSite()){
			report.updateTestLog("Navigate to Target site Dashdoard",
					"Navigated successfully"
							+ "</br><b>Target Site Name:</b> " + addRelationSite,
					Status.PASS);
		}else{
			report.updateTestLog("Navigate to Target site Dashdoard",
					"Navigation Failed"
							+ "</br><b>Target Site Name:</b> " + addRelationSite,
					Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}