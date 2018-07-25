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
public class AUT_SVG_001 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void SVG_001()
	{
		testParameters.setCurrentTestDescription(
				
				" 1)ALFDEPLOY-4397_Verify svg image having the color code #000000 is "
				+ "modified to color code #191919 once processed into processed folder"
				+"2)ALFDEPLOY-4397_Verify svg image having the color code #F7931E is "
				+ "modified to color code #FA9600 once processed into processed folder"
				+"3)ALFDEPLOY-4397_Verify svg image having the color code #0000FF is "
				+ "modified to color code #0064C8 once processed into processed folder"
				+"4)ALFDEPLOY-4397_Verify svg image having the color code #B3B3FF is"
				+ " modified to color code #99C1E9 once processed into processed folder"
				+"5)ALFDEPLOY-4397_Verify svg image having the color code #D9D9FF is "
				+ "modified to color code #CCE0F4 once processed into processed folder"
				+"6)ALFDEPLOY-4397_Verify svg image having the color code #FF00FF is "
				+ "modified to color code #DC0064 once processed into processed folder"
				+"7)ALFDEPLOY-4397_Verify svg image having the color code #FFB3FF is "
				+ "modified to color code #F199C1 once processed into processed folder"
				+"8)ALFDEPLOY-4397_Verify svg image having the color code #FFD9FF is "
				+ "modified to color code #F8CCE0 once processed into processed folder"
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
			String[] Compentents = NameofFile.split(",");
				
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(folderName);
					
			collectionPg.uploadFileInCollectionSite(filePath, Compentents[0]);
			myFiles.openFile(Compentents[0]);
			//UIHelper.waitFor(driver);
			String colorcode= svgPg.colorcode.replace("CRAFT", Compentents[1]);
			driver.switchTo().frame(Compentents[0]);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, colorcode))){
					UIHelper.highlightElement(driver, colorcode);
				List<WebElement> li = driver.findElements(By.xpath(colorcode));
				count = li.size();
				report.updateTestLog("<br>Color Code"+Compentents[1],"Color code: #"+Compentents[1]+" is presented in the "
						+ "image in "+count+" place in the file:"+Compentents[0] , Status.PASS);
				}else{
					report.updateTestLog("<br>Color Code"+Compentents[1],"Color code: #"+Compentents[1]+" is not presented in "
							+ "the image in the file:"+Compentents[0] , Status.FAIL);
				}
			driver.switchTo().defaultContent();
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder("Processed");
			myFiles.openFile(Compentents[0]);
			//UIHelper.waitFor(driver);
			String colorcode1= svgPg.colorcode.replace("CRAFT", Compentents[2]);
			driver.switchTo().frame(Compentents[0]);
			
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, colorcode1))){
					UIHelper.highlightElement(driver, colorcode1);
					List<WebElement> li = driver.findElements(By.xpath(colorcode1));
					int count1 = li.size();
					report.updateTestLog("Color Code is processed","Color code: #"+Compentents[2]+" is processed"
							+ " from color code: #"+Compentents[1]+" and presented in the image in "+count+" places "
									+ "in the file:"+Compentents[0] , Status.PASS);
					

						if(count1==count){
							report.updateTestLog("Color Code count","In all the"+count+" place, the"
									+ " Color code: #"+Compentents[1]+" is processed to color code: #"+Compentents[2]+" in "
											+ "the file:"+Compentents[0] , Status.PASS);

						}else{
							report.updateTestLog("Color Code count","In all the"+count+" place, the "
									+ "Color code: #"+Compentents[1]+" is not processed to color code: #"+Compentents[2]+" in "
											+ "the file:"+Compentents[0] , Status.FAIL);
							
						}
					driver.switchTo().defaultContent();
					}else{
						report.updateTestLog("<br>Color Code"+Compentents[1],"Color code: #"+Compentents[1]+" is not processed in "
								+ "the image in the file:"+Compentents[0] , Status.FAIL);

					}
				
			count =0;
			//UIHelper.waitFor(driver);
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