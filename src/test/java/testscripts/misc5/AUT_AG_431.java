package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_431 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void SizeCheckInFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_13_Verify the size of the documents transulated to french language for Bytes KB MB GB");
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
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest =new AlfrescoDocumentLibPageTest(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileNames = dataTable.getData("MyFiles", "FileName").split(",");
		String[] folderNames=dataTable.getData("MyFiles", "Version").split(",");
		String[] expValue=dataTable.getData("MyFiles", "StatusReportValue").split(",");
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		for(int i=0;i<folderNames.length;i++) {
			myFiles.openCreatedFolder(folderNames[i]);
			myFiles.uploadFileInMyFilesPage(filePath, fileNames[i]);
			sitesPage.enterIntoDocumentLibrary();
		}
		UIHelper.waitFor(driver);
		docLibPage.selectAllFilesAndFolders();
		docLibPage.clickOnCalculateSizeInSelectedItemsMenu();
		docLibPage.clickOnOkbuttonInFolderSizePopup();
		
		if((folderNames.length != 0 && expValue.length != 0) && (folderNames.length == expValue.length)) {
			for(int i=0;i<folderNames.length;i++) {
				docLibPageTest.verifyCalculationSizeInDocLib(folderNames[i], expValue[i]);
			}
			
		}else {
			report.updateTestLog("Size check in French", "Mismatch in expexcted value and folder names in excel", Status.FAIL);
		}
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}