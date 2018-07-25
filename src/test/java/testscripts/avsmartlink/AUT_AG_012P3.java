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

public class AUT_AG_012P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("Verify Contributor is able to create 'MD Pop up' smart link object in document library");
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
	AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
	
	String type = dataTable.getData("Home", "DashletName");
	String title = dataTable.getData("Document_Details", "FilePath");
	String extURLLink = dataTable.getData("Document_Details", "FileName");
		
	functionalLibrary.loginAsValidUser(signOnPage);

	String siteName=sitesPage.getCreatedSiteName();

	sitesPage.siteFinder(siteName);
	
	sitesPage.enterIntoDocumentLibrary();
	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
		
		avsmart.entersmarttypedata("mdPopUp", "", extURLLink, "", "", "","", "");
		
		avsmart.subcancelbtnerror("mdPopUp","Submit");
		
		avsmart.error("Title is a required field. Please fill in a Title for your smartlink","mdPopUp");
		UIHelper.waitFor(driver);
		
		avsmart.entersmarttypedata("mdPopUp", title, "", "", "", "","", "");
		
		avsmart.subcancelbtnerror("mdPopUp","Submit");
		
		/*avsmart.error("Invalid URL","mdPopUp");
		
		avsmart.entersmarttypedata("mdPopUp", title, extURLLink, "", "", "","", "");
		
		avsmart.subcancelbtn("mdPopUp","Submit");	
	*/
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		UIHelper.waitFor(driver);
	
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		sitesPage.documentdetails(title);
		UIHelper.waitFor(driver);
		myFilesTestObj.verifyUploadedFile(title, "");
	/*	
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("MD Pop Up ", "mdPopUp");
		
		avsmart.entersmarttypedata("mdPopUp", title, extURLLink, "", "", "","", "");
		
		avsmart.subcancelbtnerror("mdPopUp","Submit");
		
		myFilesTestObj.verifyUploadedFile(title, "");
		*/
					
	}
		

	@Override
	public void tearDown() {

	}
}
