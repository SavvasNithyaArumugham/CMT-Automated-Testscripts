package testscripts.objectmodel;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_016P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_016()
	{
		testParameters.setCurrentTestDescription("Verfiy user with roles (Contributor, Consumer) has no permission to add aspects to folder/file uploaded by others in document library - Part 2 - Contributor Role");
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
		
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			sitesPage.clickOnMoreSetting(folderName);
			sitePgTestObj.verifyManageAspectsOptionIsAvaible(folderName, "Contributor");
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
