package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_429 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void calculateSizeFromSearchFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_11_Verify the UI of Calculate size in french language from search page");
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
		String moreSettingsOptionName=dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName=myFiles.getFolderNames(folderDetails).get(0);
		String expPromptHeaderMsg=dataTable.getData("MyFiles", "PopUpMsg").split(",")[0];
		String expPromptBodyMsg=dataTable.getData("MyFiles", "PopUpMsg").split(",")[1];
		String actPromptHeaderMsg="";
		String actPromptBodyMsg="";
		String calculateSizeMetadata = dataTable.getData("MyFiles", "RelationshipName").split(",")[0];
		String calculateSizDateeMetadata = dataTable.getData("MyFiles", "RelationshipName").split(",")[1];
		String calculateSize="";
		String calculateSizeDate="";
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		docLibPage.uploadFileInDocumentLibPage();
		sitesPage.enterIntoDocumentLibrary();
		searchPg.commonMethodForPerformSimpleSearch(folderName);
		searchPg.commonMethodForPerformSelectedItemsOperation(folderName, moreSettingsOptionName);
		UIHelper.waitFor(driver);
		actPromptHeaderMsg=docLibPage.getPromptHeaderMessage();
		actPromptBodyMsg=docLibPage.getPromptbodyMessage();
		if(actPromptHeaderMsg.equalsIgnoreCase(expPromptHeaderMsg) && actPromptBodyMsg.contains(expPromptBodyMsg)) {
			report.updateTestLog("Verify Pop Up Msg for calculate Size in french", "Pop Up Header and body Message verification in french successfull <br><b>Header :</b>"+actPromptHeaderMsg+"<br><b>Body :</b>"+actPromptBodyMsg, Status.PASS);
		}else {
			report.updateTestLog("Verify Pop Up Msg for calculate Size in french", "Pop Up Header and body Message verification in french Unsuccessfull", Status.FAIL);
		}
		docLibPage.clickOnOkbuttonInFolderSizePopup();
		searchPg.openFileOrFolderFromSearchResultsPage(folderName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnViewDetails(folderName);
		calculateSize=searchPg.getMetadata(calculateSizeMetadata);
		calculateSizeDate=searchPg.getMetadata(calculateSizDateeMetadata);
		if(!(calculateSize.isEmpty()&&calculateSizeDate.isEmpty())) {
			report.updateTestLog("Verify Calcualte size & Calculate Size Date in french for the selected folders", "Size Calculation Date and Calculate Size field verified in french successfully ", Status.PASS);
		}else {
			report.updateTestLog("Verify Calcualte size & Calculate Size Date in french for the selected folders", "Size Calculation Date and Calculate Size field in french language verification failed ", Status.FAIL);
		}
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}