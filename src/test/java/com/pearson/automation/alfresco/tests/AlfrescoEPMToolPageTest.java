package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;

import com.pearson.automation.alfresco.pages.AlfrescoEPMToolPage;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoEPMToolPageTest extends ReusableLibrary {

	private AlfrescoEPMToolPage epmToolPgObj = new AlfrescoEPMToolPage(
			scriptHelper);
	
	private String biblioDataFilePath = "src/test/resources/AppTestData/TestOutput/BiblioData.txt";
	
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoEPMToolPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Check EPM Application Field Details in Alfresco EPM search results
	public void verifyEPMApplicationFieldDetailsInAlfresco() {
		try {

			if (epmToolPgObj.compareAlfrescoBiblioDataWithEPMBiblio()) {
				report.updateTestLog(
						"Verify EPM application field details in Alfresco EPM Search Result",
						"EPM application field details are displayed successfully in Alfresco EPM Search Result",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify EPM application field details in Alfresco EPM Search Result",
						"EPM application field details are failed to display in Alfresco EPM Search Result",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Meta data in Alfresco EPM search results
	public void verifyMetaDataInAlfrescoEPMSearchResults(
			String epmMetaDataDetails) {
		try {
			ArrayList<String> expectedAfrescoEPMFieldsData = new ArrayList<String>();
			ArrayList<String> actualAfrescoEPMFieldsData = new FileUtil()
					.readListOFDataFromFile(biblioDataFilePath);

			if (epmMetaDataDetails.contains(";")) {
				String epmMetaDataValues[] = epmMetaDataDetails.split(";");
				for (String epmMetaData : epmMetaDataValues) {
					expectedAfrescoEPMFieldsData.add(epmMetaData);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(
					expectedAfrescoEPMFieldsData, actualAfrescoEPMFieldsData)) {
				report.updateTestLog(
						"Verify Archive Folder field details in Alfresco EPM Search Result",
						"Archive Folder field details are displayed successfully in Alfresco EPM Search Result",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify Archive Folder field details in Alfresco EPM Search Result",
						"Archive Folder field details are failed to display in Alfresco EPM Search Result",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
