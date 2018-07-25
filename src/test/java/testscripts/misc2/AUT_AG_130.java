package testscripts.misc2;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_130 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_026()
	{
		testParameters.setCurrentTestDescription("1. Verify user is able to delete file(present in deep folder) successfully from selected items drop down menu"
				+ "<br>2. Verify user is able to delete file(present in deep folder) successfully from Actions Menu");
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
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String allFolderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		ArrayList<String> allFolderNameList = myFiles.getFolderNames(allFolderDetails);
		int foldersCount= allFolderNameList.size();
		
		StringTokenizer token = new StringTokenizer(allFolderDetails, ";");
		int index=1;
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			myFiles.createFolder(folderdetails);
			ArrayList<String> folderNameList = myFiles.getFolderNames(folderdetails);
			for(String folderName:folderNameList)
			{
				sitesPage.openFolder(folderName);
				
				if(index==foldersCount)
				{
					//Verify user is able to delete file(present in deep folder) successfully from selected items drop down menu
					myFiles.createFile(fileDetails);
					UIHelper.waitFor(driver);
					
					docDetailsPageObj.backToFolderOrDocumentPage(folderName);
					
					docLibPage.selectDocumentLibItems("None");
					//sitesPage.enterIntoDocumentLibraryWithoutReport();
					
					UIHelper.waitFor(driver);
					docLibPageTest.verifyDeleteFileByNavigatingFolder(fileName, "selectedItems");
					
					//Verify user is able to delete file(present in deep folder) successfully from Actions Menu
					myFiles.createFile(fileDetails);
					UIHelper.waitFor(driver);
					
					docDetailsPageObj.backToFolderOrDocumentPage(folderName);
					
					myFiles.openAFile(fileName);
					
					UIHelper.waitFor(driver);
					docLibPageTest.verifyDeleteFileByNavigatingFolder(fileName, "DocActionForFile");
				}
			}
			index++;
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
