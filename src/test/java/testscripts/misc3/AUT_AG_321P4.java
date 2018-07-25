package testscripts.misc3;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_321P4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"Verify a French translation for lifecycle name and state field names via advance search page when pearson custom lifecycle aspect(State) is selected..");
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
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);
			
			AlfrescoSearchPage searchPageobj = new AlfrescoSearchPage(scriptHelper);
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
			
			AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
			String fileName = dataTable.getData("Search", "FullText");
			String changedLifecyclePropValuesForDemote = dataTable.getData("Document_Details",
					"ChangedLifecyclePropValuesForDemote");
			homePageObj.navigateToAdvSearch();
			searchPageobj.clickShowMore();
			searchPageobj.inputAspectAdvSearch();
			searchTestObj.verLfeCyclDrpLstAdvSrch();
			searchPageobj.clickSearch();
			searchTestObj.commonMethodForVerifySearchResults(fileName);

			searchTestObj.clickofresultfile(fileName);

			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDemote, "DemoteLifeCycle");

			String expLifeCycleMetaData;
			String expLifeCycleStateMetaData;
			String metaLifeCycle = dataTable.getData("Document_Details", "Title");
			String metaLifeCycleState = dataTable.getData("Document_Details", "Comments");
			expLifeCycleMetaData = searchPageobj.getMetadata(metaLifeCycle);
			expLifeCycleStateMetaData = searchPageobj.getMetadata(metaLifeCycleState);
			if ((!expLifeCycleMetaData.isEmpty()) && (!expLifeCycleStateMetaData.isEmpty())) {
				report.updateTestLog("Verify LifeCycleName and State in Doc Details are displayed in French",
						"LifeCycleName and State in Doc Details are successfully displayed in French", Status.PASS);
			} else {
				report.updateTestLog("Verify LifeCycleName and State in Doc Details are displayed in French",
						"LifeCycleName and State in Doc Details are failed to displayed in French", Status.FAIL);
			}

	}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}