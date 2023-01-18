package com.pearson.automation.cmt.functionllibs;

import com.pearson.automation.cmt.pages.*;
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
	
	//Method for CMT Application Login
	public CmtLoginPage loginAsValidUser(CmtLoginPage signOnPage)
	{
		CmtHomePage homePage = signOnPage.loginAsValidUser();
		
		// The login succeeds if the Alfresco >> User Dashboard page is displayed
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.DONE);
		
		//signOnPage = homePage.logout();
		return signOnPage;
	}

}