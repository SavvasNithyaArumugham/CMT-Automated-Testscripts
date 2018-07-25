package testscripts.eps;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Cognizant
 */
public class AUT_AG_034 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_034()
	{
		testParameters.setCurrentTestDescription
		(			
		
		"Verify the Publishing option is available only for Non Published content"
		+ " for the EPS configured site."
		+"Verify the Publishing Options UI on clicking Publishing option for the non "
		+ "Published content in any EPS configured site."
		+"Verify the if you publish option is displayed as a dropdown in Publishing Options screen."
		+"Verify the to options is displayed as a dropdown in Publishing Options screen "
		+ "with Configured institutions."
		+"Verify the then the Delivery URL will be options is displayed with auto generated possible"
		+ " delivery URL based on the selection of 'If you publish and 'to' fields."
		+"Verify the if you publish dropdown is displayed with one option when we click on publishing "
		+ "options for the asset located on the Document library."
		+"Verify the if you publish dropdown is displayed with two options when we click on publishing"
		+ " options for the asset located on the Document library inside a folder."
		+"Verify the if you publish dropdown is displayed with all possible options when we click on publishing"
		+ " options for the asset located on the Document library inside subfolders."
		
				);	
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
		try{
			
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);	
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
		String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(";");
		String Name[] = dataTable.getData("MyFiles", "Version").split(";");
		String Instution = dataTable.getData("MyFiles", "RelationshipName");
		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//	sitesPage.siteFinder(site);
		sitesPage.enterIntoDocumentLibrary();
/*		docLibPg.deleteAllFilesAndFolders();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		// Creation of folders:		
		myFiles.createFolder(folderDetails[0]);
		myFiles.openCreatedFolder(Name[0]);
		myFiles.createFolder(folderDetails[1]);
		myFiles.openCreatedFolder(Name[1]);
		myFiles.createFolder(folderDetails[2]);
		myFiles.openCreatedFolder(Name[2]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);	
		
		// ValidationPoint:
		sitesPage.enterIntoDocumentLibrary();
		ArrayList<String> li = new ArrayList<String>();
		for(String SingleName:Name)
		{
		collectionPg.clickOnMoreSetting(SingleName);	
		
		boolean flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(SingleName,Option[3]);
		if(flag){
			li.add(SingleName);
			report.updateTestLog("Publishing Options:", "Publishing Option is presented "
					+ "for "+"<b> "+fileName[1], Status.PASS);
			collectionPg.commonMethodForClickOnMoreSettingsOption(SingleName,Option[3]);
			epsPg.PublishingoptionsValidation(SingleName,Instution,li);
			UIHelper.click(driver, epsPg.sitenotcongiCloseXpath);
			myFiles.openCreatedFolder(SingleName);
			
		}
		}
		
/*		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		sitesPage.enterIntoDocumentLibrary();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		
		
		
		}catch(Exception e){
			report.updateTestLog("EPS_34 Status", "EPS 34 Testcases has been failed", Status.FAIL);	
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}