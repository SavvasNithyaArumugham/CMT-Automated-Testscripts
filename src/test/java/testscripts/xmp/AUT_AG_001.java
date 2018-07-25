package testscripts.xmp;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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

public class AUT_AG_001 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void xmp_01() {
        testParameters.setCurrentTestDescription("1. Verify if the XMP metadata attributes are visible in the properties tab of any asset with the format mentioned in the formats tab when an asset is uploaded"
        		+ "<br>2. Verify if the user updates the value of Title in the local system it is reflected in the Title column in Alfresco properties of the file on import or edit offlline"
        		+ "<br>3. Verify if the user updates the value of Creator in the local system it is reflected in the Creator column in Alfresco properties of the file on import or upload new version");
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


        AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
        String siteName = dataTable.getData("Sites", "SiteName");
        
        sitesPage.siteFinder(siteName);
        sitesPage.enterIntoDocumentLibrary();
        
        AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

        String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
    //    myFiles.deleteCreatedFolder(folderDetails);

        myFiles.createFolder(folderDetails);

        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
   //     myFilesTestObj.verifyCreatedFolder(folderDetails);

        folderNamesList = myFiles.getFolderNames(folderDetails);

        String filePath = dataTable.getData("MyFiles", "FilePath");
        String fileName = dataTable.getData("MyFiles", "FileName");

        for (String folderName : folderNamesList) {
        	sitesPage.documentdetails(folderName);

            myFiles.uploadFileInMyFilesPage(filePath, fileName);

     //       myFilesTestObj.verifyUploadedMultipleFiles(fileName);

            AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
                    scriptHelper);

            sitesPage.documentdetails(fileName);

     //       docDetailsPageTest.verifyUploadedFileIsOpened(fileName, "");

            AlfrescoDocumentDetailsPageTest docDetailsPgTest = new AlfrescoDocumentDetailsPageTest(
                    scriptHelper);
            String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
            docDetailsPgTest.verifyXMPMetaaDataAttributesInPropertySecWithCreator(propertyValues);
        }

    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}