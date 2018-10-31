package testscripts.collections3;

import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
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
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3216_TC001P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_036() {
		testParameters
				.setCurrentTestDescription("1) can log in as a coordinator, navigate to a course object in the collections UI of a collections site I am a member of and see the same custom actions as a collaborator"+
		"Part2 login as Collaborator and see available options ");
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

		
		//Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//open site created from ALFDEPLOY_3216_TC001P1
				homePageObj.navigateToSitesTab();
				String siteName = sitesPage.getCreatedSiteName();
				
				
		//go to Created content object's view details page in collections UI
				sitesPage.openSiteFromRecentSites(siteName);
				sitesPage.enterIntoDocumentLibrary();				
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
//		//enter into default course object
//				String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
//				collectionPg.openCollectionObject(collectionObjectName);
				
		//Open view details page as SSO4 [Collaborator]
				collectionPg.clickOnMoreSetting("AutoContentObj");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				ArrayList<String> availableOptions = new ArrayList<String>();
				availableOptions = collectionPg.readMoreOptions("AutoContentObj");
				UIHelper.waitFor(driver);
				String testOutputFilePath1 = "src/test/resources/AppTestData/TestOutput/temp1.txt";
				new FileUtil().writeListTextToFile(availableOptions, testOutputFilePath1);
				
			
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
