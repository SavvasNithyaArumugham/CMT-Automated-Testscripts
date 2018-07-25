package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_042 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_042()
	{
		testParameters.setCurrentTestDescription(
				
				"User can be able to delete the Content if the Content was Published"
				+"Verify user able to delete the file if it was published as parent."
				+ "Verfiy User can be able publish content in the publishable site."
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		try{
		//Value Declaration
				String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
				String filePath = dataTable.getData("MyFiles", "FilePath");	
				String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
				String folderName = dataTable.getData("MyFiles", "Version");
				String Instution = dataTable.getData("MyFiles", "RelationshipName");
				// Script Started: 
				
				functionalLibrary.loginAsValidUser(signOnPage);	
				UIHelper.waitFor(driver);
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
				
				//Publish the content
				
				collectionPg.clickOnMoreSetting(fileName[0]);	
				
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish(Instution, fileName[0],"one");	
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibraryWithoutReport();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				
			//	String file= epsPg.fileisavilable.replace("CRAFT", fileName[0]);
				//System.out.println(file);
			//	Boolean flag = UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, file));
				//Boolean flag = docLibPg.isFileIsAvailable(fileName[0]);
				//System.out.println(fileName[0]);
				//System.out.println(flag);
				if(sitesPage.documentAvailable(fileName[0])){
					report.updateTestLog("<b>Delete the content if published:", "User not deleted the Content if the "+fileName[0]+" was Published", Status.FAIL);						
				}else{
					report.updateTestLog("<b>Delete the content if published:", "User deleted the Content if the "+fileName[0]+" was Published", Status.PASS);							
				}
				
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_42 Status", "EPS 42 Testcases has been failed", Status.FAIL);	
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}