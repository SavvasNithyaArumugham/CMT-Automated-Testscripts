package testscripts.svgtransforms;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
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
public class AUT_SVG_004 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void SVG_001()
	{
		testParameters.setCurrentTestDescription(
				
				" 1)ALFDEPLOY-4397_Verify svg image having the color code #000000 or "
				+ "#F7931E or #0000FF or #B3B3FF or #D9D9FF or #FF00FF or #FFB3FF or #FFD9FF "
				+ "remains unprocessed once saved into normal folder of document library/My files/Shared files"
				
				+"2)ALFDEPLOY-4397_Verify jpg,png,GIF,tiff,BMP,jpeg,psd image remains unprocessed once saved into "
				+ "normal folder of document library/My files/Shared files"
				
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		
		try{
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "SiteName");				
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
		String folderName = dataTable.getData("MyFiles", "Version");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
				
		
		
		// Script Started: 				
		functionalLibrary.loginAsValidUser(signOnPage);	
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver, docLibPg.myfilesxpath);
		// My files:
		UIHelper.click(driver,  docLibPg.myfilesxpath);
		
		Boolean flag1 =myFiles.isFileOrFolderDisplayed(folderName);
		if(flag1){
			myFiles.deleteCreatedFolder(folderDetails);
			myFiles.createFolder(folderDetails);
		}else{
			myFiles.createFolder(folderDetails);
		}
		for(String NameofFile:fileName){
			
			myFiles.openCreatedFolder(folderName);
			myFiles.uploadFileInMyFilesPage(filePath, NameofFile);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, svgPg.myFilesLableLinkXpath);
		
			UIHelper.waitForVisibilityOfEleByXpath(driver, svgPg.folderxpath);
			Boolean flag =myFiles.isFileOrFolderDisplayed("Processed");
				
			if(flag){
				myFiles.openCreatedFolder("Processed");
				Boolean flag3 = docLibPg.isFileIsAvailable(NameofFile);
				if(flag3){
					report.updateTestLog("<b>Check File Processed or not in MY FILES:", "File: "+NameofFile+" uploaded remain unprocessed and they are present in processed folder in MY FILES", Status.FAIL);							
					
				}else{
					report.updateTestLog("<b>Check File Processed or not in MY FILES:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder in MY FILES", Status.PASS);							
					
				}
					}else{
				report.updateTestLog("<b>Check File Processed or not in MY FILES:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder in MY FILES", Status.PASS);							
			}
			
		}
		
		// Shared Files:
		UIHelper.click(driver, docLibPg.sharedfilesXpath);
		Boolean flag2 =myFiles.isFileOrFolderDisplayed(folderName);
		if(flag2){
			myFiles.deleteCreatedFolder(folderDetails);
			myFiles.createFolder(folderDetails);
		}else{
			myFiles.createFolder(folderDetails);
		}
		for(String NameofFile:fileName){
			
			myFiles.openCreatedFolder(folderName);
			myFiles.uploadFileInMyFilesPage(filePath, NameofFile);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, svgPg.sharedfilesXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, svgPg.folderxpath);
			Boolean flag =myFiles.isFileOrFolderDisplayed("Processed");	
			System.out.println(flag);
			if(flag){
				myFiles.openCreatedFolder("Processed");
				Boolean flag3 = docLibPg.isFileIsAvailable(NameofFile);
				if(flag3){
					report.updateTestLog("<b>Check File Processed or not in SHARED FILES:", "File: "+NameofFile+" uploaded remain unprocessed and they are present in processed folder in SHARED FILES", Status.FAIL);							
					
				}else{
					report.updateTestLog("<b>Check File Processed or not in SHARED FILES:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder in SHARED FILES", Status.PASS);							
					
				}
					}else{
				report.updateTestLog("<b>Check File Processed or not in SHARED FILES:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder in SHARED FILES", Status.PASS);							
			}
			
		
		}	
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		//Normal Folder
		myFiles.createFolder(folderDetails);
			
		for(String NameofFile:fileName){

			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderName);
			
			collectionPg.uploadFileInCollectionSite(filePath, NameofFile);
			sitesPage.enterIntoDocumentLibrary();
			Boolean flag =myFiles.isFileOrFolderDisplayed("Processed");
			if(flag){
				myFiles.openCreatedFolder("Processed");
				Boolean flag3 = docLibPg.isFileIsAvailable(NameofFile);
				if(flag3){
				report.updateTestLog("<b>Check File Processed or not:", "File: "+NameofFile+" uploaded remain unprocessed and they are present in processed folder", Status.FAIL);							
			}else{
				report.updateTestLog("<b>Check File Processed or not:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder", Status.PASS);							
			}
			}else{
				report.updateTestLog("<b>Check File Processed or not:", "File: "+NameofFile+" uploaded remain unprocessed and they are not present in processed folder", Status.PASS);							
			}
			
		}
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			report.updateTestLog(" AUT_SVG_004_Test case Status", "AUT_SVG_004 Testcase is failed", Status.FAIL);							
			
			
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}