package testscripts.amts;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_122 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_70()
	{
		testParameters.setCurrentTestDescription("Verify whether the user is able to select node browser from the Admin Tools");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToAdminTab();
		
		String status = dataTable.getData("Tasks", "Status");
		
		AlfrescoTasksPage tasksPage = new AlfrescoTasksPage(scriptHelper);
		tasksPage.filter(status);
		AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);
		if(UIHelper.getTextFromWebElement(driver, appAdminToolsPg.titleXpath).equalsIgnoreCase(status)){
			report.updateTestLog("Verify user is able to select node browser",
					"user is able to select node browser from Admin Tools successfully."
							+ "<br /><b> Page Title : </b>" + UIHelper.getTextFromWebElement(driver, appAdminToolsPg.titleXpath),
					Status.PASS);
		}else{
			report.updateTestLog("Verify user is able to select node browser",
					"user is not able to select node browser from Admin Tools failed.",
					Status.FAIL);
		}
		
		
	}
	

	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}