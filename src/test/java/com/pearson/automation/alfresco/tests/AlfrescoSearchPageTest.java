package com.pearson.automation.alfresco.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Status;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

/**********************************************************************************************
 * AlfrescoMyFilesPage.java - This program contains methods for creating 1.
 * Files 2. Folders
 * 
 * @author Duvvuru Naveen
 ***********************************************************************************************/
public class AlfrescoSearchPageTest extends ReusableLibrary {

	private AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
	private ArrayList<String> searchResultsList = new ArrayList<String>();
	private boolean isDisplayedSearchResult;
	private String MsgXpath = ".//*[@class='alfresco-notifications-AlfNotification__message']";
	private String TagXpath = ".//*[@id='template_x002e_document-tags_x002e_document-details_x0023_default-body']/div/span";
	private String noResultXpath = ".//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']";
	private String tempNameXpath = "//*[@class='nameAndTitleCell']//a/span[text()='CRAFT']";
	private int indexResult;
	private String FullTextXpath = ".//*[@id='default-keywords-text']";
	private String detailViewXpath = ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']//*[@widgetid='FCTSRCH_SEARCH_ADVICE_NO_RESULTS']";
	private String galleryViewXpath = ".//*[@id='FCTSRCH_SEARCH_RESULTS_LIST']//*[@widgetid='FCTSRCH_GALLERY_VIEW']";

	/*** Site Search ***/
	private String siteTitleXpath = ".//*[@id='HEADER_TITLE']";

	/*** Doc Preview ***/
	private String previewXpath = ".//*[@id='PdfJs-viewer-pageContainer-1']/div[2]";

	/*** File Lock ***/
	private String isLockXpath = "//*[@class='filename']//a[text()='CRAFT']//ancestor::td//*[@class='info-banner']";

	/*** Aspect list ***/
	ArrayList<String> aspectDPList = new ArrayList<String>();
	ArrayList<String> aspectList = new ArrayList<String>();

	private String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/ConfMessages.txt";

	// Saved Search
	AlfrescoSearchPage searchPg = new AlfrescoSearchPage(scriptHelper);
	private String rightGadgetXpath = searchPg.rightGadgetXpath;
	private String searchQueryTxtXpath = searchPg.searchQueryTxtXpath;
	private String savedSearchTypesXpath = searchPg.savedSearchTypesXpath;
	private String selectOptionXpath = searchPg.selectOptionXpath;
	private String saveSearchNameXpath = searchPg.saveSrcNameXpath;
	private String saveSearchDescXpath = searchPg.saveSrcDescXpath;
	private String saveSearchVisiblitycXpath = searchPg.saveSearchVisibTypeXpath;
	private String saveSearchBtnXpath = searchPg.saveSearchBtnXpath;
	private String saveSrcprivateXpath = searchPg.saveSrcprivateXpath;
	public String saveSrclabel = ".//*[@class='alfresco-forms-Form save-search']//form//div[@class='title-row']//*[contains(text(),'CRAFT')]";

	private String saveSrcNameXpath = searchPg.saveSearchNameXpath;
	private String saveSrcDescXpath = searchPg.saveSearchDescXpath;
	private String saveSrcVisiblitycXpath = searchPg.saveSearchVisiblitycXpath;

	private String selectLinkXPath = ".//div[@id='SELECTED_LIST_ITEMS']//span[contains(@class,'arrow')]";
	// span[contains(@id,'alfresco_documentlibrary_AlfSelectDocumentListItems')][text()='Select']";
	private String slctListValsXPath = ".//tbody[@class='dijitReset']/tr[contains(@id,'alfresco_menus_AlfMenuItem')]/td[2]";

	private String Count;

	ArrayList<String> menuBarItems = new ArrayList<String>();
	private String lfeCyclDrpDownXpath1 = ".//*[@id='aspect-properties-dropdown-0']/option[text()='Lifecycle Name']";
	private String lfeCyclDrpDownXpath2 = ".//*[@id='aspect-properties-dropdown-0']/option[text()='State']";
	private String selectBtnlocationXpath = "//*[@id='FCTSRCH_RESULTS_COUNT_LABEL']//parent::div[contains(@class,'LeftAndRight')]//div[@id='SELECTED_ITEMS_MENU']";
	private String menuBarListXpath = "//div[@id='FCTSRCH_RESULTS_MENU_BAR']//div[@id='FCTSRCH_SEARCH_LIST_MENU_BAR']//span";

	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoSearchPageTest(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Verify file in search results Page
	public void verifyUploadedFileInSearchResults() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");
			commonMethodForVerifySearchResults(fileName);
		} catch (Exception e) {
		}
	}

	public void commonMethodForVerifySearchResults(String fileName) {
		try {
			searchResultsList = appSearchPg.getSearchResultsTitle();
			String actualResult = "";
			isDisplayedSearchResult = false;
			for (String actualSearchResult : searchResultsList) {
				if (searchResultsList.size() > 0 && actualSearchResult.equalsIgnoreCase(fileName)
						|| actualSearchResult.contains(fileName)) {

					isDisplayedSearchResult = true;
					actualResult = actualSearchResult;
					break;
				} else {
					actualResult = "File Not Found";
					isDisplayedSearchResult = false;
				}
			}

			if (isDisplayedSearchResult) {
				report.updateTestLog("Verify File/Folder in Search Results",
						"File/Folder:" + fileName + " displayed successfully in search results."
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify File/Folder in Search Results",
						"File/Folder:" + fileName + " failed to display in search results."
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify File/Folder in Search Results",
					"File/Folder:" + fileName + " failed to display in search results.", Status.FAIL);
			searchResultsList.clear();
		} finally {
			searchResultsList.clear();
		}
	}

	// Verify folder in search results Page
	public void verifyFolderInSearchResults() {
		try {
			String folderName = dataTable.getData("Search", "FullText");
			commonMethodForVerifySearchResults(folderName);
		} catch (Exception e) {
		}
	}

	public void commonMethodForVerifyFolderInSearchResults(String folderName) {
		try {
			searchResultsList = appSearchPg.getSearchResultsTitle();
			String actualResult = "";
			for (String actualSearchResult : searchResultsList) {
				if (actualSearchResult.contains(folderName)) {
					isDisplayedSearchResult = true;
					actualResult = actualSearchResult;
					break;
				} else {
					actualResult = "Folder Not Found";
					isDisplayedSearchResult = false;
				}
			}

			if (isDisplayedSearchResult) {
				report.updateTestLog("Verify folder in Search Results",
						"Folder:" + folderName + " displayed successfully in search results."
								+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify folder in Search Results",
						"Folder:" + folderName + " failed to display in search results."
								+ "<br /><b>Expected Result:</b> " + folderName + ", <br /><b>Actual Result:</b> "
								+ actualResult,
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Verify UserName Search result.
	public void verifyUserNameSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			/*
			 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
			 */
			String searchQuery = dataTable.getData("Search", "Query");
			if (appSearchPg.searchNames.size() > 0) {
				String searchResult = dataTable.getData("Search", "Result");
				String searchResult1 = dataTable.getData("Search", "Actions");

				for (String modifier : appSearchPg.modifier) {
					if (modifier.contains(searchResult) || modifier.contains(searchQuery)
							|| modifier.contains(searchResult1)) {
						isDisplayedSearchResult = true;
						break;
					} else {
						isDisplayedSearchResult = false;
						break;
					}
				}
				if (isDisplayedSearchResult) {
					report.updateTestLog("Verify User Name Search in Search Results",
							"User Name search successfully." + "<br /><b> Search Query : </b>" + searchQuery
									+ "<br /><b> Search Result from file : </b>" + appSearchPg.modifier.get(0),
							Status.PASS);
				} else {
					report.updateTestLog("Verify User Name Search in Search Results",
							"User Name search failed. Result mismatch", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify User Name Search in Search Results",
						"User Name search failed. No results found", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify User Name Search in Search Results", "User Name search fail. No results found",
					Status.FAIL);
		}
	}

	// Verify Boolean Search result.
	public void verifyBooleanSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);

			if (appSearchPg.searchNames.size() > 0) {
				report.updateTestLog("Verify NOT Boolean search ", "NOT Boolean successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify NOT Boolean search ", "NOT Boolean not successfully", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify NOT Boolean search ", "NOT Boolean not successfully", Status.FAIL);
		}
	}

	// Verify file Name Search result.
	public void verifyFileNameSearchResults() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			/*
			 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
			 * UIHelper.waitFor(driver); UIHelper.waitFor(driver);
			 */

			appSearchPg.getSearchListValues();
			String Query = dataTable.getData("Search", "Query");
			if (appSearchPg.searchNames.size() > 0) {
				for (String fileName : appSearchPg.searchNames) {
					if (fileName.contains(Query)) {
						isDisplayedSearchResult = true;
						break;
					} else {
						isDisplayedSearchResult = false;
						continue;
					}
				}
				if (isDisplayedSearchResult) {
					report.updateTestLog("Verify File Name Search in Search Results",
							"File Name search successfully in search results. " + "<br /><b> Search file NAme : </b>"
									+ Query,
							Status.PASS);
				} else {
					report.updateTestLog("Verify File Name Search in Search Results",
							"File Name search fail in search results", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify File Name Search in Search Results",
						"File Name search fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify File Name Search in Search Results", "File Name search fail in search results",
					Status.FAIL);
		}
	}

	// Verify No Search result.
	public void verifyNoSearchResults() {
		try {

			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, noResultXpath));
			String Query = dataTable.getData("Search", "Result");
			System.out.println(Query);
			System.out.println(UIHelper.findAnElementbyXpath(driver, noResultXpath).getText());
			if (UIHelper.findAnElementbyXpath(driver, noResultXpath).getText().equalsIgnoreCase(Query)) {
				report.updateTestLog("Verify No Results in Search Results",
						"No results verified successfully in search results", Status.PASS);
			} else {
				report.updateTestLog("Verify No Results in Search Results",
						"No results verified fail in search results", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify No Results in Search Results", "No results verified fail in search results",
					Status.FAIL);
		}
	}

	// Verify Delete in Search result.
	public void verifyDeleteinSearchResults() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, MsgXpath);
			String Query = dataTable.getData("Search", "Result");
			String Message = UIHelper.findAnElementbyXpath(driver, MsgXpath).getText();
			if (Message.contains(Query)) {
				report.updateTestLog("Verify Delete Document in Search Results",
						"Delete Document verified successfully in search results", Status.PASS);
			} else {
				report.updateTestLog("Verify Delete Document in Search Results",
						"Delete Document verified fail in search results", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Delete Document in Search Results",
					"Delete Document verified fail in search results", Status.FAIL);
		}
	}

	// Verify Move /Copy in Search result.
	public void verifyMoveorCopyinSearchResults() {
		String Query = dataTable.getData("Search", "Result");
		String action = dataTable.getData("Search", "Actions");
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, MsgXpath);

			String Message = UIHelper.findAnElementbyXpath(driver, MsgXpath).getText();
			if (Message.contains(Query)) {
				report.updateTestLog("Verify" + action + "Document in Search Results",
						action + " Document verified successfully in search results", Status.PASS);
			} else {
				report.updateTestLog("Verify" + action + "Document in Search Results",
						action + " Document verified failed in search results", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify" + action + "Document in Search Results",
					action + " Document verified failed in search results", Status.FAIL);
		}
	}

	// Verify default properties dropdown and Value field is disabled.
	public void verifyAspectPropDropDownResults() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.AspectPropDropDownXpath);

			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectPropDropDownXpath).isEnabled()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectValueXpath).isEnabled()) {
				report.updateTestLog("Verify properties dropdown and Value field ",
						"properties dropdown and Value field is enabled.", Status.FAIL);
			} else {

				report.updateTestLog("Verify properties dropdown and Value field ",
						"properties dropdown and Value field is disabled.", Status.PASS);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify properties dropdown and Value field ",
					"properties dropdown and Value field is enabled.", Status.FAIL);
		}
	}

	// Verify full text attribute.
	public void verifyFieldAttribute() {
		try {
			String Result = UIHelper.findAnElementbyXpath(driver, FullTextXpath).getAttribute("placeholder");
			String Query = dataTable.getData("Search", "Result");
			if (Result.contains(Query)) {
				report.updateTestLog("Verify Field Attribute", "Verify Field Attribute successfully.", Status.PASS);
			} else {

				report.updateTestLog("Verify Field Attribute", "Verify Field Attribute failed.", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Field Attribute", "Verify Field Attribute failed.", Status.FAIL);
		}
	}

	// Verify Boolean ANDSearch result.
	public void verifyBooleanANDSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				for (String fileResult : appSearchPg.searchNames) {
					indexResult = appSearchPg.searchNames.indexOf(fileResult);
					String fileName = dataTable.getData("Search", "FileName");
					String Desc = dataTable.getData("Search", "Description");
					if (appSearchPg.searchNames.get(indexResult).contains(fileName)
							&& appSearchPg.descvalue.get(indexResult).contains(Desc)) {
						report.updateTestLog("Verify Boolean AND search in Search Results",
								"Boolean AND search successfully in search results"
										+ "<br /><b>Expected Values in Result : </b>" + fileName + "and" + Desc
										+ "<br /><b>Actual File name: </b>" + appSearchPg.searchNames.get(indexResult)
										+ "<br /><b>Actual Description : </b>" + appSearchPg.descvalue.get(indexResult),
								Status.PASS);
						break;
					} else {
						report.updateTestLog("Verify Boolean AND search in Search Results",
								"Boolean AND search Fail in search results", Status.FAIL);
					}
				}
			} else {
				report.updateTestLog("Verify Boolean AND search in Search Results",
						"Boolean AND search Fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Boolean AND search in Search Results",
					"Boolean AND search Fail in search results", Status.FAIL);
		}
	}

	// Verify Boolean OR Search result.
	public void verifyBooleanORSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			if (appSearchPg.searchNames.size() > 0) {
				for (String fileResult : appSearchPg.searchNames) {
					indexResult = appSearchPg.searchNames.indexOf(fileResult);
					String fileName = dataTable.getData("Search", "FileName");
					String Desc = dataTable.getData("Search", "Description");
					if (appSearchPg.searchNames.get(indexResult).contains(fileName)
							|| appSearchPg.descvalue.get(indexResult).contains(Desc)) {
						report.updateTestLog("Verify Boolean AND search in Search Results",
								"Boolean AND search successfully in search results"
										+ "<br /><b>Expected Values in Result : </b>" + fileName + "or" + Desc
										+ "<br /><b>Actual File name: </b>" + appSearchPg.searchNames.get(indexResult)
										+ "<br /><b>Actual Description : </b>" + appSearchPg.descvalue.get(indexResult),
								Status.PASS);
						break;
					} else {
						report.updateTestLog("Verify Boolean OR search in Search Results",
								"Boolean OR search Fail in search results", Status.FAIL);
					}
				}
			} else {
				report.updateTestLog("Verify Boolean OR search in Search Results",
						"Boolean OR search Fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Boolean OR search in Search Results",
					"Boolean OR search Fail in search results", Status.FAIL);
		}
	}

	// Verify AND Search result of various input.
	public void verifyANDSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			if (appSearchPg.searchNames.size() > 0) {
				for (String fileResult : appSearchPg.searchNames) {
					indexResult = appSearchPg.searchNames.indexOf(fileResult);
					String fileName = dataTable.getData("Search", "FileName");
					String Desc = dataTable.getData("Search", "Description");
					String title = dataTable.getData("Search", "Title");
					String Modifier = dataTable.getData("Search", "Query");
					String Modifier1 = dataTable.getData("Search", "Result");
					String Metadata = dataTable.getData("Search", "Metadata");
					String ExpectedAssetType = dataTable.getData("Search", "AssetType");
					String ActualAssetType = appSearchPg.getMetadata(fileName, Metadata);
					if (appSearchPg.searchNames.get(indexResult).equals(fileName)
							&& appSearchPg.descvalue.get(indexResult).contains(Desc)
							&& (appSearchPg.modifier.get(indexResult).contains(Modifier)
									|| appSearchPg.modifier.get(indexResult).contains(Modifier1))
							&& appSearchPg.titleName.get(indexResult).contains(title)
							&& ActualAssetType.contains(ExpectedAssetType)) {
						report.updateTestLog("Verify AND search for multi input",
								"AND search for multi input successfully in search results"
										+ "<br /><b> Actual FileName : </b>" + appSearchPg.searchNames.get(indexResult)
										+ "<br /><b> Actual Description : </b>" + appSearchPg.descvalue.get(indexResult)
										+ "<br /><b> Actual Title : </b>" + appSearchPg.titleName.get(indexResult)
										+ "<br /><b> Actual Asset type : </b>" + ActualAssetType,
								Status.PASS);
						break;
					} else {
						report.updateTestLog("Verify AND search for multi input",
								"AND search for multi input fail in search results", Status.FAIL);
					}
				}
			} else {
				report.updateTestLog("Verify AND search for multi input",
						"AND search for multi input fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify AND search for multi input",
					"AND search for multi input fail in search results", Status.FAIL);
		}
	}

	// Verify FileName ANd Asset Type.
	public void verifyFileNameANDAssetSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String ExpectedFileName = dataTable.getData("Search", "FileName");
				String Metadata = dataTable.getData("Search", "Metadata");

				String ExpectedAssetType = dataTable.getData("Search", "AssetType");
				String ActualAssetType = appSearchPg.getMetadataNew(FileName, Metadata);
				if (FileName.contains(ExpectedFileName) && ActualAssetType.contains(ExpectedAssetType)) {

					report.updateTestLog("Verify FileName AND Asset Search",
							"FileName AND Asset Search successfully in Search" + "<br /><b> Actual FileName : </b>"
									+ FileName + "<br /><b> Actual Asset type : </b>" + ActualAssetType,
							Status.PASS);

				} else {
					report.updateTestLog("Verify FileName AND Asset Search", "FileName AND Asset Search fail in Search",
							Status.FAIL);
				}

			} else {
				report.updateTestLog("Verify FileName AND Asset Search", "FileName AND Asset Search fail in Search",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify FileName AND Asset Search", "FileName AND Asset Search fail in Search",
					Status.FAIL);
		}
	}

	// Verify Aspect Type Search.
	public void verifyAspectSearchResults() {
		try {
			String AspectProp = dataTable.getData("Search", "AspectProp");
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String Metadata = dataTable.getData("Search", "Metadata");
				String ExpectedAspectType = dataTable.getData("Search", "Query");
				String ActualAspectType = appSearchPg.getMetadata(FileName, Metadata);
				if (ActualAspectType.contains(ExpectedAspectType)) {
					report.updateTestLog("Verify Aspect Search",
							"Search using Aspect Name " + "<br /><b>Aspect Properties :</b>" + AspectProp
									+ "<br /><b>Expected Aspect Value :</b>" + ExpectedAspectType
									+ "<br /><b>Actual Value :</b>" + ActualAspectType,
							Status.PASS);
				}
			} else {
				report.updateTestLog("Verify Aspect Search", "Aspect Search fail in Search.No result found",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Search", "Aspect Search fail in Search.No result found", Status.FAIL);
		}
	}

	// Verify Aspect Type Search.
	public void verifyAspectResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String Metadata = dataTable.getData("Search", "Metadata");
				String ExpectedAspectType = dataTable.getData("Search", "Result");
				String AspectValue = dataTable.getData("Search", "Query");
				String ActualAspectType = appSearchPg.getMetadata(FileName, Metadata);
				if (ActualAspectType.contains(ExpectedAspectType)) {
					report.updateTestLog("Verify Aspect Search",
							"Aspect Search successfully in Search" + "<br /><b>Input Aspect:</b> " + AspectValue
									+ ", <br /><b>Actual Aspect in result:</b> " + ActualAspectType,
							Status.PASS);
				} else {
					report.updateTestLog("Verify Aspect Search",
							"Aspect Search fail in Search.No result found" + "<br /><b>Input Aspect:</b> " + AspectValue
									+ ", <br /><b>Actual Aspect in result:</b>" + ActualAspectType,
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Aspect Search", "Aspect Search fail in Search.No result found",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Search", "Aspect Search fail in Search.No result found", Status.FAIL);
		}
	}

	// Verify Aspect Type Search.
	public void verifyMultiAspectSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String Metadata = dataTable.getData("Search", "Metadata");
				String Metadata1 = dataTable.getData("Search", "Metadata1");
				String ExpectedAspectType = dataTable.getData("Search", "Query");
				String ActualAspectType = appSearchPg.getMetadata(FileName, Metadata);
				String AspectValue = dataTable.getData("Search", "Query");
				String ActualAspect1Type = appSearchPg.getMetadata(FileName, Metadata1);
				if (ActualAspectType.contains(ExpectedAspectType) && ActualAspect1Type.contains(ExpectedAspectType)) {
					report.updateTestLog("Verify MultiAspect Search",
							"MultiAspect Search successfully in Search" + "<br /><b>Input Aspect:</b> " + AspectValue
									+ ", <br /><b>Actual First Aspect in result:</b> " + ActualAspect1Type
									+ ", <br /><b>Actual Second Aspect in result:</b> " + ActualAspect1Type,
							Status.PASS);
				} else {
					report.updateTestLog("Verify MultiAspect Search",
							"MultiAspect Search fail in Search" + "<br /><b>Input Aspect:</b> " + AspectValue
									+ ", <br /><b>Actual First Aspect in result:</b> " + ActualAspect1Type
									+ ", <br /><b>Actual Second Aspect in result:</b> " + ActualAspect1Type,
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify MultiAspect Search", "MultiAspect Search fail in Search", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify MultiAspect Search", "MultiAspect Search fail in Search", Status.FAIL);
		}
	}

	/**
	 * Method to verify the Thumbnail display in search result.
	 * 
	 * @author 412766
	 */
	public void verifyThumbnailSearchResult() {
		try {
			if (new AlfrescoSearchPage(scriptHelper).isThumbnailDisplayed()) {
				report.updateTestLog("Verify Thumbnail display for Search results",
						"Thumbnail displayed successfully in search results", Status.PASS);
			} else {
				report.updateTestLog("Verify Thumbnail display for Search results",
						"Thumbnail displayed fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Thumbnail display for Search results",
					"Thumbnail displayed fail in search results", Status.FAIL);
		}
	}

	public void verifyGalleryView() {
		try {
			if (UIHelper.findAnElementbyXpath(driver, galleryViewXpath).isDisplayed()) {
				report.updateTestLog("Verify gallery view display for Search results",
						"Gallery view displayed successfully in search results", Status.PASS);
			} else {
				report.updateTestLog("Verify gallery view display for Search results",
						"Gallery view display fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify gallery display for Search results",
					"Gallery View display fail in search results", Status.FAIL);
		}
	}

	public void verifyDetailedView() {
		try {
			if (UIHelper.findAnElementbyXpath(driver, detailViewXpath).isDisplayed()) {
				report.updateTestLog("Verify detailed view display for Search results",
						"Detailed view displayed successfully in search results", Status.PASS);
			} else {
				report.updateTestLog("Verify detailed view display for Search results",
						"Detailed view displayed fail in search results", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify detailed View display for Search results",
					"Detailed View display fail in search results", Status.FAIL);
		}
	}

	// Verify Search result Sorting of various input.
	public void verifySearchResultssorting() {
		try {
			UIHelper.waitForPageToLoad(driver);
			Thread.sleep(2000);
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				if (UIHelper.isSortedList(appSearchPg.searchNames)) {
					if (appSearchPg.searchNames.size() >= 1) {
						report.updateTestLog("Verify search sorting",
								"Search result sorted." + "<br /><b> Sorted result : </b>"
										+ appSearchPg.searchNames.get(0) + appSearchPg.searchNames.get(1),
								Status.PASS);
					} else {
						report.updateTestLog("Verify search sorting", "Search result sorted."
								+ "<br /><b> Sorted result : </b>" + appSearchPg.searchNames.get(0), Status.PASS);
					}

				} else {
					report.updateTestLog("Verify search sorting", "Search result not sorted", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify search sorting", "Search result not sorted", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify search sorting", "Search result not sorted", Status.FAIL);
		}
	}

	// Verify Search result of various input.
	public void verifySearchResults() {
		try {

			appSearchPg.getSearchListValues();
			if (appSearchPg.searchNames.size() > 0) {
				for (String fileResult : appSearchPg.searchNames) {
					indexResult = appSearchPg.searchNames.indexOf(fileResult);
					if (appSearchPg.searchNames.get(indexResult) != null
							&& appSearchPg.siteName.get(indexResult) != null
							&& appSearchPg.modifier.get(indexResult) != null) {
						report.updateTestLog("Verify search result", "Search contains expected values in result"
								+ "<br /><b> Actual FileName : </b>" + appSearchPg.searchNames.get(indexResult)
								+ "<br /><b> Modifier and Last modified : </b>" + appSearchPg.modifier.get(indexResult)
								+ "<br /><b> Actual Description : </b>" + appSearchPg.modifier.get(indexResult)
								+ "<br /><b> Actual Title : </b>" + appSearchPg.titleName.get(indexResult)
								+ "<br /><b> Actual Site Name : </b>" + appSearchPg.siteName.get(indexResult)
								+ "<br /><b> Actual Size : </b>" + appSearchPg.filesize.get(indexResult), Status.PASS);
						break;
					} else {
						report.updateTestLog("Verify search result",
								"Search doesn't contains expected values in result", Status.FAIL);
					}
				}
			} else {
				report.updateTestLog("Verify search result", "Search doesn't contains expected values in result",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify search result", "Search doesn't contains expected values in result",
					Status.FAIL);
		}
	}

	// Verify Keyword Search result of various input.
	public void verifyKeywordSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				report.updateTestLog("Verify Keyword search ", "Keyword search result successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify Keyword search ", "Keyword search result failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Keyword search ", "Keyword search result failed", Status.FAIL);
		}
	}

	// Verify Advanced UI elements
	public void verifyAdvUIElements() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.searchAssetXpath);
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.fullTextXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchmodifierXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchNameXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchTitleXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchDescXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchAssetXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchBtnXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.fromDateXpath).isDisplayed()) {

				UIHelper.highlightElement(driver, appSearchPg.searchmodifierXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchNameXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchTitleXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchDescXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchAssetXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchBtnXpath);
				UIHelper.highlightElement(driver, appSearchPg.fromDateXpath);

				report.updateTestLog("Verify Advance Search UI",
						"Advance Search UI element Verified" + "<br /><b> Search Modifier Field </b>"
								+ "<br /><b> Search Name Field </b>" + "<br /><b> Search Title Field </b>"
								+ "<br /><b> Search Description Field </b>"
								+ "<br /><b> Search Asset type Drop Down </b>" + "<br /><b> Search Button </b>"
								+ "<br /><b> Search Start Date Field </b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Advance Search UI", "Advance Search UI element failed.UI Not available",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Advance Search UI", "Advance Search UI element failed.UI Not available",
					Status.FAIL);
		}

	}

	// Verify Search Result UI elements
	public void verifySearchResultUIElements() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.searchScopeDBXpath);
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.searchScopeDBXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchFilterXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchCountXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchResultXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.viewBtnXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.relevanceXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, appSearchPg.searchScopeDBXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchFilterXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchCountXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchResultXpath);
				UIHelper.highlightElement(driver, appSearchPg.viewBtnXpath);
				UIHelper.highlightElement(driver, appSearchPg.relevanceXpath);
				report.updateTestLog("Verify Search result UI",
						"Search result UI element Verified" + "<br /><b>Search Scope Drop Down</b>"
								+ "<br /><b>Search Filter Panel</b>" + "<br /><b>Search Count</b>"
								+ "<br /><b>Search Result</b>" + "<br /><b>Search Sorting Drop Down</b>"
								+ "<br /><b>Search View Drop Down</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Search result UI", "Search result UI element failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Search result UI", "Search result UI element failed", Status.FAIL);
		}

	}

	// Verify Search UI elements
	public void verifySearchUIElements() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.searchFieldXpath);
			UIHelper.click(driver, appSearchPg.searchImageIconXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.searchFieldXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.searchImageIconXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.advancedSearchXpath).isDisplayed()) {
				UIHelper.highlightElement(driver, appSearchPg.searchFieldXpath);
				UIHelper.highlightElement(driver, appSearchPg.searchImageIconXpath);
				UIHelper.highlightElement(driver, appSearchPg.advancedSearchXpath);
				report.updateTestLog("Verify Search result UI", "Search UI element Verified", Status.PASS);
			} else {
				report.updateTestLog("Verify Search result UI", "Search UI element failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Search result UI", "Search UI element failed", Status.FAIL);
		}

	}

	// Verify Multi Search component elements
	public void verifyMultiSearchElements() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.AspectPropDropDown1Xpath);
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectPropDropDown1Xpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectDropDown1Xpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectValue1Xpath).isDisplayed()) {
				report.updateTestLog("Verify Multi Search Component", "Multi Search Component Verified", Status.PASS);
			} else {
				report.updateTestLog("Verify Multi Search Component", "Multi Search Component failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Multi Search Component", "Multi Search Component failed", Status.FAIL);
		}
	}

	// Verify additional aspect Panel UI Search component elements
	public void verifyAspectPanelElements() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.AspectPropDropDownXpath);
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.aspectPanelUIXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectPropDropDownXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectDropDownXpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectValueXpath).isDisplayed()) {
				report.updateTestLog("Verify Additional Aspect panel Component",
						"Additional Aspect panel Component Verified" + "<br /><b> Additonal Aspect Panel</b>"
								+ "<br /><b> Aspect Drop Down</b>" + "<br /><b> Aspect Property Drop Down</b>"
								+ "<br /><b> Aspect Value Box</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Additional Aspect panel Component",
						"Additional Aspect panel Component failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Additional Aspect panel Component", "Additional Aspect panel Component failed",
					Status.FAIL);
		}
	}

	// Verify all aspect visibility
	public void verifyAllAspectVisible() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.AspectDropDownXpath);
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectDropDownXpath),
					searchResultsList);
			UIHelper.waitFor(driver);
			if (searchResultsList.size() > 8) {
				report.updateTestLog("Verify All Additional Aspect", " All Additional Aspect Option Visible",
						Status.PASS);
			} else {
				report.updateTestLog("Verify All Additional Aspect", " All Additional Aspect empty", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify All Additional Aspect", " All Additional Aspect empty", Status.FAIL);
		}
	}

	// Verify all aspect property visibility
	public void verifyAllAspectpropertyVisible() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.AspectPropDropDownXpath);
			UIHelper.getAllAvailableOptions(UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectPropDropDownXpath),
					searchResultsList);
			UIHelper.waitFor(driver);
			System.out.println(searchResultsList);
			if (searchResultsList.size() > 0) {
				report.updateTestLog("Verify All Additional Aspect property",
						" All Additional Aspect property Option Visible" + "<br /><b> Aspect Property Value : </b>"
								+ searchResultsList.get(0),
						Status.PASS);
			} else {
				report.updateTestLog("Verify All Additional Aspect property", " All Additional Aspect property empty",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify All Additional Aspect property", " All Additional Aspect property empty",
					Status.FAIL);
		}
	}

	// Verify All Combination Search result of various input.
	public void verifyAllCombSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			if (appSearchPg.searchNames.size() > 0) {
				for (String fileResult : appSearchPg.searchNames) {
					indexResult = appSearchPg.searchNames.indexOf(fileResult);
					String fileName = dataTable.getData("Search", "FileName");
					String Desc = dataTable.getData("Search", "Description");
					String title = dataTable.getData("Search", "Title");
					String Modifier = dataTable.getData("Search", "Query");
					// String Metadata = dataTable.getData("Search",
					// "Metadata");
					if (appSearchPg.searchNames.get(indexResult).equals(fileName)
							&& appSearchPg.descvalue.get(indexResult).contains(Desc)
							&& appSearchPg.modifier.get(indexResult).contains(Modifier)
							&& appSearchPg.titleName.get(indexResult).contains(title)) {
						report.updateTestLog("Verify All Combination Search",
								"All Combination Search successfully in search results"
										+ "<br /><b> Actual File Name : </b>" + appSearchPg.searchNames.get(indexResult)
										+ "<br /><b> Actual Description : </b>" + appSearchPg.descvalue.get(indexResult)
										+ "<br /><b> Actual Modifier : </b>"
										+ appSearchPg.modifier.get(indexResult).replace("Modified 5 days ago by ", " ")
										+ "<br /><b> Actual Title : </b>" + appSearchPg.titleName.get(indexResult),
								Status.PASS);
						break;
					} else {
						report.updateTestLog("Verify All Combination Search",
								"All Combination Search  fail in search results", Status.FAIL);
					}
				}
			} else {
				report.updateTestLog("Verify All Combination Search", "All Combination Search  fail in search results",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify All Combination Search", "All Combination Search  fail in search results",
					Status.FAIL);
		}
	}

	// Verify Advanced Search result.
	public void verifyAdvSearchResults() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			appSearchPg.getSearchListValues();
			// String Query = dataTable.getData("Search", "Query");
			if (appSearchPg.searchNames.size() > 0) {
				// for (String fileName : appSearchPg.searchNames) {
				// if (fileName.contains(Query)) {
				report.updateTestLog("Verify Advanced Search",
						"Advanced search successfully in search results. One search result Name :"
								+ appSearchPg.searchNames.get(0),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Advanced Name Search",
						"Advanced search fail in search results. No results Found", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Advanced Name Search",
					"Advanced search fail in search results. No results Found", Status.FAIL);
		}
	}

	// Verify Site Availability
	public void verifySiteSeacrh() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, siteTitleXpath);
			String siteassertValue = dataTable.getData("Sites", "SiteName");
			if (UIHelper.findAnElementbyXpath(driver, siteTitleXpath).getText().equalsIgnoreCase(siteassertValue)) {
				report.updateTestLog("Verify Site Search", "Site Search Successfull for given site :" + siteassertValue,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Site Search", "Site Search failed for given site :" + siteassertValue,
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Site Search", "Site Search failed for given site.", Status.FAIL);
		}
	}

	// Verify Wild Character Search result of various input.
	public void verifyWildCharSearch() {
		try {
			UIHelper.waitForPageToLoad(driver);
			appSearchPg.getSearchListValues();
			String Query = dataTable.getData("Search", "Query");
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				report.updateTestLog("Verify Wild Character",
						"Wild Character search result successfully for given query :" + Query, Status.PASS);

			} else {
				report.updateTestLog("Verify Wild Character",
						"Wild Character search result failed for given query :" + Query, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Wild Character", "Wild Character search result failed.", Status.FAIL);
		}
	}

	// Verify Special Character Search result of various input.
	public void verifySpecialCharSearch() {
		try {
			UIHelper.waitForPageToLoad(driver);
			appSearchPg.getSearchListValues();
			String Query = dataTable.getData("Search", "Query");
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				report.updateTestLog("Verify Special Character",
						"Special Character search result successfully for given query :" + Query, Status.PASS);

			} else {
				report.updateTestLog("Verify Special Character",
						"Special Character search result failed for given query :" + Query, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Special Character", "Special Character search result failed.", Status.FAIL);
		}
	}

	public void verifyDocPreview() {
		try {
			UIHelper.waitForPageToLoad(driver);
			String Query = dataTable.getData("Search", "FileName");
			String finalFileName = appSearchPg.tempFileNameNames.replace("CRAFT", Query);
			String finalViewName = appSearchPg.viewDocXpath.replace("CRAFT", Query);
			String file = UIHelper.findAnElementbyXpath(driver, finalFileName).getText();
			UIHelper.click(driver, finalViewName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, previewXpath);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, previewXpath)) {
				UIHelper.highlightElement(driver, previewXpath);
				report.updateTestLog("Verify Doc Preview",
						"Verify Doc Preview from search result successfully"
								+ "<br /><b> Expected file to be previewed : </b>" + Query
								+ "<br /><b> Actual file : </b>" + file,
						Status.PASS);

			} else {
				report.updateTestLog("Verify Doc Preview", "Verify Doc Preview from search result failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Doc Preview", "Verify Doc Preview from search result failed", Status.FAIL);
		}
	}

	// Verify blank spaces followed by numbers Search result of various input.
	public void verifyBlankSpacesearch() {
		try {
			UIHelper.waitForPageToLoad(driver);
			appSearchPg.getSearchListValues();
			Thread.sleep(2000);
			String Query = dataTable.getData("Search", "Query");
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				report.updateTestLog("Verify blank spaces followed by numbers",
						"blank spaces followed by numbers search result successfully for given query :" + Query,
						Status.PASS);

			} else {
				report.updateTestLog("Verify blank spaces followed by numbers",
						"Blank spaces followed by numbers search result failed for given query :" + Query, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify blank spaces followed by numbers",
					"Blank spaces followed by numbers search result failed.", Status.FAIL);
		}
	}

	// Verify Deep folder search result.
	public void verifyDeepFoldersearch() {
		try {
			appSearchPg.getSearchListValues();
			String Query = dataTable.getData("Search", "Query");
			UIHelper.waitFor(driver);
			if (appSearchPg.location.size() > 0) {
				report.updateTestLog("Verify deep folder search",
						"Deep folder search successfully for file in folder :" + appSearchPg.location.get(0),
						Status.PASS);

			} else {
				report.updateTestLog("Verify deep folder search", "Deep folder search failed " + Query, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify deep folder search", "Deep folder search failed ", Status.FAIL);
		}
	}

	// Verify Edit offline from search result.
	public void verifyEditOffAction() {
		String file = dataTable.getData("Search", "FileName");
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalisLockXpath = isLockXpath.replace("CRAFT", file);
			if (UIHelper.findAnElementbyXpath(driver, finalisLockXpath).isDisplayed()) {
				report.updateTestLog("Verify Edit Offline", "Edit Offline successfully for file :" + file, Status.PASS);

			} else {
				report.updateTestLog("Verify Edit Offline", "Edit Offline failed for file :" + file, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Edit Offline", "Edit Offline failed for file :" + file, Status.FAIL);
		}
	}

	// Verify Lemmatization search from search result.
	public void verifyLemmatizationsearch() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			String file = dataTable.getData("Search", "FileName");
			String file1 = dataTable.getData("Search", "Query");
			String file2 = dataTable.getData("Search", "Result");
			UIHelper.waitFor(driver);

			if (appSearchPg.searchNames.contains(file) && appSearchPg.searchNames.contains(file1)
					&& appSearchPg.searchNames.contains(file2)) {
				report.updateTestLog("Verify Lemmatization search", "Lemmatizationsearch successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify Lemmatization search", "Lemmatization search failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Lemmatization search", "Lemmatization search failed", Status.FAIL);
		}
	}

	// Verify Aspect Type Search.
	public void verifyFolderAspectResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String Metadata = dataTable.getData("Search", "Metadata");
				String ExpectedAspectType = dataTable.getData("Search", "Result");
				String AspectValue = dataTable.getData("Search", "Query");
				String ActualAspectType = appSearchPg.getFolderMetadata(FileName, Metadata);
				if (ActualAspectType.contains(ExpectedAspectType)) {
					report.updateTestLog("Verify Folder Aspect Search",
							"Aspect Search successfully in Search" + "<br /><b>Input Aspect:</b> " + AspectValue
									+ ", <br /><b>Actual Aspect in result:</b> " + ActualAspectType,
							Status.PASS);
				} else {
					report.updateTestLog("Verify Folder Aspect Search", "Aspect Search fail in Search.No result found",
							Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Folder Aspect Search", "Aspect Search fail in Search.No result found",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Folder Aspect Search", "Aspect Search fail in Search.No result found",
					Status.FAIL);
		}
	}

	// VerifyTag search result of various input.
	public void verifyTagSearchResults() {
		try {
			appSearchPg.getSearchListValues();
			UIHelper.waitFor(driver);

			if (appSearchPg.searchNames.size() > 0) {
				String file = dataTable.getData("Search", "FileName");
				String finalisLockXpath = tempNameXpath.replace("CRAFT", file);
				UIHelper.click(driver, finalisLockXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				if (UIHelper.findAnElementbyXpath(driver, TagXpath).isDisplayed()) {
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, TagXpath));
					UIHelper.highlightElement(driver, TagXpath);
					report.updateTestLog("Verify Tag search ", "Tag search result successfully"
							+ "<br /><b> Tag Name </b> :" + UIHelper.findAnElementbyXpath(driver, TagXpath).getText(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify Keyword search ", "Tag search result failed", Status.FAIL);
				}
			} else {
				report.updateTestLog("Verify Keyword search ", "Tag search result failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Keyword search ", "Tag search result failed", Status.FAIL);
		}
	}

	// Verify colour of the object.
	public void verifycolour(String Xpath) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, Xpath);

			if (UIHelper.findAnElementbyXpath(driver, Xpath).isDisplayed()) {
				// UIHelper.highlightElement(driver, Xpath);
				String result = UIHelper.findAnElementbyXpath(driver, Xpath).getCssValue("color");
				report.updateTestLog("Get colour",
						"Colour property of the element is verfied" + "<br><b>Rgb code :</b>" + result, Status.PASS);
			} else {

				report.updateTestLog("Get colour", "Colour property of the element is failed.Element not found",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Get colour", "Colour property of the element is failed.Element not found",
					Status.FAIL);
		}
	}

	// Click select aspect aspect
	public void clickAspect() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, appSearchPg.aspectPanelXpath);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectDropDownXpath);
			dropdownEle.click();

			report.updateTestLog("Verify Aspect Dropdown", "Aspect Dropdown expands on click successfully",
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Dropdown", "Aspect Dropdown expands on click successfully",
					Status.FAIL);
		}

	}

	// Click select aspect aspect
	public void verifyAspectDPList() {
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, appSearchPg.aspectPanelXpath);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectDropDownXpath);
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			aspectDPList = myFiles.ListFolderNames(folderDetails);
			UIHelper.waitFor(driver);
			for (String apsect : aspectDPList) {
				if (UIHelper.checkDropdownListValuesWithExpectedValue(dropdownEle, apsect)) {
					UIHelper.waitFor(driver);
					aspectList.add(apsect);
					UIHelper.waitFor(driver);
				}
			}
			report.updateTestLog("Verify Aspect Dropdown elements",
					"Aspect Dropdown elements verified successfully" + " <br><b>Aspect Elements : </b>" + aspectList,
					Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Dropdown elements", "Aspect Dropdown elements verified failed",
					Status.FAIL);
		}

	}

	// Click select aspect DP list and check sorted.
	public void verifyAspectDPSort() {
		try {
			UIHelper.waitFor(driver);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectDropDownXpath);
			UIHelper.getAllAvailableOptions(dropdownEle, aspectDPList);
			aspectDPList.remove(0);
			List<String> DPList = aspectDPList.subList(0, 5);
			UIHelper.waitFor(driver);
			if (UIHelper.isSortedList(DPList)) {
				report.updateTestLog("Verify Aspect Dropdown elements Sort", "Aspect Dropdown elements sort verified",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Aspect Dropdown elements Sort", "Aspect Dropdown elements sort failed",
					Status.FAIL);
		}

	}

	// Select My searches or All searches
	public void verifySavedSearchTypes() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, savedSearchTypesXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, savedSearchTypesXpath));
			UIHelper.waitFor(driver);

			if (UIHelper.checkForAnElementbyXpath(driver, savedSearchTypesXpath)) {
				UIHelper.highlightElement(driver, savedSearchTypesXpath);
				report.updateTestLog("Verify Saved searches types", "My searches and All searches checkbox displayed",
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Saved searches types",
					"Failed. My searches and All searches checkbox are not displayed", Status.FAIL);
		}
	}

	// Verify the search query option
	public void verifySearchQueryOption() {
		try {

			if (UIHelper.checkForAnElementbyXpath(driver, searchQueryTxtXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, searchQueryTxtXpath));
				UIHelper.highlightElement(driver, searchQueryTxtXpath);
				report.updateTestLog("Verify Save Search query option", "Save Search query displayed successfully",
						Status.PASS);
			}

		} catch (Exception e) {
			e.printStackTrace();

			report.updateTestLog("Verify Save Search query option", "Save Search query option is not displayed",
					Status.FAIL);
		}
	}

	// Verify the select drop down
	public boolean checkSelectDropDownOption() {
		boolean flag = false;
		try {

			if (UIHelper.checkForAnElementbyXpath(driver, selectOptionXpath)) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, selectOptionXpath));
				UIHelper.highlightElement(driver, selectOptionXpath);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;

		}
		return flag;
	}

	public void verifyConfirmationDailogMessage(String expectedMessageVal) {
		try {

			String actualMessageVal = appSearchPg.getTheMessageText(expectedMessageVal);
			new FileUtil().appendTextToFile(actualMessageVal, testOutputFilePath);

			System.out.println(actualMessageVal);
			System.out.println(expectedMessageVal);

			if (actualMessageVal.equalsIgnoreCase(expectedMessageVal)
					|| actualMessageVal.contains(expectedMessageVal)) {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User able to see the confirmation mesaage as: " + actualMessageVal, Status.PASS);
			} else {
				report.updateTestLog("Verify Confirmation Message Dailog Text",
						"User not able to see the confirmation mesaage as: " + actualMessageVal, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify the saved search elements
	public void verifySavedSearchElements() {
		try {

			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, rightGadgetXpath);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, saveSearchNameXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, saveSearchDescXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, saveSearchVisiblitycXpath))
					&& UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, saveSearchBtnXpath))) {

				UIHelper.highlightElement(driver, saveSearchNameXpath);
				UIHelper.highlightElement(driver, saveSearchDescXpath);
				UIHelper.highlightElement(driver, saveSearchVisiblitycXpath);
				UIHelper.highlightElement(driver, saveSearchBtnXpath);
				if (UIHelper.findAnElementbyXpath(driver, saveSrcprivateXpath).isSelected()) {

					report.updateTestLog("Verify Saved search elements", "Saved search elemets dispalyed successfully",
							Status.PASS);
				}
			} else {

				report.updateTestLog("Verify Saved search elements", "Saved search elemets are not dispalyed",
						Status.FAIL);

			}

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Saved search elements", "Failed. Saved search elemets are not dispalyed",
					Status.FAIL);
		}
	}

	// saved search functionality without name (mandatory)
	public void saveSearchWithoutName() {
		try {
			UIHelper.waitFor(driver);
			String Desc = dataTable.getData("Search", "SavedSearchDesc");
			String visiblity = dataTable.getData("Search", "Visibility");
			String finalSaveSrcVisiblityXpath = saveSrcVisiblitycXpath.replace("CRAFT", visiblity);
			UIHelper.waitForVisibilityOfEleByXpath(driver, rightGadgetXpath);
			UIHelper.findAnElementbyXpath(driver, saveSrcNameXpath).clear();
			UIHelper.findAnElementbyXpath(driver, saveSrcDescXpath).clear();
			UIHelper.sendKeysToAnElementByXpath(driver, saveSrcDescXpath, Desc);
			UIHelper.findAnElementbyXpath(driver, finalSaveSrcVisiblityXpath).click();
			UIHelper.waitFor(driver);
			System.out.println(UIHelper.findAnElementbyXpath(driver, saveSearchBtnXpath).getAttribute("disabled"));
			if (UIHelper.findAnElementbyXpath(driver, saveSearchBtnXpath).getAttribute("disabled").equals("true")) {

				UIHelper.highlightElement(driver, saveSearchBtnXpath);
				report.updateTestLog("Save the search query Without Name", "Save button is not enabled", Status.PASS);
				UIHelper.highlightElement(driver, saveSearchBtnXpath);

			} else {

				report.updateTestLog("Save the search query Without Name", "Save button is enabled", Status.FAIL);

			}
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Save the search query Without Name", "Failed", Status.FAIL);
		}
	}

	// Verify the Select Drop down Values
	public boolean verifySelectDrpDownVals(String[] expctLst) {
		boolean falg = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, selectLinkXPath);

			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(driver, selectLinkXPath));
			UIHelper.click(driver, selectLinkXPath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, slctListValsXPath);
			String[] actVlasLst = new String[3];
			// System.out.println(""+expctLst.length);
			for (int i = 0; i < expctLst.length; i++) {

				actVlasLst[i] = UIHelper.getTextFromWebElement(driver,
						slctListValsXPath + "[text()='" + expctLst[i] + "']");
				// System.out.println(""+actVlasLst[i]+expctLst[i]);
			}

			for (int i = 0; i < expctLst.length; i++) {

				if (actVlasLst[i].equalsIgnoreCase(expctLst[i])) {
					falg = true;

				} else {
					// System.out.println("In else");
					falg = false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		// System.out.println(""+falg);
		return falg;
	}

	// Verify Created Filter in Search Results Page
	public void verifyCreatedFilter(String createfilterDetails) {
		try {

			String expectedFilterName = "";
			if (createfilterDetails.contains(",")) {
				String splittedFiltereDetailas[] = createfilterDetails.split(",");

				expectedFilterName = splittedFiltereDetailas[1].replace("FilterName:", "");
			}

			ArrayList<String> filterHeadingNamesList = appSearchPg.getFilterHeadings();

			boolean isDisplayedCreatedFilter = false;
			for (String actualFilter : filterHeadingNamesList) {
				if (actualFilter.contains(expectedFilterName) || actualFilter.equalsIgnoreCase(expectedFilterName)) {
					isDisplayedCreatedFilter = true;
				}
			}

			if (isDisplayedCreatedFilter) {
				report.updateTestLog("Verify Created Filter in Search Results Page",
						"Created Filter:" + expectedFilterName + " Displayed successfully in Search Results Page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Created Filter in Search Results Page",
						"Created Filter:" + expectedFilterName + " failed to displayed in Search Results Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify file in search results Page
	public void verifyFileInSearchResultsForAppliedFilter() {
		try {
			String fileName = dataTable.getData("MyFiles", "FileName");
			searchResultsList = appSearchPg.getSearchResultsTitle();
			String actualResult = "";
			for (String actualSearchResult : searchResultsList) {
				if (actualSearchResult.equalsIgnoreCase(fileName)) {
					isDisplayedSearchResult = true;
					actualResult = actualSearchResult;
					break;
				} else {
					actualResult = "File Not Found";
					isDisplayedSearchResult = false;
				}
			}

			if (isDisplayedSearchResult) {
				report.updateTestLog("Verify File in from filtered Search Results",
						"File:" + fileName + " displayed successfully in filtered search results."
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult + "",
						Status.PASS);
			} else {
				report.updateTestLog("Verify File in from filtered Search Results",
						"File:" + fileName + " failed to display in failed search results."
								+ "<br /><b>Expected Result:</b> " + fileName + ", <br /><b>Actual Result:</b> "
								+ actualResult,
						Status.FAIL);
			}
		} catch (Exception e) {
		}
	}

	// Check search result Scope Name in Advanced search
	public void searchResultScope(String Xpath, String name) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.selectResultlocXpath);
			UIHelper.click(driver, appSearchPg.selectResultlocXpath);
			UIHelper.waitFor(driver);
			// String searchscope = dataTable.getData("Search", "Actions");
			/*
			 * String finalselectscopeXpath = selectScopeXpath.replace("CRAFT",
			 * searchscope);
			 */
			if (UIHelper.checkForAnWebElement(UIHelper.findAnElementbyXpath(driver, Xpath))) {
				UIHelper.highlightElement(driver, Xpath);
				// UIHelper.click(driver, Xpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Check Search location Availability",
						"Check Search location Availability successfully" + name, Status.PASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Check Search location Availability",
					"Check Search location Availability failed" + name, Status.FAIL);
		}

	}

	// Check search result count and provided result count are same.
	public String verifysearchcount() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.searchCountXpath);
			UIHelper.highlightElement(driver, appSearchPg.searchCountXpath);

			Count = UIHelper.findAnElementbyXpath(driver, appSearchPg.searchCountXpath).getText();
			// UIHelper.click(driver, Xpath);

			/* report.updateTestLog("Check ", "successfully", Status.PASS); */

		} catch (Exception e) {
			e.printStackTrace();
			/* report.updateTestLog("Check ", "successfully", Status.FAIL); */
			/*
			 * report.updateTestLog("Check Search location Availability",
			 * "Check Search location Availability failed" + name, Status.FAIL);
			 */
		}
		return Count;
	}

	// Verify All Sites search result.
	public void verifyAllSitesearch(String Count) {
		try {
			appSearchPg.getSearchListValues();
			String Query = dataTable.getData("Search", "Query");
			UIHelper.waitFor(driver);
			if (appSearchPg.siteName.size() > 0) {
				report.updateTestLog("Verify All Sites search",
						"All Sites search result displayed " + "<br><b> No of result : </b>" + Count
								+ "<br><b> Search Query : </b>" + Query + "<br><b> Site Name : </b>"
								+ appSearchPg.siteName.get(0),
						Status.PASS);

			} else {
				report.updateTestLog("Verify All Sites search", "All Sites search result failed " + Query, Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify All Sites search", "All Sites search result failed ", Status.FAIL);
		}
	}

	// Verify Repository search result.
	public void verifyRepositorysearch(String count) {
		try {
			appSearchPg.getSearchListValues();
			String Query = dataTable.getData("Search", "Query");
			UIHelper.waitFor(driver);
			if (appSearchPg.location.size() > 0) {
				report.updateTestLog("Verify Repository search",
						"Repository search result Displayed" + "<br><b> No of result : </b>" + Count
								+ "<br><b> Search Query : </b>" + Query + "<br><b> Location : </b>"
								+ appSearchPg.location.get(0),
						Status.PASS);

			} else {
				report.updateTestLog("Verify Repository search", "Repository search result failed ", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("VerifyRepository search", "Repository search result failed ", Status.FAIL);
		}
	}

	// Verify Aspect Type Search.
	public void verifySearchFilter(String ExpectedValue) {
		try {

			appSearchPg.getSearchListValues();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String Metadata = dataTable.getData("Search", "Metadata");
				String type = dataTable.getData("Search", "Title");
				/*
				 * String ExpectedAspectType = dataTable .getData("Search",
				 * "AssetType")
				 */;
				/*
				 * String ExpectedType = dataTable .getData("Search", "Result");
				 */
				String ActualAspectType = appSearchPg.getMetadata(FileName, Metadata);

				System.out.println(ActualAspectType);
				if (ActualAspectType.contains(ExpectedValue)) {
					report.updateTestLog("Verify" + type + " Filter",
							"Verify" + type + " Filter in search result after filter applied"
									+ "<br /><b>Expected Aspect Value :</b>" + ExpectedValue
									+ "<br /><b>Actual Value :</b>" + ActualAspectType,
							Status.PASS);
				}
			} else {
				report.updateTestLog("Verify Filter in Search result", "Verify Filter in Search result failed",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Filter in Search result", "Verify Filter in Search result failed",
					Status.FAIL);
		}
	}

	// Verify Search Result UI elements
	public void verifySearchManagerUIElements() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, appSearchPg.filterHeaderID);
			/*
			 * String filter = dataTable.getData("Search", "Actions"); String
			 * finalsearchFiltersXpath =
			 * appSearchPg.searchFiltersXpath.replace("CRAFT", filter);
			 */
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.createnewfilterxpath).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeaderID).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeadername).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeaderProp).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeaderType).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeaderResult).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeaderfilter).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, appSearchPg.filterHeaderfilterAvail).isDisplayed()) {

				UIHelper.highlightElement(driver, appSearchPg.createnewfilterxpath);
				UIHelper.highlightElement(driver, appSearchPg.filterHeaderID);
				UIHelper.highlightElement(driver, appSearchPg.filterHeadername);
				UIHelper.highlightElement(driver, appSearchPg.filterHeaderProp);
				UIHelper.highlightElement(driver, appSearchPg.filterHeaderType);
				UIHelper.highlightElement(driver, appSearchPg.filterHeaderResult);
				UIHelper.highlightElement(driver, appSearchPg.filterHeaderfilterAvail);
				report.updateTestLog("Verify Search Manager UI",
						"Search Manager UI element Verified" + "<br /><b>Create New Filter</b>"
								+ "<br /><b>Filter id</b>" + "<br /><b>Filter Name</b>" + "<br /><b>Filter Property</b>"
								+ "<br /><b>Filter Type</b>" + "<br /><b>Show with Search Results</b>"
								+ "<br /><b>Default Filter</b>" + "<br /><b>Filter Availability</b>",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Search Manager UI", "Search Manager UI element failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify Search Manager UI", "Search Manager UI element failed", Status.FAIL);
		}

	}

	// Verify Lifecycle Drop down List
	public void verLfeCyclDrpLstAdvSrch() {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, lfeCyclDrpDownXpath1);
			if (UIHelper.findAnElementbyXpath(driver, lfeCyclDrpDownXpath1).isDisplayed()
					&& UIHelper.findAnElementbyXpath(driver, lfeCyclDrpDownXpath2).isDisplayed()) {
				report.updateTestLog("Verify Pearson Custom LifeCycle Aspect Drop down",
						"Life Cycle name and State values displayed successfully ", Status.PASS);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Pearson Custom LifeCycle Aspect Drop down",
					"Life Cycle name and State values NOT displayed successfully ", Status.FAIL);
		}
	}

	// Verify 'Selected Items' Menu options From Search Results Page
	public void verifySelectedItemsMenuOptionFromSearchResultsPage(String selectedItemMenuOptVal) {
		try {
			boolean isDisplayedSelectedItem = false;
			ArrayList<String> selectedItemsMenuValues = appSearchPg.getSelectedItemMenuOption();
			String actualValue = "";
			for (String actualVal : selectedItemsMenuValues) {
				if (actualVal.contains(selectedItemMenuOptVal)) {
					isDisplayedSelectedItem = true;
					actualValue = actualVal;
					break;
				} else {
					actualValue = "Option not found in 'Selected Items' menu";
					isDisplayedSelectedItem = false;
				}
			}
      System.out.println("option"+selectedItemsMenuValues);

			if (isDisplayedSelectedItem) {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ " Option for any files or folder from Selected Items menu",
						"User is able to see the 'Selected Items' menu Option:" + selectedItemMenuOptVal
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ " Option for any files or folder from Selected Items menu",
						"User is not able to see the 'Selected Items' menu  Option:" + selectedItemMenuOptVal
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.FAIL);
			}
		} catch (Exception e) {
		}

	}

	// Negative Case - Verify 'Selected Items' Menu options From Search Results
	// Page
	public void verifySelectedItemsMenuOptionForNegativeCase(String selectedItemMenuOptVal) {
		try {
			boolean isDisplayedSelectedItem = false;
			ArrayList<String> selectedItemsMenuValues = appSearchPg.getSelectedItemMenuOption();
			String actualValue = "";
			for (String actualVal : selectedItemsMenuValues) {
				if (actualVal.contains(selectedItemMenuOptVal)) {
					isDisplayedSelectedItem = true;
					actualValue = actualVal;
					break;
				} else {
					actualValue = "'Option not found in 'Selected Items' menu";
					isDisplayedSelectedItem = false;
				}
			}

			if (!isDisplayedSelectedItem) {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ " Option for any files or folder from Selected Items menu",
						"User is not able to see the '" + selectedItemMenuOptVal + "' in 'Selected Items' menu"
								+ selectedItemMenuOptVal + "<br><b>Expected Result:</b>" + selectedItemMenuOptVal
								+ " <br><b>Actual Result:</b>" + actualValue,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the " + selectedItemMenuOptVal
								+ " Option for any files or folder from Selected Items menu",
						"User is able to see the '" + selectedItemMenuOptVal + "' in 'Selected Items' menu"
								+ "<br><b>Expected Result:</b>" + selectedItemMenuOptVal + " <br><b>Actual Result:</b>"
								+ actualValue,
						Status.FAIL);
			}

		} catch (Exception e) {
		}
	}

	// Verify Message For File when clicked Calculate Size Option
	public void verifyMessageForFileOnceClickedCalculateSizeOption() {
		try {
			String folderSizeVal = appSearchPg.getMessageForFileOnceClickedCalculateSize();

			if (!folderSizeVal.equalsIgnoreCase("'Please select folder only' not get displayed for selected file")) {
				report.updateTestLog(
						"Verify displayed message for files when clicks on 'Calculate Size' option for file alone in 'Search Results' page",
						"User able to see message as '" + folderSizeVal
								+ "' when clicks on 'Calculate Size' option for file alone in 'Search Results' page",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Verify displayed message for files when clicks on 'Calculate Size' option for file alone in 'Search Results' page",
						"User not able to see the message as 'Please select folder only.' when clicks on 'Calculate Size' option for file alone in 'Search Results' page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check Look for field value in 'Advanced Search' Page
	public void verifyLookForFieldValueInAdvancedSearchPg() {
		try {
			if (appSearchPg.checkLookForFieldValue()) {
				report.updateTestLog("Verify 'Content' is selected as default in Look for field",
						"<b>'Content'</b> displayed as default in 'Look for' field in 'Advanced Search' Page",
						Status.PASS);
			} else {
				report.updateTestLog("Verify 'Content' is selected as default in Look for field",
						"<b>'Content'</b> is failed to display as default in 'Look for' field in 'Advanced Search' Page",
						Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Aspect Type Search.
	public void verifySearchFilterNew(String ExpectedValue) {
		try {

			appSearchPg.getSearchListValues();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			if (appSearchPg.searchNames.size() > 0) {
				String FileName = appSearchPg.searchNames.get(0);
				String Metadata = dataTable.getData("Search", "Metadata");
				String type = dataTable.getData("Search", "Title");
				/*
				 * String ExpectedAspectType = dataTable .getData("Search",
				 * "AssetType")
				 */;
				/*
				 * String ExpectedType = dataTable .getData("Search", "Result");
				 */
				String ActualAspectType = appSearchPg.getMetadataNew(FileName, Metadata);

				if (ActualAspectType.contains(ExpectedValue)) {
					report.updateTestLog("Verify" + type + " Filter",
							"Verify" + type + " Filter in search result after filter applied"
									+ "<br /><b>Expected Aspect Value :</b>" + ExpectedValue
									+ "<br /><b>Actual Value :</b>" + ActualAspectType,
							Status.PASS);
				}
			} else {
				report.updateTestLog("Verify Filter in Search result", "Verify Filter in Search result failed",
						Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Verify Filter in Search result", "Verify Filter in Search result failed",
					Status.FAIL);
		}
	}

	// Check Look for field value in 'Advanced Search' Page
	public boolean verifysavesearchlabel(String value) {
		boolean flag = false;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, saveSearchNameXpath);

			String finalsaveSrclabel = saveSrclabel.replace("CRAFT", value);

			if (UIHelper.checkForAnElementbyXpath(driver, finalsaveSrclabel)) {
				UIHelper.highlightElement(driver, finalsaveSrclabel);
				flag = true;

			} else {
				flag = false;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public void clickodresultfolder(String Query) {
		try {
			UIHelper.waitForPageToLoad(driver);

			String finalFileName = appSearchPg.tempsearchresultclick.replace("CRAFT", Query);
			String breadcrumblabel = appSearchPg.tempBreadcrumblabel.replace("CRAFT", Query);

			UIHelper.click(driver, finalFileName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, breadcrumblabel);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, breadcrumblabel)) {
				UIHelper.highlightElement(driver, breadcrumblabel);
				report.updateTestLog("Verify folder/file open from search page",
						"folder/file open from search  result page successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify folder/file open from search page",
						"folder/file open from search  result page failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify folder/file open from search page",
					"folder/file open from search  result page failed", Status.FAIL);
		}
	}

	public void verifyDropDownBesidesResult() {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver, selectBtnlocationXpath)) {
				report.updateTestLog(
						"Verify the presence of Alfresco Vanilla default dropdown beside Results found label upon performing basic search for file or folder",
						"default dropdown displayed successfully beside Result found lable", Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the presence of Alfresco Vanilla default dropdown beside Results found label upon performing basic search for file or folder",
						"default dropdown failed to displayed beside Result found lable", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify the presence of Alfresco Vanilla default dropdown beside Results found label upon performing basic search for file or folder",
					"default dropdown failed to displayed beside Result found lable", Status.FAIL);
		}
	}

	public void verifySelectedItemDisabled() {
		try {
			if (UIHelper.findAnElementbyXpath(driver, selectBtnlocationXpath).getAttribute("aria-disabled")
					.equals("true")) {
				report.updateTestLog(
						"Verify Alfresco Vanilla default dropdown is disabled when none of the files or folders are selected from search results",
						"default dropdown is disabled when none of the files or folders are selected", Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Alfresco Vanilla default dropdown is disabled when none of the files or folders are selected from search results",
						"default dropdown is not disabled when none of the files or folders are selected", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify Alfresco Vanilla default dropdown is disabled when none of the files or folders are selected from search results",
					"default dropdown is not disabled when none of the files or folders are selected", Status.FAIL);
		}
	}

	public void verifyAbsenceDropdownBesideGearIcon() {
		try {
			UIHelper.findAndAddElementsToaList(driver, menuBarListXpath, menuBarItems);
			if (!menuBarItems.contains("Selected Items...")) {
				report.updateTestLog("Verify the absence of Pearson customized default dropdown beside gear icon",
						"customized default dropdown is not present besides gear icon", Status.PASS);
			} else {
				report.updateTestLog("Verify the absence of Pearson customized default dropdown beside gear icon",
						"customized default dropdown is present besides gear icon", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify the absence of Pearson customized default dropdown beside gear icon",
					"customized default dropdown is  present besides gear icon", Status.FAIL);
		}
	}

	public void verifySelectedItemEnabled() {
		try {
			UIHelper.waitFor(driver);
			if (UIHelper.findAnElementbyXpath(driver, selectBtnlocationXpath).getAttribute("aria-disabled")
					.equals("false")) {
				report.updateTestLog(
						"Verify Alfresco Vanilla default dropdown is Enabled upon selecting of the files or folders from search results",
						"default dropdown is Enabled upon selecting of the files or folders", Status.PASS);

			} else {
				report.updateTestLog(
						"Verify Alfresco Vanilla default dropdown is Enabled upon selecting of the files or folders from search results",
						"default dropdown is not Enabled upon selecting of the files or folders", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Verify Alfresco Vanilla default dropdown is Enabled upon selecting of the files or folders  from search results",
					"default dropdown is not Enabled upon selecting of the files or folders", Status.FAIL);
		}
	}
	public void clickofresultfile(String Query) {
		try {
			UIHelper.waitForPageToLoad(driver);

			String finalFileName = appSearchPg.tempsearchresultclick.replace("CRAFT", Query);
			String breadcrumblabel = appSearchPg.tempBreadCrumbForFile.replace("CRAFT", Query);

			UIHelper.click(driver, finalFileName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, breadcrumblabel);
			UIHelper.waitFor(driver);
			if (UIHelper.checkForAnElementbyXpath(driver, breadcrumblabel)) {
				UIHelper.highlightElement(driver, breadcrumblabel);
				report.updateTestLog("Verify file open from search page",
						"file open from search  result page successfully", Status.PASS);

			} else {
				report.updateTestLog("Verify file open from search page",
						"file open from search  result page failed", Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Verify file open from search page",
					"file open from search  result page failed", Status.FAIL);
		}
	}

}
