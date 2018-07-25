package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_031P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription("1.Field level validation while creating Table smart link object in document library"
				+"<br>2.Verify the user is able to create Table link when any other external URL link is given");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		try {

			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
					scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
					scriptHelper);
			AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
			AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
					scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
					scriptHelper);	
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String siteName = dataTable.getData("Sites", "SiteName");
			String moreSettingsOption1 = dataTable.getData("MyFiles", "BrowseActionName");
			String type = dataTable.getData("Home", "DashletName");
			String title = dataTable.getData("Document_Details", "FilePath");
			String title2 = dataTable.getData("Document_Details", "Version");
			String extURLLink = dataTable.getData("Document_Details",
					"FileName");
			String Type = dataTable.getData("MyFiles", "Version");
			String Name = dataTable.getData("MyFiles", "CreateFolder");
			String siteuserName = dataTable.getData("Sites", "InviteUserName");
			String roleName = dataTable.getData("Sites", "Role");
			String data = dataTable.getData("MyFiles", "CreateFileDetails");

			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitForPageToLoad(driver);	
		    UIHelper.waitForPageToLoad(driver);
	       
			homePageObj.navigateToSitesTab();
			String siteNameValue =  dataTable.getData("Sites", "SiteName");
			sitesPage.createSite(siteNameValue, "Yes");
			//sitesPage.siteFinder(siteNameValue);
			UIHelper.waitFor(driver);
			//To invite coordinator-->ALF02
			sitesPage.performInviteUserToSite(siteNameValue);
			sitesPage.enterIntoDocumentLibrary();
			UIHelper.waitForPageToLoad(driver);	
		    UIHelper.waitForPageToLoad(driver);
		
			
			if (data.contains(";")) {
				String splittedFileDetails[] = data.split(";");
				for (String nxtval : splittedFileDetails) {
					String splittedsmartDetails[] = nxtval.split(",");

					String smartfile1 = splittedsmartDetails[0];
					String smartfile2 = splittedsmartDetails[1];
					String smartName = splittedsmartDetails[2];
					String smarttype = splittedsmartDetails[3];
					
					sitesPage.enterIntoDocumentLibrary();
					
					myFiles.createcontenttype(type);
					
					avsmart.clickSmartLinkType(smartName, smarttype);
					
					avsmart.entersmarttypedata(smarttype, smartfile1, extURLLink, "Image",
							"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
			
					avsmart.submitbutton(smarttype, smartfile1);
					myFilesTestObj.verifyUploadedFile(smartfile1, "");
					
					sitesPage.enterIntoDocumentLibrary();
					myFiles.createcontenttype(type);
					avsmart.clickSmartLinkType(smartName, smarttype);
					avsmart.entersmarttypedata(smarttype, smartfile2, extURLLink, "Image",
							"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
			
					avsmart.submitbutton(smarttype, smartfile2);
					myFilesTestObj.verifyUploadedFile(smartfile2, "");
				}
			}
		}
					/*sitesPage.enterIntoDocumentLibrary();
					
					sitesPage.clickOnMoreSetting(smartfile2);
					docLibPg.commonMethodForClickOnMoreSettingsOption(smartfile2,
								moreSettingsOption1);
						
					if(properties.getProperty("ApplicationUrl").contains("apppe")){
							
						sitesPage.clickOnAddUserGroupButton("QAPERFORM55");
						sitesPage.clickOnUserRole("Coordinator", "QAPERFORM51");
							
						sitesPage.clickOnAddUserGroupButton("QAPERFORM52");
						sitesPage.clickOnUserRole("Contributor", "QAPERFORM52");
							
						sitesPage.clickOnAddUserGroupButton("QAPERFORM53");
						sitesPage.clickOnUserRole("Reviewer", "QAPERFORM53");
							
					}else if(properties.getProperty("ApplicationUrl").contains("pearsoncms")){	
						sitesPage.clickOnAddUserGroupButton("SSO2");
						sitesPage.clickOnUserRole("Manager", "SSO2");
						
						sitesPage.clickOnAddUserGroupButton("SSO3");
						sitesPage.clickOnUserRole("Coordinator", "SSO3");
						
						sitesPage.clickOnAddUserGroupButton("SSO1");
						sitesPage.clickOnUserRole("Collaborator", "SSO1");
						
					}else{
						sitesPage.clickOnAddUserGroupButton("ALF02");
						sitesPage.clickOnUserRole("Manager", "ALF02");
						
						sitesPage.clickOnAddUserGroupButton("ALF03");
						sitesPage.clickOnUserRole("Coordinator", "ALF03");
						
						sitesPage.clickOnAddUserGroupButton("ALF01");
						sitesPage.clickOnUserRole("Collaborator", "ALF01");
					}
					
						docDetailsPage.removeInheritPermissions();
				}
				
			}
		
			
			->avsmart.clickSmartLinkType(Type, Name);
			avsmart.entersmarttypedata(Name, title, extURLLink, "Image",
					"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
	
			avsmart.submitbutton(Name, title);
			myFilesTestObj.verifyUploadedFile(title, "");
			
			sitesPage.enterIntoDocumentLibrary();
			myFiles.createcontenttype(type);
			avsmart.clickSmartLinkType(Type, Name);
			avsmart.entersmarttypedata(Name, title2, extURLLink, "Image",
					"Caption", "credit", "", System.getProperty("user.dir")+ filePath + fileName);
	
			avsmart.submitbutton(Name, title2);
			myFilesTestObj.verifyUploadedFile(title2, "");
			
			sitesPage.enterIntoDocumentLibrary();
			
			sitesPage.clickOnMoreSetting(title2);
			docLibPg.commonMethodForClickOnMoreSettingsOption(title2,
						moreSettingsOption1);
				
			if(properties.getProperty("ApplicationUrl").contains("apppe")){
					
				sitesPage.clickOnAddUserGroupButton("QAPERFORM55");
				sitesPage.clickOnUserRole("Coordinator", "QAPERFORM51");
					
				sitesPage.clickOnAddUserGroupButton("QAPERFORM52");
				sitesPage.clickOnUserRole("Contributor", "QAPERFORM52");
					
				sitesPage.clickOnAddUserGroupButton("QAPERFORM53");
				sitesPage.clickOnUserRole("Reviewer", "QAPERFORM53");
					
			}else{	
				sitesPage.clickOnAddUserGroupButton("SSO2");
				sitesPage.clickOnUserRole("Manager", "SSO2");
				
				sitesPage.clickOnAddUserGroupButton("SSO3");
				sitesPage.clickOnUserRole("Coordinator", "SSO3");
				
				sitesPage.clickOnAddUserGroupButton("SSO1");
				sitesPage.clickOnUserRole("Collaborator", "SSO1");
				
			}
			
				docDetailsPage.removeInheritPermissions();
				
			*/
				
		 catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("AUT_AG_031 status:",
					"<br>AUT_AG_031 Testcase is Failed", Status.FAIL);

		}

	}

	@Override
	public void tearDown() {

	}
}
