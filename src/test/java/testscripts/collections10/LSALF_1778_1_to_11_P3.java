package testscripts.collections10;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1778_1_to_11_P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				"From Collection UI Confirm that the new dynamic course properties such as Level automation,Product Configuration and Program standards are not getting displayed on clicking Edit properties for other than course object form Share UI Via Document library>Container/Sequence/Learning Bundle/Content Object/Composite Object>Edit properties");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();

		//String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteNameValue);

		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
		
		String collectionObjectName = dataTable.getData("MyFiles", "CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);
		

		ArrayList<String> listOfObjects = new ArrayList<String>();
		listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
		for (String listOfObjectsString : listOfObjects) {
			
			if(listOfObjectsString.contains("AutoSeqObj")|| listOfObjectsString.contains("AutoLearningBundle")){
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
						"Edit Properties");		
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				
				try {
					//Check the values in the dropdown of Level Incrementing under Print Automation
					int count = 0;
				    String[] exp = {"Continue (Number and increment)", "Inherit from previous (Number, Do no increment)", "Suppress (Do not number, Do not increment)", "Skip (Do not number, increment)"};
				    WebElement dropdown = driver.findElement(By.id("template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_levelIncrementing"));
				    Select select = new Select(dropdown);

				    List<WebElement> options = select.getOptions();
				    for (WebElement we : options) {
				        for (int i = 0; i < exp.length; i++) {
				            if (we.getText().equals(exp[i])) {
				                count++;
				            }
				        }
				    }
				    if (count == exp.length) {
				    	report.updateTestLog("Print Automation","Print Automation Property dropdown values are present",Status.PASS);
				    } else {
				    	report.updateTestLog("Print Automation","Print Automation Property dropdown values are not present",Status.FAIL);
				    }
				    
				    //First Selected Value in the dropdown of Level Incrementing under Print Automation
				    if(select.getFirstSelectedOption().getText().contains("Continue")) {
				    	report.updateTestLog("Print Automation","Print Automation Property has the selected dropdown value as Continue",Status.PASS);
				    }else {
				    	report.updateTestLog("Print Automation","Print Automation Property does not have the selected dropdown value as Continue",Status.FAIL);
				    }
				    
				    //Is Multi Select Dropdown
				    if(!select.isMultiple()) {
				    	report.updateTestLog("Print Automation","Print Automation Property is not a multi select dropdown",Status.PASS);
				    }else {
				    	report.updateTestLog("Print Automation","Print Automation Property is a multi select dropdown",Status.FAIL);
				    }
				    UIHelper.waitFor(driver);
				    //Change the dropdown value of Level Incrementing under Print Automation
				    select.selectByIndex(2);
				    
				    UIHelper.waitFor(driver);
				    
					//Click the Save button
					UIHelper.click(driver, "//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default-form-submit-button\"]");
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					collectionPg.clickOnMoreSetting(listOfObjectsString);
					UIHelper.waitFor(driver);
					collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,"View Details");
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueINviewDetails("Level Incrementing:", "Suppress (Do not number, Do not increment)");	
					UIHelper.waitFor(driver);
					
				}catch(Exception e) {
					report.updateTestLog("Print Automation","Print Automation Property",Status.FAIL);
			    }
			}
	}
}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
