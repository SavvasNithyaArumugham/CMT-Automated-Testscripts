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
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC285Part2_35 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC285Part2()
	{
		testParameters.setCurrentTestDescription("Verify that the user is able to View link file via Webdav."
				+ "<br>Part2: View linked file in Webdav");
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
//		ArrayList<String> folderNamesList = new ArrayList<String>();
//		functionalLibrary.loginForWebdav();
//				
//		AlfrescoWebdavPage webdavPage=new AlfrescoWebdavPage(scriptHelper);
//		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
//		
//		String fileName = dataTable.getData("MyFiles", "FileName");
//		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
//		folderNamesList = myFiles.getFolderNames(folderDetails);
//		
//		webdavPage.navigateToSite();
//		
//		webdavPage.navigateToDocumentLibrary();
//		
//		webdavPage.navigateToFolder(folderNamesList.get(0));
//		webdavPage.findFileOrFolder(fileName);
		report.updateTestLog("web dev related TC", "", Status.FAIL);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}