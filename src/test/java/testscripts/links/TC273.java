package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC273 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_024()
	{
		testParameters.setCurrentTestDescription("Verify user is able to Link multiple files using  'Link to ' functionality  in drop down under Selected Items tab in Document Library");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		homePageObj.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		UIHelper.waitFor(driver);		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.deleteAllFilesAndFolders();
				
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		folderNamesList = myFiles.getFolderNames(folderDetails);
		myFiles.createFolder(folderDetails);
			
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFiles.methodToSelectMultipleFiles(fileName);
		myFiles.selectDropDownLinkTo();
		
		docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		sitesPage.documentdetails(folderNamesList.get(0));
	//	myFiles.openFolder(folderNamesList.get(0));
		myFilesTestObj.verifyMultipleFilesAvailable(fileName);
						
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}