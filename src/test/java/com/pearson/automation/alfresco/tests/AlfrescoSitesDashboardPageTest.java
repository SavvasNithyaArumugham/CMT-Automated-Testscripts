package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**********************************************************************************************
 * AlfrescoDocumentDetailsPageTest.java - This program contains verification
 * methods for Document details page
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoSitesDashboardPageTest extends ReusableLibrary {

	private AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
	private ArrayList<String> expectedFieldValuesList = new ArrayList<String>();
	private ArrayList<String> actualFieldsList = new ArrayList<String>();
	private ArrayList<String> actualFieldsDefaultValueList = new ArrayList<String>();
	private String splittedFieldValues[];
	private boolean isDisplayedAllFieldsDataInProgramSite;
	private ArrayList<String> programDashletList = new ArrayList<String>();
	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
	private String testOutputMultipleFilePath = "src/test/resources/AppTestData/TestOutput/MultipleSiteDetails.txt";
	private String createdSiteNameWithtimestamp;
	private String[] createdSiteNames;
	private ArrayList<String> programSiteFieldNamesList = new ArrayList<String>();
	private ArrayList<String> contentDropdownList = new ArrayList<String>();

	private ArrayList<String> expectedSiteNamesList = new ArrayList<String>();
	private ArrayList<String> actualSiteNamesList = new ArrayList<String>();

	private boolean isDisplayedComponentSites;

	private String siteDashboardXpath = ".//*[@id='HEADER_SITE_DASHBOARD_text']/a";
	private boolean isDisplayedSiteDashboard;

	private String programSiteDashboardItemsXpath = "//*[@id='bd']//*[@class='title']";
	private ArrayList<String> programSiteDashboardItemsList = new ArrayList<String>();
	private String splittedDahletNames[];
	ArrayList<String> expectedListOfDashlets = new ArrayList<String>();

	private String divisionXpath = ".//*[@id='select-division-button']";
	private String sourceCodeXpath = ".//*[@id='select-sourceCode-button']";

	private String editorialDisciplineXpath = ".//*[@id='select-editorialDiscipline-button']";
	private String groupCodeXpath = ".//*[@id='select-groupCode-button']";

	private String alfrescoGlobalImgXpath = ".//*[contains(@id,'global')]/div/span/a/img";

	private String businessAreaXpath = ".//*[@id='prgm-site-details-tablediv']/table/tbody/tr/td[contains(.,'Business Area')]";

	private ArrayList<String> actualDropdDownFieldsAndValuesInProgramDashlet = new ArrayList<String>();
	private ArrayList<String> expectedDropDownValuesList = new ArrayList<String>();

	private String dashletListXPath = ".//*[@class='availableList']/li/span";
	private String dashletListFullXPath = ".//*[@class='availableList']";
	private ArrayList<String> availableDashletList = new ArrayList<String>();
	private String dashletValues = ".//*[@class='used']//div//div//ul//span";

	private String mySitesDropDownXpath = ".//*[contains(@id,'default-my-sites-title')]/ancestor::div[1]//div//span/button";
	private String mySitesValueXpath = ".//*[contains(@class,'yuimenuitem')]/a[Text()='All']";
	private String mySitesXpath = ".//*[contains(@id,'default-my-sites-title')]";
	private String documentLibraryXpath = ".//*[@id='HEADER_TITLE']";
	private ArrayList<String> uploadedFilesNameList = new ArrayList<String>();
	private boolean isDisplayedUploadedFile;

	private String fldrXPath = ".//div[@id='epmDashlet']//div[@id='accordion-epm']//div[@id='folderListTable-epm_wrapper']//table[@id='folderListTable-epm']//*[text()='CRAFT']";
	private String fleXPath = ".//div[@id='epmDashlet']//div[@id='accordion-epm']//div[@id='folderListTable-epm_wrapper']//table[@id='folderListTable-epm']//*[text()='CRAFT']";

	private ArrayList<String> dashletColumn1ValuesFromCustDashboard = new ArrayList<String>();
	private ArrayList<String> dashletColumn2ValuesFromCustDashboard = new ArrayList<String>();
	private ArrayList<String> dashletColumn3ValuesFromCustDashboard = new ArrayList<String>();
	private ArrayList<String> dashletColumn4ValuesFromCustDashboard = new ArrayList<String>();

	private ArrayList<String> dashletColumn1ValuesFromSiteDashboard = new ArrayList<String>();
	private ArrayList<String> dashletColumn2ValuesFromSiteDashboard = new ArrayList<String>();
	private ArrayList<String> dashletColumn3ValuesFromSiteDashboard = new ArrayList<String>();
	private ArrayList<String> dashletColumn4ValuesFromSiteDashboard = new ArrayList<String>();

	private String splittedActivities[];
	private ArrayList<String> expectedActivitiesList = new ArrayList<String>();
	private ArrayList<String> actualActivitiesList = new ArrayList<String>();
	
	private String deleteSiteErrorMsgXpath="//div[contains(text(),\"Something's wrong with this page\")]";
	private String backToDashboardButtonXpath="//a[contains(text(),'Back to My Dashboard')]";

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSitesDashboardPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify created Program Component Site field in site dashboard page
	public void verifyProgramComponentSiteFields(String fieldNames) {
		try {
			actualFieldsList = sitesDashboardPg.getProgramComponentDashletDetails();

			if (fieldNames.contains(";")) {
				splittedFieldValues = fieldNames.split(";");

			} else if (fieldNames.contains(",")) {
				splittedFieldValues = fieldNames.split(",");
			} else {
				expectedFieldValuesList.add(fieldNames);
			}

			if (splittedFieldValues != null & splittedFieldValues.length > 0) {
				for (String expPropValue : splittedFieldValues) {
					expectedFieldValuesList.add(expPropValue);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedFieldValuesList, actualFieldsList)) {
				report.updateTestLog("Verify Program Component Site fields in Site Dashboard page",
						"All Fields are displayed successfully in Site Dashboard page"
								+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
								+ ",<br/><b>Actual Field Values:</b>" + actualFieldsList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Component Site fields in Site Dashboard page",
						"All Fields are failed to display in Site Dashboard page" + "<br/><b>Expected Field Values:</b>"
								+ expectedFieldValuesList + ",<br/><b>Actual Field Values:</b>" + actualFieldsList,
						Status.FAIL);
			}

			actualFieldsList.clear();
			expectedFieldValuesList.clear();
		} catch (Exception e) {
		}
	}

	// Verify created Program Component Site field values in site dashboard page
	public void verifyProgramComponentSiteFieldValues(String fieldDetails) {
		try {

			if (fieldDetails.contains(":")) {
				String splittedFieldDetails[] = fieldDetails.split(":");

				if (splittedFieldDetails != null && splittedFieldDetails.length > 1) {

					if (splittedFieldDetails[1].contains(",")) {
						String splittedFieldValues[] = splittedFieldDetails[1].split(",");
						if (splittedFieldValues != null & splittedFieldValues.length > 0) {
							for (String expPropValue : splittedFieldValues) {
								expectedFieldValuesList.add(expPropValue);
							}
						}

					} else {
						expectedFieldValuesList.add(splittedFieldValues[1]);
					}

					actualFieldsList = sitesDashboardPg
							.getContentDropdownValuesFromProgramComponent(splittedFieldDetails[0]);

					if (UIHelper.compareTwoDiffSizeOfLists(expectedFieldValuesList, actualFieldsList)) {
						report.updateTestLog("Verify Program Component Site field values in Site Dashboard page",
								"<b>" + splittedFieldDetails[0]
										+ "</b> drop down values are displayed successfully in Site Dashboard page"
										+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
										+ ",<br/><b>Actual Field Values:</b>" + actualFieldsList,
								Status.PASS);
					} else {
						report.updateTestLog("Verify Program Component Site fields in Site Dashboard page",
								"<b>" + splittedFieldDetails[0]
										+ "</b> drop down values are failed to display in Site Dashboard page"
										+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
										+ ",<br/><b>Actual Field Values:</b>" + actualFieldsList,
								Status.FAIL);
					}
				}
			}

			expectedFieldValuesList.clear();
			actualFieldsList.clear();

		} catch (Exception e) {
		}
	}

	// Verify created Program Component Site default field values in site
	// dashboard page
	public void verifyProgramComponentSiteDefaultFieldValues(String allfieldDetails) {
		try {
			if (allfieldDetails.contains(";")) {
				String splittedAllFieldDetails[] = allfieldDetails.split(";");

				for (String fieldDetails : splittedAllFieldDetails) {
					addDataToExpectedList(fieldDetails);
				}
			} else {
				addDataToExpectedList(allfieldDetails);
			}

			actualFieldsList = sitesDashboardPg.getProgramComponentFieldsDefaultValues(allfieldDetails);

			if (UIHelper.compareTwoDiffSizeOfLists(expectedFieldValuesList, actualFieldsList)) {
				report.updateTestLog("Verify Program Component Site default field values in Site Dashboard page",

						"Program Component site default field values are displayed in Site Dashboard page"
								+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
								+ ",<br/><b>Actual Field Values:</b>" + actualFieldsList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Component Site fields in Site Dashboard page",
						"Program Component site default field values are failed to display in Site Dashboard page"
								+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
								+ ",<br/><b>Actual Field Values:</b>" + actualFieldsList,
						Status.FAIL);
			}

		} catch (Exception e) {
		}
	}

	// Verify created Program Component Site default field values in site
	// dashboard page
	public void verifyProgramComponentSiteDefaultFieldValue(String allfieldDetails) {
		try {
			if (allfieldDetails.contains(";")) {
				String splittedAllFieldDetails[] = allfieldDetails.split(";");

				for (String fieldDetails : splittedAllFieldDetails) {
					addDataToExpectedList(fieldDetails);
				}
			} else {
				addDataToExpectedList(allfieldDetails);
			}

			actualFieldsDefaultValueList = sitesDashboardPg.getAllProgramComponentFieldDefaultValues(allfieldDetails);

			if (UIHelper.compareTwoDiffSizeOfLists(expectedFieldValuesList, actualFieldsDefaultValueList)) {
				report.updateTestLog("Verify Program Component Site default field values in Site Dashboard page",

						"Program Component site default field values are displayed in Site Dashboard page"
								+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
								+ ",<br/><b>Actual Field Values:</b>" + actualFieldsDefaultValueList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Component Site fields in Site Dashboard page",
						"Program Component site default field values are failed to display in Site Dashboard page"
								+ "<br/><b>Expected Field Values:</b>" + expectedFieldValuesList
								+ ",<br/><b>Actual Field Values:</b>" + actualFieldsDefaultValueList,
						Status.FAIL);
			}

		} catch (Exception e) {
		}
	}

	public void addDataToExpectedList(String fieldDetails) {
		try {
			if (fieldDetails.contains(":")) {

				expectedFieldValuesList.add(fieldDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify added Dashlet
	public void verifyAddDashletInCustomDashBrd() {
		boolean assertValue = sitesDashboardPg.addDashletInCustomDashBoard();
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");
		String colNoofAddDashletTest = dataTable.getData("Home", "ColumnNoofAddDashlet");

		if (assertValue) {
			report.updateTestLog(
					"Verify Added Dashlet", "<b>Dashlet:</b>" + dashletNmetoAddTest + " added to Column No "
							+ colNoofAddDashletTest + " using Add Dashlet Functionality in Custom Dashboard",
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify Added Dashlet", "<b>Dashlet:</b>" + dashletNmetoAddTest + " failed to add to Column No "
							+ colNoofAddDashletTest + " using Add Dashlet Functionality in Custom Dashboard",
					Status.FAIL);
		}
	}

	// Verify removed Dashlet
	public void verifyRemovedDashletInCustomDashBrd() {
		boolean assertValue = sitesDashboardPg.removeDashletFromCustomDashBoard();
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");

		if (!assertValue) {
			report.updateTestLog("Verify Removed Dashlet",
					"<b>Dashlet:</b>" + dashletNmetoAddTest + " removed successfully from Dashboard", Status.PASS);
		} else {
			report.updateTestLog("Verify Removed Dashlet",
					"<b>Dashlet:</b>" + dashletNmetoAddTest + " failed to remove from Dashboard", Status.FAIL);
		}
	}

	// Add multiple Dashlets
	public void verifyAddingOfMultipleDashlets(String dashletName, String colNoDetailsForAddDashlet) {
		try {
			if (dashletName.contains(";") && colNoDetailsForAddDashlet.contains(";")) {
				String splittedDashletNames[] = dashletName.split(";");
				String splittedColumnVal[] = colNoDetailsForAddDashlet.split(";");

				if (splittedDashletNames != null && splittedColumnVal != null
						&& splittedDashletNames.length == splittedColumnVal.length) {
					for (int index = 0; index < splittedDashletNames.length; index++) {
						if (splittedDashletNames[index].contains(",")) {
							String splittedAddDashletsForColumn[] = splittedDashletNames[index].split(",");

							if (splittedAddDashletsForColumn != null) {
								for (String addDashletForCoulmn : splittedAddDashletsForColumn) {
									sitesDashboardPg.customizeSiteDashboard();

									commonMethodForVerifyAddDashletInCustomDashBrd(addDashletForCoulmn,
											splittedColumnVal[index]);
								}
							}
						} else {
							sitesDashboardPg.customizeSiteDashboard();

							commonMethodForVerifyAddDashletInCustomDashBrd(splittedDashletNames[index],
									splittedColumnVal[index]);
						}
					}
				}
			} else {
				sitesDashboardPg.customizeSiteDashboard();

				commonMethodForVerifyAddDashletInCustomDashBrd(dashletName, colNoDetailsForAddDashlet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method for Verify Added Dashlet
	public void commonMethodForVerifyAddDashletInCustomDashBrd(String dashletNmetoAddTest, String colNoofAddDashlet) {
		boolean assertValue = sitesDashboardPg.commonMethodForAddDashletInCustomDashBoard(dashletNmetoAddTest,
				colNoofAddDashlet);

		if (assertValue) {
			report.updateTestLog("Verify Added Dashlet",
					"<b>Dashlet:</b>" + dashletNmetoAddTest + " added to Column No " + colNoofAddDashlet
							+ " using Add Dashlet Functionality in Custom Dashboard",
					Status.PASS);
		} else {
			report.updateTestLog("Verify Added Dashlet",
					"<b>Dashlet:</b>" + dashletNmetoAddTest + " failed to add to Column No " + colNoofAddDashlet
							+ " using Add Dashlet Functionality in Custom Dashboard",
					Status.FAIL);
		}
	}

	// Remove multiple dashlets
	public void verifyRemovingOfMultipleDashlets(String dashletName) {
		try {
			if (dashletName.contains(";")) {
				String splittedDashletNames[] = dashletName.split(";");

				if (splittedDashletNames != null) {
					for (String dashletNameVal : splittedDashletNames) {
						if (dashletNameVal.contains(",")) {
							String splittedDashletsForColumn[] = dashletNameVal.split(",");

							if (splittedDashletsForColumn != null) {
								for (String dashletForCoulmn : splittedDashletsForColumn) {
									if (sitesDashboardPg.checkDashletInSiteDashboard(dashletForCoulmn)) {
										sitesDashboardPg.customizeSiteDashboard();
										commonMethodForVerifyRemoveDashletInCustomDashBrd(dashletForCoulmn);
									}
								}
							}
						} else {
							if (sitesDashboardPg.checkDashletInSiteDashboard(dashletNameVal)) {
								sitesDashboardPg.customizeSiteDashboard();
								commonMethodForVerifyRemoveDashletInCustomDashBrd(dashletNameVal);
							}
						}
					}
				}
			} else {
				if (sitesDashboardPg.checkDashletInSiteDashboard(dashletName)) {
					sitesDashboardPg.customizeSiteDashboard();
					commonMethodForVerifyRemoveDashletInCustomDashBrd(dashletName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method for Verify Added Dashlet
	public void commonMethodForVerifyRemoveDashletInCustomDashBrd(String dashletNmetoAddTest) {
		boolean assertValue = sitesDashboardPg.commonMethodForRemoveDashletFromCustomDashBoard(dashletNmetoAddTest);

		if (!assertValue) {
			report.updateTestLog("Verify Removed Dashlet",
					"<b>Dashlet:</b>" + dashletNmetoAddTest + " removed successfully from Dashboard", Status.PASS);
		} else {
			report.updateTestLog("Verify Removed Dashlet",
					"<b>Dashlet:</b>" + dashletNmetoAddTest + " failed to remove from Dashboard", Status.FAIL);
		}
	}

	// Verify created Program Site field in site dashboard page
	public void verifyProgramSiteFields(String fieldNames) {
		try {
			/*
			 * String programSiteDashletText = sitesDashboardPg
			 * .getProgramDashletDetails();
			 */

			programDashletList = sitesDashboardPg.getProgramDashletFieldsDetails();

			if (fieldNames.contains(";")) {
				splittedFieldValues = fieldNames.split(";");

			} else if (fieldNames.contains(",")) {
				splittedFieldValues = fieldNames.split(",");
			} else {
				expectedFieldValuesList.add(fieldNames);
			}

			if (splittedFieldValues != null & splittedFieldValues.length > 0) {
				for (String expPropValue : splittedFieldValues) {
					if (expPropValue.contains(":")) {
						String splittedExpPropValue[] = expPropValue.split(":");
						if (splittedExpPropValue != null) {
							expectedFieldValuesList.add(splittedExpPropValue[0]);
						}
					}

				}
			}

			if (splittedFieldValues != null & splittedFieldValues.length > 0) {
				for (String expPropValue : splittedFieldValues) {
					if (expPropValue.contains(":")) {
						String splittedExpPropValue[] = expPropValue.split(":");
						if (splittedExpPropValue != null && splittedExpPropValue.length > 1
								&& !splittedExpPropValue[1].isEmpty()) {
							expectedFieldValuesList.add(splittedExpPropValue[1]);
						}
					}

				}
			}

			for (String expectVal : expectedFieldValuesList) {
				for (String actualVal : programDashletList) {
					if (actualVal.contains(expectVal) || actualVal.equalsIgnoreCase(expectVal)) {
						isDisplayedAllFieldsDataInProgramSite = true;
						break;
					} else {
						isDisplayedAllFieldsDataInProgramSite = false;
					}
				}
			}

			if (isDisplayedAllFieldsDataInProgramSite) {
				report.updateTestLog("Verify Program Site fields in Site Dashboard page",
						"All Fields are displayed successfully with default field sata in Program Site Dashboard page"
								+ "<br/><b>Expected Result:</b>" + expectedFieldValuesList + ""
								+ "<br/><b>Actual Result:</b>" + programDashletList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Site fields in Site Dashboard page",
						"All Fields are failed to display default field data in Program Site Dashboard page"
								+ "<br/><b>Expected Result:</b>" + expectedFieldValuesList + ""
								+ "<br/><b>Actual Result:</b>" + programDashletList,
						Status.FAIL);
			}

			expectedFieldValuesList.clear();
		} catch (Exception e) {
		}
	}

	// Verify created Program Site field in site dashboard page
	public void verifyProgramNameFields() {
		try {

			String expectedVal = new FileUtil().readDataFromFile(testOutputFilePath);

			String actualVal = sitesDashboardPg.getPageHeadTitleText();

			if (actualVal.contains(expectedVal) || actualVal.equalsIgnoreCase(expectedVal)) {
				report.updateTestLog("Verify the 'Program Name' field",
						"User able to see the Program Name as " + expectedVal + " in created Program Site"
								+ "<br/><b>Expected Program Name:</b>" + expectedVal + ","
								+ "<br/><b>Actual Program Name:</b>" + actualVal,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Program Name' field",
						"User not able to see the Program Name as " + expectedVal + " in created Program Site"
								+ "<br/><b>Expected Program Name:</b>" + expectedVal + ","
								+ "<br/><b>Actual Program Name:</b>" + actualVal,
						Status.FAIL);
			}

			expectedFieldValuesList.clear();
		} catch (Exception e) {
		}
	}

	// Verify created Program Component Site field in site dashboard page
	public void verifyProgramComponentTitleField() {
		try {

			String expectedVal = new FileUtil().readDataFromFile(testOutputFilePath);

			String actualVal = sitesDashboardPg.getPageHeadTitleText();

			if (actualVal.contains(expectedVal) || actualVal.equalsIgnoreCase(expectedVal)) {
				report.updateTestLog("Verify the 'Program Component Title' field in created Program component Site",
						"User able to see the Program Component Title as " + expectedVal
								+ " in created Program Component Site" + "<br/><b>Expected Program Component Title:</b>"
								+ expectedVal + "," + "<br/><b>Actual Program Component Title:</b>" + actualVal,
						Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Program Component Title' field in created Program component Site",
						"User not able to see the Program Component Title as " + expectedVal
								+ " in created Program Component Site" + "<br/><b>Expected Program Component Title:</b>"
								+ expectedVal + "," + "<br/><b>Actual Program Component Title:</b>" + actualVal,
						Status.FAIL);
			}

			expectedFieldValuesList.clear();
		} catch (Exception e) {
		}
	}

	// Verify created Program Site field in site dashboard page
	public void verifyComponentNavigatorDashlet() {
		try {

			String expectedVal = new FileUtil().readDataFromFile(testOutputFilePath);

			String actualVal = sitesDashboardPg.getSiteLinkFormComponentNavigatorDashlet();

			if (actualVal.contains(expectedVal) || actualVal.equalsIgnoreCase(expectedVal)) {
				report.updateTestLog("Verify the Program Site link in 'Component Navigator' Dashlet",
						"User able to see the Program Site Link as " + expectedVal
								+ " in 'Component Navigator' Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Program Site link in 'Component Navigator' Dashlet",
						"User not able to see the Program Site Link as " + expectedVal
								+ " in 'Component Navigator' Dashlet",
						Status.FAIL);
			}

			expectedFieldValuesList.clear();
		} catch (Exception e) {
		}
	}

	public String getTheCreatedSiteName() {
		try {
			createdSiteNameWithtimestamp = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createdSiteNameWithtimestamp;
	}

	// Get the created multiple site names
	public String[] getTheCreatedSiteNames() {
		try {
			createdSiteNames = new FileUtil().readSetOFDataFromFile(testOutputMultipleFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createdSiteNames;
	}

	// Verify user able to modify the Program Site Fields for Program Site
	// Properties fields in site dashboard page
	public void verifyProgramSiteReadOnlyFields(String fieldDetails) {
		try {

			String splittedFieldValues[];

			if (fieldDetails.contains(",")) {
				splittedFieldValues = fieldDetails.split(",");
				if (splittedFieldValues != null & splittedFieldValues.length > 0) {
					for (String expPropValue : splittedFieldValues) {
						commonMethodForValidateReadOnlyFieldsInProgSite(expPropValue);
					}
				}
			} else {
				commonMethodForValidateReadOnlyFieldsInProgSite(fieldDetails);
			}

		} catch (Exception e) {
		}
	}

	public void commonMethodForValidateReadOnlyFieldsInProgSite(String fieldDetails) {
		try {
			if (fieldDetails.contains(":")) {
				String splittedExpPropValue[] = fieldDetails.split(":");
				if (splittedExpPropValue != null && splittedExpPropValue.length > 1) {

					boolean isDisplayedReadonlyFieldInProgSite = sitesDashboardPg
							.isDisplayedReadOnlyField(splittedExpPropValue[0], splittedExpPropValue[1]);

					if (isDisplayedReadonlyFieldInProgSite) {
						report.updateTestLog(
								"Verify that the user is not able to update '" + splittedExpPropValue[0]
										+ "' of the  program site, from 'Program' dashlet",
								"User not able to update the '" + splittedExpPropValue[0]
										+ "' of the Program Site, from 'Program' dashlet",
								Status.PASS);
					} else {
						report.updateTestLog(
								"Verify that the user is not able to update '" + splittedExpPropValue[0]
										+ "' of the  program site, from 'Program' dashlet",
								"User able to update the '" + splittedExpPropValue[0]
										+ "' of the Program Site, from 'Program' dashlet",
								Status.FAIL);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Program, Program component sites under 'Component Navigator'
	// dashlet
	public void verifySiteInComponentNavigator(String progSiteName, String progComponentSite1,
			String progComponentSite2) {
		try {

			expectedSiteNamesList.add(progSiteName);
			expectedSiteNamesList.add(progComponentSite1);
			expectedSiteNamesList.add(progComponentSite2);

			actualSiteNamesList = sitesDashboardPg.getComponentNavigatorSites();

			isDisplayedComponentSites = sitesDashboardPg.isDisplayedProgSiteAsParentToComponentSites(progSiteName);

			if (UIHelper.compareTwoSimilarLists(actualSiteNamesList, expectedSiteNamesList)) {
				report.updateTestLog("Verify sites in 'Component Navigator' Dashlet",
						"<b>Sites:</b>" + expectedSiteNamesList + " are displayed in 'Component Navigator' Dashlet"
								+ "<br /><b>Expected Site Names:</b> " + expectedSiteNamesList
								+ ", <br /><b>Actual Site Names:</b> " + actualSiteNamesList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify sites in 'Component Navigator' Dashlet",
						"<b>Sites:</b>" + expectedSiteNamesList
								+ " are failed to display in 'Component Navigator' Dashlet"
								+ "<br /><b>Expected Site Names:</b> " + expectedSiteNamesList
								+ ", <br /><b>Actual Site Names:</b> " + actualSiteNamesList,
						Status.FAIL);
			}

			if (isDisplayedComponentSites) {
				report.updateTestLog("Verify Program Site as 'Parent Site' for 'Program Component Site'",
						"<br/><b>Program Site:</b>" + progSiteName
								+ " is displayed as parent site for program component sites:" + progComponentSite1
								+ ", " + progComponentSite2
								+ "<br /><b>Displayed View In Component Navigator Sashlet:</b>" + progSiteName
								+ "<br/>                " + progComponentSite1 + "<br/>                "
								+ progComponentSite2,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Site as 'Parent Site' for 'Program Component Site'",
						"<br/><b>Program Site:</b>" + progSiteName
								+ " is failed to display as parent site for program component sites:"
								+ progComponentSite1 + ", " + progComponentSite2,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify user navigated to program site or program Component Sites when
	// click on prog or component sites in component navigator dashlet
	public void verifyUserNavigatedToProgOrComponentSite(String expectedSiteName) {
		try {
			String actualSiteName = sitesDashboardPg.getPageHeadTitleText();

			if (actualSiteName.equalsIgnoreCase(expectedSiteName) || actualSiteName.contains(expectedSiteName)) {
				report.updateTestLog("Verify Site Navigation",
						"User navigated to site:" + expectedSiteName + " page Successfully"
								+ "<br /><b>Expected Site Page :</b> " + expectedSiteName
								+ ", <br /><b>Actual Site Page:</b> " + actualSiteName + "",
						Status.PASS);

			} else {
				report.updateTestLog("Verify Site Navigation",
						"User failed to navigate to site:" + expectedSiteName + " page"
								+ "<br /><b>Expected Site Page :</b> " + expectedSiteName
								+ ", <br /><b>Actual Site Page:</b> " + actualSiteName + "",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyPrgmSiteDashboardIsDisplayed() {

		try {

			isDisplayedSiteDashboard = UIHelper.checkForAnElementbyXpath(driver, siteDashboardXpath);
			UIHelper.waitFor(driver);

			if (isDisplayedSiteDashboard) {

				report.updateTestLog("Verify if Site Dashboard is displayed", "Site Dashboard is displayed ",
						Status.PASS);

			} else {

				report.updateTestLog("Verify if Site Dashboard is displayed", "Site Dashboard is not displayed ",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyPrgmSiteDashletsAreDisplayed(String expectedDefaultDashlet) {
		try {

			List<WebElement> programSiteItems = UIHelper.findListOfElementsbyXpath(programSiteDashboardItemsXpath,
					driver);

			for (WebElement ele : programSiteItems) {
				programSiteDashboardItemsList.add(ele.getText());
			}

			if (expectedDefaultDashlet.contains(",")) {
				splittedDahletNames = expectedDefaultDashlet.split(",");
			} else if (expectedDefaultDashlet.contains(";")) {
				splittedDahletNames = expectedDefaultDashlet.split(";");
			} else {
				expectedListOfDashlets.add(expectedDefaultDashlet);
			}

			if (splittedDahletNames != null && splittedDahletNames.length > 1) {

				for (String fieldLabel : splittedDahletNames) {

					expectedListOfDashlets.add(fieldLabel);
				}
			}

			boolean isEqualTwolists = false;

			isEqualTwolists = UIHelper.compareTwoSimilarLists(expectedListOfDashlets, programSiteDashboardItemsList);
			;

			if (isEqualTwolists) {

				report.updateTestLog("Verify if all Default Dashlets are displayed",
						"Program site Default Dashlets are displayed successfully"
								+ "<br/><b>Expected Dropdown Values:</b>" + expectedListOfDashlets + ","
								+ "<br/><b>Actual Dropdown Values:</b>" + programSiteDashboardItemsList,
						Status.PASS);

			} else {

				report.updateTestLog("Verify if all Default Dashlets are displayed",
						"Program site Default Dashlets failed to display" + "<br/><b>Expected Dropdown Values:</b>"
								+ expectedListOfDashlets + "," + "<br/><b>Actual Dropdown Values:</b>"
								+ programSiteDashboardItemsList,
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify 'Update Details' button in Program Dashlet
	public void verifyUpdateDetailsBtnInProgramDashlet() {
		try {
			if (sitesDashboardPg.checkUpdateDetailsBtnInProgramDashlet()) {
				report.updateTestLog("Verify 'Update Details' button on the Program dashlet in Site Dashboard page",
						"'Update Details' button displayed successfully in Program dashlet", Status.PASS);
			} else {
				report.updateTestLog("Verify 'Update Details' button on the Program dashlet in Site Dashboard page",
						"'Update Details' button failed to display in Program dashlet", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify created Program Site field Labels only in site dashboard page
	public void verifyProgramSiteFieldLabels(String fieldNames) {
		try {

			ArrayList<String> actualProgramDashletList = sitesDashboardPg.getProgramDashletFieldDetails();

			if (fieldNames.contains(";")) {
				splittedFieldValues = fieldNames.split(";");

			} else if (fieldNames.contains(",")) {
				splittedFieldValues = fieldNames.split(",");
			} else {
				expectedFieldValuesList.add(fieldNames);
			}

			if (splittedFieldValues != null & splittedFieldValues.length > 0) {
				for (String expPropValue : splittedFieldValues) {
					if (expPropValue.contains(":")) {
						String splittedExpPropValue[] = expPropValue.split(":");
						if (splittedExpPropValue != null) {
							expectedFieldValuesList.add(splittedExpPropValue[0]);
						}
					}

				}
			}

			if (splittedFieldValues != null & splittedFieldValues.length > 0) {
				for (String expPropValue : splittedFieldValues) {
					if (expPropValue.contains(":")) {
						String splittedExpPropValue[] = expPropValue.split(":");
						if (splittedExpPropValue != null && splittedExpPropValue.length > 1
								&& !splittedExpPropValue[1].isEmpty()) {
							expectedFieldValuesList.add(splittedExpPropValue[1]);
						}
					}

				}
			}

			for (String expectVal : expectedFieldValuesList) {
				for (String actualVal : actualProgramDashletList) {
					if (actualVal.contains(expectVal) || actualVal.equalsIgnoreCase(expectVal)) {
						isDisplayedAllFieldsDataInProgramSite = true;
						break;
					} else {
						isDisplayedAllFieldsDataInProgramSite = false;
					}
				}
			}

			if (isDisplayedAllFieldsDataInProgramSite) {

				report.updateTestLog("Verify fields available on the Program dashlet in Site Dashboard page",
						"All Fields are displayed successfully" + "<br/><b>Expected Values:</b>"
								+ expectedFieldValuesList + "," + "<br/><b>Actual Values:</b>"
								+ expectedFieldValuesList,
						Status.PASS);

			} else {

				report.updateTestLog("Verify fields available on the Program dashlet in Site Dashboard page",
						"Program site fields are failed to display", Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	public void commonMethodForVerifyProgramDropDowns(String fieldDetail) {
		try {
			if (fieldDetail.contains(";")) {
				String splittedFieldNames[] = fieldDetail.split(";");

				for (String fieldValues : splittedFieldNames) {
					commonMethodForAddValuesExpectedList(fieldValues);
				}
			} else {
				commonMethodForAddValuesExpectedList(fieldDetail);
			}

			actualDropdDownFieldsAndValuesInProgramDashlet = sitesDashboardPg
					.getactualDropdDownFieldsAndValuesInProgramDashlet(fieldDetail);

			commonMethodForValidateProgSiteDropdownValues(expectedDropDownValuesList,
					actualDropdDownFieldsAndValuesInProgramDashlet);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void commonMethodForAddValuesExpectedList(String fieldDetail) {
		try {
			if (fieldDetail.contains(":")) {
				expectedDropDownValuesList.add(fieldDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForValidateProgSiteDropdownValues(ArrayList<String> expectedDropDownValues,
			ArrayList<String> actualDropdDownFieldsAndValues) {
		try {
			boolean isEqualTwolists = UIHelper.compareTwoDiffSizeOfLists(expectedDropDownValues,
					actualDropdDownFieldsAndValues);

			if (isEqualTwolists) {

				report.updateTestLog("Verify Program site DropDown Values",
						"Program site Dropdown Fieldsvalues are displayed successfully"
								+ "<br/><b>Expected Dropdown Values:</b>" + expectedDropDownValues + ","
								+ "<br/><b>Actual Dropdown Values:</b>" + actualDropdDownFieldsAndValues,
						Status.PASS);

			} else {

				report.updateTestLog("Verify Program site DropDown Values",
						"Program site Dropdown Fieldsvalues are failed display"
								+ "<br/><b>Expected Dropdown Values:</b>" + expectedDropDownValues + ","
								+ "<br/><b>Actual Dropdown Values:</b>" + actualDropdDownFieldsAndValues,
						Status.FAIL);

			}

			isEqualTwolists = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifySourceCodeValueIsSameAsDivision() {
		try {

			String divisionValue = UIHelper.findAnElementbyXpath(driver, divisionXpath).getText();
			String sourceCodeValue = UIHelper.findAnElementbyXpath(driver, sourceCodeXpath).getText();
			if (divisionValue.equalsIgnoreCase(sourceCodeValue)) {
				report.updateTestLog("Verify Souce Code value is same as Division value",
						"Souce Code Value is same as Division value" + "<br/><b>Expected Source Code Value:</b>"
								+ divisionValue + "," + "<br/><b>Actual Source Code Value:</b>" + sourceCodeValue,
						Status.PASS);

			} else {

				report.updateTestLog("Verify Souce Code value is same as Division value",
						"Case Failed as Souce Code Value is not same as Division value"
								+ "<br/><b>Expected Source Code Value:</b>" + divisionValue + ","
								+ "<br/><b>Actual Source Code Value:</b>" + sourceCodeValue,
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateEditorialDisDescrpIsSameAsGroupCode() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, editorialDisciplineXpath);
			String editorialDisciplineValue = UIHelper.findAnElementbyXpath(driver, editorialDisciplineXpath).getText();
			UIHelper.highlightElement(driver, groupCodeXpath);
			String groupCodeValue = UIHelper.findAnElementbyXpath(driver, groupCodeXpath).getText();

			if (groupCodeValue.contains(editorialDisciplineValue)) {
				report.updateTestLog("Verify Editorial Discipline value is same as Group Code value",
						"Editorial Discipline Value is same as Group Code value"
								+ "<br/><b>Expected Editorial Discipline Value:</b>" + groupCodeValue + ","
								+ "<br/><b>Actual Editorial Discipline Value:</b>" + editorialDisciplineValue,
						Status.PASS);

			} else {

				report.updateTestLog("Verify Editorial Discipline value is same as Group Code value",
						"Case Failed as Editorial Discipline Value is not same as Group Code value"
								+ "<br/><b>Expected Editorial Discipline Value:</b>" + groupCodeValue + ","
								+ "<br/><b>Actual Editorial Discipline Value:</b>" + editorialDisciplineValue,
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Dashlet
	public void verifyProgramDashletAsDefaultInSiteDashBrd() {
		String dashletNme = dataTable.getData("Home", "DashletName");
		boolean assertValue = sitesDashboardPg.checkProgramDashletAsDefaultOneForProgramSite(dashletNme);

		if (!assertValue) {
			report.updateTestLog("Verify Program Dashlet as default dashlet in Site Dashboard",
					"<b>Dashlet:</b>" + dashletNme + "is not displayed as default dashlet in Site Dashboard",
					Status.PASS);
		} else {

			report.updateTestLog("Verify Program Dashlet as default dashlet in Site Dashboard",
					"<b>Dashlet:</b>" + dashletNme + "is displayed as default dashlet in Site Dashboard", Status.FAIL);
		}
	}

	// Verify Application Logo
	public void verifyApplicationLogo(String menuName) {
		String themeName = dataTable.getData("Admin", "Theme_Name");
		boolean assertValue = sitesDashboardPg.checkApplicationLogo();

		if (assertValue) {
			report.updateTestLog("Verify " + themeName + "logo under" + menuName + "Dashboard",
					"<b>" + themeName + "</b>" + " log is successfully displayed under" + menuName + "Dashboard",
					Status.PASS);
		} else {
			report.updateTestLog("Verify " + themeName + "logo under" + menuName + "Dashboard",
					"<b>" + themeName + "</b>" + " log is failed to display under" + menuName + "Dashboard",
					Status.FAIL);
		}
	}

	// Verify Blue tints in Dashlet Titles
	public void verifyBlueTintOnSiteDashboard() {
		try {
			boolean assertValue = sitesDashboardPg.checkBlueTints();

			if (assertValue) {
				report.updateTestLog("Verify blue tints in the dashlet titles on Site dashboard",
						"<b>Blue tints</b>" + " displayed successfully in the dashlet titles on Site dashboard",
						Status.PASS);
			} else {
				report.updateTestLog("Verify blue tints in the dashlet titles on Site dashboard",
						"<b>Blue tints</b>" + " are failed to display in the dashlet titles on Site dashboard",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify created Site Program Dashlet Default field in site dashboard page
	public void verifyProgramDashletDefaultFields(String fieldNames) {
		try {
			/*
			 * String programSiteDashletText = sitesDashboardPg
			 * .getProgramDashletDetails();
			 */

			programDashletList = sitesDashboardPg.getProgramDashletFieldDefaultValues();

			if (fieldNames.contains(",")) {
				splittedFieldValues = fieldNames.split(",");
				if (splittedFieldValues != null & splittedFieldValues.length > 0) {
					for (String expPropValue : splittedFieldValues) {
						if (expPropValue.contains(":")) {
							expectedFieldValuesList.add(expPropValue);
						}

					}
				}

			} else {
				expectedFieldValuesList.add(fieldNames);
			}

			for (String expectVal : expectedFieldValuesList) {
				for (String actualVal : programDashletList) {
					if (actualVal.contains(expectVal) || actualVal.equalsIgnoreCase(expectVal)) {
						isDisplayedAllFieldsDataInProgramSite = true;
						break;
					} else {
						isDisplayedAllFieldsDataInProgramSite = false;
					}
				}
			}

			if (isDisplayedAllFieldsDataInProgramSite) {
				report.updateTestLog("Verify Program Dashlet fields in Site Dashboard page",
						"Program Dashlet is not having any value by default in Site Dashboard page"
								+ "<br/><b>Expected Result:</b>" + expectedFieldValuesList + ""
								+ "<br/><b>Actual Result:</b>" + programDashletList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Program Dashlet fields in Site Dashboard page",
						"Program Dashlet is having value by default in Site Dashboard page"
								+ "<br/><b>Expected Result:</b>" + expectedFieldValuesList + ""
								+ "<br/><b>Actual Result:</b>" + programDashletList,
						Status.FAIL);
			}

			expectedFieldValuesList.clear();
		} catch (Exception e) {
		}
	}

	// Verify dashlet is availble
	public boolean verifyDashletAvailable() {
		boolean flag = false;
		try {
			String dashletName = dataTable.getData("Home", "DashletName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashletListFullXPath);
			UIHelper.findandAddElementsToaList(driver, dashletListXPath, availableDashletList);
			for (String dashlet : availableDashletList) {
				if (dashlet.equalsIgnoreCase(dashletName)) {
					flag = true;
				} else {
					flag = false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Dashlet values
	public boolean verifyDashletAvailableInDashboard() {
		boolean flag = false;
		try {
			String dashletName = dataTable.getData("Home", "DashletName");
			UIHelper.waitForVisibilityOfEleByXpath(driver, dashletValues);

			UIHelper.findandAddElementsToaList(driver, dashletValues, availableDashletList);

			for (String dashlet : availableDashletList) {
				if (dashlet.equalsIgnoreCase(dashletName)) {
					flag = true;
					break;
				} else {
					flag = false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Mysites dashlet check
	public void verifyMySitesDashletSyncMsg() {

		try {

			/*
			 * UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
			 * mySitesXpath)); UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * mySitesXpath); UIHelper.highlightElement(driver, mySitesXpath);
			 * UIHelper.highlightElement(driver, mySitesDropDownXpath);
			 * UIHelper.click(driver, mySitesDropDownXpath);
			 * 
			 * 
			 * 
			 * 
			 * WebElement Element = UIHelper.findAnElementbyXpath( driver,
			 * mySitesValueXpath); JavascriptExecutor executor =
			 * (JavascriptExecutor) driver;
			 * executor.executeScript("arguments[0].click();", Element);
			 * System.out.println(mySitesValueXpath);
			 */
			/* UIHelper.click(driver, mySitesValueXpath); */
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, mySitesXpath));
			UIHelper.highlightElement(driver, mySitesXpath);
			System.out.println(UIHelper.findAnElementbyXpath(driver, mySitesXpath).getText());
			if (UIHelper.findAnElementbyXpath(driver, mySitesXpath).getText()
					.contains("Synchronizing Remote Sites List")) {

				report.updateTestLog("Verify Mysites dashlet",
						"Sycn message displayed successfully in the dashlet title", Status.PASS);
			} else {
				report.updateTestLog("Verify Mysites dashlet", "Sycn message is not displayed in the dashlet title",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Mysites dashlet", "Failed. Sycn message is not displayed in the dashlet title",
					Status.FAIL);
		}

	}

	// Verify site dashboard page opened
	public void siteDashboardPageFrmMysites(String siteName) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath);
			if (UIHelper.findAnElementbyXpath(driver, documentLibraryXpath).getText().equalsIgnoreCase(siteName)) {
				report.updateTestLog("Verify Site dashboard",
						"Site opened successfully from Mysites " + "Site Name = " + siteName, Status.PASS);
			} else {
				report.updateTestLog("Verify Site dashboard",
						"Failed to open site from Mysites " + "Site Name = " + siteName, Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Site dashboard", "Failed " + "Site Name = " + siteName, Status.FAIL);
		}

	}

	// Verify site Dashboard page opened
	public void verifySiteDashboardPageIsOpenedFrmMysites() {
		try {

			if (sitesDashboardPg.checkSiteDashboard()) {
				report.updateTestLog("Verify Site dashboard is opened from 'My Sites' Dashlet",
						"Site Dashboard is displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Site dashboard from 'My Sites' Dashlet",
						"Site Dashboard is failed to display", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Site dashboard from 'My Sites' Dashlet", "Site Dashboard is failed to display",
					Status.FAIL);
		}

	}

	// Verify site Dashboard
	public void verifySiteDashboard() {
		try {

			if (sitesDashboardPg.checkSiteDashboard()) {
				report.updateTestLog("Verify Site dashboard", "Site Dashboard is displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Site dashboard", "Site Dashboard is failed to display", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Site dashboard", "Site Dashboard is failed to display", Status.FAIL);
		}

	}

	public void verifyIsFileAvilabileInEPMDashlet(String fileName) {
		try {
			uploadedFilesNameList = sitesDashboardPg.getUploadedFileOrFolderTitle();
			String actualName = "File Not Found";
			for (String actualFileName : uploadedFilesNameList) {
				if (actualFileName.contains(fileName)) {
					isDisplayedUploadedFile = true;
					actualName = actualFileName;
					break;
				} else {
					isDisplayedUploadedFile = false;
				}
			}

			if (isDisplayedUploadedFile) {
				report.updateTestLog("Find File/Folder",
						"File/Folder: " + fileName + " Available<br/><b>Expected Result:</b>" + fileName
								+ "<br/><b>Actual Result:</b>" + actualName,
						Status.PASS);
			} else {
				report.updateTestLog("Find File/Folder",
						"File/Folder: " + fileName + " not Available<br/><b>Expected Result:</b>" + fileName
								+ "<br/><b>Actual Result:</b>" + actualName,
						Status.FAIL);
			}

			uploadedFilesNameList.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// To Verify Library is sorted
	public void verifySortedLibrary(String sortBy) {
		try {
			ArrayList<String> sortedList = sitesDashboardPg.sortEPMDocumentLibrary(sortBy);
			boolean isSorted = true;

			for (int i = 0; i < sortedList.size() - 1; i++) {
				if (sortedList.get(i).compareToIgnoreCase(sortedList.get(i + 1)) > 0) {
					isSorted = false;
				}
			}
			if (isSorted)
				report.updateTestLog("Sort By " + sortBy, "Records sorted sucessfully by " + sortBy, Status.PASS);
			else
				report.updateTestLog("Sort By " + sortBy, "Records are not sorted sucessfully by " + sortBy,
						Status.FAIL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateEPMSearchResults(String searchContent, String property) {
		try {
			boolean isDisplayed = sitesDashboardPg.getEPMSearchResults(searchContent, property);

			if (isDisplayed) {
				report.updateTestLog("Verify Search Results",
						"Search Results are displayed as per Search Criteria:" + searchContent, Status.PASS);
			} else {
				report.updateTestLog("Verify Search Results",
						"Search Results are not displayed as per Search Criteria:" + searchContent, Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Search Results", "Search Results are not displayed as per Search Criteria",
					Status.FAIL);
		}

	}

	/**
	 * @author 412766
	 */
	public void verifyEPMSearchFields() {
		try {
			ArrayList<String> fieldsAvailableList = new ArrayList<String>();
			ArrayList<String> fieldsNotAvailableList = new ArrayList<String>();

			String empSearchFields = dataTable.getData("EPM", "EPMFields");
			StringTokenizer token = new StringTokenizer(empSearchFields, ",");
			while (token.hasMoreElements()) {
				String epmField = token.nextElement().toString();
				if (new AlfrescoSitesDashboardPage(scriptHelper).isEPMSearchFieldsAvailable(epmField)) {
					fieldsAvailableList.add(epmField);
				} else {
					fieldsNotAvailableList.add(epmField);
				}
			}

			if (fieldsNotAvailableList.size() == 0) {
				report.updateTestLog("Verify EPM search Result fields", "All Fields verified successfully"
						+ "<br/><b>Fields Verified:</b>" + fieldsAvailableList.toString(), Status.PASS);
			} else {
				report.updateTestLog("Verify EPM search Result fields", "All Fields not available"
						+ "<br/><b>Fields Not Available:</b>" + fieldsNotAvailableList.toString(), Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify EPM search Result fields", "Verify EPM search Result fields Failed",
					Status.FAIL);
		}
	}

	// Verify Folder Full Title and ISBN Value in Database Search - EPM Dashlet
	public void verifyFolderFullTitleAndISBN(String expectedFullTitle, String expectedISBN) {
		try {

			String actualFolderFullTitleVal = sitesDashboardPg.getFolderFullTitleValueFromDataBaseSearchEPMDashlet();
			String actualFolderISBNVal = sitesDashboardPg.getFolderISBNValFromDataBaseSearchEPMDashlet();

			if (actualFolderFullTitleVal.contains(expectedFullTitle) && actualFolderISBNVal.contains(expectedISBN)) {
				report.updateTestLog("Verify Folder Full Title and ISBN Value in Database Search - EPM Dashlet",
						"Folder Full Title and ISBN values are displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Folder Full Title and ISBN Value in Database Search - EPM Dashlet",
						"Folder Full Title and ISBN values are failed to display", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify Disabled Search Results Tab in Database Search - EPM Dashlet
	public void verifyDisabledSearchResultsTab() {
		try {

			if (sitesDashboardPg.isDisabledSearchResultsTabInDatabaseSearchEPM()) {
				report.updateTestLog("Verify Search Results Tab in Database Search - EPM Dashlet",
						"Search Results Tab is disabled", Status.PASS);
			} else {
				report.updateTestLog("Verify Site dashboard", "Search Results Tab is enabled", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Verify EPM Dashlet File/Folder Displayed
	public void verEPMFileFldrDisp(String fldrNme, String fleNme) {
		String fldrXPathFinal = fldrXPath.replace("CRAFT", fldrNme);
		String fleXPathFinal = fleXPath.replace("CRAFT", fleNme);
		UIHelper.waitForVisibilityOfEleByXpath(driver, fldrXPathFinal);
		UIHelper.waitForVisibilityOfEleByXpath(driver, fleXPathFinal);

		try {
			if (UIHelper.checkForAnElementbyXpath(driver, fldrXPathFinal)
					&& UIHelper.checkForAnElementbyXpath(driver, fleXPathFinal)) {
				report.updateTestLog(
						"Verify whether the files created in the document library of the archive site is displayed in the 'Database Search - EPM' dash-let of the archive review site dashboard",
						"Created Files and folders are displayed successfully ", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify whether the files created in the document library of the archive site is displayed in the 'Database Search - EPM' dash-let of the archive review site dashboard",
						"Created Files and folders are  NOT displayed successfully ", Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify added Dashlet in Site Dsahboard Page
	public void verifyPlacingOfDashletsInSiteDashboard(String layoutOption) {
		try {
			if (checkDashletPlacing(layoutOption)) {
				report.updateTestLog("Verify Placing of Dashlets",
						"All the dashlets are properly placed in respective columns in site dashboard", Status.PASS);
			} else {
				report.updateTestLog("Verify Placing of Dashlets",
						"All the dashlets are not properly placed in respective columns in site dashboard",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean checkDashletPlacing(String layoutOption) {
		boolean isPlacedDashletsProperly = false;
		try {
			switch (layoutOption) {
			case "One":
				isPlacedDashletsProperly = checkColumnOneDashlets();
				break;
			case "Two":
				isPlacedDashletsProperly = checkColumnTwoDashlets();
				break;
			case "Three":
				isPlacedDashletsProperly = checkColumnThreeDashlets();
				break;
			case "Four":
				isPlacedDashletsProperly = checkColumnFourDashlets();
				break;
			default:
				isPlacedDashletsProperly = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPlacedDashletsProperly;
	}

	// Check Column1 Dashlets
	public boolean checkColumnOneDashlets() {
		boolean isPlacedColumnDashletsProperly = false;
		try {
			sitesDashboardPg.customizeSiteDashboard();
			dashletColumn1ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("1");

			sitesDashboardPg.clickOnCancelBtnInCustomizeDasboardPg();

			dashletColumn1ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("1");

			isPlacedColumnDashletsProperly = compareTwoLists(dashletColumn1ValuesFromCustDashboard,
					dashletColumn1ValuesFromSiteDashboard);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPlacedColumnDashletsProperly;
	}

	// Check Column2 Dashlets
	public boolean checkColumnTwoDashlets() {
		boolean isPlacedColumnDashletsProperly = false;
		try {

			sitesDashboardPg.customizeSiteDashboard();

			dashletColumn1ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("1");
			dashletColumn2ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("2");

			sitesDashboardPg.clickOnCancelBtnInCustomizeDasboardPg();

			dashletColumn1ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("1");
			dashletColumn2ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("2");

			boolean isPlacedColumn1DashletsProperly = compareTwoLists(dashletColumn1ValuesFromCustDashboard,
					dashletColumn1ValuesFromSiteDashboard);
			boolean isPlacedColumn2DashletsProperly = compareTwoLists(dashletColumn2ValuesFromCustDashboard,
					dashletColumn2ValuesFromSiteDashboard);

			if (isPlacedColumn1DashletsProperly && isPlacedColumn2DashletsProperly) {
				isPlacedColumnDashletsProperly = true;
			} else {
				isPlacedColumnDashletsProperly = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPlacedColumnDashletsProperly;
	}

	// Check Column3 Dashlets
	public boolean checkColumnThreeDashlets() {
		boolean isPlacedColumnDashletsProperly = false;
		try {

			sitesDashboardPg.customizeSiteDashboard();

			dashletColumn1ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("1");
			dashletColumn2ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("2");
			dashletColumn3ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("3");

			sitesDashboardPg.clickOnCancelBtnInCustomizeDasboardPg();

			dashletColumn1ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("1");
			dashletColumn2ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("2");
			dashletColumn3ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("3");

			boolean isPlacedColumn1DashletsProperly = compareTwoLists(dashletColumn1ValuesFromCustDashboard,
					dashletColumn1ValuesFromSiteDashboard);
			boolean isPlacedColumn2DashletsProperly = compareTwoLists(dashletColumn2ValuesFromCustDashboard,
					dashletColumn2ValuesFromSiteDashboard);
			boolean isPlacedColumn3DashletsProperly = compareTwoLists(dashletColumn3ValuesFromCustDashboard,
					dashletColumn3ValuesFromSiteDashboard);

			if (isPlacedColumn1DashletsProperly && isPlacedColumn2DashletsProperly && isPlacedColumn3DashletsProperly) {
				isPlacedColumnDashletsProperly = true;
			} else {
				isPlacedColumnDashletsProperly = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPlacedColumnDashletsProperly;
	}

	// Check Column4 Dashlets
	public boolean checkColumnFourDashlets() {
		boolean isPlacedColumnDashletsProperly = false;
		try {

			sitesDashboardPg.customizeSiteDashboard();

			dashletColumn1ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("1");
			dashletColumn2ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("2");
			dashletColumn3ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("3");
			dashletColumn4ValuesFromCustDashboard = sitesDashboardPg.getDashletByColumnInAddDashletPage("4");

			sitesDashboardPg.clickOnCancelBtnInCustomizeDasboardPg();

			dashletColumn1ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("1");
			dashletColumn2ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("2");
			dashletColumn3ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("3");
			dashletColumn4ValuesFromSiteDashboard = sitesDashboardPg.getDashletByColumnInSiteDashboardPage("4");

			boolean isPlacedColumn1DashletsProperly = compareTwoLists(dashletColumn1ValuesFromCustDashboard,
					dashletColumn1ValuesFromSiteDashboard);
			boolean isPlacedColumn2DashletsProperly = compareTwoLists(dashletColumn2ValuesFromCustDashboard,
					dashletColumn2ValuesFromSiteDashboard);
			boolean isPlacedColumn3DashletsProperly = compareTwoLists(dashletColumn3ValuesFromCustDashboard,
					dashletColumn3ValuesFromSiteDashboard);
			boolean isPlacedColumn4DashletsProperly = compareTwoLists(dashletColumn4ValuesFromCustDashboard,
					dashletColumn4ValuesFromSiteDashboard);

			if (isPlacedColumn1DashletsProperly && isPlacedColumn2DashletsProperly && isPlacedColumn3DashletsProperly
					&& isPlacedColumn4DashletsProperly) {
				isPlacedColumnDashletsProperly = true;
			} else {
				isPlacedColumnDashletsProperly = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPlacedColumnDashletsProperly;
	}

	// Compare Two Lists data
	public boolean compareTwoLists(ArrayList<String> list1, ArrayList<String> list2) {
		boolean isEqualTwoListsData = false;
		try {
			if (UIHelper.compareTwoDiffSizeOfLists(list1, list2)) {
				isEqualTwoListsData = true;
			} else {
				isEqualTwoListsData = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isEqualTwoListsData;
	}

	// Verify Header names in 'Async Status For EPS &* Media Transform' Dashlet
	public void verifyHeaderNamesInAsyncDashlet(String expectedHeaderNames) {
		try {
			ArrayList<String> expectedHeaderNamesList = new ArrayList<String>();

			if (expectedHeaderNames.contains(";")) {
				String splittedExpHeaderValues[] = expectedHeaderNames.split(";");

				if (splittedExpHeaderValues != null) {
					for (String expectHeaderName : splittedExpHeaderValues) {
						expectedHeaderNamesList.add(expectHeaderName.trim());
					}
				}
			}
			ArrayList<String> actualHeaderNamesList = sitesDashboardPg.getAsyncDashletHeaderNames();
			boolean isDisplayedHeadersInAsyncDashlet = UIHelper.compareTwoDiffSizeOfLists(actualHeaderNamesList,
					expectedHeaderNamesList);

			if (isDisplayedHeadersInAsyncDashlet) {
				report.updateTestLog("Verify the Header names",
						"Table Headers: " + expectedHeaderNamesList
								+ " are displayed successfully in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Header names",
						"Table Headers: " + expectedHeaderNamesList
								+ " are failed to display in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify that the 'Message' header inside the dashlet displays the status
	// of the Publish for the Job Type 'EPS'
	public void verifyPublishedFileQueuStatusInAsyncDashlet(String expectedQueueNames, String itemName) {
		try {
			ArrayList<String> expectedQueueNamesList = new ArrayList<String>();

			if (expectedQueueNames.contains(";")) {
				String splittedExpectQueueValues[] = expectedQueueNames.split(";");

				if (splittedExpectQueueValues != null) {
					for (String expectQueueName : splittedExpectQueueValues) {
						expectedQueueNamesList.add(expectQueueName.trim());
					}
				}
			}

			ArrayList<String> actualQueueNamesList = sitesDashboardPg
					.getPublishedFileQueuedStatusFromAsyncDashlet(itemName);
			boolean isDisplayedQueueNamesForEPS = UIHelper.compareTwoDiffSizeOfLists(actualQueueNamesList,
					expectedQueueNamesList);

			if (isDisplayedQueueNamesForEPS) {
				report.updateTestLog("Verify the 'Message' header value for the Job Type 'EPS'",
						"'Message' header successfully displayed the queued status: " + expectedQueueNamesList
								+ " for Job Type: 'EPS' in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Message' header value for the Job Type 'EPS'",
						"'Message' header failed to display the queued status: " + expectedQueueNamesList
								+ " for Job Type: 'EPS' in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Negative case - Verify that the 'Message' header inside the dashlet displays the status
	// of the Publish for the Job Type 'EPS'
	public void verifyPublishedFileQueuStatusInAsyncDashletForNegativeCase(String expectedQueueNames, String itemName) {
		try {
			ArrayList<String> expectedQueueNamesList = new ArrayList<String>();

			if (expectedQueueNames.contains(";")) {
				String splittedExpectQueueValues[] = expectedQueueNames.split(";");

				if (splittedExpectQueueValues != null) {
					for (String expectQueueName : splittedExpectQueueValues) {
						expectedQueueNamesList.add(expectQueueName.trim());
					}
				}
			}

			ArrayList<String> actualQueueNamesList = sitesDashboardPg
					.getPublishedFileQueuedStatusFromAsyncDashlet(itemName);
			boolean isDisplayedQueueNamesForEPS = UIHelper.compareTwoDiffSizeOfLists(actualQueueNamesList,
					expectedQueueNamesList);

			if (!isDisplayedQueueNamesForEPS) {
				report.updateTestLog("Verify the 'Message' header value for the Job Type 'EPS'",
						"he status of the profile transformation not include the " + expectedQueueNamesList
								+ " in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the 'Message' header value for the Job Type 'EPS'",
						"'Message' header successfully displayed the queued status: " + expectedQueueNamesList
								+ " in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify that user able to see only the first 10 status of the process in
	// 'Async Status For EPS &* Media Transform' Dashlet
	public void verifyNumberOfEntriesInAsyncDashlet() {
		try {

			if (sitesDashboardPg.checkEntriesInAsyncStatusForEPSDashlet()) {
				report.updateTestLog("Verify the Process Entries",
						"User able to see only the first 10 status of the process in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the Process Entries",
						"User unable to see only the first 10 status of the process in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check User able to see the page navigation and able to navigate to
	// different pages
	public void verifyPageNavigationForAsyncStatusDashlet() {
		try {

			boolean isUserNavigatedToDifferentPages = false;

			if (sitesDashboardPg.checkPageNavigation() && sitesDashboardPg.checkUserNavigationToDifferentPages()) {
				isUserNavigatedToDifferentPages = true;
			} else {
				isUserNavigatedToDifferentPages = false;
			}

			if (isUserNavigatedToDifferentPages) {
				report.updateTestLog("Verify the page navigation",
						"User able to see the page navigation and able to navigate to different pages in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.PASS);
			} else {
				report.updateTestLog("Verify the page navigation",
						"User unable to see the page navigation and unable to navigate to different pages in 'Async Status For EPS & MediaTransform' Dashlet",
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check sort is working for "Job type","Item name", "Message", "Time",
	// "Site" columns in 'Async Status For EPS &* Media Transform' Dashlet
	public void verifySortingInAsyncDashlet(String sortBy) {
		try {

			if (sitesDashboardPg.performSortOperationForAsyncStatusHeaders(sortBy)) {
				report.updateTestLog("Verify sort is working for '" + sortBy + "'",
						"User able to sort the values in 'Async Status For EPS & MediaTransform' Dashlet using sort option: "
								+ sortBy,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify sort is working for '" + sortBy + "' in Async Status For EPS & MediaTransform Dashlet",
						"User unable to sort the values  in 'Async Status For EPS & MediaTransform' Dashlet using sort option: "
								+ sortBy,
						Status.FAIL);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify uploaded file property values in Document Details Page
	public void verifyFirstThreeMyActivitiesDetails(String activities) {
		try {
			actualActivitiesList = sitesDashboardPg.getFirstThreeActivities();

			if (activities.contains(";")) {
				splittedActivities = activities.split(";");

			} else if (activities.contains(",")) {
				splittedActivities = activities.split(",");
			} else {
				expectedActivitiesList.add(activities);
			}

			if (splittedActivities != null & splittedActivities.length > 0) {
				for (String expActivityValue : splittedActivities) {
					expectedActivitiesList.add(expActivityValue);
				}
			}

			if (UIHelper.compareTwoDiffSizeOfLists(expectedActivitiesList, actualActivitiesList)) {
				report.updateTestLog("Verify performed activities in 'My Activities' Dashlet in Home Page",
						"Performed activities:" + activities + " are succefully displayed in 'My Activities' Dashlet"
								+ "<br/><b>Performed Activities on Document:</b>" + expectedActivitiesList,
						Status.PASS);
			} else {
				report.updateTestLog("Verify performed activities in 'My Activities' Dashlet in Home Page",
						"Performed activities:" + activities + " are failed to display in 'My Activities' Dashlet"
								+ "<br/><b>Performed Activities on Document:</b>" + expectedActivitiesList,
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verifyErrorAfterDeleteSiteInDashboard()
	{
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,deleteSiteErrorMsgXpath);
			UIHelper.highlightElement(driver, deleteSiteErrorMsgXpath);
			report.updateTestLog("Verify Error message in Dashboard Page",
					"Error message successfully displayed in Dashboard Page ",
					Status.PASS);
	}
		catch(Exception e)
		{			
			report.updateTestLog("Verify Error message in Dashboard Page",
					"Error message not displayed in Dashboard Page ",
					Status.FAIL);
			}
		}
	
	public void clickOnBackToDashBoard() {
		try {
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,backToDashboardButtonXpath);
		UIHelper.highlightElement(driver,backToDashboardButtonXpath);
		UIHelper.click(driver, backToDashboardButtonXpath);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		report.updateTestLog("Verify Click on Back To DashBoard",
				"Back To DashBoard clicked  successfull in Error Dashboard Page ",
				Status.PASS);
		
	}catch(Exception e){
		report.updateTestLog("Verify Click on Back To DashBoard",
				"Back To DashBoard click  Unsuccessfull in  Error Dashboard Page ",
				Status.FAIL);
	}
	  }
		
}
