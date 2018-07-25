package testscripts.admin;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_createF extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void ADMIN_001()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to create a group "
				+ "and add users in it");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
	//	AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
	//	docLibPg.deleteAllFilesAndFolders();		
		for(int i=742;i<1400;i++){
			 //UIHelper.waitForPageToLoad(driver);
			  myFiles.commonMethodForCreateFolder("Autotest"+i, "Testfolder", "BulkRequirement");
			 System.out.println("Autotest"+i+Calendar.getInstance().getTimeInMillis());
		}
      
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}