package testscripts.proofhq;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_032 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void proofhq_18()
	{
		testParameters.setCurrentTestDescription("Verifying the alfresco My files feature for proof related files/folders is working properly");
		
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);	
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoProofHQPage proofHQObj = new AlfrescoProofHQPage(scriptHelper);
		
		homePage.navigateToMyFilesTab();
		docLibPg.deleteAllFilesAndFolders();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFiles.uploadFile(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		String moreSettingsOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingsOption1="",moreSettingsOption2="",moreSettingsOption3="";
		if(moreSettingsOptions.contains(","))
		{
			String splittedMoreSettingOptions[] = moreSettingsOptions.split(",");
			if(splittedMoreSettingOptions!=null && splittedMoreSettingOptions.length>2)
			{
				moreSettingsOption1=splittedMoreSettingOptions[0];
				moreSettingsOption2=splittedMoreSettingOptions[1];
				moreSettingsOption3=splittedMoreSettingOptions[2];
			}
			else
			{
				moreSettingsOption1="Create Proof in ProofHQ";
				moreSettingsOption2="Open in ProofHQ";
				moreSettingsOption3="Remove from ProofHQ";
			}
		}
		else
		{
			moreSettingsOption1="Create Proof in ProofHQ";
			moreSettingsOption2="Open in ProofHQ";
			moreSettingsOption3="Remove from ProofHQ";
		}
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption1);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption1);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption2);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption3);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption3);
		proofHQObj.clickOnOkBtnInRemoveFromProofHQPopup("Yes");
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}