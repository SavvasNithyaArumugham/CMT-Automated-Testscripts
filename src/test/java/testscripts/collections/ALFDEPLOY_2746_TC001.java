package testscripts.collections;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author ASHOK
 */
public class ALFDEPLOY_2746_TC001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_055() {
		testParameters
				.setCurrentTestDescription("Following a generation of the Realize CSVs can download the course plan and see that the course object (where ROOT=TRUE) has a blank value under parent descriptor");

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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		// login
		System.out.println("Step  1");
		functionalLibrary.loginAsValidUser(signOnPage);

		// create random site
		System.out.println("Step  2");
		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// enter collection UI
		System.out.println("Step  3");
		sitesPage.enterIntoDocumentLibrary();
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		System.out.println("Step  4");
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
		System.out.println("Step  5");
		// String createObjectData =
		// dataTable.getData("MyFiles","CollectionObjectBasicData");
		// collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// navigate back to program in collection UI
		System.out.println("Step  6");

		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnEditCollectionButton();

		// click generate realize CSV on course
		System.out.println("Step  7");
		String moreSettingsOptionName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		// String collectionObjectName = dataTable.getData("MyFiles",
		// "CreateMenuItemsForCollection");
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName);
		
		UIHelper.waitFor(driver);
		collectionPg.clickonrealizebox();
		// navigate to generated csv file location
		System.out.println("Step  8");
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Exports");
		myFiles.openCreatedFolder("Realize Export");
		myFiles.openCreatedFolder("Course");
		
		myFiles.openCreatedFolder(myFiles.getUploadedFileOrFolderTitle().get(0));

		// click download on generated realize csv file
		System.out.println("Step  9");
		ArrayList<String> generatedFileList = new ArrayList<String>();
		generatedFileList = myFiles.getUploadedFileOrFolderTitle();
		for (String file : generatedFileList) {
			if (file.contains("Course")) {
				sitesPage.commonMethodForPerformBrowseOption(file, "Download");
				String downloadFilePath = properties
						.getProperty("DefaultDownloadPath");
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath,
						file);
				File downloadedFile = new File(downloadFilePath + "/" + file);
				if (downloadedFile.exists()
						&& !file.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: "
							+ file + " downloaded sucessfully", Status.PASS);

					ArrayList<String> csvFileRowDataList = new CSVUtil()
							.readLinesOfDataFromCSVFile(downloadFilePath + "/"
									+ file);
					int count = 0;boolean flag = false;
					// int fieldPosition=0;
					// int incrementer=0;
					for (String csvRow : csvFileRowDataList) {
						System.out.println(csvFileRowDataList.get(count));
						String[] splittedCSVRow = csvRow.split(",");
						for (String splitString : splittedCSVRow) {
							System.out.println(splitString);

							if (count != 0) {
								ArrayList<String> cellValue = new ArrayList<>();
								cellValue.add(splitString);
								if (cellValue.get(0) == "true") {
									flag =true;
									
								} else {
									flag=false;
									
								}
							}

						}
						count++;
					}
					
					if (flag) {
						report.updateTestLog("  ", " ", Status.PASS);
					} else {
						report.updateTestLog("  ", " ", Status.FAIL);

					}

				} else {
					report.updateTestLog("Verify download file", "File: "
							+ file + " failed to download", Status.FAIL);
				}
			} else {
				System.out.println("course realize csv File is not found!!!! ");
			}
		}



	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}