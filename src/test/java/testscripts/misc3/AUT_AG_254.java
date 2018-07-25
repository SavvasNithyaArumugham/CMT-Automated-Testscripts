package testscripts.misc3;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_254 extends TestCase
{
private FunctionalLibrary functionalLibrary;
@Test
public void misc3_038()
{
	testParameters.setCurrentTestDescription("Verify that user is able to checkout multiple file at once "
			+"<br>2.Verify that user is able to checking multiple file at once");
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
	
	AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
	AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
			scriptHelper);
	AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
	
	homePageObj.navigateToSitesTab();
	
	String siteName = dataTable.getData("Sites", "SiteName");
	sitesPage.openSiteFromRecentSites(siteName);
	
	sitesPage.enterIntoDocumentLibrary();
	
	String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
	
	myFiles.releaseLockOnFile("Documents");
	
	try{
		docLibPg.deleteAllFilesAndFolders();
		//docLibPg.deleteAllFilesAndFolders();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	myFiles.createFile(fileDetails);
	
	docDetailsPageObj.backToFolderOrDocumentPage("");
	
	sitesPage.enterIntoDocumentLibrary();
	
	docLibPg.selectAllItems("Multiple Files");
	
	sitesPage.clickOnSelectedItems();

	String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	String[] selectedItemMenuOptValList=selectedItemMenuOptVal.split(",");
	sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptValList[0]);
	sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptValList[1]);
	
	docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
	
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	
	String filePath = dataTable.getData("MyFiles", "FilePath");
	String fileName = dataTable.getData("MyFiles", "FileName");
	
	docLibPg.deleteAllFilesAndFolders();
	UIHelper.waitFor(driver);
	docLibPg.deleteAllFilesAndFolders();
	
	myFiles.uploadFileInMyFilesPage(filePath, fileName);
	
	ArrayList<String> menuOptionList = new ArrayList<String>(); 
	String menuOption = dataTable.getData("Sites", "SelectedItemsMenuOption");
	StringTokenizer tokenMenuOption = new StringTokenizer(menuOption, ",");
	while (tokenMenuOption.hasMoreElements()) {
		menuOptionList.add(tokenMenuOption.nextToken());
	}
	
	docLibPg.selectAllFilesAndFolders();
	sitesPage.clickOnSelectedItems();
	
	
	
	String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
	
	new FileUtil().deleteIfAllFilesExistsInDownloadPath(fileDownloadPath, fileName);
	
	docLibPg.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(0));
	

	UIHelper.waitFor(driver);
	UIHelper.waitFor(driver);
	
	sitesPage.enterIntoDocumentLibraryWithoutReport();
	
	docLibPg.selectAllFilesAndFolders();
	ArrayList<String> fileNameList = new ArrayList<String>(); 
	StringTokenizer tokenFileName = new StringTokenizer(fileName, ",");
	while (tokenFileName.hasMoreElements()) {
		
		docLibPg.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(1));
		String newVersionfileName = tokenFileName.nextToken();
		fileNameList.add(newVersionfileName);
		newVersionfileName = newVersionfileName.replace(".docx", ".docx");
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage(newVersionfileName);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
	}		
			
	AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
	docLibPg.openAFile(fileNameList.get(0));
	docDetailsPageTest.verifyUploadedNewVersionFile();
	
	sitesPage.enterIntoDocumentLibrary();
	
	docLibPg.openAFile(fileNameList.get(1));
	docDetailsPageTest.verifyUploadedNewVersionFile();
}


@Override
public void tearDown()
{
	// Nothing to do
}
}