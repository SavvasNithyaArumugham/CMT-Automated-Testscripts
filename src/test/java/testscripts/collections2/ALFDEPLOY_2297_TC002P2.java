package testscripts.collections2;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2297_TC002P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void COLLECTIONS_020()
	{
		testParameters.setCurrentTestDescription("Do not see the action to 'Generate Realize CSVs' on a Course in the right pane of the Collection UI if I do not have permission to create content in the current Site (e.g., Consumer) - Part 3 : Verify 'Generate Realize CSVs' option for Consumer");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		

		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		
		collectionPg.clickOnMoreSetting(collectionObjectName);
		if(collectionPg.checkMoreSettingsOption(collectionObjectName, moreSettingsOptionName)){
			report.updateTestLog(
					"Verify More Settings Option for Course Object in Collection UI through Consumer Login",
					"Option available successfully"
					+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.FAIL);
		}else{
			report.updateTestLog(
					"Verify More Settings Option for Course Object in Collection UI through Consumer Login",
					"Option not available"
					+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.PASS);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}