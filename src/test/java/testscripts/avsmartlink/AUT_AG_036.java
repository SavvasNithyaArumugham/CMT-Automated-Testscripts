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
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
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

public class AUT_AG_036 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Verify the user not getting red banner issue on creation of Image external link inside the 1st subfolder which is opened via left pane document view"
				+"<br>2.Verify the user not getting red banner issue on creation of Video external link inside the 2nd level subfolder which is opened via left pane document view"
				+"<br>3.Verify the user not getting red banner issue on creation of Audio external link inside the 3rd level subfolder which is opened via left pane document view"
				+"<br>4.Verify the user not getting red banner issue on creation of External external link inside the 4th level subfolder which is opened via left pane document view"
				+"<br>5.Verify the user not getting red banner issue on creation of PDF external link inside the 5th level subfolder which is opened via left pane document view"
				+"<br>6.Verify the user not getting red banner issue on creation of 3rd party interactive link inside the 6th level subfolder which is opened via left pane document view"
				+"<br>7.Verify the user not getting red banner issue on creation of Metrodigi link inside the 7th level subfolder which is opened via left pane document view"
				+"<br>8.Verify the user not getting red banner issue on creation of MD Pop Up link inside the 8th level subfolder which is opened via left pane document view"
				+"<br>9.Verify the user not getting red banner issue on creation of Table External link inside the 9th level subfolder which is opened via left pane document view");
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
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		


		String fileName = dataTable.getData("MyFiles", "FileName");
		String type = dataTable.getData("Home", "DashletName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String Name = dataTable.getData("MyFiles", "CreateFolder");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details",
				"FileName");
		String datatype = dataTable.getData("MyFiles", "AccessToken");
		String dataname = dataTable.getData("MyFiles", "CreateChildFolder");
		String siteName = dataTable.getData("Sites", "SiteName");
		String moreSettingsOption1 = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		String folderTitle = dataTable.getData("MyFiles", "BrowseActionName");
		String folderDescription = dataTable.getData("MyFiles", "Sort Options");
		String Type = dataTable.getData("MyFiles", "Version");
		
		

		ArrayList<String> smarttype = new ArrayList<String>();
		smarttype = myFiles.getFileNames(datatype);
		System.out.println(smarttype);
		
		ArrayList<String> smartname = new ArrayList<String>();
		smartname = myFiles.getFileNames(dataname);
		System.out.println(smartname);
		
		
		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
		
		for (int i =1;i<10;i++) {
			
			myFiles.commonMethodForCreateFolder("AutoTest"+i, folderTitle, folderDescription);
			
			taskPage.filterfolderview("AutoTest"+i);
			
			myFiles.createcontenttype(type);
			
			avsmart.clickSmartLinkType(smartname.get(i-1),  smarttype.get(i-1));
			
			avsmart.entersmarttypedata( smarttype.get(i-1),  smarttype.get(i-1)+i, extURLLink,
					"Images", "", "", "", "");
			
			avsmart.submitbutton(smarttype.get(i-1),  smarttype.get(i-1)+i);

			myFilesTestObj.verifyUploadedFile(smarttype.get(i-1)+i, "");
			
			int j=1;
			int k=i;
			
			 do{  
				 taskPage.filterfolderview("AutoTest"+j);
				 j++;
				 k--;
			    }while(k>=1); 
			
			
		}

	}
	//}

	@Override
	public void tearDown() {

	}
}
