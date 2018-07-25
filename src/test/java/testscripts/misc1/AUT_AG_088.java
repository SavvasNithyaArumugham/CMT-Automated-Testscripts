package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEditTaskPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_088 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_078()
	{
		testParameters.setCurrentTestDescription("1. Verify whether 'Edit in Alfresco' option is available for Plain text, HTML and XML files"
				+ "<br>2. Verify whether 'Edit in Alfresco' option is available for documents, excel and PPTs"
				+ "<br>3. Verify whether user is able to perform 'Edit in Alfresco' operation on plain text directly in the document library"
				+ "<br>4. Verify whether user is able to perform 'Edit in Alfresco' operation on HTML directly in the document library"
				+ "<br>5. Verify whether user is able to perform 'Edit in Alfresco' operation on XML directly in the document library");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoEditTaskPage editTaskPage = new AlfrescoEditTaskPage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		String moreSettingsOption = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		
		String[] fileNames = fileName.split(",");
		for (String filename : fileNames) {
			
			sitesPage.clickOnMoreSetting(filename);	
			boolean flag = sitesPage.checkMoreSettingsOption(filename, moreSettingsOption);
			
			if(filename.contains("txt") || filename.contains("html") || filename.contains("xml"))
			{
				if(flag){
					report.updateTestLog("Verify the 'Edit in Alfresco' option",
							"Verified Succesfully"
									+ "<br><b>Verified File Name : </b>"
									+ filename,
							Status.PASS);
				}else{
					report.updateTestLog("Verify the 'Edit in Alfresco' option",
							"Verification Failed"
									+ "<br><b>Verified File Name : </b>"
									+ filename,
							Status.FAIL);
				}
			}
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		for (String fileNameVal : fileNames) {
			if(fileNameVal.contains("txt"))
			{
				sitesPage.clickOnMoreSetting(fileNameVal);	
				sitesPage.clickOnMoreOptionLink(fileNameVal);
				editTaskPage.doEditInAlfresco("Alfresco Test Demo Update", fileNameVal, "plain");
				docLibPg.openAFile(fileNameVal);
				editTaskPage.checkEditedValue("Alfresco Test Demo Update", fileNameVal);
				
				sitesPage.enterIntoDocumentLibrary();
			}
			else if(fileNameVal.contains("html"))
			{
				sitesPage.clickOnMoreSetting(fileNameVal);	
				sitesPage.clickOnMoreOptionLink(fileNameVal);
				editTaskPage.doEditInAlfresco("Alfresco Test Demo Update", fileNameVal, "html");
				docLibPg.openAFile(fileNameVal);
				editTaskPage.checkEditedValue("Alfresco Test Demo Update", fileNameVal);
				
				sitesPage.enterIntoDocumentLibrary();
			}
			else if(fileNameVal.contains("xml"))
			{
				sitesPage.clickOnMoreSetting(fileNameVal);	
				sitesPage.clickOnMoreOptionLink(fileNameVal);
				editTaskPage.doEditInAlfresco("Alfresco Test Demo Update", fileNameVal, "xml");
				docLibPg.openAFile(fileNameVal);
				editTaskPage.checkEditedValue("Alfresco Test Demo Update", fileNameVal);
				
				sitesPage.enterIntoDocumentLibrary();
			}
		}
	}

	@Override
	public void tearDown() {
		
	}

}
