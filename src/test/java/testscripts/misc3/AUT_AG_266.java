package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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

public class AUT_AG_266 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_051()
	{
		testParameters.setCurrentTestDescription("Verify that Download button is disable in preview page of Document Library."
				+"<br>2.Verify that Download button is disable in preview page of My Files."
				+"<br>3.Verify that Download button is disable in preview page of Shared Files."
				+"<br>4.Verify that Download button is disable in preview page of Repository.");
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
		String filePath = dataTable.getData("MyFiles", "FilePath");
		//Verifying Download Button in Preview page of Document Library
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}else {
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			//String filePath = dataTable.getData("MyFiles", "FilePath");
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			sitesPage.documentdetails(fileName);
		}
		
		AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsTest.verifyNoElementPresent(docDetailsTest.nodeHeaderdXpath, "Download");
		
		//Verify that Download button is disable in preview page of My Files

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToMyFilesTab();
		
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}else {
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			sitesPage.documentdetails(fileName);
		}
	
		docDetailsTest.verifyNoElementPresent(docDetailsTest.nodeHeaderdXpath, "Download");
		
		
		//Verify that Download button is disable in preview page of Shared Files.
		
		homePageObj.navigateToSharedFilesTab();
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folder = dataTable.getData("MyFiles", "Version");
	//	AlfrescoDocumentLibPage docDetailsPage= new AlfrescoDocumentLibPage(scriptHelper);
	//	docDetailsPage.deleteAllFilesAndFolders();
	//	docDetailsPage.deleteAllFilesAndFolders();
		
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		if(sitesPage.Checkdocument(folder)){			
			sitesPage.documentdetails(folder);
				
			}else {				
				myFiles.createFolder(folderDetails);
				sitesPage.documentdetails(folder);		
			}	
	
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}else {
		
			//String filePath = dataTable.getData("MyFiles", "FilePath");
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			sitesPage.documentdetails(fileName);
		}
		
		
		docDetailsTest.verifyNoElementPresent(docDetailsTest.nodeHeaderdXpath, "Download");
		
	
		/*//Verify that Download button is disable in preview page of Repository.
		if(sitesPage.Checkdocument(folder)){
			sitesPage.documentdetails(folder);
				
			}else {
				myFiles.createFolder(folderDetails);
				sitesPage.documentdetails(folder);		
			}	
		
		if(sitesPage.Checkdocument(fileName)){
				
			}else {
				myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			}
		sitesPage.documentdetails(fileName);
		
		
		docDetailsTest.verifyNoElementPresent(docDetailsTest.nodeHeaderdXpath, "Download");*/
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
	

