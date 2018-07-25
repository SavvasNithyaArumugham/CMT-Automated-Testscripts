package testscripts.misc1;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Naresh Kumar Salla
 */
public class AUT_AG_082 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_072() {
		testParameters.setCurrentTestDescription("1. Verify that user is able to checkout multiple file at once"
				+ "<br>2. Verify that user is able to checking multiple file at once"
				+ "<br>3. Verify that user is able to select Minor/Major version while check-in multiple files"
				+ "<br>4. Verify that only selected items are checked In while check-in multiple files"
				+ "<br>5. Verify that user is able to checkout multiple file at once on folder level");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);

		sitesPage.enterIntoDocumentLibrary();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		UIHelper.waitFor(driver);
		docLibPage.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		new FileUtil().deleteIfAllFilesExistsInDownloadPath(fileDownloadPath, fileName);

		ArrayList<String> menuOptionList = new ArrayList<String>();
		String selectedItemsMenuOption = dataTable.getData("Sites", "SelectedItemsMenuOption");
		StringTokenizer tokenMenuOption = new StringTokenizer(selectedItemsMenuOption, ",");
		while (tokenMenuOption.hasMoreElements()) {
			menuOptionList.add(tokenMenuOption.nextToken());
		}

		docLibPage.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPage.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(0));

		new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath, fileName);

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		docLibPage.selectDocumentLibItems("None");
		//docLibPage.selectAllFilesAndFolders();
		int index = 0;
		ArrayList<String> fileNameList = new ArrayList<String>();
		StringTokenizer tokenFileName = new StringTokenizer(fileName, ",");
		while (tokenFileName.hasMoreElements()) {

			if (index > 2) {
				break;
			}
			String newVersionfileName = tokenFileName.nextToken();
			docLibPage.selectAllItems(newVersionfileName);
			sitesPage.clickOnSelectedItems();
			docLibPage.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(1));
			fileNameList.add(newVersionfileName);
			newVersionfileName = newVersionfileName.replace(".", ".");
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPage.uploadNewVersionFileInDocumentDetailsPage(newVersionfileName);
			UIHelper.waitFor(driver);

			index++;
		}

		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docLibPage.openAFile(fileNameList.get(0));
		docDetailsPageTest.verifyUploadedNewVersionFile();

		sitesPage.enterIntoDocumentLibrary();

		docLibPage.openAFile(fileNameList.get(1));
		docDetailsPageTest.verifyUploadedNewVersionFile();

		sitesPage.enterIntoDocumentLibrary();

		docLibPage.openAFile(fileNameList.get(2));
		docDetailsPageTest.verifyUploadedNewVersionFile();

		//Verify that user is able to checkout multiple file at once on folder level
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(folderDetails);

		docLibPage.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPage.verifyFolderCheckOutMessage(menuOptionList.get(0));
	}

	@Override
	public void tearDown() {

	}

}