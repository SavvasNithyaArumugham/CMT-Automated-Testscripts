package testscripts.collections9;

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
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1912_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("To Check Only contents alfresco out of box more section needs to be display the option like 'ActivePreviewURL' link");
		
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
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String Option = dataTable.getData("MyFiles", "MoreSettingsOption");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
							
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.enterIntoDocumentLibrary();
		
		// go to Project Files and upload course
		myFiles.openCreatedFolder("Project Files");
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);

		//Active Preview for PDF file			
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(fileName[0]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[0], Option);
		
	String imageActivePreviewUrl= collectionPg.clickOnOKButtoninActivePreview();
		
	if (imageActivePreviewUrl.contains(fileName[0]))
	{
		report.updateTestLog("Active Preview of alfresco content",
				"Image URL is displayed "+imageActivePreviewUrl, Status.PASS);
	}
	else
	{
		report.updateTestLog("Active Preview of alfresco content",
				"Image URL not displayed "+imageActivePreviewUrl, Status.FAIL);
	}
	
		//Active Preview for PDF file
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(fileName[1]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[1], Option);
		
		String pdfActivePreviewUrl= collectionPg.clickOnOKButtoninActivePreview();
				
		if (pdfActivePreviewUrl.contains(fileName[1]))
		{
			report.updateTestLog("Active Preview of alfresco content",
					"PDF URL is displayed "+pdfActivePreviewUrl, Status.PASS);
		}
		else
		{
			report.updateTestLog("Active Preview of alfresco content",
					"PDF URL not displayed "+pdfActivePreviewUrl, Status.FAIL);
		}
		
		UIHelper.waitFor(driver);
		
		}
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
