package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_032 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_032()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the Publishing option is not displayed for the asset when the site is "
				+ "not configured to EPS.<br>"
				+"Verify user cannot be able to publish the content in non publishable site."
				
						);	
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
		try{
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderName = dataTable.getData("MyFiles", "Version");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
		
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		UIHelper.waitFor(driver);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		UIHelper.waitForPageToLoad(driver);
		
		//Click on the Publishing Options
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(fileName[1]);		
		boolean flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[1],"Publishing Options");
		if(flag){
			report.updateTestLog("Publishing Options:", "Publishing Option is presented "
					+ "for "+"<b> "+fileName[1], Status.PASS);	
			collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[1],"Publishing Options");
			epsPg.Sitenotconfiguredandclose();
			collectionPg.clickOnMoreSetting(fileName[1]);
			epsPg.publishbutton(fileName[1],"Publish");
			epsPg.Sitenotconfiguredandclose();
			
			
		}else{
			report.updateTestLog("Publishing Options:", "Publishing Option is not presented "
					+ "for "+"<b> "+fileName[1], Status.FAIL);			
			
		}			
		myFiles.createFolder(folderDetails);
		
		//Click on the Publishing Options
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(folderName);		
		boolean flag1 = collectionPg.CheckOptionsForClickOnMoreSettingsOption(folderName,"Publishing Options");
		if(flag1){
			report.updateTestLog("Publishing Options:", "Publishing Option is presented "
					+ "for "+"<b> "+folderName, Status.PASS);	
			collectionPg.commonMethodForClickOnMoreSettingsOption(folderName,"Publishing Options");
			epsPg.Sitenotconfiguredandclose();
			collectionPg.clickOnMoreSetting(folderName);
			epsPg.publishbutton(folderName,"Publish");
			epsPg.Sitenotconfiguredandclose();
		}else{
			report.updateTestLog("Publishing Options:", "Publishing Option is not presented"
					+ " for "+"<b> "+folderName, Status.FAIL);				
		}	
		
		
		
		}catch(Exception e){
			report.updateTestLog("EPS_32 Status", "EPS 32 Testcases has been failed", Status.FAIL);	
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}