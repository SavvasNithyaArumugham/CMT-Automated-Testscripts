package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
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
public class AUT_AG_003 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_002()
	{
		testParameters.setCurrentTestDescription("Verify the object displayed with red 'X' icon if the publishing action got failed.");
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
			
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String Option = dataTable.getData("MyFiles", "MoreSettingsOption");		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoEPSPageTest epsTest = new AlfrescoEPSPageTest(scriptHelper);
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		if(epsTest.verifyFilePublishStatus("errored")){
			
		}
		else{
			
		if (sitesPage.Checkdocument(folderName)) {
			myFiles.openCreatedFolder(folderName);
		} else {
			myFiles.createFolder(folderDetails);
			myFiles.openCreatedFolder(folderName);
		}

		if (sitesPage.Checkdocument(fileNme)) {
			sitesPage.clickOnMoreSetting(fileNme);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, Option);
			docLibPg.clickDeletePopup();
		}
		myFiles.createFile(fileDetails);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderName);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptName);	
		
		//docLibPg.clickPublishPopup();
		
		epsTest.verifyFilePublishStatus("errored");
		
		}	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}