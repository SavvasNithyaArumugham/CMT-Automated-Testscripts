package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_114 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_010()
	{
		testParameters.setCurrentTestDescription("Verify All Items Records is displayed on selecting all Items value in the Dropdown on the Site Activites Dashlet");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		homePageObj.navigateToSitesTab();

		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileName);
		
		sitesPage.enterIntoSiteDashboard();
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!sitesDashboardPg.isSiteActivityDashletAdded()){
			sitesDashboardPg.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesDashboardPg.selectMyActivitiesInSiteActivityDashlet();
		sitesDashboardPg.selectMyActivitiesCategory("all items");
		sitesDashboardPg.selectMyActivitiesDateRange("in the last 28 days");
		int noOfActivities = sitesDashboardPg.toGetNoOfSiteActivitiesInSiteActivityDashlet();
		System.out.println("No of Activities : " + noOfActivities);
		if(noOfActivities > 0){
			report.updateTestLog("Verify records displayed in 'Site Activitied Dashlet'",
					"Records displayed successfully"
							+ "<br /><b> No of Records Displayed : </b>" + 
							sitesDashboardPg.toGetNoOfSiteActivitiesInSiteActivityDashlet(),
					Status.PASS);
		}
		
		else
		{
			report.updateTestLog("Verify records displayed in 'Site Activitied Dashlet'",
					"Records displayed successfully"
							+ "<br /><b> No Records Displayed : </b>" + 
							sitesDashboardPg.toGetNoOfSiteActivitiesInSiteActivityDashlet(),
					Status.FAIL);
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}