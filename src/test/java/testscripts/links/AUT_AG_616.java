package testscripts.links;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_616 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_047()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to view the folder along with Four subfolders and files when it is linked to deep folder hierarchy");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		
		/************* TEST ***********/
		
		/*homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			//myFiles.createFolder(folderdetails);
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for(String folderName:folderNamesList)
			{
				myFiles.openFolder(folderName);
			}
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		//docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);*/
		
		/************* TEST ***********/
		
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();		
		docLibPage.deleteAllFilesAndFolders();
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		ArrayList<String> allFolderNamesList = new ArrayList<String>();
		
		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			myFiles.createFolder(folderdetails);
			ArrayList<String> folderNamesList = new ArrayList<String>();
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for(String folderName:folderNamesList)
			{
				myFiles.openFolder(folderName);
				allFolderNamesList.add(folderName);
			}
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(allFolderNamesList.get(0));			
		sitesPage.clickOnMoreOptionLink(allFolderNamesList.get(0));
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		docLibPage.linkFileIntoAnotherSite(allFolderNamesList.get(0), targetSiteName);
		
		sitesPage.clickOnViewDetails(allFolderNamesList.get(0));
		
		docLibPage.clickOnSecondaryLink(targetSiteName);
		
		for (String folderName : allFolderNamesList) {
			myFiles.openFolder(folderName);
		}
		
		ArrayList<String> filesNamesList = myFiles.getFileNames(fileName);	
		
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		docLibPageTest.verifyFileInSecondaryLinkFolder(filesNamesList);
	}

	@Override
	public void tearDown() {
		
	}

}