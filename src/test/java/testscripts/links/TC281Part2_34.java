package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoWebdavPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC281Part2_34 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC281Part2()
	{
		testParameters.setCurrentTestDescription("Verify that the user is able to download link file via Webdav."
				+ "<br>Part2: Download linked file from Webdav");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("WebdavUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("WebdavUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
				
//		AlfrescoWebdavPage webdavPage=new AlfrescoWebdavPage(scriptHelper);
//		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
//		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
//
//		
//		String fileName = dataTable.getData("MyFiles", "FileName");
//		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
//		
//		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
//		String siteName = dataTable.getData("Sites", "SiteName");
//		
//		ArrayList<String> folderNamesList = new ArrayList<String>();
//
//		folderNamesList = myFiles.getFolderNames(folderDetails);
//		
//		functionalLibrary.loginForWebdav();
//
//		webdavPage.navigateToSite(siteName);
//		
//		webdavPage.navigateToDocumentLibrary();
//		
//		webdavPage.navigateToFolder(folderNamesList.get(0));
//		webdavPage.findFileOrFolder(fileName);
//		
//		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
//				fileName);
//		webdavPage.downloadFile(fileName);
//		
//		
//		docDetailsTestPage.verifyDownloadedFile(false, null);		
		report.updateTestLog("web dev related TC", "", Status.FAIL);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}