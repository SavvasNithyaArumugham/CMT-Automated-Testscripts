package testscripts.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
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

public class AUT_AG_029 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_060() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4034_Verify User Should receive a Notication email to suggest that CSV import was completed successfully.");
		
		
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
		AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMyTasksPageTest alfrescoTaskPage= new AlfrescoMyTasksPageTest(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		
		String collectionsobj = dataTable.getData("MyFiles", "CreateFileDetails");
		
		
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			    UIHelper.waitFor(driver);
	        	//Create site
				homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);
				String siteNameValue =  dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "Yes");
				UIHelper.waitFor(driver);
				
	         // Go to collection UI
			    sitesPage.enterIntoDocumentLibrary();
				//UIHelper.waitForPageToLoad(driver);	
			    UIHelper.waitForPageToLoad(driver);
			 
	
			  //go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
	
		     // upload course plan
				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
			    driver.navigate().refresh();
			   
			    sitesPage.enterIntoDocumentLibrary();
			   //myFiles.openCreatedFolder("Completed");
				//UIHelper.waitForPageToLoad(driver);
	           
	            //go to course object 1
	            Date date = new Date();
	            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	           
	           
	            myFiles.openCreatedFolder("Data Imports");
	            myFiles.openCreatedFolder("Completed");
	            myFiles.openCreatedFolder(currentDate.substring(0, 4));
	            myFiles.openCreatedFolder(currentDate.substring(5, 7));
	            myFiles.openCreatedFolder(currentDate.substring(8, 10));
	            report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
	           
				
				//check My task is created
	          
	            AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
	            AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
	            taskPage.navigateToMyTasksMenu();
	            WebElement noDateXPath = driver.findElement(By.xpath("//*[@id='alf-filters']//a[@rel='noDate']"));
	            noDateXPath.click();
	            UIHelper.waitForLong(driver);
	            UIHelper.waitForLong(driver);
	            WebElement file = driver.findElement(By.xpath("//a[contains(text(),'Import process complete for ALFDEPLOY-3447.csv. Visit the Imports page for details.')]"));
	            UIHelper.highlightElement(driver, file);
	          /*  taskPage.filterWFtasks();
	    		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
	    		wfPageObj.checkWF("Import process complete for ALFDEPLOY-3447.csv. Visit the Imports page for details.");
	    		*/
	            report.updateTestLog("Check whether Task is created" , "Status: "  +"Task is created", Status.PASS);
	    		
	    		
            }
					    
		  
				
			

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

