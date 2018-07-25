package testscripts.misc1;

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
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_012P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_012()
	{
		testParameters.setCurrentTestDescription("Verfiy members of the site is able to 'Add Relationship' for between sites - Invite users and Add Relationship");
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
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		
		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		sitesPage.siteFinder(addRelationSite);
		
		sitesPage.performInviteUserToSite(addRelationSite);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String files = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.siteFinder(siteNameValue);
		if(!sitesDashboardPg.isSiteRelationshipDashletAdded()){	
			AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
			sitesDashboardPg.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesPage.performInviteUserToSite(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, files);
		myFiles.openAFile(fileName);
        docDetailsPage.commonMethodForPerformDocAction(docActionVal);
				
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.addRelationshipBtwAssetAndSite(relationshipName, fileName, addRelationSite);
				
		docDetailsPageTest.verifyAddedRelationshipData(addRelationSite.toLowerCase());
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}