package testscripts.avsmartlink;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_43 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void newFieldsCheck() {
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5852_Verify decimal values are not accepted in Width and Height fields in 3rd party Interactive link of Create Smartlink screen."
				+"<br>2.ALFDEPLOY-5852_Verify decimal values are not accepted in Width and Height fields in Metrodigi link of Create Smartlink screen."
				+"<br>3.ALFDEPLOY-5852_Verify error Please input numeric values in pixels only message is displayed when other than numeric values like characters and special character"
				+"<br>4.ALFDEPLOY-5852_Verify error Please input numeric values in pixels only message is displayed when other than numeric values like characters and special characters are entered in Width and Height fields in Metrodigi link of Create Smartlink screen.");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		
		String type = dataTable.getData("Home", "DashletName");
		String data = dataTable.getData("MyFiles", "CreateFolder");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String[] tempheight = dataTable.getData("Document_Details", "Version").split(",");
		String[] tempwidth = dataTable.getData("Document_Details", "Comments").split(",");
		String height=null;
		String width=null;
		String errorMsg=dataTable.getData("MyFiles", "PopUpMsg");
		String[] valuetypes=dataTable.getData("Document_Details", "DocActionName").split(",");
		functionalLibrary.loginAsValidUser(signOnPage);

		
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
      
		 
		for(String value: valuetypes){
			  if(value.equalsIgnoreCase("Decimal")){
				  height=tempheight[0];
				  width=tempwidth[0];
			  }else{
				  height=tempheight[1];
				  width=tempwidth[1];
			  }
			  report.updateTestLog(" Error Verify scenario", " <b>Error scenario for "+value+" value</b>.",
						Status.DONE);
		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			
			 for (String detail : splittedFileDetails){
			    String splittedsmartDetails[] = detail.split(",");
				String smarttitle = splittedsmartDetails[0];
			    String smarttype = splittedsmartDetails[1];
				String smartId = splittedsmartDetails[2];
				myFiles.createcontenttype(type);
				avsmart.clickSmartLinkType(smarttype, smartId);
				avsmart.enterDataInThirdPartyInteractiveOrMetrodigiLink(smartId,smarttitle,extURLLink,height,width,"","","","","","");
				avsmart.error(errorMsg,smartId);
				sitesPage.enterIntoDocumentLibrary();
			 }
		 }else{
			    String splittedsmartDetails[] = data.split(",");
				String smarttitle = splittedsmartDetails[0];
				String smarttype = splittedsmartDetails[1];
				String smartId = splittedsmartDetails[2];
				myFiles.createcontenttype(type);
				avsmart.clickSmartLinkType(smarttype, smartId);
				avsmart.enterDataInThirdPartyInteractiveOrMetrodigiLink(smartId,smarttitle,extURLLink,height,width,"","","","","","");
				avsmart.error(errorMsg,smartId);
				sitesPage.enterIntoDocumentLibrary();
		     }
		}
		
		
		
	}
	

	@Override
	public void tearDown() {

	}
}
