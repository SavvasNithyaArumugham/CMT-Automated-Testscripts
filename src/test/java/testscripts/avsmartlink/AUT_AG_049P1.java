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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_049P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription(" ALFDEPLOY-3996_Verify the creation of Video external smartlink object with image reference Url filled with" + 
	" Alfresco supported special characters,alphanumeric,numeric,alphabets,portuguese,latin,255 char via Create smartlink ");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");
		String type = dataTable.getData("Home", "DashletName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String data = dataTable.getData("MyFiles", "CreateFileDetails");
		String siteName = dataTable.getData("Sites", "SiteName");
		
		
		
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();

		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String nxtval : splittedFileDetails) {
				String splittedsmartDetails[] = nxtval.split(",");

				String smarttitle = splittedsmartDetails[0];
				String smartname = splittedsmartDetails[1];
				String smarttype = splittedsmartDetails[2];
				String imageref = splittedsmartDetails[3];
				
				myFiles.createcontenttype(type);
			    avsmart.clickSmartLinkType(smartname, smarttype);
				avsmart.entersmarttypedata(smarttype, smarttitle, extURLLink,
						imageref, "", "", "", System.getProperty("user.dir")
								+ filePath + fileName);
				avsmart.submitbutton(smarttype, smarttitle);

				myFilesTestObj.verifyUploadedFile(smarttitle, "");
				
				sitesPage.enterIntoDocumentLibrary();
				
				if(sitesPage.documentAvailable(smarttitle)){
					report.updateTestLog("Verify the creation of new content folder ", "New folder created with Image Ref URL is successfully", Status.PASS);
					
				}else{
					report.updateTestLog("Verify the creation of new content folder ", "New folder created with Image Ref URL is not created as expected. ", Status.FAIL);
				}
			}
		}
	
	}
		

	@Override
	
	public void tearDown() {

	}
}
