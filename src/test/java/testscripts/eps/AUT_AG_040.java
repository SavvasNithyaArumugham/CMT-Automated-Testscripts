package testscripts.eps;

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
public class AUT_AG_040 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_040()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the publish of ZIP file via batch publish with checkbox unselected on "
				+ "Publishing options screen."
				+"Verify the spinning indicator when the publishing is in progress for an asset/folder"
				+"Verify the Green numbered sequence indicator when the asset is successfully published"
				+ " based on how many institutions it got published"
				+"Verify the publish of sco ZIP file via batch publish with checkbox selected on Publishing options screen"
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
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String filepubinprogress = epsPg.filepublishinprogress.replace("CRAFT", fileName[0]);	
		
		try{
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//	sitesPage.siteFinder(site);
		sitesPage.enterIntoDocumentLibrary();
	/*	docLibPg.deleteAllFilesAndFolders();
		
		UIHelper.waitForLong(driver);
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		
	/*	try{
			String sco= epsPg.foldernameofdoc.replace("CRAFT", "sco");
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, sco))){
			myFiles.openCreatedFolder(folderName);
			epsPg.DeleteFolderandFile();
			epsPg.DeleteFolderandFile();
			epsPg.DeleteFolderandFile();
			sitesPage.enterIntoDocumentLibrary();
			docLibPg.deleteAllFilesAndFolders();
			}
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
	
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	

		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	


		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
		if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton)))
		{
		UIHelper.highlightElement(driver, epsPg.zipuploadxpath);
		UIHelper.click(driver, epsPg.zipuploadxpath);
		UIHelper.highlightElement(driver, epsPg.batchPublishButton);
		UIHelper.click(driver, epsPg.batchPublishButton);
	

		/*Boolean flag2= UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchqueuedXpath));
		if(flag2){
			report.updateTestLog("Batch queued successfully for publishing:", "Batch queued successfully for publishing is displayed", Status.PASS);
		}else{
			report.updateTestLog("Batch queued successfully for publishing:", "Batch queued successfully for publishing is not displayed", Status.FAIL);		
		}*/
		UIHelper.waitForLong(driver);
		
		
		
		UIHelper.waitForVisibilityOfEleByXpath(driver, filepubinprogress);
		
		Boolean flag1 = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, filepubinprogress));
		if(flag1){
			UIHelper.highlightElement(driver, filepubinprogress);
			report.updateTestLog("PublishInprogress:", "Spinning indicator is displayed for the asset", Status.PASS);
		}else{
			report.updateTestLog("PublishInprogress:", "Spinning indicator is not displayed for the asset", Status.FAIL);		
		}
		epsPg.batchpublish(fileName[0],"one");
		epsPg.batchpublish(fileName[1],"one");
		
		// Validationfor the Publishin options not available
		collectionPg.clickOnMoreSetting(fileName[0]);
		boolean flag3 = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[0],Option[3]);
		if(flag3){
			report.updateTestLog("Publishing Options:", "Publishing Option is presented "
					+ "for "+"<b> "+fileName[0], Status.FAIL);
		}else{
			report.updateTestLog("Publishing Options:", "Publishing Option is not presented "
					+ "for "+"<b> "+fileName[0], Status.PASS);
		}
		
		
		
		
	/*	collectionPg.clickOnMoreSetting(fileName[0]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[0],Option[0]);		
		String player = epsPg.publicationurldynamic.replace("CRAFT", "Alfresco CDN");		
		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, player));
		UIHelper.highlightElement(driver, player);
		String url = UIHelper.getTextFromWebElement(driver, player);
		
		if(flag){
			report.updateTestLog("Publication URL:", "Publication url is ended as .zip as:<b>"+url, Status.PASS);
		}else{
			report.updateTestLog("Publication URL:", "Publication url is not ended as .zip as:<b>"+url, Status.FAIL);		
		}
		
		UIHelper.click(driver, epsPg.batchPublishcancelbutton);*/
	/*	UIHelper.waitForPageToLoad(driver);
		epsPg.DeleteFolderandFile();
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		epsPg.DeleteFolderandFile();
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		epsPg.DeleteFolderandFile();*/
		}
			
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_40 Status", "EPS 40 Testcases has been failed", Status.FAIL);		
		}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}