package testscripts.eps;



import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_038 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_038()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the checkbox not displayed to publish as a ZIP when the selected "
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		try{
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//	sitesPage.siteFinder(site);
		sitesPage.enterIntoDocumentLibrary();
/*		docLibPg.deleteAllFilesAndFolders();
		
		UIHelper.waitFor(driver);
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		UIHelper.waitFor(driver);*/
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		UIHelper.waitFor(driver);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
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
		
/*		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		sitesPage.enterIntoDocumentLibrary();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_38 Status", "EPS 38 Testcases has been failed", Status.FAIL);	
		}
		
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}