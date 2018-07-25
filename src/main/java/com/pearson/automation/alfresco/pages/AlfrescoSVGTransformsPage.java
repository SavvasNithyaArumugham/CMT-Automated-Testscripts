package com.pearson.automation.alfresco.pages;

import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AlfrescoSVGTransformsPage extends ReusableLibrary{

	
	public String colorcode =".//*[@stroke='#CRAFT']";
	public String nocolorcode = ".//*[@fill='#CRAFT']";
	public String myFilesLableLinkXpath = ".//*[contains(@id,'yui-gen')]//a[contains(.,'Documents')] | .//*[contains(@id,'yui-gen')]//a[contains(.,'Files')]";
	public String sharedfilesXpath = ".//*[contains(@id,'yui-gen')]//a[contains(.,'Documents')] | .//*[contains(@id,'yui-gen')]//a[contains(.,'Shared Files')]";
	public String folderxpath = "//*[@class='documents yui-dt']//*[text()='~SVGTransforms']";
	public AlfrescoSVGTransformsPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	
	/**
	 * @author 412766
	 * @return boolean
	 */
	
		
	
		
}
