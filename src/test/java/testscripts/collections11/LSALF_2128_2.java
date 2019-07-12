package testscripts.collections11;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2128_2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters.setCurrentTestDescription("Verify on importing a print plan CSV with Dynamic Content Type invalid , the error report is generated and attached to the completed course CSV once moved to completed folder.");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	
	
	@Override
	public void executeTest() {
		
		// Log in Pearson Schools project
				AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
				functionalLibrary.loginAsValidUser(signOnPage);
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
				AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
				AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
				AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
				
		
			
		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");
		
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String origFileName = dataTable.getData("MyFiles", "Version");
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		//String filepathfilename = dataTable.getData("MyFiles", "FileName");
		String invalidFileName = dataTable.getData("MyFiles", "CreateFolder");
		// Import Original Course
				myFiles.openCreatedFolder("Data Imports");
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder("Course Plan");
				UIHelper.waitFor(driver);

				// upload course plan
				collectionPg.uploadFileInCollectionSite(filepathfile, origFileName);
				UIHelper.waitFor(driver);
				// Import invalid dynamic content type in the DC object of the csv		
				sitesPage.enterIntoDocumentLibrary();
		// go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Course Plan");
		UIHelper.waitFor(driver);

		// upload course plan
		collectionPg.uploadFileInCollectionSite(filepathfile, invalidFileName);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Data Imports");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Completed");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder(currentDate.substring(0, 4));
		myFiles.openCreatedFolder(currentDate.substring(5, 7));
		myFiles.openCreatedFolder(currentDate.substring(8, 10));
		UIHelper.waitFor(driver);

		collectionPg.clickOnMoreSetting(invalidFileName);
		UIHelper.waitFor(driver);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,errorfile);	
		collectionPg.commonMethodForClickOnMoreSettingsOption(invalidFileName, "View Error Report");
		UIHelper.waitFor(driver);					
		new FileUtil().waitUptoAllFilesDownloadComplete(downloadFilePath,errorfile);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		homePageObj.openNewTab(downloadFilePath + "\\" + errorfile);	
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		collectionPg.errormessagecontains("invalid value","Property [Dynamic Content Type] of type [{http://cms.pearsonnals.com/model/content/1.0}dynamicContentType] has invalid value");
		
	}
	


	

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
