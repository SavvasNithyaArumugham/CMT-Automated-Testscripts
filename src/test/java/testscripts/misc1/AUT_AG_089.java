package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_089 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_079()
	{
		testParameters.setCurrentTestDescription("1. Verify whether user is able to perform 'Edit in Microsoft Office' operation on documents in Alfresco"
				+ "<br>2. Verify whether user is able to perform 'Edit in Microsoft Office' operation on  excel sheet in Alfresco"
				+ "<br>3. Verify whether user is able to perform 'Edit in Microsoft Office' operation on PPT in Alfresco");
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
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		docLibPg.deleteAllFilesAndFolders();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		String moreSettingsOption = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		
		String[] fileNames = fileName.split(",");
		for (String filename : fileNames) {
			
			sitesPage.clickOnMoreSetting(filename);	
			boolean flag = sitesPage.checkMoreSettingsOption(filename, moreSettingsOption);
			
			if(filename.contains("doc") || filename.contains("xls") || filename.contains("ppt"))
			{
				if(flag){
					report.updateTestLog("Verify the 'Edit in Microsoft Office' option is not Available",
							"Verified Succesfully"
									+ "<br><b>Verified File Name : </b>"
									+ filename,
							Status.PASS);
				}else{
					report.updateTestLog("Verify the 'Edit in Microsoft Office' option is not Available",
							"Verification Failed"
									+ "<br><b>Verified File Name : </b>"
									+ filename,
							Status.FAIL);
				}
			}
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		for (String fileNameValue : fileNames) {
			
			if(fileNameValue.contains("doc") || fileNameValue.contains("xls") || fileNameValue.contains("ppt"))
			{
				sitesPage.clickOnMoreSetting(fileNameValue);	
				sitesPage.clickOnMoreOptionLink(fileNameValue);
				
				sitesPage.enterIntoDocumentLibrary();
			}
		}
		
	}

	@Override
	public void tearDown() {
		
	}

}
