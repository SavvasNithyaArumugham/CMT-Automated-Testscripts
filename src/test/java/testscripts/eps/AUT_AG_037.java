package testscripts.eps;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_037 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_032()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the Publishing Options UI on clicking Batch Publish via selected items menu with "
				+ "more than one assets selected."
				+"<br>Verify the Publish the selected objects option is displayed as a dropdown with configured institutions."
				+"<br>Verify the navigation on clicking Cancel button with any institution selected from Publishing "
				+ "options screen during batch publish."
				+"<br>Verify the navigation on clicking Batch publish button with any institution selected from "
				+ "Publishing options screen during batch publish."
				+"<br>Verify all the selected assets getting queued for publishing on clicking batch publish button"
				+ " with any institution selected from publishing options via Batch publish."
				+"<br>Verify the checkbox to publish as a ZIP when the selected assest contains ZIP file during Batch Publish"
				+"<br>Verify all the selected assets getting queued for publishing on clicking batch publish "
				+ "button with any institution selected from publishing options via Batch publish"
				+"<br>Verify the Publish the selected object option is displayed as a dropdown with configured institutions."
				+"<br>Verify the Publishing Options UI on clicking Batch Publish via selected items menu with more than one assets selected."
				+"<br>Verify the checkbox not displayed to publish as a ZIP when the selected "
				+ "assest contains not contains ZIP file during Batch Publish"
				);	
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
		
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String batchpublishfilename = dataTable.getData("MyFiles", "CreateFileDetails");
		String batchpublishfilename1 = dataTable.getData("MyFiles", "RelationshipName");
		
		try{
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//	sitesPage.siteFinder(site);
		sitesPage.enterIntoDocumentLibrary();
		//docLibPg.deleteAllFilesAndFolders();
		
	/*	UIHelper.waitFor(driver);
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		UIHelper.waitFor(driver);
		*/
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[3]);	
		UIHelper.waitFor(driver);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[4]);	
		UIHelper.waitFor(driver);
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
		UIHelper.waitFor(driver);
		try{
		UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishSelectedFilezipfiles);
		report.updateTestLog("Zip file checkbox", "Selected files contains Zip files. Please select "
				+ "the checkbox to publish those as zip itself. is displayed",Status.FAIL);
		}catch(NoSuchElementException e){
			
			report.updateTestLog("Zip file checkbox", "<b>Selected files contains Zip files. Please select"
					+ " the checkbox to publish those as zip itself. is not displayed",Status.PASS);				
		}
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);

		docLibPg.deleteAllFilesAndFolders();
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		UIHelper.waitFor(driver);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
		UIHelper.waitForPageToLoad(driver);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[2]);	
		UIHelper.waitFor(driver);
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
		UIHelper.waitFor(driver);
		boolean slected= 	UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishtheSelectedOptionsXpath));
		if(slected){
			report.updateTestLog("Publish the selected objects to : ", "Publish the selected objects to : is displayed",Status.PASS);
		}else{
			report.updateTestLog("Publish the selected objects to : ", "Publish the selected objects to : is not displayed",Status.FAIL);
		}
		boolean zipfiles= UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishSelectedFilezipfiles));
		if(zipfiles){
			report.updateTestLog("Zip file checkbox", "Selected files contains Zip files. Please select the checkbox to publish those as zip itself. is displayed",Status.PASS);
		}else{
			report.updateTestLog("Zip file checkbox", "Selected files contains Zip files. Please select the checkbox to publish those as zip itself. is not displayed",Status.FAIL);
		}
		boolean drop1 = UIHelper.checkDropdownListValuesWithExpectedValue(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishInstXpath), "School Content");
		boolean drop2 =UIHelper.checkDropdownListValuesWithExpectedValue(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishInstXpath), "Alfresco CDN");
		if(drop1 && drop2){
			report.updateTestLog("Batch Publish Instution:", "Dropdown values: <b> School Content and Alfresco CDN of the Batch publish Instution are available",Status.PASS);
		}else{
			report.updateTestLog("Batch Publish Instution:", "Dropdown values: School Content and Alfresco CDN of the Batch publish Instution are not available",Status.FAIL);
		}
		System.out.println(batchpublishfilename);
		String value = UIHelper.getTextFromWebElement(driver, epsPg.batchPublishFileNames);
		System.out.println(value);
		
		if(value.contains(batchpublishfilename)||value.contains(batchpublishfilename1)){
			report.updateTestLog("Batch Publish File name:", "values:<b> "+value+" files are ready to publish",Status.PASS);
		}else{
			report.updateTestLog("Batch Publish File name:", "values: "+batchpublishfilename+" files are not ready to publish",Status.FAIL);
		}
		
		
		UIHelper.click(driver, epsPg.batchPublishcancelbutton);
		boolean flag= UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishdocumentsXpath));		
		if(flag){
			report.updateTestLog("Cancel and back Page:", "<b>Clicked on Cancel button and Navigated Back to the Documents Page",Status.PASS);
		}else{
			report.updateTestLog("Cancel and back Page:", "Clicked on Cancel button and not navigated Back to the Documents Page",Status.FAIL);
		}
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
		if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton)))
		{	
		UIHelper.click(driver, epsPg.batchPublishButton);
		UIHelper.waitFor(driver);
		epsPg.batchpublish(fileName[0],"one");
		epsPg.batchpublish(fileName[1],"one");
		epsPg.batchpublish(fileName[2],"one");
		
		}
		
	/*	
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		sitesPage.enterIntoDocumentLibrary();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_37 Status", "EPS 37 Testcases has been failed", Status.FAIL);	
		}

}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}