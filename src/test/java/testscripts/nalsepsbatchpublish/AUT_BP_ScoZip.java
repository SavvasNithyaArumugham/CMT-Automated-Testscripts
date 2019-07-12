package testscripts.nalsepsbatchpublish;

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
 * Test for login with user credentials
 * @author Cognizant
 */
public class AUT_BP_ScoZip extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_ScoZip()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the publish of ZIP file via batch publish with checkbox unselected/selected on "
				+ "Publishing options screen."
				+"Verify the spinning indicator when the publishing is in progress for an asset/folder"
				+"Verify the Green numbered sequence indicator when the asset is successfully published");	
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
		String files = dataTable.getData("MyFiles", "CreateFolder");
		String filepubinprogress = epsPg.filepublishinprogress.replace("CRAFT", fileName[0]);	
		
		try{
		
		functionalLibrary.loginAsValidUser(signOnPage);	
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	

		UIHelper.waitFor(driver);
		myFiles.commonMethodForSelectingFiles(fileName[0]);		
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
		UIHelper.waitForLong(driver);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		UIHelper.waitFor(driver);
		myFiles.commonMethodForSelectingFiles(fileName[1]);		
		sitesPage.clickOnSelectedItems();
		
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
		if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton)))
		{
		UIHelper.highlightElement(driver, epsPg.zipuploadxpath);
		UIHelper.click(driver, epsPg.zipuploadxpath);
		UIHelper.waitFor(driver);
		UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishSelectedFilezipfiles).click();
		UIHelper.waitFor(driver);
		UIHelper.click(driver, epsPg.batchPublishButton);
		UIHelper.waitForLong(driver);
		}
		
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		epsPg.batchpublish(fileName[0],"one");
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		epsPg.batchpublish(fileName[1],"one");
		
		/*AlfrescoEPSPage epsPage=new AlfrescoEPSPage(scriptHelper);
		
		sitesPage.enterIntoDocumentLibrary();
		if (files.contains(",")) {
			String splittedFileNames[] = files.split(",");
			for (String fileNameVal : splittedFileNames) {
				UIHelper.waitFor(driver);
				epsPage.checkEPSID(fileNameVal);
				UIHelper.waitFor(driver);
			}
		} else {
			UIHelper.waitFor(driver);
			epsPage.checkEPSID(files);
			UIHelper.waitFor(driver);
			
		}*/
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS SCO ZIP Batch Publish Status", "EPS Sco Zip BP", Status.FAIL);		
		}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}