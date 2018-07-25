package testscripts.release184;

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
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
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
public class ALFDEPLOY_2747_TC001_2_3_4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runALFDEPLOY_2747_TC001_2_3_4() {
		testParameters
				.setCurrentTestDescription("1)The Content Type and File Type property value of 'Sequence' has a capital 'S'.     2)The Media Type property value of 'Lesson' has a capital 'L'.     3}The Course Objects Media Type value of 'Tier' has a capital 'T'.     4}The Player Target property value of 'realize' has a lower case 'r'.");

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
		functionalLibrary.loginAsValidUser(signOnPage);

		// create random site
		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// enter collection UI
		sitesPage.enterIntoDocumentLibrary();
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		collectionPg
				.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// navigate back to program in collection UI
		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnEditCollectionButton();

		// click generate realize CSV on course and CMT content CSV
		String moreSettingsOptionName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String moreSettingsOptionName2 = dataTable.getData("MyFiles",
				"MoreSettingsOption2");
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName2);

		// navigate to generated csv file location and download
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Data Exports");
		myFiles.openCreatedFolder("Realize Export");
		myFiles.openCreatedFolder("Course");
		myFiles.openCreatedFolder(myFiles.getUploadedFileOrFolderTitle().get(0));

		// check download on generated realize csv file
		boolean downloadFlag = false;
		boolean downloadFlag2 = false;

		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		ArrayList<String> csvFileRowDataList2 = null;

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		ArrayList<String> generatedFileList = new ArrayList<String>();
		generatedFileList = myFiles.getUploadedFileOrFolderTitle();

		for (String file : generatedFileList) {

			if (file.contains("Course")) {

				sitesPage.commonMethodForPerformBrowseOption(file, "Download");

				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath,
						file);
				downloadedFile = new File(downloadFilePath + "/" + file);
				if (downloadedFile.exists()
						&& !file.equalsIgnoreCase("File Not Found")) {

					report.updateTestLog("Verify download file", "File: "
							+ file + " downloaded sucessfully", Status.PASS);
					downloadFlag = true;
					csvFileRowDataList = new CSVUtil()
							.readLinesOfDataFromCSVFile(downloadFilePath + "/"
									+ file);

				} else {

					report.updateTestLog("Verify download file", "File: "
							+ file + " failed to download", Status.FAIL);
				}
			} else {

				System.out.println(file + "Not expected File");
			}
			
			if (file.contains("Standards")) {

				sitesPage.commonMethodForPerformBrowseOption(file, "Download");

				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath,
						file);
				downloadedFile = new File(downloadFilePath + "/" + file);
				if (downloadedFile.exists()
						&& !file.equalsIgnoreCase("File Not Found")) {

					report.updateTestLog("Verify download file", "File: "
							+ file + " downloaded sucessfully", Status.PASS);
					downloadFlag2 = true;
					csvFileRowDataList2 = new CSVUtil()
							.readLinesOfDataFromCSVFile(downloadFilePath + "/"
									+ file);

				} else {

					report.updateTestLog("Verify download file", "File: "
							+ file + " failed to download", Status.FAIL);
				}
			} else {

				System.out.println(file + "Not expected File");
			}
		}

		if (downloadFlag) {
		
		

			for (String csvRow : csvFileRowDataList) {
				String splitedRow[] = null;
				splitedRow = csvRow.replace("\"", "").replace("", " ")
						.split(",");	 
		
			if(splitedRow[0].replace(" ", "").contains("true") && splitedRow[13].contains(" ") ){	report.updateTestLog("Root: "+ splitedRow[0].replace(" ", "") ,"PD: " +splitedRow[13] , Status.PASS);}else 	if(splitedRow[0].replace(" ", "").contains("true") && !splitedRow[13].contains(" ") ){report.updateTestLog("Root: "+ splitedRow[0].replace(" ", "") ,"PD: " +splitedRow[13] , Status.FAIL); }
			if(splitedRow[22].replace(" ", "").contains("Sequence")  && splitedRow[22].charAt(1) =='S'){ 	report.updateTestLog("Content Type: "+ splitedRow[22].replace(" ", "") ,"Has " +splitedRow[22].charAt(1) + " as first letter", Status.PASS);}else if(splitedRow[22].replace(" ", "").contains("Sequence")  && splitedRow[22].charAt(1) !='S'){report.updateTestLog("Content Type: "+ splitedRow[22].replace(" ", "") ,"Has " +splitedRow[22].charAt(1) + " as first letter", Status.FAIL); } 
			if(splitedRow[23].replace(" ", "").contains("Sequence")  && splitedRow[23].charAt(1) =='S'){ 	report.updateTestLog("File Type: "+ splitedRow[23].replace(" ", "") ,"Has " +splitedRow[23].charAt(1) + " as first letter", Status.PASS);}else if(splitedRow[23].replace(" ", "").contains("Sequence")  && splitedRow[23].charAt(1) !='S'){report.updateTestLog("File Type: "+ splitedRow[23].replace(" ", "") ,"Has " +splitedRow[23].charAt(1) + " as first letter", Status.FAIL); } 
			if(splitedRow[24].replace(" ", "").contains("Lesson")  && splitedRow[24].charAt(1) =='L'){ 	report.updateTestLog("Media Type: "+ splitedRow[24].replace(" ", "") ,"Has " +splitedRow[24].charAt(1) + " as first letter", Status.PASS);}else	if(splitedRow[24].replace(" ", "").contains("Lesson")  && splitedRow[24].charAt(1) !='L'){ report.updateTestLog("Media Type: "+ splitedRow[24].replace(" ", "") ,"Has " +splitedRow[24].charAt(1) + " as first letter", Status.FAIL);} 
			if(splitedRow[24].replace(" ", "").contains("Tier")  && splitedRow[24].charAt(1) =='T'){ 	report.updateTestLog("Media Type: "+ splitedRow[24].replace(" ", "") ,"Has " +splitedRow[24].charAt(1) + " as first letter", Status.PASS);}else if(splitedRow[24].replace(" ", "").contains("Tier")  && splitedRow[24].charAt(1) !='T'){report.updateTestLog("Media Type: "+ splitedRow[24].replace(" ", "") ,"Has " +splitedRow[24].charAt(1) + " as first letter", Status.FAIL); } 
			if(splitedRow[80].replace(" ", "").contains("realize")  && splitedRow[80].charAt(1) =='r'){ 	report.updateTestLog("player Target: "+ splitedRow[80].replace(" ", "") ,"Has " +splitedRow[80].charAt(1) + " as first letter", Status.PASS);}else 	if(splitedRow[80].replace(" ", "").contains("realize")  && splitedRow[80].charAt(1) !='r'){ report.updateTestLog("player Target: "+ splitedRow[80].replace(" ", "") ,"Has " +splitedRow[80].charAt(1) + " as first letter", Status.FAIL);} 
			if(splitedRow[6].replace(" ", "").contains("Social Studies") && splitedRow[9].replace(" ", "").contains("Humanities")  ){ 	report.updateTestLog("Pearsonbu: "+ splitedRow[9].replace(" ", "") ,"for Discipline : Social Studies", Status.PASS);}else if(splitedRow[6].replace(" ", "").contains("Social Studies") && !splitedRow[9].replace(" ", "").contains("Humanities") ) 	{ report.updateTestLog("Pearsonbu: "+ splitedRow[9].replace(" ", "") ,"for Discipline : Social Studies", Status.FAIL);} 

			}

		}
		
		if (downloadFlag2) {
			for (String csvRow : csvFileRowDataList2) {				
					String splitedRow[] = null;
					splitedRow = csvRow.replace("\"", "").replace("", " ")
							.split(",");									
			
				if(splitedRow[2].replace(" ", "").contains("Parent") && splitedRow[3].replace(" ", "").contains("state_code") && splitedRow[4].replace(" ", "").contains("statedescription")&& splitedRow[5].replace(" ", "").contains("Subject")){	report.updateTestLog("Between "+ splitedRow[2].replace(" ", "")+" and "+splitedRow[5].replace(" ", "") ," the standard csv have " +splitedRow[3].replace(" ", "")+" and "+splitedRow[4].replace(" ", "") , Status.PASS); break; }else 	if(!splitedRow[2].replace(" ", "").contains("Parent") && !splitedRow[3].contains("state_code") && !splitedRow[4].contains("state description")&& !splitedRow[5].contains("Subject")){report.updateTestLog("Between "+ splitedRow[2].replace(" ", "")+" and "+splitedRow[5].replace(" ", "") ," the standard csv have " +splitedRow[3].replace(" ", "")+" and "+splitedRow[4].replace(" ", "") , Status.PASS); }

				
			}
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}