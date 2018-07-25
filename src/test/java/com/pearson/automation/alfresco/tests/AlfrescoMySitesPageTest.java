package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;

import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.framework.Status;

public class AlfrescoMySitesPageTest extends ReusableLibrary {

	public AlfrescoMySitesPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * @author 412766
	 */
	public void verifyMySitesAvailable() {
		try{
			List<String> siteNameList = new ArrayList<String>();
			if(new AlfrescoMySitesPage(scriptHelper).isMySitesListAvaialable(siteNameList)){
				report.updateTestLog("Verify Sites In My Sites Page",
						"Sites verified successfully"
								+ "<br><b> Verified Sites Name : </b> " + siteNameList.toString(), Status.PASS);
			}else{
				report.updateTestLog("Verify Sites In My Sites Page",
						"Sites not verified"
								+ "<br><b> Verified Sites Name : </b> " + siteNameList.toString(), Status.FAIL);
			}
		}catch(Exception e){
			report.updateTestLog("Verify Sites In My Sites Page",
					"Verify Sites In My Sites Page Failed", Status.FAIL);
		}
	}
}
