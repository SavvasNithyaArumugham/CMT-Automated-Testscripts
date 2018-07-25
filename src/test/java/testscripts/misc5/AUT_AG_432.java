package testscripts.misc5;


import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_432 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_001()
	{
		testParameters.setCurrentTestDescription("1) ALFDEPLOY-5448&6209_Verify user able to see the french translation for Batch Publish option and Batch publish screen."
										   +"</br>2) ALFDEPLOY-5448&6209_Verify user able to see the french translation for batch publish successful message." );
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage doclib=new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoEPSPage epsPage=new AlfrescoEPSPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoEPSPageTest epsPageTest = new AlfrescoEPSPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String selectedItemMenuOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		String cancelButtonInFrench=dataTable.getData("MyFiles", "RelationshipName");
		String expMessage=dataTable.getData("MyFiles", "PopUpMsg");
		String expPromptHeaderValue=dataTable.getData("MyFiles", "BrowseActionName");
		String folderName=myFiles.getFolderNames(folderDetails).get(0);
		
		sitesPage.siteFinder(sourceSiteName);		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);	
		myFiles.uploadFile(filePath, fileName);
		myFiles.methodToSelectMultipleFiles(fileName);
		sitesPage.clickOnSelectedItems();
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		doclib.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		String actPromptHeaderValue=doclib.getPromptHeaderMessage();
		if(actPromptHeaderValue.equalsIgnoreCase(expPromptHeaderValue)) {
			report.updateTestLog("Verify prompt header message in french", "Prompt header message verified in french successfully <br><b>Expected :</b>"+expPromptHeaderValue+"<br><b>Actual :</b>"+actPromptHeaderValue, Status.PASS);
		}else {
			report.updateTestLog("Verify prompt header message in french", "Prompt header message verification in french failed <br><b>Expected :</b>"+expPromptHeaderValue+"<br><b>Actual :</b>"+actPromptHeaderValue, Status.FAIL);
		}
		epsPageTest.verifyFilesListedOnBatchPublish(fileName);
		epsPageTest.verifyPromptFooterButton(cancelButtonInFrench);
		epsPage.clickBatchPublishButtonWithoutWait();
		docLibPgTest.verifyLoadingMessage(expMessage);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}