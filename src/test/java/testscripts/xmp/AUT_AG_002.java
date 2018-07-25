package testscripts.xmp;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_002 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void XMP_002() {
        testParameters.setCurrentTestDescription(
                "Verify if the user is able to copy a file with XMP metadata to a another location.");
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

        String filePath = dataTable.getData("MyFiles", "FilePath");
        String fileName = dataTable.getData("MyFiles", "FileName");

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
        AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
        homePageObj.navigateToSitesTab();

        AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
        String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
        String sourceSiteName = dataTable.getData("Sites", "SiteName");

        sitesPage.siteFinder(targetSiteName);

        /*
         * sitesPage.enterIntoMySitesPage();
         * 
         * sitesPage.openTargetSite();
         */

        sitesPage.enterIntoDocumentLibrary();

        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        docLibPg.deleteAllFilesAndFolders();

        homePageObj.navigateToSitesTab();
        sitesPage.openSiteFromRecentSites(sourceSiteName);
        /*
         * sitesPage.enterIntoMySitesPage(); sitesPage.openSite();
         */
        sitesPage.enterIntoDocumentLibrary();

        docLibPg.deleteAllFilesAndFolders();

        myFiles.uploadFileInMyFilesPage(filePath, fileName);

        AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
                scriptHelper);

        sitesPage.documentdetails(fileName);

        AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
                scriptHelper);

        docDetailsPageObj.clickCopyToDocAction();
        docDetailsPageObj.selectFileInCopyPopUp();
       

      

        sitesPage.siteFinder(targetSiteName);
        /*
         * sitesPage.enterIntoMySitesPage();
         * 
         * sitesPage.openTargetSite();
         */

        sitesPage.enterIntoDocumentLibrary();
        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
        myFilesTestObj.verifyCopieddFileInTargetSite(fileName, "");
        sitesPage.documentdetails(fileName);
        AlfrescoDocumentDetailsPageTest docDetailsPgTest = new AlfrescoDocumentDetailsPageTest(
                scriptHelper);
        String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
        docDetailsPgTest.verifyXMPMetaaDataAttributesInPropertySec(propertyValues);

    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}