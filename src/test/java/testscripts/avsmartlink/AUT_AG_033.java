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
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_033 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_011() {
		testParameters
				.setCurrentTestDescription("1.User is able to create Table smart link object inside sub folder of document library"
						+"<br>2.Verify the creation of Table smart link object when only mandatory fields are given"
						+"<br>3.Verify smart link object is not created when invalid char star or blank values are given in Title field during Table smart link object creation and check error message Title value contains illegal Characters is displayed");
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
		try {
			
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
					scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
					scriptHelper);

			String siteName = dataTable.getData("Sites", "SiteName");
			String type = dataTable.getData("Home", "DashletName");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String folderDetails = dataTable.getData("MyFiles", "RelationshipName");
			String folder = dataTable.getData("MyFiles", "Sort Options");
			String Type = dataTable.getData("MyFiles", "Version");
			String Name = dataTable.getData("MyFiles", "CreateFolder");
			String title = dataTable.getData("Document_Details", "FilePath");
			String extURLLink = dataTable.getData("Document_Details",
					"FileName");
			

			functionalLibrary.loginAsValidUser(signOnPage);

			sitesPage.siteFinder(siteName);

			sitesPage.enterIntoDocumentLibrary();

			docLibPg.deleteAllFilesAndFolders();
			
			myFiles.createFolder(folderDetails);
			
			sitesPage.documentdetails(folder);

			myFiles.createcontenttype(type);
			
			avsmart.clickSmartLinkType(Type, Name);
			avsmart.entersmarttypedata(Name, title, extURLLink, "",
					"", "", "", "");
			avsmart.submitbutton(Name, title);
			
			if(sitesPage.documentAvailable(title)){
				report.updateTestLog("Verify create Table smart link object inside sub folder of document library", "smart link created successfully", Status.PASS);
				report.updateTestLog("Verify the creation of Table smart link object with only mandatory fields", "smart link created successfully", Status.PASS);
			}else{
				report.updateTestLog("Verify create Table smart link object inside sub folder of document library", "smart link was not created", Status.FAIL);
				report.updateTestLog("Verify the creation of Table smart link object with only mandatory fields", "smart link was not created ", Status.FAIL);
			}

			
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(Type, Name);
			avsmart.entersmarttypedata(Name, "", extURLLink, "", "", "","", "");
		//	avsmart.negsubmit(Name);
			avsmart.error("Title is a required field. Please fill in a Title for your smartlink",Name);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("AUT_AG_033 status:",
					"<br>AUT_AG_033 Testcase is Failed", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}
}
