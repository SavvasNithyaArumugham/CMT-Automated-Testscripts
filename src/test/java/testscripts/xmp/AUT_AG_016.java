package testscripts.xmp;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_016 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void xmp_16()
	{
		testParameters.setCurrentTestDescription("1. Verify that default property(Title, Creator and Description) is updated for JPG File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>2. Verify that XMP property(Title, Creator and Description) is updated for JPG File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>3. Verify that default property(Title, Creator and Description) is updated for TIFF File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>4. Verify that Xmp properties is updated for TIFF File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>5. Verify that default property(Title, Creator and Description) is updated for AVI File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>6. Verify that Xmp properties is updated for avi File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>7. Verify that default property(Title, Creator and Description) is updated for PDF File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>8. Verify that default property(Title, Creator and Description) is updated for XLSX File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>9. Verify that default property(Title, Creator and Description) is updated for docx File when user Performs Upload as new Version for the File which has properties value as Null"
				+ "<br>10. Verify that default property(Title, Creator and Description) is updated for PPTX File when user Performs Upload as new Version for the File which has properties value as Null");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");

		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);		
		docLibPg.deleteAllFilesAndFolders();		
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNames = dataTable.getData("MyFiles", "FileName");
		String fileNamesForUploadNewVersion = dataTable.getData("Document_Details", "FileName");
		String docProperties = dataTable.getData("Document_Details", "DocProperties");
		String docPropertiesValue = dataTable.getData("Document_Details", "DocPropertyValues");
		
		ArrayList<String> docPropertiesList = new ArrayList<String>();
		StringTokenizer tokenPro = new StringTokenizer(docProperties, ";");
		while (tokenPro.hasMoreElements()) {
			docPropertiesList.add(tokenPro.nextElement().toString());
		}
		
		ArrayList<String> docPropertiesValueList = new ArrayList<String>();
		StringTokenizer tokenProVal = new StringTokenizer(docPropertiesValue, ";");
		while (tokenProVal.hasMoreElements()) {
			docPropertiesValueList.add(tokenProVal.nextElement().toString());
		}
		
		myFiles.uploadFileInMyFilesPage(filePath, fileNames);	
		myFilesTestObj.verifyUploadedMultipleFiles(fileNames);
		
		ArrayList<String> fileNamesList = myFiles.getFileNames(fileNames);
		ArrayList<String> fileNamesListForUploadNewVersion = myFiles.getFileNames(fileNamesForUploadNewVersion);
		
		int index=0;
		for(String fileName:fileNamesList)
		{
			ArrayList<String> docPropertiesLabelList = new ArrayList<String>();
			StringTokenizer tokenPropLabel = new StringTokenizer(docPropertiesList.get(index), ",");
			while (tokenPropLabel.hasMoreElements()) {
				docPropertiesLabelList.add(tokenPropLabel.nextElement().toString());
			}
			
			ArrayList<String> docPropertiesLabelValueList = new ArrayList<String>();
			StringTokenizer tokenPropLabelVal = new StringTokenizer(docPropertiesValueList.get(index), ",");
			while (tokenPropLabelVal.hasMoreElements()) {
				docPropertiesLabelValueList.add(tokenPropLabelVal.nextElement().toString());
			}
		
			docLibPgObj.openAFile(fileName);
			
			if(index!=1)
			{
				docDetailsPageTest.verifyNullProperties(fileName, docPropertiesLabelList);
			}
			
			UIHelper.waitFor(driver);
			docDetailsPage.commonMethodForUploadNewVersionFile(fileNamesListForUploadNewVersion.get(index));
			UIHelper.waitFor(driver);
			docDetailsPageTest.verifyAddedProperties(fileName, docPropertiesLabelList, docPropertiesLabelValueList);
			
			docDetailsPage.backToFolderOrDocumentPage("");
			
			index++;
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
