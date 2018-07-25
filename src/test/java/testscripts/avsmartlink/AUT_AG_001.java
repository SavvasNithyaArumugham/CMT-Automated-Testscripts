package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_01() {
		testParameters.setCurrentTestDescription("Verify whether user is able to create smart link object in My files or shared files");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToMyFilesTab();
		
		String type = dataTable.getData("Home", "DashletName");
		
		myFiles.createcontenttype(type);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, avsmart.messageXpath);
			String getMessage = UIHelper.getTextFromWebElement(driver,
					avsmart.messageXpath);
			
			if(getMessage.equalsIgnoreCase("This action is not possible in My Files")){
				
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link cannot be created in my files. <br><b>Message : <b> " +getMessage, Status.PASS);
			}else{
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link can be created in my files which is not execpted", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link",
					"Smart Link can be created in my files which is not execpted", Status.FAIL);
		}
		
		
		homePageObj.navigateToSharedFilesTab();
		
		myFiles.createcontenttype(type);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, avsmart.messageXpath);
			String getMessage = UIHelper.getTextFromWebElement(driver,
					avsmart.messageXpath);
			
			if(getMessage.equalsIgnoreCase("This action is not possible in Shared Files area")){
				
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link cannot be created in my files. <br><b>Message : <b> " +getMessage, Status.PASS);
			}else{
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link can be created in my files which is not execpted", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link",
					"Smart Link can be created in my files which is not execpted", Status.FAIL);
		}
	}
		

	@Override
	public void tearDown() {

	}
}
