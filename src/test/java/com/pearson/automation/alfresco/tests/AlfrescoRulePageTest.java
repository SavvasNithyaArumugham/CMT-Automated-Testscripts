package com.pearson.automation.alfresco.tests;

import com.pearson.automation.alfresco.pages.AlfrescoPeoplePage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;


/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1. Files
 * 2. Folders
 *
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoRulePageTest extends ReusableLibrary {
	
	private AlfrescoPeoplePage appPeoplePg = new AlfrescoPeoplePage(scriptHelper);
	
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public AlfrescoRulePageTest(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
	}
}
