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
public class AUT_SVG_002 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void SVG_002()
	{
		testParameters.setCurrentTestDescription(
				
				" 1)ALFDEPLOY-4397_Verify svg image having no color code remains unprocessed "
				+ "in svg-transform repo rule applied folder"
				
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
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] colors = dataTable.getData("MyFiles", "CreateFileDetails").split(",");
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
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
				
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		myFiles.openFile(fileName);
		
		
		for(String nocolorcode:colors){
			//System.out.println(nocolorcode);
		String colorcode= svgPg.nocolorcode.replace("CRAFT", nocolorcode);
		//System.out.println(colorcode);
		driver.switchTo().frame(fileName);	
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, colorcode))){
				//System.out.println("presented: "+colorcode);
				UIHelper.highlightElement(driver, colorcode);
			report.updateTestLog("<br>Color Code"+nocolorcode,"Color code: #"+nocolorcode+" is presented in the "
					+ " file:"+fileName , Status.PASS);
			}else{
				report.updateTestLog("<br>Color Code"+nocolorcode,"Color code: #"+nocolorcode+" is not presented in "
						+ "the image in the file:"+fileName , Status.FAIL);
			}
		driver.switchTo().defaultContent();	
		}
		
		driver.switchTo().defaultContent();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder("Processed");
		myFiles.openFile(fileName);
		
		
		for(String nocolorcode:colors){
		//	System.out.println(nocolorcode);
		String colorcode1= svgPg.nocolorcode.replace("CRAFT", nocolorcode);		
		//System.out.println(colorcode1);
		driver.switchTo().frame(fileName);	
			if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, colorcode1))){
				//System.out.println("Presnted: "+colorcode1);
				UIHelper.highlightElement(driver, colorcode1);			
				report.updateTestLog("Process of color code","Color code: #"+nocolorcode+" is not processed and remains same"
								+ "in the file:"+fileName , Status.PASS);
			}else{
				//System.out.println("No presence"+ colorcode1);
				report.updateTestLog("Process of color code","Color code: #"+nocolorcode+" is processed "
						+ "in the file:"+fileName , Status.FAIL);
			}
		driver.switchTo().defaultContent();
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