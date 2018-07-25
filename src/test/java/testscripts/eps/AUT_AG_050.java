package testscripts.eps;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_050 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_050()
	{
		testParameters.setCurrentTestDescription(
			"Verify User can be able to check the changes from review changes option."
			+"Verify the version shows the current version of published content if it is a minor version"
			+"Verify the version shows the current version of published content if it is a minor version"
			+"Verify there is a publish button under action field for Content which have some change."
			+"Verify there is a publication history available at the bottom of the publish dialog box."
			+"Verify the Pubhish dialog box contains name as folder for the content when it was published from parent."
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
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish("School Content", fileName[0],"one");
				
				docLibPg.openAFile(fileName[0]);
				AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
				docDetailsPage.editFileProperties();
				docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderName);
				epsPg.orangepublish(fileName[0], "one");
			
				collectionPg.clickOnMoreSetting(fileName[0]);
				
				sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], "Review Changes");
				epsPg.reviewchanges("modified",fileName[0]);
				collectionPg.clickOnMoreSetting(fileName[0]);
				epsPg.publishbutton(fileName[0],Option[0]);
				

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
				
				// Publication History Validation:
				
				for(int i=1;i<=8;i++){
					int j=0;
					String history = "//*[@id='prompt']//*[@class='bd']/div//tr[1]/td["+i+"]/b";
					String a1= UIHelper.getTextFromWebElement(driver, history);
					
					String values[] = PublishFieldValues[3].split(",");					
					for(String expected:values){
						//System.out.println(expected);
						//System.out.println(a1);
						String date = DateUtil.getCurrentDateAndTime();
						//System.out.println(date);
						date=date.substring(0, 10);
						date= date.replace("/", "-");
						//System.out.println(date);
						if(a1.contains(expected)||a1.startsWith(date)){
							j++;
							break;
						}
						
					}
					//System.out.println(j);
					if(j==1){
						report.updateTestLog("EPS Publishing History", "<b>"+a1+" value is appeared in the publishing history"
								,Status.PASS);
					}else{
						report.updateTestLog("EPS Publishing History", "<b>"+a1+" value is not appeared in the publishing history" ,Status.FAIL);
					}
					
				}
			
				
				
				epsPg.Publish("School Content", fileName[0],"one");
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibrary();
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_50 Status", "EPS 50 Testcases has been failed", Status.FAIL);	
			}
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}