package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_027 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_046() {
		testParameters
				.setCurrentTestDescription("<br>ALFDEPLOY-4051Verify When user provide invalid values(other than US region) Version Country,State code,Version disttrict"
		+ " and Version statement for Content object.Erros should be reported in Error.html file name.Errors should be reported in Error.html file name."
		+"<br>ALFDEPLOY-4051Verify When user provide invalid values(other than US region) for Version Country,State code,Version disttrict and Version statement for learning bundle object.Erros should be reported in Error.html file name.Erros should be reported in Error.html file name.Errors should be reported in Error.html file name."
		+"<br>ALFDEPLOY-4051Verify When user provide invalid values(other than US region) for Version Country,State code,Version disttrict and Version statement for Course object.Errors should be reported in Error.html file name."
		+"<br>ALFDEPLOY-4051Verify When user provide invalid values(other than US region) for Version Country,State code,Version disttrict and Version statement for Sequence object.Errors should be reported in Error.html file name."
		+"<br>ALFDEPLOY-4051Verify When user provide invalid values(other than US region) for Version Country,State code,Version disttrict and Version statement for Container object.Erros should be reported in Error.html file name."
		+"<br>ALFDEPLOY-4051Verify When user provide invalid values(other than US region),State code,Version disttrict and Version statement for asset object.Erros should be reported in Error.html file name."
		+"<br>ALFDEPLOY-4051Verify User Can able to import multiple invalid values for Version Country,State code,Version disttrict and Version statement using Pipe delimiter for all the collection object then error message should be reported in Error.html filoe name."
	);

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		try{
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";		
				// Log in Pearson Schools project
						functionalLibrary.loginAsValidUser(signOnPage);
						
				//Create site
						
						UIHelper.waitForLong(driver);
				homePageObj.navigateToSitesTab();
				String siteNameValue =  dataTable.getData("Sites", "SiteName");				
				//sitesPage.siteFinder("AutoRefModCollectionSite18841217223603");
				sitesPage.createSite(siteNameValue, "Yes");
				
				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				
				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				UIHelper.waitForPageToLoad(driver);
				myFiles.openCreatedFolder("Course Plan");
				UIHelper.waitForPageToLoad(driver);
				// upload course plan
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				//collectionPg.verifyImportProcessProgress(fileName);
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);
				//UIHelper.pageRefresh(driver);
				
				sitesPage.enterIntoDocumentLibrary();
				
				//go to course object 1
				Date date = new Date();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				
				
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));

				collectionPg.clickOnMoreSetting(fileName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName, "View Error Report");
				new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,errorfile);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);
				
				collectionPg.errormessage("xxx, Course Object 1","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, Course Object 1].");				
				collectionPg.errormessage("xxx, Content Object 1","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, Content Object 1].");			
				collectionPg.errormessage("xxx, Sequence Object 1","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, Sequence Object 1].");				
				collectionPg.errormessage("xxx, Learning Bundle 1","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, Learning Bundle 1].");				
				collectionPg.errormessage("xxx, CompositeTitle","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, CompositeTitle].");				
				collectionPg.errormessage("xxx, AssetTitle","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, AssetTitle].");				
				collectionPg.errormessage("xxx, Container Object 5","Property [Version Country] of type [{http://cms.pearsonnals.com/model/content/1.0}versionCountry] has invalid values [zzz, xxx, Container Object 5].");				
				collectionPg.errormessage("zzz, Course Object 1","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, Course Object 1].");				
				collectionPg.errormessage("zzz, Content Object 1","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, Content Object 1].");				
				collectionPg.errormessage("zzz, Sequence Object 1","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, Sequence Object 1].");				
				collectionPg.errormessage("zzz, Learning Bundle 1","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, Learning Bundle 1].");				
				collectionPg.errormessage("zzz, CompositeTitle","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, CompositeTitle].");				
				collectionPg.errormessage("zzz, AssetTitle","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, AssetTitle].");				
				collectionPg.errormessage("zzz, Container Object 5","Property [State Code] of type [{http://cms.pearsonnals.com/model/content/1.0}versionState] has invalid values [zzz, Container Object 5].");
				
				
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
