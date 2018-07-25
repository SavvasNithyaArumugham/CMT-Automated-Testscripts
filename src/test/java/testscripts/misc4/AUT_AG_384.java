   package testscripts.misc4;



import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_384 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_060()
	{
		testParameters.setCurrentTestDescription("verify whether user is able to upload/create files when sharing not done completely");
		
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
        AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest= new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		//Login
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		// Navigate to site and upload file
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
         
	    docLibPg.deleteAllFilesAndFolders();
		
	    ArrayList<String> allFolderNamesList = new ArrayList<String>();

	    String folderDetails = dataTable.getData("MyFiles", "CreateFileDetails");
	    
	    ArrayList<String> folderNamesList = new ArrayList<String>();
		StringTokenizer token = new StringTokenizer(folderDetails, ";");
		while (token.hasMoreElements()) {
			String folderdetails = token.nextElement().toString();
			myFiles.createFolder(folderdetails);
		
			folderNamesList = myFiles.getFolderNames(folderdetails);
			for (String folderName : folderNamesList) {
				myFiles.openCreatedFolder(folderName);
				allFolderNamesList.add(folderName);
			}}
		
	 
			String breadCumbFolderLinkDetails = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
			
			if (breadCumbFolderLinkDetails.contains(";")) {
				String splittedBreadCumbDetails[] = breadCumbFolderLinkDetails.split(";");
				for (String breadCumbLinkVal : splittedBreadCumbDetails)
				{
					docLibPg.clickOnBreadCrumbLinkDocLibPg(breadCumbLinkVal);
						
				}
				}
	}
		
   
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}