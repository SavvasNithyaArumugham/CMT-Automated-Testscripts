package testscripts.misc3;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_230P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_020()
	{
		testParameters.setCurrentTestDescription("Verify that user with restricted permission can only download & View the file in browser and canot perform below operations:"
				+"<br>- Edit Offline"
				+"<br>- Edit Properties"
				+"<br>- Move"
				+"<br>- Delete");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = sitesPage.getCreatedSiteName();
		
		/*boolean isDisplayedSite = homePageObj.commonMethodForCheckTaskInMyTasksDashlet(siteName);
		
		if(isDisplayedSite)
		{
			homePageObj.clickOnTaskInMyDashlet(siteName);
			taskPage.performAcceptOnTask(siteName);
		}*/
		
	
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.selectAllItems("File");
		
		sitesPage.clickOnSelectedItems();
	
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		if(selectedItemMenuOptVal.contains(","))
		{
			String splittedSelectOptions[] = selectedItemMenuOptVal.split(",");
			if(splittedSelectOptions!=null)
			{
				for(String selectItemDropOption: splittedSelectOptions)
				{
					sitePageTest.verifySelectedItemsMenuOptionForNegativeCase(selectItemDropOption);
				}
			}
		}
		
		String browseOption = dataTable.getData("MyFiles", "BrowseActionName");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		ArrayList<String> createdFileNames = myFilesTestObj.getCreatedFileNames(fileDetails);
		
		for(String fileName:createdFileNames)
		{
			sitePageTest.verifyBrowseActionNameForFileOrFolderInNegativeCase(fileName, browseOption);
			
			myFiles.downloadFile(fileName);
			
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			docDetailsPageTest.verifyDownloadedFile(false, fileName);
			
			myFiles.openUploadedOrCreatedFile(fileName,"");
			
			docDetailsPageTest.verifyFileIsViewable(fileName,"");
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}