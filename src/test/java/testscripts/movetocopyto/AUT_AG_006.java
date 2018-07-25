package testscripts.movetocopyto;


import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_006 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void movetocopyto_03() {
		testParameters.setCurrentTestDescription(
		"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
		+ "folders/files in myfiles"
				
		+	"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
		+ "folders/files in sharedfiles"
		
		+	"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
		+ "folders/files in search result page"
		
		+	"ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for multiple "
		+ "folders/files by using selected items dropdown"
						
				);
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
		AlfrescoSearchPage SearchPage = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");

		String SearchName = dataTable.getData("Search", "FileName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		String options = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		functionalLibrary.loginAsValidUser(signOnPage);
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		report.updateTestLog("Testcase Name","ALFDEPLOY-3980_Verify user should not see create link option in"
						+ " copy to screen for multiple folders/files by using selected items dropdown",	Status.DONE);
		
		
		for(String name:fileName){
		myFiles.uploadFileInMyFilesPage(filePath, name);
		}
		
		docLibPage.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		UIHelper.waitForPageToLoad(driver);
		sitesPage.selectItemFromSelectedItemsMenuOption(options);	
		
		try{
		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, docLibPage.createlinkxpath));
		report.updateTestLog("Create Link option presence:","Create Link option is presented ",	Status.FAIL);
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Create Link option presence:","Create Link option is not presented ",Status.PASS);
		}
		
		report.updateTestLog("Testcase Name","ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
		+"folders/files in <br> myfiles ",	Status.DONE);

		
		
		UIHelper.click(driver, docLibPage.myfilesxpath);
		
		docLibPage.createlinkpresence(folderName,folderDetails,fileName,filePath,options);
		
		report.updateTestLog("Testcase Name","ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
				+"folders/files in <br>Shared Files",	Status.DONE);
		
		UIHelper.click(driver, docLibPage.sharedfilesXpath);
		docLibPage.createlinkpresence(folderName,folderDetails,fileName,filePath,options);
		
		report.updateTestLog("Testcase Name","ALFDEPLOY-3980_Verify user should not see create link option in copy to screen for any "
		+ "folders/files in search result page",	Status.DONE);

		// In Search page:
		homePageObj.navigateToAdvSearch();

		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);		
		String Resultfile = SearchPage.tempactionMouseXpath.replace(
				"CRAFT", SearchName);	
		UIHelper.waitForVisibilityOfEleByXpath(driver, Resultfile);
		//UIHelper.mouseOveranElement(driver, UIHelper
		//		.findAnElementbyXpath(driver, Resultfile));
		String finalactionMenuXpath = SearchPage.tempactionMenuXpath
				.replace("CRAFT", SearchName);
		UIHelper.findAnElementbyXpath(driver,
				finalactionMenuXpath).click();
		UIHelper.waitFor(driver);
		UIHelper.click(driver, SearchPage.copybuttonxpath);	
		
		try{
		Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, docLibPage.createlinkxpath));
		report.updateTestLog("Create Link option presence:","Create Link option is presented ",	Status.FAIL);
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("Create Link option presence:","Create Link option is not presented ",Status.PASS);
		}
		
	
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}