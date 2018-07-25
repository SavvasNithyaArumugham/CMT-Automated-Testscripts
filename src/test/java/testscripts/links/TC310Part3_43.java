package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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

public class TC310Part3_43 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_039()
	{
		testParameters.setCurrentTestDescription("Verify that Manager is  able to Link Files or Folders created by other roles (Both Source file or folder  and Destination folder not created by manager)"
				+ "<br/><b>Part3 :Manager links folders</b>");
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
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		String siteName=sitesPage.getCreatedSiteName();	
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(siteName);
		//sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		
	
		folderNamesList = myFiles.getFolderNames(folderDetails);
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);
			sitesPage.clickOnMoreLinkOptions(folderName, propertyName);
			break;
		}
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.selectFolderInLinkPopUp(siteName);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			
		myFiles.openFolder(folderNamesList.get(1));
		myFilesTestObj.verifyIsFileAvilabile(folderNamesList.get(0),"");
				
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}