package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.util.ArrayList;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_052 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"1.Verify new smart links are created in folders based on the spreadsheet values on selecting 'Create Multi Smart Link' option for the valid sl_3rd_party.csv file<br>"
				+"2.Validate the smart link is updated correctly when Title,external URL,JSON string fields are edited via edit properties for 3rd party interactive link.");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String csvfile = dataTable.getData("MyFiles", "Version");
		String newcsvfile = dataTable.getData("MyFiles", "CreateFolder");
		String action = dataTable.getData("MyFiles", "BrowseActionName");
	
		
		String uploadFilePath = properties.getProperty("DefaultUploadPath");
		

		File downloadedFile = null;
		File newdownloadedFile = null;
	

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.documentdetails(fileName);

		docDetailsPageObj.performUnzipDocAction(extractTo);
		sitesPage.enterIntoDocumentLibrary();

		
		ArrayList<String> smartLinkcsv = new ArrayList<String>();
		smartLinkcsv = myFiles.getFileNames(csvfile);
		
		ArrayList<String> newsmartLinkcsv = new ArrayList<String>();
		newsmartLinkcsv = myFiles.getFileNames(newcsvfile);
		
		for (int i = 0; i < newsmartLinkcsv.size(); i++) {
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
					smartLinkcsv.get(i));
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
					newsmartLinkcsv.get(i));
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
					"Test"+i+".csv");
			sitesPage.documentdetails(folderName);

			sitesPage.commonMethodForPerformBrowseOption(smartLinkcsv.get(i),
					action);

			new FileUtil().waitUptoFileDownloadComplete(downloadFilePath,
					smartLinkcsv.get(i));
			downloadedFile = new File(downloadFilePath + "/"
					+ smartLinkcsv.get(i));
			
			newdownloadedFile = new File(downloadFilePath + "/"
					+ newsmartLinkcsv.get(i));

			if (downloadedFile.exists()
					&& !smartLinkcsv.get(i).equalsIgnoreCase("File Not Found")) {
				new FileUtil().renamefile(downloadFilePath,
						smartLinkcsv.get(i), newsmartLinkcsv.get(i));

				myFiles.commonMethodForUploadFiles(uploadFilePath,
						newsmartLinkcsv.get(i));

				sitesPage.clickOnMoreSetting(newsmartLinkcsv.get(i));
				docLibPg.commonMethodForClickOnMoreSettingsOption(
						newsmartLinkcsv.get(i), moreSettingsOption);

				UIHelper.waitForPageToLoad(driver);
				sitesPage.enterIntoDocumentLibraryWithoutReport();
				
				if (newdownloadedFile.exists()
						&& !newsmartLinkcsv.get(i).equalsIgnoreCase("File Not Found")) {
					
					new FileUtil().renamefile(downloadFilePath,
							newsmartLinkcsv.get(i),"Test"+i+".csv" );
					
					UIHelper.waitForPageToLoad(driver);
					
					myFiles.commonMethodForUploadFiles(uploadFilePath,
							"Test"+i+".csv");
					UIHelper.waitForPageToLoad(driver);
					sitesPage.clickOnMoreSetting("Test"+i+".csv");
					
					sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase("Test"+i+".csv", moreSettingsOption);
					
					
				}

			}

		}
		
		smartLinkcsv.clear();
		
		smartLinkcsv =  myFiles.getFileNames(smartlinks);
		sitesPage.documentdetails(folderName);
		for (String smartlink : smartLinkcsv) {
			myFilesTestObj.verifyUploadedFile(smartlink, "");
		}
		


	}

	@Override
	public void tearDown() {

	}
}
