package testscripts.misc2;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_106 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_002() {
		testParameters
				.setCurrentTestDescription("Verify the folder that is marked as favourite is visible in my documents dashlet");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
	

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);

	/*homePageObj.customizeSiteDashboard();
		
		//homePageObj.commoncustDd("Customize Dashboard");
		
		sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();*/
		


		String siteName = dataTable.getData("Sites", "SiteName");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			docLibPg.clickOnFavoriteLink(folderName);
		
			homePageObj.navigateToHomePage();
			
			String dashletNmetoAdd = dataTable.getData("Home", "DashletName");
	
			if(!homePageObj.checkExpectedDashletInHomePage(dashletNmetoAdd)){
				
				homePageObj.customizeSiteDashboard();
				
				//homePageObj.commoncustDd("Customize Dashboard");
				
				sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
			}
			
			String myDocumentsFilterOptionVal = dataTable.getData("Home",
					"MyDocumentsFilterDropdownValue");
			
			homePageObj.filterMyDocumentsDashletData(myDocumentsFilterOptionVal);
			
			homePageObj.waitForFilteredItemInMyDocumentsDashlet(folderName);
			
			homePageTest.verifyFilteredDocumentsInMySitesDashlet(folderName, myDocumentsFilterOptionVal);
		}
	}

	@Override
	public void tearDown() {

	}

}