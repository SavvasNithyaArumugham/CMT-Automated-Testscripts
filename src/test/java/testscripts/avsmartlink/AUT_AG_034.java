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

public class AUT_AG_034 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify Consumer is not able to view Create button-Smartlink option"
				+"<br>2.Verify Reviewer is not able to view Create button-Smartlink option"
				+"<br>3.Verify Contributor is able to Edit Smartlink created by others for Metrodigi link when the smart link folder is given permission as Manager/coordinator/collaborator"
				+"<br>4.Verify Consumer is able to Edit Smartlink created by others for Metrodigi link when the smart link folder is given permission as Manager/coordinator/collaborator"
				+"<br>5.Verify Reviewer is able to Edit Smartlink created by others for Metrodigi link when the smart link folder is given permission as Manager/coordinator/collaborator"
				+"<br>6.Verify the creation of new content folder when Title field in Table Link type is provided with special characters, numbers, alphanumeric and with space prefixed"
				+"<br>7.Verify the creation of smart link object when maximum characters are entered in Title field for Table Linktype"
				+"<br>8.Verify the navigation of user to document library upon clicking Cancel button during Table smart link object creation");
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
				
				myFiles.createcontenttype(type);
				
				avsmart.clickSmartLinkType(smartname, smarttype);

		
				avsmart.entersmarttypedata(smarttype, smarttitle, extURLLink,
						"Images", "", "", "", System.getProperty("user.dir")
								+ filePath + fileName);
				avsmart.submitbutton(smarttype, smarttitle);

				myFilesTestObj.verifyUploadedFile(smarttitle, "");
				
				sitesPage.enterIntoDocumentLibrary();
				
				if(sitesPage.documentAvailable(smarttitle)){
					report.updateTestLog("Verify the creation of new content folder ", "New folder created with smart link title name successfully", Status.PASS);
					
				}else{
					report.updateTestLog("Verify the creation of new content folder ", "New folder created with smart link title name not created as expected. ", Status.FAIL);
							}

			}
			
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
			avsmart.entersmarttypedata("imageExternalLink", "Test", extURLLink, "", "", "","", "");
			avsmart.subcancelbtn("imageExternalLink","Cancel");
					
		}

	
	}
		

	@Override
	public void tearDown() {

	}
}
