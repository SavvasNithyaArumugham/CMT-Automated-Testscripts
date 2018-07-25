package testscripts.collections;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3191_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3191_TC002() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_3191_TC002: Verify Leveled Readers Section is visible for any content object "+ 
		"<br>ALFDEPLOY_3191_TC003:Verify Leveled Readers properties are visible and in correct order"	+
		"<br>ALFDEPLOY_3191_TC004:Verify Lexile property displays the value given from new course csv file" +			
		"<br>ALFDEPLOY_3191_TC005:Verify Lexile property field validation with valid and invalid data" +
		"<br>ALFDEPLOY_3191_TC006:Verify Guided Reading property displays the value given from new course csv file" +
		"<br>ALFDEPLOY_3191_TC007:Verify Guided Reading property field validation with valid data" +
		"<br>ALFDEPLOY_3191_TC008:Verify DRA property displays the value given from new course csv file" +
		"<br>ALFDEPLOY_3191_TC009:Verify DRA property field validation with valid data" +
		"<br>ALFDEPLOY_3191_TC0010:Verify Reading Maturity Metric property displays the value given from new course csv file" +
		"<br>ALFDEPLOY_3191_TC0011:Verify Reading Maturity Metric property field validation with valid data" +		
		"<br>ALFDEPLOY_3191_TC0012:Verify Quantile property displays the value given from new course csv file" +
		"<br>ALFDEPLOY_3191_TC0013:Verify Quantile property field validation with valid data" +
		"<br>ALFDEPLOY_3191_TC0014:Verify Comprehension Skills property displays the value given from new course csv file" +
		"<br>ALFDEPLOY_3191_TC0015:Verify Comprehension Skills property field validation with valid data" +	
		"<br>ALFDEPLOY_3191_TC0016:Verify Comprehension Skills property specified Values" 			
				);
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
		try{
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
			AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);	
			String errorfile = dataTable.getData("MyFiles", "Subfolders1");
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);
			String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");			
			String allProperties = ".//a[contains(text(),'All Properties...')]";
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";	
						
			// Log in Pearson Schools project
			functionalLibrary.loginAsValidUser(signOnPage);
							
			//Create site
			UIHelper.waitForLong(driver);
			homePageObj.navigateToSitesTab();
			String siteNameValue =  dataTable.getData("Sites", "SiteName");				
			sitesPage.siteFinder(siteNameValue);			
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();		
						
					//go to course object 1				
					
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(folderNames[0]);		
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(folderNames[1]);
					collectionPgTest.verifyEditCollectionOption();
					collectionPg.clickOnEditCollectionButton();				
					UIHelper.waitForPageToLoad(driver);
					
					//click on edit properties	and All properties			
					 
					collectionPg.clickOnMouseOverMenu(folderNames[2],"Edit Properties");
					UIHelper.click(driver, allProperties); 					
				
					//  validate level Readers section and the order of leveled readers options	(case 2 and case 3) 
					collectionPg.LeveledReaderSectionAndOptionsVerify("Leveled Readers");
					UIHelper.waitFor(driver);
					
					//Verify Lexile property displays the value given from new course csv file (case 4)
					collectionPg.VerifyPropertyValue("Lexile:", "-200");	
					UIHelper.waitFor(driver);
					
					//Verify Lexile property field validation with valid and invalid data (case 5)
					collectionPg.VerifyPropertyValueInValidData("Lexile:","Lorum Ipsum");
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueValidData("Lexile:", "500");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					
					//Verify Guided Reading property displays the value given from new course csv file (case 6)
					collectionPg.clickOnMouseOverMenu(folderNames[3],"Edit Properties");
					UIHelper.waitFor(driver);
					UIHelper.click(driver, allProperties);
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValue("Guided Reading:", "Lorum Ipsum");		
					UIHelper.waitFor(driver);
					
					//Verify Guided Reading property field validation with valid data	(case 7)
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueValidData("Guided Reading:", "100");
					UIHelper.waitFor(driver);
					
					//Verify DRA property displays the value given from new course csv file (case 8)
					UIHelper.waitFor(driver);
					collectionPg.clickOnMouseOverMenu(folderNames[4],"Edit Properties");
					UIHelper.click(driver, allProperties);
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValue("DRA (Developmental Reading Assessment):", "12");		
					UIHelper.waitFor(driver);
					
					//Verify DRA property field validation with valid and invalid data	(case 9)
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueInValidData("DRA (Developmental Reading Assessment):","Lorum Ipsum");
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueValidData("DRA (Developmental Reading Assessment):", "500");
					UIHelper.waitFor(driver);
					
					//Verify Reading Maturity Metric property displays the value given from new course csv file (case 10)
					UIHelper.waitFor(driver);
					collectionPg.clickOnMouseOverMenu(folderNames[5],"Edit Properties");
					UIHelper.click(driver, allProperties);
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValue("Reading Maturity Metric:", "Lorum Ipsum");		
					UIHelper.waitFor(driver);
					
					//Verify Reading Maturity Metric property field validation with valid data	(case 11)
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueValidData("Reading Maturity Metric:", "100");
					UIHelper.waitFor(driver);
					
					//Verify Quantile property displays the value given from new course csv file (case 12)
					UIHelper.waitFor(driver);
					collectionPg.clickOnMouseOverMenu(folderNames[6],"Edit Properties");
					UIHelper.click(driver, allProperties);
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValue("Quantile:", "Lorum Ipsum");		
																		
					//Verify Quantile property field validation with valid data	(case 13)
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueValidData("Quantile:", "100");
					UIHelper.waitFor(driver);
					
					//Verify Comprehension Skills property displays the value given from new course csv file (case 14)
					//Verify Comprehension Skills property specified Values & select any of the value (case 15 and 16)	
					UIHelper.waitFor(driver);
					collectionPg.clickOnMouseOverMenu(folderNames[7],"Edit Properties");
					UIHelper.click(driver, allProperties);					
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					collectionPg.VerifyComprehensiveValueAndList("Comprehension Skills:","Fact and Opinion");			
					UIHelper.waitFor(driver);			
					
					
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub

		}
	}


