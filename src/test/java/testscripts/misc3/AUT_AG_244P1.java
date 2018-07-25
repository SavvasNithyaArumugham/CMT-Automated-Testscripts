package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for check out a document
 * @author 516188
 */
public class AUT_AG_244P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_032()
	{
		testParameters.setCurrentTestDescription("check out a document <br> Part1: Lock File to Edit");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		//String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		homePageObj.navigateToSitesTab();
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(siteName);
		sitesPage.documentlib();
		UIHelper.waitFor(driver);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String file = dataTable.getData("Sites", "FileName");
		//myFiles.deleteUploadedFile(file);
		//epsPg.DeleteFolderandFile();
		Boolean flag3 = docLibPg.isFileIsAvailable(file);
		if(flag3){
			collectionPg.clickOnMoreSetting(file);
			collectionPg.commonMethodForClickOnMoreSettingsOption(file,"Delete Document");
			UIHelper.click(driver, epsPg.deletedocumentXpath);
			UIHelper.waitForPageToLoad(driver);
		}else{
		}
		
		myFiles.uploadFile(filePath, file);
		
		sitesPage.documentlib();
		sitesPage.documentdetails(file);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.deleteLockedFileInDownloadedPath(false);
		sitesPage.editOffline();
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}