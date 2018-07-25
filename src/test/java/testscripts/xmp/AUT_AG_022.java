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

public class AUT_AG_022 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void runAUT_AG_022() {
        testParameters.setCurrentTestDescription("Verify the additional xmp mapping for AI file with below fields:"
        		+ "<br> XMP Keywords --> Keywords (Content Asset aspect)"
        		+ "<br> XMP Source ID --> Source ID (Content Asset aspect)"
        		+ "<br> XMP Source --> Asset Source (Content Asset aspect)"
        		+ "<br> XMP Credit Line --> Caption (Content Asset aspect)");

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
        homePageObj.navigateToSitesTab();

        AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
        AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
                scriptHelper);
        AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
                scriptHelper);

        String siteName = dataTable.getData("Sites", "SiteName");
        sitesPage.siteFinder(siteName);

        sitesPage.enterIntoDocumentLibrary();
        
        AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();

        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
        
        String filePath = dataTable.getData("MyFiles", "FilePath");
        String fileName = dataTable.getData("MyFiles", "FileName");
        
        myFiles.uploadFileInMyFilesPage(filePath, fileName);
        myFilesTestObj.verifyUploadedMultipleFiles(fileName);
        
        sitesPage.clickOnEditProperties(fileName);
		docDetailsPageObj.clickAllProperties();
		
		String docProperties = dataTable.getData("Document_Details", "DocProperties");
		docDetailsPageTestObj.verifyFieldsInEditProperties(docProperties);
        
		String fieldsData = dataTable.getData("Document_Details", "DefaultFieldValues");
		docDetailsPageTestObj.verifyFieldValuesInEditPropPage(fieldsData);
		
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
	    docDetailsPageTestObj.verifyUploadedFileIsOpened(fileName, "");
	        
		docDetailsPageObj.performManageAspectsDocAction();
		
		docDetailsPageTestObj.verifyAspectsAvailable();
		docDetailsPageObj.deleteAspectsAndApllyChangesToAFile();
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPageObj.clickAllProperties();
		
		docDetailsPageTestObj.verifyFieldsInEditPropertiesForNegativeCase(docProperties);
        
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
		
		docDetailsPageObj.performManageAspectsDocAction();
		docDetailsPageTestObj.verifyAspectsAvailable();
		docDetailsPageObj.addAspectsAndApllyChangesToAFile();
		
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openUploadedOrCreatedFile(fileName, "");
		docDetailsPageTestObj.verifyXMPMetaaDataAttributesInPropertySec(fieldsData);
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPageObj.clickAllProperties();
		
		docDetailsPageTestObj.verifyFieldsInEditProperties(docProperties);
		//docDetailsPageTestObj.verifyFieldValuesInEditPropPage(fieldsData);
		
		docDetailsPageObj.enterDataAndSaveIntoEditProperties();
        
        String propertyValues = dataTable.getData("Document_Details", "PropertyFieldValues");
        docDetailsPageTestObj.verifyXMPMetaaDataAttributesInPropertySec(propertyValues);

    }

    @Override
    public void tearDown() {
        // Nothing to do
    }
}