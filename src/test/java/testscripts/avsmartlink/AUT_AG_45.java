package testscripts.avsmartlink;

import java.io.File;
import java.util.ArrayList;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAVSmartLinkPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_45 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void metrodigiCsvFieldCheck() {
		testParameters.setCurrentTestDescription(
				"1.ALFDEPLOY-5852_Verify 2 new fields Width and Height are added in Metrodigi multi smartlink csv template"
				+"<br>2.ALFDEPLOY-5852_Verify success message is displayed in Metrodigi multi smart link csv template when numeric values are entered in Width and Height columns of csv template."
				+"<br>3.ALFDEPLOY-5852_Verify error message is displayed in Metrodigi multi smart link csv template when decimal values are entered in Width and Height columns of csv template."	
				+"<br>4.ALFDEPLOY-5852_Verify error message is displayed in Metrodigi multi smart link csv template when other than numeric values like characters and special characters are entered in Width and Height columns of csv template.");
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
		AlfrescoAVSmartLinkPageTest avsmartPgTest=new AlfrescoAVSmartLinkPageTest(scriptHelper);
		FileUtil  fileUtil=new FileUtil();
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String csvName = dataTable.getData("MyFiles", "Version");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String[] givenLinks=dataTable.getData("MyFiles", "CreateFolder").split(",");
		
        String titleColumnInCsv=dataTable.getData("Document_Details", "FilePath").split(",")[0];
        int title=Integer.parseInt(titleColumnInCsv);
        String statusColumnInCsv=dataTable.getData("Document_Details", "FilePath").split(",")[1];
        int status=Integer.parseInt(statusColumnInCsv);
        String[] expStatus=dataTable.getData("MyFiles", "BrowseActionName").split(",");
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		ArrayList<String> createdSmartLinkList=null;
		

		functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.documentdetails(fileName);
		docDetailsPageObj.performUnzipDocAction(extractTo);
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(folderName);
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(folderName);
		createdSmartLinkList=docLibPg.getFolderDetails();
		
		ArrayList<String> smartLinkList = new ArrayList<String>();
		smartLinkList = myFiles.getFileNames(smartlinks);

		if(UIHelper.compareTwoSimilarLists(createdSmartLinkList,smartLinkList)) {
			report.updateTestLog("Verify MultiSmartLink created as expected", "MulitSmartLinks created as expected", Status.PASS);
		}else {
			report.updateTestLog("Verify MultiSmartLink created as expected", "MulitSmartLinks not created as expected <br> Expected: "+smartLinkList+"<br> Links created:"+createdSmartLinkList, Status.FAIL);
		}
		
		fileUtil.deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		sitesPage.commonMethodForPerformBrowseOption(csvName, "Download");
		fileUtil.waitUptoFileDownloadComplete(downloadFilePath, csvName);
		
		String csvFile=downloadFilePath + "/" + csvName;
		downloadedFile = new File(csvFile);
		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			UIHelper.waitFor(driver);
			avsmartPgTest.verifyFieldPresenfInCSVHeader(csvFile, "Height");
			avsmartPgTest.verifyFieldPresenfInCSVHeader(csvFile, "Width");
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFileWithoutHeader(csvFile);
			avsmartPgTest.verifyMultiSmartLinkStatus(givenLinks, csvFileRowDataList, expStatus, title, status);
		
		} else {

			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}
	
	}

	@Override
	public void tearDown() {

	}
}
