package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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
public class AUT_AG_267 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_052()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to view content as label above content field when user try to create plain text file"
				+"<br>2.Verify that user is able to view content as label above content field when user try to create HTML file"
				+"<br>3.Verify that user is able to view content as label above content field when user try to create XML file");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fieldLabelName = dataTable.getData("MyFiles", "CreateFileFieldName");
		String[] splittedFileDetails=fileDetails.split(",");
		String textFileDetails=splittedFileDetails[0];
		String htmlFileDetails=splittedFileDetails[1];
		String xmlFileDetails=splittedFileDetails[2];
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		
		//For plain text file
		sitesPage.enterIntoDocumentLibrary();
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		myFiles.commonMethodForClickOnCreateDropdownItems(textFileDetails);
		
		myFilesTestObj.verifyCreateFileFieldLabel(fieldLabelName);
		
		
		//For HTML file
        sitesPage.enterIntoDocumentLibrary();
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		myFiles.commonMethodForClickOnCreateDropdownItems(htmlFileDetails);
		myFilesTestObj.verifyCreateFileFieldLabel(fieldLabelName);
		
		
		//For XML file
		

		sitesPage.enterIntoDocumentLibrary();
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
		myFiles.commonMethodForClickOnCreateDropdownItems(xmlFileDetails);
		
		
		myFilesTestObj.verifyCreateFileFieldLabel(fieldLabelName);
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}