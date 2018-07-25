package testscripts.xmp;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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

public class AUT_AG_010 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void xmp_10() {
        testParameters.setCurrentTestDescription("1. Verify if the XMP metadata Properties are visible in a newly imported EPS file"
        		+ "<br>2. Verify if the user is able to edit/update the metadata Properties in a EPS file.");
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
        ArrayList<String> folderNamesList = new ArrayList<String>();

        AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
        functionalLibrary.loginAsValidUser(signOnPage);

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
        homePageObj.navigateToSitesTab();

        AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
        AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
                scriptHelper);
        AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
                scriptHelper);

        String siteName = dataTable.getData("Sites", "SiteName");
        sitesPage.siteFinder(siteName);
       
        sitesPage.enterIntoDocumentLibrary();

        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

        String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
        myFiles.deleteCreatedFolder(folderDetails);

        myFiles.createFolder(folderDetails);

        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
        myFilesTestObj.verifyCreatedFolder(folderDetails);

        folderNamesList = myFiles.getFolderNames(folderDetails);

        String filePath = dataTable.getData("MyFiles", "FilePath");
        String fileName = dataTable.getData("MyFiles", "FileName");

        for (String folderName : folderNamesList) {
        	myFiles.openCreatedFolder(folderName);

            myFiles.uploadFileInMyFilesPage(filePath, fileName);
            myFilesTestObj.verifyUploadedMultipleFiles(fileName);

            myFiles.openUploadedOrCreatedFile(fileName, "");
            docDetailsPageTestObj.verifyUploadedFileIsOpened(fileName, "");
            
            String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
            docDetailsPageTestObj.verifyXMPMetaaDataAttributesInPropertySec(propertyValues);
            
            docDetailsPageObj.editFileProperties();
            String editPropertyTitleValue = dataTable.getData("Document_Details", "Title");
            docDetailsPageTestObj.verifyEditedPropertiesForFile(editPropertyTitleValue);
        }

    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}