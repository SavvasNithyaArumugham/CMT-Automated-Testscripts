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
public class AUT_AG_033 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_033()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the Publishing option is not displayed for the asset that is already "
				+ "published to any instituion once when the site is configured to EPS.<br>"
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
		String Instution = dataTable.getData("MyFiles", "RelationshipName");	
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
		String folderName = dataTable.getData("MyFiles", "Version");
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		UIHelper.waitFor(driver);
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		UIHelper.waitFor(driver);
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		
		//Click on the Publishing Options
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(fileName[1]);	
		System.out.println(Option[3]);
		boolean flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[1],Option[3]);
		if(flag){
			report.updateTestLog("Publishing Options:", "Publishing Option is presented "
					+ "for "+"<b> "+fileName[1], Status.PASS);
			epsPg.publishbutton(fileName[1], "Publish");
			epsPg.Publish(Instution, fileName[1],"one");
			collectionPg.clickOnMoreSetting(fileName[1]);
			boolean flag1 = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[1],Option[3]);
			if(flag1){
				report.updateTestLog("Publishing Options:", "Publishing Option is presented "
						+ "for "+"<b> "+fileName[1], Status.FAIL);
			}else{
				report.updateTestLog("Publishing Options:", "Publishing Option is not presented "
						+ "for "+"<b> "+fileName[1], Status.PASS);
			}
		}else{
			report.updateTestLog("Publishing Options:", "Publishing Option is not presented "
					+ "for "+"<b> "+fileName[1], Status.FAIL);			
			
		}
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		sitesPage.enterIntoDocumentLibrary();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		
		}catch(Exception e){
			report.updateTestLog("EPS_33 Status", "EPS 33 Testcases has been failed", Status.FAIL);	
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}