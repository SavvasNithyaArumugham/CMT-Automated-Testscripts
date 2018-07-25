package testscripts.misc4;

import java.io.File;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_399P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC030P1()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in search results page<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in shared files page<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a sub folder under folder that contains percent symbol in document library<br>"+
				"ALFDEPLOY-3415_Verify user able to access the file which contains percent symbol inside a folder that contains percent symbol in document library<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in document library<br>"+
				"ALFDEPLOY-3415_Verify user is able to access any type of file inside a folder that contains percent symbol in My files page");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
	
		String file1 = dataTable.getData("MyFiles", "CreateFolder");
		String downloadopt = dataTable.getData("MyFiles", "BrowseActionName");
	
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(file1);
		
		if(!docDetailsTest.verifyElementPresentreturn(docDetailsTest.downloadbutton,downloadopt)) {
			report.updateTestLog("Verify Download Button is available",
					"Download Button Element is not displayed in the page", Status.PASS);
		}else {
			report.updateTestLog("Verify Download Button is available",
					"Download Button Element is displayed in the page whihc is not expected", Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}