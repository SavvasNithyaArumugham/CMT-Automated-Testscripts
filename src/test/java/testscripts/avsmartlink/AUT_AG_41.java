package testscripts.avsmartlink;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoAVSmartLinkPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_41 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void newFieldsCheck() {
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5852_Verify 2 new fields Width and Height are added and is by "
				+ "default empty in 3rd party Interactive link of Create Smartlink screen"
				+"<br>2.ALFDEPLOY-5852_Verify 2 new fields Width and Height are added and is by default empty in Metrodigi link of Create"
				+ " Smartlink screen"
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

		
		String type = dataTable.getData("Home", "DashletName");
		String data = dataTable.getData("MyFiles", "CreateFolder");
		String siteName = dataTable.getData("Sites", "SiteName");
		String[] fieldName = dataTable.getData("MyFiles", "TagName").split(",");
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
      
			
		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String detail : splittedFileDetails){
				String splittedsmartDetails[] = detail.split(",");
				String smarttype = splittedsmartDetails[0];
				String smartId = splittedsmartDetails[1];
						
				myFiles.createcontenttype(type);	
				avsmart.clickSmartLinkType(smarttype, smartId);
				avsmartTest.verifyFieldPresent(smartId,fieldName[0]);
				avsmartTest.verifyFieldPresent(smartId,fieldName[1]);
				avsmartTest.isEmptyfield(smartId,fieldName[0]);
				avsmartTest.isEmptyfield(smartId, fieldName[1]);
				sitesPage.enterIntoDocumentLibrary();
			}
		}else{
			String splittedsmartDetails[] = data.split(",");
			String smarttype = splittedsmartDetails[0];
			String smartId = splittedsmartDetails[1];
					
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(smarttype, smartId);
			avsmartTest.verifyFieldPresent(smartId,fieldName[0]);
			avsmartTest.verifyFieldPresent(smartId,fieldName[1]);
			avsmartTest.isEmptyfield(smartId, fieldName[0]);
			avsmartTest.isEmptyfield(smartId, fieldName[1]);
		}
		
	}
	

	@Override
	public void tearDown() {

	}
}
