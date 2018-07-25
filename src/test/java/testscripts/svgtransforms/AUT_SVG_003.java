package testscripts.svgtransforms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRulePage;
import com.pearson.automation.alfresco.pages.AlfrescoSVGTransformsPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_SVG_003 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void SVG_001()
	{
		testParameters.setCurrentTestDescription(
				
				" 1)ALFDEPLOY-4397_Verify jpg,png,GIF,tiff,BMP,PSD,jpeg images remains "
				+ "unprocessed in the svg-transform repo rule applied folder"
				
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
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoSVGTransformsPage svgPg = new AlfrescoSVGTransformsPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoRulePage rule = new AlfrescoRulePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		
		try{
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "SiteName");				
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
		String folderName = dataTable.getData("MyFiles", "Version");
		String[] moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		int count=0;		
		
		
		// Script Started: 				
		functionalLibrary.loginAsValidUser(signOnPage);	
		UIHelper.waitFor(driver);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
					
		collectionPg.clickOnMoreSetting(folderName);
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSettingsOption[0]);
		
		rule.clickCreateRuleORNewRule(sourceSiteName);
			
		for(String NameofFile:fileName){

			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderName);
					
			collectionPg.uploadFileInCollectionSite(filePath, NameofFile);
			sitesPage.enterIntoDocumentLibrary();
			//myFiles.openCreatedFolder("Processed");
			Boolean flag =myFiles.isFileOrFolderDisplayed("Processed");
			//Boolean flag = docLibPg.isFileIsAvailable(NameofFile);		
			if(flag){
				report.updateTestLog("<b>Check File Processed or not:", "File: "+NameofFile+" uploaded remain unprocessed and they are present in processed folder", Status.FAIL);							
			}else{
				report.updateTestLog("<b>Check File Processed or not:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder", Status.PASS);							
			}
			
		}
							
		}catch(Exception e){
			e.printStackTrace();
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}