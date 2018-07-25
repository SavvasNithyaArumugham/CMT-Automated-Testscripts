package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoUserNamePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_357 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_051()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to see My profile option in User name dropdown list"
				+"<br>2.Verify the 'Training' link available under the Username drop-down menu"
				+"<br>3.Verify the 'Alfresco Forum' link available under the Username drop-down menu");
		
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
		AlfrescoUserNamePageTest userNamePgTest = new AlfrescoUserNamePageTest(scriptHelper);
		
		//Veryfing Profile Name
		homePageObj.navigateToUserMenuTab();
		userNamePgTest.verifyUserProfileOption();
		
		String urlToCheck = dataTable.getData("Home", "URL");
		String[] splittedUrlToCheck=urlToCheck.split(",");
		String trainingLinkUrl=splittedUrlToCheck[0];
		String AlfrescoForumLinkUrl=splittedUrlToCheck[1];
		
		//veryfing Training' link
	    AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		homePage.clickUserNameDropDown();
		homePage.clickTrainingMenu();
		homePage.switchtab(1);
		
		String currentURL = driver.getCurrentUrl();
	
		if(currentURL.contains(trainingLinkUrl)){
			report.updateTestLog("Verify the URL of 'Training' Link",
					"Verified successfully"
					+"</br> <b> URL Verified : </b>" + trainingLinkUrl, Status.PASS);
		}else{
			report.updateTestLog("Verify the URL of 'Training' Link",
					"URL not found"
					+"</br> <b> URL Verified : </b>" + trainingLinkUrl, Status.FAIL);
		}
		
		//Verify the 'Alfresco Forum' link 
		homePage.switchtab(0);
		homePage.clickUserNameDropDown();
		homePage.clickAlfrescoForumMenu();
		homePage.switchtab(2);
		
		String currentURL1 = driver.getCurrentUrl();
		System.out.println(currentURL1);
		
		if(currentURL1.contains(AlfrescoForumLinkUrl)){
			report.updateTestLog("Verify the URL of 'Alfresco Forum' Link",
					"Verified successfully"
					+"</br> <b> URL Verified : </b>" + AlfrescoForumLinkUrl, Status.PASS);
		}else{
			report.updateTestLog("Verify the URL of 'Alfresco Forum' Link",
					"URL not found"
					+"</br> <b> URL Verified : </b>" + AlfrescoForumLinkUrl, Status.FAIL);
		}
		
		
	}

	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}