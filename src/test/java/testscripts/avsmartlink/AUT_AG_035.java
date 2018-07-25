package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_035 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify there is no association between image and smart link for smart link object(Image External link) created without image preview"
				+"<br>2.Verify there is no association between image and smart link for smart link object(Video External link) created without image preview"
				+"<br>3.Verify there is no association between image and smart link for smart link object(Audio External link) created without image preview"
				+"<br>4.Verify there is no association between image and smart link for smart link object(External website link) created without image preview"
				+"<br>5.Verify there is no association between image and smart link for smart link object(3rd party interactive link) created without image preview"
				+"<br>6.Verify there is no association between image and smart link for smart link object(metrodigi link) created without image preview"
				+"<br>7.Verify there is no association between image and smart link for smart link object(table link) created without image preview"
				+"<br>8.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for Image External smart link object which was created without image preview"
				+"<br>9.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for Video External smart link object which was created without image preview"
				+"<br>10.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for Audio External smart link object which was created without image preview"
				+"<br>11.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for External website smart link object which was created without image preview"
				+"<br>12.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for 3rd party interactive smart link object which was created without image preview"
				+"<br>13.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for metrodigi smart link object which was created without image preview"
				+"<br>14.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Upload option for table smart link object which was created without image preview"
				+"<br>15.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for Image External smart link object which was created without image preview"
				+"<br>16.Verify the existing association between image and smart link when smart link object is created with image preview uploaded via upload option"
				+"<br>17.Verify the existing association between image and smart link when smart link object is created with image preview uploaded via select option"
				+"<br>18.Verify the removal of association between image and smart link via edit smart link-clear button"
				+"<br>19.Verify the removal of association between image and smart link via relationship widget"
				+"<br>20.Verify the existing association between image and smart link can be replaced with new one using upload button"
				+"<br>21.Verify the existing association between image and smart link can be replaced with new one using select button"
				+"<br>22.Verify the existing smartlink association relationship in image preview screen when the relationship is made manually"
				+"<br>23.Verify smartlink object is not created when external Url Link is left blank or invalid url provided in the Table link type and check the error message Invalid URL is displayed"
				+"<br>24.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for Video External smart link object which was created without image preview"
				+"<br>25.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for Audio External smart link object which was created without image preview"
				+"<br>26.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for External Website smart link object which was created without image preview"
				+"<br>27.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for 3rd party interactive smart link object which was created without image preview"
				+"<br>28.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for Metrodigi smart link object which was created without image preview"
				+"<br>29.Verify the creation of new association made between image and smart link via Edit smart link-Image Preview-Select option for Table smart link object which was created without image preview"
				+"<br>30.Verify there is an error when user provide Invalid URL format for smart link - Table"
				+"<br>31.Verify there is an error when user provide Invalid URL format for smart link - External Web site"
				+"<br>32.Verify there is an error when user provide Invalid URL format for smart link - Metrodigi form"
				+"<br>33.Verify there is an error when user provide Invalid URL format for smart link -MD popup form"
				+"<br>34.Verify there is an error when user provide Invalid URL format for smart link -Pdf"
				+"<br>35.Verify there is an error when user provide Invalid URL format for smart link -audio"
				+"<br>36.Verify there is an error when user provide Invalid URL format for smart link -3rd party interactive"
				+"<br>37.Verify there is an error when user provide Invalid URL format for smart link -image"
				+"<br>38.Verify there is an error when user provide Invalid URL format for smart link -video"
				+"<br>39.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under Image External link"
				+"<br>40.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under Image external Link"
				+"<br>41.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under Video External link"
				+"<br>42.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under Video external Link"
				+"<br>43.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under Audio External link"
				+"<br>44.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under Audio external Link"
				+"<br>45.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under External Website link"
				+"<br>46.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under External Website Link"
				+"<br>47.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under PDF link"
				+"<br>48.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under PDF Link"
				+"<br>49.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under 3rd Party Interactive Link"
				+"<br>50.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under 3rd Party Interactive Link"
				+"<br>51.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under Metrodigi Link"
				+"<br>52.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under Metrodigi Link"
				+"<br>53.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under MD Pop up Link"
				+"<br>54.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under MD Pop up Link"
				+"<br>55.To verify ,while creating Smart Link user is able to provide 'https' link in External url link under table Link"
				+"<br>56.To verify ,while creating Smart Link user is getting error message when provides link which is not 'https' in External url link under table Link");
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage=new AlfrescoDocumentDetailsPage(scriptHelper);


		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileName1 = dataTable.getData("MyFiles", "Version");
		String type = dataTable.getData("Home", "DashletName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String extURLhttp = dataTable.getData("Document_Details", "Version");
		String extURLerror = dataTable.getData("Document_Details", "Comments");
		String data = dataTable.getData("MyFiles", "CreateFolder");
		String siteName = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption1 = dataTable.getData("MyFiles", "MoreSettingsOption");
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileName1);

		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String nxtval : splittedFileDetails) {
				String splittedsmartDetails[] = nxtval.split(",");

				String smarttitle = splittedsmartDetails[0];
				String smartname = splittedsmartDetails[1];
				String smarttype = splittedsmartDetails[2];
			
				
				myFiles.createcontenttype(type);
				
				
				report.updateTestLog("AV Smart Link ",smartname , Status.DONE);
				
				avsmart.clickSmartLinkType(smartname, smarttype);

		
				avsmart.entersmarttypedata(smarttype, smarttitle, extURLhttp,
						"Images", "", "", "", "");
			
				avsmart.error("Insecure",smarttype);
				
				avsmart.entersmarttypedata(smarttype, smarttitle, extURLerror,
						"Images", "", "", "", "");
		
				avsmart.error("Invalid",smarttype);
				
				avsmart.entersmarttypedata(smarttype, smarttitle, extURLLink,
						"Images", "", "", "", "");
				
				avsmart.submitbutton(smarttype, smarttitle);

				myFilesTestObj.verifyUploadedFile(smarttitle, "");
				
				sitesPage.enterIntoDocumentLibrary();
				
				if(sitesPage.documentAvailable(smarttitle)){
					report.updateTestLog("Verify the creation of new content folder ", "New folder created with smart link title name successfully", Status.PASS);
				}else{
					report.updateTestLog("Verify the creation of new content folder ", "New folder created with smart link title name not created as expected. ", Status.FAIL);
				}
				sitesPage.enterIntoDocumentLibrary();
				if(smarttitle.equalsIgnoreCase("MD Pop Up1") || smarttitle.equalsIgnoreCase("PDF Link1")){
					
				}else{
				sitesPage.documentdetails(smarttitle);
				
				if(!sitesPage.documentAvailable(fileName)){
					report.updateTestLog("Verify there is no association between image and smart link for smart link object created without image preview ",
							"Works as expected for " +smartname, Status.PASS);
				}else{
					report.updateTestLog("Verify there is no association between image and smart link for smart link object created without image preview ",
							"image association is available which is not expected." +smartname, Status.PASS);
				}
				
				sitesPage.clickOnMoreSetting(smarttitle);
				docLibPg.commonMethodForClickOnMoreSettingsOption(smarttitle,
							moreSettingsOption1);
				
				avsmart.entersmarttypedata(smarttype, smarttitle, extURLLink,
						"Images", "", "", "", System.getProperty("user.dir")
						+ filePath + fileName);
				
				
				avsmart.submitbutton(smarttype, smarttitle);
			
				sitesPage.clickOnEditProperties(smarttitle);
				
				try{
					UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, avsmart.jsonString));	
					report.updateTestLog("JSON String ",
							"JSON String is appeared in the Edit Properties", Status.FAIL);
				}catch(Exception e){
					report.updateTestLog("JSON String ",
							"JSON String is not appeared in the Edit Properties", Status.PASS);
				}
				UIHelper.click(driver, avsmart.cancelonEditproperties);
				
				sitesPage.documentdetails(fileName);
				
				docDetailsPageTest.verifyAddedRelationshipData(smarttitle);
				
				docDetailsPage.deleteRelationshipData(smarttitle);
				
				sitesPage.enterIntoDocumentLibrary();
				
				sitesPage.documentdetails(smarttitle);
				
				sitesPage.clickOnMoreSetting(smarttitle);
				docLibPg.commonMethodForClickOnMoreSettingsOption(smarttitle,
							moreSettingsOption1);
				
				avsmart.selectimagefromSelect(smarttype,siteName,fileName1);
			//	UIHelper.waitForLong(driver);
				avsmart.submitbutton(smarttype, smarttitle);
			//	UIHelper.waitForLong(driver);
				sitesPage.enterIntoDocumentLibraryWithoutReport();
				sitesPage.documentdetails(fileName1);
				
				docDetailsPageTest.verifyAddedRelationshipData(smarttitle);
				docDetailsPage.deleteRelationshipData(smarttitle);
				
				}
				
				sitesPage.enterIntoDocumentLibrary();
				
			}
		
		}
	}
	

	@Override
	public void tearDown() {

	}
}
