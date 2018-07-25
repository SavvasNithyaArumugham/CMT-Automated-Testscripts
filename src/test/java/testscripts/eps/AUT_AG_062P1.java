package testscripts.eps;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.Zip;
import org.testng.annotations.Test;

import java.util.zip.*;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_062P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_060()
	{
		testParameters.setCurrentTestDescription("Verify user not able to delete the folder if it was published as content."+
				"<br>Verify User can be able to check the changes from review changes option."
				+"<br>Verify the version shows the current version of published content if it is a minor version"
				+"<br>Verify the version shows the current version of published content if it is a minor version"
				+"<br>Verify there is a publish button under action field for Content which have some change."
				+"<br>Verify there is a publication history available at the bottom of the publish dialog box."
				+"<br>Verify the Pubhish dialog box contains name as folder for the content when it was published from parent.");	
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			
		
		// Variables
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
	
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
		String folderName = dataTable.getData("MyFiles", "Version");
		ArrayList<String> history = new ArrayList<String>();
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		
		functionalLibrary.loginAsValidUser(signOnPage);	 
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//	sitesPage.siteFinder(site);
		
       	sitesPage.performInviteUserToSite(site);
        siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);
		
        sitesPage.enterIntoDocumentLibrary();
        myFiles.createFolder(folderDetails);
        sitesPage.documentdetails(folderName);
        collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);

    	collectionPg.clickOnMoreSetting(fileName[0]);				
		epsPg.publishbutton(fileName[0],Option[0]);
		
		epsPg.Publish("School Content", fileName[0],"one");

      	sitesPage.documentdetails(fileName[0]);
      	docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		 sitesPage.documentdetails(folderName);
		 epsPg.orangepublish(fileName[0], "one");
		
		collectionPg.clickOnMoreSetting(fileName[0]);
		
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], "Review Changes");
		epsPg.reviewchanges("modified",fileName[0]);

	    collectionPg.clickOnMoreSetting(fileName[0]);				
		epsPg.publishbutton(fileName[0],Option[0]);
			
		epsPg.Publish("School Content", fileName[0],"one");
			
		collectionPg.clickOnMoreSetting(fileName[0]);
		epsPg.publishbutton(fileName[0],Option[0]);
			
		history= epsPg.publishhistory("1");
			
		if(history != null && history.get(3).equalsIgnoreCase("School Content")) {
				
				report.updateTestLog("Verify publish  history", "Publish history of the file/folder is <br>File/Folder Name" + fileName[0]
				+history.get(0)+ " "+history.get(1)+", "+history.get(2)+ " "+history.get(3)+", "
				+history.get(4)+ " "+history.get(5)+", "+history.get(6)+ " "+history.get(7), Status.PASS);	
		}else {
				report.updateTestLog("Verify publish  history", "Failed to retreive publish history", Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		collectionPg.clickOnMoreSetting(folderName);
		epsPg.publishbutton(folderName, Option[1]);
		docLibPg.deletefolderwithMoreactions();
		
		if(!sitesPage.documentAvailable(folderName)){
			report.updateTestLog("<b>Delete the content if published:", "User deleted folder if published as content<br><b> Folder Name</b> : "+folderName +" which is not expected", Status.FAIL);						
		}else{
			report.updateTestLog("<b>Delete the content if published:", "User deleted folder if published as content<br><b> Folder Name</b> : "+folderName +" as expected", Status.PASS);								
		}
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_62P1 Status", "EPS 61P1 Testcases has been failed", Status.FAIL);	
			}		
	
}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}