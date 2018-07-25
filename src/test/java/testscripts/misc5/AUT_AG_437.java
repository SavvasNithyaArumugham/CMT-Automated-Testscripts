package testscripts.misc5;

import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_437 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void newRelationshipFromDocumentDetailsFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_22_Verify lables and button in new realtionship popup window via Document details page also perfrom user is able to add relationship for file"
				    +"<br>2.ALFDEPLOY-5164_25_Verify user able to add new relationship and verify all the label & button displayed in French language while adding relationship" + 
				    "verify the delete relationship functionality"
				    +"<br>3.ALFDEPLOY-5164_19_Verify lables and button in Delete Realtionship pop up window also check Yes/No button functionality");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest= new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest =new AlfrescoDocumentDetailsPageTest(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String moreSettingsOption=dataTable.getData("MyFiles", "MoreSettingsOption");
		String [] tempExpVal=dataTable.getData("MyFiles", "PopUpMsg").split(",");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		ArrayList<String> expVal=new ArrayList<String>(Arrays.asList(tempExpVal));
		String [] tempExpVal1=dataTable.getData("MyFiles", "TagName").split(",");
		ArrayList<String> expVal1=new ArrayList<String>(Arrays.asList(tempExpVal1));
		String expMsg=dataTable.getData("MyFiles", "Sort Options");
		String expectedMsg=dataTable.getData("MyFiles", "SelectDropdownValue");
	    String actualMsg=null;
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName[0]);
		myFiles.uploadFileInMyFilesPage(filePath, fileName[1]);
		myFiles.openAFile(fileName[0]);
		docDetailsPage.clickDocAction(moreSettingsOption);
		docDetailsPageTest.verifyRelationShipFields(expVal);
		UIHelper.waitFor(driver);
		sitesPage.addRelationshipForMessageCheck(relationshipName, fileName[1]);
		docLibPageTest.verifyLoadingMessage(expMsg);
		UIHelper.waitFor(driver);
		docDetailsPageTest.verifyAddedRelationshipData(fileName[1]);
		docDetailsPage.clickOnDeleteRelationship(fileName[1],relationshipName);
		docDetailsPageTest.verifyDeleteRelationShipFields(expVal1);
		docDetailsPage.clickOnYesButtonInDeleteRelationship();
        actualMsg=docDetailsPage.getRelations(fileName[0]).get(0);
		if(actualMsg!=null && actualMsg.equalsIgnoreCase(expectedMsg)) {
			report.updateTestLog("Verify Relationship deleted","Relationship deleted successfully", Status.PASS);
		}else {
			report.updateTestLog("Verify Relationship deleted","Relationship deletion unsuccessfull", Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
