package com.pearson.automation.alfresco.tests;


import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.selenium.WebDriverUtil;


/**
 * Functional Components class
 * @author Cognizant
 */
public class AlfrescoLoginPageTest extends ReusableLibrary
{
	private WebDriverUtil driverUtil;
	
	private AlfrescoLoginPage appLoginPg = new AlfrescoLoginPage(scriptHelper);
	
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public AlfrescoLoginPageTest(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		driverUtil = new WebDriverUtil(driver);
	}
}