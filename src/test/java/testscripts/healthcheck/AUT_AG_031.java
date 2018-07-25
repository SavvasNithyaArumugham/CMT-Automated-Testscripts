package testscripts.healthcheck;

import java.util.ArrayList;

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
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_031 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_056()
	{
		testParameters.setCurrentTestDescription("1. Verify that Calculate Size action is available in the Document Action menu of the folder.(User belongs to GROUP_Folder_Calculation"
				+ "<br>2. Verify that 'Calculate Size' action is available in the Document Action menu of the folder on 'View Details' page.(User belongs to GROUP_Folder_Calculation)"
				+ "<br>3. Verify that 'Calculate Size' action is not available in the Document Action menu of the file.(User belongs to GROUP_Folder_Calculation)"
				+ "<br>4. Verify that size of the folder and the date is to be displayed as metadata in the view details page when it was calculated");
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
		
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			
		docLibPg.deleteAllFilesAndFolders();
	
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		

		myFiles.createFolder(folderDetails);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folder = dataTable.getData("MyFiles", "Version");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		sitesPage.documentdetails(folder);
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(folder);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderNamesList.get(0), moreSettingsOption);
		
		
		sitesPage.clickOnViewDetails(folder);
		AlfrescoDocumentDetailsPageTest docTest=new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docTest.verifyDocumentActionsOption(moreSettingsOption);
		
        docDetailsPage.clickDocAction(moreSettingsOption);
        
    /*    String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
        
		if (docLibPgTest.isPopUpMsgDisplayed(popUpMsg)) {
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message displayed successfully"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.PASS);
		}else{
			report.updateTestLog("Verify PopUp Message",
					"PopUp Message not displayed"
							+ "<br><b> Verified Pop Message : </b>"
							+ popUpMsg, Status.FAIL);
		}*/
        
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		String docProperty = dataTable.getData("Document_Details", "DocProperties");
		String[] docProperties = docProperty.split(",");
		boolean flag1 = docDetailsPage.isDocumentPropertyAvailable(docProperties[0]);
		boolean flag2 = docDetailsPage.isDocumentPropertyAvailable(docProperties[1]);
        if(flag1 && flag2){
        	report.updateTestLog("Verify 'Size' in Doc/Folder properties",
					"Property verified successfully"
							+ "</br><b>Properties Verified:</b> " + docProperties[0] +","+docProperties[1],
					Status.PASS);
        }else{
        	report.updateTestLog("Verify 'Size' in Doc/Folder properties",
					"Property not verified"
							+ "</br><b>Properties Verified:</b> " + docProperties[0] +","+docProperties[1],
					Status.FAIL);
        }
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}