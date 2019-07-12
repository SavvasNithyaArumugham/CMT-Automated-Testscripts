package testscripts.collections13;

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

public class ALFDEPLOY_2118_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Check Publication Date: New property for Content Objects");
		
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
				//sitesPage.openSiteFromRecentSites("AutoReferCollectionSite1110719150639");
				
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				String siteName = sitesPage.getCreatedSiteName();
				sitesPage.enterIntoDocumentLibrary();
				
				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				String filePath = dataTable.getData("Sites", "InviteUserName");
				String fileName = dataTable.getData("Sites", "Role");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);

				sitesPage.enterIntoDocumentLibrary();
				String folderNames[] = dataTable.getData("Sites", "CreateSiteLabelNames").split(",");
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitFor(driver);
				
				collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
				collectionPg.clickOnMouseOverMenu("Test Object - Discussion Prompt", "Edit Properties");
				String allProperties = ".//a[contains(text(),'All Properties...')]";
				UIHelper.click(driver, allProperties);
				UIHelper.waitForPageToLoad(driver);
			

				boolean theme  = collectionPg.isObjectFieldAvailable("Theme:");
				boolean skillsOppertunities = collectionPg.isObjectFieldAvailable("Skills Teaching Opportunities:");
				boolean ccConnections = collectionPg.isObjectFieldAvailable("Cross Curricular Connections:");
				boolean publicationDate = collectionPg.isObjectFieldAvailable("Publication Date:");	
			
				if((theme = true) && (skillsOppertunities= true) &&(ccConnections= true) &&(publicationDate= true)) 
				{
					report.updateTestLog("Verify new property fields are displayed Content Type","Theme: \r\n" + 
							"Skills Teaching Opportunities: \r\n" + 
							"Cross Curricular Connections: \r\n" + 
							"Publication Date: \r\n"  
							, Status.PASS);
				}else
				{
					report.updateTestLog("Verify new property fields  available for the “Content Type” property", "New Content Type field not available", Status.FAIL);
				}
				
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitFor(driver);
				// Click on Generate Realize Csv for course object
			
				String browseActionName = dataTable.getData("Sites", "TargetFolder");
				collectionPg.clickOnBrowseActionsInCollectionUI(folderNames[2], browseActionName);
				UIHelper.waitFor(driver);
				String okButtonXpath = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-ok-button-button']";
				UIHelper.click(driver,okButtonXpath);
				UIHelper.waitFor(driver);
					
				// Navigate to Document library
				sitesPage.enterIntoDocumentLibrary();
				
				myFiles.openCreatedFolder("Data Exports");
				myFiles.openCreatedFolder("Realize Export");
				myFiles.openCreatedFolder("CCP Test Course 4 - ALFDEPLOY-3440");
							
				// Navigate to generated csv file path and check whether
				Date date = new Date();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				sitesPage.documentdetails(currentDate);

				// CSVfile presence and filename
					
				String fileName1 = folderNames[2] + "-" + currentDate;
				String filename2 = mediaTransPage.RetreiveFilename(fileName1);
				
				System.out.println("filename2 : " + filename2); 
				
				sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");
				String downloadFilePath = properties.getProperty("DefaultDownloadPath");
				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				File downloadedFile = null;
				downloadedFile = new File(downloadFilePath + "/" + filename2);
						
				if (downloadedFile.exists()
						&& !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2+ " downloaded sucessfully", Status.PASS);
					
				ArrayList<String> csvFileRowDataList = null;
				csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2);

				for (String csvRow : csvFileRowDataList) {
					String splitedRow[] = null;
					splitedRow = csvRow.split(",");

					String[] filterselection1 = dataTable.getData("Sites", "CreateSiteTypeValues").split(",");
					for (String listOfObjectsString : filterselection1) {
						if (listOfObjectsString.contains(splitedRow[99].replace("\"", ""))) {
							report.updateTestLog(listOfObjectsString + " header is appearing in the csv file ",
									"Status: " + listOfObjectsString + " appeared in the csv file", Status.PASS);
						} 
						
						if (listOfObjectsString.contains(splitedRow[100].replace("\"", ""))) {
							report.updateTestLog(listOfObjectsString + " header is appearing in the csv file ",
									"Status: " + listOfObjectsString + " appeared in the csv file", Status.PASS);
						} 
						
						if (listOfObjectsString.contains(splitedRow[101].replace("\"", ""))) {
							report.updateTestLog(listOfObjectsString + " header is appearing in the csv file ",
									"Status: " + listOfObjectsString + " appeared in the csv file", Status.PASS);
						} 
						
						if (listOfObjectsString.contains(splitedRow[102].replace("\"", ""))) {
							report.updateTestLog(listOfObjectsString + " header is appearing in the csv file ",
									"Status: " + listOfObjectsString + " appeared in the csv file", Status.PASS);
						} 
						
						
						else {
							// System.out.println("it is not appearing");
						}
					}
					break;
				}

			} else {

				report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
			}
			
				String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
				System.out.println("downloadedCSVFileANmeWithPath" +downloadedCSVFileANmeWithPath);
				String input1 = dataTable.getData("Sites", "SelectedItemsMenuOption");
				try {
					CSVUtil.updateCSV(downloadedCSVFileANmeWithPath, input1 ,2 ,102);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			String disciplizeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_discipline')]";
			String discipline = "Math";
			
		// Upload the updated CSV ( new value updated for Skills Teaching Opportunities field)
			
			sitesPage.enterIntoDocumentLibrary();

			//go to Course plan
			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");
					
			// upload updated course plan from system download path 
					
			collectionPg.uploadUpdatedFileInCollectionSite(downloadFilePath, filename2);
			UIHelper.waitForLong(driver);
			
		    driver.navigate().refresh();
		    
		    // Go to Courses and navigate to Content Type
			sitesPage.enterIntoDocumentLibrary();
			
			myFiles.openCreatedFolder(folderNames[0]);
			myFiles.openCreatedFolder(folderNames[1]);
			UIHelper.waitForPageToLoad(driver);
				
			collectionPg.clickOnEditCollectionButton();
			UIHelper.waitFor(driver);
			
			collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
			
			collectionPg.clickOnMouseOverMenu("Test Object - Discussion Prompt", "Edit Properties");
			UIHelper.click(driver, allProperties);
			UIHelper.waitForPageToLoad(driver);
			
			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,disciplizeDropdownXpathInCreateObject, discipline);
			}
			
			WebElement select1 = driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_theme-entry\"]/option[3]"));
			
			 Actions action = new Actions(driver);
		        action.keyDown(Keys.CONTROL).click(select1).build().perform();				
				collectionPg.clickOnSaveBtn();
					
				collectionPg.clickOnMoreSetting("Test Object - Discussion Prompt");
				UIHelper.waitFor(driver);
				collectionPg.commonMethodForClickOnMoreSettingsOption("Test Object - Discussion Prompt","View Details");
				UIHelper.waitFor(driver);
				collectionPg.VerifyPropertyValueINviewDetails("Publication Date:", "12");	
				UIHelper.waitFor(driver);
				
		}
				
	
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}