package testscripts.avsmartlink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class AUT_AG_38P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify the creation of 3rd party interactive link with 3PI vendor(A closer look) and Optimized for use on mobile radio button options filled");
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
	AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
	AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
	
	AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
	AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
	AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
			scriptHelper);
	AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
	AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
	
	
	String fileName = dataTable.getData("MyFiles", "FileName");
	
	String type = dataTable.getData("Home", "DashletName");
	String title = dataTable.getData("Document_Details", "FilePath");
	String extURLLink = dataTable.getData("Document_Details", "FileName");
	//String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
	
	
	

    // Log in Pearson Schools project
	  functionalLibrary.loginAsValidUser(signOnPage);
	
	  //Create site
	 UIHelper.waitForPageToLoad(driver);
	 
	
	homePageObj.navigateToSitesTab();
	String siteNameValue =  dataTable.getData("Sites", "SiteName");
	//sitesPage.createSite(siteNameValue, "Yes");
	sitesPage.siteFinder(siteNameValue);
	
	

 /* Site finder
     String siteassertValue = dataTable.getData("Sites", "SiteName");
     sitesPage.siteFinder(siteassertValue);
   */

     // Go to document library
	    sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitForPageToLoad(driver);	
	    UIHelper.waitForPageToLoad(driver);
	

	 // Navigate to Smart link Page
	 		myFiles.createcontenttype(type);
	 		avsmart.clickSmartLinkType("3rd Party Interactive Link", "thirdPartyInteractiveLink");
	 		
	 		//3PI-A Closer Look
	 		String PI = "//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]";
	 		UIHelper.waitForVisibilityOfEleByXpath(driver, PI);
	 				
	 		WebElement w=driver.findElement(By.xpath(PI));
	 		//w.click();
	 		Select s= new Select(w);
	 		s.selectByVisibleText("A Closer Look");
	 	
	 			
	 		// OptimizedthirdPartyInteractiveLink
	 		
	 		String Optimized = "//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']";
	 	
	 		UIHelper.waitForVisibilityOfEleByXpath(driver, Optimized);
	 				
	 		WebElement w1=	driver.findElement(By.xpath(Optimized));
	 		UIHelper.highlightElement(driver,w1);
	 	    w1.click();

		
	    String filePath = dataTable.getData("MyFiles", "FilePath"); 
		//avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "credit","", System.getProperty("user.dir")+filePath+fileName);
		avsmart.entersmarttypedata("thirdPartyInteractiveLink", title, extURLLink, "Image", "Caption", "credit","",  System.getProperty("user.dir")+filePath+fileName); 
		
		avsmart.subcancelbtn("thirdPartyInteractiveLink","Submit");
		UIHelper.waitForPageToLoad(driver);
		report.updateTestLog("Check whether 3rd party interactive link with 3PI vendor(A closer look) and Optimized for use on mobile is created" , "Status: "  +"3rd Party Interactive smartlink is created", Status.PASS);
	
}


	@Override
	public void tearDown() {

	}
}
