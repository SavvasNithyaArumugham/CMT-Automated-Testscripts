package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;

import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;
/**********************************************************************************************
 * AlfrescoAVSmartLinkPageTest.java - This program contains verify method for smartLinks
 * 
 * @author Cognizant
 ***********************************************************************************************/
public class AlfrescoAVSmartLinkPageTest extends ReusableLibrary {
   
	AlfrescoAVSmartLinkPage  smartLinkPage=new AlfrescoAVSmartLinkPage(scriptHelper);
	CSVUtil csvUtil=new CSVUtil();
	
	public String inputfieldXpath=".//form[@id='CRAFT']//*[contains(text(),'LABELNAME')]//ancestor::div/input";
    public String labelCheckXpath="//form[@id='CRAFT']//*[contains(text(),'LABELNAME')]";
	 
	public AlfrescoAVSmartLinkPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void verifyFieldPresent(String name,String label){
		
		String finalFieldXpath=inputfieldXpath.replace("CRAFT", name).replace("LABELNAME",label);
		if(UIHelper.checkForAnElementbyXpath(driver, finalFieldXpath) && labelPresent(name,label)){
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, finalFieldXpath);
			report.updateTestLog(" Verify Presence of  field", " Field present  " +label+ "." ,
					Status.PASS);
		}else {
			report.updateTestLog(" Verify Presence of height field", " Field not present under "+label+"." ,
					Status.FAIL);
		}
	}
	
	// Check for the presence of label with label name and name of the link(id in xpath)
	public boolean labelPresent(String name,String label){
		boolean flag=false;
		String finalLabelXpath=labelCheckXpath.replace("CRAFT", name).replace("LABELNAME",label);
		if(UIHelper.checkForAnElementbyXpath(driver, finalLabelXpath)){
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, finalLabelXpath);
			flag=true;
		}
		return flag;
	}
	
	// Verify the field is empty - name of the link(id in xpath) and field name
	public void isEmptyfield(String name, String field){
		String value="";
		String finalFieldXpath=inputfieldXpath.replace("CRAFT", name).replace("LABELNAME",field);
		try{
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldXpath);
			//value=UIHelper.getTextFromWebElement(driver, finalFieldXpath);
			value=UIHelper.getAttributeFromWebElement(driver, finalFieldXpath, "value");
			if(value.isEmpty()){
				report.updateTestLog(" Verify "+field+" field is empty", " "+field+" field is empty ",
						Status.PASS);
			}else{
				report.updateTestLog(" Verify "+field+" field is empty", " "+field+" field is not empty ",
						Status.FAIL);
			}
		
		}catch(Exception e){
			report.updateTestLog(" Verify "+field+" field is empty", " Empty check failed ",
					Status.FAIL);
		}
		
	}
	
	// Retrieve the value for the field - name of the link(id in xpath) and field name
		public String getValueForField(String name, String field){
			String value="";
			String finalFieldXpath=inputfieldXpath.replace("CRAFT", name).replace("LABELNAME",field);
			try{
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldXpath);
				value=UIHelper.getAttributeFromWebElement(driver, finalFieldXpath, "value");
							
			}catch(Exception e){
				report.updateTestLog("Retrieve field value ", " Value retrieval failed ",
						Status.FAIL);
			}
			return value;
		}
		
		// Verify the populated value in a field is same as expected
		public void verifyFieldValue(String name, String field,String expValue){
			String actualValue="";
			actualValue=getValueForField(name,field);
				if(actualValue.equals(expValue)){
					report.updateTestLog(" Verify field Value", " Values for "+field+" is same as expected ",
							Status.PASS);
				}else{
					report.updateTestLog(" Verify field Value", " Values for "+field+" is not same as expected ",
							Status.FAIL);
				}
		}
		
		// Enter value into particular field
		public void enterValueIntoParticularInputField(String name, String field, String fieldValue) {
			String finalFieldXpath=inputfieldXpath.replace("CRAFT", name).replace("LABELNAME",field);
			try{
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalFieldXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, finalFieldXpath, fieldValue);
				report.updateTestLog("Enter value into "+field+" field", " Value for "+field+" field entered successfully ",
						Status.PASS);			
			}catch(Exception e){
				report.updateTestLog("Enter value into "+field+" field ", " Value for "+field+" field entery failed ",
						Status.FAIL);
			}
		}
		
		public void verifyFieldPresenfInCSVHeader(String csvFile,String fieldName) {
			List<String> data=null;
			try {
				data=csvUtil.readLinesOfDataFromCSVFile(csvFile);
				if(data.get(0).contains(fieldName)) {
					report.updateTestLog("Verify field present in header of CSV", " CSV has the "+fieldName+" field in the header",
							Status.PASS);
				}else {
					report.updateTestLog("Verify field present in header of CSV", " CSV doesn't have the "+fieldName+" field in the header",
							Status.FAIL);
				}
			}catch(Exception e) {
				report.updateTestLog("Verify field present in header of CSV", "CSV header check failed",
						Status.FAIL);
			}
		}
		
	
		// Verify the MultiSmartLink Status from the downloaded CSV file
		public void verifyMultiSmartLinkStatus(String[] allLink, ArrayList<String> csvFileRowDataList,String[] expStatus,int title,int status) {
			
			try {
			if((csvFileRowDataList.size()==allLink.length) && (csvFileRowDataList.size()==expStatus.length)) {
				for(String csvRow : csvFileRowDataList) {
					boolean flag=false;
					csvRow=csvRow.replace("\"", "");
					String[] splitedData =  csvRow.split(",");
					for(int i=0;i<allLink.length;i++) {
						if(splitedData[title].equalsIgnoreCase(allLink[i]) && splitedData[status].contains(expStatus[i])){
						report.updateTestLog("SmartLink: " + splitedData[title] +" with expected status "+expStatus[i]+"",  "<b>Status: </b> " + splitedData[status], Status.PASS);
						flag=true;
						break;
						}
						
					}
					if(!flag) {
						report.updateTestLog("SmartLink: " + splitedData[title], "<b>Status: </b>" + splitedData[status]+" which is not expected", Status.FAIL);
					}
				}
			}else {
				report.updateTestLog("SmartLink status check ", "Data sent in the parameter has issue" , Status.FAIL);
			}
			
		}catch(Exception e) {
			report.updateTestLog("SmartLink status check ", "Status check failed" , Status.FAIL);
		}
	}
}
