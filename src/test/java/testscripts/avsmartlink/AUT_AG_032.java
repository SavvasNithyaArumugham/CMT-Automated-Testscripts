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
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_032 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_011() {
		testParameters
				.setCurrentTestDescription("1.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'Image External Link' type in Create Smart Link screen.<br>"
						+ "2.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'Video External Link' type in Create Smart Link screen.<br>"
						+ "3.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'Audio External Link' type in Create Smart Link screen.<br>"
						+ "4.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'External Website Link' type in Create Smart Link screen.<br>"
						+ "5.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'PDF Link' type in Create Smart Link screen.<br>"
						+ "6.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for '3rd Party Interactive Link' type in Create Smart Link screen.<br>"
						+ "7.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'Metrodigi Link' type in Create Smart Link screen.<br>"
						+ "8.Verify typo of 'CopyRight or Credit Text' field is changed to 'Copyright or Credit Text' for 'MD Pop up' type in Create Smart Link screen.<br>");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		try {
			
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
					scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
					scriptHelper);

			String siteName = dataTable.getData("Sites", "SiteName");
			String type = dataTable.getData("Home", "DashletName");
			String fileName = dataTable.getData("MyFiles", "FileName");

			functionalLibrary.loginAsValidUser(signOnPage);

			sitesPage.siteFinder(siteName);

			sitesPage.enterIntoDocumentLibrary();

			docLibPg.deleteAllFilesAndFolders();

			myFiles.createcontenttype(type);

			if (fileName.contains(";")) {
				String splittedFileDetailas[] = fileName.split(";");
				for (String fileValues : splittedFileDetailas) {
					String splittedFileValues[] = fileValues.split(",");

					String Type = splittedFileValues[0];
					String Name = splittedFileValues[1];

					avsmart.clickSmartLinkType(Type, Name);

					String finalimgrefURL = avsmart.imgrefxpathcheck.replace(
							"CRAFT", Name);
					

					if (Name.equalsIgnoreCase("imageExternalLink")) {


						
						
						if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
							
							report.updateTestLog(
									"Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is hidden for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.FAIL);
						}

					} else if (Name.equalsIgnoreCase("videoExternalLink")) {
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								finalimgrefURL);
						String getMessage = UIHelper.getTextFromWebElement(
								driver, finalimgrefURL);

						if (getMessage.equals("Image Reference URL")) {
							UIHelper.scrollToAnElement(UIHelper
									.findAnElementbyXpath(driver,
											finalimgrefURL));
							UIHelper.highlightElement(driver, finalimgrefURL);

							report.updateTestLog(
									"Verify the presence of Image Reference URL field in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify the presence of Image Reference URL field in Create smartlink of " +Type,
									"Image Reference URL field  is not present for type"
											+ Type, Status.FAIL);
						}

					} else if (Type.equalsIgnoreCase("audioExternalLink")) {
						
						if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
						

							report.updateTestLog(
									"Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is hidden for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.FAIL);
						}

					} else if (Name.equalsIgnoreCase("externalWebsiteLink")) {
						
						if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
					

							report.updateTestLog(
									"Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is hidden for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.FAIL);
						}

					} else if (Name
							.equalsIgnoreCase("thirdPartyInteractiveLink")) {
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								finalimgrefURL);
						String getMessage = UIHelper.getTextFromWebElement(
								driver, finalimgrefURL);
					
						
						if (getMessage.equals("Image Reference URL")) {
							UIHelper.scrollToAnElement(UIHelper
									.findAnElementbyXpath(driver,
											finalimgrefURL));
							UIHelper.highlightElement(driver, finalimgrefURL);

							report.updateTestLog(
									"Verify the presence of Image Reference URL field in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify the presence of Image Reference URL field in Create smartlink of " +Type,
									"Image Reference URL field  is not present for type"
											+ Type, Status.FAIL);
						}


					} else if (Name.equalsIgnoreCase("metrodigiLink")) {
						
						UIHelper.waitForVisibilityOfEleByXpath(driver,
								finalimgrefURL);
						String getMessage = UIHelper.getTextFromWebElement(
								driver, finalimgrefURL);
						
						if (getMessage.equals("Image Reference URL")) {
							UIHelper.scrollToAnElement(UIHelper
									.findAnElementbyXpath(driver,
											finalimgrefURL));
							UIHelper.highlightElement(driver, finalimgrefURL);

							report.updateTestLog(
									"Verify the presence of Image Reference URL field in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify the presence of Image Reference URL field in Create smartlink of " +Type,
									"Image Reference URL field  is not present for type"
											+ Type, Status.FAIL);
						}


					} else if (Name.equalsIgnoreCase("pdfLink")) {
						
					
						
						if (!UIHelper.checkForAnElementbyXpath(driver, finalimgrefURL)) {
				

							report.updateTestLog(
									"Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is hidden for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify Image Reference URL field  is hidden in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.FAIL);
						}

					}else if (Name.equalsIgnoreCase("mdPopUp")) {

						UIHelper.waitForVisibilityOfEleByXpath(driver,
								finalimgrefURL);
						String getMessage = UIHelper.getTextFromWebElement(
								driver, finalimgrefURL);
						if (getMessage.equals("Image Reference URL")) {
							UIHelper.scrollToAnElement(UIHelper
									.findAnElementbyXpath(driver,
											finalimgrefURL));
							UIHelper.highlightElement(driver, finalimgrefURL);

							report.updateTestLog(
									"Verify the presence of Image Reference URL field in Create smartlink of" +Type,
									"Image Reference URL field  is present for type"
											+ Type, Status.PASS);
						} else {
							report.updateTestLog(
									" Verify the presence of Image Reference URL field in Create smartlink of " +Type,
									"Image Reference URL field  is not present for type"
											+ Type, Status.FAIL);
						}
					}


				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("AUT_AG_032 status:",
					"<br>AUT_AG_032 Testcase is Failed", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {

	}
}
