package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.openqa.selenium.WebElement;

import com.jayway.restassured.response.Response;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.utils.CC_API_Helper;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

/**********************************************************************************************
 * 
 * @author Lokesh
 ***********************************************************************************************/
public class AlfrescoMarklogicPageTest extends ReusableLibrary {
	
	
	private String aspectcontainerXpath = ".//*[contains(@id,'view-node-aspects')]";

	


	public AlfrescoMarklogicPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @author 317085
	 */
	public Response verifyLoginAuth() {
		try{
			
			Response response = CC_API_Helper.generate_PI_AuthToken();
			//Response Answer = CC_API_Helper.getManifestationReturnresponse(auth_token, "s");
			
			if(response.getStatusCode() == (HttpStatus.SC_OK )){
			
			report.updateTestLog("Authentic Login Token", "Login authentication Successful"
					+"<br><b>Expected Response Code : </b> "+HttpStatus.SC_OK
					+"<br><b> Response Code : </b> "+response.getStatusCode(), Status.DONE);
	
		}else {
			report.updateTestLog("Authentic Login Token", "Login authentication failed"
					+"<br><b>Expected Response Code : </b> "+HttpStatus.SC_OK
					+"<br><b> Actual Response Code : </b> "+response.getStatusCode(), Status.FAIL);
		}
			return response;
			
		}catch(Exception e){
			report.updateTestLog("Authentic Login Token", "Login authentication failed"
			, Status.FAIL);
		return null;
		}
	}
	
	
	/**
	 * @author 317085	
	 */
	public void verifygetManifestationResponse(Response Answer) {
		try{
			
			//Response Answer = CC_API_Helper.getManifestationReturnresponse(auth_token, "s");
			
			if(Answer.getStatusCode() == (HttpStatus.SC_OK)){
			
			report.updateTestLog("Get Manifestation Response", "Get Manifestation Response Successful"
					+"<br><b>Expected Response Code : </b> "+HttpStatus.SC_OK
					+"<br><b> Response Code : </b> "+Answer.getStatusCode(), Status.DONE);		
		}else {
			report.updateTestLog("Get Manifestation Response", "Get Manifestation Response failed"
					+"<br><b>Expected Response Code : </b> "+HttpStatus.SC_OK
					+"<br><b> Actual Response Code : </b> "+Answer.getStatusCode(), Status.FAIL);
		}
			
			
		}catch(Exception e){
			report.updateTestLog("Get Manifestation Response", "Get Manifestation Response failed"
			, Status.FAIL);
		}
	}
	
	/**
	 * @author 317085	
	 */
	public void verifyresponseValue(Response Answer, String attribute, String Values) {
		try{
			
			//Response Answer = CC_API_Helper.getManifestationReturnresponse(auth_token, "s");
			
			if(Answer.getStatusCode() == (HttpStatus.SC_OK)){
				
				String RespValue = CC_API_Helper.retriveValuefromJson("$.."+attribute, Answer.getBody().asString());
				System.out.println(RespValue);
				
				
			if(RespValue.contains(Values)){
				report.updateTestLog("Verify "+attribute+" from Response", attribute+" is retrieved as expected"
						+"<br><b>Expected Response Code : </b> "+Values
						+"<br><b> Response Code : </b> "+RespValue, Status.PASS);
			}else {
			report.updateTestLog("Verify "+attribute+" from Response", attribute+" is not retrieved as expected"
					, Status.FAIL);
		}
			}else{
				report.updateTestLog("Verify "+attribute+" from Response", attribute+" is not retrieved as expected"
						, Status.FAIL);
		}
		}catch(Exception e){
			report.updateTestLog("Get Manifestation Response", "Get Manifestation Response failed"
			, Status.FAIL);
		}
	}
	
	/**
	 * @author 317085	
	 */
	public Map<String, Object> verifyallresponseValue(Response response) {
		Map<String, Object> map = null;
		String responseSTR = null;
		try{
			
			responseSTR = response.getBody().asString();
		
			if(response.getStatusCode() == (HttpStatus.SC_OK)){
				
				map = CC_API_Helper.jsonString2Map(responseSTR);
				
			if(map.get("id").toString().contains("manifestation")){
				report.updateTestLog("Verify Manifestation from Response", " Response is retrieved as expected"
						+"<br><b> Response Value : </b> "+map, Status.PASS);
			}else {
				report.updateTestLog("Verify Work from Response", " Response is retrieved as expected"
						+"<br><b> Response Value : </b> "+map, Status.PASS);
		}
			}else{
				report.updateTestLog("Verify Response", "Response is not retrieved as expected"
						+"<br><b> Response Received : </b> "+responseSTR
						, Status.FAIL);
		}
		}catch(Exception e){
			report.updateTestLog("Verify Response", "Response is not retrieved as expected"
					+"<br><b> Response Received : </b> "+responseSTR
			, Status.FAIL);
		}
		
		return map;
	}
	
	/**
	 * @author 317085	
	 */
	public void verifyResourceaspect(String aspect) {

		try {

			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, aspectcontainerXpath);

			if (UIHelper.findAnElementbyXpath(driver, aspectcontainerXpath)
					.getText().contains(aspect)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, aspectcontainerXpath));
				UIHelper.highlightElement(driver, aspectcontainerXpath);
				report.updateTestLog("Verify Aspect fro Folder", aspect
						+ "Aspect should be added to the folder", Status.PASS);

			}
		} catch (Exception e) {
			report.updateTestLog("Verify Aspect fro Folder", aspect
					+ "Aspect is not added to the folder", Status.FAIL);
		}

	}
	
	
	/**
	 * @author 317085	
	 */
	public Map<String, Object> verifyallresponsenewValue(Response response) {
		Map<String, Object> map = null;
		String responseSTR = null;
		try {

			responseSTR = response.getBody().asString();

			map = CC_API_Helper.jsonString2Map(responseSTR);

			if (map.get("status").toString().equalsIgnoreCase("ok")) {
				report.updateTestLog("Verify Response",
						" Response is retrieved as expected"
								+ "<br><b> Response Value : </b> " + map,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Work from Response",
						" Response is not retrieved as expected"
								+ "<br><b> Response Value : </b> " + map,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog(
					"Verify Response",
					"Response is not retrieved as expected"
							+ "<br><b> Response Received : </b> " + responseSTR,
					Status.FAIL);
		}

		return map;
	}
	
	
	/**
	 * @author 317085	
	 */
	public Map<String, Object> verifynegativeresponsenewValue(Response response) {
		Map<String, Object> map = null;
		String responseSTR = null;
		try {

			responseSTR = response.getBody().asString();

			map = CC_API_Helper.jsonString2Map(responseSTR);

			if (map.get("status").toString().equalsIgnoreCase("error")) {
				report.updateTestLog("Verify Response",
						" Response is retrieved as expected"
								+ "<br><b> Response Value : </b> " + map,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Work from Response",
						" Response is not retrieved as expected"
								+ "<br><b> Response Value : </b> " + map,
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog(
					"Verify Response",
					"Response is not retrieved as expected"
							+ "<br><b> Response Received : </b> " + responseSTR,
					Status.FAIL);
		}

		return map;
	}
}
