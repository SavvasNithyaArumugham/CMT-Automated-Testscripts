package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

public class AUT_AG_046 extends TestCase {
	private FunctionalLibrary functionalLibrary;
	@Test
	public void sharebox_046() {
		
		testParameters.setCurrentTestDescription("1. Verify the option Exclude from sharebox is displayed via more actions for folder." 
						+"<br>2. Verify the option Exclude from sharebox is displayed via more actions for file."
						+"<br>3. Verify the confirmation dialogue box details displays properly on clicking Exclude from sharebox option via More for Folder"
						+"<br>4. Verify the confirmation dialogue box details displays properly on clicking Exclude from sharebox option via More for File" 
						+"<br>5. Verify the message Folder and all its subfolders and content are excluded from sharebox sharing is displayed below the file folder on performing Exclude sharebox sharing actions via More actions." 
						+"<br>6. Verify the dynamic option Remove sharebox exlusion is displayed for the already excluded files folders."
						+"<br>7. Verify the confirmation dialogue box details displays properly on clicking Remove sharebox exclusion option via More for Folder File which are already excluded."
						+"<br>8. Verify the option Remove sharebox exlusion is displayed via More on cancelling the Remove sharebox exclusion confirmation window."
						);
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
				
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
			moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		//Creating a folder
		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : folderNamesList) {
		sitesPage.clickOnMoreSetting(folderName);

		// 1. Verify user is able to see the 'Exclude from Sharebox' option
		// under actions in the document library page for folder
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderName, moreSettingsOptionNames[0]);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[0]);
		
		//2. Verify the confirmation window is displayed after clicking the
		//Exclude form sharebox option for a folder
	    docLibPg.verifyConfirmationWindow();
	    docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	    //Cancel button
	    docLibPg.clickOnOkcancelBtnInPopup();
	    //OK button
	    docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[0]);
	    docLibPg.clickOnOkBtnInPopup();
	    
	    //3. Verify the text available after exclusion applied for a folder
	    docLibPg.verifyexcludedfoldertextavailble(folderName);
	    
	    //4. Verify user is able to see the 'Remove Sharebox exclusion" option
	    //under actions in the document library page for folder
	    sitesPage.clickOnMoreSetting(folderName);
	    sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderName, moreSettingsOptionNames[1]);
	    docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[1]);
	    
	    //5. Verify the confirmation window is displayed after clicking the
	    //Remove Sharebox exclusion option for a folder
	    docLibPg.verifyConfirmationWindow();
	    docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	    //Cancel popup
	    docLibPg.clickOnOkcancelBtnInPopup();
	    //OK popup
	    docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionNames[1]);
	    docLibPg.clickOnOkBtnInPopup();
	    //Uploading a file into site
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.clickOnMoreSetting(fileName);
	    
	    // 1. Verify user is able to see the 'Exclude from Sharebox' option
	 	// under actions in the document library page for file
	 	sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileName , moreSettingsOptionNames[0]);
	 	docLibPg.commonMethodForClickOnMoreSettingsOption(fileName , moreSettingsOptionNames[0]);
	 		
	 	//2. Verify the confirmation window is displayed after clicking the
	 	//Exclude form sharebox option for a file
	 	docLibPg.verifyConfirmationWindow();
	 	docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	 	//Cancel popup
	 	docLibPg.clickOnOkcancelBtnInPopup();
	 	//OK popup
	 	docLibPg.commonMethodForClickOnMoreSettingsOption(fileName , moreSettingsOptionNames[0]);
	 	docLibPg.clickOnOkBtnInPopup();
	 	    
	 	//3. Verify the text available after exclusion applied for a file
	 	docLibPg.verifyexcludedfiletextavailble(fileName);
	 	    
	 	//4. Verify user is able to see the 'Remove Sharebox exclusion" option
	 	//under actions in the document library page for file
	 	sitesPage.clickOnMoreSetting(fileName);
	 	sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileName , moreSettingsOptionNames[1]);
	 	docLibPg.commonMethodForClickOnMoreSettingsOption(fileName , moreSettingsOptionNames[1]);
	 	    
	 	//5. Verify the confirmation window is displayed after clicking the
	 	//Remove Sharebox exclusion option for a file
	 	docLibPg.verifyConfirmationWindow();
	 	docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	 	//Cancel button
	 	docLibPg.clickOnOkcancelBtnInPopup();
	 	//OK button
	 	docLibPg.commonMethodForClickOnMoreSettingsOption(fileName , moreSettingsOptionNames[1]);
	 	docLibPg.clickOnOkBtnInPopup();
		}
		}
	@Override
	public void tearDown() {
		// Nothing to do
	}

}
