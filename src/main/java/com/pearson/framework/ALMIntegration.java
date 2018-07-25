package com.pearson.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;

import com.jacob.com.LibraryLoader;
import com.pearson.automation.utils.ExcelUtil;

public class ALMIntegration  {

	ExcelUtil excel = new ExcelUtil();
	String excelMapFile = "src/test/resources/HPALM/ALMTestcasesMap.xls";
	String tempStatusFile = "src/test/resources/HPALM/TestcaseStatus.txt";

	public String loadProperties(String suite) {
		Properties prop = new Properties();
		InputStream input = null;
		String property = null;
		try {
			input = new FileInputStream("HP ALM.properties");
			prop.load(input);
			property = prop.getProperty(suite);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return property;
	}
	
	public String loadGlobalProperties(String suite) {
		Properties prop = new Properties();
		InputStream input = null;
		String property = null;
		try {
			input = new FileInputStream("Global Settings.properties");
			prop.load(input);
			property = prop.getProperty(suite);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return property;
	}

	public void pushStatusToALM() throws FileNotFoundException {
		
		try {
			String HPALMStatusUpdateVal=loadProperties("HPALMStatusUpdate");
			if(HPALMStatusUpdateVal.equalsIgnoreCase("True"))
			{
				String tempJacobDllPath = loadProperties("JacobDLLPathForALM");
				String jacobDLLPath;
				if(tempJacobDllPath!=null && !tempJacobDllPath.isEmpty() && tempJacobDllPath.contains("PROJECT_DIR"))
				{
					
					String currentProjDir = System.getProperty("user.dir");
					jacobDLLPath = tempJacobDllPath.replace("PROJECT_DIR", currentProjDir);
				}
				else if(tempJacobDllPath!=null && !tempJacobDllPath.isEmpty() && !tempJacobDllPath.contains("PROJECT_DIR"))
				{
					jacobDLLPath = tempJacobDllPath;
				}
				else
				{
					jacobDLLPath = "jacob-1.18-x86.dll";
				}
					
				String HPALMUrl=loadProperties("HP_ALM_ApplicationURL");
				ALMServiceWrapper wrapper = new ALMServiceWrapper(HPALMUrl);
				System.setProperty("jacob.dll.path",jacobDLLPath);
				LibraryLoader.loadJacobLibrary();
				String hpALMAppUserName = loadProperties("HPALMUserName");
				String hpALMAppPassword = loadProperties("HPALMPassword");
				String hpALMDomainName = loadProperties("HPALMDomainName");
				String hpALMProjectName = loadProperties("HPALMProjectName");
				
				try {
					wrapper.connect(hpALMAppUserName, hpALMAppPassword, hpALMDomainName, hpALMProjectName);
					System.out.println("Connected");
				} catch (ALMServiceException e1) {
					e1.printStackTrace();
				}
				
				String appURL = loadGlobalProperties("ApplicationUrl");
				String finalAppURL = "";
				if (appURL.contains("https://")) {
					finalAppURL = appURL.replace("https://", "");
				} else if (appURL.contains("http://")) {
					finalAppURL = appURL.replace("http://", "");
				}
				String splittedAppURL[] = finalAppURL.split(Pattern.quote("."));
				String environment=splittedAppURL[0].toUpperCase();
				String folderPath=loadProperties("ALMFolderPath");
				//folderPath=folderPath+environment;
				folderPath=folderPath;
				
				// To read text document
				Scanner scan=new Scanner(new File(tempStatusFile));
				while(scan.hasNextLine())
				{
					String	testcase = scan.nextLine().trim();
					String[] testcaseDetails = testcase.split(",");
					String suite = environment+"_"+testcaseDetails[0];
					String testcaseId = testcaseDetails[1];
					String testcaseStatus = testcaseDetails[2];
	
					String tempSuite = loadProperties(suite);
					String suiteNameDetails[] = tempSuite.split(";");
					String ALMSuiteName = suiteNameDetails[0];
					int ALMTestSetID = Integer.parseInt(suiteNameDetails[1]);
	
					// To get corresponding testcasename for testcaseid
					String testcaseName = excel.getDataFromExcelSheet(excelMapFile,
							testcaseDetails[0], testcaseId);
					
					if (testcaseStatus.equalsIgnoreCase("Passed")) {
						try {
							
							wrapper.updateResult(folderPath, ALMSuiteName,
									ALMTestSetID, testcaseName, StatusAs.PASSED);
						
						} catch (ALMServiceException e) {
							e.printStackTrace();
						}
					} else {
						try {
							wrapper.updateResult(folderPath, ALMSuiteName,
									ALMTestSetID, testcaseName,
									StatusAs.FAILED);
						} catch (ALMServiceException e) {
							e.printStackTrace();
						}
					}
				}
				
				if(wrapper != null){
					wrapper = null;
				}
				//tearDownALM(wrapper);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void pushStatusToALM(String suite, String testcaseId, String testcaseStatus) throws FileNotFoundException {
		
		try {
			String HPALMStatusUpdateVal=loadProperties("HPALMStatusUpdate");
			if(HPALMStatusUpdateVal.equalsIgnoreCase("True"))
			{
				String tempJacobDllPath = loadProperties("JacobDLLPathForALM");
				String jacobDLLPath;
				if(tempJacobDllPath!=null && !tempJacobDllPath.isEmpty() && tempJacobDllPath.contains("PROJECT_DIR"))
				{
					
					String currentProjDir = System.getProperty("user.dir");
					jacobDLLPath = tempJacobDllPath.replace("PROJECT_DIR", currentProjDir);
				}
				else if(tempJacobDllPath!=null && !tempJacobDllPath.isEmpty() && !tempJacobDllPath.contains("PROJECT_DIR"))
				{
					jacobDLLPath = tempJacobDllPath;
				}
				else
				{
					jacobDLLPath = "jacob-1.18-x86.dll";
				}
					
				String HPALMUrl=loadProperties("HP_ALM_ApplicationURL");
				ALMServiceWrapper wrapper = new ALMServiceWrapper(HPALMUrl);
				System.setProperty("jacob.dll.path",jacobDLLPath);
				LibraryLoader.loadJacobLibrary();
				String hpALMAppUserName = loadProperties("HPALMUserName");
				String hpALMAppPassword = loadProperties("HPALMPassword");
				String hpALMDomainName = loadProperties("HPALMDomainName");
				String hpALMProjectName = loadProperties("HPALMProjectName");
				
				try {
					wrapper.connect(hpALMAppUserName, hpALMAppPassword, hpALMDomainName, hpALMProjectName);
					System.out.println("Connected");
				} catch (ALMServiceException e1) {
					e1.printStackTrace();
				}
				
				String appURL = loadGlobalProperties("ApplicationUrl");
				String finalAppURL = "";
				if (appURL.contains("https://")) {
					finalAppURL = appURL.replace("https://", "");
				} else if (appURL.contains("http://")) {
					finalAppURL = appURL.replace("http://", "");
				}
				String splittedAppURL[] = finalAppURL.split(Pattern.quote("."));
				String environment=splittedAppURL[0].toUpperCase();
				String folderPath=loadProperties("ALMFolderPath");
				
				//folderPath=folderPath+environment;
				
				// To read text document
				//Scanner scan=new Scanner(new File(tempStatusFile));
				//while(scan.hasNextLine())
				//{
					//String	testcase = scan.nextLine().trim();
					//String[] testcaseDetails = testcase.split(",");
					//String suite = environment+"_"+testcaseDetails[0];
					//String testcaseId = testcaseDetails[1];
					//String testcaseStatus = testcaseDetails[2];
					String amlSuite = environment+"_"+suite;
	
					String tempSuite = loadProperties(amlSuite);
					String suiteNameDetails[] = tempSuite.split(";");
					String ALMSuiteName = suiteNameDetails[0];
					int ALMTestSetID = Integer.parseInt(suiteNameDetails[1]);
	
					// To get corresponding testcasename for testcaseid
					String testcaseName = excel.getDataFromExcelSheet(excelMapFile,
							suite, testcaseId);
					
					if (testcaseStatus.equalsIgnoreCase("Passed")) {
						try {
							
							wrapper.updateResult(folderPath, ALMSuiteName,
									ALMTestSetID, testcaseName, StatusAs.PASSED);
						
						} catch (ALMServiceException e) {
							e.printStackTrace();
						}
					} else {
						try {
							wrapper.updateResult(folderPath, ALMSuiteName,
									ALMTestSetID, testcaseName,
									StatusAs.FAILED);
						} catch (ALMServiceException e) {
							e.printStackTrace();
						}
					}
				//}
				if(wrapper != null){
					wrapper = null;
				}
					
				//tearDownALM(wrapper);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void tearDownALM(ALMServiceWrapper wrapper) {

		try{
			if (wrapper.getAlmObj().isConnected()) {
				System.out.println("Disconnecting");
				wrapper.getAlmObj().disconnect();
			}
			/*if (wrapper.getAlmObj().isLoggedIn()) {
				System.out.println("Is LoggedIn?");
				wrapper.getAlmObj().logout();
			}
			wrapper.getAlmObj().releaseConnection();*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
