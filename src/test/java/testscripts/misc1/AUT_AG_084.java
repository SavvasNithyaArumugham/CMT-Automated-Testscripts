package testscripts.misc1;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_084 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_074() {
		testParameters.setCurrentTestDescription(
				"1. Verify that user is not able to view the content on Browser When clicked on 'Edit Offline' from the document action when file is in the the document library"
						+ "<br>2. Verify that user is not able to view  the content in Browser When clicked on 'Edit Offline' from the document action when file is opend through search result");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToSitesTab();

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName, "");

		myFiles.openUploadedOrCreatedFile(fileName, "");

		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		String parentWindowId = docDetailsPg.getParentWindowDriverID();

		String docActionVal = dataTable.getData("Document_Details", "DocumentActionName");

		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyDocAction(docActionVal);

		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		String finalFileName;
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0];
			finalFileName = part1 + "." + splitVal[1];
		} else {
			finalFileName = fileName;
		}

		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, finalFileName);

		docDetailsPg.commonMethodForPerformDocAction(docActionVal);

		new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, finalFileName);

		docDetailsPageTest.verifyBrowserViewOfDocAfterEditOffline(parentWindowId);

		sitesPage.enterIntoDocumentLibrary();
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");

		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		searchObj.performSearch();

		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		searchTestObj.verifyUploadedFileInSearchResults();

		searchObj.commonMethodForOpenSearchResultsItem(fileName);

		String parentWindowId2 = docDetailsPg.getParentWindowDriverID();

		docDetailsPageTest.verifyDocAction(docActionVal);

		String finalFileName2;
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0];
			finalFileName2 = part1 + "." + splitVal[1];
		} else {
			finalFileName2 = fileName;
		}

		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath, finalFileName2);

		docDetailsPg.commonMethodForPerformDocAction(docActionVal);

		new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, finalFileName2);

		docDetailsPageTest.verifyBrowserViewOfDocAfterEditOffline(parentWindowId2);
	}

	@Override
	public void tearDown() {

	}

}
