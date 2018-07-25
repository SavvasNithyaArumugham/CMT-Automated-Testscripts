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
public class AUT_AG_039 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_039()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the publish of ZIP file via batch publish with checkbox unselected on "
				+ "Publishing options screen."
				
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
		
		UIHelper.waitFor(driver);
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		UIHelper.waitFor(driver);
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
		UIHelper.waitForPageToLoad(driver);

		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
		if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton)))
		{
		UIHelper.click(driver, epsPg.batchPublishButton);		
		epsPg.batchpublish(fileName[0],"one");
		epsPg.batchpublish(fileName[1],"one");
		collectionPg.clickOnMoreSetting(fileName[0]);
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[0],Option[0]);
		//System.out.println(fileName[0]);
		String Name = fileName[0].replace(".zip", "");
		//System.out.println(Name);
		String player = epsPg.playerhtmlXpath.replace("CRAFT", Name);
		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, player));
		//System.out.println(flag);
		String url = UIHelper.getTextFromWebElement(driver, player);
		//System.out.println(url);
		if(flag){
			report.updateTestLog("Publication URL:", "Publication url is ended as player html as:"+url, Status.PASS);
		}else{
			report.updateTestLog("Publication URL:", "Publication url is not ended as player html as:"+url, Status.FAIL);		
		}
/*		UIHelper.pageRefresh(driver);
		epsPg.DeleteFolderandFile();
		UIHelper.pageRefresh(driver);
		epsPg.DeleteFolderandFile();
		UIHelper.pageRefresh(driver);
		epsPg.DeleteFolderandFile();*/
		
		}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_39 Status", "EPS 39 Testcases has been failed", Status.FAIL);	
		}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}