package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_012P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1. Verify Manager is able to create 'MD Pop up' smart link object in document library"
				+"<br>2.Verify whether user is able to edit 'Title' ,'URL', 'JSON String' of 'MD Pop up' smart link object via edit properties and verify the correct values are populated in the content folder."
				+"<br>3. Verify the creation of smart link object when maximum characters are entered in 'Title' field for 'MD Pop up link' type."
				+"<br>4. Verify the creation of 'MD Pop up' smart link object when only mandatory fields 'Title' and 'External URL Link' are given");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		
		String title1 = dataTable.getData("MyFiles", "FilePath");
		String extURLLink1 = dataTable.getData("MyFiles", "FileName");
		
		String edittitle = dataTable.getData("Parametrized_Checkpoints", "FileName");

		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		
		homePage.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		//sitesPage.siteFinder(siteName);
		
		String site=sitesPage.getCreatedSiteName();
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		sitesPage.performInviteUserToSite(site);
		
		

		siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);

		sitesPage.enterIntoDocumentLibrary();
		
		//docLibPg.deleteAllFilesAndFolders();
	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
		
		avsmart.entersmarttypedata("mdPopUp", title, extURLLink, "", "Caption for MDPopup", "Credit text for MDPopup","", "");
	
		avsmart.subcancelbtn("mdPopUp","Submit");
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(title);
		
		myFilesTestObj.verifyUploadedFile(title, "");
		
		sitesPage.clickOnMoreSetting(title);
		
		sitesPage.clickOnEditProperties(title);
		
		avsmart.editsmartlink(edittitle,extURLLink);
		
		sitesPage.enterIntoDocumentLibrary();
		/*UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);*/
		
		sitesPage.documentdetails(title);
		//myFilesTestObj.getCreatedFolder(title1);
		//sitesPage.openFolder(title);
		
		myFilesTestObj.verifyUploadedFile(edittitle, "");
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
		
		avsmart.entersmarttypedata("mdPopUp", title1, extURLLink1, "", "", "","", "");
		
		avsmart.subcancelbtn("mdPopUp","Submit");
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		sitesPage.documentdetails(title1);
		//sitesPage.openFolder(title1);
		//myFilesTestObj.getCreatedFolder(title1);
		
		myFilesTestObj.verifyUploadedFile(title1, "");
		
		//myFilesTestObj.getCreatedFolder(title1);
	/*	
		AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(
				scriptHelper);
		homePageTestObj.verifyHelpURL();
		*/	
	}
		

	@Override
	public void tearDown() {

	}
}
