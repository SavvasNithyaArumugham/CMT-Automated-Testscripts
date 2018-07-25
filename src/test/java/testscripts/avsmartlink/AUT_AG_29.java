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

public class AUT_AG_29 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("ALFDEPLOY-3873_Verify the 3PI vendor dropdown and Optimized for use on mobile radio button options are hidden in Create smartlink of Image External or Video External or Audio External or External website or PDF"
				+ " or Metrodigi or MD pop up link");
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
	String[] smartlink = dataTable.getData("MyFiles", "CreateFolder").split(",");
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
	/*for(String typeofstring:smartlink)
	{
		avsmart.clickSmartLinkType("", "");
		
		
	*/
	//3PI 
		try{
			avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
			UIHelper.waitForLong(driver);
			boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
			if(PIvendor==true)
			{
				report.updateTestLog("Check whether 3PI vendor dropdown is present in Image External Link" , "Status: "  +"3PI vendor dropdown is present", Status.FAIL);
			}
			else
			{
				report.updateTestLog("Check whether 3PI vendor dropdown is present in Image External Link" , "Status: "  +"3PI vendor dropdown is NOT present", Status.PASS);
			}
			
			//OptimizedthirdPartyInteractiveLink
			boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
			if(Optimized1==true)
			{
				report.updateTestLog("Check whether Optimized for use on Mobile? is present in Image External Link" , "Status: "  +"Optimized for use on Mobile? is present", Status.FAIL);
			}
			else
			{
				report.updateTestLog("Check whether Optimized for use on Mobile? is present in Image External Link" , "Status: "  +"Optimized for use on Mobile? is NOT present", Status.PASS);
			}
			
			UIHelper.waitForLong(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				report.updateTestLog("Check whether Optimized for use on Mobile? is present in Image External Link" , "Status: "  +"Optimized for use on Mobile? is NOT present", Status.FAIL);
			}
			
			
			//Video
			try{
				avsmart.clickSmartLinkType("Video External Link", "videoExternalLink");
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Video External Link" , "Status: "  +"3PI vendor dropdown is present in Video External Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Video External Link" , "Status: "  +"3PI vendor dropdown is NOT present in Video External Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{report.updateTestLog("Check whether Optimized for use on Mobile? is present in Video External Link" , "Status: "  +"Optimized for use on Mobile? is present in Video External Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Video External Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Video External Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Video External Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Video External Link", Status.FAIL);
				}
			
		   //Audio external
			try{
				avsmart.clickSmartLinkType("Audio External Link", "audioExternalLink");
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
				
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Audio External Link" , "Status: "  +"3PI vendor dropdown is present in Audio External Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Audio External Link" , "Status: "  +"3PI vendor dropdown is NOT present in Audio External Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Audio External Link" , "Status: "  +"Optimized for use on Mobile? is present in Audio External Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Audio External Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Audio External Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Audio External Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Audio External Link", Status.FAIL);
				}
			
			//External 
			try{
				avsmart.clickSmartLinkType("External Website Link", "externalWebsiteLink");
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
			
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in External Website Link" , "Status: "  +"3PI vendor dropdown is present in External Website Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in External Website Link" , "Status: "  +"3PI vendor dropdown is NOT present in External Website Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in External Website Link" , "Status: "  +"Optimized for use on Mobile? is present in External Website Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in External Website Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in External Website Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in External Website Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in External Website Link", Status.FAIL);
				}
			
			//PDF
			try{
				avsmart.clickSmartLinkType("PDF Link", "pdfLink");
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in PDF Link" , "Status: "  +"3PI vendor dropdown is present in PDF Website Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in PDF Link" , "Status: "  +"3PI vendor dropdown is NOT present in PDF Website Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in PDF Website Link" , "Status: "  +"Optimized for use on Mobile? is present in PDF Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in PDF Website Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in PDF Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in PDF Website Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in PDF Link", Status.FAIL);
				}
			// Metrodigi Link
			try{
				avsmart.clickSmartLinkType("Metrodigi Link", "metrodigiLink");
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
				
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Metrodigi Link" , "Status: "  +"3PI vendor dropdown is present in Metrodigi Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Metrodigi Link" , "Status: "  +"3PI vendor dropdown is NOT present in Metrodigi Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Metrodigi Link" , "Status: "  +"Optimized for use on Mobile? is present in Metrodigi Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Metrodigi Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Metrodigi Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Metrodigi Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Metrodigi Link", Status.FAIL);
				}

			//MD Pop Up 
			try{
				avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
				UIHelper.waitForLong(driver);
				UIHelper.waitForLong(driver);
				
				
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
				
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in MD Pop Up Link" , "Status: "  +"3PI vendor dropdown is present in MD Pop Up Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in MD Pop Up Link" , "Status: "  +"3PI vendor dropdown is NOT present in MD Pop Up Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in MD Pop Up Link" , "Status: "  +"Optimized for use on Mobile? is present in MD Pop Up Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in MD Pop Up Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in MD Pop Up Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in MD Pop Up Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in MD Pop Up Link", Status.FAIL);
				}
			//TABLE
			
			try{
				avsmart.clickSmartLinkType("Table Link", "tableLink");
				UIHelper.waitForLong(driver);
				boolean PIvendor = driver.findElement(By.xpath("//*[@class='dropDownBox'][contains(@id,'3PIVendorDDthirdPartyInteractiveLink')]")).isDisplayed();
				if(PIvendor==true)
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Table Link" , "Status: "  +"3PI vendor dropdown is present in Table Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether 3PI vendor dropdown is present in Table Link" , "Status: "  +"3PI vendor dropdown is NOT present in Table Link", Status.PASS);
				}
				
				//OptimizedthirdPartyInteractiveLink
				boolean Optimized1 = driver.findElement(By.xpath("//*[@class='thirdpartytable']//input[@class='createEditThirdRadioYes'][@id='radio1']")).isDisplayed();
				if(Optimized1==true)
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Table Link" , "Status: "  +"Optimized for use on Mobile? is present in Table Link", Status.FAIL);
				}
				else
				{
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Table Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Table Link", Status.PASS);
				}
				
				UIHelper.waitForLong(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					report.updateTestLog("Check whether Optimized for use on Mobile? is present in Table Link" , "Status: "  +"Optimized for use on Mobile? is NOT present in Table Link", Status.FAIL);
				}
			}


	@Override
	public void tearDown() {

	}

}

		
	
	
	
	
