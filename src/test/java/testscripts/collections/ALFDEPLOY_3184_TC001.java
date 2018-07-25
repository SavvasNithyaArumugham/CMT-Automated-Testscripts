package testscripts.collections;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3184_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3184_Validate course import with duplicate descriptors"
						+"<br>ALFDEPLOY-3184_Confirm Shared Tools Container was created correctly"
						+"<br>ALFDEPLOY-3184_Confirm Shared Tools Content Objects were created correctly");
		
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
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String chapter1Xpath ="//*[@title='/Sequence Objects/c/Chapter 1']";
		String chapter2Xpath ="//*[@title='/Sequence Objects/c/Chapter 2']";
		String co1Xpath = "//*[@title='/Content Objects/s/Shared Calculator Tool']";
		String co2Xpath = "//*[@title='/Content Objects/s/Shared Lab Tool']";
		String co3Xpath = "//*[@title='/Content Objects/s/Shared Writing Tool']";
		String co4Xpath = "//*[@title='/Containers/s/Shared Tools Container']";
		String viewDetailsTitle = ".//*[@title='Shared Calculator Tool']";
		
		boolean flag = false;
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			    UIHelper.waitForLong(driver);
	        	//Create site
				homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);
/*
				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);*/
				 
				sitesPage.createSite(siteNameValue, "Yes");
				UIHelper.waitFor(driver);
			
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
		          
			    Date date = new Date();
			    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	           	           
	            myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));
				report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
	           
			    /*********************************Validate course import with duplicate descriptors******************************/
	          				
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
				
				collectionPg.clickOnLeftSideFolderFromTreeView(folderNames[2]);
				UIHelper.waitForLong(driver);
				collectionPg.clickOnMouseOverMenu(folderNames[3], "View Details");
				UIHelper.waitForPageToLoad(driver);
							
				/********************************Confirm Shared Tools Container was created correctly**************************/
				collectionPg.verifyObjectsInViewDetails(chapter1Xpath,"Chapter 1");
				collectionPg.verifyObjectsInViewDetails(chapter2Xpath,"Chapter 2");
				collectionPg.verifyObjectsInViewDetails(co1Xpath,"Shared Calculator Tool");
				collectionPg.verifyObjectsInViewDetails(co2Xpath,"Shared Lab Tool");
				collectionPg.verifyObjectsInViewDetails(co3Xpath,"Shared Writing Tool");
				
				/****************************Confirm Shared Tools Content Objects were created correctly******************/				
				UIHelper.click(driver, co1Xpath);
				
				UIHelper.waitForPageToLoad(driver);
				
				if(UIHelper.checkForAnWebElement(driver.findElement(By.xpath(viewDetailsTitle))))
				{
				report.updateTestLog("Click the file name of the Shared Calculator Tool under Child References to open that object's details page ",
						"The Objects Details page is displayed", Status.DONE);
				}
				
				collectionPg.verifyObjectsInViewDetails(co4Xpath,"Shared Tools Container");
		}	  

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

