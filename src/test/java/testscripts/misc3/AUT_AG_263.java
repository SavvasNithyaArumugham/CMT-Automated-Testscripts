package testscripts.misc3;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_263 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_048()
	{
		testParameters.setCurrentTestDescription("Test that user is able to preview the various type of files");
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
	public void executeTest() {
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNames = dataTable.getData("MyFiles", "FileName");
		
		StringTokenizer token = new StringTokenizer(fileNames,",");
		List<String> fileNameList = new ArrayList<String>();
		while (token.hasMoreElements()) {
			fileNameList.add(token.nextElement().toString());
		}
		int index = 1;
		for (String fileName : fileNameList) {
			
			AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			if(!docLibPage.isFileIsAvailable(fileName)){
				myFiles.uploadFileInMyFilesPage(filePath, fileName);
				
				AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
				myFilesTestObj.verifyUploadedFile(fileName,"");
			}			
			docLibPage.openAFile(fileName);
			
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			if(fileName.contains(".jpeg") || fileName.contains(".bmp")){
				docLibPageTest.verifyImagePreviewed(fileName);
			}else{
				docLibPageTest.verifyDocumentPreviewed(fileName);
			}
			if(index < fileNameList.size()){
				sitesPage.enterIntoDocumentLibrary();
			}
			index++;
		}
		
	}

	@Override
	public void tearDown() {
		
	}


}
