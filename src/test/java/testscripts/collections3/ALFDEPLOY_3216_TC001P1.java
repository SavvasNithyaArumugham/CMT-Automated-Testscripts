package testscripts.collections3;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3216_TC001P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_036() {
		testParameters
				.setCurrentTestDescription("1) can log in as a coordinator, navigate to a course object in the collections UI of a collections site I am a member of and see the same custom actions as a collaborator"+
		"Part1 is to creatse site and add users");
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
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				homePageObj.navigateToSitesTab();
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				sitesPage.createSite(siteNameValue, "No");
				//String siteName = sitesPage.getCreatedSiteName();
				
		//Add users SSO1,SSO3 as Coordinator,Collaborator respectively
				String userName = dataTable.getData("Sites", "InviteUserName");
				String roleName = dataTable.getData("Sites", "Role");				
				sitesPage.performInviteUserToSite(siteNameValue);
				siteMemPgTest.verifySiteMemebrs(siteNameValue, userName,roleName );
				
		//Create content object in collections UI
				sitesPage.openSiteFromRecentSites(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();				
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				String createObjectData = dataTable.getData("MyFiles","CollectionObjectBasicData");
				collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
				
				sitesPage.enterIntoDocumentLibrary();	
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				
				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting("AutoContentObj");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				//Read available options 
				ArrayList<String> availableOptions = new ArrayList<String>();
				availableOptions = collectionPg.readMoreOptions("AutoContentObj");
				UIHelper.waitFor(driver);
				
				if(availableOptions.contains("Duplicate All")){
					
					report.updateTestLog("Fail" , "opt available: " + availableOptions , Status.FAIL);
					
				}
				else{
					report.updateTestLog("PASS" , "option not available " +availableOptions , Status.PASS);

				}
				
					
			
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
