package testscripts.collections9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1798_TC003 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Verify the Print Automation Section is displayed in properties screen while creating content object");
		
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
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String levelXpath =".//*[contains(@id,'prop_cpnals_dynamicContentType') and @name='prop_cpnals_dynamicContentType']";
				 
		// Print Automation xpaths
		String folioPrefixXpath = ".//*[contains(@id,'prop_cpnals_folioPrefix') and @name='prop_cpnals_folioPrefix']";
		String folioStyleXpath = ".//*[contains(@id,'prop_cpnals_folioStyle') and @name='prop_cpnals_folioStyle']";
		String folioStartXpath = ".//*[contains(@id,'prop_cpnals_folioStart') and @name='prop_cpnals_folioStart']";
		String folioSpecialXpath = ".//*[contains(@id,'prop_cpnals_folioSpecial') and @name='prop_cpnals_folioSpecial']";
		String tocIncFromXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeFrom') and @name='prop_cpnals_tocIncludeFrom']";
		String tocIncToXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeTo') and @name='prop_cpnals_tocIncludeTo']";
		String contentObjectAggregationXpath = ".//*[contains(@id,'prop_cpnals_contentObjectAggregationType') and @name='prop_cpnals_contentObjectAggregationType']";
	
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
	
		sitesPage.enterIntoDocumentLibrary();
				
		UIHelper.waitFor(driver);
				
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
	
		collectionPg.clickOnCreateButton();
		collectionPg.clickOnCreateMenuItem("Content Object");
		
		UIHelper.waitFor(driver);
		UIHelper.sendKeysToAnElementByXpath(driver,folioPrefixXpath , "testvalue12@  ");
		UIHelper.selectbyVisibleText(driver,folioStyleXpath,"1 - Arabic" );
		UIHelper.sendKeysToAnElementByXpath(driver,folioStartXpath , "1");
		UIHelper.selectbyVisibleText(driver,folioSpecialXpath,"Forward Interleaf" );
		UIHelper.selectbyVisibleText(driver,tocIncFromXpath,"Level 1" );
		UIHelper.selectbyVisibleText(driver,tocIncToXpath,"Level 1" );
		UIHelper.selectbyVisibleText(driver,contentObjectAggregationXpath,"Additional Answers" );
			
		//collectionPg.clickOnSaveBtnForSubmitCreateObjectData();
		UIHelper.waitFor(driver);
			
		
		}
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
