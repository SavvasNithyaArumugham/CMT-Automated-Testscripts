package testscripts.search;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_170P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void search_119() {
		testParameters
				.setCurrentTestDescription(
						"ALFDEPLOY-2347_To verify , user (Manager/co-ordinator/Collaborator/Contributor) can execute all actions successfully from search results using document/folder actions "
								+ "under Selected Item , when performs search for folder via Baisc/Advance search"
								+"ALFDEPLOY-2347_To verify , user (Manager/co-ordinator/Collaborator/Contributor) can execute all actions successfully from search results using document/folder +"
								+ "actions under Selected Item , when performs search for file via Baisc/Advance search"
								+"ALFDEPLOY-2347_To verify , user (Manager/co-ordinator/Collaborator/Contributor) can execute all actions successfully from search results using document/folder +"
								+ "actions under Actions , when performs search for folder via Baisc/Advance search"
								+"ALFDEPLOY-2347_To verify , user (Manager/co-ordinator/Collaborator/Contributor) can execute all actions successfully from search results using document/folder+"
								+ "actions under Actions , when performs search for file via Baisc/Advance search"
						
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
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		
		// Manager
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		
		String fileName[] = dataTable.getData("MyFiles", "Version").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String siteName = dataTable.getData("Sites", "SiteName");
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String actionsvalue[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String selecteditems[] = dataTable.getData("MyFiles", "RelationshipName").split(",");
		String actionsvaluefolder[] = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String selecteditemsfolder[] = dataTable.getData("MyFiles", "Sort Options").split(",");
		
		
			
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		sitesPage.performInviteUserToSite(siteName);
		siteMemPgTest.verifySiteMemebrs(siteName, siteuserName, roleName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		//Folder
		
		myFiles.createFolder(folderDetails);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(fileName[0]);
		
		// File
		
		if (sitesPage.Checkdocument(fileName[1])) {
		} else {
			myFiles.createFile(fileDetails);
		}
		
		
		// File
		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearchparam(fileName[1]);
		appSearchPg.clickSearch();
		UIHelper.click(driver, "//*[@class='filterLabel' and text()='Today']");
		appSearchPgTest.commonMethodForVerifySearchResults(fileName[1]);

		appSearchPg.selValFrmSerchResultDrpDow("All");
		ArrayList<String> options = new ArrayList<String>();
		options=appSearchPg.getSelectedItemsoptions();
		
		for(String value:options){
			System.out.println(value);
			int j=0;
			if(!value.isEmpty()){
			for(String menu:selecteditems){
				if(value.contains(menu)){
					j++;
				}
			}
			
			if(j==1){
				report.updateTestLog("Selected Items Options",
						value+" options is presented in the "+fileName[1], Status.PASS);
			}else{
				report.updateTestLog("Selected Items Options",
						value+" options is not presented in the "+fileName[1], Status.FAIL);
			}
			}
			}
			
		
		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearchparam(fileName[1]);
		appSearchPg.clickSearch();
		
		ArrayList<String> actionsoptions = new ArrayList<String>();
		fileName[1]=fileName[1].replace(".txt", "");
		actionsoptions=appSearchPg.getactionsoptions(fileName[1]);
		
		for(String value:actionsoptions){
			System.out.println(value);
			
			int j=0;
			
			for(String menu:actionsvalue){
				if(value.contains(menu)){
					j++;
				}
			}
			
			if(j==1){
				report.updateTestLog("Actions Options",
						value+" options is presented in the "+fileName[1], Status.PASS);
			}else{
				report.updateTestLog("Actions Options",
						value+" options is not presented in the "+fileName[1], Status.FAIL);
			}
			
		}
		
		
		// Folder
		homePage.navigateToAdvSearch();		
		appSearchPg.selectLookForFieldOption("Folders");
		appSearchPg.inputFileNameAdvSearchparam(fileName[0]);
		appSearchPg.clickSearch();
		UIHelper.click(driver, "//*[@class='filterLabel' and text()='Today']");		
		appSearchPgTest.commonMethodForVerifySearchResults(fileName[0]);	
	
		appSearchPg.selValFrmSerchResultDrpDow("All");
		ArrayList<String> optionsfolder = new ArrayList<String>();
		optionsfolder=appSearchPg.getSelectedItemsoptions();
		
		for(String value:optionsfolder){
			System.out.println(value);
			int j=0;
			if(!value.isEmpty()){
			for(String menu:selecteditemsfolder){
				if(value.contains(menu)){
					j++;
				}
			}
			
			if(j==1){
				report.updateTestLog("Selected Items Options",
						value+" options is presented in the "+fileName[0], Status.PASS);
			}else{
				report.updateTestLog("Selected Items Options",
						value+" options is not presented in the "+fileName[0], Status.FAIL);
			}
			}
			}
			
		
		
		
		ArrayList<String> actionsoptionsfolder = new ArrayList<String>();
		//fileName[1]=fileName[1].replace(".txt", "");
		actionsoptionsfolder=appSearchPg.getactionsoptions(fileName[0]);
		
		for(String value:actionsoptionsfolder){
			System.out.println(value);
			
			int j=0;
			
			for(String menu:actionsvaluefolder){
				if(value.contains(menu)){
					j++;
				}
			}
			
			if(j==1){
				report.updateTestLog("Actions Options",
						value+" options is presented in the "+fileName[0], Status.PASS);
			}else{
				report.updateTestLog("Actions Options",
						value+" options is not presented in the "+fileName[0], Status.FAIL);
			}
			
		}
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}