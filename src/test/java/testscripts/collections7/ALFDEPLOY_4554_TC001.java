package testscripts.collections7;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class ALFDEPLOY_4554_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4554_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_4554_TC001 Validate import testdata course." +
			    "<br>ALFDEPLOY_4554_TC002_Apply filter to the course) + "
			    + "<br>ALFDEPLOY_4554_TC003_Verify that editing properties on filtered objects does not clear the filter)");
				testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
				driverScript = new DriverScript(testParameters);
				driverScript.driveTestExecution();		

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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String contentObj = "//*[@class='yui-dt-data']//h3/span";
		functionalLibrary.loginAsValidUser(signOnPage);

		//Go to site
		//Create site
		UIHelper.waitForLong(driver);
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");				
		sitesPage.siteFinder(siteNameValue);
		
		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		
		//upload file in collectionsite		
		
		myFiles.openCreatedFolder(folderNames[0]);
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder(folderNames[1]);	
		UIHelper.waitFor(driver);		
		UIHelper.waitFor(driver);	
		
		//upload file in course plan		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		//collectionPg.verifyImportProcessProgress(fileName);
		UIHelper.waitForLong(driver);	
		UIHelper.pageRefresh(driver);
		sitesPage.enterIntoDocumentLibrary();
		
		//case 2: ALFDEPLOY_4554_TC002_Apply filter to the course
		
		myFiles.openCreatedFolder(folderNames[2]);
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder(folderNames[3]);
		UIHelper.waitFor(driver);
		collectionPg.clickOnEditCollectionButton();
		
		//filter
		collectionPg.ClickonFilter("filter");
		collectionPg.SelectionOfFilter("versionState","FilterCollection_VersionState");		
		collectionPg.ClickonFilter("filterbutton");
		UIHelper.waitFor(driver);		
		UIHelper.click(driver, contentObj);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		collectionPg.filtervalidationforContentObjects("Texas State Unit 2", "Texas State Unit 2");		
		
		//case 3: Verify that editing properties on filtered objects does not clear the filter
		
		collectionPg.clickOnMouseOverMenu("Texas State Unit 2","Edit Properties");		
		collectionPg.editpropAndSave("Version State:", "FL");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		collectionPg.ClickonFilter("clickhere");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		collectionPg.appliedFilterVerification();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		myFiles.deleteCreatedFolder(fileName);
	}
	

	@Override
	public void tearDown() {

	}
}
