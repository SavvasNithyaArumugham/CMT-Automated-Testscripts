package testscripts.collections1;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class ALFDEPLOY_2235_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void COLLECTIONS_006() {
		testParameters
				.setCurrentTestDescription("when making a 'copy' of an object with an external ID can navigate to the copy and see that the external ID metadata of the copy is same as that of the original object");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest()	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName=sitesPage.getCreatedSiteName();
		
		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        myFiles.createFolder(folderDetails);
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		sitesPage.documentdetails(folderNames[0]);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnViewDetails("Program");
		
		String externalIdBeforeEdit = collectionPg.getFolderPropValue("External ID:").replace("L", "");
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderNames[0]);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting("Program");	
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.commonMethodForClickOnMoreSettingsOption("Program", "Copy to...");
		
		docLibPg.performCopyToOperation(siteName, "AutoTest");
	
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		sitesPage.clickOnViewDetails("Program-1");
		String externalIdAfterEdit = collectionPg.getFolderPropValue("External ID:").replace("L", "");
	
		if(Integer.parseInt(externalIdAfterEdit) == Integer.parseInt(externalIdBeforeEdit)){
			
			report.updateTestLog("Verify External ID of Copied Object", 
					"External ID of Copied Object " + "<br /><b> Copied Ext. Id : </b> " + "L"+externalIdBeforeEdit
					+ "<br /><b> Orginial Ext. Id : </b> " + "L"+externalIdAfterEdit+" is same as expected",
					Status.PASS);
			
		}else{
			
			report.updateTestLog("Verify External ID of Copied Object", 
					"External ID of Copied Object " + "<br /><b> Copied Ext. Id : </b> " + "L"+externalIdBeforeEdit
					+ "<br /><b> Orginial Ext. Id : </b> " + "L"+externalIdAfterEdit+" is not same",
					Status.FAIL);
			
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
