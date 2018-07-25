package testscripts.misc5;

import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoAdminToolsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_433 extends TestCase{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void newRelationShipPromptFrenchCheck(){
		testParameters.setCurrentTestDescription("ALFDEPLOY-5164_15_Verify labels button are in french language in Admin page, New Relationship page also verify functionality of save and cancel button in New Relationship page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoAdminToolsPage adminToolsPage = new AlfrescoAdminToolsPage(scriptHelper);
		AlfrescoAdminToolsPageTest adminToolsPageTest = new AlfrescoAdminToolsPageTest(scriptHelper);
		String link=dataTable.getData("Search", "Actions");
		String[] tempExpFields= dataTable.getData("Search", "Query").split(",");
		String expLinkValue=dataTable.getData("Search", "Aspect");
		String actualLinkValue=null;
		ArrayList<String> expFields= new ArrayList<String>(Arrays.asList(tempExpFields));
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();
			adminToolsPageTest.verifyAdminToolsOptionsLink(link);
			adminToolsPage.navigateToAdminToolsOptionsLink(link);	
			if(adminToolsPage.isRelationShipButtonDisplayed()) {
				actualLinkValue=AlfrescoAdminToolsPage.getActualValue();
				if(actualLinkValue.equalsIgnoreCase(expLinkValue)) {
					report.updateTestLog("Verify New Relatiship link in French","New Relatiship link in French verified successfully.", Status.PASS);
				}else {
					report.updateTestLog("Verify New Relatiship link in French","New Relatiship link in French verification unsuccessfull.", Status.FAIL);
				}
			}else {
				report.updateTestLog("Verify New Relatiship link in French","New Relatiship link not displayed.", Status.FAIL);
			}
			adminToolsPage.navigateToNewRelationship();
			adminToolsPageTest.verifyNewRelationshipFields(expFields);
			
		}else{
			report.updateTestLog("Admin Tools Option not available","User dont have Admin rights.", Status.FAIL);
		}		
	}

	@Override
	public void tearDown() {
		
	}
}
