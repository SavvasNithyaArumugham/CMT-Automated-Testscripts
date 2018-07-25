package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
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

public class AUT_AG_022 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"1.Verify user can add Image, Audio, Video, External Website, 3rd Party Interactive, Metrodigi, MD Pop up, PDF links in CSV all at once and user is able to create separate smart link object for each types via multi smart link<br>"
				+"2. Verify the default ‘Smart Link’ icon is used when no image preview is included <br>"
				+"3. Verify the smart link object is not created when the row value is missed in between the rows filled in the csv.<br>"
				+"4. Verify smart link object is not created when image preview field is filled with multiple image previews<br>"
				+"5. Verify the same success status in csv is retained when trying to create multi smart link for the same csv file for which the smart link object is already created"
				+"6. Verify user can directly upload the spreadsheet into Alfresco and is able to create multi smart link for the valid rows.<br>"
				+"7. Verify user can provide image preview filename field as 'filename.png' when we have preview images in the same location where we have the spreadsheet."
				+"8. Verify user can provide image preview filename field as 'sites/test/documentLibrary/testfolder/imagename.jpg' when we have preview images in different site of same repository"
				+"9. Verify if Images are already in Alfresco, then a site URL path is provided in the Image Preview field of spreadsheet.");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String site = dataTable.getData("Sites", "TargetSiteName");
		String csvName = dataTable.getData("MyFiles", "Version");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String smartlinkmultiimg = dataTable.getData("MyFiles", "RelationshipName");
		String smartlinkimage = dataTable.getData("MyFiles", "Sort Options");
		String updateimg = dataTable.getData("MyFiles", "CreateChildFolder");
		String siteimg= dataTable.getData("MyFiles", "PopUpMsg");
		String moreSettingsOption2 = dataTable.getData("MyFiles", "AccessToken");
		
		String sameSitelocation = "sites/"+siteName.toLowerCase()+"/documentlibrary/Imageform1.jpg";
		String diffSitelocation = "sites/"+site.toLowerCase()+"/documentlibrary/Imageform1.jpg";

		
		
		
		
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		
	
		String uploadPath = System.getProperty("user.dir") + filePath + csvName;
		
		try {
			CSVUtil.updateCSV(uploadPath, sameSitelocation, 11, 2);
			CSVUtil.updateCSV(uploadPath, diffSitelocation, 12, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(site);  
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, siteimg);

		sitesPage.siteFinder(siteName);  

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, siteimg);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		ArrayList<String> smartLinkList = new ArrayList<String>();
		smartLinkList = myFiles.getFileNames(smartlinks);

		for (String name : smartLinkList) {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			sitesPage.documentdetails(name);
			myFilesTestObj.verifyUploadedFile(name, "");
			//sitesPage.enterIntoDocumentLibraryWithoutReport();
			driver.navigate().back();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
		}
		
		sitesPage.documentdetails(smartlinkimage);
		String finalwebthumbnail = myFiles.webthumbnail.replace("CRAFT", smartlinkimage);
	
		UIHelper.waitForVisibilityOfEleByXpath(driver, finalwebthumbnail);
		
		if(UIHelper.findAnElementbyXpath(driver, finalwebthumbnail).getAttribute("src").contains("WebResource.png"))
		{
	
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalwebthumbnail));
			UIHelper.highlightElement(driver, finalwebthumbnail);
			
			report.updateTestLog("Verify Web resource thumbnail image",
					"Web resource thumbnail image based on domain of URL", Status.PASS);
		}else {
		
			report.updateTestLog("Verify Web resource thumbnail image",
					"Web resource thumbnail image not based on domain of URL", Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		sitesPage.commonMethodForPerformBrowseOption(csvName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
		downloadedFile = new File(downloadFilePath + "/" + csvName);
		
		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + csvName;
		
		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + csvName);

			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				if (splitedRow[0].replace("\"", "").contains(smartlinkmultiimg)
						&& splitedRow[7].replace("\"", "").contains("Error-Image is not available.Reverted")) {
					report.updateTestLog("Verify smart link object is not created when image preview field is filled with multiple image previews",
							"Multi smart Link  when image preview field filled with multiple image previews are not created with excel upload successfully and status in csv is updated. Status: " + splitedRow[8], Status.PASS);
					continue;
				}
				
				if (splitedRow[7].replace("\"", "").contains("Error - Title and External URL should be mandatory field")) {
					report.updateTestLog("Verify smart link object is not when empty place is given in CSV file",
							"Multi smart Link  when empty place is given in CSV file are not created with excel upload successfully and status in csv is updated. Status: " + splitedRow[7], Status.PASS);
				//	continue;
				}
			}
		} else {
			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}
		
		try {
			CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, updateimg, 9, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption2);
	
		
		docLibPg.uploadNewVersionFileInDocLibPage(csvName, downloadFilePath, "Test Comments");
		
		myFiles.uploadFileInMyFilesPage(filePath, updateimg);
		
		sitesPage.clickOnMoreSetting(csvName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(csvName, moreSettingsOption);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, csvName);
		sitesPage.commonMethodForPerformBrowseOption(csvName, "Download");
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, csvName);
		downloadedFile = new File(downloadFilePath + "/" + csvName);
	
		if (downloadedFile.exists() && !csvName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + csvName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + csvName);
			
			System.out.println(csvFileRowDataList);

			for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");

				if (splitedRow[0].replace("\"", "").equals(smartlinkmultiimg)
						&& splitedRow[7].replace("\"", "").contains("Success")) {
					report.updateTestLog("Verify ",
							"Multi smart Link  . Status: " + splitedRow[7], Status.PASS);
				//	continue;
				}
				
				if (splitedRow[0].replace("\"", "").contains(smartlinkimage)
						&& splitedRow[8].replace("\"", "").contains("Success")) {
					report.updateTestLog("Verify the same success status in csv is retained when trying to create multi smart link for the same csv file for which the smart link object is already created",
							"same success status in csv is retained when trying to create multi smart link for the same csv file for which the smart link object is already created. Status: " + splitedRow[8], Status.PASS);
				//	continue;
				}
				
			}
		} else {

			report.updateTestLog("Verify download file", "File: " + csvName + " failed to download", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {

	}
}
