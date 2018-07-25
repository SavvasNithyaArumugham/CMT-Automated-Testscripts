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

public class AUT_AG_426 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void calculateSizeFromSearch2()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-4902_04_Verify Calculate size using selected item drop down for a file and folder together in document library page");
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
		AlfrescoDocumentDetailsPage docDetailsPg=new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSearchPage searchPg=new AlfrescoSearchPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName1 = dataTable.getData("MyFiles", "FileName").split(",")[0];
		String fileName2 = dataTable.getData("MyFiles", "FileName").split(",")[1];
		String selectedItemsOptionName=dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName=myFiles.getFolderNames(folderDetails).get(0);
		String folderSizeInPopUp=null;
		String folderSizeInDetailsPage=null;
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName1);
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFileInMyFilesPage(filePath, fileName2);
		sitesPage.enterIntoDocumentLibrary();
		searchPg.commonMethodForPerformSimpleSearch(folderName);
		searchPg.commonMethodtoselectsearchcheck(folderName);
		searchPg.commonMethodtoselectsearchcheck(fileName1);
		searchPg.commonMethodForsearchselectedoption(selectedItemsOptionName);
		folderSizeInPopUp=docLibPage.getFolderSize();
		docLibPage.clickOnOkbuttonInFolderSizePopup();
		searchPg.openFileOrFolderFromSearchResultsPage(folderName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(folderName);
		folderSizeInDetailsPage=docDetailsPg.getFolderSizeFromDocDetailsPage();
		if(folderSizeInPopUp.contains(folderSizeInDetailsPage)) {
			report.updateTestLog("Verify size of the selected folders", "Size calculation verified successfully  <br>Size displayed in Pop Up: <b>"+folderSizeInPopUp+"</b><br>Size displayed in Details Page: <b>"+folderSizeInDetailsPage, Status.PASS);
		}else {
			report.updateTestLog("Verify size of the selected folders", "Size calculation veerified successfully", Status.FAIL);
		}
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}