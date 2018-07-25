package testscripts.misc2;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_192P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC192P2()
	{
		testParameters.setCurrentTestDescription("Able to follow documents (template), when a document is followed, then the follower will receive an indication in their dash board when a new version of it is uploaded to the system<br> - Part2:Follow the same file with another site member and perform 'Upload New Version'");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		docLibPgObj.selectDocLibViewType("Detailed View");
		docLibPgObj.clickOnFollowOption(fileName);
		
		docLibPgTest.verifyUnfollowLinkForFile(fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettOptValues = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingOption1="";
		String moreSettingOption2="";
		if(moreSettOptValues.contains(","))
		{
			String splittedOptions[] = moreSettOptValues.split(",");
			if(splittedOptions!=null && splittedOptions.length==2)
			{
				moreSettingOption1 = splittedOptions[0];
				moreSettingOption2 = splittedOptions[1];
			}
		}
	
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		String finalFileName;
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0];
			finalFileName = part1 + "." + splitVal[1];
		} else {
			finalFileName = fileName;
		}
		
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
				finalFileName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingOption1);
		
		new FileUtil().waitUptoFileDownloadComplete(fileDownloadPath, finalFileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingOption2);
		
		docLibPg.uploadNewVersionFileInDocLibPage(finalFileName, fileDownloadPath, "Test Comments");
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}