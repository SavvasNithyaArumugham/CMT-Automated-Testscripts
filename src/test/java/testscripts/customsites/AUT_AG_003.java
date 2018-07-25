package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_003 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_003()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to create a Program site"
				+"<br>2. Verify program dashlet is not displayed by default in Program site template"
				+"<br>3. Verify the GUI of 'Program' dashlet"
				+"<br>4. Verify the default values in all the fields on 'Program' dashlet, when a program site is created"
				+"<br>5. Verify that the 'Program Name' field display site name as entered on 'Create Site' screen"
				+"<br>6. Verify that the user is not able to update 'Program Name' of the  program site, from 'Program' dashlet"
				+"<br>7. Verify that the 'Component Navigator' dashlet, contains link for the program site"
				+"<br>8. Verify the values available in the 'Company' dropdown on 'Program' dashlet"
				+"<br>9. Verify that the user is able to set/update properties of a program site, from 'Program' dashlet"
				+"<br>10. Verify the values available in the Division and Source code dropdown on selecting Company attribute as 'USHE' of the Program Dashlet"
				+"<br>11. Verify the values on Source Code attribute is automatically populated with same value of Division"
				+"<br>12. Verify the values available in Group Code Dropdown on selecting particular Division/Source Code dropdown of the Program Dashlet"
				+"<br>13. Verify the values available in the 'Editorial Discipline' dropdown is automatically populated with same value of Group Code on 'Program' dashlet"
				+"<br>14. Verify the values available in the 'Discipline' dropdown on 'Program' dashlet"
				+"<br>15. Verify the values available in the 'Super Discipline' dropdown on 'Program' dashlet"
				+"<br>16. Verify values are same for the Source code and Division Dropdown even after user update either source code/Divison field"
				+"<br>17. Verify values are same for the Group Code and Editorial Discipline Dropdown even after user update either Group Code /Editorial Discipline"
				+"<br>18. Verify the values available in the 'Type' dropdown on the 'Create Site' screen, when opened from a Program Site");
		
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		siteTestObj.verifyCreatedSite();
		
		sitesDashboardPageTest.verifySiteDashboard();
		
		/*homePageObj.navigateToSitesTab();
		String siteName = siteTestObj.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);*/
		
		sitesDashboardPageTest.verifyProgramDashletAsDefaultInSiteDashBrd();
		
		String dashletName = dataTable.getData("Home", "DashletName");
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashletName))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		String fieldNames = dataTable.getData("Sites", "SiteDashletFieldNames");
		sitesDashboardPageTest.verifyProgramSiteFieldLabels(fieldNames);
		
		sitesDashboardPageTest.verifyUpdateDetailsBtnInProgramDashlet();
		
		String programDashletFieldDefaultNames = dataTable.getData("Sites", "SiteDashletFieldValues");
		sitesDashboardPageTest.verifyProgramSiteFields(programDashletFieldDefaultNames);
		
		String fieldDetail = dataTable.getData("Sites", "SiteDashletDropdownFieldValues1");
		sitesDashboardPageTest.commonMethodForVerifyProgramDropDowns(fieldDetail);
		
		sitesDashboardPageTest.verifyProgramNameFields();
		
		String fieldDetails = dataTable.getData("Sites", "DashletFieldsDefaultValues");
		sitesDashboardPageTest.verifyProgramSiteReadOnlyFields(fieldDetails);
		
		sitesDashboardPageTest.verifyComponentNavigatorDashlet();
		
		String compDropdownFieldDetails = dataTable.getData("Sites", "SiteDashletFieldDetails1");
		sitesDashboardPage.enterDataInProgramSiteProperties(compDropdownFieldDetails);
		
		String divisionAndSourceCodeFieldDetails = dataTable.getData("Sites", "SiteDashletDropdownFieldValues2");
		sitesDashboardPageTest.commonMethodForVerifyProgramDropDowns(divisionAndSourceCodeFieldDetails);
		
		String companyAndDisvisionFieldDetails = dataTable.getData("Sites", "SiteDashletFieldDetails2");
		sitesDashboardPage.enterDataInProgramSiteProperties(companyAndDisvisionFieldDetails);
		
		String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
		docLibPgTest.verifyConfirmationDailogMessage(expectedMessageVal);
								
		sitesDashboardPageTest.verifySourceCodeValueIsSameAsDivision();
		
		String groupCodeAndEdDeciplineFieldDetails = dataTable.getData("Sites", "SiteDashletDropdownFieldValues3");
		sitesDashboardPageTest.commonMethodForVerifyProgramDropDowns(groupCodeAndEdDeciplineFieldDetails);
		
		sitesDashboardPageTest.validateEditorialDisDescrpIsSameAsGroupCode();
		
		String divisionFieldDetail = dataTable.getData("Sites", "SiteDashletFieldDetails3");
		sitesDashboardPage.enterDataInProgramSiteProperties(divisionFieldDetail);
		
		sitesDashboardPageTest.verifySourceCodeValueIsSameAsDivision();
		
		String compGroupSourceCodeFieldDetails = dataTable.getData("Sites", "SiteDashletFieldDetails4");
		sitesDashboardPage.enterDataInProgramSiteProperties(compGroupSourceCodeFieldDetails);
		
		String groupCodeFieldDetails = dataTable.getData("Sites", "SiteDashletFieldDetails5");
		sitesDashboardPage.enterDataInProgramSiteProperties(groupCodeFieldDetails);
		sitesDashboardPageTest.validateEditorialDisDescrpIsSameAsGroupCode();
		
		homePageObj.navigateToSitesTab();
		sitesPage.clickOnCreateSite();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		siteTestObj.verifyCreateSiteTypeValues();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}