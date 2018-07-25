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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC311Part2_44 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_040()
	{
		testParameters.setCurrentTestDescription("Verify that Contributer is able to Link  to self created Files or Folders and "
				+ "also created by other roles <br/>Part2 : Try to link Folders created by Self and by manager");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	//	homePage.navigateToTasksTab();
	//	taskPage.navigateToMyTasksMenu();
		String siteName=sitesPage.getCreatedSiteName();	
	//	taskPage.openCreatedOrAssignedTask(siteName);
	//	taskPage.acceptSiteInvitation(siteName);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
	//	homePageObj.navigateToSitesTab();
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		ArrayList<String> folderNamesList = new ArrayList<String>();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderNames = dataTable.getData("MyFiles", "FileName");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
			
		myFiles.createFolder(folderDetails);
		
		folderNamesList = myFiles.getFolderNames(folderNames);
		
		sitesPage.clickOnMoreSetting(folderNamesList.get(0));
		sitesPage.clickOnMoreLinkOptions(folderNamesList.get(0), propertyName);
			
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(siteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFiles.openFolder(folderNamesList.get(1));
		myFilesTestObj.verifyIsFileAvilabile(folderNamesList.get(0),"");
		
		sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		sitesPage.clickOnMoreSetting(folderNamesList.get(2));
		docLibPageTest.verifyOptionInMoreLinkOptions(folderNamesList.get(2), propertyName);
		
						
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}