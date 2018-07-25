package testscripts.eps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
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
public class AUT_AG_041 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_041()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify the Orange numbered sequence indicator when the asset is updated or modified."
				+"Verify the Spinning indicator below the green numbered indicator for an asset"
				+ " which is already published for more than one institution"
				+"Verify the orange alert indicator for an asset when the publish contains some issues"
				+"Verify the status field showing Orange icon on any change in the published Content"
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
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);

		
		try{
		//Value Declaration
				String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
				String filePath = dataTable.getData("MyFiles", "FilePath");	
				String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
				String FilepathofDoc = dataTable.getData("Document_Details", "FilePath");
				String Instution = dataTable.getData("MyFiles", "RelationshipName");
				
				// Script Started: 
				functionalLibrary.loginAsValidUser(signOnPage);	
				AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
				homePage.navigateToSitesTab();
				sitesPage.createSite(sourceSiteName, "Yes");
				String site=sitesPage.getCreatedSiteName();
				//	sitesPage.siteFinder(site);
				sitesPage.enterIntoDocumentLibrary();
			/*	docLibPg.deleteAllFilesAndFolders();
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();*/
				collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
				
				//Click on the Publishing Options
				
				collectionPg.clickOnMoreSetting(fileName[1]);	
				
				epsPg.publishbutton(fileName[1],Option[0]);
				epsPg.Publish("School Content", fileName[1],"one");	
				
				
				// Update a new version and check orange color is appearing
				docLibPg.openAFile(fileName[1]);
			
				docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
			
				sitesPage.enterIntoDocumentLibrary();
				epsPg.orangepublish(fileName[1],"one");
				// Again publish
				collectionPg.clickOnMoreSetting(fileName[1]);
				epsPg.publishbutton(fileName[1],Option[0]);
				epsPg.Publish(Instution, fileName[1],"one");				
		
				
				// Validation for the PublishinG options not available
				collectionPg.clickOnMoreSetting(fileName[1]);
				boolean flag3 = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[1],Option[3]);
				if(flag3){
					report.updateTestLog("Publishing Options:", "Publishing Option is presented "
							+ "for "+"<b> "+fileName[1], Status.FAIL);
				}else{
					report.updateTestLog("Publishing Options:", "Publishing Option is not presented "
							+ "for "+"<b> "+fileName[1], Status.PASS);
				}
																	
			/*	epsPg.DeleteFolderandFile();
				UIHelper.pageRefresh(driver);
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibrary();
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();*/
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_41 Status", "EPS 41 Testcases has been failed", Status.FAIL);		
		}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}