package testscripts.avsmartlink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class AUT_AG_23 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-3873_Verify the presence of 3PI vendor dropdown and Optimized for use on mobile options in Create smartlink of 3rd party interactive link "
				+"<br>3.ALFDEPLOY-3873_Verify the default option of optimized for use on mobile radio button in 3rd party interactive link form"
				+"<br>4.ALFDEPLOY-3873_Verify the creation of 3rd party interactive link without 3PI vendor dropdown and Optimized for use on mobile radio button options filled"
				);
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
	 UIHelper.waitForPageToLoad(driver);
	 
	
	homePageObj.navigateToSitesTab();
	String siteNameValue =  dataTable.getData("Sites", "SiteName");
	//sitesPage.createSite(siteNameValue, "Yes");
	sitesPage.siteFinder(siteNameValue);
	
	

 /* Site finder
     String siteassertValue = dataTable.getData("Sites", "SiteName");
     sitesPage.siteFinder(siteassertValue);
   */

     // Go to collection UI
	    sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitForPageToLoad(driver);	
	    UIHelper.waitForPageToLoad(driver);
	

	// Navigate to Smart link Page
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "3rd Party Interactive Link");
		
		//Verify 3PI
		String PI = "//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]";
		UIHelper.waitForVisibilityOfEleByXpath(driver, PI);
				
		WebElement w=	driver.findElement(By.xpath(PI));
		UIHelper.highlightElement(driver,w);
		Boolean flag=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, PI));
		if (flag){
		report.updateTestLog("Check weather 3PI dropdown is appearing in  "+ "3rd Party Interactive Link", "3PI dropdown is appearing in "+"3rd Party Interactive Link",Status.PASS);
		}else{
		report.updateTestLog("Check weather 3PI dropdown is appearing in  "+ "3rd Party Interactive Link", "3PI dropdown is not appearing in "+"3rd Party Interactive Link",Status.FAIL);
		}
			
		//Verify OptimizedthirdPartyInteractiveLink
		
		String Optimized = "//table[@class='thirdpartytable']//td[text()='Optimized for use on Mobile?']";
		UIHelper.waitForVisibilityOfEleByXpath(driver, Optimized);
				
		WebElement w1=	driver.findElement(By.xpath(Optimized));
		UIHelper.highlightElement(driver,w1);
		Boolean flag1=	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, Optimized));
		if (flag1){
		report.updateTestLog("Check weather Optimized for use on Mobile is appearing in  "+ "3rd Party Interactive Link", "Optimized for use on Mobile is appearing in "+"3rd Party Interactive Link",Status.PASS);
		}else{
		report.updateTestLog("Check weather Optimized for use on Mobile is appearing in  "+ "3rd Party Interactive Link", "Optimized for use on Mobile is not appearing in  "+"3rd Party Interactive Link",Status.FAIL);
		}
		
		//check default selection
		String No = "//*[@class='thirdpartytable']//input[@class='createEditThirdRadioNo'][@id='radio2']";
		UIHelper.waitForVisibilityOfEleByXpath(driver, No);
		
		WebElement w2 =	driver.findElement(By.xpath(No));
		UIHelper.highlightElement(driver,w2);
		Boolean flag2=	w2.isSelected();
		if (flag2){
		report.updateTestLog("Check weather No is selected by default in  "+ "3rd Party Interactive Link", "No is selected by default in "+"3rd Party Interactive Link",Status.PASS);
		}else{
		report.updateTestLog("Check weather No is selected by default in  "+ "3rd Party Interactive Link", "No is not selected by default in   "+"3rd Party Interactive Link",Status.FAIL);
		}
		String filePath = dataTable.getData("MyFiles", "FilePath"); 
		//avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "credit","", System.getProperty("user.dir")+filePath+fileName);
		avsmart.entersmarttypedata("thirdPartyInteractiveLink", title, extURLLink, "Image", "Caption", "credit","",  System.getProperty("user.dir")+filePath+fileName); 
		
		avsmart.subcancelbtn("thirdPartyInteractiveLink","Submit");
		
	
	}
		

	@Override
	public void tearDown() {

	}
}
