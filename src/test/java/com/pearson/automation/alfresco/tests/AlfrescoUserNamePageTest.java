package com.pearson.automation.alfresco.tests;

import com.pearson.automation.alfresco.pages.AlfrescoUserNamePage;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoUserNamePageTest extends ReusableLibrary {

	private AlfrescoUserNamePage appUserPg = new AlfrescoUserNamePage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoUserNamePageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Get uploaded file/folder name from My Files Page
	public void verifyUserProfileOption() {
		try {
			String expectedUserProfileOptionName = dataTable.getData("UserName", "AssertValue");
			String actualUserProfileOptionName = appUserPg.getUserProfileOptionName();

			if (actualUserProfileOptionName.equalsIgnoreCase(expectedUserProfileOptionName)) {
				report.updateTestLog("Verify My Profile Option in User Name Dropdown list",
						"My Profile option:" + expectedUserProfileOptionName + " displayed Successfully"
								+ "<br /><b>Expected Result:</b> " + expectedUserProfileOptionName
								+ ", <br /><b>Actual Result:</b> " + actualUserProfileOptionName + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify My Profile Option in User Name Dropdown list",
						"My Profile option fialed to display in User Name dropdown list"
								+ "<br /><b>Expected Result:</b> " + expectedUserProfileOptionName
								+ ", <br /><b>Actual Result:</b> " + actualUserProfileOptionName + "",
						Status.FAIL);
				/*
				 * frameworkParameters.setStopExecution(true); throw new
				 * FrameworkException("Verify My Profile Option",
				 * "My Profile fialed to display in User Name dropdown list");
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
