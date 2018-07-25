package testscripts.eps;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
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
public class AUT_AG_049 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_049()
	{
		testParameters.setCurrentTestDescription(
				
				"Verify User cannot publish the content if the parent was already published."
			   +
			    "Verify there is a publish button under action field for non published Content"
				+
			    "Verify the Institution field shows all the Configured Institution."
			    +"Verify there is no publish button under action field for Content which have no change."
				+"Verfiy there is field populated for the published institution and remaining are null for non published Content."
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
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		try{
		//Value Declaration
				String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
				String filePath = dataTable.getData("MyFiles", "FilePath");	
				String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
				String folderDetails = dataTable.getData("MyFiles", "CreateFolder");	
				String folderName = dataTable.getData("MyFiles", "Version");
				String PublishFieldValues[] = dataTable.getData("MyFiles", "CreateFileDetails").split(";");
				
				// Script Started: 
				
				functionalLibrary.loginAsValidUser(signOnPage);	
				UIHelper.waitFor(driver);
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				myFiles.createFolder(folderDetails);
				myFiles.openCreatedFolder(folderName);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);	
				UIHelper.waitFor(driver);
				sitesPage.enterIntoDocumentLibrary();
				collectionPg.clickOnMoreSetting(folderName);				
				epsPg.publishbutton(folderName,Option[0]);
				
				for(int i=1;i<=6;i++){
					int j=0;
					String a ="//table[@class='spaces']/tbody/tr[1]/td["+i+"]/*";
				
					String a1= UIHelper.getTextFromWebElement(driver, a);
					System.out.println(a1);
					String values[] = PublishFieldValues[0].split(",");
					for(String expected:values){
						if(a1.equalsIgnoreCase(expected)){
							j++;							
						}else{}
					}
					
					if(j==1){
						report.updateTestLog("EPS Publishing Status", "Column value "+a1+" is appeared in the column"
								,Status.PASS);
					}else{
						report.updateTestLog("EPS Publishing Status", "Column value "+a1+" is not appeared in the column"
								,Status.FAIL);
					}
					
				}
				
				
				for(int i=1;i<=6;i++){
					int j=0;
					String a ="//table[@class='spaces']/tbody/tr[2]/td["+i+"]/*";
					String b ="//table[@class='spaces']/tbody/tr[1]/td["+i+"]/*";
					String a1= UIHelper.getTextFromWebElement(driver, a);
					String b1= UIHelper.getTextFromWebElement(driver, b);
					//System.out.println(a1);
					String values[] = PublishFieldValues[1].split(",");
					for(String expected:values){
						if(a1.equalsIgnoreCase(expected)){
							j++;							
						}else{
						//	System.out.println("is"+expected+"is"+a1+"ths");
						}
					}
					
					if(j==1){
						report.updateTestLog("EPS Publishing Status", b1+" Column value "+a1+" is appeared in the column"
								,Status.PASS);
					}else{
						report.updateTestLog("EPS Publishing Status", b1+" Column value "+a1+" is not appeared in the column"
								,Status.FAIL);				
					}		
				}
				
				epsPg.Publish("School Content", folderName,"one");
				
				//Publish the content
				myFiles.openCreatedFolder(folderName);
				//collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);
				collectionPg.clickOnMoreSetting(fileName[0]);
				epsPg.publishbutton(fileName[0],Option[0]);	
				
				
				
				String actionbutton = epsPg.publishbutton.replace("CRAFT", "Alfresco CDN");
				UIHelper.javascriptClick(driver, UIHelper.findAnElementbyXpath(driver, actionbutton));	
				UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.filealreadypublished);
				boolean flag=UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.filealreadypublished));
				String text=UIHelper.getTextFromWebElement(driver, epsPg.filealreadypublished);
				//System.out.println(flag);
				if(flag){
					report.updateTestLog("Publish Status", "User cannot publish the content if the parent was"
							+ " already published. and displayed the error message "+text,
							Status.PASS);
				}else{
					report.updateTestLog("Publish Status", "User can publish the content if the parent was"
							+ " already published",
							Status.FAIL);
				}
				
				for(int i=1;i<=6;i++){
					int j=0;
				
					String a ="//table[@class='spaces']/tbody/tr[2]/td["+i+"]/*";
					String b ="//table[@class='spaces']/tbody/tr[1]/td["+i+"]/*";
					String a1= UIHelper.getTextFromWebElement(driver, a);
					String b1= UIHelper.getTextFromWebElement(driver, b);
					//System.out.println(a1);
					String values[] = PublishFieldValues[2].split(",");
					
					for(String expected:values){
						if(!a1.isEmpty()&&!expected.isEmpty()){
						if(a1.contains(expected)){
							//j++;
						}
						}else{
							if(a1.contains(expected)){
								j++;							
							}else{
								
							}	
							}	
						}	
					
					if(j==1){
						report.updateTestLog("EPS Publishing Status", b1+" Column value "+a1+" is appeared in the column"
								,Status.PASS);
					}else{
						report.updateTestLog("EPS Publishing Status", b1+" Column value "+a1+" is not appeared in the column"
								,Status.FAIL);
					}
				}
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibrary();
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
		}catch(Exception e){
			e.printStackTrace();
			
			report.updateTestLog("EPS_49 Status", "EPS 49 Testcases has been failed", Status.FAIL);	
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}