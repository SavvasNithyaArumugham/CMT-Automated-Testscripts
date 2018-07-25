package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_044P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				
				"Verify Co-ordinator/Manager/Contributor is getting Edit smartlink  option displayed for Image External smartlink object"+
				" which is created by contributor and is able to edit"
					
				);
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

		try {

			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
					scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
					scriptHelper);
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
					scriptHelper);


			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");
			String edittitle = dataTable.getData("Parametrized_Checkpoints",
					"FileName");
			String type = dataTable.getData("Home", "DashletName");
			String title = dataTable.getData("Document_Details", "FilePath");
			String folder = dataTable.getData("Document_Details", "DocPropertyValues");
			String titledit = dataTable.getData("Document_Details", "Version");
			String managertitledit = dataTable.getData("MyFiles", "RelationshipName");
			String extURLLink = dataTable.getData("Document_Details",
					"FileName");
			String extURLLinkedit = dataTable.getData("Document_Details",
					"Comments");
			String Type = dataTable.getData("MyFiles", "Version");
			String Name = dataTable.getData("MyFiles", "CreateFolder");
		

			functionalLibrary.loginAsValidUser(signOnPage);
			
			 String siteNameValue =  dataTable.getData("Sites", "SiteName");
				sitesPage.siteFinder(siteNameValue);
			sitesPage.enterIntoDocumentLibrary();
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(Type, Name);
			avsmart.entersmarttypedata(Name, title, extURLLink, "Image",
					"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
			UIHelper.waitFor(driver);
            avsmart.submitbutton(Name, title);
            
            UIHelper.waitForPageToLoad(driver);
            UIHelper.waitForPageToLoad(driver);
            sitesPage.clickOnMoreSetting(title);
			docLibPg
					.commonMethodForClickOnMoreSettingsOption(title, editSL);
			UIHelper.waitFor(driver);
			avsmart.entersmarttypedata(Name, titledit, extURLLink, "Images",
					"Captions", "credits", "", System.getProperty("user.dir")+ filePath + fileName);
			UIHelper.waitFor(driver);
			avsmart.submitbutton(Name, titledit);

			myFilesTestObj.verifyUploadedFile(titledit, "");
		
          /*  sitesPage.enterIntoDocumentLibrary();
			
			sitesPage.documentdetails(folder);
			UIHelper.waitForPageToLoad(driver);
			sitesPage.clickOnMoreSetting(edittitle);
			docLibPg
					.commonMethodForClickOnMoreSettingsOption(edittitle, editSL);
			avsmart.entersmarttypedata(Name, managertitledit, extURLLinkedit, "Image",
					"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
				System.out.println(System.getProperty("user.dir")+ filePath + fileName);
			avsmart.submitbutton(Name, title);
			
			myFilesTestObj.verifyUploadedFile(managertitledit, "");
			*/

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("AUT_AG_030 status:",
					"<br>AUT_AG_030 Testcase is Failed", Status.FAIL);

		}

	}

	@Override
	public void tearDown() {

	}
}
