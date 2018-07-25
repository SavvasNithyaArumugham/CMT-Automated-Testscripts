package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_229 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void misc3_019() {
        testParameters.setCurrentTestDescription("Verify that user is able to Manage Permission for the file");
        testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

        driverScript = new DriverScript(testParameters);
        driverScript.driveTestExecution();
    }

    @Override
    public void setUp() {
        functionalLibrary = new FunctionalLibrary(scriptHelper);
        report.addTestLogSection("Setup");

        driver.get(properties.getProperty("ApplicationUrl"));
        report.updateTestLog(
                "Invoke Application",
                "Invoke the application under test @ " + properties.getProperty("ApplicationUrl"),
                Status.DONE);

    }

    @Override
    public void executeTest() {

        AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
        functionalLibrary.loginAsValidUser(signOnPage);

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

        AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        AlfrescoSitesPageTest sitePgTest = new AlfrescoSitesPageTest(scriptHelper);

        homePageObj.navigateToSitesTab();
        
        String siteName = dataTable.getData("Sites", "SiteName");
        String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
        sitesPage.openSiteFromRecentSites(siteName);
        /*sitesPage.enterIntoMySitesPage();
        sitesPage.openSite();*/
        sitesPage.enterIntoDocumentLibrary();

        /*AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();*/
        myFiles.deleteUploadedFile(fileName);

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
        
        myFilesTestObj.verifyUploadedFile(fileName,"");

        sitesPage.managePermissionOnFile();
        
        sitesPage.clickOnAddUserGroupButton();
        
        String roleName = dataTable.getData("Sites", "Role");
        sitesPage.mangePermission(roleName);
        
        sitePgTest.verifyManagePermissionsOnFile(roleName);
    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}