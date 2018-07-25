package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_435 extends TestCase{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void newRelationShipPromptFrenchCheck(){
		testParameters.setCurrentTestDescription("ALFDEPLOY-5164_17_Verify Label cannot be empty and Source Target cannot be empty message get displayed in french language");
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
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		String link=dataTable.getData("Search", "Actions");
		String[] types= dataTable.getData("Search", "Query").split(",");
		String[] expErrorMsg=dataTable.getData("Search", "Result").split(",");
		
		
		if(homePage.isAdminToolsAvailable()){
			homePage.navigateToAdminTab();
			adminToolsPage.navigateToAdminToolsOptionsLink(link);	
			adminToolsPage.navigateToNewRelationship();
			adminToolsPage.createRleationshipForErrorCheck(types[0]);
			docLibPgTest.verifyLoadingMessage(expErrorMsg[0]);
			UIHelper.waitFor(driver);
			adminToolsPage.createRleationshipForErrorCheck(types[1]);
			docLibPgTest.verifyLoadingMessage(expErrorMsg[1]);
		}else{
			report.updateTestLog("Admin Tools Option not available","User dont have Admin rights.", Status.FAIL);
		}		
	}

	@Override
	public void tearDown() {
		
	}
}
