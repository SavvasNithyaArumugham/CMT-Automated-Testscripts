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

public class AUT_AG_003 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("Verify user is able to edit external url and title of smartlink object via edit properties");
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

		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileNamezip = dataTable.getData("MyFiles", "CreateFileDetails");
		
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
	
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
		
		avsmart.entersmarttypedata("imageExternalLink", title, extURLLink, "", "", "","", System.getProperty("user.dir")+filePath+fileName);
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		avsmart.clearfunction("imageExternalLink");
		ArrayList<String> filesNamesList = new ArrayList<String>();
		filesNamesList = myFiles.getFileNames(fileNamezip);
		
		for (String name : filesNamesList) {
			avsmart.editsmarttypedatainduvidual("imageExternalLink", "imgpreview", System.getProperty("user.dir")+filePath+name);
			//avsmart.entersmarttypedata("imageExternalLink", title, extURLLink, "", "", "","", System.getProperty("user.dir")+filePath+name);
			//avsmart.errormsg(name);
			avsmart.errormsgSelectAndUpload(name, "Upload", "imageExternalLink");
			avsmart.clearfunction("imageExternalLink");
			
		}	
		
	}
		

	@Override
	public void tearDown() {

	}
}
