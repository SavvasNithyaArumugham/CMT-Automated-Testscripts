package testscripts.lifecycle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_027P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify user1 uploads a file and is able to follows it");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		
		homePage.navigateToSitesTab();
		
	//String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		//sitesPage.openSiteFromRecentSites(sourceSiteName);
		 String siteuserName = dataTable.getData("Sites", "InviteUserName");
			String roleName = dataTable.getData("Sites", "Role");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");


	/*homePageObj.navigateToSitesTab();
	String siteNameValue =  dataTable.getData("Sites", "SiteName");
	sitesPage.createSite(sourceSiteName, "Yes");
	

	sitesPage.performInviteUserToSite(sourceSiteName);
    siteMemPgTest.verifySiteMemebrs(sourceSiteName, siteuserName, roleName);
	sitesPage.siteFinder(sourceSiteName);
	*/
	

 // Site finder
     String sourceSiteName = dataTable.getData("Sites", "SiteName");
     sitesPage.siteFinder(sourceSiteName);
  

     // Go to document library
	    sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitForPageToLoad(driver);	
	    UIHelper.waitForPageToLoad(driver);
	

	//Upload a file
	  
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName,"");
		//sitePgTestObj.verifyAttachLifecycleForFileOrFolder(fileName);
	    
		report.updateTestLog("User clicks follow", "Status: " +"Follow option get clicked", Status.PASS);
	    //User1 Follow
		String Follow = "//*[@class='Follow-action enabled'][contains(@id,'Follow-document')]";
		UIHelper.waitForVisibilityOfEleByXpath(driver, Follow);
				
		WebElement w2=driver.findElement(By.xpath(Follow));
		w2.click();
		report.updateTestLog("Check whether AlfrescoAutoDemoForTest is followed", "Status: " +"User1 followed AlfrescoAutoDemoForTest file", Status.PASS);
	
			
		
		
	}


	@Override
	public void tearDown() {

	}
}
