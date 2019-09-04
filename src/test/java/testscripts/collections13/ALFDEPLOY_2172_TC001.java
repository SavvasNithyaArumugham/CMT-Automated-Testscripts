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

public class ALFDEPLOY_2172_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Confirm successfull Import of test data set and m Genres property is available and populated with CSV values");
		
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
		//AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
							
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);
		
		// goto site > document lib
		homePageObj.navigateToSitesTab();
	//	sitesPage.openSiteFromRecentSites("AutoReferCollectionSite1110719150639");
		
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
		
		String values[] = dataTable.getData("Sites", "TargetSiteName").split(",");
		myFiles.openCreatedFolder(values[0]);
		myFiles.openCreatedFolder(values[1]);
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		collectionPg.openCollectionObject("CCP Test Course 4 - ALFDEPLOY-3440");
		UIHelper.waitFor(driver);
		collectionPg.clickOnCreateButton();
		collectionPg.clickOnCreateMenuItem("Dynamic Content Object");
		boolean folioSpecial  = collectionPg.isObjectFieldAvailable("Folio Special:");
		if((folioSpecial = true)) 
		{
			report.updateTestLog("Verify new property fields are displayed Content Type","Folio Special:", Status.PASS);
		}else
		{
			report.updateTestLog("Verify new property fields  available for the “Content Type” property", "New Content Type field not available", Status.FAIL);
		}
		String createObjectData = dataTable.getData("Sites", "SiteToSelect");
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(values[0]);
		myFiles.openCreatedFolder(values[1]);
		collectionPg.clickOnEditCollectionButton();

		// Click on Generate Realize Csv for course object
		String browseAction = dataTable.getData("Sites", "TargetFolder");
		
		collectionPg.clickOnBrowseActionsInCollectionUI(values[2],browseAction);
		String okButtonXpath = "//*[@id='template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-ok-button-button']";
		UIHelper.click(driver,okButtonXpath);
		UIHelper.waitFor(driver);
		
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();
		String filePath1[] = dataTable.getData("Sites", "FileName").split("/");
		for (String path : filePath1) {
			sitesPage.documentdetails(path);
		}
		// Navigate to generated csv file path and check whether
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		sitesPage.documentdetails(currentDate);
		
		// CSVfile presence and filename
	
		String fileName1 = "CCP Test Course 4 - ALFDEPLOY-3440" + "-" + currentDate;
		String filename2 = mediaTransPage.RetreiveFilename(fileName1);
	
		System.out.println("filename2 : " + filename2); 
		
		sitesPage.commonMethodForPerformBrowseOption(filename2, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		File downloadedFile = null;
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		
		
		if (downloadedFile.exists()
				&& !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2
					+ " downloaded sucessfully", Status.PASS);
			String downloadedCSVFileANmeWithPath = downloadFilePath + "/" + filename2;

		try {
			if(CSVUtil.verifyDataInCell(downloadedCSVFileANmeWithPath,"Skip Leaf",3,20))
				{
	report.updateTestLog("CSV value check for 'Folio Special' property in Dynamic content object",
						"Check Pass", Status.PASS);
				}
			else
			{
	report.updateTestLog("CSV value check for 'Folio Special' property in Dynamic content object",
						"Check Failed", Status.FAIL);
			}
			
		}catch (IOException e) {
			report.updateTestLog("Confirm that export values are as expected",
					"Export CSV values are not as expected", Status.FAIL);	
				e.printStackTrace();
	}	
		
			
		}
		
	}
	
	
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}