package testscripts.xmp;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_004 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void xmp_04() {
        testParameters.setCurrentTestDescription(
                "Verify if the user is able to link a file with XMP metadata to another location.");
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
        ArrayList<String> sourceFilePropertiesList = new ArrayList<String>();
        ArrayList<String> targetFilePropertiesList = new ArrayList<String>();
        AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
        functionalLibrary.loginAsValidUser(signOnPage);

        String filePath = dataTable.getData("MyFiles", "FilePath");
        String fileName = dataTable.getData("MyFiles", "FileName");

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
        homePageObj.navigateToSitesTab();

        AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
        String sourceSiteName = dataTable.getData("Sites", "SiteName");

        sitesPage.siteFinder(sourceSiteName);
        sitesPage.enterIntoDocumentLibrary();
        
        AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
    
		docLibPg.deleteAllFilesAndFolders();
	

        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

        myFiles.createFolder(folderDetails);

        myFiles.uploadFileInMyFilesPage(filePath, fileName);

        sitesPage.clickOnMoreSetting(fileName);
        docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,"Link To");

        AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
                scriptHelper);
        docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
        docDetailsPageObj.clickLinkBtnInLinkPopUp();

        myFiles.openUploadedOrCreatedFile(fileName, "");

        sourceFilePropertiesList = docDetailsPageObj.getPropertiesOfFile(fileName);

        sitesPage.enterIntoDocumentLibrary();

        folderNamesList = myFiles.getFolderNames(folderDetails);

        for (String folderName : folderNamesList) {
            myFiles.openCreatedFolder(folderName);
            myFiles.openUploadedOrCreatedFile(fileName, "");
            targetFilePropertiesList = docDetailsPageObj.getPropertiesOfFile(fileName);
        }

        docDetailsPageObj.comparePropertiesOfTwoFiles(
                sourceFilePropertiesList,
                targetFilePropertiesList);

    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}