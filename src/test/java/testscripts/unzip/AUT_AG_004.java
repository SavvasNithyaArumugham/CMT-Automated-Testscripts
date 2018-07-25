package testscripts.unzip;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
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
public class AUT_AG_004 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void unzip_04()
	{
		testParameters.setCurrentTestDescription("1. Verify user is not able to see 'Unzip to' options for a normal folder"
				+ "<br>2. Verify user is not able to see 'Unzip to' option from Selected items menu"
				+ "<br>3. Verify user is not able to see 'Unzip to' option when hover on zip file/folder"
				+ "<br>4. Verify user is not able to perform 'Unzip to' in a folder where already the Unzip operation is performed");
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
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDTPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
        myFiles.createFolder(folderDetails);
        myFilesTestObj.verifyCreatedFolder(folderDetails);
        
        String docActionMenu = dataTable.getData("Sites", "SelectedItemsMenuOption");
        
        ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) {
			sitesPage.clickOnViewDetails(folderName);
        
	        ArrayList<String> docOptionValues = docDTPg.getDocActionOptions();
	        boolean flag = false;
			for (String actualVal : docOptionValues) {
				if (actualVal.contains(docActionMenu)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
	        
			if(flag){
				report.updateTestLog("Verify the UnZip option for normal folder",
						"Unzip option available"
						+"</br> <b>Folder Name : </b>" +folderName , Status.FAIL);
			}else{
				report.updateTestLog("Verify the UnZip option for normal folder",
						"Unzip option not available"
						+"</br> <b>Folder Name : </b>" +folderName , Status.PASS);
			}
			
			sitesPage.enterIntoDocumentLibrary();
		}
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String splitVal[] = fileName.split(Pattern.quote("."));
		String finalFileName = splitVal[0];
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		sitePageTest.verifySelectedItemsMenuOptionForNegativeCase(docActionMenu);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();		
		
		sitesPage.clickOnMoreSetting(fileName);
		sitePageTest.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, docActionMenu);
		
		myFiles.openUploadedOrCreatedFile(fileName,"");
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		docDTPg.performUnzipDocAction(extractTo);
		
		docDTPg.backToFolderOrDocumentPage("");
		
		myFilesTestObj.verifyUnzippedFolder(finalFileName);
		
		myFiles.openUploadedOrCreatedFile(fileName,"");
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
		docDTPg.performUnzipForPopUpMsg(extractTo);
		
		String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
		if (docLibPgTest.isUnzipPopUpDispayed()) {
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message displayed successfully"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.PASS);
			UIHelper.waitFor(driver);
		}else{
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message not displayed"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}