package testscripts.movetocopyto;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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

public class AUT_AG_002 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void movetocopyto_02() {
        testParameters.setCurrentTestDescription(
                "Validate that when user moves content from one site to another then permission on that content should overwrite with those of the new site");
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
        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        AlfrescoSitesPageTest sitePgTest = new AlfrescoSitesPageTest(scriptHelper);

        String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
        String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
        String sourceSiteName = dataTable.getData("Sites", "SiteName");
        
        homePageObj.navigateToSitesTab();
        sitesPage.openSiteFromRecentSites(targetSiteName);
        sitesPage.enterIntoDocumentLibrary();
        myFiles.deleteCreatedFolder(folderDetails);

        homePageObj.navigateToSitesTab();
        sitesPage.openSiteFromRecentSites(sourceSiteName);
        sitesPage.enterIntoDocumentLibrary();
        myFiles.deleteCreatedFolder(folderDetails);

        myFiles.createFolder(folderDetails);
        myFilesTestObj.verifyCreatedFolder(folderDetails);

        folderNamesList = myFiles.getFolderNames(folderDetails);

        for (String folderName : folderNamesList) {
            myFiles.openCreatedFolder(folderName);

            String filePath = dataTable.getData("MyFiles", "FilePath");
    		String fileName = dataTable.getData("MyFiles", "FileName");
    		myFiles.uploadFileInMyFilesPage(filePath, fileName);

            myFilesTestObj.verifyUploadedMultipleFiles(fileName);
        }

        myFiles.navigateToMyFilesContainer();
        
        sitesPage.manageUserPermForFileMoveInOneSiteToAnother();
        
        sitesPage.clickOnAddUserGroupButton();
        
        sitesPage.clickOnUserRoleBtn();
        
        sitesPage.writeUserRoleDetails();
        
        sitesPage.clickSaveButtonInManagePermission();

        sitesPage.moveFolderFromOneSiteToAnother();
        
        homePageObj.navigateToSitesTab();

        sitesPage.openSiteFromRecentSites(targetSiteName);
        sitesPage.enterIntoDocumentLibrary();

        myFilesTestObj.verifyMovedFolder(folderDetails);
        
        sitesPage.manageUserPermForFileMoveInOneSiteToAnother();
        
        sitesPage.clickOnUserRoleBtn();
        
        sitePgTest.verifyMovedFolderUserRoleInTargetSite();
    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}