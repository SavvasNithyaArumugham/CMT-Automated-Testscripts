package testscripts.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_4666_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_004() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4666_Validate new property values in Alfresco Properties forms"
						+"<br>ALFDEPLOY-4666_Confirm new property rules"
						+"<br>ALFDEPLOY-4666_Confirm new values applied on CSV output");
		
		
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue =  dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		String fieldData[] = dataTable.getData("MyFiles", "FieldDataForQuickEdit").split(";");
		String subfieldData[]=null;
		
		File downloadedFile = null;
		Date date = new Date();
	    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		boolean flag = false;
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String saveButton = "//*[contains(@id,'default-form-submit-button')]";
		String skillsPath = ".//*[contains(@name,'skillsPath')]";
		String externalIdPrefixAndSeparator = ".//*[contains(@name,'externalIdPrefixAndSeparator')]";
		String editProperties =".//h1[contains(text(),'Edit')]";
		String rumbaPrgName =".//*[contains(@name,'prop_cpnals_rumbaProgramName')]";
		String courseAbbrev = ".//*[contains(@name,'courseAbbreviation')]";
		String discipline = ".//*[@name='prop_cpnals_discipline']";
		
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			
	        	//Create site
				homePageObj.navigateToSitesTab();
				

				/*String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);*/
				
				sitesPage.createSite(siteNameValue, "Yes");
			
			
	         // Go to collection UI
		       sitesPage.enterIntoDocumentLibrary();
		
		     //go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
	
		     // upload course plan	
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
			    driver.navigate().refresh();
			   
			    sitesPage.enterIntoDocumentLibrary();
		
			    //go to course object 1
		             	           
	            myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));
				report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
				flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName,Option[0]);
				if(flag){
					report.updateTestLog("View Error Report :", "View Error Report is present "
							+ "for "+"<b> "+fileName, Status.FAIL);		
				}else{
					report.updateTestLog("View Error Report:", "View Error Report is not presented "
							+ "for "+"<b> "+fileName, Status.PASS);			
				}
					
				// Go to Courses and navigate to Content Type
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
						
				collectionPg.clickOnMouseOverMenu(folderNames[2],"Edit Properties");
				UIHelper.click(driver, allProperties);
				UIHelper.waitForPageToLoad(driver);
				/**********************************Validate new property values in Alfresco Properties forms**************************************************/
				if (!UIHelper.checkForAnElementbyXpath(driver,courseAbbrev))
				{
					report.updateTestLog("Confirm that the property Course Abbreviation no longer appears in properties form ",
							"the property Course Abbreviation no longer appears in properties form", Status.PASS);	
				}
				
				if (UIHelper.checkForAnElementbyXpath(driver,skillsPath))
				{
					UIHelper.highlightElement(driver, skillsPath);
					report.updateTestLog("Confirm that new property Skills Path appears in properties form ",
							"new property Skills Path appears in properties form", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that new property Skills Path appears in properties form ",
							"new property Skills Path does not appear in properties form", Status.FAIL);	
				}
				if (UIHelper.checkForAnElementbyXpath(driver,externalIdPrefixAndSeparator))
				{
					UIHelper.highlightElement(driver, ".//*[contains(@name,'externalIdPrefixAndSeparator')]");
					report.updateTestLog(" Confirm that new property External ID Prefix and Separator appears in properties form", 
							"new property External ID Prefix and Separator appears in properties form",Status.PASS);	
				}
				else
				{
					report.updateTestLog(" Confirm that new property External ID Prefix and Separator appears in properties form", 
							"new property External ID Prefix and Separator does not appear in properties form",Status.FAIL);
				}
				
				UIHelper.sendKeysToAnElementByXpath(driver, skillsPath, "");
				UIHelper.click(driver, saveButton);
				UIHelper.waitForVisibilityOfEleByXpath(driver,editProperties );
				WebElement WebEledocumentLibrary = driver.findElement(By.xpath(editProperties)) ;
			
				UIHelper.highlightElement(driver, editProperties);	
				if(UIHelper.isWebElementDisplayed(WebEledocumentLibrary))
				{
					report.updateTestLog("Confirm that Skills Path is required property (cannot save if blank)",
							"Skills Path is required property (cannot save if blank)", Status.PASS);	 
				}
				else
				{
					report.updateTestLog("Confirm that Skills Path is required property (cannot save if blank)",
							"Skills Path is not required property (cannot save if blank)", Status.FAIL);	   
				}
				
				/*****************************************Confirm new property rules**********************************************/
				
				subfieldData =fieldData[0].split(":");
				UIHelper.sendKeysToAnElementByXpath(driver,skillsPath," ");
				UIHelper.sendKeysToAnElementByXpath(driver,skillsPath,subfieldData[1]);
				
				subfieldData =fieldData[1].split(":");
				UIHelper.sendKeysToAnElementByXpath(driver,externalIdPrefixAndSeparator," ");
				UIHelper.sendKeysToAnElementByXpath(driver,externalIdPrefixAndSeparator,subfieldData[1]);
				
				Select oSelect_Discipline = new Select(driver.findElement(By.xpath(discipline)));
				oSelect_Discipline.selectByIndex(1);
				
				UIHelper.sendKeysToAnElementByXpath(driver,rumbaPrgName," ");
				UIHelper.sendKeysToAnElementByXpath(driver,rumbaPrgName,"test");
				
				UIHelper.click(driver, saveButton);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				
				/*************************************************Confirm new values applied on CSV output**************************************************/
				
				sitesPage.enterIntoDocumentLibrary();
			    sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
	
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[1]);
				collectionPg.clickonrealizebox();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				
				sitesPage.enterIntoDocumentLibrary();

				for (String path : filePath1) {
					sitesPage.documentdetails(path);
				}
			
				// Navigate to generated csv file path and check whether
				sitesPage.documentdetails(currentDate);

				// CSVfile presence and filename
				String fileName1 = folderNames[2] + "-" + currentDate;
				String filename2 = collectionPg.CSVFilename(fileName1);

				System.out.println("filename1 : " + fileName1); 
				
				new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,
						filename2);

				sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

				new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
				downloadedFile = new File(downloadFilePath + "/" + filename2);
				if (downloadedFile.exists()
						&& !filename2.equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + filename2
							+ " downloaded sucessfully", Status.PASS);
				}	
				
				String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
				try {
					if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"TEST1-L34574162",1,12))
						{
report.updateTestLog("confirm that the External ID Prefix and Separator value is appearing in Column M (External ID) appended to the beginning of the External ID value without any space (should see TEST1-L34574162 for Course object).",
								"TEST1-L34574162 is displayed for the Course object", Status.PASS);
						}
					else
					{
report.updateTestLog("confirm that the External ID Prefix and Separator value is appearing in Column M (External ID) appended to the beginning of the External ID value without any space (should see TEST1-L34574162 for Course object).",
								"TEST1-L34574162 is not displayed for the Course object", Status.FAIL);
					}
					
				}catch (IOException e) {
					report.updateTestLog("Confirm that export values are as expected",
							"Export CSV values are not as expected", Status.FAIL);	
						e.printStackTrace();
			}	
				
		}	  

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

