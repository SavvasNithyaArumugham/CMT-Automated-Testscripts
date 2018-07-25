package testscripts.misc1;

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
public class AUT_AG_044 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_035()
	{
		testParameters.setCurrentTestDescription("Verify that file remains followed when user reverts the file to the previous versions except original version(1.0)");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");

		
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);		
		docLibPg.deleteAllFilesAndFolders();		
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);	
		
		sitesPage.documentdetails(fileName);
		
		UIHelper.waitFor(driver);
		docLibPgObj.clickFollowOption(fileName);
		
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		UIHelper.waitFor(driver);
		docDetailsPage.revertAnyOlderVersionToCurrentVersion();
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		if(!docLibPgObj.isFollowLinkSelected()){
			report.updateTestLog("Verify File should not be followed",
					"File not Followed as expected"
							+ "<br /><b> File Name : </b>"
							+ fileName, Status.PASS);
		}else{
			report.updateTestLog("Verify follow option is selected",
					"File  Followed"
							+ "<br /><b> File Name : </b>"
							+ fileName, Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
