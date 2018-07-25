package testscripts.avsmartlink;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAVSmartLinkPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_42P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void newFieldsCheck() {
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5852_Verify 2 new fields Width and Height are displayed in Metrodigi link of Edit Smartlink Screen with the values populated correctly."
				+"<br>2.ALFDEPLOY-5852_Verify 2 new fields Width and Height are displayed in 3rd party Interactive link of Edit Smartlink Screen with the values populated correctly."
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPageTest avsmartTest =new AlfrescoAVSmartLinkPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		
		String data = dataTable.getData("MyFiles", "CreateFolder");
		String siteName = dataTable.getData("Sites", "SiteName");
		String height = dataTable.getData("Document_Details", "Version");
		String width = dataTable.getData("Document_Details", "Comments");
		String moreSettingsOptionName=dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] fieldName = dataTable.getData("MyFiles", "TagName").split(",");
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
			
		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String detail : splittedFileDetails){
				String splittedsmartDetails[] = detail.split(",");
				String smarttitle = splittedsmartDetails[0];
				String smarttype = splittedsmartDetails[1];
				String smartId = splittedsmartDetails[2];
				myFiles.openUploadedOrCreatedFile(smarttitle,"");
				sitesPage.clickOnMoreSetting(smarttitle);
				docLibPg.commonMethodForClickOnMoreSettingsOption(smarttitle, moreSettingsOptionName);
				avsmartTest.verifyFieldValue(smartId, fieldName[0], height);
				avsmartTest.verifyFieldValue(smartId, fieldName[1], width);
				sitesPage.enterIntoDocumentLibrary();
				
			}
		}
		
	}
	

	@Override
	public void tearDown() {

	}
}
