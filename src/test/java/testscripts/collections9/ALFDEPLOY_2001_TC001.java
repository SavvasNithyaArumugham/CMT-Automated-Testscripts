package testscripts.collections9;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2001_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1.Confirm that the Linked Asset column display the Alfresco CMIS URL of the 1st direct child cpnals:asset file of the node.");
		
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

	/* (non-Javadoc)
	 * @see com.pearson.automation.utils.TestCase#executeTest()
	 */
	/* (non-Javadoc)
	 * @see com.pearson.automation.utils.TestCase#executeTest()
	 */
	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
	  String okBtnXpathForPrintPlanCSV = ".//*[contains(@id,'default-generate-manifest-ok-button')]";
		
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		// Upload image file in Assets 
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[2]);
		UIHelper.waitFor(driver);
		//Get node id from current url
		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Image");
		myFiles.openCreatedFolder("a");
		myFiles.openAFile(fileName[1]); 
		String currentURL  = driver.getCurrentUrl();
		String imageNodeIdFromURL=currentURL.substring(137, 173);
		System.out.println("Image ID : " +imageNodeIdFromURL ); 
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Assets");
		myFiles.openCreatedFolder("Image");
		myFiles.openCreatedFolder("a");
		myFiles.openAFile(fileName[2]); 
		String currentURL1  = driver.getCurrentUrl();
		String imageNodeIdFromURL1=currentURL1.substring(137, 173);
		System.out.println("Image ID 1 : " +imageNodeIdFromURL1 ); 
		
		// go to Course plan
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);

		
		// verify uploaded object in the collection view
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
	
		collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
		
		collectionPg.clickOnMouseOverMenu(folderNames[3], "Child references");
		collectionPg.addImageChildReference("Assets","Image","a");
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
		// Added for clicking Ok button in print plan CSV 
		UIHelper.click(driver, okBtnXpathForPrintPlanCSV);
		sitesPage.enterIntoDocumentLibrary();
							
		for (String path : filePath1) {
			sitesPage.documentdetails(path);
		}
	
		// Navigate to generated csv file path 
		
		  Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 = folderNames[2] + "-" + currentDate;
		String filename2 = collectionPg.CSVFilename(fileName1);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,filename2);
		sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
				
		if (downloadedFile.exists()
				&& !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2
					+ " CSV file downloaded sucessfully", Status.PASS);
		}

		String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
		
		csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFileWithoutHeader(downloadFilePath + "/" + filename2);	
			
		try {
			String imagenodeIDFromCSV = CSVUtil.readDataInCell(downloadedCSVFileANmeWithPath,2,27);
						
			if (imagenodeIDFromCSV.contains(imageNodeIdFromURL))
			{
				report.updateTestLog("Verify node ID in the CSV with the node id from URL", "Node ID present " + imagenodeIDFromCSV + "Linked Asset column display sucessfully", Status.PASS);
			}else
			{
				report.updateTestLog("Verify node ID in the CSV with the node id from URL", "Node not ID present " + imagenodeIDFromCSV + "Linked Asset column not display ", Status.FAIL);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
							
			}
		
		
		
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
