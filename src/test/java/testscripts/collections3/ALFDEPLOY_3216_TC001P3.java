package testscripts.collections3;

import java.io.File;
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
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3216_TC001P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_036() {
		testParameters
				.setCurrentTestDescription("1) can log in as a coordinator, navigate to a course object in the collections UI of a collections site I am a member of and see the same custom actions as a collaborator"+
		"Part2 login as Coordinator and see available options "
						+"2) when viewing a course object in a collection can see an action to 'auto-link assets' underneath the 'duplicate all to..' action");
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
				//String siteName = sitesPage.getCreatedSiteName();
				
				String siteNameValue = dataTable.getData("Sites", "SiteName");
				
		//go to Created content object's view details page in collections UI
				sitesPage.openSiteFromRecentSites(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();				
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
		
		//Open view details page as SSO1 [Coordinator]
				collectionPg.clickOnMoreSetting("AutoContentObj");
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				ArrayList<String> availableOptions2 = new ArrayList<String>();
				availableOptions2 = collectionPg.readMoreOptions("AutoContentObj");
				UIHelper.waitFor(driver);
				
		//Read available options and compare with previous list of available options
				String testOutputFilePath1 = "src/test/resources/AppTestData/TestOutput/temp1.txt";
				ArrayList<String> availableOptions1 = new ArrayList<String>();

			try {
				availableOptions1=new FileUtil().readListOFDataFromFile(testOutputFilePath1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			if(availableOptions1.equals(availableOptions2)){
				
				report.updateTestLog("For Collections Object: AutoContentObj" , "available options as Collaborator: " + availableOptions1 + "and available options as Coordinator:" +availableOptions2 , Status.PASS);
				
			}
			else{
				report.updateTestLog("For Collections Object: AutoContentObj" , "available options as Collaborator: " + availableOptions1 + "and available options as Coordinator:" +availableOptions2 , Status.FAIL);

			}
			
			//Verify Auto-Link-Assets for Course
			
			collectionPg.clickOnMoreSetting("Course");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			ArrayList<String> availableOptions3 = new ArrayList<String>();
			availableOptions3 = collectionPg.readMoreOptions("Course");
			UIHelper.waitFor(driver);
			
			System.out.println(availableOptions3);
			
			if(availableOptions3.contains("Auto-link assets")){
				report.updateTestLog("For Collections Object: Course" , "Auto-link assets is exist out of  " + availableOptions3 , Status.PASS);

				
			}
			else{
				report.updateTestLog("For Collections Object: Course" , "Auto-link assets is exist out of  " + availableOptions3 , Status.FAIL);

				
			}

			
				


			
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
