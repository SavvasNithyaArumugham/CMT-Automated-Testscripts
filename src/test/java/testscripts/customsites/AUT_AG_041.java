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
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_041 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_041()
	{
		testParameters.setCurrentTestDescription("Verify latest 300 records is displayed on Site Activites Dashboard");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		homePageObj.navigateToSitesTab();

		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		sitesPage.openSiteFromRecentSites(siteName);
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!sitesDashboardPg.isSiteActivityDashletAdded()){
			sitesDashboardPg.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesDashboardPg.selectMyActivitiesInSiteActivityDashlet();
		sitesDashboardPg.selectMyActivitiesDateRange("in the last 28 days");
		sitesDashboardPg.selectMyActivitiesCategory("all items");
		int noOfActivities = sitesDashboardPg.toGetNoOfSiteActivitiesInSiteActivityDashlet();
		System.out.println("No of Activities : " + noOfActivities);
	/*	if(noOfActivities < 300){
			sitesPage.enterIntoDocumentLibrary();
			docLibPage.deleteAllFilesAndFolders();
			float activityCount = (300 - noOfActivities)/3;
			if(activityCount > 0 && activityCount < 1){
				activityCount = 1;
			}else{
				activityCount = (Math.round(activityCount))+1;
			}
			System.out.println("No of Loops count : " + activityCount);
			for (int index = 0; index < activityCount; index++) {
				myFiles.uploadFile(filePath, fileName);
				myFiles.openAFile(fileName);
				docDetailsPage.commonMethodForPerformDocAction(docActionVal);
				sitesDashboardPg.clickDeleteButton();
			}
			
			sitesPage.enterIntoSiteDashboard();
		}*/
		UIHelper.pageRefresh(driver);
		sitesDashboardPg.selectMyActivitiesInSiteActivityDashlet();
		sitesDashboardPg.selectMyActivitiesDateRange("in the last 28 days");
		sitesDashboardPg.selectMyActivitiesCategory("all items");
		if(sitesDashboardPg.isMyActivitiesDisplayedInSiteActivityDashlet(300)){
			report.updateTestLog("Verify 300 records displayed in 'Site Activitied Dashlet'",
					"Records displayed successfully"
							+ "<br /><b> No of Records Displayed : </b>" + 
							sitesDashboardPg.toGetNoOfSiteActivitiesInSiteActivityDashlet(),
					Status.PASS);
		}else{
			report.updateTestLog("Verify 300 records displayed in 'Site Activitied Dashlet'",
					"Records not displayed"
							+ "<br /><b> No of Records Displayed : </b>" + 
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