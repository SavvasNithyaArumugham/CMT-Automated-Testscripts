package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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
public class AUT_AG_144 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_040()
	{
		testParameters.setCurrentTestDescription("Verify options under document actions for Plain text, HTML and XML is in the below order"+
													"<br>View In Browser"+
													"<br>Edit in Alfresco"+
													"<br>Upload New Version"+
													"<br>Edit Properties"+
													"<br>Edit Offline");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper); 
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		docLibPg.deleteAllFilesAndFolders();		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		String[] fileNames = fileName.split(",");
		String[] docActionMenuOptions = moreSettingsOption.split(",");
		
		for (String filename : fileNames) {
			boolean flag = false;
			myFiles.openAFile(filename);
			
			for (String docActionMenu : docActionMenuOptions) {
				if(docDetailsPage.isDocActionMenuAvailable(docActionMenu)){
					flag = true;
				}else{
					flag = false;
					break;
				}
			}
			
			if(flag){
				report.updateTestLog("Verify the Document Action options",
						"Verified sucessfully"
						+"<br> <b> File Verified : </b>"+filename
						+"<br> <b> Verified Document Action Menu: </b>"+moreSettingsOption,
						Status.PASS);
			}else{
				report.updateTestLog("Verify the Document Action options",
						"Verification Failed"
						+"<br> <b> File Verified : </b>"+filename
						+"<br> <b> Verified Document Action Menu: </b>"+moreSettingsOption,
						Status.FAIL);
			}
			
			sitesPage.enterIntoDocumentLibrary();
		}
	}

	@Override
	public void tearDown() {
		
	}

}
