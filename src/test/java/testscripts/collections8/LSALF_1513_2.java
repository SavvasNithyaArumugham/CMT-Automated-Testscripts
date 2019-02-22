package testscripts.collections8;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1513_2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_02() {
		testParameters
				.setCurrentTestDescription("Upload one .JSON file into the Rubric folder, and confirm successful upload");
		
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
				
		String filepathfile = dataTable.getData("MyFiles", "uploadpath");
		String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
				functionalLibrary.loginAsValidUser(signOnPage);
				
				homePageObj.navigateToSitesTab();
				sitesPage.createSite(siteNameValue, "Yes");
				String flag = "true";
				// Navigate to document library and click on a Assets>rubrics
				try {		
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				}catch(Exception e) {
					flag= "false";
					report.updateTestLog("Problem in entering into Rubric Folder", "Issue in entering into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
							Status.FAIL);
				}
				
				if(flag.equalsIgnoreCase("true"))
				report.updateTestLog("Enter into Rubric Folder", "Enter into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				
				// upload course plan
				if(flag.equalsIgnoreCase("true")) {
				collectionPg.uploadFileInCollectionSite(filepathfile, filepathfilename);
				report.updateTestLog("Upload .json file", "successfull upload of file into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
						Status.PASS);
				}else
					report.updateTestLog("Upload .json file", "Issue in uploading file into Rubric Folder using <br/><b>Site Name:</b> " + siteNameValue,
							Status.FAIL);								
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
