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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_086 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC1_076()
	{
		testParameters.setCurrentTestDescription("Verify whether user is able to view below edit actions in more option while mouse hovering a file."
													+"1. Edit offline"
													+"2. Edit in Alfresco");
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
		String fileDetails= dataTable.getData("MyFiles", "CreateFileDetails");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		docLibPg.deleteAllFilesAndFolders();
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.createFile(fileDetails);
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.pageRefresh(driver);
		
		String moreSettingsOption = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		
		String[] moreOptions = moreSettingsOption.split(",");
		for (String moreOption : moreOptions) {
			sitesPage.clickOnMoreSetting(fileName);	
			boolean flag = sitesPage.checkMoreSettingsOption(fileName, moreOption);
			
			if(flag){
				report.updateTestLog("Verify the '"+moreOption+"' option",
						"Verified Succesfully"
								+ "<br><b>Verified File Name : </b>"
								+ fileName,
						Status.PASS);
			}else{
				report.updateTestLog("Verify the '"+moreOption+"' option",
						"Verification Failed"
								+ "<br><b>Verified File Name : </b>"
								+ fileName,
						Status.FAIL);
			}
			UIHelper.pageRefresh(driver);
			UIHelper.waitFor(driver);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
