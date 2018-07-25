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

public class AUT_AG_012P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify Coordinator is able to create 'MD Pop up' smart link object in document library"
				+"2. Verify the navigation of user to document library upon clicking 'Cancel' button during 'MD Pop up' smart link object creation"
				+"3. Verify user is able to perform document actions such as delete, manage aspects, manage permissions for 'MD Pop up' smart link object");
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
	public void executeTest() {AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
	AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
	/*AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
	AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
			scriptHelper);*/
	AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
			scriptHelper);
	AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
	
/*	String filePath = dataTable.getData("MyFiles", "FilePath");
	String fileName = dataTable.getData("MyFiles", "FileName");*/
	
	String type = dataTable.getData("Home", "DashletName");
	String title = dataTable.getData("Document_Details", "FilePath");
	String extURLLink = dataTable.getData("Document_Details", "FileName");
	String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
	String moreSettingsOption1 = dataTable.getData("MyFiles", "RelationshipName");
	AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);	
	
	     functionalLibrary.loginAsValidUser(signOnPage);
       /*  String siteNameValue =  dataTable.getData("Sites", "SiteName");
	     sitesPage.siteFinder(siteNameValue);*/
	     
	     String siteName=sitesPage.getCreatedSiteName();

	 	sitesPage.siteFinder(siteName);
		
			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitFor(driver);
		//docLibPg.deleteAllFilesAndFolders();
	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
		
		avsmart.entersmarttypedata("mdPopUp", title, extURLLink, "", "", "","", "");
	
		avsmart.subcancelbtn("mdPopUp","Submit");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		sitesPage.enterIntoDocumentLibraryWithoutReport();
	
		
		sitesPage.documentdetails(title);
		
		myFilesTestObj.verifyUploadedFile(title, "");

		
		sitesPage.enterIntoDocumentLibraryWithoutReport();

		
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
		
		avsmart.subcancelbtn("mdPopUp","Cancel");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		//
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, moreSettingsOption);
		docDetailsPage.addAspectsAndApllyChangesToAFile();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitForPageToLoad(driver);
		
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title,
				moreSettingsOption1);
		UIHelper.waitForPageToLoad(driver);
		if(properties.getProperty("ApplicationUrl").contains("apppe")){
			
			sitesPage.clickOnAddUserGroupButton("QAPERFORM51");
			sitesPage.clickOnUserRole("Coordinator", "QAPERFORM51");
			
			sitesPage.clickOnAddUserGroupButton("QAPERFORM52");
			sitesPage.clickOnUserRole("Contributor", "QAPERFORM52");
			
			sitesPage.clickOnAddUserGroupButton("QAPERFORM53");
			sitesPage.clickOnUserRole("Reviewer", "QAPERFORM53");
			
		}
		else{	
		sitesPage.clickOnAddUserGroupButton("ALF01");
		sitesPage.clickOnUserRole("Coordinator", "ALF01");
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnAddUserGroupButton("ALF04");
		sitesPage.clickOnUserRole("Contributor", "ALF04");
		UIHelper.waitFor(driver);
		
		sitesPage.clickOnAddUserGroupButton("ALF03");
		sitesPage.clickOnUserRole("Reviewer", "ALF03");
		UIHelper.waitFor(driver);
		}
		UIHelper.waitFor(driver);
		docDetailsPage.removeInheritPermissions();
		}
		

	@Override
	public void tearDown() {

	}
}
