package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
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
public class AUT_AG_036 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_032()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify on clicking Batch publish"
				+ " via selected items menu with more than one assets selected for a Non EPS site<br>"
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
		try{
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
		
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
	
		
	
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
	
		collectionPg.uploadFileInCollectionSite(filePath, fileName[2]);	
		
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");
	
		UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
		if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton))){
		UIHelper.click(driver, epsPg.batchPublishButton);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.sitenotconfiguredxpath));
		if(flag){
			report.updateTestLog("Site not configured Message:", "<b> 'Site is not configured for publishing' is displayed", Status.PASS);				
		}else{
			report.updateTestLog("Site not configured Message:", "<b> 'Site is not configured for publishing' is not displayed", Status.FAIL);			
			
		}
		}
		}catch(Exception e){
			report.updateTestLog("EPS_36 Status", "EPS 36 Testcases has been failed", Status.FAIL);	
		}
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}