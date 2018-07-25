package testscripts.proofhq;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_042 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_26() {
		testParameters
				.setCurrentTestDescription("Verifying the create proof option for alfresco HTML file.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);
		AlfrescoProofHQPage proofHQObj = new AlfrescoProofHQPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String moreSettingsOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingsOption1="",moreSettingsOption2="";
		if(moreSettingsOptions.contains(","))
		{
			String splittedMoreSettingOptions[] = moreSettingsOptions.split(",");
			if(splittedMoreSettingOptions!=null && splittedMoreSettingOptions.length>1)
			{
				moreSettingsOption1=splittedMoreSettingOptions[0];
				moreSettingsOption2=splittedMoreSettingOptions[1];
			}
			else
			{
				moreSettingsOption1="Create Proof in ProofHQ";
				moreSettingsOption2="Remove from ProofHQ";
			}
		}
		else
		{
			moreSettingsOption1="Create Proof in ProofHQ";
			moreSettingsOption2="Remove from ProofHQ";
		}
	
		sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileNme);
		myFilesTestObj.verifyUploadedFile(fileNme, "");
		
		sitesPage.clickOnMoreSetting(fileNme);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileNme, moreSettingsOption1);
		/*docLibPge.commonMethodForClickOnMoreSettingsOption(fileNme, moreSettingsOption1);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileNme);
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileNme, moreSettingsOption2);
		proofHQObj.clickOnOkBtnInRemoveFromProofHQPopup("Yes");*/

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}