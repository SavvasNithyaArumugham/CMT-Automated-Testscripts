package com.pearson.automation.alfresco.functionllibs;

import com.pearson.automation.alfresco.pages.*;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.Status;


/**
 * Functional Library class
 * @author Cognizant
 */
public class FunctionalLibrary extends ReusableLibrary
{
	/**
	 * Constructor to initialize the functional library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public FunctionalLibrary(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
	}
	
	//Method for Alfresco Application Login
	public AlfrescoLoginPage loginAsValidUser(AlfrescoLoginPage signOnPage)
	{
		AlfrescoHomePage homePage = signOnPage.loginAsValidUser();
		
		// The login succeeds if the Alfresco >> User Dashboard page is displayed
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.DONE);
		
		//signOnPage = homePage.logout();
		return signOnPage;
	}
	
	//Method for Pearson Mail Login
	public AlfrescoLoginPage loginAsValidUserforPearsonMail(AlfrescoLoginPage signOnPage)
	{
		AlfrescoHomePage homePage = signOnPage.loginAsValidUserForPearsonMail();
		
		report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
		
		//signOnPage = homePage.logout();
		return signOnPage;
	}
	
	//Method for WebDav Login
	public AlfrescoLoginPage loginForWebdav()
	{
		AlfrescoLoginPage signOnPage=new AlfrescoLoginPage(scriptHelper);
		signOnPage.loginAsValidUserForWebdav();
		
		
		return signOnPage;
	}
}