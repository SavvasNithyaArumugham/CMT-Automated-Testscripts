package testscripts.customsites;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_010 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_010()
	{
		testParameters.setCurrentTestDescription("Verify the user(who created Program Site and Component Site) is able to access Component Site which has Private visibilty");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		String testOutputMultipleFilePath = "src/test/resources/AppTestData/TestOutput/MultipleSiteDetails.txt";
		new FileUtil().clearFile(testOutputMultipleFilePath);
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);		
		
		ArrayList<String> sitedata = null;
		try {
			sitedata = new FileUtil().readListOFDataFromFile(testOutputMultipleFilePath);
			System.out.println("DATA : " + sitedata.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sitedata.get(0));
		sitesPage.enterIntoDocumentLibrary();	
		
		AlfrescoMyFilesPageTest myFilesPageTest = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesPageTest.verifyComponentSiteNameFolderInProgramSite(sitedata.get(1));
		myFiles.openCreatedFolder(sitedata.get(1));
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
