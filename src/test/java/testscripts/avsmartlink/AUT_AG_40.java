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
import java.util.Random;
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

public class AUT_AG_40 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify the 3PI vendor dropdown and Optimized for use on mobile radio button options are hidden in Create smartlink of Image External or Video External or Audio External or External website or PDF or Metrodigi or MD pop up link");
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
	String[] smartlinktype =dataTable.getData("MyFiles", "CreateFolder").split(",");
	String typeofsmart="";
	
	

    // Log in Pearson Schools project
	  functionalLibrary.loginAsValidUser(signOnPage);
	
	  //Create site
	 UIHelper.waitForPageToLoad(driver);

	   homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");
		//sitesPage.createSite(siteNameValue, "Yes");
		sitesPage.siteFinder(siteNameValue);
	

     // Go to document library
	    sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitForPageToLoad(driver);	
	    UIHelper.waitForPageToLoad(driver);
	

	// Navigate to Smart link Page
	    for (String link:smartlinktype){
	    
			 if(link.equalsIgnoreCase("mdPopUp ")){
				typeofsmart= "MD Pop Up ";	
				String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
                String PIvendor=avsmart.sample2.replace("CRAFT", link);
                if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                     
                      report.updateTestLog(
                                  "Verify Optimized for use on mobile field is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
              if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                     
                      report.updateTestLog(
                                  "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }


                            }
				
			else if(link.equalsIgnoreCase("metrodigiLink")){
			    typeofsmart= "Metrodigi Link";	
			    
			    String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
                String PIvendor=avsmart.sample2.replace("CRAFT", link);
                if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                     
                      report.updateTestLog(
                                  "Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
              if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                     
                      report.updateTestLog(
                                  "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }


                            }
			    
				
			else if(link.equalsIgnoreCase("videoExternalLink")){
				typeofsmart= "Video External Link";	
				String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
                String PIvendor=avsmart.sample2.replace("CRAFT", link);
                if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                     
                      report.updateTestLog(
                                  "Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
              if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                     
                      report.updateTestLog(
                                  "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
			}
		
			else if(link.equalsIgnoreCase("imageExternalLink")){
				typeofsmart= "Image External Link";	
				String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
                String PIvendor=avsmart.sample2.replace("CRAFT", link);
                if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                     
                      report.updateTestLog(
                                  "Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
              if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                     
                      report.updateTestLog(
                                  "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
                            }
		      
			else if(link.equalsIgnoreCase("audioExternalLink")){
				typeofsmart= "Audio External Link";
				String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
                String PIvendor=avsmart.sample2.replace("CRAFT", link);
                if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                     
                      report.updateTestLog(
                                  "Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
              if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                     
                      report.updateTestLog(
                                  "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }               
		      
		}
			else if(link.equalsIgnoreCase("externalWebsiteLink")){
				typeofsmart= "External Website Link";
				String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
                String PIvendor=avsmart.sample2.replace("CRAFT", link);
                if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                     
                      report.updateTestLog(
                                  "Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                                  "Optimized for use on mobile field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
              if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                     
                      report.updateTestLog(
                                  "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is hidden for type"
                                              + typeofsmart, Status.PASS);
                } else {
                      report.updateTestLog(
                                  " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                                  "3PI field  is present for type"
                                              + typeofsmart, Status.FAIL);
                }
			}

                          
		else if(link.equalsIgnoreCase("pdfLink")){
			typeofsmart= "PDF Link";
			String finalimgrefURL = avsmart.sample1.replace("CRAFT", link);                                                          
            String PIvendor=avsmart.sample2.replace("CRAFT", link);
            if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
                 
                  report.updateTestLog(
                              "Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                              "Optimized for use on mobile field  is hidden for type"
                                          + typeofsmart, Status.PASS);
            } else {
                  report.updateTestLog(
                              " Verify Optimized for use on mobile field  is hidden in Create smartlink of" + typeofsmart,
                              "Optimized for use on mobile field  is present for type"
                                          + typeofsmart, Status.FAIL);
            }
          if (!UIHelper.checkForAnElementbyXpath(driver, PIvendor)) {
                 
                  report.updateTestLog(
                              "Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                              "3PI field  is hidden for type"
                                          + typeofsmart, Status.PASS);
            } else {
                  report.updateTestLog(
                              " Verify 3PI field  is hidden in Create smartlink of" + typeofsmart,
                              "3PI field  is present for type"
                                          + typeofsmart, Status.FAIL);
            }
		}
		
	    myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType(typeofsmart, link);
	    }
            }
		
	
	@Override
	public void tearDown() {

	}
		}
		
		

