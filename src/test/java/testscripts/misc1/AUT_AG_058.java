package testscripts.misc1;

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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_058 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_049()
	{
		testParameters.setCurrentTestDescription("Verify that calculate size action to be asynchronous");
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
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteNameValue);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String popUpMsg = dataTable.getData("MyFiles", "PopUpMsg");
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickViewDetails();
        docDetailsPage.clickDocAction(docActionVal);
        
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
		}
		
		driver.navigate().back();
		myFiles.uploadFile(filePath, fileName);
		sitesPage.clickViewDetails();
        
		String docProperty = dataTable.getData("Document_Details", "DocProperties");
        if(docDetailsPage.isDocumentPropertyAvailable(docProperty)){
        	report.updateTestLog("Verify 'Size' in Doc/Folder properties",
					"Property verified successfully"
							+ "</br><b>Property Verified:</b> " + docProperty,
					Status.PASS);
        }else{
        	report.updateTestLog("Verify 'Size' in Doc/Folder properties",
					"Property not verified"
							+ "</br><b>Property Verified:</b> " + docProperty,
					Status.FAIL);
        }
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}