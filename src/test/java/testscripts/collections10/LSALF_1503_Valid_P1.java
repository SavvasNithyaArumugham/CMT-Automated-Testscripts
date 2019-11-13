package testscripts.collections10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1503_Valid_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_01_02() {
		testParameters
				.setCurrentTestDescription("Bulk Skill Removal via CSV Import: "
						+ "Need an Alfresco scripted folder action where user can input a CSV containing a list of Alfresco Workspace ID's, and script will remove all <cpnals:skillObjectAlignment> relationships for those listed objects.");
		
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
						
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		Date date = new Date();
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Connected Mathematics 3 Realize Edition Grade 6')]";
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
	
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.createSite(siteNameValue, "No");
		//sitesPage.openSiteFromRecentSites("1503_Valid_QATestSite56895");
				// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				// upload course plan
				collectionPg.uploadFileInCollectionSite(filepathfile, objectName);

				// verify import process progress
				collectionPg.verifyImportProcessProgress(objectName);				
				sitesPage.enterIntoDocumentLibrary();
				
				// go to Course plan
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
				UIHelper.waitFor(driver);
				UIHelper.click(driver, CourseXpath);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching");
				UIHelper.waitFor(driver);	
				collectionPg.commonMethodForClickOnMoreSettingsOption("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching", "Align Skills");
				UIHelper.waitFor(driver);	
				collectionPg.addAlignmentinObjects();
				
				driver.navigate().back();
				collectionPg.clickOnEditCollectionButton();		
				UIHelper.waitFor(driver);
				
				UIHelper.click(driver, CourseXpath);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching");
				UIHelper.waitFor(driver);	
				collectionPg.commonMethodForClickOnMoreSettingsOption("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching", "View Details");
				UIHelper.waitFor(driver);
				String currentUrl = driver.getCurrentUrl();
				UIHelper.waitFor(driver);	
				
				String workspaceid="";
				if(currentUrl!=null){
					workspaceid=currentUrl.substring(98, currentUrl.length());					
				}else {
					workspaceid	= currentUrl;
				}
						
				try {
					String excelFilePath="C:\\Users\\167346\\Documents\\alfresco\\src\\test\\resources\\AppTestData\\MyFiles\\UploadFiles\\BulkSkillRemoval.xlsx";
					 FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			          Workbook workbook = WorkbookFactory.create(inputStream);
					Sheet sheet = workbook.getSheetAt(0);
					Row row = sheet.createRow(1);
		            Cell cell = row.createCell(0);
		            //cell.setCellValue(workspaceid);
					Cell cell2Update = sheet.getRow(1).getCell(0);
					cell2Update.setCellValue(workspaceid);
					 inputStream.close();
					FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		            workbook.write(outputStream);
		            workbook.close();
		            outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
				String createdFolder = dataTable.getData("MyFiles", "CreateFileDetails");
				String MoreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
				String createRulesXPath="//*[@id=\"template_x002e_rules-none_x002e_folder-rules_x0023_default-body\"]/div[2]/div[1]/a";
				String createdFolderXpath="//*[@id=\"template_x002e_path_x002e_folder-rules_x0023_default\"]/div/div[1]/div/span[5]/a";
				String completedFolderXpath="//*[@id=\"yui-gen182\"]";
				String ruleDetails = dataTable.getData("MyFiles", "Subfolders1");
				String fileName = dataTable.getData("MyFiles", "Subfolders2");
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[2]);
				//Create Bulk Skill Removal folder
				myFiles.createFolder(folderDetails);
				collectionPg.clickOnMoreSetting(createdFolder);
				collectionPg.commonMethodForClickOnMoreSettingsOption(createdFolder,MoreSettingsOption);
				UIHelper.click(driver, createRulesXPath);
				collectionPg.clickOnCreateRules();
				collectionPg.createRulesBasicData(ruleDetails,"Bulk SKill Removal");
				try {
				UIHelper.click(driver,createdFolderXpath );
				myFiles.uploadFileInMyFilesPage(filepathfile, fileName);
			
				UIHelper.waitFor(driver);
				UIHelper.click(driver, completedFolderXpath);
				report.updateTestLog("Complete Folder",
						"Completed folder is present", Status.PASS);
				}catch(Exception e) {
					report.updateTestLog("Complete Folder",
							"Completed folder is not present", Status.FAIL);
					e.printStackTrace();
				}
				UIHelper.waitFor(driver);
				// Navigate to document library and click on a program>Program Object
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				UIHelper.waitFor(driver);
				// Click on "Edit collection"
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitFor(driver);
				UIHelper.click(driver, CourseXpath);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching");
				UIHelper.waitFor(driver);
				collectionPg.commonMethodForClickOnMoreSettingsOption("A Guide to Connected Mathematics 3_ Understanding, Implementing, and Teaching","View Details");
				UIHelper.waitFor(driver);
				WebElement e = UIHelper.findAnElementbyXpath(driver, "//*[@id=\"template_x002e_comments_x002e_folder-details_x0023_relationships-list-incomingRelations\"]/div[1]/div[1]");
				
				if(e.getText().contains("Has Alignment")) {
					report.updateTestLog("Skill Alignment","Has Alignment",Status.FAIL);
				}else {
					report.updateTestLog("Skill Alignment","Does not have Alignment",Status.PASS);
				}
				
				
				try {
					String excelFilePath="C:\\Users\\167346\\Documents\\alfresco\\src\\test\\resources\\AppTestData\\MyFiles\\UploadFiles\\BulkSkillRemoval.xlsx";
					 FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			          Workbook workbook = WorkbookFactory.create(inputStream);
					Sheet sheet = workbook.getSheetAt(0);
					Row row = sheet.getRow(1);
		           // Cell cell = row.getCell(0);
		            //cell.setCellValue(workspaceid);
					Cell cell2Update = sheet.getRow(1).getCell(0);
					cell2Update.setCellValue("");
					 inputStream.close();
					FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		            workbook.write(outputStream);
		            workbook.close();
		            outputStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (EncryptedDocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
