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

public class ALFDEPLOY_3651_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_026() {
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-3191_017_Confirm Text Features field was populated in import");
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
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);	
	
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String collectionsobj = dataTable.getData("MyFiles", "CreateFileDetails");
		String siteNameValue =  dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] Metadata = dataTable.getData("MyFiles", "FieldDataForAllProperties").split(";");	
		String[] fieldData = dataTable.getData("MyFiles", "FieldDataForQuickEdit").split(";");
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String moreSettingsOptionName = dataTable.getData("MyFiles","MoreSettingsOption");
		String[] filePath1 = dataTable.getData("MyFiles", "CreateFileDetails").split("/");
		
		File downloadedFile = null;
		boolean flag = false;
		String discipline = ".//*[@name='prop_cpnals_discipline']";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		String saveButton = "//*[contains(@id,'default-form-submit-button')]";
		String editProperties =".//h1[contains(text(),'Edit')]";
		String skillsPath = ".//*[contains(@name,'skillsPath')]";
		
		
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			    UIHelper.waitForLong(driver);
			    
			    //Create site
			    homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);

				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);
			 
				/*sitesPage.createSite(siteNameValue, "Yes");
				UIHelper.waitFor(driver);
			
	         // Go to collection UI
			sitesPage.enterIntoDocumentLibrary();
		  
			//go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
	
		     // upload course plan
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
			    driver.navigate().refresh();
			    sitesPage.enterIntoDocumentLibrary();
			    
			    //go to course object 1
		          
			    Date date = new Date();
			    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	           	           
	            myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));
				report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
				*/
				// Go to Courses and navigate to Content Type
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
									
				collectionPg.clickOnMouseOverMenu(folderNames[2],"Edit Properties");
				UIHelper.click(driver, allProperties);
				UIHelper.waitForPageToLoad(driver);
				
				UIHelper.sendKeysToAnElementByXpath(driver,skillsPath," ");
				UIHelper.sendKeysToAnElementByXpath(driver,skillsPath,"test");
				
				
				Select oSelect_Discipline = new Select(driver.findElement(By.xpath(discipline)));
				UIHelper.highlightElement(driver, discipline);
				String selectedOption = oSelect_Discipline.getFirstSelectedOption().getText();
				
				if(!selectedOption.isEmpty())
				{
					oSelect_Discipline.selectByVisibleText("");
				}
				UIHelper.click(driver, saveButton);
				UIHelper.waitForVisibilityOfEleByXpath(driver,editProperties );
				WebElement WebEledocumentLibrary = driver.findElement(By.xpath(editProperties)) ;
			
				UIHelper.highlightElement(driver, editProperties);	
				if(UIHelper.isWebElementDisplayed(WebEledocumentLibrary))
				{
					report.updateTestLog("Confirm that Discipline is required property (cannot save if blank)",
							"Discipline is required property (cannot save if blank)", Status.PASS);	 
				}
				else
				{
					report.updateTestLog("Confirm that Discipline is required property (cannot save if blank)",
							"Discipline is not required property (cannot save if blank)", Status.FAIL);	   
				}
				
				
				
			
		}	  

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

