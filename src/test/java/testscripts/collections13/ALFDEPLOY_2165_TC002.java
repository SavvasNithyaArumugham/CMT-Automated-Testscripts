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

public class ALFDEPLOY_2165_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Confirm the Product Configuration sections contains below field for the course object at the right side under Maintain Orientation with Fillers checkbox Facsimile Directory- Text field");
		
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
		sitesPage.openSiteFromRecentSites("autorefercollectionsite1100719180342");
		String siteNameValue = dataTable.getData("Sites", "SiteName");		
		sitesPage.createSite(siteNameValue, "Yes");

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
		
		collectionPg.clickOnMouseOverMenu(values[2], "Edit Properties");
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		UIHelper.click(driver, allProperties);
		UIHelper.waitForPageToLoad(driver);
		
		String skillsPathXpath = "//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_skillsPath']";
		UIHelper.sendKeysToAnElementByXpath(driver, skillsPathXpath, "test");
		String disciplizeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_discipline')]";
		UIHelper.selectbyVisibleText(driver,disciplizeDropdownXpathInCreateObject, "Math");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		boolean type  = collectionPg.isObjectFieldAvailable("Product Type:");
		boolean facsimileDir = collectionPg.isObjectFieldAvailable("Facsimile Directory:");
		if((type = true) && (facsimileDir= true)) 
		{
			report.updateTestLog("Verify new property fields are displayed in Product Configuration","Product Type: \r\n" + 
					"Facsimile Directory: \r\n" , Status.PASS);
		}else
		{
			report.updateTestLog("Verify new property fields are displayed in Product Configuration section", "New Content Type field not available", Status.FAIL);
		}	
		String productConfigurationXpath = ".//*[contains(@id,'prop_cpnals_productConfigurationCollection-type-1')]";
		String[] valuesOf = dataTable.getData("Sites", "SiteToSelect").split(",");
		UIHelper.selectbyVisibleText(driver,productConfigurationXpath, valuesOf[0]);
		String facsimileDirXpath = ".//*[contains(@id,'prop_cpnals_productConfigurationCollection-facsimile-directory-1')]";
		UIHelper.sendKeysToAnElementByXpath(driver,facsimileDirXpath, valuesOf[1]);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
	String maintainOrientationXpath = "//*[@class='set-bordered-panel-body']//*[contains(@id,'rop_cpnals_productConfigurationCollection-main')]//input[@type='checkbox']";
	UIHelper.click(driver,maintainOrientationXpath);
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	String saveButton = "//*[contains(@id,'default-form-submit-button')]";
	
	UIHelper.click(driver, saveButton);
	UIHelper.waitFor(driver);
	
	sitesPage.enterIntoDocumentLibrary();
	myFiles.openCreatedFolder(values[0]);
	myFiles.openCreatedFolder(values[1]);
	collectionPg.clickOnEditCollectionButton();
	
	UIHelper.waitFor(driver);
	collectionPg.clickOnMoreSetting(values[2]);
	collectionPg.commonMethodForClickOnMoreSettingsOption("CCP Test Course 4 - ALFDEPLOY-3440", "Generate Bookbuild");
	UIHelper.waitFor(driver);
	
	String okButtonXpath = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-ok-button-button']";
	UIHelper.click(driver,okButtonXpath);
	UIHelper.waitFor(driver);
	
		
	UIHelper.click(driver, "//*[@id=\"HEADER_REPOSITORY_text\"]/a");
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	
	myFiles.openCreatedFolder("Data Exports");
	myFiles.openCreatedFolder("JSON Export");
	myFiles.openCreatedFolder("CCP Test Course 4 - ALFDEPLOY-3440");
	
	/*String[] filePath1 = dataTable.getData("Sites", "TargetSiteName").split("/");
	for (String path : filePath1) {
		sitesPage.documentdetails(path);
		}*/
				
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
	
			final String filePathJSON = "C:\\Automation\\Alfresco\\DownloadFiles\\"+filename2;
			if (!filePathJSON.isEmpty()) {
				FileReader reader = null;
				try {
					reader = new FileReader(filePathJSON);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = null;
				try {
					jsonObject = (JSONObject) jsonParser.parse(reader);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            JSONArray ProductConfiguration = (JSONArray) jsonObject.get("Product Configuration");
	            report.updateTestLog("Verify Product Configuration present in the Course json file", "Product Configuration : " + ProductConfiguration, Status.DONE);	
			Iterator itr = ProductConfiguration.iterator();
			
	        	if (itr!=null) {		
	    			while (itr.hasNext())
	    			{
	    				 JSONObject innerObj3 = (JSONObject) itr.next();
	    				 System.out.println("inner object "+ innerObj3);
	    				 
	    					if(innerObj3.get("Product Type").toString().contains(valuesOf[0])) {
	    						report.updateTestLog("Product Type matches json and excel","",Status.PASS);
	    					}else {
	    						report.updateTestLog("Product Type does not match json and excel","",Status.FAIL);
	    					}
	    					if(innerObj3.get("Facsimile Directory").toString().contains(valuesOf[1])) {
	    						report.updateTestLog("Facsimile Directory matches json and excel","",Status.PASS);
	    					}else {
	    						report.updateTestLog("Facsimile Directory does not match json and excel","",Status.FAIL);
	    					}
	    					
	    					if(innerObj3.get("Maintain Orientation with Fillers").toString().contains("false")) {
	    						report.updateTestLog("Maintain Orientation with Fillers matches json and value 'true' ","",Status.PASS);
	    					}else {
	    						report.updateTestLog("Maintain Orientation with Fillers does not match json and excel","",Status.FAIL);
	    					
	    			        }
			
			       }		
			
	        	             }		


			}
	}
		
	
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}