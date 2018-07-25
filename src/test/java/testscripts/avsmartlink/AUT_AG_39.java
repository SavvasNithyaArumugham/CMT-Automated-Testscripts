package testscripts.avsmartlink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;
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

public class AUT_AG_39 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-3873_Verify user can edit the 3PI vendor dropdown and optimized for use on mobile radio button options via Edit smartlink "
				+"2.Verify the options(Social Explorer,A Closer Look,Geogebra) of 3PI Vendor dropdown of Create smartlink of 3rd party interactive link and displaying in alphabetical order");
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
	AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
	AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);	
	AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(scriptHelper);
	
	
	String fileName = dataTable.getData("MyFiles", "FileName");
	
	String type = dataTable.getData("Home", "DashletName");
	String title = dataTable.getData("Document_Details", "FilePath");
	String extURLLink = dataTable.getData("Document_Details", "FileName");
	String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
	//String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
	
	//contains(text(),'+filename+')]
	

    // Log in Pearson Schools project
	  functionalLibrary.loginAsValidUser(signOnPage);
	
	  //Create site
	 UIHelper.waitForPageToLoad(driver);
	 
	
	homePageObj.navigateToSitesTab();
	String siteNameValue =  dataTable.getData("Sites", "SiteName");
//sitesPage.createSite(siteNameValue, "Yes");
	UIHelper.waitForPageToLoad(driver);	
	sitesPage.siteFinder(siteNameValue);
	docLibPg.deleteAllFilesAndFolders();
	

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
		avsmart.clickSmartLinkType("3rd Party Interactive Link", "3rd Party Interactive Link");
	
	//Geogebra
	String PI1 = "//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]";
	UIHelper.waitForVisibilityOfEleByXpath(driver, PI1);
			
	WebElement dropdown=driver.findElement(By.xpath(PI1));
	Select s1= new Select(dropdown);
	List<WebElement>alloptions = s1.getOptions(); 
	
	//checking alphabetical order
	for(int i=0; i<alloptions.size(); i++){
		System.out.println(alloptions.get(i).getText());
	}
	List<String> alloptions1 = new ArrayList<String>();
	
	
	
	
	//Selecting Geogebra
	dropdown.click();
	s1.selectByVisibleText("Geogebra");
	UIHelper.waitForLong(driver);

		
	// OptimizedthirdPartyInteractiveLink
	
	String Optimized1 = "//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']";

	UIHelper.waitForVisibilityOfEleByXpath(driver, Optimized1);
			
	WebElement w2=	driver.findElement(By.xpath(Optimized1));
	UIHelper.highlightElement(driver,w2);
    w2.click();
	
	UIHelper.waitForPageToLoad(driver);
	 String filePath = dataTable.getData("MyFiles", "FilePath"); 
		//avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "credit","", System.getProperty("user.dir")+filePath+fileName);
		avsmart.entersmarttypedata("thirdPartyInteractiveLink", title, extURLLink, "Image", "Caption", "credit","",""); 
		
		avsmart.subcancelbtn("thirdPartyInteractiveLink","Submit");
		UIHelper.waitForPageToLoad(driver);
		
	report.updateTestLog("Check whether 3rd party interactive link with 3PI vendor(Geogebra) and Optimized for use on mobile is created" , "Status: "  +"3rd Party Interactive smartlink is created", Status.PASS);
	
	   //More option
	
	    sitesPage.enterIntoDocumentLibrary();
	    myFiles.openCreatedFolder("Auto3Pi");
	    collectionPg.clickOnMoreSetting("Auto3Pi");
		collectionPg.commonMethodForClickOnMoreSettingsOption("Auto3Pi","Edit SmartLink");
		
		 /* Edit smart link
		    String Editsmartlink = "//*[@class='action-link'][contains(@title,'Edit SmartLink')]";
			UIHelper.waitForVisibilityOfEleByXpath(driver, Editsmartlink);
					
			WebElement w3=driver.findElement(By.xpath(Editsmartlink));
			w3.click();
			Select s3= new Select(w3);
			s3.selectByVisibleText("Edit SmartLink");
			*/
		   UIHelper.waitForPageToLoad(driver);

		//A Closer Look
		String PI = "//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]";
		UIHelper.waitForVisibilityOfEleByXpath(driver, PI);
				
		WebElement w4=driver.findElement(By.xpath(PI));
		
		   w4.click();
		Select s4= new Select(w4);
		
		s4.selectByVisibleText("A Closer Look");
        UIHelper.waitForLong(driver);
			
		// OptimizedthirdPartyInteractiveLink
		
		String Optimized = "//*[@class='thirdpartytable']//input[@class='createEditThirdRadioNo'][@id='editradio2']";

		UIHelper.waitForVisibilityOfEleByXpath(driver, Optimized);
				
		WebElement w5 =	driver.findElement(By.xpath(Optimized));
		UIHelper.highlightElement(driver,w5);
	    w5.click();  
	   
	    avsmart.subcancelbtn("thirdPartyInteractiveLink","Submit");
		UIHelper.waitForPageToLoad(driver);
		
	report.updateTestLog("Check whether 3rd party interactive link is edited with 3PI vendor(A Closer Look) and without Optimized for use on mobile is created" , "Status: "  +"3rd Party Interactive smartlink is edited", Status.PASS);
	
	WebElement element1 = driver.findElement(By.xpath("//*[contains(text(),'Auto3Pi')][@id='yui-gen97']"));
	UIHelper.mouseOveranElement(driver,element1);
	
	report.updateTestLog("Check whether 3rd party interactive link version is incremented" , "Status: "  +"3rd Party Interactive smartlink is icremented", Status.PASS);
	
	
	

	
	}
	
	
	@Override
	public void tearDown() {

	}
}
