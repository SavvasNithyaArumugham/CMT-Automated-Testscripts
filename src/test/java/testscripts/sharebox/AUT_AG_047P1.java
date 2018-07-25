package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

public class AUT_AG_047P1 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	@Test
	public void sharebox_047P1() {
		
		testParameters.setCurrentTestDescription("1. Verify the option Exclude from sharebox is displayed in folder details page for folder."
						+"<br>2. Verify the option Exclude from sharebox is displayed in document details page for file."
						+"<br>3. Verify the option Remove sharebox exlusion is displayed in folder details page for folderfile which are already excluded."
						+"<br>4. Verify the confirmation dialogue box details displays properly on clicking Remove sharebox exclusion option via Folder details Document details page for Folder File which are already excluded.");
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
		AlfrescoDocumentDetailsPage folderdeatils = new AlfrescoDocumentDetailsPage(scriptHelper);
		
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
		sitesPage.clickOnViewDetails(folderName);
		
		// 1. Verify user is able to see the 'Exclude from Sharebox' option
		//in view details page for folder
		folderdeatils.isDocActionMenuAvailable(moreSettingsOptionNames[0]);
		folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[0]);
			
		//2. Verify the confirmation window is displayed after clicking the
		//Exclude form sharebox option for a folder from view details page
	    docLibPg.verifyConfirmationWindow();
	    docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	    //Cancel button
	    docLibPg.clickOnOkcancelBtnInPopup();
	    //OK button
	    folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[0]);
	    docLibPg.clickOnOkBtnInPopup();
	    	    
	    //3. Verify user is able to see the 'Remove Sharebox exclusion" option
	    //under actions in the document library page for folder
	    folderdeatils.isDocActionMenuAvailable(moreSettingsOptionNames[1]);
		folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[1]);
	    
	    //4. Verify the confirmation window is displayed after clicking the
	    //Remove Sharebox exclusion option for a folder from view details page
	    docLibPg.verifyConfirmationWindow();
	    docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	    //Cancel popup
	    docLibPg.clickOnOkcancelBtnInPopup();
	    //OK popup
	    folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[1]);
	    docLibPg.clickOnOkBtnInPopup();
	    
	    //Navigate back to document library to uplaod a file
	    docLibPg.navigateToDocumentLibrary();
	    
	    //Uploading a file into site
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFiles.openAFile(fileName);
	    
	    // 1. Verify user is able to see the 'Exclude from Sharebox' option
	 	// under actions in the document preview page for file
		folderdeatils.isDocActionMenuAvailable(moreSettingsOptionNames[0]);
		folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[0]);
		
	 	//2. Verify the confirmation window is displayed after clicking the
	 	//Exclude form sharebox option for a file form document preview page
	 	docLibPg.verifyConfirmationWindow();
	 	docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	 	//Cancel popup
	 	docLibPg.clickOnOkcancelBtnInPopup();
	 	//OK popup
	 	folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[0]);
	 	docLibPg.clickOnOkBtnInPopup();
	 	     	    
	 	//3. Verify user is able to see the 'Remove Sharebox exclusion" option
	 	//under actions in the document library page for file form document preview page
	 	folderdeatils.isDocActionMenuAvailable(moreSettingsOptionNames[1]);
		folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[1]);
	 	    
	 	//5. Verify the confirmation window is displayed after clicking the
	 	//Remove Sharebox exclusion option for a file form document preview page.
	 	docLibPg.verifyConfirmationWindow();
	 	docLibPg.verifytextavailbleinExcludeconfirmationwindow();
	 	//Cancel button
	 	docLibPg.clickOnOkcancelBtnInPopup();
	 	//OK button
	 	folderdeatils.clickOptionFromDocActionsPg(moreSettingsOptionNames[1]);
	 	docLibPg.clickOnOkBtnInPopup();
		}
		}
	@Override
	public void tearDown() {
		// Nothing to do
	}

}
