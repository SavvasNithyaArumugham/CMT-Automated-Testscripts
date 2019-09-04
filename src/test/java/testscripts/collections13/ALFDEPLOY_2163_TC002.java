package testscripts.collections13;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2163_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Confirm there is no error related to skill alignments on importing a updated a print plan CSV with Asterisk-Marked as 'Key' for skills alignments values. ");
		
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
			//  login
			functionalLibrary.loginAsValidUser(signOnPage);
			
			// goto site > document lib
			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites("autorefercollectionsite1150719154633");
			/*String siteNameValue = dataTable.getData("Sites", "SiteName");		
			sitesPage.createSite(siteNameValue, "Yes");
	//	sitesPage.openSiteFromRecentSites("autorefercollectionsite1150719154633");
			sitesPage.enterIntoDocumentLibrary();
			
			// go to Course plan
			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");
					
			// upload course plan
									
			String filePath = dataTable.getData("Sites", "InviteUserName");
			String fileName = dataTable.getData("Sites", "Role");
			
			collectionPg.uploadFileInCollectionSite(filePath, fileName);
			sitesPage.enterIntoDocumentLibrary();
			String values[] = dataTable.getData("Sites", "CreateSiteLabelNames").split(",");
			myFiles.openCreatedFolder(values[0]);
			myFiles.openCreatedFolder(values[1]);
			collectionPg.clickOnEditCollectionButton();
			UIHelper.waitFor(driver);
		
		String moreSettingsOption[] = dataTable.getData("Sites", "TargetFolder").split(",");
		collectionPg.clickOnMoreSetting(values[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(values[2],moreSettingsOption[0]);
		UIHelper.waitFor(driver);
		
		String okButtonXpath = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-ok-button-button']";
		UIHelper.click(driver,okButtonXpath);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();		
		myFiles.openCreatedFolder("Data Exports");
		myFiles.openCreatedFolder("Realize Export");
		myFiles.openCreatedFolder("Batch 4 Test Course");
		
		String[] filePath1 = dataTable.getData("Sites", "TargetSiteName").split("/");
		for (String path : filePath1) {
			sitesPage.documentdetails(path);
			}
					
		// Navigate to generated csv file path and check whether
				Date date = new Date();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				sitesPage.documentdetails(currentDate);

				// CSVfile presence and filename
				String objectName = dataTable.getData("Sites", "FileName");
				String fileName1 =objectName + "-" + currentDate;
				String filename2 = mediaTransPage.RetreiveFilename(fileName1);
				System.out.println("JSON file name :" +filename2 );
			
				sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");
				UIHelper.waitFor(driver);
				
				String downloadFilePath = properties.getProperty("DefaultDownloadPath");
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				File downloadedFile = null;
				downloadedFile = new File(downloadFilePath + "/" + filename2);
							
				UIHelper.waitFor(driver);
				report.updateTestLog("Verify Course json file is available in the Repository>", "CCP Test Course 4 - ALFDEPLOY-3440.json" + "is displayed", Status.PASS);
		

				String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
				System.out.println("downloadedCSVFileANmeWithPath" +downloadedCSVFileANmeWithPath);
				
				//Open the CSV and Update the skills column property with  skill values by mentioning *(Key) astrisk before the skill values.
				String input1 = dataTable.getData("Sites", "SelectedItemsMenuOption");
				try {
					CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input1 ,5,41);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
				
				sitesPage.enterIntoDocumentLibrary();

				//go to programs-program
				
				String values[] = dataTable.getData("Sites", "CreateSiteLabelNames").split(",");
				String moreSettingsOption[] = dataTable.getData("Sites", "TargetFolder").split(",");
				myFiles.openCreatedFolder(values[0]);
				myFiles.openCreatedFolder(values[1]);
				collectionPg.clickOnEditCollectionButton();
				collectionPg.openCollectionObject(values[2]);
				/*collectionPg.clickOnMoreSetting(values[3]);	
				collectionPg.commonMethodForClickOnMoreSettingsOption(values[3], moreSettingsOption[2]);
				collectionPg.addAlignmentinObjects();*/
				
				UIHelper.waitFor(driver);	
				UIHelper.waitFor(driver);
				
				collectionPg.clickOnMouseOverMenu(values[3],"View Details");
				
				collectionPg.verifyIncomingHasAlignmentViewDetailsPg("ELA.CP.1");
				
	}
	
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}