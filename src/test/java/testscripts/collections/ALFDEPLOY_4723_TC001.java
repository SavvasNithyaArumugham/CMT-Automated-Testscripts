package testscripts.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_4723_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_004() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-4723_Pre-Test Step- Apply Collections Tree filter"
						+"<br>ALFDEPLOY-4723_Apply highlighting to filtered values"
						+"<br>ALFDEPLOY-4723_Confirm VA highlights"
						+"<br>ALFDEPLOY-4723_Confirm Blank, OK and WV highlights"
						+"<br>ALFDEPLOY-4723_Confirm that highlighting a State filter will not apply to other Exclusion states");
		
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue =  dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
	
		Date date = new Date();
	    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	    String BQboarderxpath6 =".//*[@class='highlightedElement']//div[contains(text(),'Exclude VA')]";
	    String BQboarderxpath7 =".//*[@class='highlightedElement']//div[contains(text(),'Exclude OK')]";
	    
	    
		boolean flag = false;
		       // Log in Pearson Schools project
			    functionalLibrary.loginAsValidUser(signOnPage);
			    UIHelper.waitForLong(driver);
	        	//Create site
				homePageObj.navigateToSitesTab();
				UIHelper.waitFor(driver);
/*
				String siteName = sitesPage.getCreatedSiteName();				
				sitesPage.openSiteFromRecentSites(siteName);*/
				 
				sitesPage.createSite(siteNameValue, "Yes");
				UIHelper.waitFor(driver);
			
	         // Go to collection UI
		      sitesPage.enterIntoDocumentLibrary();
		
		     //go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
	
		     // upload course plan	
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForLong(driver);
				UIHelper.pageRefresh(driver);
			    driver.navigate().refresh();
			   
			    sitesPage.enterIntoDocumentLibrary();
		
	
			    //go to course object 1
		             	           
	            myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Completed");
				myFiles.openCreatedFolder(currentDate.substring(0, 4));
				myFiles.openCreatedFolder(currentDate.substring(5, 7));
				myFiles.openCreatedFolder(currentDate.substring(8, 10));
				report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
				
		/*************************Pre-Test Step: Apply Collections Tree filter*********************************/
	
				// Go to Courses and navigate to Content Type
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetailsColl(folderNames[0]);
				sitesPage.documentdetailsColl(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.ClickonFilter("filter");
				collectionPg.SelectionOfFilter("versionState","FilterCollection_VersionState");
				collectionPg.ClickonFilter("filterbutton");
	
				
		/*******************************Apply highlighting to filtered values********************************/		
				
				collectionPg.SummaryreportsofFilter();
				collectionPg.ClickonFilter("clickhere");
				UIHelper.waitForPageToLoad(driver);
				String filterselection = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
				collectionPg.Summaryfiltervalidation(filterselection);
				UIHelper.click(driver, "//*[@id='applyHighlightCheckBox-1']");
				collectionPg.ClickonFilter("OKbutton");	
				
		/********************************Confirm VA highlights*********************************************/
				
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, ".//span[contains(@class,'dynatree-expander')]");
				UIHelper.click(driver, ".//span[contains(@class,'dynatree-expander')]");			
				UIHelper.waitFor(driver);
				collectionPg.verifyFiterHighlights("eText_ Grade 5 - VA","#fec0871","background-color","Orange");
				
 				collectionPg.verifyFiterHighlights("Overview_ Grade 5 - VA","#fec0871","background-color","Orange");
				
		/**********************************Confirm Blank, OK and WV highlights**********************************/
				
				
				collectionPg.ClickonFilter("clickhere");
				UIHelper.click(driver, "//*[@id='applyHighlightCheckBox-1']");
				UIHelper.click(driver, "//*[@id='applyHighlightCheckBox-0']");
				UIHelper.click(driver, "//*[@id='applyHighlightCheckBox-2']");
				collectionPg.ClickonFilter("OKbutton");	
				
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				collectionPg.verifyFiterHighlights("eText_ Grade 5 - OK","#ffff881","background-color","Yellow");
				collectionPg.verifyFiterHighlights("Overview_ Grade 5 - OK","#ffff881","background-color","Yellow");
				
				collectionPg.verifyFiterHighlights("eText_ Grade 5 - WV","#ff80801","background-color","Red");
				collectionPg.verifyFiterHighlights("Overview_ Grade 5 - WV","#ff80801","background-color","Red");
			
				
				/**********************************Confirm that highlighting a State filter will not apply to other Exclusion states*************************************/
				
				collectionPg.ClickonFilter("filter");
				Actions ac = new Actions(driver);
				ac.keyDown(Keys.CONTROL);
				
				WebElement object = driver.findElement(By.xpath("//select[contains(@id,'versionState')]/option[@value='BLANK (No Value)']"));
				UIHelper.highlightElement(driver, object);
				object.click();
				
				WebElement object1 = driver.findElement(By.xpath("//select[contains(@id,'versionState')]/option[@value='VA']"));
				UIHelper.highlightElement(driver, object1);
				object1.click();
				
				WebElement object2 = driver.findElement(By.xpath("//select[contains(@id,'versionState')]/option[@value='OK']"));
				UIHelper.highlightElement(driver, object2);
				object2.click();
				
				
				UIHelper.waitFor(driver);
				ac.keyDown(Keys.CONTROL);
				ac.keyUp(Keys.CONTROL);
				collectionPg.ClickonFilter("filterbutton");
				
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, ".//span[contains(@class,'dynatree-expander')]");
				UIHelper.click(driver, ".//span[contains(@class,'dynatree-expander')]");			
				UIHelper.waitFor(driver);
				String ElementTxt1 = UIHelper.getTextFromWebElement(driver, BQboarderxpath6);
				String ElementTxt2 = UIHelper.getTextFromWebElement(driver, BQboarderxpath7);
				
				if((UIHelper.checkForAnElementbyXpath(driver,BQboarderxpath6))&&(UIHelper.checkForAnElementbyXpath(driver,BQboarderxpath7)))
				{	
					report.updateTestLog(" view the immediate children of the Course object and confirm that the following objects are present",
							ElementTxt1 + " Object is the immediate children of the Course object", Status.PASS);
					report.updateTestLog(" view the immediate children of the Course object and confirm that the following objects are present",
							ElementTxt2 + " Object is the immediate children of the Course object", Status.PASS);
				}
				else
				{
					report.updateTestLog("view the immediate children of the Course object and confirm that the following objects are present",
							" Objects are not the immediate children of the Course object", Status.FAIL);
				}
				collectionPg.ClickonFilter("clickhere");
				UIHelper.waitForPageToLoad(driver);
				UIHelper.click(driver, "//*[@id='applyHighlightCheckBox-1']");
				collectionPg.ClickonFilter("OKbutton");
				
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				
				collectionPg.verifyFiterHighlights("eText_ Grade 5 - WV","#fec0871","background-color","Orange");
 				collectionPg.verifyFiterHighlights("Overview_ Grade 5 - WV","#fec0871","background-color","Orange");
				
			}	  

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
	}

	}

