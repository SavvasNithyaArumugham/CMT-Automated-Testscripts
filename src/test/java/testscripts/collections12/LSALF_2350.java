package testscripts.collections12;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2350 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				"Confirm Confirm able to create a course object without skill path property as its not a mandatory property.Confirm updating the course object with skill path property blank/empty spaces without any issues.Confirm the skills property column on the exported course CSV when the skills path value is updated with empty spaces for the course object via edit collection UI.");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String CourseXpath = "//*[@class='filename']//*[contains(text(),'Course')]";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(siteNameValue, "Yes");		
		try {
		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(folderNames[2]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2],
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cm_title\"]")).sendKeys("Test");
				UIHelper.waitFor(driver);	
				driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_rumbaProgramName\"]")).sendKeys("Rumba");
				UIHelper.waitFor(driver);	
				Select selectBox1 = new Select(driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_discipline\"]")));
				selectBox1.selectByIndex(2);
				UIHelper.waitFor(driver);
				collectionPg.clickOnSaveBtn();
				UIHelper.waitFor(driver);
				report.updateTestLog("Able to save the course object with the skillpath empty","Course Object SkillsPath Property is empty", Status.PASS);	
				UIHelper.waitFor(driver);
				
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2],
								"Edit Properties");
						UIHelper.waitFor(driver);
						UIHelper.click(driver, allProperties);
						UIHelper.waitFor(driver);
						String toupdate=" ";
						driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_skillsPath\"]")).sendKeys(toupdate);
						UIHelper.waitFor(driver);
						collectionPg.clickOnSaveBtn();
						UIHelper.waitFor(driver);
						report.updateTestLog("Able to save the course object with the skillpath with Double Space","Course Object SkillsPath Property is with space", Status.PASS);
						UIHelper.waitFor(driver);
						
						/*// go to Course plan and click generate realise csv
						myFiles.openCreatedFolder(folderNames[0]);
						myFiles.openCreatedFolder(folderNames[1]);
						collectionPg.clickOnEditCollectionButton();*/
						String objectName = dataTable.getData("MyFiles", "FileName");
						String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
						// Click on Generate Realize Csv for course object
						collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
						collectionPg.clickonrealizebox();
						// Navigate to Document library
						sitesPage.enterIntoDocumentLibrary();
						Date date = new Date();
						String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
						String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
						for (String path : filePath) {
							sitesPage.documentdetails(path);
						}
						UIHelper.waitFor(driver);
						// Navigate to generated csv file path and check whether
						sitesPage.documentdetails(currentDate);
						AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
						String downloadFilePath = properties.getProperty("DefaultDownloadPath");
						File downloadedFile = null;
						ArrayList<String> csvFileRowDataList = null;
						// CSVfile presence and filename
						String fileName1 = objectName + "-" + currentDate;
						String filenameCSV = mediaTransPage.RetreiveFilename(fileName1);
						String filename2 = collectionPg.CSVFilename(fileName1);
						collectionPg.clickOnMoreSetting(filename2);
						collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");
						UIHelper.waitFor(driver);

						new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
						downloadedFile = new File(downloadFilePath + "/" + filename2);
						if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
							report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
																
							/***************************Confirm Library properties are exporting properly*********************/
							String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;
							try {
								
								if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,toupdate.trim(),1,41))
								{
								report.updateTestLog("Confirm SkillsPath property value is exporting properly ",
										"Column 41 (Skills):"+CSVUtil.readDataInCell(downloadedCSVFileANmeWithPath,1,41), Status.PASS);
								}else {
									report.updateTestLog("Confirm Library property value is not exporting properly ",
											"Column 41 (Skills):"+CSVUtil.readDataInCell(downloadedCSVFileANmeWithPath,1,41), Status.FAIL);
								}
							} catch (IOException e) {
							
								e.printStackTrace();
							}
						}
		}catch(Exception e ){
				report.updateTestLog("Able to save the course object with the skillpath empty","Course Object SkillsPath Property", Status.FAIL);	
		}
}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
