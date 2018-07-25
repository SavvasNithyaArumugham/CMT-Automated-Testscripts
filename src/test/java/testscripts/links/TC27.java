package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocPreviewPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC27 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_019()
	{
		testParameters.setCurrentTestDescription("Verify user is able to open primary location linked file with special characters '&','+'");
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
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocPreviewPage docPrevPage = new AlfrescoDocPreviewPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] folderNames = folderName.split(",");
 		
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		myFiles.openFolder(folderNames[0]);
		myFiles.uploadFile(filePath, fileName);
		myFiles.openAFile(fileName);
		
		docDetailsPage.clickonLinkToInPreviewPage(fileName);
//		docDetailsPage.selectFolderInLinkPopUp(siteNameValue, folderNames[1].replace("&", "&amp;"));
		docDetailsPageObj.selectFolderInLinkPopUp(siteNameValue);
		docDetailsPage.clickLinkBtnInLinkPopUp();
		
		docPrevPage.isLinkedLocationURLAvailable("Original");
		docPrevPage.isLinkedLocationURLAvailable("Secondary");
		
		docPrevPage.clickLinkedLocationURL("Secondary");
		
		if(docLibPg.isFileIsAvailable(fileName)){
			report.updateTestLog("Verify the Linked File",
					"File Linked successfully"
					+"</br> <b> Linked File Name : </b> " + fileName, Status.PASS);
		}else{
			report.updateTestLog("Verify the Linked File",
					"File not Linked"
					+"</br> <b> Linked File Name : </b>" + fileName, Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
