package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_427 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void calculateSizeUsingSelectedItemDropDownFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_10_Verify the UI of Calculate size in french language for multiple folder using selceted items");
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
		AlfrescoSearchPage searchPg= new AlfrescoSearchPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String calculateSizeMetadata = dataTable.getData("MyFiles", "MoreSettingsOption").split(",")[0];
		String calculateSizDateeMetadata = dataTable.getData("MyFiles", "MoreSettingsOption").split(",")[1];
		String[] folderNames= dataTable.getData("MyFiles", "Version").split(",");	
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String calculateSize="";
		String calculateSizeDate="";
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		for(String tempFolderName:folderNames) {
			myFiles.openCreatedFolder(tempFolderName);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			sitesPage.enterIntoDocumentLibrary();
			}
		docLibPage.selectAllFilesAndFolders();
		docLibPage.clickOnCalculateSizeInSelectedItemsMenu();
		docLibPage.clickOnOkbuttonInFolderSizePopup();
		
		for(String tempFolderName:folderNames) {
		sitesPage.clickOnViewDetails(tempFolderName);
		calculateSize=searchPg.getMetadata(calculateSizeMetadata);
		calculateSizeDate=searchPg.getMetadata(calculateSizDateeMetadata);
			if(!(calculateSize.isEmpty()&&calculateSizeDate.isEmpty())) {
				report.updateTestLog("Verify Calcualte size & Calculate Size Date in french for the selected folders", "Size Calculation Date and Calculate Size field verified in french successfully for folder - "+tempFolderName, Status.PASS);
			}else {
				report.updateTestLog("Verify Calcualte size & Calculate Size Date in french for the selected folders", "Size Calculation Date and Calculate Size field in french language verification failed for folder - "+tempFolderName, Status.FAIL);
			}
		sitesPage.enterIntoDocumentLibrary();	
	   }
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}