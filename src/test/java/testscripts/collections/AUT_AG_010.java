package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_010 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("1. TC-ALFDEPLOY-3447-Verify able to create Content object by uploading realize CSV with File Type value ≠ SEQUENCE<br>"
						+"2. TC-ALFDEPLOY-3447-Verify able to create Container object by uploading realize CSV"
						+"<br>3. TC-ALFDEPLOY-3447-Verify user able to create Sequence object by uploading realize CSV"
						+"<br>4. TC-ALFDEPLOY-3447-Verify able to create Course object by uploading realize CSV"
						+"<br>5. TC-ALFDEPLOY-3447-Verify user able to create Content object by uploading realize CSV"
						+"<br>6. TC-ALFDEPLOY-3447-Verify user not able to create sequence object by uploading realize CSV");
		
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String objectNames = dataTable.getData("MyFiles", "CreateFileDetails");
		ArrayList<String> labelList = myFiles.getFolderNames(objectNames);
		ArrayList<String> CR_CourseImportTestsObjectList = new ArrayList<>();

		// // login
		functionalLibrary.loginAsValidUser(signOnPage);

		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// go to Course plan
		myFiles.openCreatedFolder("Data Imports");
		myFiles.openCreatedFolder("Course Plan");

		// upload course plan
		collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// verify import process progress
		collectionPg.verifyImportProcessProgress(fileName);

		// verify uploaded object in the collection view
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.openCollectionObject(collectionObjectName);
		CR_CourseImportTestsObjectList = collectionPg
				.getFoldersFromRightPanInCollectionUi();
		
		System.out.println(CR_CourseImportTestsObjectList);
		System.out.println(labelList);
		boolean flag  = false;
		
		for(String object : labelList){
		if(CR_CourseImportTestsObjectList.contains(object)){
			
			flag = true;
		
				}else{
					
					flag = false;
				
					break;
				}
		}
		
		if(flag){
			report.updateTestLog("Create object by uploading realize CSV",
					" create Content object by uploading realize CSV with File Type value ≠ SEQUENCE successfully"
					+"<br> create Container object by uploading realize CSV successfully"
					+"<br> create Sequence object by uploading realize CSV successfully"
					+"<br> create Course object by uploading realize CSV successfully"
					+"<br> create Content object by uploading realize CSV successfully"
					+"<br> user not able to create sequence object by uploading realize CSV successfully"
					, Status.PASS);
		}else{
			report.updateTestLog("Create object by uploading realize CSV",
					" <br>create Content object by uploading realize CSV with File Type value ≠ SEQUENCE failed"
					+"<br> create Container object by uploading realize CSV failed"
					+"<br> create Sequence object by uploading realize CSV failed"
					+"<br> create Course object by uploading realize CSV failed"
					+"<br> create Content object by uploading realize CSV failed"
					+"<br> user not able to create sequence object by uploading realize CSV failed"
					, Status.FAIL);
		}
	
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
