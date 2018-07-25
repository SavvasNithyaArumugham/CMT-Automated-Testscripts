package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_034P5 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void sharebox_034()
	{
		testParameters.setCurrentTestDescription("1. Verify whether the Unzip option is displayed in alfresco for the zip file which is uploaded via sharebox by External User"
				+ "<br>2. Verify whether the user can share the folder externally which is unzipped form the Zip file - Part5: Verify uploaded file in Alfresco");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String foldershare = dataTable.getData("MyFiles", "Version");
		
		sitesPage.siteFinder(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();

		sitesPage.documentdetails(foldershare);	
		String fileName = dataTable.getData("MyFiles", "FileName");
			
		myFilesTestObj.verifyUploadedFile(fileName,"");
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}