package testscripts.epm;


import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_015 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_015()
	{
		testParameters.setCurrentTestDescription("Verify the warning message window displayed when trying to search with empty fields in search tab");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
	
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		//homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");

	sitesPage.siteFinder(sourceSiteName);
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		
		siteDashboard.navigateToSiteDashboard();
		
		for (String folderName : folderNamesList) {
			System.out.println(folderName);
			siteDashboard.selectFoldersInEPMDashlet(folderName);
			siteDashboard.clickOnFindButton();
			siteDashboard.verifyEPMPopUpMsg("Invalid search criteria. Please enter text to be searched.");
			siteDashboard.clickOKButtonInPopMsg();
			
			String searchContent=dataTable.getData("EPM", "ListTitlesWithFieldValue");
			siteDashboard.enterListTitle(searchContent);
			siteDashboard.enterFromDate("13/04/2016");
			siteDashboard.clickOnFindButton();
			siteDashboard.verifyEPMPopUpMsg("Invalid search criteria. To Date cannot be blank if From date is populated.");
			siteDashboard.clickOKButtonInPopMsg();
			siteDashboard.clearFromDate();
			
			siteDashboard.enterToDate("13/04/2016");
			siteDashboard.clickOnFindButton();
			siteDashboard.verifyEPMPopUpMsg("Invalid search criteria. From Date cannot be blank if To date is populated.");
			siteDashboard.clickOKButtonInPopMsg();
			
			siteDashboard.enterFromDate("13/04/2016");
			siteDashboard.enterToDate("12/04/2016");
			siteDashboard.clickOnFindButton();
			siteDashboard.verifyEPMPopUpMsg("Invalid search criteria. From Date cannot be greater than To Date.");
			siteDashboard.clickOKButtonInPopMsg();
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}