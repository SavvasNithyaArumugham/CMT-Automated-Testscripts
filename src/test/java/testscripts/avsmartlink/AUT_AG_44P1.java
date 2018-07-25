package testscripts.avsmartlink;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAVSmartLinkPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_44P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void newFieldsCheck() {
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5852_Verify 2 new fields Width and Height are displayed in 3rd party Interactive link of Edit Smartlink Screen -Part1"
				+"<br>2.ALFDEPLOY-5852_Verify 2 new fields Width and Height are displayed in Metrodigi link of Edit Smartlink Screen."
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
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
				scriptHelper);
		AlfrescoAVSmartLinkPageTest avsmartTest =new AlfrescoAVSmartLinkPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		
		String type = dataTable.getData("Home", "DashletName");
		String data = dataTable.getData("MyFiles", "CreateFolder");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
      
			
		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String detail : splittedFileDetails){
				String splittedsmartDetails[] = detail.split(",");
				String smarttitle = splittedsmartDetails[0];
				String smarttype = splittedsmartDetails[1];
				String smartId = splittedsmartDetails[2];
				myFiles.createcontenttype(type);
				System.out.println("");
				avsmart.clickSmartLinkType(smarttype, smartId);
				avsmart.enterDataInThirdPartyInteractiveOrMetrodigiLink(smartId,smarttitle,extURLLink,"","","","","","","","");
				avsmart.submitbutton(smartId, smarttitle);
				myFilesTestObj.verifyUploadedFile(smarttitle, "");
				sitesPage.enterIntoDocumentLibrary();
				if(sitesPage.documentAvailable(smarttitle)){
					report.updateTestLog("Verify the creation of smartLink with out height and width field ", "New folder created for "+smarttype+" smart link with title name "+smarttitle+" successfully", Status.PASS);
				}else{
					report.updateTestLog("Verify the creation of smartLink with out height and width field", "New folder for "+smarttype+" not created. ", Status.FAIL);
				}
			}
		}
		
	}
	

	@Override
	public void tearDown() {

	}
}
