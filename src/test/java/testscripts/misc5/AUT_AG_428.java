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

public class AUT_AG_428 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void calculateSizeUsingMoreOptionsFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_09_Verify the UI of Calculate size in french language for single folder using more options");
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
		String calculateSizeMetadata = dataTable.getData("MyFiles", "RelationshipName").split(",")[0];
		String calculateSizDateeMetadata = dataTable.getData("MyFiles", "RelationshipName").split(",")[1];
		String moreSettingsOptionName=dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName=myFiles.getFolderNames(folderDetails).get(0);
		String calculateSize="";
		String calculateSizeDate="";
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		docLibPage.uploadFileInDocumentLibPage();
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderName);
		docLibPage.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName);
		UIHelper.waitFor(driver);
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