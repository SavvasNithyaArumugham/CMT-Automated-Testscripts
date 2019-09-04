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

public class ALFDEPLOY_1702_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Confirm importing a new course which contains circular references as beow"
						+ "Case 1) An object which contains itself as a direct child (1 level down)\r\n" + 
						"Case 2) An object which contains itself as an indirect child (2 or more levels down)\r\n" + 
						"Case 3) An object which contains itself as an indirect child two times (2 or more levels down under different parents)");
		
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
	
			//  login
			functionalLibrary.loginAsValidUser(signOnPage);
			
			// goto site > document lib
			homePageObj.navigateToSitesTab();
	//		sitesPage.openSiteFromRecentSites("autorefercollectionsite1170719184302");
		String siteNameValue = dataTable.getData("Sites", "SiteName");		
			sitesPage.createSite(siteNameValue, "Yes");
			sitesPage.enterIntoDocumentLibrary();
	
						
			// go to Course plan
			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Course Plan");
								
			// upload course plan ( Course which has the specified structure and child references added - Course-LSALF-1702.csv )
			
			String filePath = dataTable.getData("Sites", "InviteUserName");
			String fileName = dataTable.getData("Sites", "Role");
						
			collectionPg.uploadFileInCollectionSite(filePath, fileName);
			
			UIHelper.waitForLong(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.pageRefresh(driver);
			sitesPage.enterIntoDocumentLibrary();
			
			// go to Course plan
			myFiles.openCreatedFolder("Data Imports");
			myFiles.openCreatedFolder("Completed");
			
			Date date = new Date();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			myFiles.openCreatedFolder(currentDate.substring(0, 4));
			myFiles.openCreatedFolder(currentDate.substring(5, 7));
			myFiles.openCreatedFolder(currentDate.substring(8, 10));			
			UIHelper.waitFor(driver);			
						
			//Download the error report and view the error report	
			String downloadFilePath = properties.getProperty("DefaultDownloadPath");
			// Delete existing error report in the download path
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath,"error-report.html");			
			collectionPg.clickOnMoreSetting(fileName);
			UIHelper.waitFor(driver);
			collectionPg.commonMethodForClickOnMoreSettingsOption(fileName, "View Error Report");
			UIHelper.waitFor(driver);
			new FileUtil().waitUptoAllFilesDownloadComplete(downloadFilePath,"error-report.html");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			homePageObj.openNewTab(downloadFilePath + "\\" + "error-report.html");	
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			/*collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference with row [10]","circular reference with row ");
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference with row [13]","circular reference with row ");
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference with row [4]","circular reference with row ");
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference with row [5]","circular reference with row ");
			*/
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference","circular reference");
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference","circular reference");
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference","circular reference");
			collectionPg.errormessagecontains("The object is reported as not persisting due to circular reference","circular reference");
				
	}
	
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}