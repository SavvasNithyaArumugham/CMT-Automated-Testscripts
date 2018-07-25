package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_294P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3()
	{
		testParameters.setCurrentTestDescription("Validate the user able to see the thumbnail for any folder/file  in the document library with the reviewer role - Part2: Login has reviewer and check files are visible");
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
		
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
	
		
		sitesPage.enterIntoDocumentLibrary();
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		
		sitesPage.documentdetails(fileName);
	
		if(UIHelper.checkForAnElementbyXpath(driver, ".//canvas")){
			report.updateTestLog("Preview Doc for Reviewer",
					"Document is not available for Preview from Reviewer Role", Status.PASS);
		}else{
			report.updateTestLog("Preview Doc for Reviewer",
					"Document is available for Preview from Reviewer Role", Status.FAIL);
		}
		

		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}