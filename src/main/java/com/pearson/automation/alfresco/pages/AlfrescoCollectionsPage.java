package com.pearson.automation.alfresco.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * Functional Components class
 * 
 * @author Cognizant
 */
public class AlfrescoCollectionsPage extends ReusableLibrary {
	private String editCollectionOptionXpath = ".//*[contains(@id,'default-assembly-view-button-button') and not(contains(@disabled,'disabled'))]";
	public String messageXpath = ".//*[@id='message']/div";
	private String createMenuItemsXpath = ".//*[contains(@id,'default-createContent-menu')]//*[contains(@id,'yui-gen')]/a[@class='yuimenuitemlabel']/span";
	public String createLinkXpath = ".//*[@id='bd']//span[contains(@id,'createContent-button')]//span/*[contains(.,'Create')]";
	public String createRulesLinkXpath = "//*[@id=\"template_x002e_rules-none_x002e_folder-rules_x0023_default-body\"]/div[2]/div[1]/a";
	private String createContentContainerXpath = ".//*[contains(@id,'default-createContent-menu')]/*[@class='bd']";
	private String loadingDocumentsXpath = ".//*[@class='yui-dt-message']//*[contains(@id,'yui-gen')]/*[contains(.,'Loading Documents')]";
	private String loadingDocumentsnewXpath = ".//*[@class='yui-dt-message']//*[contains(@id,'yui-gen')]/*[contains(.,'Loading Documents')]";
	private String uploadBtnXpath = ".//*[@class='file-upload']//span//button[contains(.,'Upload')]";
	private String dailogTitleXpath = ".//*[contains(@id,'default-createObject-dialogTitle')]";
	private String tempXpathForLeftSideFolderFromTreeView = ".//*[@id='assembly-view-tree-container']//a/*[text()='CRAFT']";
	private String tempXpathForLeftSideParentFolderFromTreeView = ".//*[@id='assembly-view-tree-container']//a[text()='CRAFT']";
	public String paneltwister = ".//div[@class='document-details-panel']//h2";
	public String paneltwisteropen = ".//div[@class='document-details-panel']//h2[contains(@class,'twister-open')]";

	private String tempXpathForCreateMenuItemLink = ".//*[contains(@id,'yui-gen')]/a/span[contains(.,'CRAFT')]";
	private String nameFieldXpathInCreateObject = ".//*[contains(@id,'createObject_prop_cm_name')]";
	private String titleXpath = ".//*[contains(@id,'prop_cm_title')]";
	
	// Added Dynamic content object xpath for NALS
	private String folioPrefixXpath = ".//*[contains(@id,'prop_cpnals_folioPrefix') and @name='prop_cpnals_folioPrefix']";
	private String folioStyleXpath = ".//*[contains(@id,'prop_cpnals_folioStyle') and @name='prop_cpnals_folioStyle']";
	private String folioStartXpath = ".//*[contains(@id,'prop_cpnals_folioStart') and @name='prop_cpnals_folioStart']";
	private String tocIncFromXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeFrom') and @name='prop_cpnals_tocIncludeFrom']";
	private String tocIncToXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeTo') and @name='prop_cpnals_tocIncludeTo']";
	private String stdCorrelationTableTypeXpath = ".//*[contains(@id,'cpnals_standardCorrelationTableType') and @name='prop_cpnals_standardCorrelationTableType']";
	private String contentObjectAggregationXpath = ".//*[contains(@id,'prop_cpnals_contentObjectAggregationType') and @name='prop_cpnals_contentObjectAggregationType']";
		
	//Added rumbaprogramnameXpath as part of NALS 
	private String rumbaprogramnameXpath = ".//*[contains(@id,'prop_cpnals_rumbaProgramName')]";
	private String skillspathXpath = ".//*[contains(@id,'prop_cpnals_skillsPath')]";
	// Added Level Automation,Product Configuration,Program Standards fields Xpath for NALS
	
	//Level Automation fields
	private String levelLable1Xpath =".//*[contains(@id,'levelAutomationCollection-label-1')]";
	private String levelLable2Xpath = ".//*[contains(@id,'levelAutomationCollection-label-2')]";
	private String numberingFormatXpath = ".//*[contains(@id,'levelAutomationCollection-numbering-format-1')]";
	private String startValueXpath = ".//*[contains(@id,'levelAutomationCollection-start-value-1')]";
	private String restartatLevelXpath = ".//*[contains(@id,'levelAutomationCollection-restart-level-2')]";
	private String addLevelButtonXpath = ".//*[contains(@id,'default_prop_cpnals_levelAutomationCollection-add-level-automation\\')]/span";
	private String orientationCheckBoxXpath = ".//*[contains(@id,'default_prop_cpnals_levelAutomationCollection-add-level-automation\\')]/span";
	
	//Product Configuration fields
	private String productTypeXpath = ".//*[contains(@id,'productConfigurationCollection-type-1')]";
	private String addProductButtonXpath = ".//*[contains(@id,'prop_cpnals_productConfigurationCollection-add-product-configuration')]/span";
	
	//Program Standards fields
	private String cmtStandardsXpath = ".//*[contains(@id,'prop_cpnals_cmtStandards')]";
	
	  
	private String descriptionXpath = ".//*[contains(@id,'prop_cm_description')]";
	private String folioprefixXpath = ".//*[contains(@id,'prop_cpnals_folioPrefix')]";
	private String foliostyleXpath = ".//*[contains(@id,'prop_cpnals_folioStyle')]";
	private String foliostartXpath = ".//*[contains(@id,'prop_cpnals_folioStart')]";
	private String tocIncludeFromXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeFrom')]";
	private String tocIncludeToXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeTo')]";
	private String aggregationtypeXpath =".//*[contains(@id,'prop_cpnals_contentObjectAggregationType')]";
	//Added as part of NALS
	private String versionStateDropdownXpath = ".//*[contains(@id,'prop_cpnals_versionState') and @name='-']";
	//Modified as part of NALS Release SOW7 1709_3 Added genres
	private String genresDropdownXpath = ".//*[contains(@id,'prop_cpnals_genres') and @name='-']";
	private String librarydropdownxpath = ".//*[contains(@id,'prop_cpnals_library') and @name='-']";
	private String releaseversionxpath = ".//*[contains(@id,'prop_cpnals_releaseVersion') and @name='-']";
	private String versioncountryDropdownXpath = ".//*[contains(@id,'prop_cpnals_versionCountry') and @name='-']";
	private String versiondistricttextxpath = ".//*[contains(@id,'prop_cpnals_versionDistrict')]";
	private String versionstatementtextxpath = ".//*[contains(@id,'prop_cpnals_versionStatement')]";
	private String downloadrestricationsdropdownxpath = ".//*[contains(@id,'prop_cpnals_downloadRestrictions')]";
	private String courseAbbrevationFieldXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_courseAbbreviation')]";
	private String courseskillpathXpathInCreateObject = ".//input[@name='prop_cpnals_skillsPath']";
	
	private String courserumbaXpathInCreateObject = ".//input[@name='prop_cpnals_rumbaProgramName']";
	private String mediaTypeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_mediaType')]";
	private String contentTypeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_contentType')]";
	private String dynamiccontentTypeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_dynamicContentType')]";
	private String contribSourceDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_contribSource')]";
	private String realizeFileTypeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_realizeFileType')]";
	//private String disciplizeDropdownXpathInCreateObject = "//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_discipline\"]";
	private String disciplizeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_discipline')]";
	private String productTypeDropdownXpathInCreateObject = ".//*[contains(@id,'prop_cpnals_productType')]";
	private String saveBtnXpathInCreateObject = ".//*[contains(@id,'form-submit-button')]";
	private String cancelBtnXpathInCreateObject = ".//*[contains(@id,'form-cancel-button')]";

	private String defaultAspectsLeftContainerXpath = ".//*[contains(@id,'default-aspects-left')]";
	private String addedAspectsListXpath = ".//*[@class='title-right']//following-sibling::div//tr//td//*[@class='name']";
	private String defaultAspectsRightContainerXpath = ".//*[contains(@id,'default-aspects-right')]";
	private String aspectLoadingXpath = ".//*[@class='yui-dt-empty']";

	private String createObjectLabelNamexpath = ".//*[contains(@id,'form-fields')]//label";
	private String createObjectInputXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_LABEL')]/following-sibling::input";
	private String createObjectTextAreaXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'TEXT_AREA_LABEL')]/following-sibling::textarea";
	private String createObjectDropDownXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'DROPDOWN_FIELD_LABEL')]/following-sibling::select";
	private String createObjectChkBoxXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'CHKBOX_LABEL')]/preceding-sibling::input[1]";

	private String createdCollectionObjectsXpath = "//*[@class='yui-dt-data']//h3/*[contains(@id,'alf-id')]";
	private String collectionstatusXpath = "//*[@class='yui-dt-data']//h3/*[contains(text(),'CRAFT')]//ancestor::tr//td//img[@title='Auto-link Errors']";

	private String tempXpathForCreateMenuItemIcon = ".//*[contains(@id,'yui-gen')]/a/*[contains(@style,'CRAFT')]";

	private String tempXpathForCreatedObjects = "//*[@class='yui-dt-data']/tr//h3/span[contains(.,'OBJECT_NAME')]//ancestor::tr/td//img[contains(@src,'OBJECT_TYPE')]";
	private String tempxpathForSeqObject = "//*[@class='yui-dt-data']/tr//h3/span[contains(.,'OBJECT_NAME')]//ancestor::tr/td//img";

	private String tempXpathForDocumentLibTreeViewFolders = ".//*[contains(@id,'default-treeview')]//table/tbody/tr//*[text()='OBJECT_TYPE']";
	private String tempXpathForObjectFolderInTreeView = ".//*[contains(@id,'default-treeview')]//table/tbody/tr//*[text()='OBJECT_TYPE']//ancestor::table/following-sibling::*//*[text()='OBJECT_FOLDER']";
	private String tempXpathForObjectSubfolderInTreeView = ".//*[contains(@id,'default-treeview')]//table/tbody/tr//*[text()='OBJECT_TYPE']//ancestor::table/following-sibling::*//*[text()='OBJECT_FOLDER']//ancestor::table/following-sibling::*//*[text()='OBJECT_SUB_FOLDER']";

	private String tempXpathForLeftTreeViewObjectsInCollectionUI = ".//*[@id='assembly-view-tree-container']//a/*[text()='OBJECT_NAME']//ancestor::a/preceding-sibling::img[contains(@src,'OBJECT_TYPE')]";

	private String tempXpathForSelectedCollectionObjectName = "//*[@class='filename']//*[text()='CRAFT']//ancestor::td";
	private String tempXpathForBrowseActionName = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span[contains(.,'BROWSE_ACTION')]";
	private String tempXpathForBrowseActionNameList = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span";

	private String relationshipViewerXpath = ".//div[@class='panel-body relationships-list-container']/table/tbody/tr/td";
	private String createMenuOptionXpath = ".//*[contains(@id,'default-createContent-menu')]//*[contains(@id,'yui-gen')]/a[@class='yuimenuitemlabel']/span[text()='CRAFT']";

	private String labelForObjectCreationPopUpXapth = ".//label[text()='CRAFT']";

	private String collectionsDocListXpath = ".//*[@class='documents doclist yui-dt']//tbody[@class='yui-dt-data']//h3//span[1]";
	private String relationshipDeleteXpath = ".//div[contains(@id,'_relationships-list-incomingRelations')]/div[1]/div/div/div[contains(@style,'remove-icon')]";
	private String deleteRelationshipOkXpath = ".//button[text()='OK']";
	private String deleteFolderXpath = ".//*[@id='onActionDelete']/a/span[text()='Delete Folder']";
	private String deleteDocumentXpath = ".//*[@id='onActionDelete']/a/span[text()='Delete Document']";
	private String deleteBtnXpath = ".//button[text()='Delete']";

	public String selectedFolderORFileItemXpath = "//*[@class='filename']//*[text()='CRAFT']//ancestor::tr//td[contains(@class,'yui-dt-col-fileName')]";
	private String tempXpathForViewDetailsLink = ".//*[contains(@id,'yuievtautoid')]//span[text()='CRAFT']//ancestor::tr//a/span[contains(.,'MENU')]";
	private String myFilesTablesXpath = ".//*[@class='sticky-wrapper']//*[@id='assembly-view-details-panel']";

	private String editPropTextAreaFieldValueXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_NAME')]/following-sibling::textarea[contains(.,'FIELD_VALUE')]";
	private String editPropTextFieldValueXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_NAME')]/following-sibling::input[contains(@value,'FIELD_VALUE')]";
	private String editPropDropdownFieldValueXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_NAME')]/following-sibling::select[contains(.,'FIELD_VALUE')]";
	private String editPropCheckeChBoxFieldValueXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_NAME')]/preceding-sibling::input[contains(@checked,'checked')]";
	private String editPropUnCheckeChBoxFieldValueXpath = ".//*[contains(@id,'form-fields')]//label[contains(text(),'FIELD_NAME')]/preceding-sibling::input[1][not(contains(@checked,'checked'))]";

	private String allFieldLabelsXpathInEditProp = ".//*[contains(@id,'form-fields')]//label";
	private String acceptedTypeLabelXpath = ".//*[@class='form-field']//label[contains(.,'Accepted Types')]";
	private String acceptedOptionXpath = ".//*[contains(@id,'acceptedTypes-entry')]//option";
	private ArrayList<String> acceptedTypesList = new ArrayList<String>();
	private ArrayList<String> getAcceptedTypesList = new ArrayList<String>();
	private String aspectsCurrentlyAddedXpath = ".//*[@class='title-right']//following-sibling::div//table//tr//td[contains(.,'CRAFT')]";
	private String collectionRelationFilePathXpath = ".//*[@class='relationships-list-container-table']//*[@class='relationships-element-type-table']//div[contains(.,'RELATIONSHIP')]//following-sibling::*//a[contains(.,'FILE_PATH')]";
	private String collectionDOMXpath = ".//*[@class='relationships-list-container-table']";
	private String[] acceptedTypes;

	private String selctFilesToUploadBtnXpath = "//*[contains(@id,'default-file-selection-button-overlay')]/span//*[contains(.,'Select files to upload')]";
	private String uploadInputField = ".//*[contains(@id,'default-file-selection-button-overlay')]/span/input";

	private String childReferencesXapth = ".//*[contains(@id,'relationships-list-outgoingRelations')]//*[text()='Child References']//following-sibling::*/a";
	private String titleTxtAreaXapth = ".//textarea[@name='prop_cm_title']";
	//Modified as part of NALS
	//private String urlImageXapth = ".//td//h3/span[text()='CRAFT']//ancestor::tr//td//div[@class='status']";
	private String urlImageXapth = ".//td//h3/span[text()='CRAFT']";

	private String folderPropValueXpath = ".//span[text()='CRAFT']/following-sibling::*";
	private String externalIdInputXapth = ".//label[text()='CRAFT']/following-sibling::*";
	private String editPropSaveButton = ".//button[text()='Save']";

	private String parentReferencesXpath = ".//*[@class='relationships-incoming']//*[text()='Parent References']//following-sibling::*/a";
	private String containByXpath = ".//*[@class='relationships-incoming']//*[text()='Is Contained By']//following-sibling::*/a";

	private String failureXpathForUpload = ".//*[contains(@style,'visible')]//*[contains(@id,'default-filelist-table')]//*[@class='yui-dt-data']/tr/td//span[contains(.,'Failure: Failed to execute')]";

	private String refObjectXpath = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td[contains(.,'CRAFT')]//ancestor::tr//span";
	private String selectedRefObjectXpath = ".//*[contains(@id,'selectedItems')]//table//tbody//tr//td[contains(.,'CRAFT')]";
	private String childRefOkXpath = ".//*[@type='button' and contains(.,'OK')]";
	private String externalIdXpath = ".//*[contains(@id,'default_prop_cpnals_externalID')]";
	private String refFolderXpath = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[contains(.,'CRAFT')]";
	//Added for NALS
	private String refFolderXpaththumb = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[contains(.,'CRAFT')]";
	private String refFolderXpaththumbFile = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[@title='Add']";
	private String refFolderXpaththumbFilee = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr[2]//td//a[@title='Add']";
	private String childRefFileAvailableCheck = ".//*[contains(@id,'relationships-list-outgoingRelations')]//table//tbody//tr//td////a[@title='/Assets/Thumbnails/AlfrescoJPEGFile.jpg']";
	private String collectionIcon = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[contains(.,'CRAFT')]//ancestor::tr//img";
	private String activePreviewOKXpath = "//*[@id='prompt']//div[@class='ft']//span[@class='first-child']";
	
	//private String activePreviewContentXpath = "//*[@id='prompt']//div[@class='bd']";
	
//	private String activePreviewContentXpath ="//div[contains(text(),'https://usppewip.pearsoncms.com/alfresco')]";
	
	private String activePreviewContentXpath ="//*[@id='prompt']/div[2]/text()";
	
	private String tempSelectFolderChkboxXpath = "//*[contains(@class,'filename')]//*[text()='CRAFT']//ancestor::tr//td//*[@type='checkbox']";
	private String tempXpathForMoreOptionLink = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span[contains(.,'More')]";
	private String uploadedFilesTitlesXpath = "//*[@class='yui-dt-data']//h3/*[contains(@id,'alf-id')]";
	private String tempXpathForMoreSettingsOptLink = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span[contains(.,'MORE_OPT')]";
	public String tempXpathForpublishOptLink = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span[text()='MORE_OPT']";

	private String twisterClosedXpathForLibraryInDocLib = ".//*[@class='treeview filter']/*[contains(@class,'alfresco-twister-closed')]";
	private String twisterOpenXpathForLibraryInDocLib = ".//*[@class='treeview filter']/*[contains(@class,'alfresco-twister-open')]";
	public String errorStatusIcon = "//*[@class='filename']//a[contains(text(),'Course-2016-10-17')]//ancestor::tr//*[@class='status']//img";

	private String searchResultsListXpath = ".//*[@class='documents yui-dt']//tbody[@class='yui-dt-data']//h3//a";
	
	private String leftFoldersListFromShareUiXpath = "//*[contains(@class,'ygtvchildren')]//*[contains(@class,'ygtvitem selected')]//*[contains(@class,'documentDroppable ')]//*[contains(@class,'ygtvcell ')]//span[@class='ygtvlabel']";
	private String leftFoldersListFromCollectionUiXpath = "//*[contains(@id,'assembly-view-node-workspace')]//*[contains(@class,'dynatree-title')]//div[1]";

	private String childReferencesLinkTitleXpath = ".//*[contains(@id,'relationships-list-outgoingRelations')]//a";

	private String commonPageTitleXpath = ".//*[@id='HEADER_TITLE']";

	public String errormsg = "html/body/table/tbody//td[contains(text(),'CRAFT')]";

	// TC_4050
	private String filterxpath = "(//*[contains(text(),'Filter')])[last()-1]";
	private String filtercollectionboxxpath = "//*[contains(text(),'Filter Collection')]";
	public String productcheckbox = ".//*[contains(@id,'productType-checkbox')]";
	public String versioncountry = ".//*[contains(@id,'versionCountry-checkbox')]";
	public String versionstate = ".//*[contains(@id,'versionState-checkbox')]";
	private String filterbuttonxpath = ".//*[contains(@id,'filterResults-ok-button')]";
	private String cancelbuttonxpath = ".//*[contains(@id,'filterResults-cancel-button')]";
	private String primarytoolbar = "(.//*[@class='sticky-wrapper']//*[contains(@id,'collections-primary-toolbar')])[last()-1]";
	private String Filtermsg = "//*[@id='assembly-view-main-toolbar-filter-label']/span";
	private String filtermsglink = "//*[@id='assembly-view-main-toolbar-filter-label']/span/a";
	private String appliedfilter = ".//*[contains(@id,'filterParametersDialog')]";
	private String summaryOKbutton = "//button[contains(text(),'Ok')]";
	private String expandcollapse = "//*[@class='dynatree-expander']";
	private String dragfromxpath = "//*[@class='treeview']/li/ul/li[2]/span";
	private String dragtoxpath = "//*[@class='treeview']/li/ul/li[3]/span";
	private String realizecsvbox = "//*[contains(text(),'Realize Course CSV filtering')]";
	private String printcsvrealize = "//*[contains(text(),'Print CSV filtering')]";
	
	private String detailsrealizecsvbox = "//*[@id=\"onActionObjectGenerateRealizeCsv\"]/a/span";
	private String realizecsvboxOKbutton = "//button[contains(text(),'Ok')]";
	private String realizecsvvaluesxpath = "//select[@name='versionStates']";
	private String programscreenxpath = "//a[@class='dynatree-title' and contains(text(),'Program')]";
	
	private String detailbutton = ".//button[@id='assembly-view-secondary-toolbar-metadata-button-button']";
	private String folderaction = ".//div[@class='doclist']//a[@title='CRAFT']";
	public String rightFoldersListFromCollectionUiXpath = "//*[contains(@class,'filename')]//span[1]";
	private String appliedfiltersxpath = "//*[@class='filterValue']";
	///********************************Added by Sangeetha****************************//
		public String metadataSearchTitleXpath = "//*[@class='set-bordered-panel-body']//span[@class='viewmode-value']";
		//Modified as part of NALS
		private String versionstateProperty = "//*[contains(@id,'versionState-entry')]";
		private String tempPropertyXpath = ".//*[@name='CRAFT']";
		private String tempPropertyXpath1 = "//*[contains(@id,'CRAFT')]";
		
		private String saveButton = "//*[contains(@id,'default-form-submit-button')]";
		private String documentLibraryXpath = ".//*[@id='HEADER_SITE_DOCUMENTLIBRARY_text']/a";
		private String editMetadataViewXpath = ".//*[contains(@id,'edit-metadata-mgr')]/div/h1";
		private String chapter1Xpath ="//*[@title='/Sequence Objects/c/Chapter 1']";
		private String chapter2Xpath ="//*[@title='/Sequence Objects/c/Chapter 2']";
		private String co1Xpath = "/Content Objects/s/Shared Calculator Tool";
		private String co2Xpath = "/Content Objects/s/Shared Lab Tool";
		private String co3Xpath = "/Content Objects/s/Shared Writing Tool";
		private String excludeSelectedStates = "//*[@class='formsCheckBox']";
		private String skillsPath =".//*[@name='prop_cpnals_skillsPath']";
		
		private String BQboarderxpath = ".//div[contains(text(),'CRAFT')]/ancestor::span[@class='highlightedElement highlightedColorCOLOR']";
		private String skillDescXpath = ".//span[@class='mat-button-wrapper']";
		private String skillXpath = ".//*[contains(@value,'CRAFT')]";
		private String plusSymbolXpath = ".//*[@title='Add skill to Aligned Skills']";
		private String alignedSkills = ".//*[text()='Aligned Skills']/parent::div/following-sibling::form//*[contains(@class,'mat-column-skillDesc') and not (contains(@class,'header'))]";
		private String alignSkillsSave = ".//*[text()='Save']";
		private String viewDetailsSkills = ".//*[contains(@title,'SS.')]";
		private String removeSkills = ".//*[@title='Remove aligned skill']";
		
	//sathyaxpath

	private	String collectionSite = "//div[@id='detailed-list-view']";	
	private String thumbSelect = "//div[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_assoc_cpnals_thumbnail-cntrl-itemGroupActions']//button[text()='Select']"; 
	private String assertElexpath = "//h3[@class='item-name']//a[contains(.,'Assets')]";
	private String thumbElexpath = "//h3[@class='item-name']//a[contains(.,'Thumbnails')]";
	private String thumblist = "//h3[@class='item-name']";
	private String cancelXpath = "//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_assoc_cpnals_thumbnail-cntrl-cancel-button']";			
	private String filterTitle = "//div[@class='filterTitle']";
	private String filterValue =  "//div[@class='filterValue']";	
	private String gridThumbnailLink = "//div[@class='form-field']//input[@name='prop_cpnals_gridThumbnailToLink']";
	private String ThumbnailLink = "//div[@class='form-field']//input[@name='prop_cpnals_thumbnailToLink']";
	private String LeveledReaderEleXpath = "//div[@class='set-bordered-panel']//*[contains(text(),'Leveled Readers')]";
	private String leveledReaderListXpath = " (//div[@class='set-bordered-panel-body'])[last()]//label";
	private String LexilePropertyEleXpath = "//*[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_lexile']";			

	
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	public AlfrescoCollectionsPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	// Check 'Edit Collection' option
	public boolean checkEditCollectionOptionInCollectionPage() {
		boolean isDisplayedEditCollectionOption = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editCollectionOptionXpath);
			if (UIHelper.checkForAnElementbyXpath(driver,
					editCollectionOptionXpath)) {
				UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
						driver, editCollectionOptionXpath));
				isDisplayedEditCollectionOption = true;
			} else {
				isDisplayedEditCollectionOption = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedEditCollectionOption;
	}

	// Click on Edit Collection Option button
	public void clickOnEditCollectionButton() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					editCollectionOptionXpath);
			if (UIHelper.checkForAnElementbyXpath(driver,
					editCollectionOptionXpath)) {
				UIHelper.click(driver, editCollectionOptionXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingDocumentsXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Create Button
	public void clickOnCreateButton() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					createContentContainerXpath);
			WebElement createDailogEle = UIHelper.findAnElementbyXpath(driver,
					createContentContainerXpath);
			UIHelper.highlightElement(driver, createDailogEle);
			UIHelper.mouseOveranElement(driver, createDailogEle);

			report.updateTestLog("Click on 'Create' button",
					"User clicked the 'Create' button", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Create Button
	public void clickOnCreateButtonWithoutReportTag() {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.click(driver, createLinkXpath);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Create Menu Items
	public ArrayList<String> getCreateMenuItems() {
		ArrayList<String> createMenuItemsList = new ArrayList<String>();
		try {
			List<WebElement> createMenuItemsEle = UIHelper
					.findListOfElementsbyXpath(createMenuItemsXpath, driver);

			for (WebElement ele : createMenuItemsEle) {
				createMenuItemsList.add(ele.getText().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createMenuItemsList;
	}

	// Click on left side folder from tree view
	public void clickOnLeftSideFolderFromTreeView(String folderName) {
		try {
			String finalXpathForLeftSideFolderFromTreeView = tempXpathForLeftSideFolderFromTreeView
					.replace("CRAFT", folderName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForLeftSideFolderFromTreeView)) {
				UIHelper.click(driver, finalXpathForLeftSideFolderFromTreeView);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingDocumentsXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingDocumentsXpath);

				report.updateTestLog("Click on left side tree item",
						"User clicked the Left side tree item:" + folderName,
						Status.PASS);
			} else {
				report.updateTestLog("Click on left side tree item",
						"User failed to click the Left side tree item:"
								+ folderName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on left side folder from tree view
	public void clickOnLeftSideParentFolderFromTreeView(String folderName) {
		try {
			UIHelper.waitFor(driver);
			String finalXpathForLeftSideFolderFromTreeView = tempXpathForLeftSideParentFolderFromTreeView
					.replace("CRAFT", folderName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForLeftSideFolderFromTreeView)) {
				UIHelper.highlightElement(driver,
						finalXpathForLeftSideFolderFromTreeView);
				UIHelper.click(driver, finalXpathForLeftSideFolderFromTreeView);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingDocumentsXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						loadingDocumentsXpath);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click on left side tree item",
						"User clicked the Left side tree item:" + folderName,
						Status.PASS);
			} else {
				report.updateTestLog("Click on left side tree item",
						"User failed to click the Left side tree item:"
								+ folderName, Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Create Menu item
	public void clickOnCreateMenuItem(String createMenuItemName) {
		try {
			String finalXpathForCreateMenuItemLink = tempXpathForCreateMenuItemLink
					.replace("CRAFT", createMenuItemName);

			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalXpathForCreateMenuItemLink);
			UIHelper.highlightElement(driver, finalXpathForCreateMenuItemLink);

			UIHelper.click(driver, finalXpathForCreateMenuItemLink);
			UIHelper.waitForVisibilityOfEleByXpath(driver, dailogTitleXpath);

			report.updateTestLog("Click on '" + createMenuItemName + "'",
					"User clicked the '" + createMenuItemName
							+ "' from Create menu items", Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Objects for collection program
	public void createBasicCollectionObjectFromCreateMenu(
			String createObjectData) {
		try {
			if (createObjectData.contains(";")) {			
				String splittedObjectData[] = createObjectData.split(";");
				
				for (String fileValues : splittedObjectData) {
					String splittedFileValues[] = fileValues.split(",");					
					if (splittedFileValues != null) {
						
						String objectType = splittedFileValues[0].replace(
								"ObjectType:", "");						
						createCollectionObjectsByBasicData(objectType,
								splittedFileValues);

					}
				}
			} else {				
				String splittedObjectData[] = createObjectData.split(",");

				if (splittedObjectData != null) {
					String objectType = splittedObjectData[0].replace(
							"ObjectType:", "");
					createCollectionObjectsByBasicData(objectType,
							splittedObjectData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFieldValueFromExcelForCreateObjects(
			String splittedFileValues[], String fieldName) {
		String fieldValue = "";
		try {
			for (String fieldNameWithValue : splittedFileValues) {
				if (fieldNameWithValue.contains(fieldName)) {
					fieldValue = fieldNameWithValue.replace(fieldName, "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fieldValue;
	}

	// Create structural object with basic data
	public void createCollectionObjectsByBasicData(String objectType,
			String splittedFileValues[]) {
		try {
			//Added rumba,skillspath,cmtskills as part of NALS
			String name = "", title = "", description = "", courseAbbrevation = "", contentType = "", contribSource = "", realizeFileType = "", discipline = "",rumbaprogramname ="", skillspath = "",mediaType = "",cmtskills = "";
			String productType="",dynamiccontentType="",folioprefix="",foliostyle="",foliostart="",folioSpecial,tocIncludeFrom="",tocIncludeTo="",aggregationtype="";
			if (objectType.equalsIgnoreCase("Course")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				courseAbbrevation = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "CourseAbbrevation:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
			//	Added rumba,skillspath,cmtskills as part of NALS
				rumbaprogramname = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "RUMBA Program:");
				skillspath = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Skills Path:");
				cmtskills = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "CMT Skills Discipline:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForCreateCourseObject(name, title, description,
						courseAbbrevation, discipline, rumbaprogramname,skillspath,cmtskills);//	Added rumba,skillspath,cmtskills as part of NALS
				
				clickOnSaveBtnForSubmitCreateObjectData();
			} else if (objectType.equalsIgnoreCase("Sequence Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				mediaType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "MediaType:");

				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForSequenceObject(name, title, description,
						mediaType, discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Container")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				foliostart=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioStart:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForContainer(name, title, description, discipline,foliostart);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Content Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				contentType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContentType:");
				contribSource = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContribSource:");
				realizeFileType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "RealizeFileType:");
				mediaType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "MediaType:");
				//Added as part of NALS
				folioprefix=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioPrefix:");
				foliostyle=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioStyle:");
				foliostart=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioStart:");				
				tocIncludeFrom=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "TocIncludeFrom:");				
				tocIncludeTo=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "TocIncludeTo:");
				aggregationtype=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ConObjAggregationType:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForContentObject(name, title, description,
						contentType, contribSource, realizeFileType, mediaType,
						discipline,folioprefix,foliostart,foliostyle,tocIncludeFrom,tocIncludeTo,aggregationtype);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Learning Bundle")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");

				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForLearningBundle(name, title, description,
						discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Asset")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				contribSource = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContribSource:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");

				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForAsset(name, title, description, contribSource,
						discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Composite Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				contentType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContentType:");
				contribSource = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContribSource:");
				realizeFileType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "RealizeFileType:");
				mediaType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "MediaType:");

				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForCompositeObject(name, title, description,
						contentType, contribSource, realizeFileType, mediaType,
						discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Dynamic Content Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				System.out.println(name);
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				System.out.println(title);
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				System.out.println(description);
				productType=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ProductType:");
				System.out.println(productType);
				dynamiccontentType=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "DynamicContentType:");
				System.out.println(dynamiccontentType);
				folioSpecial=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioSpecial:");
				System.out.println(folioSpecial);
				folioprefix=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioPrefix:");
				System.out.println(folioprefix);
				foliostyle=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioStyle:");
				System.out.println(foliostyle);
				foliostart=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "FolioStart:");
				System.out.println(foliostart);
				tocIncludeFrom=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "TocIncludeFrom:");
				System.out.println(tocIncludeFrom);
				tocIncludeTo=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "TocIncludeTo:");
				System.out.println(tocIncludeTo);
				aggregationtype=getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ConObjAggregationType:");
				System.out.println(aggregationtype);
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForDynamicContentObject(name, title,
						description, productType,dynamiccontentType,folioSpecial,folioprefix,
						foliostyle, foliostart, tocIncludeFrom, tocIncludeTo);
				clickOnSaveBtnForSubmitCreateObjectData();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Enter field data to create Collection objects
	public ArrayList<String> enterCollectionObjectData(
			String collectionObjectData) {
		ArrayList<String> dataList = new ArrayList<String>();
		try {
			StringTokenizer token = new StringTokenizer(collectionObjectData,
					",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				String title = "";
				String dataType = "";
				String value = "";
				StringTokenizer subToken = new StringTokenizer(docProperty, "-");
				while (subToken.hasMoreElements()) {
					title = subToken.nextToken().trim();
					dataType = subToken.nextToken().trim();
					value = subToken.nextToken().trim();
					dataList.add(title + " " + value);
				}

				String finalInputBoxXpath = "";

				if (dataType.equalsIgnoreCase("d:text")
						|| dataType.equalsIgnoreCase("d:int")) {
					finalInputBoxXpath = createObjectInputXpath.replace(
							"FIELD_LABEL", title);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.clear();
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.sendKeys(value);
				} else if (dataType.equalsIgnoreCase("d:mltext")) {
					finalInputBoxXpath = createObjectTextAreaXpath.replace(
							"TEXT_AREA_LABEL", title);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.clear();
					UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath)
							.sendKeys(value);
				} else if (dataType.equalsIgnoreCase("d:dropdown")) {
					finalInputBoxXpath = createObjectDropDownXpath.replace(
							"DROPDOWN_FIELD_LABEL", title);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
							driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.selectbyVisibleText(driver, finalInputBoxXpath,
							value);
				} else if (dataType.equalsIgnoreCase("d:chkbox")) {
					finalInputBoxXpath = createObjectChkBoxXpath.replace(
							"CHKBOX_LABEL", title);

					if (value.equalsIgnoreCase("Yes")) {
						UIHelper.click(driver, finalInputBoxXpath);
					}

				}
			}
			report.updateTestLog("Enter data for Collection Object",
					"User entered data:" + dataList + " for Collection Object",
					Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	// Create Collections Objects
	//Added rumba,skillspath,cmtskills as part of NALS
	public void createCollectionObjects(String createObjectType, String name,
			String title, String description, String courseAbbrevation,
			String contentType, String contribSource, String realizeFileType,
			String mediaType, String discipline, String rumba,String skillspath,String cmtskills,String productType,String folioSpecial,String dynamiccontentType,String folioprefix,String foliostart,String foliostyle,String tocIncludeFrom,String tocIncludeTo,String aggregationtype) {

		try {
			switch (createObjectType) {
			case "Course":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				//Added rumba,skillspath as part of NALS
				enterBasicDataForCreateCourseObject(name, title, description,
						courseAbbrevation, discipline, rumba,skillspath,cmtskills);
				//Added rumba,skillspath as part of NALS
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Sequence Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForSequenceObject(name, title, description,
						mediaType, discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Container":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForContainer(name, title, description, discipline,foliostart);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Content Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForContentObject(name, title, description,
						contentType, contribSource, realizeFileType, mediaType,
						discipline,folioprefix,foliostart,foliostyle,tocIncludeFrom,tocIncludeTo,aggregationtype);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Learning Bundle":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForLearningBundle(name, title, description,
						discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Asset":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForAsset(name, title, description, contribSource,
						discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Composite Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForCompositeObject(name, title, description,
						contentType, contribSource, realizeFileType, mediaType,
						discipline);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Dynamic Content Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForDynamicContentObject(name, title, description, productType, dynamiccontentType,folioSpecial, folioprefix, foliostyle, foliostart, tocIncludeFrom, tocIncludeTo);
				clickOnSaveBtnForSubmitCreateObjectData();
			default:
				System.out.println("Option not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Added New properties in Alfresco Course Property Form as part of NALS
	
		public void enterDataForCreateCourseObjectNewFields(String levelOne, String levelTwo,
				String numFormat, String startValue, String restartAtLevel,String productType,String cmtStandards) {
		try {
			
			if (!levelOne.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, levelLable1Xpath,
						levelOne);
			}
			
			if (!levelTwo.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, levelLable2Xpath,
						levelTwo);
			}
			
			if (!numFormat.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, numberingFormatXpath, numFormat);
			}
			
			if (!startValue.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,startValueXpath,startValue );
			}
			
			if (!restartAtLevel.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,restartatLevelXpath,restartAtLevel );
			}
			
			if (!productType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,productTypeXpath,productType );
			}
			
			if (!cmtStandards.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,cmtStandardsXpath,cmtStandards );
			}
			
			
report.updateTestLog("Input data for new fields (Level Automation,Product Configuration,Program Standards) in 'Course' object",
					"User able to enter data for 'Course' object"
							+ "<br>Level 1 Lable: " + levelOne + 
							", " + "Level 2 Lable: " + levelTwo+ 
							", <br>Numbering Format: " + numFormat+ 
							", <br>Start Value: " + startValue
							+ "Restart at Level: " + restartAtLevel +
							", <br>Product Type: " + productType +
							", <br>CMT Standards: " + cmtStandards, Status.DONE);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// New Dynamic Content Object Details - Added as a part of NALS

	// Create Course objects from Create menu items - Enter Data in fields
	//Added rumba,skillspath as part of NALS
	public void enterBasicDataForCreateCourseObject(String name, String title,
			String description, String courseAbbrevation, String discipline,String rumba,String skillspath,String cmtskills) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}
			//Added rumba,skillspath as part of NALS
			if (!rumba.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, rumbaprogramnameXpath, rumba);
			}
			
			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}
			//Added rumba,skillspath as part of NALS
			if (!skillspath.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, skillspathXpath, skillspath);
			}
			//Added rumba,skillspath as part of NALS
			//Modified as part of NALS
			/*if (!courseAbbrevation.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						courseskillpathXpathInCreateObject,
						courseAbbrevation);
				//Modified as part of NALS
					UIHelper.sendKeysToAnElementByXpath(driver,
						courserumbaXpathInCreateObject,
						rumba);
			}*/

			if (!discipline.isEmpty()) {
				Select selectBox = new Select(driver.findElement(By.xpath(disciplizeDropdownXpathInCreateObject)));
				selectBox.selectByIndex(2);
				/*UIHelper.selectIntergerValue(driver,
						disciplizeDropdownXpathInCreateObject, 2);*/
			}
			
			if(!cmtskills.isEmpty()) {				
				WebElement select1 = driver.findElement(By.xpath("//*[@id=\"template_x002e_collections-secondary-toolbar_x002e_assembly-view_x0023_default-createObject_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[1]"));
		        WebElement select2 = driver.findElement(By.xpath("//*[@id=\"template_x002e_collections-secondary-toolbar_x002e_assembly-view_x0023_default-createObject_prop_cpnals_cmtSkillsDiscipline-entry\"]/option[2]"));        
		        Actions action = new Actions(driver);		        
		        action.keyDown(Keys.CONTROL).click(select1).click(select2).build().perform();
			}else {
				report.updateTestLog("Input data to create 'Course' object",
						"User unable to enter data for CMT Skills of Course Object"
								+ "<br>Name: " + name + ", " + "Title: " + title
								+ ", <br>" + "Description: " + description
								+ ", <br>Course Abbrevation: " + courseAbbrevation
								+ "<br>Discipline: " + discipline 
								+ "<br>CMT Skills Discipline: " + cmtskills, Status.FAIL);
			}
			report.updateTestLog("Input data to create 'Course' object",
					"User able to enter data for 'Course' object"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ ", <br>Course Abbrevation: " + courseAbbrevation
							+ "Discipline: " + discipline 
							+ "CMT Skills Discipline: " + cmtskills, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Sequence objects from Create menu items
	// Enter Data in fields
	public void enterBasicDataForSequenceObject(String name, String title,
			String description, String mediaType, String discipline) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				Select selectBox = new Select(driver.findElement(By.xpath(disciplizeDropdownXpathInCreateObject)));
				selectBox.selectByIndex(2);
			}

			if (!mediaType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						mediaTypeDropdownXpathInCreateObject, mediaType);
			}

			report.updateTestLog("Input data to create 'Sequence Object'",
					"User able to enter data for 'Sequence object'"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ ", <br>Discipline: " + discipline
							+ "Media Type: " + mediaType, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Container from Create menu items
	// Enter Data in fields
	public void enterBasicDataForContainer(String name, String title,
			String description, String discipline,String foliostart) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				Select selectBox = new Select(driver.findElement(By.xpath(disciplizeDropdownXpathInCreateObject)));
				selectBox.selectByIndex(2);
			}
			
			if (!foliostart.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, foliostartXpath,
						foliostart);
			}

			report.updateTestLog("Input data to create 'Container' object",
					"User able to enter data for 'Container' object"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ "Discipline: " + discipline, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Content objects from Create menu items
	// Enter Data in fields
	public void enterBasicDataForContentObject(String name, String title,
			String description, String contentType, String contribSource,
			String realizeFileType, String mediaType, String discipline,String folioprefix,String foliostart,String foliostyle,String tocIncludeFrom,String tocIncludeTo,String aggregationtype) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				Select selectBox = new Select(driver.findElement(By.xpath(disciplizeDropdownXpathInCreateObject)));
				selectBox.selectByIndex(2);
				/*UIHelper.selectIntergerValue(driver,
						disciplizeDropdownXpathInCreateObject, 2);*/
			}

			if (!contentType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contentTypeDropdownXpathInCreateObject, contentType);
			}

			if (!contribSource.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contribSourceDropdownXpathInCreateObject, contribSource);
			}

			if (!realizeFileType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						realizeFileTypeDropdownXpathInCreateObject,
						realizeFileType);
			}

			if (!mediaType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						mediaTypeDropdownXpathInCreateObject, mediaType);
			}
			//Added as part of NALS
			System.out.println("Content Object");
			if (!folioprefix.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, folioprefixXpath,
						folioprefix);
			}System.out.println("folioprefix:"+folioprefix);
			
			if (!foliostyle.isEmpty()) {
				Select selectBox = new Select(driver.findElement(By.xpath(foliostyleXpath)));
				selectBox.selectByIndex(1);	
			}System.out.println("Content Object");
			
			if (!foliostart.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, foliostartXpath,
						foliostart);
			}System.out.println("foliostart:"+foliostart);
			
			if (!aggregationtype.isEmpty()) {
				Select selectBox = new Select(driver.findElement(By.xpath(aggregationtypeXpath)));
				selectBox.selectByIndex(1);
			}
			System.out.println("Content Object");
			report.updateTestLog("Input data to create 'Content Object'",
					"User able to enter data for 'Content Object'"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ ", <br>" + "Discipline: " + discipline
							+ "Content Type: " + contentType
							+ "<br>Contrib Source: " + contribSource
							+ "Realize File Type: " + realizeFileType
							+ "<br>Media Type: " + mediaType, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Learning Bundle from Create menu items
	// Enter Data in fields
	public void enterBasicDataForLearningBundle(String name, String title,
			String description, String discipline) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			report.updateTestLog(
					"Input data to create 'Learning Bundle' object",
					"User able to enter data for 'Learning Bundle' object"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ "Discipline: " + discipline, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Asset from Create menu items
	// Enter Data in fields
	public void enterBasicDataForAsset(String name, String title,
			String description, String contribSource, String discipline) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!contribSource.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contribSourceDropdownXpathInCreateObject, contribSource);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			report.updateTestLog("Input data to create 'Asset' Object",
					"User able to enter data for 'Asset' object" + "<br>Name: "
							+ name + ", " + "Title: " + title + ", <br>"
							+ "Description: " + description + ", <br>"
							+ "Contrib Source: " + contribSource + ", <br>"
							+ "Discipline: " + discipline, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Composite objects from Create menu items
	// Enter Data in fields
	public void enterBasicDataForCompositeObject(String name, String title,
			String description, String contentType, String contribSource,
			String realizeFileType, String mediaType, String discipline) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!contentType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contentTypeDropdownXpathInCreateObject, contentType);
			}

			if (!contribSource.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contribSourceDropdownXpathInCreateObject, contribSource);
			}

			if (!realizeFileType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						realizeFileTypeDropdownXpathInCreateObject,
						realizeFileType);
			}

			if (!mediaType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						mediaTypeDropdownXpathInCreateObject, mediaType);
			}

			report.updateTestLog("Input data to create 'Composite Object'",
					"User able to enter data for 'Composite Object'"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ ", <br>Discipline: " + discipline
							+ ", <br>Content Type: " + contentType
							+ ", <br>Contrib Source: " + contribSource
							+ ", <br>Realize File Type: " + realizeFileType
							+ "<br>Media Type: " + mediaType, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Save Button
	public void clickOnSaveBtnForSubmitCreateObjectData() {
		try {
			UIHelper.click(driver, saveBtnXpathInCreateObject);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingDocumentsXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on 'Save' button",
					"User clicked the 'Save' button", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Cancel button
	public void clickOnCancelBtnInEditPropPage() {
		try {
			UIHelper.click(driver, cancelBtnXpathInCreateObject);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingDocumentsXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingDocumentsXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingDocumentsXpath);
			report.updateTestLog("Click on 'Cancel' button",
					"User clicked the 'Cancel' button", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Save Button
	public String clickOnSaveBtnAndGetConfirmMsg() {
		String confirmMsgVal = "";
		try {
			UIHelper.click(driver, saveBtnXpathInCreateObject);
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			confirmMsgVal = UIHelper
					.getTextFromWebElement(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingDocumentsXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click on 'Save' button",
					"User clicked the 'Save' button", Status.DONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return confirmMsgVal;
	}

	/**
	 * @author 391543
	 * @param aspectName
	 * @return
	 */
	// To check the aspect added to folder/file
	public boolean isAspectsAdded(String aspectName) {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					defaultAspectsLeftContainerXpath);

			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					aspectLoadingXpath);
			UIHelper.highlightElement(driver, defaultAspectsLeftContainerXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					defaultAspectsRightContainerXpath);
			UIHelper.highlightElement(driver, defaultAspectsRightContainerXpath);
			List<WebElement> aspectsList = UIHelper.findListOfElementsbyXpath(
					addedAspectsListXpath, driver);
			for (WebElement webElement : aspectsList) {
				if (UIHelper.isWebElementDisplayed(webElement)) {
					if (webElement.getText().equalsIgnoreCase(aspectName)) {
						UIHelper.scrollToAnElement(webElement);
						UIHelper.highlightElement(driver, webElement);
						UIHelper.waitFor(driver);
						flag = true;
						break;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// Open Collection Object
	public void openCollectionObject(String collectionObjectName) {
		try {
			List<WebElement> createdObjectTitleEleList = UIHelper
					.findListOfElementsbyXpath(createdCollectionObjectsXpath,
							driver);
			for (WebElement ele : createdObjectTitleEleList) {
				if (ele.getText().equalsIgnoreCase(collectionObjectName)) {
					UIHelper.highlightElement(driver, ele);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", ele);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							loadingDocumentsXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							loadingDocumentsnewXpath);
					UIHelper.waitFor(driver);
					report.updateTestLog("Open collection object",
							"Opened the 'Collection Object' using "
									+ collectionObjectName, Status.DONE);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check image icons for create menu items
	public boolean checkCreateMenuItemsIcons(
			ArrayList<String> createMenuItemsList) {
		boolean isDisplayedIconsForCreateMenuItems = false;
		try {
			for (String createMenuItemName : createMenuItemsList) {
				String finalCreateMenuItemName = createMenuItemName
						.replace(" ", "").toLowerCase().trim();
				String finalXpathForCreateMenuItemIcon = tempXpathForCreateMenuItemIcon
						.replace("CRAFT", finalCreateMenuItemName);

				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForCreateMenuItemIcon)) {
					isDisplayedIconsForCreateMenuItems = true;
				} else {
					isDisplayedIconsForCreateMenuItems = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedIconsForCreateMenuItems;
	}

	// Check image icons for created objects
	public boolean checkImageIconsForCreatedObjects(String createdObjectName,
			String objectType) {
		boolean isDisplayedImageIconsForCreatedObjects = false;
		try {
			String updateObjectTypeWithColon = objectType.replace(" ", ":");
			String finalObjectType = "";
			if (updateObjectTypeWithColon.contains(":")) {
				String splittedObjectType[] = updateObjectTypeWithColon
						.split(":");
				if (splittedObjectType != null && splittedObjectType.length > 1) {
					String objectTypeWordOne = splittedObjectType[0]
							.toLowerCase().trim();
					String objectTypeWordTwo = splittedObjectType[1].trim();
					finalObjectType = objectTypeWordOne + objectTypeWordTwo;
				}
			} else {
				finalObjectType = updateObjectTypeWithColon.toLowerCase()
						.trim();
			}

			String finalXpathForCreatedObjectImageIcon = "";

			if (objectType.equalsIgnoreCase("Asset")) {
				finalXpathForCreatedObjectImageIcon = tempxpathForSeqObject
						.replace("OBJECT_NAME", createdObjectName);
			} else {
				finalXpathForCreatedObjectImageIcon = tempXpathForCreatedObjects
						.replace("OBJECT_TYPE", finalObjectType).replace(
								"OBJECT_NAME", createdObjectName);
			}

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForCreatedObjectImageIcon)) {
				isDisplayedImageIconsForCreatedObjects = true;
			} else {
				isDisplayedImageIconsForCreatedObjects = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedImageIconsForCreatedObjects;
	}

	// Click on Collection object folder from left side tree view in Document
	// Library
	public void clickOnCollectionObjectFromTreeViewInDocLibPag(
			String collectionObjectType, String createdObjectName) {
		try {
			String collectionObjectName = collectionObjectType + "s";
			String firstChar = "" + createdObjectName.charAt(0);
			String finalXpathForDocumentLibTreeViewFolders = tempXpathForDocumentLibTreeViewFolders
					.replace("OBJECT_TYPE", collectionObjectName);
			String finalXpathForObjectFolderInTreeView = tempXpathForObjectFolderInTreeView
					.replace("OBJECT_TYPE", collectionObjectName);
			String finalXpathForObjectSubfolderInTreeView = tempXpathForObjectSubfolderInTreeView
					.replace("OBJECT_TYPE", collectionObjectName);

			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);

			if (UIHelper.checkForAnElementbyXpath(driver,
					twisterClosedXpathForLibraryInDocLib)) {
				UIHelper.click(driver, twisterClosedXpathForLibraryInDocLib);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						twisterOpenXpathForLibraryInDocLib);
				UIHelper.waitFor(driver);
			}

			if (collectionObjectName.equalsIgnoreCase("Assets")) {
				clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);
				finalXpathForObjectFolderInTreeView = finalXpathForObjectFolderInTreeView
						.replace("OBJECT_FOLDER", "Other");
				clickOnCollectionObjectInTreeView(finalXpathForObjectFolderInTreeView);
				finalXpathForObjectSubfolderInTreeView = finalXpathForObjectSubfolderInTreeView
						.replace("OBJECT_FOLDER", "Other").replace(
								"OBJECT_FOLDER", firstChar.toLowerCase());
				clickOnCollectionObjectInTreeView(finalXpathForObjectSubfolderInTreeView);
			} else if (collectionObjectName.equalsIgnoreCase("Courses")) {
				clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);
			} else {
				clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);
				finalXpathForObjectFolderInTreeView = finalXpathForObjectFolderInTreeView
						.replace("OBJECT_FOLDER", firstChar.toLowerCase());
				clickOnCollectionObjectInTreeView(finalXpathForObjectFolderInTreeView);
			}

			report.updateTestLog(
					"Navigate to created collection object from Document Library",
					"User successfully navigated to the created collection Object: "
							+ createdObjectName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Collection object in Document Library tree view
	public void clickOnCollectionObjectInTreeView(
			String finalXpathForDocumentLibTreeViewFolders) {
		try {
			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForDocumentLibTreeViewFolders)) {
				UIHelper.click(driver, finalXpathForDocumentLibTreeViewFolders);
				UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageXpath);
				UIHelper.waitFor(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Check image icons for created objects in Left side tree view collection
	// UI
	public boolean checkImageIconsForLeftSideTreeObjectsInCollectionUI(
			String createdObjectName, String objectType) {
		boolean isDisplayedImageIconsForCreatedObjects = false;
		try {
			String updateObjectTypeWithColon = objectType.replace(" ", ":");
			String finalObjectType = "";
			if (updateObjectTypeWithColon.contains(":")) {
				String splittedObjectType[] = updateObjectTypeWithColon
						.split(":");
				if (splittedObjectType != null && splittedObjectType.length > 1) {
					String objectTypeWordOne = splittedObjectType[0]
							.toLowerCase().trim();
					String objectTypeWordTwo = splittedObjectType[1].trim();
					finalObjectType = objectTypeWordOne + objectTypeWordTwo;
				}
			} else {
				finalObjectType = updateObjectTypeWithColon;
			}
			String finalXpathForCreatedObjectImageIcon = tempXpathForLeftTreeViewObjectsInCollectionUI
					.replace("OBJECT_TYPE", finalObjectType).replace(
							"OBJECT_NAME", createdObjectName);

			if (UIHelper.checkForAnElementbyXpath(driver,
					finalXpathForCreatedObjectImageIcon)) {
				isDisplayedImageIconsForCreatedObjects = true;
			} else {
				isDisplayedImageIconsForCreatedObjects = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedImageIconsForCreatedObjects;
	}

	// Common method for click on Object actions in Collection UI Screen
	public void clickOnBrowseActionsInCollectionUI(String collectionObjectName,
			String browseActionName) {
		try {
			UIHelper.waitFor(driver);
			String finalXpathForSelectedCollectionObjectName = tempXpathForSelectedCollectionObjectName
					.replace("CRAFT", collectionObjectName);
			String finalXpathForBrowseActionLink = tempXpathForBrowseActionName
					.replace("CRAFT", collectionObjectName).replace(
							"BROWSE_ACTION", browseActionName);

			String finalXpathForMoreOption = tempXpathForBrowseActionName
					.replace("CRAFT", collectionObjectName).replace(
							"BROWSE_ACTION", "More");

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(createdCollectionObjectsXpath,
							driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(collectionObjectName)) {
					UIHelper.highlightElement(driver, ele);

					WebElement folderEle = UIHelper.findAnElementbyXpath(
							driver, finalXpathForSelectedCollectionObjectName);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					UIHelper.findAnElementbyXpath(driver,
							finalXpathForMoreOption).click();
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(
							driver, finalXpathForBrowseActionLink);

					UIHelper.highlightElement(driver, moreSettingsEle);
					executor.executeScript("arguments[0].click();",
							moreSettingsEle);
					UIHelper.waitFor(driver);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickonrealizebox() {

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, realizecsvbox);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, realizecsvbox))) {
				report.updateTestLog("Realize CSV filter options is available",
						"Realize CSV filter options is available", Status.DONE);
				String[] values = dataTable.getData("MyFiles",
						"CSVFilteroptions").split("-");
				for (String options : values) {
					UIHelper.selectbyVisibleText(driver, realizecsvvaluesxpath,
							options);
					report.updateTestLog("Click on '" + options, "clicked on"
							+ options, Status.PASS);

				}

				UIHelper.click(driver, realizecsvboxOKbutton);
				UIHelper.waitForPageToLoad(driver);
				if (UIHelper.isWebElementDisplayed(UIHelper
						.findAnElementbyXpath(driver, programscreenxpath))) {
					report.updateTestLog("Click on ok button", "clicked on ok",
							Status.PASS);
				} else {
					report.updateTestLog("Click on ok button",
							"clicked on ok Failed", Status.FAIL);
				}

			} else {
				report.updateTestLog(
						"Realize CSV filter options is available?",
						"Realize CSV filter options is not available",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void clickonDetailsRealizeBox() {

		try {
			
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(
					driver, detailsrealizecsvbox);

			UIHelper.highlightElement(driver, moreSettingsEle);
			executor.executeScript("arguments[0].click();",
					moreSettingsEle);
			UIHelper.waitFor(driver);


			UIHelper.waitForVisibilityOfEleByXpath(driver, realizecsvbox);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, realizecsvbox))) {
				report.updateTestLog("Realize CSV filter options is available",
						"Realize CSV filter options is available", Status.DONE);
				String[] values = dataTable.getData("MyFiles",
						"CSVFilteroptions").split("-");
				for (String options : values) {
					UIHelper.selectbyVisibleText(driver, realizecsvvaluesxpath,
							options);
					report.updateTestLog("Click on '" + options, "clicked on"
							+ options, Status.PASS);

				}

				UIHelper.click(driver, realizecsvboxOKbutton);
				UIHelper.waitForPageToLoad(driver);
				if (UIHelper.isWebElementDisplayed(UIHelper
						.findAnElementbyXpath(driver, programscreenxpath))) {
					report.updateTestLog("Click on ok button", "clicked on ok",
							Status.PASS);
				} else {
					report.updateTestLog("Click on ok button",
							"clicked on ok Failed", Status.FAIL);
				}

			} else {
				report.updateTestLog(
						"Realize CSV filter options is available?",
						"Realize CSV filter options is not available",
						Status.FAIL);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @author 412766
	 * @return
	 */
	public boolean isRelationshipViewerDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					relationshipViewerXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, relationshipViewerXpath))) {
				UIHelper.highlightElement(driver, relationshipViewerXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Relationship viewer",
					"Verify Relationship viewer Failed", Status.FAIL);
		}

		return flag;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isCollectionObjectDisplayed(String objectName) {
		boolean flag = false;
		try {
			String finalCreateMenuOptionXpath = createMenuOptionXpath.replace(
					"CRAFT", objectName);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalCreateMenuOptionXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, finalCreateMenuOptionXpath))) {
				UIHelper.highlightElement(driver, finalCreateMenuOptionXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Collection object",
					"Verify Collection object Failed", Status.FAIL);
		}

		return flag;
	}

	/**
	 * @author 412766
	 * @param objectFieldName
	 * @return
	 */
	public boolean isObjectFieldAvailable(String objectFieldName) {
		boolean flag = false;
		try {
			String finalLabelForObjectCreationPopUpXapth = labelForObjectCreationPopUpXapth
					.replace("CRAFT", objectFieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalLabelForObjectCreationPopUpXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, finalLabelForObjectCreationPopUpXapth))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalLabelForObjectCreationPopUpXapth));
				UIHelper.highlightElement(driver,
						finalLabelForObjectCreationPopUpXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Collection object fields",
					"Verify Collection object fields Failed", Status.FAIL);
		}

		return flag;
	}

	/**
	 * @author 412766
	 */
	public void moveToFirstInCollectionPage() {
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, createLinkXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					createLinkXpath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return
	 */
	public boolean isCreatedObjectAvailable(String fileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By
					.xpath(collectionsDocListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (fileName.equals(ele.getText())) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check file is available",
					"Check file is available Failed", Status.FAIL);
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param objectName
	 */
	public void deleteCollectionObject(String objectName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					relationshipDeleteXpath);
			UIHelper.mouseOverandclickanElement(driver, UIHelper
					.findAnElementbyXpath(driver, relationshipDeleteXpath));
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, deleteRelationshipOkXpath);
			UIHelper.click(driver, deleteRelationshipOkXpath);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteFolderXpath);
			UIHelper.highlightElement(driver, deleteFolderXpath);
			UIHelper.click(driver, deleteFolderXpath);
			UIHelper.waitFor(driver);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, deleteBtnXpath))) {
				UIHelper.highlightElement(driver, deleteBtnXpath);
				report.updateTestLog("Delete object created",
						"Object deleted successfully"
								+ "<br><b> Object File Name : </b>"
								+ objectName, Status.PASS);
				UIHelper.click(driver, deleteBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Delete object created",
						"Object not deleted"
								+ "<br><b> Object File Name : </b>"
								+ objectName, Status.FAIL);
			}

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Delete object created",
					"Delete object created Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param assetName
	 */
	public void deleteCollectionAsset(String assetName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					relationshipDeleteXpath);
			UIHelper.mouseOverandclickanElement(driver, UIHelper
					.findAnElementbyXpath(driver, relationshipDeleteXpath));
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, deleteRelationshipOkXpath);
			UIHelper.click(driver, deleteRelationshipOkXpath);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, deleteDocumentXpath);
			UIHelper.highlightElement(driver, deleteDocumentXpath);
			UIHelper.click(driver, deleteDocumentXpath);
			UIHelper.waitFor(driver);

			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, deleteBtnXpath))) {
				UIHelper.highlightElement(driver, deleteBtnXpath);
				report.updateTestLog("Delete Asset created",
						"Asset deleted successfully"
								+ "<br><b> Asset File Name : </b>" + assetName,
						Status.PASS);
				UIHelper.click(driver, deleteBtnXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
			} else {
				report.updateTestLog("Delete Asset created",
						"Asset not deleted" + "<br><b> Asset File Name : </b>"
								+ assetName, Status.FAIL);
			}

			UIHelper.waitFor(driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Delete object created",
					"Delete object created Failed", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param objectName
	 * @return
	 */
	public AlfrescoMyFilesPage clickOnMouseOverMenu(String objectName,
			String menuType) {
		try {
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath
					.replace("CRAFT", objectName);
			String finalXpathForViewDetailsOptionLink = tempXpathForViewDetailsLink
					.replace("CRAFT", objectName);
			finalXpathForViewDetailsOptionLink = finalXpathForViewDetailsOptionLink
					.replace("MENU", menuType);

			UIHelper.waitForVisibilityOfEleByXpath(driver, myFilesTablesXpath);
			WebElement myFilesTableEle = driver.findElement(By
					.xpath(myFilesTablesXpath));
			UIHelper.highlightElement(driver, myFilesTableEle);
			UIHelper.mouseOveranElement(driver, myFilesTableEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(collectionsDocListXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(objectName)) {
					UIHelper.highlightElement(driver, ele);

					JavascriptExecutor executor = (JavascriptExecutor) driver;

					WebElement folderEle = UIHelper.findAnElementbyXpath(
							driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement viewDetailsEle = UIHelper.findAnElementbyXpath(
							driver, finalXpathForViewDetailsOptionLink);
					UIHelper.highlightElement(driver, viewDetailsEle);
					executor.executeScript("arguments[0].click();",
							viewDetailsEle);
					UIHelper.waitFor(driver);

					report.updateTestLog("Click on Mouse over Menu option",
							"Clicked the '" + menuType
									+ "' option using Folder" + objectName,
							Status.DONE);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new AlfrescoMyFilesPage(scriptHelper);
	}

	// Check the entered field data for Collection objects in Edit Properties
	public boolean checkCollectionObjectDataInEditPropPg(
			String collectionObjectData) {
		boolean isDisplayedEntereFieldData = false;
		try {
			StringTokenizer token = new StringTokenizer(collectionObjectData,
					",");
			while (token.hasMoreElements()) {
				String docProperty = token.nextToken();
				String title = "";
				String dataType = "";
				String value = "";
				StringTokenizer subToken = new StringTokenizer(docProperty, "-");
				System.out.println("subToken " + subToken.toString());
				while (subToken.hasMoreElements()) {
					title = subToken.nextToken().trim();
					dataType = subToken.nextToken().trim();
					value = subToken.nextToken().trim();
				}

				String finalFieldLabelWithValueXpath = "";

				if (dataType.equalsIgnoreCase("d:text")
						|| dataType.equalsIgnoreCase("d:int")) {
					finalFieldLabelWithValueXpath = editPropTextFieldValueXpath
							.replace("FIELD_NAME", title).replace(
									"FIELD_VALUE", value);

					if (UIHelper.checkForAnElementbyXpath(driver,
							finalFieldLabelWithValueXpath)) {
						UIHelper.scrollToAnElement(UIHelper
								.findAnElementbyXpath(driver,
										finalFieldLabelWithValueXpath));
						UIHelper.highlightElement(driver,
								finalFieldLabelWithValueXpath);
						isDisplayedEntereFieldData = true;
					} else {
						isDisplayedEntereFieldData = false;
					}
				} else if (dataType.equalsIgnoreCase("d:mltext")) {
					finalFieldLabelWithValueXpath = editPropTextAreaFieldValueXpath
							.replace("FIELD_NAME", title).replace(
									"FIELD_VALUE", value);

					if (UIHelper.checkForAnElementbyXpath(driver,
							finalFieldLabelWithValueXpath)) {
						UIHelper.scrollToAnElement(UIHelper
								.findAnElementbyXpath(driver,
										finalFieldLabelWithValueXpath));
						UIHelper.highlightElement(driver,
								finalFieldLabelWithValueXpath);
						isDisplayedEntereFieldData = true;
					} else {
						isDisplayedEntereFieldData = false;
					}
				} else if (dataType.equalsIgnoreCase("d:dropdown")) {
					if (value.contains("_")) {
						String splitData[] = value.split("_");
						if (splitData != null) {
							for (String fieldVal : splitData) {
								finalFieldLabelWithValueXpath = editPropDropdownFieldValueXpath
										.replace("FIELD_NAME", title).replace(
												"FIELD_VALUE", fieldVal);
								if (UIHelper.checkForAnElementbyXpath(driver,
										finalFieldLabelWithValueXpath)) {
									UIHelper.scrollToAnElement(UIHelper
											.findAnElementbyXpath(driver,
													finalFieldLabelWithValueXpath));
									UIHelper.highlightElement(driver,
											finalFieldLabelWithValueXpath);
									isDisplayedEntereFieldData = true;
								} else {
									isDisplayedEntereFieldData = false;
								}
							}
						}

					} else {
						finalFieldLabelWithValueXpath = editPropDropdownFieldValueXpath
								.replace("FIELD_NAME", title).replace(
										"FIELD_VALUE", value);

						if (UIHelper.checkForAnElementbyXpath(driver,
								finalFieldLabelWithValueXpath)) {
							UIHelper.scrollToAnElement(UIHelper
									.findAnElementbyXpath(driver,
											finalFieldLabelWithValueXpath));
							UIHelper.highlightElement(driver,
									finalFieldLabelWithValueXpath);
							isDisplayedEntereFieldData = true;
						} else {
							isDisplayedEntereFieldData = false;
						}
					}
				} else if (dataType.equalsIgnoreCase("d:chkbox")) {

					if (value.equalsIgnoreCase("Yes")) {
						finalFieldLabelWithValueXpath = editPropCheckeChBoxFieldValueXpath
								.replace("FIELD_NAME", title);

						if (UIHelper.checkForAnElementbyXpath(driver,
								finalFieldLabelWithValueXpath)) {
							UIHelper.highlightElement(driver,
									finalFieldLabelWithValueXpath);
							isDisplayedEntereFieldData = true;
						} else {
							isDisplayedEntereFieldData = false;
						}
					} else {
						finalFieldLabelWithValueXpath = editPropUnCheckeChBoxFieldValueXpath
								.replace("FIELD_NAME", title);

						if (UIHelper.checkForAnElementbyXpath(driver,
								finalFieldLabelWithValueXpath)) {
							UIHelper.highlightElement(driver,
									finalFieldLabelWithValueXpath);
							isDisplayedEntereFieldData = true;
						} else {
							isDisplayedEntereFieldData = false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedEntereFieldData;
	}

	// Check the entered field labels for Collection objects in Edit Properties
	public ArrayList<String> checkCollectionObjectFieldLabelsInEditPropPg() {
		ArrayList<String> actualFieldLabelNamesListFromEditProp = new ArrayList<String>();

		try {
			List<WebElement> editPropFieldLabelNamesList = UIHelper
					.findListOfElementsbyXpath(allFieldLabelsXpathInEditProp,
							driver);

			for (WebElement ele : editPropFieldLabelNamesList) {
				actualFieldLabelNamesListFromEditProp.add(ele.getText().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actualFieldLabelNamesListFromEditProp;
	}

	/**
	 * @author 412766
	 */
	public void clickCancelForEditProperties() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					cancelBtnXpathInCreateObject);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					cancelBtnXpathInCreateObject));
			UIHelper.highlightElement(driver, cancelBtnXpathInCreateObject);
			report.updateTestLog("Click cancel button", "Clicked successfully",
					Status.DONE);
			UIHelper.click(driver, cancelBtnXpathInCreateObject);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			moveToFirstInCollectionPage();
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Click cancel button",
					"Click cancel button Failed", Status.FAIL);
		}
	}

	/**
	 * @author 391543
	 * @param acceptedTypeValues
	 * @return
	 */
	// To check the selection accepted types in edit properties
	public boolean isAcceptedTypeValuesDisplayed(String acceptedTypeValues) {
		boolean isAcceptedTypeValuesDisplayed = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					acceptedTypeLabelXpath);
			UIHelper.highlightElement(driver, acceptedTypeLabelXpath);
			acceptedTypes = acceptedTypeValues.split(",");

			acceptedTypesList = new ArrayList<String>(
					Arrays.asList(acceptedTypes));

			List<WebElement> getAcceptedTypesListEle = UIHelper
					.findListOfElementsbyXpath(acceptedOptionXpath, driver);

			for (WebElement propertyEle : getAcceptedTypesListEle) {
				getAcceptedTypesList.add(propertyEle.getText());
			}

			if (UIHelper.compareTwoSimilarLists(getAcceptedTypesList,
					acceptedTypesList)) {
				isAcceptedTypeValuesDisplayed = true;

			} else {
				isAcceptedTypeValuesDisplayed = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			isAcceptedTypeValuesDisplayed = false;
		}
		return isAcceptedTypeValuesDisplayed;
	}

	/**
	 * @author 391543
	 * @param aspect
	 * @return
	 */
	public boolean isAspectadded(String aspectName) {
		boolean flag = false;
		try {
			String finalAspectsCurrentlyAddedXpath = aspectsCurrentlyAddedXpath
					.replace("CRAFT", aspectName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalAspectsCurrentlyAddedXpath);
			if (UIHelper.findAnElementbyXpath(driver,
					finalAspectsCurrentlyAddedXpath).isDisplayed()) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// Check collection - relationship file path
	public boolean isCollectionRelationshipPathDisplayed(String relationship,
			String filePath) {
		boolean flag = false;
		try {
			String finalCollectionRelationFilePathXpath = collectionRelationFilePathXpath
					.replace("RELATIONSHIP", relationship).replace("FILE_PATH",
							filePath);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, collectionDOMXpath);
			if (UIHelper.findAnElementbyXpath(driver,
					finalCollectionRelationFilePathXpath).isDisplayed()) {
				UIHelper.highlightElement(driver,
						finalCollectionRelationFilePathXpath);
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

	// Upload a file in Collection Site Page
	public void uploadFileInCollectionSite(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}
			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForUploadMultipleFiles(finalFilePath,
							fileNameVal);
				}
			} else {
				commonMethodForUploadMultipleFiles(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//upload updated file in collection site -Added for NALS
	public void uploadUpdatedFileInCollectionSite(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				//Added for NALS
				finalFilePath = filePath;
			}
			
			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForUploadMultipleFiles(finalFilePath,
							fileNameVal);
				}
			} else {
				commonMethodForUploadMultipleFiles(finalFilePath, fileName);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void commonMethodForUploadMultipleFiles(String filePath,
			String fileName) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By
					.xpath(uploadBtnXpath));

			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				UIHelper.highlightElement(driver, uploadBtnEle);
				driver.findElement(By.xpath(uploadBtnXpath)).click();
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						selctFilesToUploadBtnXpath);
				WebElement uploadInputFieldEle = driver.findElement(By
						.xpath(selctFilesToUploadBtnXpath));
				UIHelper.highlightElement(driver, uploadInputFieldEle);

				WebElement fileInput = driver.findElement(By
						.xpath(uploadInputField));
				fileInput.sendKeys(filePath + fileName);

				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
						messageXpath);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageXpath);

				//UIHelper.waitForPageToLoad(driver);
				//UIHelper.waitFor(driver);

				report.updateTestLog("Upload File", "Upload file using -"
						+ "<b>FilePath:</b>" + filePath + ", "
						+ "<b>FileName:</b>" + fileName, Status.DONE);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Click on Folder from left side tree view
	public void clickOnFolderInLeftsideTreeView(String collectionObjectType) {
		try {
			String collectionObjectName = collectionObjectType + "s";
			String finalXpathForDocumentLibTreeViewFolders = tempXpathForDocumentLibTreeViewFolders
					.replace("OBJECT_TYPE", collectionObjectName);
			clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);

			report.updateTestLog("Open " + collectionObjectType
					+ " folder from 'Library' tree view", "User able to open '"
					+ collectionObjectType + "' folder", Status.PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on Assets folder from left side tree view in Document
	// Library
	public void clickOnAssetsSubFoldersFromTreeViewInDocLibPag(
			String collectionObjectType, String uploadedObjectName,
			String assetType) {
		try {
			String collectionObjectName = collectionObjectType + "s";
			String firstChar = "" + uploadedObjectName.charAt(0);

			String finalXpathForObjectFolderInTreeView = tempXpathForObjectFolderInTreeView
					.replace("OBJECT_TYPE", collectionObjectName).replace(
							"OBJECT_FOLDER", assetType);
			clickOnCollectionObjectInTreeView(finalXpathForObjectFolderInTreeView);

			report.updateTestLog("Open " + assetType + " folder",
					"User able to open '" + assetType
							+ "' folder from left side 'Library' tree view",
					Status.DONE);

			String finalXpathForObjectSubfolderInTreeView = tempXpathForObjectSubfolderInTreeView
					.replace("OBJECT_TYPE", collectionObjectName)
					.replace("OBJECT_FOLDER", assetType)
					.replace("OBJECT_SUB_FOLDER", firstChar.toLowerCase());
			clickOnCollectionObjectInTreeView(finalXpathForObjectSubfolderInTreeView);

			report.updateTestLog("Open " + firstChar.toLowerCase() + " folder",
					"User able to open '" + firstChar.toLowerCase()
							+ "' folder from left side 'Library' tree view",
					Status.DONE);

			/*
			 * report.updateTestLog(
			 * "Navigate to uploded content under Assets from Document Library",
			 * "User successfully navigated to the uploaded content: " +
			 * uploadedObjectName, Status.DONE);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get child references from folder details page
	public ArrayList<String> getChildreferencesFromDocDetailsPage() {
		ArrayList<String> actualChildReferencesList = new ArrayList<String>();
		try {
			List<WebElement> childrefEleList = UIHelper
					.findListOfElementsbyXpath(childReferencesXapth, driver);

			for (WebElement ele : childrefEleList) {
				actualChildReferencesList.add(ele.getAttribute("title").trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actualChildReferencesList;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public String getTitleValueFromAllProp() {
		String titleValue = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, titleTxtAreaXapth);
			titleValue = UIHelper.getTextFromWebElement(driver,
					titleTxtAreaXapth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titleValue;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isURLImageDisplayed(String objectName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			String finalImgURL = urlImageXapth.replace("CRAFT", objectName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalImgURL);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, finalImgURL))) {
				UIHelper.highlightElement(driver, finalImgURL);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param propName
	 * @return String
	 */
	public String getFolderPropValue(String propName) {

		String propValue = "";
		try {
			String finalXpath = folderPropValueXpath.replace("CRAFT", propName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalXpath));
			UIHelper.highlightElement(driver, finalXpath);
			propValue = UIHelper.getTextFromWebElement(driver, finalXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propValue;
	}

	/**
	 * @author 412766
	 * @param propName
	 * @return boolean
	 */
	public boolean editValueInEditProperties(String propName, String propValue) {
		boolean flag = false;
		try {
			String finalXpath = externalIdInputXapth.replace("CRAFT", propName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalXpath);
			if (UIHelper.isWebElementEnabled(UIHelper.findAnElementbyXpath(
					driver, finalXpath))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, finalXpath));
				UIHelper.highlightElement(driver, finalXpath);
				UIHelper.sendKeysToAnElementByXpath(driver, finalXpath,
						propValue);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 */
	public void clickSaveInEditProp() {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, editPropSaveButton);
			UIHelper.click(driver, editPropSaveButton);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get the parent references details
	public String getParenReferenceInfoFromDetailsPage() {
		String parentReference = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					parentReferencesXpath);

			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					parentReferencesXpath));
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, UIHelper.findAnElementbyXpath(
					driver, parentReferencesXpath));
			parentReference = UIHelper.getTextFromWebElement(driver,
					parentReferencesXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parentReference;
	}

	// Check Parent Reference
	public boolean checkParenReferenceInfoFromDetailsPage() {
		boolean isDisplayedParentReference = false;
		try {

			UIHelper.waitFor(driver);
			if (UIHelper
					.checkForAnElementbyXpath(driver, parentReferencesXpath)) {
				isDisplayedParentReference = true;
			} else {
				isDisplayedParentReference = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayedParentReference;
	}

	// Get the containedByReference references details
	public String getIsContainedByInfoFromDetailsPage() {
		String containedByReference = "";
		try {
			UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
					containByXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					containByXpath));
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, containByXpath));
			containedByReference = UIHelper.getTextFromWebElement(driver,
					containByXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return containedByReference;
	}

	// Upload a file in My Files Page
	public void uploadFileForFailure(String filePath, String fileName) {
		try {
			String finalFilePath;
			if (filePath.contains("Automation/Alfresco")) {
				finalFilePath = filePath;
			} else {
				finalFilePath = System.getProperty("user.dir") + filePath;
			}
			if (fileName.contains(",")) {
				String splittedFileNames[] = fileName.split(",");
				for (String fileNameVal : splittedFileNames) {
					commonMethodForUploadFilesForFailure(finalFilePath,
							fileNameVal);
				}
			} else {
				commonMethodForUploadFilesForFailure(finalFilePath, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonMethodForUploadFilesForFailure(String filePath,
			String fileName) {
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, uploadBtnXpath);
			WebElement uploadBtnEle = driver.findElement(By
					.xpath(uploadBtnXpath));

			if (UIHelper.checkForAnWebElement(uploadBtnEle)) {
				UIHelper.highlightElement(driver, uploadBtnEle);
				driver.findElement(By.xpath(uploadBtnXpath)).click();
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						selctFilesToUploadBtnXpath);
				WebElement uploadInputFieldEle = driver.findElement(By
						.xpath(selctFilesToUploadBtnXpath));
				UIHelper.highlightElement(driver, uploadInputFieldEle);

				WebElement fileInput = driver.findElement(By
						.xpath(uploadInputField));
				fileInput.sendKeys(filePath + fileName);

				UIHelper.waitForVisibilityOfEleByXpathForLongTime(driver,
						failureXpathForUpload);
				UIHelper.waitFor(driver);

				if (UIHelper.checkForAnElementbyXpath(driver,
						failureXpathForUpload)) {
					report.updateTestLog("Verify file upload", "File:"
							+ fileName + " failed to upload", Status.PASS);
				} else {
					report.updateTestLog("Verify file upload", "File:"
							+ fileName + "upload successfully", Status.FAIL);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Click on Collection object folder from left side tree view in Document
	// Library
	public boolean checkCreatedObjectNamesFromTreeViewInDocLibPag(
			String createdObjectName, String collectionObjectType) {
		boolean isDisplayedCreatedObjectInLowerCaseLetters = false;
		try {
			String collectionObjectName = collectionObjectType + "s";
			String firstChar = "" + createdObjectName.charAt(0);
			String finalXpathForDocumentLibTreeViewFolders = tempXpathForDocumentLibTreeViewFolders
					.replace("OBJECT_TYPE", collectionObjectName);
			String finalXpathForObjectFolderInTreeView = tempXpathForObjectFolderInTreeView
					.replace("OBJECT_TYPE", collectionObjectName);
			String finalXpathForObjectSubfolderInTreeView = tempXpathForObjectSubfolderInTreeView
					.replace("OBJECT_TYPE", collectionObjectName);
			if (collectionObjectName.equalsIgnoreCase("Assets")) {
				clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);
				finalXpathForObjectFolderInTreeView = finalXpathForObjectFolderInTreeView
						.replace("OBJECT_FOLDER", "Other");
				clickOnCollectionObjectInTreeView(finalXpathForObjectFolderInTreeView);
				finalXpathForObjectSubfolderInTreeView = finalXpathForObjectSubfolderInTreeView
						.replace("OBJECT_FOLDER", "Other").replace(
								"OBJECT_FOLDER", firstChar.toLowerCase());
				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForObjectSubfolderInTreeView)) {
					isDisplayedCreatedObjectInLowerCaseLetters = true;
				} else {
					isDisplayedCreatedObjectInLowerCaseLetters = false;
				}
			} else if (collectionObjectName.equalsIgnoreCase("Courses")) {
				clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);
			} else {
				clickOnCollectionObjectInTreeView(finalXpathForDocumentLibTreeViewFolders);
				finalXpathForObjectFolderInTreeView = finalXpathForObjectFolderInTreeView
						.replace("OBJECT_FOLDER", firstChar.toLowerCase());
				if (UIHelper.checkForAnElementbyXpath(driver,
						finalXpathForObjectFolderInTreeView)) {
					isDisplayedCreatedObjectInLowerCaseLetters = true;
				} else {
					isDisplayedCreatedObjectInLowerCaseLetters = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDisplayedCreatedObjectInLowerCaseLetters;
	}

	// Add child reference
	public void addChildReference(String refFolderName, String objectName) {
		try {
			String finalRefObjectXpath = refObjectXpath.replace("CRAFT",
					objectName.trim());
			String finalSelectedRefObjectXpath = selectedRefObjectXpath
					.replace("CRAFT", objectName.trim());
			String finalrefFolderXpath = refFolderXpath.replace("CRAFT",
					refFolderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
			UIHelper.click(driver, finalrefFolderXpath);
			char finalFolderName = objectName.toLowerCase().charAt(0);
			String finalFolder = Character.toString(finalFolderName);
			String finalFolderXpath = refFolderXpath.replace("CRAFT",
					finalFolder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFolderXpath);
			UIHelper.click(driver, finalFolderXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalRefObjectXpath);
			UIHelper.highlightElement(driver, finalRefObjectXpath);
			UIHelper.click(driver, finalRefObjectXpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					finalSelectedRefObjectXpath);
			UIHelper.highlightElement(driver, finalSelectedRefObjectXpath);
			UIHelper.click(driver, childRefOkXpath);

			report.updateTestLog(
					"Add reference object in Child reference pop up",
					"Reference object is added successfully"
							+ "<br><b>Reference Object Name: </br>"
							+ objectName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Add reference object in Child reference pop up",
					"Failed to add reference object"
							+ "<br><b>Reference Object Name: </br>"
							+ objectName, Status.FAIL);

		}
	}
	
	// Add Thumbnail child reference Added for NALS
		public void addThumbnailChildReference(String refFolderName, String asset, String objectName) {
			try {
			
				//Click on Assets
				String finalrefFolderXpath = refFolderXpath.replace("CRAFT",refFolderName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
				UIHelper.click(driver, finalrefFolderXpath);
				UIHelper.waitFor(driver);
				//Click on Thumbnails
				String finalrefFolderthumbnailXpath = refFolderXpaththumb.replace("CRAFT",asset);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderthumbnailXpath);
				UIHelper.click(driver, finalrefFolderthumbnailXpath);
				UIHelper.waitFor(driver);
							
				UIHelper.waitForVisibilityOfEleByXpath(driver,refFolderXpaththumbFile);
				UIHelper.highlightElement(driver, refFolderXpaththumbFile);
				UIHelper.click(driver, refFolderXpaththumbFile);
				UIHelper.waitFor(driver);
				
				// Click on "OK"
				UIHelper.click(driver, childRefOkXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				report.updateTestLog(
						"Add reference object in Child reference pop up",
						"Reference object is added successfully"
								+ "<br><b>Reference Object Name: </br>"
								+ objectName, Status.DONE);

			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog(
						"Add reference object in Child reference pop up",
						"Failed to add reference object"
								+ "<br><b>Reference Object Name: </br>"
								+ objectName, Status.FAIL);

			}
		}
		
		// Added for Image add child xpath
		
		public void addImageChildReference(String refFolderName, String asset,String folder) {
			try {
			
				//Click on Assets
				String finalrefFolderXpath = refFolderXpath.replace("CRAFT",refFolderName);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
				UIHelper.click(driver, finalrefFolderXpath);
				UIHelper.waitFor(driver);
				//Click on Image
				String finalrefFolderthumbnailXpath = refFolderXpaththumb.replace("CRAFT",asset);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderthumbnailXpath);
				UIHelper.click(driver, finalrefFolderthumbnailXpath);
				UIHelper.waitFor(driver);
				
				//Click on a
				String finalrefFolderaXpath = refFolderXpaththumb.replace("CRAFT",folder);
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderaXpath);
				UIHelper.click(driver, finalrefFolderaXpath);
				UIHelper.waitFor(driver);
							
				UIHelper.waitForVisibilityOfEleByXpath(driver,refFolderXpaththumbFile);
				UIHelper.highlightElement(driver, refFolderXpaththumbFile);
				UIHelper.click(driver, refFolderXpaththumbFile);
				UIHelper.waitFor(driver);
				
				UIHelper.waitForVisibilityOfEleByXpath(driver,refFolderXpaththumbFilee);
				UIHelper.highlightElement(driver, refFolderXpaththumbFilee);
				UIHelper.click(driver, refFolderXpaththumbFilee);
				UIHelper.waitFor(driver);
				
				// Click on "OK"
				UIHelper.click(driver, childRefOkXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				report.updateTestLog(
						"Add reference object in Child reference pop up",
						"Reference object is added successfully"
								+ "<br><b>Reference Object Name: </br>"
								, Status.DONE);

			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog(
						"Add reference object in Child reference pop up",
						"Failed to add reference object"
								+ "<br><b>Reference Object Name: </br>"
								, Status.FAIL);

			}
		}
		
		
		// Add Project Files child reference Added for NALS
				public void addProjectFilesChildReference(String refFolderName) {
					try {
					
						//Click on Project Files
						String finalrefFolderXpath = refFolderXpath.replace("CRAFT",refFolderName);
						UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
						UIHelper.click(driver, finalrefFolderXpath);
						UIHelper.waitFor(driver);
						// Click on the file								
						UIHelper.waitForVisibilityOfEleByXpath(driver,refFolderXpaththumbFile);
						UIHelper.highlightElement(driver, refFolderXpaththumbFile);
						UIHelper.click(driver, refFolderXpaththumbFile);
						UIHelper.waitFor(driver);
											
						// Click on "OK"
						UIHelper.click(driver, childRefOkXpath);
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);
						
						report.updateTestLog(
								"Add reference object in Child reference pop up",
								"Reference object is added successfully"
										+ "<br><b>Reference Object Name: </br>"
										, Status.DONE);

					} catch (Exception e) {
						e.printStackTrace();
						report.updateTestLog(
								"Add reference object in Child reference pop up",
								"Failed to add reference object"
										+ "<br><b>Reference Object Name: </br>"
										, Status.FAIL);

					}
				}

	// Get External ID
	public int getExternalId() {
		int externalId = 0;
		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, externalIdXpath);
			String externalIdText = UIHelper
					.findAnElementbyXpath(driver, externalIdXpath)
					.getAttribute("value").replace("L", "");
			externalId = Integer.parseInt(externalIdText);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return externalId;
	}

	/**
	 * @author 412766
	 * @return
	 */
	public boolean isPopUpDisplayed() {
		boolean flag = false;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, messageXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, messageXpath))) {
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(
						driver, messageXpath));
				UIHelper.highlightElement(driver, messageXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @author 412766
	 * @param folderName
	 */
	public void clickDeleteButton(String folderName) {
		try {
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, deleteBtnXpath);
			report.updateTestLog("Delete Bucket folder",
					"Bucket folder deleted successfully"
							+ "<br><b> Deleted Folder Name: </b>" + folderName,
					Status.DONE);
			UIHelper.click(driver, deleteBtnXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickOnSaveBtn() {
		try {
			UIHelper.highlightElement(driver, saveBtnXpathInCreateObject);
			UIHelper.click(driver, saveBtnXpathInCreateObject);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Click Save Button", "Clicked successfully",
					Status.DONE);
			UIHelper.click(driver, deleteBtnXpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add child reference
	public void childRefIcon(String refFolderName, String objectName) {
		try {
			String finalRefObjectXpath = refObjectXpath.replace("CRAFT",
					objectName.trim());
			String finalSelectedRefObjectXpath = selectedRefObjectXpath
					.replace("CRAFT", objectName.trim());
			String finalrefFolderXpath = refFolderXpath.replace("CRAFT",
					refFolderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
			UIHelper.click(driver, finalrefFolderXpath);
			char finalFolderName = objectName.toLowerCase().charAt(0);
			String finalFolder = Character.toString(finalFolderName);
			String finalFolderXpath = refFolderXpath.replace("CRAFT",
					finalFolder);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalFolderXpath);
			UIHelper.click(driver, finalFolderXpath);
			// UIHelper.waitForVisibilitAyOfEleByXpath(driver,
			// finalRefObjectXpath);

			String collectionIconXpath = collectionIcon.replace("CRAFT",
					objectName.trim());
			UIHelper.highlightElement(driver, collectionIconXpath);

			report.updateTestLog(
					"Add reference object in Child reference pop up",
					"Reference object is added successfully"
							+ "<br><b>Reference Object Name: </br>"
							+ objectName, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog(
					"Add reference object in Child reference pop up",
					"Failed to add reference object"
							+ "<br><b>Reference Object Name: </br>"
							+ objectName, Status.FAIL);

		}
	}

	// Click on 'More Settings' option link for file or Folder
	public void clickOnMoreSetting(String fileOrFolderName) {
		try {
			String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath
					.replace("CRAFT", fileOrFolderName);
			String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath
					.replace("CRAFT", fileOrFolderName);
			String finalXpathForMoreOptionLink = tempXpathForMoreOptionLink
					.replace("CRAFT", fileOrFolderName);

			/*
			 * UIHelper.waitForVisibilityOfEleByXpath(driver,
			 * myFilesTablesXpath); WebElement myFilesTableEle =
			 * driver.findElement(By .xpath(myFilesTablesXpath));
			 * UIHelper.highlightElement(driver, myFilesTableEle);
			 * UIHelper.mouseOveranElement(driver, myFilesTableEle);
			 */

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
					// UIHelper.scrollToAnElement(ele);
					UIHelper.highlightElement(driver, ele);
					WebElement chkboxElement = UIHelper.findAnElementbyXpath(
							driver, finalSelectFolderChkboxXpath);

					UIHelper.highlightElement(driver, chkboxElement);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();",
							chkboxElement);
					UIHelper.waitFor(driver);

					WebElement folderEle = UIHelper.findAnElementbyXpath(
							driver, finalselectedFolderORFileItemXpath);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(
							driver, finalXpathForMoreOptionLink);
					UIHelper.highlightElement(driver, moreSettingsEle);
					report.updateTestLog(
							"Click on 'More Settings' Link Option",
							"User successfully clicked the <b> 'More Settings'</b> Option using "
									+ fileOrFolderName, Status.PASS);
					executor.executeScript("arguments[0].click();",
							moreSettingsEle);
					UIHelper.waitFor(driver);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Click on More settings option
	public void commonMethodForClickOnMoreSettingsOption(
			String fileOrfolderName, String moreSettingsOptName) {
		try {
			clickMoreSettingsOptionOnly(fileOrfolderName, moreSettingsOptName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 */
	public void clickMoreSettingsOptionOnly(String fileOrfolderName,
			String moreSettingsOptName) {
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink
					.replace("CRAFT", fileOrfolderName).replace("MORE_OPT",
							moreSettingsOptName);
			UIHelper.waitFor(driver);
			WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(
					driver, finalXpathForCopyToFolderLink);
			UIHelper.highlightElement(driver, moreSettingsOptElement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();",
					moreSettingsOptElement);
			UIHelper.waitFor(driver);
			UIHelper.highlightElement(driver, summaryOKbutton);
			UIHelper.waitFor(driver);
			UIHelper.click(driver, summaryOKbutton);
			report.updateTestLog("Click on " + moreSettingsOptName,
					"User able to click the " + moreSettingsOptName,
					Status.PASS);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 412766
	 * @param fileOrfolderName
	 * @param moreSettingsOptName
	 * @return boolean
	 */
	public boolean checkMoreSettingsOption(String fileOrfolderName,
			String moreSettingsOptName) {
		boolean flag = false;
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink
					.replace("CRAFT", fileOrfolderName).replace("MORE_OPT",
							moreSettingsOptName);

			WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(
					driver, finalXpathForCopyToFolderLink);
			if (UIHelper.isWebElementDisplayed(moreSettingsOptElement)) {
				UIHelper.highlightElement(driver, moreSettingsOptElement);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Common method for check on Object actions in Collection UI Screen
	public void checkOnBrowseActionsInCollectionUI(String collectionObjectName,
			String browseActions) {
		ArrayList<String> actualActionsList = new ArrayList<String>();
		ArrayList<String> expectedActionsList = new ArrayList<String>();
		try {
			String finalXpathForSelectedCollectionObjectName = tempXpathForSelectedCollectionObjectName
					.replace("CRAFT", collectionObjectName);
			String finalXpathForBrowseActionLink = tempXpathForBrowseActionNameList
					.replace("CRAFT", collectionObjectName);

			String finalXpathForMoreOption = tempXpathForBrowseActionName
					.replace("CRAFT", collectionObjectName).replace(
							"BROWSE_ACTION", "More");

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(createdCollectionObjectsXpath,
							driver);

			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				if (ele.getText().equalsIgnoreCase(collectionObjectName)) {
					UIHelper.highlightElement(driver, ele);

					WebElement folderEle = UIHelper.findAnElementbyXpath(
							driver, finalXpathForSelectedCollectionObjectName);
					UIHelper.highlightElement(driver, folderEle);
					UIHelper.mouseOveranElement(driver, folderEle);

					UIHelper.findAnElementbyXpath(driver,
							finalXpathForMoreOption).click();

					List<WebElement> actualActions = UIHelper
							.findListOfElementsbyXpath(
									finalXpathForBrowseActionLink, driver);

					for (WebElement actualAction : actualActions) {
						actualActionsList.add(actualAction.getText());
					}
					String[] expectedActions = browseActions.split(",");
					for (String expectedAction : expectedActions) {
						expectedActionsList.add(expectedAction);
					}

					if (UIHelper.compareTwoDiffSizeOfLists(actualActionsList,
							expectedActionsList)) {

						report.updateTestLog("Verify More actions list",
								"All the options displayed successfully"
										+ "<br><b>Expected Actions: </b></br>"
										+ expectedActionsList
										+ "<br><b>Actual Actions: </b></br>"
										+ actualActionsList, Status.PASS);
					} else {

						report.updateTestLog("Verify More actions list",
								"All the options are not displayed"
										+ "<br><b>Expected Actions: </b></br>"
										+ expectedActionsList
										+ "<br><b>Actual Actions: </b></br>"
										+ actualActionsList, Status.FAIL);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify More actions list",
					"Failed to display more acions", Status.FAIL);
		}
	}

	/**
	 * @author 412766
	 * @param fileName
	 * @return boolean
	 */
	public boolean isFileIsAvailable(String fileName) {
		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			List<WebElement> searchResultsListEle = driver.findElements(By
					.xpath(searchResultsListXpath));
			for (WebElement ele : searchResultsListEle) {
				if (ele.getText().contains(fileName)) {
					UIHelper.highlightElement(driver, ele);
					UIHelper.scrollToAnElement(ele);
					flag = true;
					break;

				}
			}
		} catch (Exception e) {
			report.updateTestLog("Check file is available",
					"Check file is available Failed", Status.FAIL);
		}
		return flag;
	}

	public void verifyRelationshipViewerOnAllObjectsInCollectionUi() {
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		ArrayList<String> foldersListFromRightPanInCollectionUi = new ArrayList<String>();
		foldersListFromRightPanInCollectionUi = getFoldersFromRightPanInCollectionUi();
		for (String folderName : foldersListFromRightPanInCollectionUi) {
			collectionPg.clickOnBrowseActionsInCollectionUI(folderName,
					"View Details");

			if (collectionPg.isRelationshipViewerDisplayed()) {

				if (collectionPg.isCollectionReferenceDisplayed()) {

					report.updateTestLog("Verify Relationship viewer",
							"Relationship viewer displayed successfully",
							Status.PASS);
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageXpath);

				} else {

					report.updateTestLog("Verify Relationship viewer",
							"Relationship viewer not displayed", Status.FAIL);
					driver.navigate().back();
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageXpath);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
							messageXpath);

				}

			} else {
				report.updateTestLog("Verify Relationship viewer",
						"Relationship viewer not displayed", Status.FAIL);
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageXpath);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
						messageXpath);
			}

			UIHelper.waitFor(driver);

		}

	}

	public boolean isCollectionReferenceDisplayed() {

		boolean flag = false;
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					relationshipViewerXpath);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, parentReferencesXpath))) {
				UIHelper.highlightElement(driver, parentReferencesXpath);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Relationship viewer",
					"Verify Relationship viewer Failed", Status.FAIL);
		}

		return flag;
	}

	public void verifyRelationshipViewerOnAllObjectsInShareUI() {

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		ArrayList<String> foldersListFromRightTreeInShareUi = new ArrayList<String>();
		foldersListFromRightTreeInShareUi = getFoldersFromRightPanInShareUi();
		for (String folderName : foldersListFromRightTreeInShareUi) {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightFoldersListFromShareUiXpath);
			sitesPage.clickOnViewDetails(folderName);

			if (isRelationshipViewerDisplayed()) {

				report.updateTestLog("Verify Relationship viewer",
						"Relationship viewer displayed successfully",
						Status.PASS);
				driver.navigate().back();

			} else {
				report.updateTestLog("Verify Relationship viewer",
						"Relationship viewer not displayed", Status.FAIL);
				driver.navigate().back();

			}

		}

	}

	public void verifyRelationshipViewerOnAllObjectsInShareUINegative() {

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		ArrayList<String> foldersFromRightPanInShareUi = new ArrayList<String>();
		foldersFromRightPanInShareUi = getFoldersFromRightPanInShareUi();
		for (String folderName : foldersFromRightPanInShareUi) {
			sitesPage.clickOnViewDetails(folderName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					relationshipViewerXpath);
			if (!isRelationshipViewerDisplayed()) {

				report.updateTestLog("Verify Relationship viewer",
						"Relationship viewer not  displayed ", Status.PASS);

			} else {
				report.updateTestLog("Verify Relationship viewer",
						"Relationship viewer  displayed", Status.FAIL);
			}
			driver.navigate().back();
		}

		sitesPage = null;
	}

	public ArrayList<String> getFoldersFromLeftTreeInShareUi() {
		ArrayList<String> foldersListFromLeftTreeInShareUi = new ArrayList<String>();
		StringBuffer listOfFolders = new StringBuffer();
		int i = 1;
		try {
			List<WebElement> foldersFromLeftTreeInShareUiEle = UIHelper
					.findListOfElementsbyXpath(leftFoldersListFromShareUiXpath,
							driver);
			UIHelper.highlightElement(driver, leftFoldersListFromShareUiXpath);
			foldersFromLeftTreeInShareUiEle.remove(0);
			for (WebElement ele : foldersFromLeftTreeInShareUiEle) {
				foldersListFromLeftTreeInShareUi.add(ele.getText().trim());
			}

			for (String folderName : foldersListFromLeftTreeInShareUi) {
				listOfFolders.append(i++ + "." + folderName + ",\n");
			}

			report.updateTestLog("Verify bucketed folders from share UI ",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify bucketed folders from share UI",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.FAIL);
			e.printStackTrace();
		}
		return foldersListFromLeftTreeInShareUi;

	}

	public ArrayList<String> getFoldersFromLeftTreeInCollectionUi() {
		ArrayList<String> foldersListFromLeftTreeInCollectionUi = new ArrayList<String>();
		StringBuffer listOfFolders = new StringBuffer();
		int i = 1;
		try {
			List<WebElement> foldersFromLeftTreeIncollectionUiEle = UIHelper
					.findListOfElementsbyXpath(
							leftFoldersListFromCollectionUiXpath, driver);
			UIHelper.highlightElement(driver,
					leftFoldersListFromCollectionUiXpath);
			foldersFromLeftTreeIncollectionUiEle.remove(0);
			for (WebElement ele : foldersFromLeftTreeIncollectionUiEle) {
				foldersListFromLeftTreeInCollectionUi.add(ele.getText().trim());
			}

			for (String folderName : foldersListFromLeftTreeInCollectionUi) {
				listOfFolders.append(i++ + "." + folderName + ",\n");
			}

			report.updateTestLog("Verify bucketed folders from share UI ",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify bucketed folders from share UI",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.FAIL);
			e.printStackTrace();
		}
		return foldersListFromLeftTreeInCollectionUi;

	}

	

	public ArrayList<String> getFoldersFromRightPanInCollectionUi() {
		ArrayList<String> foldersListFromRightPanInCollectionUi = new ArrayList<String>();
		StringBuffer listOfFolders = new StringBuffer();
		int i = 1;

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightFoldersListFromCollectionUiXpath);
			List<WebElement> foldersFromRightIncollectionUiEle = UIHelper
					.findListOfElementsbyXpath(
							rightFoldersListFromCollectionUiXpath, driver);
			UIHelper.highlightElement(driver,
					rightFoldersListFromCollectionUiXpath);
			for (WebElement ele : foldersFromRightIncollectionUiEle) {
				foldersListFromRightPanInCollectionUi.add(ele.getText().trim());
			}

			for (String folderName : foldersListFromRightPanInCollectionUi) {
				listOfFolders.append(i++ + "." + folderName + ",\n");
			}

			report.updateTestLog("Verify bucketed folders from Collection UI ",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify bucketed folders from Collection UI",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.FAIL);
			e.printStackTrace();
		}
		return foldersListFromRightPanInCollectionUi;

	}

	public String rightFoldersListFromShareUiXpath = "//*[contains(@class,'documents ')]//*[contains(@class,'yui-dt-data')]//tr[contains(@class,'yui-dt-rec')]//td//h3//a";

	public ArrayList<String> getFoldersFromRightPanInShareUi() {
		ArrayList<String> foldersListFromRightPanInShareUi = new ArrayList<String>();
		StringBuffer listOfFolders = new StringBuffer();
		int i = 1;
		try {
			List<WebElement> foldersFromRightInShareUiEle = UIHelper
					.findListOfElementsbyXpath(
							rightFoldersListFromShareUiXpath, driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightFoldersListFromShareUiXpath);
			UIHelper.highlightElement(driver, rightFoldersListFromShareUiXpath);

			for (WebElement ele : foldersFromRightInShareUiEle) {
				foldersListFromRightPanInShareUi.add(ele.getText().trim());
			}

			for (String folderName : foldersListFromRightPanInShareUi) {
				listOfFolders.append(i++ + "." + folderName + ",\n");
			}

			report.updateTestLog("Verify bucketed folders from share UI ",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.PASS);

		} catch (Exception e) {
			report.updateTestLog("Verify bucketed folders from share UI",
					"Folders:" + listOfFolders + " are exist, Total:" + (i - 1)
							+ "are Available by default", Status.FAIL);
			e.printStackTrace();
		}
		return foldersListFromRightPanInShareUi;

	}

	public boolean createBasicCollectionObjectFromCreateMenuNegative(
			String createObjectData) {

		boolean flag = false;
		try {
			if (createObjectData.contains(";")) {
				String splittedObjectData[] = createObjectData.split(";");
				for (String fileValues : splittedObjectData) {
					String splittedFileValues[] = fileValues.split(",");

					if (splittedFileValues != null) {
						String objectType = splittedFileValues[0].replace(
								"ObjectType:", "");

						createCollectionObjectsByBasicData(objectType,
								splittedFileValues);

					}
				}
			} else {

				String splittedObjectData[] = createObjectData.split(",");

				if (splittedObjectData != null) {
					String objectType = splittedObjectData[0].replace(
							"ObjectType:", "");

					createCollectionObjectsByBasicData(objectType,
							splittedObjectData);

				}
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	public void checkMoreSettingsOptionAndCkick(String fileOrfolderName,
			String moreSettingsOptName) {
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink
					.replace("CRAFT", fileOrfolderName).replace("MORE_OPT",
							moreSettingsOptName);

			WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(
					driver, finalXpathForCopyToFolderLink);
			if (UIHelper.isWebElementDisplayed(moreSettingsOptElement)) {
				UIHelper.highlightElement(driver, moreSettingsOptElement);
				UIHelper.clickanElement(moreSettingsOptElement);
			} else {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String editPropertyfieldListXpath = ".//*[contains(@class,'form-fields')]//*[contains(@for,'template_x002e_detailed-list')]";

	public void verifyQuickEditPropertiesDialogBoxFieldsForCollectionObjects() {
		ArrayList<String> actualFieldsList = null;
		String FilePath = System.getProperty("user.dir")
				+ "/src/test/resources/AppTestData/MyFiles/UploadFiles";
		String file = "CollectionsObjectQuickEditFieldList.csv";
		actualFieldsList = new CSVUtil().readLinesOfDataFromCSVFile(FilePath
				+ "/" + file);

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		ArrayList<String> folderList = new ArrayList<>();
		folderList = getFoldersFromRightPanInCollectionUi();
		try {
			for (String Folder : folderList) {
				List<WebElement> editPropertyfieldWebElementList = null;
				ArrayList<String> expectedFieldsList = new ArrayList<>();

				clickOnMoreSetting(Folder);
				commonMethodForClickOnMoreSettingsOption(Folder,
						"Edit Properties");
				UIHelper.waitFor(driver);
				editPropertyfieldWebElementList = UIHelper
						.findListOfElementsbyXpath(editPropertyfieldListXpath,
								driver);
				UIHelper.highlightElement(driver, editPropertyfieldListXpath);
				UIHelper.waitFor(driver);
				expectedFieldsList.add(Folder);
				for (WebElement webElement : editPropertyfieldWebElementList) {
					expectedFieldsList.add(webElement.getText());

				}
				String editPropertyfieldStr = expectedFieldsList.toString();
				// System.out.println(editPropertyfieldStr);

				ArrayList<String> csvList = new ArrayList<>();
				for (String string : actualFieldsList) {
					csvList.add(string);
					String csvStr = csvList.get(0);
					// System.out.println(csvStr);

					if (editPropertyfieldStr.equalsIgnoreCase(csvStr)) {
						// System.out.println("inside if");
						// System.out.println("1 " + csvList);
						// System.out.println("2 " + expectedFieldsList);
						report.updateTestLog("Expected fields " + csvList,
								" actual fields " + expectedFieldsList
										+ " For: " + Folder, Status.PASS);
					}

					csvList.clear();

				}

				clickOnCancelBtnInEditPropPage();

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("", "", Status.FAIL);
		}

	}

	private String fullEditPropertyfieldListXpath = ".//*[contains(@class,'form-fields')]//*[contains(@for,'template_x002e_edit-metadata_x002e_edit')]";

	private String allProperties = ".//a[contains(text(),'All Properties...')]";

	public void verifyFullEditPropertiesDialogBoxFieldsForCollectionObjects() {

		ArrayList<String> actualFieldsList = null;
		String FilePath = System.getProperty("user.dir")
				+ "/src/test/resources/AppTestData/MyFiles/UploadFiles";
		String file = "CollectionsObjectFullEditFieldList.csv";
		actualFieldsList = new CSVUtil().readLinesOfDataFromCSVFile(FilePath
				+ "/" + file);

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		ArrayList<String> folderList = new ArrayList<>();
		folderList = getFoldersFromRightPanInCollectionUi();
		try {
			for (String Folder : folderList) {
				List<WebElement> editPropertyfieldWebElementList = null;
				ArrayList<String> expectedFieldsList = new ArrayList<>();

				clickOnMoreSetting(Folder);
				commonMethodForClickOnMoreSettingsOption(Folder,
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				editPropertyfieldWebElementList = UIHelper
						.findListOfElementsbyXpath(
								fullEditPropertyfieldListXpath, driver);
				UIHelper.highlightElement(driver,
						fullEditPropertyfieldListXpath);
				UIHelper.waitFor(driver);
				expectedFieldsList.add(Folder);
				for (WebElement webElement : editPropertyfieldWebElementList) {
					expectedFieldsList.add(webElement.getText());

				}
				String editPropertyfieldStr = expectedFieldsList.toString();
				// System.out.println(editPropertyfieldStr);

				ArrayList<String> csvList = new ArrayList<>();
				for (String string : actualFieldsList) {
					csvList.add(string);
					String csvStr = csvList.get(0);
					// System.out.println(csvStr);

					if (editPropertyfieldStr.equalsIgnoreCase(csvStr)) {

						report.updateTestLog("Expected fields " + csvList,
								" actual fields " + expectedFieldsList
										+ " For: " + Folder, Status.PASS);
					}

					csvList.clear();

				}

				clickOnCancelBtnInEditPropPage();

			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("", "", Status.FAIL);
		}

	}

	private String createObjectItelListXpath = ".//div[contains(@class,'bd')]//ul[contains(@class,'first-of-type')]//a//span[contains(@class,'cpnals')]";
	private String CreatefieldListXpath = ".//*[contains(@class,'form-fields')]//*[contains(@for,'template_x002e_collections')]";

	public void verifyCreateDialogBoxFieldsForCollectionObjects() {

		ArrayList<String> actualFieldsList = null;
		String FilePath = System.getProperty("user.dir")
				+ "/src/test/resources/AppTestData/MyFiles/UploadFiles";
		String file = "CollectionsObjectCreateFieldList.csv";
		actualFieldsList = new CSVUtil().readLinesOfDataFromCSVFile(FilePath
				+ "/" + file);

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		clickOnCreateButton();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		List<WebElement> createObjectItelElementList = UIHelper
				.findListOfElementsbyXpath(createObjectItelListXpath, driver);
		ArrayList<String> createObjectIteltList = new ArrayList<>();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		for (WebElement webElement : createObjectItelElementList) {
			createObjectIteltList.add(webElement.getText());
		}

		clickOnCreateButton();
		try {
			for (String stringCreateObject : createObjectIteltList) {
				// System.out.println(stringCreateObject);

				clickOnCreateButton();
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				clickOnCreateMenuItem(stringCreateObject);
				UIHelper.waitFor(driver);
				List<WebElement> CreatefieldElementList = UIHelper
						.findListOfElementsbyXpath(CreatefieldListXpath, driver);
				UIHelper.waitFor(driver);
				ArrayList<String> CreatefieldList = new ArrayList<>();
				CreatefieldList.add(stringCreateObject);
				for (WebElement webElement : CreatefieldElementList) {
					CreatefieldList.add(webElement.getText());

				}
				// System.out.println(CreatefieldList);
				String CreatefieldSrt = CreatefieldList.toString();
				// System.out.println(CreatefieldSrt);

				ArrayList<String> csvList = new ArrayList<>();
				for (String string : actualFieldsList) {
					csvList.add(string);
					String csvStr = csvList.get(0);
					// System.out.println(csvStr);

					if (CreatefieldSrt.equalsIgnoreCase(csvStr)) {
						System.out.println("inside if");
						System.out.println("1 " + csvList);
						System.out.println("2 " + CreatefieldList);
						report.updateTestLog("Expected fields " + csvList,
								" actual fields " + CreatefieldList + " For: "
										+ stringCreateObject, Status.PASS);
					}

					csvList.clear();

				}

				clickOnCancelBtnInEditPropPage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("", "", Status.FAIL);
		}

	}

	private String selectSiteDialogBoxXpathForDuplicatAllTo = ".//*[contains(@class,'yui-panel-container yui-dialog shadow')]//*";
	private String selectSiteDropDownXpathForDuplicatAllTo = ".//select[contains(@name,'sites')]";
	private String selectSiteDropDownOptionXpathForDuplicatAllTo = ".//div[contains(@class,'yui-u')]//select//option[text()='SITE_NAME']";
	private String duplicatAllButton = ".//*[contains(@class,'yui-panel-container yui-dialog shadow')]//span[contains(@class,'yui-button yui-submit-button alf-primary-button')]//button";

	public void mapSiteForDuplicateAllTo(String siteName) {
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				selectSiteDialogBoxXpathForDuplicatAllTo);
		UIHelper.click(driver, selectSiteDropDownXpathForDuplicatAllTo);
		String temp_selectSiteDropDownOptionXpathForDuplicatAllTo = selectSiteDropDownOptionXpathForDuplicatAllTo
				.replace("SITE_NAME", siteName);

		UIHelper.findAnElementbyXpath(driver,
				temp_selectSiteDropDownOptionXpathForDuplicatAllTo);
		UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
				temp_selectSiteDropDownOptionXpathForDuplicatAllTo));
		UIHelper.highlightElement(driver,
				temp_selectSiteDropDownOptionXpathForDuplicatAllTo);
		UIHelper.click(driver,
				temp_selectSiteDropDownOptionXpathForDuplicatAllTo);
		UIHelper.click(driver, duplicatAllButton);

	}

	private String parentReferenceButtonXpathInViewDetailsPage = ".//div[contains(@class,'action-set')]//div[contains(@class,'add-parent-references')]//a";
	private String referToObjectsTableXpath = ".//tr[contains(@class,'yui-dt-rec yui-dt')]";
	private String respectiveAddButtonXpath = "	.//tr[contains(@class,'yui-dt-rec yui-dt')]//div[contains(@class,'yui-dt-liner')]//a[text()='CRAFT']//parent::h3//parent::div//parent::td//parent::tr//td[contains(@class,'yui-dt-col-add yui-dt-last')]//a";
	private String respectiveAddButtonXpathToNavigate = ".//tr[contains(@class,'yui-dt-rec yui-dt')]//div[contains(@class,'yui-dt-liner')]//a[text()='CRAFT']";
	private String NavigateUpButton = ".//div[contains(@class,'folder-up')]//button";

	private String okButtonXpath = ".//*[contains(@style,'visible')]//*[contains(@class,'picker')]//button[text()='OK']";

	private String saveButtonXpath = ".//button[text()='Save']";

	private String childReferenceButtonXpathInViewDetailsPage = ".//div[contains(@class,'action-set')]//div[contains(@class,'add-child-references')]//a";

	public void addReferenceInViewDetailsPageInShareUi(String referenceType,
			String referToObject) {
		String[] referToObjectAtringArray;
		String referToVal = null;

		if (referenceType.contains("Parent")) {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					parentReferenceButtonXpathInViewDetailsPage);
			UIHelper.highlightElement(driver,
					parentReferenceButtonXpathInViewDetailsPage);
			UIHelper.click(driver, parentReferenceButtonXpathInViewDetailsPage);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					referToObjectsTableXpath);

			referToObjectAtringArray = referToObject.split("/");

			for (String referToValStr : referToObjectAtringArray) {

				// System.out.println(referToValStr);
				String temp_respectiveAddButtonXpathToNavigate = respectiveAddButtonXpathToNavigate
						.replace("CRAFT", referToValStr);
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						temp_respectiveAddButtonXpathToNavigate);
				UIHelper.highlightElement(driver,
						temp_respectiveAddButtonXpathToNavigate);
				UIHelper.click(driver, temp_respectiveAddButtonXpathToNavigate);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				referToVal = referToValStr;
			}

			UIHelper.click(driver, NavigateUpButton);
			String temp_respectiveAddButtonXpath = respectiveAddButtonXpath
					.replace("CRAFT", referToVal);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					temp_respectiveAddButtonXpath);
			UIHelper.highlightElement(driver, temp_respectiveAddButtonXpath);
			UIHelper.click(driver, temp_respectiveAddButtonXpath);

			UIHelper.waitForVisibilityOfEleByXpath(driver, okButtonXpath);
			UIHelper.highlightElement(driver, okButtonXpath);
			UIHelper.click(driver, okButtonXpath);
			UIHelper.waitFor(driver);

		}

	}

	public void addReferenceInInCollectionUi(String referenceType,
			String referToObject) {
		String[] referToObjectAtringArray;
		String referToVal = null;

		UIHelper.waitForVisibilityOfEleByXpath(driver, referToObjectsTableXpath);

		referToObjectAtringArray = referToObject.split("/");

		for (String referToValStr : referToObjectAtringArray) {

			// System.out.println(referToValStr);
			if(referToValStr.contains(".jpg") || referToValStr.contains(".json")) {
				break;
			}else {
			String temp_respectiveAddButtonXpathToNavigate = respectiveAddButtonXpathToNavigate
					.replace("CRAFT", referToValStr);
			//System.out.println("temp_respectiveAddButtonXpathToNavigate"+temp_respectiveAddButtonXpathToNavigate);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					temp_respectiveAddButtonXpathToNavigate);
			UIHelper.highlightElement(driver,
					temp_respectiveAddButtonXpathToNavigate);
			UIHelper.click(driver, temp_respectiveAddButtonXpathToNavigate);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			referToVal = referToValStr;
			//System.out.println("referToVal"+referToVal);
			}
		}

		/*UIHelper.click(driver, NavigateUpButton);
		String temp_respectiveAddButtonXpath = respectiveAddButtonXpath
				.replace("CRAFT", referToVal);
		System.out.println("temp_respectiveAddButtonXpath"+temp_respectiveAddButtonXpath);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				temp_respectiveAddButtonXpath);
		UIHelper.highlightElement(driver, temp_respectiveAddButtonXpath);
		UIHelper.click(driver, temp_respectiveAddButtonXpath);*/
		
		String temp_respectiveAddButtonXpath = "//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[@title='Add']";
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				temp_respectiveAddButtonXpath);
		UIHelper.highlightElement(driver, temp_respectiveAddButtonXpath);
		UIHelper.click(driver, temp_respectiveAddButtonXpath);

		UIHelper.waitForVisibilityOfEleByXpath(driver, okButtonXpath);
		UIHelper.highlightElement(driver, okButtonXpath);
		UIHelper.click(driver, okButtonXpath);
		UIHelper.waitFor(driver);

	}

	
	private String selectButton = ".//div[contains(@class,'form-field')]//label[text()='CRAFT']//parent::div//button";
	private String addButtonXpath = ".//tr[contains(@class,'yui-dt-rec yui-dt')]//div[contains(@class,'yui-dt-liner')]//h3[text()='CRAFT']//parent::h3//parent::div//parent::td//parent::tr//td[contains(@class,'yui-dt-col-add')]//a";
	private String collectionUiRighPanTableXpath = "	.//tbody[@class='yui-dt-data']//tr";

	public void addThumbnailAndGridThumbnail() {
		String[] referToObjectAtringArray;
		String referToObject = "Assets/Image/a";

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				collectionUiRighPanTableXpath);

		ArrayList<String> folderList = new ArrayList<>();
		folderList = getFoldersFromRightPanInCollectionUi();

		System.out.println("inside method" + folderList);

		for (String Folder : folderList) {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					collectionUiRighPanTableXpath);

			if (!Folder.contains("Asset") && !Folder.contains("Course")
					&& !Folder.contains("Container")) {

				clickOnMoreSetting(Folder);
				commonMethodForClickOnMoreSettingsOption(Folder,
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);

				UIHelper.click(driver,
						selectButton.replace("CRAFT", "Thumbnail:"));
				UIHelper.waitFor(driver);

				referToObjectAtringArray = referToObject.split("/");

				for (String referToValStr : referToObjectAtringArray) {

					System.out.println(referToValStr);
					String temp_respectiveAddButtonXpathToNavigate = respectiveAddButtonXpathToNavigate
							.replace("CRAFT", referToValStr);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							temp_respectiveAddButtonXpathToNavigate);
					UIHelper.highlightElement(driver,
							temp_respectiveAddButtonXpathToNavigate);
					UIHelper.click(driver,
							temp_respectiveAddButtonXpathToNavigate);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
				}

				UIHelper.highlightElement(driver,
						addButtonXpath.replace("CRAFT", "AlfTum.jpg"));
				UIHelper.waitFor(driver);

				UIHelper.click(driver,
						addButtonXpath.replace("CRAFT", "AlfTum.jpg"));
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, okButtonXpath);

				UIHelper.click(driver, okButtonXpath);

				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				UIHelper.click(driver,
						selectButton.replace("CRAFT", "Grid Thumbnail:"));
				UIHelper.waitFor(driver);

				for (String referToValStr : referToObjectAtringArray) {

					System.out.println(referToValStr);
					String temp_respectiveAddButtonXpathToNavigate = respectiveAddButtonXpathToNavigate
							.replace("CRAFT", referToValStr);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							temp_respectiveAddButtonXpathToNavigate);
					UIHelper.highlightElement(driver,
							temp_respectiveAddButtonXpathToNavigate);
					UIHelper.click(driver,
							temp_respectiveAddButtonXpathToNavigate);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
				}

				UIHelper.click(driver,
						addButtonXpath.replace("CRAFT", "AlfGrid.jpg"));
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, okButtonXpath);

				UIHelper.click(driver, okButtonXpath);

				UIHelper.waitFor(driver);

				UIHelper.click(driver, saveButtonXpath);

			}
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					collectionUiRighPanTableXpath);
		}

	}

	private String references = ".//table[contains(@class,'relationships-list')]//tbody//tr//div[contains(@class,'relationships-REFERENCE_TYPE')]//div[contains(@class,'relationships-element')]//div[text()='REFERENCE']";
	private String references_temp = ".//div[@class='divHead' and text()='CRAFT']";
	private String rightPanTableDataInCollectionUi = ".//*[contains(@class,'relationships-list-container-table')]//tr//td";
	private String referencesTable = ".//table[contains(@class,'relationships-list')]//tbody//tr//*";

	public void verifyRelationshipViewerOnObjectsInCollectionUi(
			String objectName, String referenceType, String reference) {
		references_temp = references_temp.replace("CRAFT", reference);
		// references_temp = references_temp.replace("REFERENCE", reference);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				rightFoldersListFromCollectionUiXpath);

		clickOnMoreSetting(objectName);
		commonMethodForClickOnMoreSettingsOption(objectName, "View Details");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		UIHelper.waitForVisibilityOfEleByXpath(driver, references_temp);
		UIHelper.highlightElement(driver, references_temp);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
				driver, references_temp))) {
			report.updateTestLog("Expected Reference",
					UIHelper.getTextFromWebElement(driver, references_temp)
							+ " for " + objectName, Status.PASS);
		} else {
			report.updateTestLog("Expected Reference",
					UIHelper.getTextFromWebElement(driver, references_temp)
							+ " for " + objectName, Status.FAIL);

		}
		driver.navigate().back();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				rightFoldersListFromCollectionUiXpath);

	}

	public void verifyRelationshipViewerOnObjectsInCollectionUiNegative(
			String objectName, String referenceType, String reference) {
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				rightFoldersListFromCollectionUiXpath);

		clickOnMoreSetting(objectName);
		commonMethodForClickOnMoreSettingsOption(objectName, "View Details");

		references_temp = references_temp.replace("CRAFT", reference);
		// references_temp = references_temp.replace("REFERENCE", reference);

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		try {
			if (!UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
					driver, references_temp))) {
				report.updateTestLog("Expected Reference",
						UIHelper.getTextFromWebElement(driver, references_temp)
								+ " for " + objectName, Status.PASS);
			} else {
				report.updateTestLog("Expected Reference",
						UIHelper.getTextFromWebElement(driver, references_temp)
								+ " for " + objectName, Status.FAIL);

			}
		} catch (Exception e) {
			report.updateTestLog("Expected Reference",
					UIHelper.getTextFromWebElement(driver, references_temp)
							+ " for " + objectName, Status.PASS);
			e.printStackTrace();
		}
		driver.navigate().back();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				rightFoldersListFromCollectionUiXpath);

	}

	private String importInProgressXpath = ".//*[contains(@class,'yui-dt-data')]//img[@src='/share/res/components/documentlibrary/indicators/import-in-progress-16.gif']";

	public void verifyImportProcessProgress(String fileName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					importInProgressXpath);
			UIHelper.highlightElement(driver, importInProgressXpath);
			report.updateTestLog("import In Progress for ", fileName
					+ " displayed", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("import In Progress for ", fileName
					+ " not displayed", Status.FAIL);
			e.printStackTrace();
		}

	}

	private String errorIndicatorXpath = "	.//*[contains(@class,'yui-dt-data')]//img[@src='/share/res/components/documentlibrary/indicators/validation-error-16.png']";

	public void verifyErrorIndicator(String fileName) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightFoldersListFromShareUiXpath);
			UIHelper.highlightElement(driver, errorIndicatorXpath);
			report.updateTestLog("Error Indicator for ", fileName
					+ " displayed", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("Error Indicator for ", fileName
					+ " not displayed", Status.PASS);
			e.printStackTrace();
		}

	}

	private String rightPanDetailsInViewDetailsPage = "	.//*[contains(@class,'yui-u')]//*[contains(@class,'folder-details')]//*[text()='CRAFT:']//parent::div//span[2]";
	private String rightPanDetailsInViewDetailsPage_temp = "";

	public String verifyObjectDetailsInViewdatailsPage(String objectName,
			String requiredDetail) {
		String externalID = null;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightFoldersListFromCollectionUiXpath);
			clickOnMoreSetting(objectName);
			commonMethodForClickOnMoreSettingsOption(objectName, "View Details");
			rightPanDetailsInViewDetailsPage_temp = rightPanDetailsInViewDetailsPage
					.replace("CRAFT", requiredDetail);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightPanDetailsInViewDetailsPage_temp);
			UIHelper.highlightElement(driver,
					rightPanDetailsInViewDetailsPage_temp);
			externalID = UIHelper.getTextFromWebElement(driver,
					rightPanDetailsInViewDetailsPage_temp);
			report.updateTestLog(requiredDetail + " for given object  ",
					externalID, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog(requiredDetail + "  for given object  ",
					externalID, Status.FAIL);

		}

		driver.navigate().back();
		return externalID;

	}

	public String verifyObjectDetailsInViewdatailsPageInShareUi(
			String objectName, String requiredDetail) {
		String externalID = null;
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightFoldersListFromShareUiXpath);

			clickOnMoreSetting(objectName);
			commonMethodForClickOnMoreSettingsOption(objectName, "View Details");
			rightPanDetailsInViewDetailsPage_temp = rightPanDetailsInViewDetailsPage
					.replace("CRAFT", requiredDetail);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					rightPanDetailsInViewDetailsPage_temp);
			UIHelper.highlightElement(driver,
					rightPanDetailsInViewDetailsPage_temp);
			externalID = UIHelper.getTextFromWebElement(driver,
					rightPanDetailsInViewDetailsPage_temp);
			report.updateTestLog(requiredDetail + " for given object  ",
					externalID, Status.PASS);

		} catch (Exception e) {
			report.updateTestLog(requiredDetail + "  for given object  ",
					externalID, Status.FAIL);

		}

		driver.navigate().back();
		return externalID;

	}

	public String setProg_Titlevalue(String TitleValue) {

		String TitleInput = "";
		try {

			UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, TitleValue);

			UIHelper.highlightElement(driver, TitleValue);
			TitleInput = UIHelper.getTextFromWebElement(driver, TitleValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TitleInput;
	}

	// Get child reference titles from collection object view details page
	public ArrayList<String> getChildRefFromCollObjVieDetailsPg() {
		ArrayList<String> childRefValues = new ArrayList<String>();
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			List<WebElement> childRefEleList = UIHelper
					.findListOfElementsbyXpath(childReferencesLinkTitleXpath,
							driver);

			for (WebElement ele : childRefEleList) {
				String childRefVal = ele.getAttribute("title");
				childRefValues.add(childRefVal);
			}

			Set<String> hs1 = new HashSet<>();
			hs1.addAll(childRefValues);
			childRefValues.clear();
			childRefValues.addAll(hs1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return childRefValues;
	}

	// Navigate back to collection view page
	public void backToCollectionObjPage() {
		try {
			driver.navigate().back();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					loadingDocumentsXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Wait to load collection obj in collection view page
	public void waitForCollectionObjInCollViewPage() {
		try {
			driver.navigate().refresh();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForPageToLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get uploaded file/folder name
	public ArrayList<String> getUploadedFileOrFolderTitle() {

		ArrayList<String> uploadedFilesNameList = new ArrayList<String>();

		try {

			UIHelper.waitForVisibilityOfEleByXpath(driver, commonPageTitleXpath);

			WebElement pageHeaderEle = UIHelper.findAnElementbyXpath(driver,
					commonPageTitleXpath);
			UIHelper.highlightElement(driver, pageHeaderEle);

			List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
					.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);
			for (WebElement ele : uploadedFileOrFolderTitleEleList) {
				uploadedFilesNameList.add(ele.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadedFilesNameList;
	}

	private String moreOptions = ".//*[contains(@id,'yuievtautoid')]//*[text()='CRAFT']//ancestor::tr//a/span";
	private String moreOptionsTemp;

	public ArrayList<String> readMoreOptions(String ObjectName) {
		moreOptionsTemp = moreOptions.replace("CRAFT", ObjectName);
		ArrayList<String> availableMoreOptionsArr = new ArrayList<String>();
		UIHelper.waitForVisibilityOfEleByXpath(driver, moreOptionsTemp);
		UIHelper.highlightElement(driver, moreOptionsTemp);
		List<WebElement> availableMoreOptionsEle = UIHelper
				.findListOfElementsbyXpath(moreOptionsTemp, driver);
		for (WebElement webElement : availableMoreOptionsEle) {
			availableMoreOptionsArr.add(webElement.getText().trim());

		}
		report.updateTestLog("For Collections Object: " + ObjectName,
				"available options are: " + availableMoreOptionsArr,
				Status.DONE);
		return availableMoreOptionsArr;
	}

	private String editPropInputXpath = ".//*[contains(@id,'form-fields')]//label[text()='CRAFT']/following-sibling::input";
	private String editPropInputXpathTemp;

	public void enterCollectionObjectA2LData(String fieldName, String fieldValue) {
		try {
			editPropInputXpathTemp = editPropInputXpath.replace("CRAFT",
					fieldName);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					editPropInputXpathTemp));
			UIHelper.highlightElement(driver, editPropInputXpathTemp);
			UIHelper.click(driver, editPropInputXpathTemp);
			UIHelper.sendKeysToAnElementByXpath(driver, editPropInputXpathTemp,
					fieldValue);

			report.updateTestLog("Enter Link Properties", fieldName + " : "
					+ fieldValue, Status.PASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Enter Link Properties",
					"Enter edit properties failed", Status.FAIL);
		}
	}

	private String TumGTumFileXpathInAllProp = ".//*[contains(text(),'jpg')]";

	public void verifyThumbnailGridThumbnailValuesInAllPropPage(
			String ObjectName) {
		UIHelper.waitFor(driver);
		UIHelper.click(driver, allProperties);
		UIHelper.waitFor(driver);
		UIHelper.highlightElement(driver, TumGTumFileXpathInAllProp);
		ArrayList<String> availableMoreOptionsArr = new ArrayList<String>();
		List<WebElement> availableMoreOptionsEle = UIHelper
				.findListOfElementsbyXpath(TumGTumFileXpathInAllProp, driver);
		for (WebElement webElement : availableMoreOptionsEle) {
			availableMoreOptionsArr
					.add(webElement.getAttribute("title").trim());

		}
		if (!availableMoreOptionsArr.isEmpty()) {
			for (String availableMoreOptionsArrStr : availableMoreOptionsArr) {
				if (availableMoreOptionsArrStr.contains("Tum")) {
					report.updateTestLog("For Collections Object: "
							+ ObjectName, "available file for Thumbnail are: "
							+ availableMoreOptionsArrStr, Status.PASS);

				} else if (availableMoreOptionsArrStr.contains("Grid")) {
					report.updateTestLog("For Collections Object: "
							+ ObjectName,
							"available file for GridThumbnail are: "
									+ availableMoreOptionsArrStr, Status.PASS);

				}

			}
		} else {
			report.updateTestLog("For Collections Object: " + ObjectName,
					"available file for GridThumbnail are: "
							+ availableMoreOptionsArr, Status.FAIL);

		}
		// System.out.println(availableMoreOptionsArr);

		clickOnCancelBtnInEditPropPage();
	}

	private String listOfRef = "/following-sibling::div/a";

	public void verifyListOfReferenveValue(String objectName,
			String referenceType, String reference, String expectedValue) {

		String finalreferences_temp = references_temp.replace("CRAFT",
				reference);
		// references_temp = references_temp.replace("REFERENCE", reference);
		String finallistOfRef = finalreferences_temp + listOfRef;
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				rightFoldersListFromCollectionUiXpath);

		clickOnMoreSetting(objectName);
		commonMethodForClickOnMoreSettingsOption(objectName, "View Details");
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		if (UIHelper.checkForAnElementbyXpath(driver, paneltwisteropen)) {

		} else {

			UIHelper.click(driver, paneltwister);
			UIHelper.waitFor(driver);

		}
		// UIHelper.waitForVisibilityOfEleByXpath(driver, references_temp);
		UIHelper.highlightElement(driver, references_temp);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
				driver, finalreferences_temp))) {
			report.updateTestLog(
					"Expected Reference",
					UIHelper.getTextFromWebElement(driver, finalreferences_temp)
							+ " for " + objectName, Status.PASS);
		} else {
			report.updateTestLog(
					"Expected Reference",
					UIHelper.getTextFromWebElement(driver, finalreferences_temp)
							+ " for " + objectName, Status.FAIL);

		}

		UIHelper.waitFor(driver);
		UIHelper.highlightElement(driver, finallistOfRef);
		UIHelper.waitFor(driver);
		ArrayList<String> listOfRefArr = new ArrayList<String>();
		List<WebElement> list = UIHelper.findListOfElementsbyXpath(
				finallistOfRef, driver);
		for (WebElement webElement : list) {
			listOfRefArr.add(webElement.getAttribute("title")
					.replace("/Assets/Thumbnails/", "").trim());

		}
		/*
		 * System.out.println(listOfRefArr); System.out.println(expectedValue);
		 */

		if (listOfRefArr.get(0).contains(expectedValue)) {
			report.updateTestLog("Expected Reference: " + expectedValue
					+ " is exist in", "" + listOfRefArr, Status.PASS);
		} else {
			report.updateTestLog("Expected Reference: " + expectedValue
					+ " is not exist in", "" + listOfRefArr, Status.FAIL);

		}

		driver.navigate().back();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				rightFoldersListFromCollectionUiXpath);
		listOfRef = "/following-sibling::div/a";

	}

	// verify collection error status icon in the doc lib
	public void collectionObjStatus(String object) {
		try {
			String finalstatusXpath = collectionstatusXpath.replace("CRAFT",
					object);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalstatusXpath);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalstatusXpath));
			UIHelper.highlightElement(driver, finalstatusXpath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Verify error msg from the downloaded error html
	public void errormessage(String value, String error) {
		try {
			String finalerrormsg = errormsg.replace("CRAFT", value);
			
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalerrormsg);
			UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
					finalerrormsg));
			UIHelper.highlightElement(driver, finalerrormsg);
			String finalmsg = UIHelper.getTextFromWebElement(driver,
					finalerrormsg);
			
			if (finalmsg.equalsIgnoreCase(error)) {
				report.updateTestLog("Verify Error Msg" + error,
						"Error msg as expected.<br><b> Error msg : <b>"
								+ finalmsg, Status.PASS);

			} else {
				report.updateTestLog("Verify Error Msg" + error,
						"Error msg not as expected.<br><b> Error msg : <b>"
								+ finalmsg, Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Verify Error Msg",
					"Error msg not as expected.", Status.FAIL);
		}

	}

	public void ClickonFilter(String actionsOnFilters) {
		if (actionsOnFilters.contentEquals("filter")) {
			UIHelper.highlightElement(driver, filterxpath);
			UIHelper.click(driver, filterxpath);
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					filtercollectionboxxpath);
			WebElement Filterbox = driver.findElement(By
					.xpath(filtercollectionboxxpath));
			UIHelper.highlightElement(driver, filtercollectionboxxpath);
			Boolean flag = UIHelper.isWebElementDisplayed(Filterbox);
			if (flag) {
				report.updateTestLog(
						"Click on Filter and navigate to filter collection",
						"Clicked on filter and navigated to filter collection",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Click on cancel and navigate to filter collection",
						"Failed to navigate to filter Collection", Status.FAIL);
			}
		} else if (actionsOnFilters.contentEquals("filterbutton")) {
			UIHelper.highlightElement(driver, filterbuttonxpath);
			UIHelper.click(driver, filterbuttonxpath);
			WebElement toolbar = driver.findElement(By.xpath(primarytoolbar));
			Boolean flag = UIHelper.isWebElementDisplayed(toolbar);
			if (flag) {
				report.updateTestLog(
						"Click on cancel and navigate back to Edit Collection",
						"Clicked on cancel and navigated back to Edit Collection",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Click on cancel and navigate back to Edit Collection",
						"Failed to navigate back to Edit Collection",
						Status.FAIL);
			}
		} else if (actionsOnFilters.contentEquals("cancel")) {
			UIHelper.highlightElement(driver, cancelbuttonxpath);
			UIHelper.click(driver, cancelbuttonxpath);
			WebElement toolbar = driver.findElement(By.xpath(primarytoolbar));
			Boolean flag = UIHelper.isWebElementDisplayed(toolbar);
			if (flag) {
				report.updateTestLog(
						"Click on cancel and navigate back to Edit Collection",
						"Clicked on cancel and navigated back to Edit Collection",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Click on cancel and navigate back to Edit Collection",
						"Failed to navigate back to Edit Collection",
						Status.FAIL);
			}
		} else if (actionsOnFilters.contentEquals("clickhere")) {
			UIHelper.highlightElement(driver, filtermsglink);
			UIHelper.click(driver, filtermsglink);
			WebElement toolbar = driver.findElement(By.xpath(appliedfilter));
			Boolean flag = UIHelper.isWebElementDisplayed(toolbar);
			if (flag) {
				report.updateTestLog("appearance of summary for filters",
						"Applied filter summary is appeared", Status.PASS);
			} else {
				report.updateTestLog("appearance of summary for filters",
						"Applied filter summary is not appeared", Status.FAIL);
			}
		} else if (actionsOnFilters.contentEquals("OKbutton")) {
			UIHelper.highlightElement(driver, summaryOKbutton);
			UIHelper.click(driver, summaryOKbutton);
			WebElement toolbar = driver.findElement(By.xpath(primarytoolbar));
			Boolean flag = UIHelper.isWebElementDisplayed(toolbar);
			if (flag) {
				report.updateTestLog("OK button on Filter collection",
						"clicked on ok button", Status.PASS);
			} else {
				report.updateTestLog("OK button on Filter collection",
						"failed to click on ok button", Status.FAIL);
			}
		}
	}

	public void SelectionOfFilter(String filtername, String filtercolumnname) {

		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, filtercollectionboxxpath);
		UIHelper.highlightElement(driver, filtercollectionboxxpath);
		System.out.println(filtername);

		UIHelper.click(driver, ".//*[contains(@id,'" + filtername
				+ "-checkbox')]");

		if (driver.findElement(
				By.xpath(".//*[contains(@id,'" + filtername + "-checkbox')]"))
				.isSelected()) {
			report.updateTestLog("Check the " + filtername + " Checkbox",
					filtername + " is Checked for the filter" + "<br /><b>"
							+ filtername + "</b>", Status.PASS);
		} else {
			report.updateTestLog("Check the " + filtername + " Checkbox",
					filtername + " is Checked for the filter" + "<br /><b>"
							+ filtername + "</b>", Status.FAIL);
		}

		String[] filterselection = dataTable.getData("Sites", filtercolumnname)
				.split(",");
		Actions ac = new Actions(driver);
		ac.keyDown(Keys.CONTROL);
		for (String listOfObjectsString : filterselection) {
			String local = listOfObjectsString.trim();
			WebElement object = driver.findElement(By.xpath("//option[@value='"
					+ local + "']"));
			UIHelper.highlightElement(driver, object);
			object.click();
			UIHelper.waitFor(driver);
			ac.keyDown(Keys.CONTROL);
		}
		ac.keyUp(Keys.CONTROL);

	}

	public void disableoffiltervalue(String filtername) {

		WebElement product = driver.findElement(By
				.xpath(".//*[contains(@id,'prop_cpnals_" + filtername
						+ "-entry')]"));
		UIHelper.highlightElement(driver, product);
		Boolean flag = UIHelper.isWebElementEnabled(product);
		if (flag) {
			report.updateTestLog(filtername + " should be disable", filtername
					+ " is not disabled", Status.FAIL);
		} else {
			report.updateTestLog(filtername + " should be disable", filtername
					+ " is disabled", Status.PASS);
		}

	}

	public void SummaryreportsofFilter() {

		UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,
				Filtermsg));
		UIHelper.highlightElement(driver, Filtermsg);
		WebElement msg = driver.findElement(By.xpath(Filtermsg));
		String filterselection = dataTable.getData("MyFiles", "FilterMessage");
		System.out.println(filterselection);
		String message = UIHelper.getTextFromWebElement(driver, Filtermsg);
		System.out.println(message);
		boolean flag1 = UIHelper.compareListValue(msg, filterselection);
		if (message.equalsIgnoreCase(filterselection)) {
			report.updateTestLog(message + " message appears", message
					+ " message is appeared", Status.PASS);
		} else {
			report.updateTestLog(message + " message appears", message
					+ " message is not appeared", Status.FAIL);
		}

	}

	public void Summaryfiltervalidation(String filtertype) {
		
		List<WebElement> li = driver.findElements(By.xpath(appliedfiltersxpath));
		li.size();
		ArrayList<String> lil = new ArrayList<String>();
		for(int i=0;i<=li.size()-1;i++){
			String str=li.get(i).getText();
			System.out.println("ui "+str);
			lil.add(str);
			
		}
		System.out.println(filtertype);
		String[] expected=filtertype.split(",");
		
		for(String excp:expected){
			System.out.println("excel "+excp);
		}
		
		ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(expected));
		
		Boolean flag= UIHelper.compareTwoDiffSizeOfLists(lil, stringList);
		
		if (flag) {
			report.updateTestLog("Applied filters:","Applied filters are applied successfully", Status.PASS);
		} else {
			report.updateTestLog("Applied filters:","Applied filters are not applied successfully", Status.FAIL);
			
		}

	}

	public void expandAndCollapse() {

		UIHelper.click(driver, expandcollapse);
		report.updateTestLog("Expand and collapse",
				"Expand and collapse has been done", Status.DONE);
	}

	public void draganddrop() {

		UIHelper.dragAndDrop(driver, dragfromxpath, dragtoxpath);
		report.updateTestLog("drag and drop", "drag and drop has been done",
				Status.DONE);
	}

	public void filtervalidation() {
		try {
			UIHelper.waitForPageToLoad(driver);
			ArrayList<String> listOfObjects1 = new ArrayList<String>();
			UIHelper.waitForPageToLoad(driver);
			listOfObjects1 = getFoldersFromRightPanInCollectionUi();
			UIHelper.waitForPageToLoad(driver);

			for (String listOfObjectsString : listOfObjects1) {
				System.out.println(listOfObjectsString);
				if (listOfObjectsString.contentEquals("Sequence Object 1")
						|| listOfObjectsString
								.contentEquals("Learning Bundle 1")) {
					// report.updateTestLog("Filter is applied",
					// listOfObjectsString+" these contents are filtered",Status.PASS);
					System.out.println(listOfObjectsString);
				} else {
					report.updateTestLog("Filter is applied",
							listOfObjectsString
									+ " , the contents are not filtered",
							Status.FAIL);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletethecreatedfolder() {

		WebElement ele = driver
				.findElement(By
						.xpath(".//*[@class='documents doclist yui-dt']//tbody[@class='yui-dt-data']//tr[2]//h3//span[1]"));
		UIHelper.scrollToAnElement(ele);
		clickOnMoreSetting("Sequence Object 1");
		commonMethodForClickOnMoreSettingsOption("Sequence Object 1",
				"View Details");
		UIHelper.click(driver,
				"//div[@class='relationships-element-type-table']/div/div[2]/div");
		UIHelper.click(driver, "//button[contains(text(),'OK')]");
		driver.navigate().back();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

	}

	public void Editpropertiesfolder() {

		WebElement ele = driver
				.findElement(By
						.xpath(".//*[@class='documents doclist yui-dt']//tbody[@class='yui-dt-data']//tr[2]//h3//span[1]"));
		UIHelper.scrollToAnElement(ele);
		clickOnMoreSetting("Sequence Object 1");
		commonMethodForClickOnMoreSettingsOption("Sequence Object 1",
				"Edit Properties");
		UIHelper.click(
				driver,
				"//div[@class='yui-module yui-overlay yui-panel']/a[@class='container-close' and contains(text(),'Close')]");

	}

	public String CSVFilename(String fileName1) {

		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		if (mediaTransPage.isTransferredFileIsAvailable(fileName1)) {
			report.updateTestLog("Verify CSV file " + fileName1
					+ "is available", fileName1 + " is available", Status.PASS);

		} else {
			report.updateTestLog("Verify CSV file " + fileName1
					+ "is available", fileName1 + " is not available",
					Status.FAIL);
		}
		
		String filename2 = mediaTransPage.RetreiveFilename(fileName1);
		
		int len = filename2.length();
		String name="";
		if(!filename2.contains(".json")){
			name = filename2.substring(0, len - 9);	
		}else {
			name = filename2.substring(0, len - 10);
		}
		System.out.println("filename1"+fileName1);
		System.out.println("name"+name);
		System.out.println("filename2"+filename2);
		String filterselection = dataTable.getData("MyFiles",
				"CSVFilteroptions");
		if (filterselection.contentEquals("NO FILTER")) {
			String expectedname = fileName1;

			if (name.contentEquals(expectedname)) {
				report.updateTestLog("Check the file name of the csv file ",
						"File: " + filename2 + "is generated successfully",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Check the file name of the csv file ",
						"Filename: " + filename2 + "is not generated correctly",
						Status.FAIL);

			}
		} else {
			
			String expectedname = filterselection + "-" + fileName1;
				if (name.contentEquals(expectedname)) {
				report.updateTestLog("Check the file name of the csv file ",
						"File: " + filename2 + "is generated successfully",
						Status.PASS);
			} else {
				report.updateTestLog(
						"Check the file name of the csv file ",
						"Filename: " + filename2 + "is not generated correctly",
						Status.FAIL);

			}
		}
		return filename2;

	}

	public void createBasicCollectionObjectFromCreateMenuforcsv(
			String createObjectData) {
		try {
			if (createObjectData.contains(";")) {
				String splittedObjectData[] = createObjectData.split(";");
				for (String fileValues : splittedObjectData) {
					String splittedFileValues[] = fileValues.split(",");

					if (splittedFileValues != null) {

						String objectType = splittedFileValues[0].replace(
								"ObjectType:", "");

						createCollectionObjectsByBasicDataforcsv(objectType,
								splittedFileValues);

					}
				}
			} else {

				String splittedObjectData[] = createObjectData.split(",");

				if (splittedObjectData != null) {
					String objectType = splittedObjectData[0].replace(
							"ObjectType:", "");

					createCollectionObjectsByBasicDataforcsv(objectType,
							splittedObjectData);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create structural object with basic data
	public void createCollectionObjectsByBasicDataforcsv(String objectType,
			String splittedFileValues[]) {
		try {//Added genres as part of NALS
			String name = "", title = "", description = "", courseAbbrevation = "", contentType = "", contribSource = "", realizeFileType = "", discipline = "", mediaType = "", versionCountry = "", versionState = "", versionDistrict = "", versionStatement = "", downloadRestrictions = "",rumbaprogramname = "", skillpath="",genres="",library="",releaseversion="";

			if (objectType.equalsIgnoreCase("Course")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				rumbaprogramname = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "RUMBA Program:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				skillpath = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Skills Path:");
				/*courseAbbrevation = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "CourseAbbrevation:");*/
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");
				//Added genres as part of NALS	
				genres = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Genres:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);

				//Added rumba,skillspath as part of NALS
				enterBasicDataForCreateCourseObjectforcsv(name, title,rumbaprogramname,
						description,skillpath,courseAbbrevation, discipline,
						versionCountry, versionState, versionDistrict,
						versionStatement,genres);

				clickOnSaveBtnForSubmitCreateObjectData();
			} else if (objectType.equalsIgnoreCase("Sequence Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				mediaType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "MediaType:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");
				/*downloadRestrictions = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "DownloadRestrictions:");
				*/
				library = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Library:");
				releaseversion = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Release Version:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForSequenceObjectforcsv(name, title, description,
						mediaType, discipline, versionCountry, versionState,
						versionDistrict, versionStatement, downloadRestrictions,library,releaseversion);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Container")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");
				genres = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Genres:");
				library = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Library:");

				releaseversion = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Release Version:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForContainerforcsv(name, title,rumbaprogramname, description,skillpath,
						discipline, versionCountry, versionState,
						versionDistrict, versionStatement,genres,library,releaseversion);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Content Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				contentType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContentType:");
				contribSource = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContribSource:");
				realizeFileType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "RealizeFileType:");
				mediaType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "MediaType:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");
				downloadRestrictions = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "DownloadRestrictions:");

				releaseversion = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Release Version:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForContentObjectforcsv(name, title, description,
						contentType, contribSource, realizeFileType, mediaType,
						discipline, versionCountry, versionState,
						versionDistrict, versionStatement, downloadRestrictions,releaseversion);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Learning Bundle")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");

				releaseversion = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Release Version:");
				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForLearningBundleforcsv(name, title, description,
						discipline, versionCountry, versionState,
						versionDistrict, versionStatement,releaseversion);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Asset")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				contribSource = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContribSource:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");

				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForAssetforcsv(name, title, description,
						contribSource, discipline, versionCountry,
						versionState, versionDistrict, versionStatement);
				clickOnSaveBtnForSubmitCreateObjectData();

			} else if (objectType.equalsIgnoreCase("Composite Object")) {

				name = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Name:");
				title = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Title:");
				description = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Description:");
				discipline = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "Discipline:");
				contentType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContentType:");
				contribSource = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "ContribSource:");
				realizeFileType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "RealizeFileType:");
				mediaType = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "MediaType:");
				versionCountry = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionCountry:");
				versionState = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionState:");
				versionDistrict = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionDistrict:");
				versionStatement = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "VersionStatement:");
				downloadRestrictions = getFieldValueFromExcelForCreateObjects(
						splittedFileValues, "DownloadRestrictions:");

				clickOnCreateButton();
				clickOnCreateMenuItem(objectType);
				enterBasicDataForCompositeObjectforcsv(name, title,
						description, contentType, contribSource,
						realizeFileType, mediaType, discipline, versionCountry,
						versionState, versionDistrict, versionStatement,
						downloadRestrictions);
				clickOnSaveBtnForSubmitCreateObjectData();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Collections Objects
	public void createCollectionObjectsforcsv(String createObjectType,
			String name, String title,String rumbaprogramname, String description,String skillpath,
			String courseAbbrevation, String contentType, String contribSource,
			String realizeFileType, String mediaType, String discipline,
			String versionCountry, String versionState, String versionDistrict,
			String versionStatement, String downloadRestrications,String genres,String library,String releaseversion) {

		try {
			switch (createObjectType) {
			case "Course":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForCreateCourseObjectforcsv(name, title,rumbaprogramname,
						description,skillpath, courseAbbrevation, discipline,
						versionCountry, versionState, versionDistrict,
						versionStatement,genres);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Sequence Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForSequenceObjectforcsv(name, title, description,
						mediaType, discipline, versionCountry, versionState,
						versionDistrict, versionStatement,
						downloadRestrications,"","");
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Container":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForContainerforcsv(name, title,rumbaprogramname,description,skillpath,
						discipline, versionCountry, versionState,
						versionDistrict, versionStatement,"","","");//Modified as part of NALS Release SOW7 1709_3
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Content Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForContentObjectforcsv(name, title, description,
						contentType, contribSource, realizeFileType, mediaType,
						discipline, versionCountry, versionState,
						versionDistrict, versionStatement,
						downloadRestrications,"");
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Learning Bundle":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForLearningBundleforcsv(name, title, description,
						discipline, versionCountry, versionState,
						versionDistrict, versionStatement,"");
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Asset":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForAssetforcsv(name, title, description,
						contribSource, discipline, versionCountry,
						versionState, versionDistrict, versionStatement);
				clickOnSaveBtnForSubmitCreateObjectData();

			case "Composite Object":
				clickOnCreateButton();
				clickOnCreateMenuItem(createObjectType);
				enterBasicDataForCompositeObjectforcsv(name, title,
						description, contentType, contribSource,
						realizeFileType, mediaType, discipline, versionCountry,
						versionState, versionDistrict, versionStatement,
						downloadRestrications);
				clickOnSaveBtnForSubmitCreateObjectData();

			default:
				System.out.println("Option not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Course objects from Create menu items
	// Enter Data in fields

	//Added rumba,skillspath as part of NALS
	public void enterBasicDataForCreateCourseObjectforcsv(String name,
			String title,String rumbaprogramname, String description,String skillspath,String courseAbbrevation,
			String discipline, String versionCountry, String versionState,
			String versionDistrict, String versionStatement,String genres) {

		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}
			//Added as part of NALS
			if (!rumbaprogramname.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, rumbaprogramnameXpath, rumbaprogramname);
			}
			//Added as part of NALS
			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}
			//Added as part of NALS 
			if (!skillspath.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, skillspathXpath, skillspath);
			}
			//Added as part of NALS

			if (!courseAbbrevation.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						courseAbbrevationFieldXpathInCreateObject,
						courseAbbrevation);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				String[] states = versionState.split("|");
				for (String versionStateMut : states) {
					UIHelper.selectbyVisibleText(driver,
							versionStateDropdownXpath, versionStateMut);
				}
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}
			
			if (!genres.isEmpty()) {
				String[] genresProp = genres.split("|");
				for (String genresPropValue : genresProp) {
					UIHelper.selectbyVisibleText(driver,
							genresDropdownXpath, genresPropValue);
				}
			}
			
			

			report.updateTestLog("Input data to create 'Course' object",
					"User able to enter data for 'Course' object"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ "<br>rumbaprogramname: " + rumbaprogramname 
							+ ", <br>" + "Description: " + description
							+ "<br>skillspath: " + skillspath 
							+ ", <br>Course Abbrevation: " + courseAbbrevation
							+ "Discipline: " + discipline
							+ "<br> Version State:" + versionState
							+ ", <br> Version Country:" + versionCountry
							+ ", version district:" + versionDistrict
							+ ",version statement:" + versionStatement,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Sequence objects from Create menu items
	// Enter Data in fields
	public void enterBasicDataForSequenceObjectforcsv(String name,
			String title, String description, String mediaType,
			String discipline, String versionCountry, String versionState,
			String versionDistrict, String versionStatement,
			String downloadRestrictions,String library,String releaseversion) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!mediaType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						mediaTypeDropdownXpathInCreateObject, mediaType);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, versionStateDropdownXpath,
						versionState);
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}

			if (!downloadRestrictions.isEmpty()) {

				UIHelper.selectbyVisibleText(driver,
						downloadrestricationsdropdownxpath,
						downloadRestrictions);
			}
			//Modified as part of NALS Added Library Release SOW9 LSALF-2123
			if (!library.isEmpty()) {

				UIHelper.selectbyVisibleText(driver,
						librarydropdownxpath,
						library);
			}

			if (!releaseversion.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						releaseversionxpath, releaseversion);
			}
			report.updateTestLog("Input data to create 'Sequence Object'",
					"User able to enter data for 'Sequence object'"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ ", <br>Discipline: " + discipline
							+ "Media Type: " + mediaType
							+ "<br> Version State:" + versionState
							+ ", <br> Version Country:" + versionCountry
							+ ", version district:" + versionDistrict
							+ ",version statement:" + versionStatement
							+ ", Download Restrications:"
							+ downloadRestrictions, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Container from Create menu items
	// Enter Data in fields
	public void enterBasicDataForContainerforcsv(String name, String title,String rumbaprogramname,
			String description,String skillpath, String discipline, String versionCountry,
			String versionState, String versionDistrict, String versionStatement,String genres,String library,String releaseversion) {
		//Modified as part of NALS Added Genres Release SOW7 1709_3
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, versionStateDropdownXpath,
						versionState);
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}
			//Modified as part of NALS Added Genres Release SOW7 1709_3
			if (!genres.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, genresDropdownXpath,
						genres);
			}
			//Modified as part of NALS Added Library Release SOW9 LSALF-2123
			if (!library.isEmpty()) {

				UIHelper.selectbyVisibleText(driver,
						librarydropdownxpath,
						library);
			}
				
			if (!releaseversion.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						releaseversionxpath, releaseversion);
			}
			report.updateTestLog("Input data to create 'Container' object",
					"User able to enter data for 'Container' object"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ "Discipline: " + discipline
							+ "<br> Version State:" + versionState
							+ ", <br> Version Country:" + versionCountry
							+ ", version district:" + versionDistrict
							+ ",version statement:" + versionStatement
							+ ",genres:" + genres,
					Status.DONE);
			//Modified as part of NALS Added Genres Release SOW7 1709_3
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Content objects from Create menu items
	// Enter Data in fields
	public void enterBasicDataForContentObjectforcsv(String name, String title,
			String description, String contentType, String contribSource,
			String realizeFileType, String mediaType, String discipline,
			String versionCountry, String versionState, String versionDistrict,
			String versionStatement, String downloadRestrictions,String releaseversion) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!contentType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contentTypeDropdownXpathInCreateObject, contentType);
			}

			if (!contribSource.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contribSourceDropdownXpathInCreateObject, contribSource);
			}

			if (!realizeFileType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						realizeFileTypeDropdownXpathInCreateObject,
						realizeFileType);
			}

			if (!mediaType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						mediaTypeDropdownXpathInCreateObject, mediaType);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, versionStateDropdownXpath,
						versionState);
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}

			if (!downloadRestrictions.isEmpty()) {

				UIHelper.selectbyVisibleText(driver,
						downloadrestricationsdropdownxpath,
						downloadRestrictions);
			}
			
			if (!releaseversion.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						releaseversionxpath, releaseversion);
			}

			report.updateTestLog("Input data to create 'Content Object'",
					"User able to enter data for 'Content Object'"
							+ "<br>Name: "
							+ name
							+ ", "
							+ "Title: "
							+ title
							+ ", <br>"
							+ "Description: "
							+ description
							+ ", <br>"
							+ "Discipline: "
							+ discipline
							+ "Content Type: "
							+ contentType
							+ "<br>Contrib Source: "
							+ contribSource
							+ "Realize File Type: "
							+ realizeFileType
							+ "<br>Media Type: "
							+ mediaType
							+ "<br> Version State:"
							+ versionState
							+ ", <br> Version Country:"
							+ versionCountry
							+ ", version district:"
							+ versionDistrict
							+ ",version statement:"
							+ versionStatement
							+ ", Download Restrications:"
							+ downloadRestrictions, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Learning Bundle from Create menu items
	// Enter Data in fields
	public void enterBasicDataForLearningBundleforcsv(String name,
			String title, String description, String discipline,
			String versionCountry, String versionState, String versionDistrict,
			String versionStatement,String releaseversion) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, versionStateDropdownXpath,
						versionState);
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}
			
			if (!releaseversion.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						releaseversionxpath, releaseversion);
			}

			report.updateTestLog(
					"Input data to create 'Learning Bundle' object",
					"User able to enter data for 'Learning Bundle' object"
							+ "<br>Name: " + name + ", " + "Title: " + title
							+ ", <br>" + "Description: " + description
							+ "Discipline: " + discipline
							+ "<br> Version State:" + versionState
							+ ", <br> Version Country:" + versionCountry
							+ ", version district:" + versionDistrict
							+ ",version statement:" + versionStatement,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Asset from Create menu items
	// Enter Data in fields
	public void enterBasicDataForAssetforcsv(String name, String title,
			String description, String contribSource, String discipline,
			String versionCountry, String versionState, String versionDistrict,
			String versionStatement) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!contribSource.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contribSourceDropdownXpathInCreateObject, contribSource);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, versionStateDropdownXpath,
						versionState);
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}
			report.updateTestLog("Input data to create 'Asset' Object",
					"User able to enter data for 'Asset' object" + "<br>Name: "
							+ name + ", " + "Title: " + title + ", <br>"
							+ "Description: " + description + ", <br>"
							+ "Contrib Source: " + contribSource + ", <br>"
							+ "Discipline: " + discipline
							+ "<br> Version State:" + versionState
							+ ", <br> Version Country:" + versionCountry
							+ ", version district:" + versionDistrict
							+ ",version statement:" + versionStatement,
					Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create Composite objects from Create menu items
	// Enter Data in fields
	public void enterBasicDataForCompositeObjectforcsv(String name,
			String title, String description, String contentType,
			String contribSource, String realizeFileType, String mediaType,
			String discipline, String versionCountry, String versionState,
			String versionDistrict, String versionStatement,
			String downloadRestrictions) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver,
					nameFieldXpathInCreateObject);

			if (!name.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						nameFieldXpathInCreateObject, name);
			}

			if (!title.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
			}

			if (!description.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
						description);
			}

			if (!discipline.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						disciplizeDropdownXpathInCreateObject, discipline);
			}

			if (!contentType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contentTypeDropdownXpathInCreateObject, contentType);
			}

			if (!contribSource.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						contribSourceDropdownXpathInCreateObject, contribSource);
			}

			if (!realizeFileType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						realizeFileTypeDropdownXpathInCreateObject,
						realizeFileType);
			}

			if (!mediaType.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						mediaTypeDropdownXpathInCreateObject, mediaType);
			}

			if (!versionCountry.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						versioncountryDropdownXpath, versionCountry);
			}

			if (!versionState.isEmpty()) {
				UIHelper.selectbyVisibleText(driver, versionStateDropdownXpath,
						versionState);
			}

			if (!versionDistrict.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versiondistricttextxpath, versionDistrict);
			}

			if (!versionStatement.isEmpty()) {
				UIHelper.sendKeysToAnElementByXpath(driver,
						versionstatementtextxpath, versionStatement);
			}

			if (!downloadRestrictions.isEmpty()) {
				UIHelper.selectbyVisibleText(driver,
						downloadrestricationsdropdownxpath,
						downloadRestrictions);
			}

			report.updateTestLog("Input data to create 'Composite Object'",
					"User able to enter data for 'Composite Object'"
							+ "<br>Name: "
							+ name
							+ ", "
							+ "Title: "
							+ title
							+ ", <br>"
							+ "Description: "
							+ description
							+ ", <br>Discipline: "
							+ discipline
							+ ", <br>Content Type: "
							+ contentType
							+ ", <br>Contrib Source: "
							+ contribSource
							+ ", <br>Realize File Type: "
							+ realizeFileType
							+ "<br>Media Type: "
							+ mediaType
							+ "<br> Version State:"
							+ versionState
							+ ", <br> Version Country:"
							+ versionCountry
							+ ", version district:"
							+ versionDistrict
							+ ",version statement:"
							+ versionStatement
							+ ", Download Restrications:"
							+ downloadRestrictions, Status.DONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void valuecomparation(String[] splitedRow, List<String> version,
			int no, String title) {
		try {
			for (String listOfObjectsString : version) {

				if (listOfObjectsString.contains(splitedRow[no].replace("\"",
						""))) {
					report.updateTestLog(
							"Check whether value is present in the '" + title
									+ "' column for " + splitedRow[21], "'"
									+ listOfObjectsString
									+ "' value is present in the '" + title
									+ "' column for " + splitedRow[21],
							Status.PASS);
					break;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void FilterSignleversionstate(List<String> version,
			String[] splitedRow) {
		try {

			for (String listOfObjectsString : version) {
				if (splitedRow[21].replace("\"", "").contains("CourseTitle")
						|| splitedRow[21].replace("\"", "").contains(
								"SequenceTitle")
						|| splitedRow[21].replace("\"", "").contains(
								"ContentTitle")
						|| splitedRow[21].replace("\"", "").contains(
								"LearningTitle")
						|| splitedRow[21].replace("\"", "").contains(
								"CompositeTitle")
						|| splitedRow[21].replace("\"", "").contains(
								"ContainerTitle")) {

					report.updateTestLog("Filter is applied for "
							+ splitedRow[21], " Filter is applied for "
							+ splitedRow[21] + " for " + listOfObjectsString,
							Status.PASS);
					// break;
				} else if (splitedRow[21].replace("\"", "").contentEquals("")) {

					report.updateTestLog(
							"Filter is not applied for course and Programs ",
							"Filter is not applied for course and Programs for"
									+ listOfObjectsString, Status.PASS);

				} else if (splitedRow[21].replace("\"", "").contains(
						"Title In Sequence")) {
				} else {
					report.updateTestLog("Filter is not applied for "
							+ splitedRow[21], "Filter is not applied for "
							+ splitedRow[21], Status.FAIL);
				}
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> CSVValues(String createObjectData, String fieldname,
			int no) {

		List<String> li = new ArrayList<String>();
		try {
			if (createObjectData.contains(";")) {
				String splittedObjectData[] = createObjectData.split(";");

				for (String fileValues : splittedObjectData) {
					String splittedFileValues[] = fileValues.split(",");

					if (splittedFileValues != null) {
						String objectType = splittedFileValues[no].replace(
								fieldname, "");
						li.add(objectType);
					}
				}
			} else {

				String splittedObjectData[] = createObjectData.split(",");

				if (splittedObjectData != null) {
					String objectType = splittedObjectData[0].replace(
							fieldname, "");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return li;
	}

	// duplicate program
	public void duplicteProg(String value) {
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, detailbutton);
			UIHelper.click(driver, detailbutton);
			String finalactions = folderaction.replace("CRAFT", value);

			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalactions);
			UIHelper.click(driver, detailbutton);

			report.updateTestLog("Verify program duplicate clicked ",
					"program duplicate clicked.", Status.DONE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Verify program duplicate clicked ",
					"program duplicate not clicked.", Status.FAIL);
		}

	}
	
	public boolean CheckOptionsForClickOnMoreSettingsOption(
			String fileOrfolderName, String moreSettingsOptName) {
		boolean flag=false;
		
		try {
			 flag = checkMoreSettingsOptionOnly(fileOrfolderName, moreSettingsOptName);			
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
			UIHelper.waitForPageToLoad(driver);

		} catch (Exception e) {
		}
		
		return flag;
	}
	
	public boolean checkMoreSettingsOptionOnly(String fileOrfolderName,
			String moreSettingsOptName) {
		Boolean flag = false;
		try {
			String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink
					.replace("CRAFT", fileOrfolderName).replace("MORE_OPT",
							moreSettingsOptName);
			UIHelper.waitFor(driver);
			WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(
					driver, finalXpathForCopyToFolderLink);
			UIHelper.highlightElement(driver, moreSettingsOptElement);
			UIHelper.isWebElementDisplayed(moreSettingsOptElement);
				flag=true;	
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	
	

		   //Modify Filter
		   public void ModifyFilter(String filtername, String filtercolumnname){
		    
		    UIHelper.waitFor(driver);
		    UIHelper.waitForVisibilityOfEleByXpath(driver, filtercollectionboxxpath);
		    UIHelper.highlightElement(driver, filtercollectionboxxpath);
		    
		    
		   /* UIHelper.click(driver, ".//*[contains(@id,'"+filtername+"-checkbox')]");
		        
		        if(driver.findElement(By.xpath(".//*[contains(@id,'"+filtername+"-checkbox')]")).isSelected()){
		            report.updateTestLog("Check the "+filtername +" Checkbox", filtername+" is Checked for the filter" + "<br /><b>"+filtername+"</b>",
		                    Status.PASS);
		        }else{
		            report.updateTestLog("Check the "+filtername +" Checkbox", filtername+" is Checked for the filter" + "<br /><b>"+filtername+"</b>",
		                    Status.FAIL);
		        }
		    */
		    String[] filterselection = dataTable.getData("Sites", filtercolumnname).split(",");
		    for (String de:filterselection){
		    	System.out.println(de);
		    }
		    Actions ac = new Actions(driver);
		    ac.keyDown(Keys.CONTROL);
		    for (String listOfObjectsString : filterselection) {
		        String local = listOfObjectsString.trim();
		        WebElement object = driver.findElement(By.xpath("//option[@value='"+local+"']"));
		        UIHelper.highlightElement(driver, object);
		        object.click();
		        UIHelper.waitFor(driver);
		        ac.keyDown(Keys.CONTROL);
		        }    
		    ac.keyUp(Keys.CONTROL);   
		}
		
		

	
	//sathya		
			
			public void errormessagecontains(String value, String error) {
				try {
					String finalerrormsg = errormsg.replace("CRAFT", value);
					System.out.println(finalerrormsg);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalerrormsg);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver,
							finalerrormsg));
					UIHelper.highlightElement(driver, finalerrormsg);
					String finalmsg = UIHelper.getTextFromWebElement(driver,
							finalerrormsg);
					System.out.println(finalmsg);
					if (finalmsg.contains(error)) {
						report.updateTestLog("Verify Error Msg" + error,
								"Error msg as expected.<br><b> Error msg : <b>"
										+ finalmsg, Status.PASS);

					} else {
						report.updateTestLog("Verify Error Msg" + error,
								"Error msg not as expected.<br><b> Error msg : <b>"
										+ finalmsg, Status.FAIL);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					report.updateTestLog("Verify Error Msg",
							"Error msg not as expected.", Status.FAIL);
				}

			}
			
			public void VerifyErrorNotINUI(String textToVerify){
				try{
					
					if(!UIHelper.chkForThisStringinUI(driver, textToVerify))
					{
						report.updateTestLog("Verify the error is present in the error report","Reported error is not found in Error report", Status.PASS);			
					}else		{
						report.updateTestLog("Verify the error is present in the error report","Reported error is found in Error report", Status.FAIL);
					}													
						
					
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			
			public void VerifyPropertyValue(String fieldName, String fieldValue){
				try{
					System.out.println("22");
					editPropInputXpathTemp = editPropInputXpath.replace("CRAFT", fieldName);
					System.out.println("23");
					WebElement PropertyEle = driver.findElement(By.xpath(editPropInputXpath));
					System.out.println("24");
					String propertyValue = PropertyEle.getAttribute("value");
					System.out.println(propertyValue+"propertyValue");
					System.out.println(fieldValue+"fieldValue");
					if(propertyValue.contains(fieldValue))
					{
						System.out.println("1");
						report.updateTestLog("Validate the presence of Genres " + fieldName , "Validate the presence of Genres "  + fieldValue , Status.PASS);
					}else
					{
						System.out.println("2");
						report.updateTestLog("Validate the presence of Genres " + fieldName , "Validate the presence of Genres "  + fieldValue , Status.FAIL);
					}																
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
				
			public void VerifyPropertyValueValidData(String fieldName, String fieldValue){
				try{
					String fileList = "//div[@id='alfresco-documentlibrary']";
					String skillseleXpath = "//input[@name='prop_cpnals_skillsPath']"; 
					WebElement saveButtonEle = driver.findElement(By.xpath(saveButtonXpath));
					editPropInputXpathTemp = editPropInputXpath.replace("CRAFT", fieldName);
					WebElement PropertyEle = driver.findElement(By.xpath(editPropInputXpathTemp));
					UIHelper.sendKeysToAnWebElement(driver, PropertyEle, fieldValue);
					UIHelper.selectIntergerValue(driver,disciplizeDropdownXpathInCreateObject, 1);
					UIHelper.waitFor(driver);
					UIHelper.sendKeysToAnElementByXpath(driver, skillseleXpath, "test");
					UIHelper.waitFor(driver);
					UIHelper.mouseOverandclickanElement(driver, saveButtonEle);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);					
					report.updateTestLog("Validate the property value for  " + fieldName + "with the valid data", "Property value is accepting the given valid value  "   + fieldValue + "for " + fieldName , Status.PASS);
										
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			public void VerifyPropertyValueInValidData(String fieldName, String fieldValue){
				try{
						
					editPropInputXpathTemp = editPropInputXpath.replace("CRAFT", fieldName);
					System.out.println("editPropInputXpathTemp"+editPropInputXpathTemp);
					WebElement PropertyEle = driver.findElement(By.xpath(editPropInputXpathTemp));				
					UIHelper.sendKeysToAnWebElement(driver, PropertyEle, fieldValue);		
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					if(PropertyEle.getAttribute("title").contains("Value")){
						report.updateTestLog("Validate the presence of warning message for invalid data for " + fieldName +" ", "Warning message is displayed for invalid data", Status.PASS);
					}else
					{
						report.updateTestLog("Validate the presence of warning message for invalid data for " + fieldName +" ", "Warning message is not displayed for invalid data", Status.FAIL );
					}									
						
					
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			
			public void VerifyComprehensiveValueAndList(String fieldName, String fieldValue){
				try{
					editPropInputXpathTemp = ".//select[@id='template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_comprehensionSkills']";		
					UIHelper.selectIntergerValue(driver,disciplizeDropdownXpathInCreateObject, 1);
					Select selectBox = new Select(driver.findElement(By.xpath(editPropInputXpathTemp)));
					String selectedOption = selectBox.getFirstSelectedOption().getText();	
					UIHelper.waitFor(driver);
					
					if(selectedOption.contains("Fact and Opinion"))
					{
						report.updateTestLog("Validate the Leveled Readers value for  " + fieldName + "with the valid data", "Leveled Readers property value is present with  "   + fieldValue + "for " +fieldName , Status.PASS);
					}else
					{
						report.updateTestLog("Validate the Leveled Readers value for  " + fieldName + "with the valid data", "Leveled Readers property value is not present "  + "for " +fieldName , Status.FAIL);
					}		
					UIHelper.waitFor(driver);			

					List<WebElement> selectOptions = selectBox.getOptions();

					for (WebElement temp : selectOptions)
					{
						String CompValues = temp.getText();
						System.out.println(CompValues);

					}		
					if(selectOptions.size()>=19)
					{
						report.updateTestLog("Printing the selected values in the Comprehensive Skills property ", "Values present in the property is displayed successfully" , Status.PASS);
					}else
					{
						report.updateTestLog("Printing the selected values in the Comprehensive Skills property ", "Values present in the property is not displayed successfully" , Status.FAIL);
					}
					
					UIHelper.waitFor(driver);			
					WebElement saveButtonEle = driver.findElement(By.xpath(saveButtonXpath));
					UIHelper.selectValueFromDropdown(driver, editPropInputXpathTemp, "Predict");
					UIHelper.mouseOverandclickanElement(driver, saveButtonEle);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);	
					UIHelper.waitFor(driver);
					if(UIHelper.checkForAnElementbyXpath(driver, collectionSite))
					{
						report.updateTestLog("Validate the Leveled Readers value for  " + fieldName + "with the valid data", "Leveled Readers property value is accepting the given valid value  "  +fieldName , Status.PASS);
					}else
					{
						report.updateTestLog("Validate the Leveled Readers value for" + fieldName + "with the valid data", "Leveled Readers property value is not accepting the given valid value"  +fieldName, Status.FAIL);
					}										
														
						
					
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}	
			
			// verify uploaded files in thumbnails 
			public void VerifyThumbnailListinAllproperties(int fieldValue){
				try{					
					UIHelper.click(driver, thumbSelect);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, assertElexpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, thumbElexpath);			
					UIHelper.waitForLong(driver);				
					List<WebElement> ThumbnailList = driver.findElements(By.xpath(thumblist));
					int ThumbnailListsize = ThumbnailList.size();
					if(ThumbnailListsize>fieldValue)
					{
						report.updateTestLog("Validate the added Thumbnail files ", "Picker Window displayed all the Thumbnail Files successfully  "  , Status.PASS);
					}else
					{
						report.updateTestLog("Validate the added Thumbnail files ", "Picker Window is not displayed all the Thumbnail Files successfully  "  , Status.FAIL);
					}			
					UIHelper.click(driver, cancelXpath);			
					
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			
			public void filtervalidationforContentObjects(String content1, String content2) {
				try {
					UIHelper.waitForPageToLoad(driver);
					ArrayList<String> listOfObjects1 = new ArrayList<String>();
					UIHelper.waitForPageToLoad(driver);
					listOfObjects1 = getFoldersFromRightPanInCollectionUi();
					UIHelper.waitForPageToLoad(driver);

					for (String listOfObjectsString : listOfObjects1) {
						System.out.println(listOfObjectsString);
						if (listOfObjectsString.contentEquals(content1)
								|| listOfObjectsString
										.contentEquals(content2)) {
					report.updateTestLog("Filter is applied",
							listOfObjectsString+" these contents are filtered",Status.PASS);
						
						} else {
							report.updateTestLog("Filter is applied",
									listOfObjectsString
											+ " , the contents are not filtered",
									Status.FAIL);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void editpropAndSave(String fieldName, String fieldValue) {
				try {
					
					String finalInputBoxXpath = createObjectDropDownXpath.replace("DROPDOWN_FIELD_LABEL",fieldName);
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalInputBoxXpath));
					UIHelper.highlightElement(driver, finalInputBoxXpath);
					UIHelper.deselectAll(driver, finalInputBoxXpath);
					UIHelper.selectbyVisibleText(driver, finalInputBoxXpath,fieldValue);			
					WebElement saveButtonEle = driver.findElement(By.xpath(saveButtonXpath));			
					UIHelper.mouseOverandclickanElement(driver, saveButtonEle);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					if(UIHelper.checkForAnElementbyXpath(driver, collectionSite))			
					{
						report.updateTestLog("Property edited for" + fieldName + " property value", "property value edited successfully with "  + fieldValue , Status.PASS);
					}else
					{
						report.updateTestLog("Property edited for" + fieldName + " property value", "property value is not edited successfully with "  + fieldValue , Status.FAIL);
					}						
							
								
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void appliedFilterVerification() {
				try {	
					
				String expfilterValue = dataTable.getData("Sites", "FilterCollection_VersionState");		
				String actfilterValue = UIHelper.getTextFromWebElement(driver, filterValue);			
				if (expfilterValue.equalsIgnoreCase(actfilterValue)) {
					report.updateTestLog("Verify Filter values are retained after editing the Content Object", "Filter values are retained after editing the Content Object",Status.PASS);
				} else {
					report.updateTestLog("Verify Filter values are retained after editing the Content Object", "Filter values are not retained after editing the Content Object",Status.FAIL);
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void addPDFChildReference(String refFolderName, String objectName) {
				try {
					String AddFileXpathRef = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[contains(.,'')]";
					String finalRefObjectXpath = refObjectXpath.replace("CRAFT",objectName.trim());
					String finalSelectedRefObjectXpath = selectedRefObjectXpath.replace("CRAFT", objectName.trim());
					String finalrefFolderXpath = refFolderXpath.replace("CRAFT",refFolderName);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
					UIHelper.waitFor(driver);			
					UIHelper.click(driver, finalrefFolderXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, AddFileXpathRef);			
					UIHelper.waitFor(driver);
					UIHelper.click(driver, finalRefObjectXpath);
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver,
							finalSelectedRefObjectXpath);
					UIHelper.highlightElement(driver, finalSelectedRefObjectXpath);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, childRefOkXpath);
					report.updateTestLog("Add reference object in Child reference pop up",	"Reference object is added successfully"
									+ "<br><b>Reference Object Name: </br>"
									+ objectName, Status.PASS);

				} catch (Exception e) {
					e.printStackTrace();		

				}
			}

			public void linkThumbnailandGridThumbnail(String thumbnailValue) {
				try {		
								
					WebElement gridThumbnailLinkEle = driver.findElement(By.xpath(gridThumbnailLink));
					WebElement ThumbnailLinkEle = driver.findElement(By.xpath(ThumbnailLink));
					UIHelper.sendKeysToAnWebElement(driver, gridThumbnailLinkEle, thumbnailValue);
					UIHelper.waitFor(driver);
					UIHelper.sendKeysToAnWebElement(driver, ThumbnailLinkEle, thumbnailValue);
					UIHelper.waitFor(driver);
					UIHelper.click(driver, saveButtonXpath);
					UIHelper.waitFor(driver);
					if(UIHelper.checkForAnElementbyXpath(driver, collectionSite))
					{
						report.updateTestLog("Validate Asset Linking section after successful file upload" , "Asset Linking section - Fields are populated successfully with the provided file  "  , Status.PASS);
					}else
					{
						report.updateTestLog("Validate Asset Linking section after successful file upload" , "Asset Linking section - Fields are not populated successfully with the provided file "  , Status.FAIL);
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void clickDetailsAndValue(String value) {
				try {
					UIHelper.waitForVisibilityOfEleByXpath(driver, detailbutton);
					UIHelper.click(driver, detailbutton);
					String finalactions = folderaction.replace("CRAFT", value);			
					UIHelper.waitFor(driver);
					UIHelper.waitForVisibilityOfEleByXpath(driver, finalactions);			
					UIHelper.click(driver, finalactions);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					report.updateTestLog("Verify " +value + "generation of edited content object",  
							"" + value + " is generated successfully for the edited content object ", Status.PASS);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}

			}
			
			public void verifyOutgoingReferenceInCollObjVieDetailsPg(String content1) {
				
				try {
					
					boolean flag = false;
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
					List<WebElement> childRefEleList = UIHelper.findListOfElementsbyXpath(childReferencesLinkTitleXpath,driver);
					String[] OGList = new String[childRefEleList.size()];
					int index = 0;
						for(WebElement OGElement : childRefEleList)
						{
							OGList[index]= OGElement.getText();	
							index++;
						}	
						for(int i=0; i<OGList.length;i++)
						{				
							 if(Arrays.asList(OGList[i]).contains(content1))		 
							 	 
								 flag = true; 
						}
						
						if(flag = true)
						{
							report.updateTestLog("Verify Content Object references for Thumbnail and Grid Thumbnail" ,
									"Content Object references for Thumbnail and Grid Thumbnail are added successfully"  , Status.PASS);
						}
						else
						{
							report.updateTestLog("Verify Content Object references for Thumbnail and Grid Thumbnail" ,
								"Content Object references for Thumbnail and Grid Thumbnail are not added successfully"  , Status.FAIL);	
						}
						
					} catch (Exception e) {
					e.printStackTrace();
				}		
			
			}
			
			public void lockFileValidation() {
				try {
					String offlineMessage = "//div[@class='info-banner']";
					WebElement offlineMessageEle = UIHelper.findAnElementbyXpath(driver, offlineMessage);
					if(UIHelper.checkForAnWebElement(offlineMessageEle))
					{
						report.updateTestLog("Validate whether the user is able to lock the file" , "User is able to lock the file for offline editing "  , Status.PASS);
					}else
					{
						report.updateTestLog("Validate whether the user is able to lock the file" , "User is not able to lock the file for offline editing "  , Status.FAIL);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}

			}
			
			
			public void LeveledReaderSectionAndOptionsVerify(String lrText) {
				try {
					
					WebElement LeveledReaderEle = driver.findElement(By.xpath(LeveledReaderEleXpath));
					UIHelper.highlightElement(driver, LeveledReaderEle);
					String leveledReaderText = LeveledReaderEle.getText();	
					if(leveledReaderText.equalsIgnoreCase(lrText))
					{
						report.updateTestLog("Validate the presence of Leveled Readers section", "Leveled Readers section is Present", Status.PASS);
					}else
					{
						report.updateTestLog("Validate the presence of Leveled Readers section", "Leveled Readers section is not Present", Status.FAIL );
					}
						
					//validate level readers section option and the order 
					
					boolean isPresent =false;				
					List<WebElement> leveledReaderList = driver.findElements(By.xpath(leveledReaderListXpath));			
					String[] lRList = new String[leveledReaderList.size()];
					int index = 0;
						for(WebElement leveledReaderElement : leveledReaderList)
						{
							lRList[index]= leveledReaderElement.getText();	
							index++;
						}						

							if(lRList[0].contains("Genres:") && lRList[1].contains("Lexile:") && lRList[2].contains("Guided Reading:") 
							&& lRList[3].contains("DRA (Developmental Reading Assessment):")&& lRList[4].contains("Reading Maturity Metric:") 
							&& lRList[5].contains("Quantile:") && lRList[6].contains("Comprehension Skills:") && lRList[7].contains("Text Features:")
							&& lRList[8].contains("Content Areas:") && lRList[9].contains("ISBN:")&& lRList[10].contains("Author:"))
								
							{
								isPresent = true;								
								
							}
						
						if(isPresent){
							report.updateTestLog("Verify defined Leveled Readers properties are visible and in correct order", "Defined Leveled Readers properties are visible and in correct order", Status.PASS);
						}else{
							report.updateTestLog("Verify defined Leveled Readers properties are visible and in correct order", "Defined Leveled Readers properties are not visible and not in correct order", Status.FAIL);
					}									
								
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}

			}	
			
			//verify property value in document details page
			public void VerifyPropertyValueINviewDetails(String fieldName, String fieldValue){
				try{
					String propertyvalueText = "//div[@class='form-field']//div[@class='viewmode-field']//span[text()='CRAFT']/following-sibling::*";
					String propValueXpathTemp = propertyvalueText.replace("CRAFT", fieldName);
					WebElement PropertyEle = driver.findElement(By.xpath(propValueXpathTemp));		
					String propertyValue = PropertyEle.getText();
					if(propertyValue.contains(fieldValue))
					{
						report.updateTestLog("Validate the presence of property value in view details page  " + fieldName , "Expected Property value is present in view details page  "  + fieldValue , Status.PASS);
					}else
					{
						report.updateTestLog("Validate the presence of property value in view details page  " + fieldName , "Expected Property value is not present in view details page  "  + fieldValue , Status.FAIL);
					}																
						
					
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			
			// Verify Collection Object List
				public void VerifyCollectionObjectList(String collectionObjectName) {
					try {
						
						String[] collectionObjectNameList = collectionObjectName.split(",");
						String expandButtonxpath = "//*[@id='assembly-view-main-toolbar-expand-all-button-button']";
						UIHelper.click(driver, expandButtonxpath);
						UIHelper.waitFor(driver);
						String collectionobjListXpath = ".//*[@id='assembly-view-tree-container']//a";
						List<WebElement> collectionObjectTitleEleList = UIHelper
								.findListOfElementsbyXpath(collectionobjListXpath,
										driver);
						String[] objList = new String[collectionObjectTitleEleList.size()];
						int index = 0;
						
						for (WebElement ele : collectionObjectTitleEleList) {
							objList[index]=ele.getText();
							index++;
						}
						System.out.println(objList[0]);
						System.out.println(collectionObjectNameList[0]);
						System.out.println(objList[1]);
						System.out.println(collectionObjectNameList[1]);
						ArrayList<String> ar = new ArrayList<String>();
						for(int i = 1; i<collectionObjectTitleEleList.size();i++)					
						 {
						      if(!Arrays.asList(collectionObjectNameList).contains(objList[i]))
						      {				    	
						    	  report.updateTestLog("Verify collecion object List", "Collection object list is displayed as expected " +collectionObjectNameList[i] , Status.PASS);
						    	  
						      }else
						      {
						    	  report.updateTestLog("Verify collecion object List", "Collection object list is not displayed as expected " , Status.FAIL);
						      }
						    }
					
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				public void verifyOutgoingReferenceInCollObjTitle(String content1) {
					
					try {
						boolean flag = false;
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);
						List<WebElement> childRefEleList = UIHelper.findListOfElementsbyXpath(childReferencesLinkTitleXpath,driver);
						String[] OGList = new String[childRefEleList.size()];
						int index = 0;
							for(WebElement OGElement : childRefEleList)
							{
								OGList[index]= OGElement.getAttribute("title");
								index++;
							}	
							for(int i=0; i<OGList.length;i++)
							{				
								 if(Arrays.asList(OGList[i]).contains(content1))		 
								 	 
									 flag = true; 
							}
							
							if(flag = true)
							{
								report.updateTestLog("Verify Content Object references for Thumbnail and Grid Thumbnail in the specified directory" ,
										"Content Object references for Thumbnail and Grid Thumbnail are added successfully from the specified directory"  , Status.PASS);
							}
							else
							{
								report.updateTestLog("Verify Content Object references for Thumbnail and Grid Thumbnail in the specified directory" ,
									"Content Object references for Thumbnail and Grid Thumbnail are not added successfully from the specified directory"  , Status.FAIL);	
							}
							
						} catch (Exception e) {
						e.printStackTrace();
					}		
				
				}
				
				public void openObjectsInViewDetails() {
					try {
											
							UIHelper.waitForPageToLoad(driver);
							UIHelper.waitFor(driver);
							List<WebElement> childRefEleList = UIHelper.findListOfElementsbyXpath(childReferencesLinkTitleXpath,driver);								
								for(WebElement OGElement : childRefEleList)
								{
									OGElement.click();							
								}							
								
							} catch (Exception e) {
							e.printStackTrace();
						}		
					
				}
				
				public void verifyEmptyFolder(String foldervalue){
					try{
						boolean flag = false;
						AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
						 myFiles.openCreatedFolder("courses");				
						 flag = UIHelper.chkForThisStringinUI(driver, foldervalue);	 				
						if(flag = true)
						{
							report.updateTestLog("Validate folder in document library  " , "Folder is empty and the contents are deleted successfully "  + foldervalue , Status.PASS);
						}else
						{
							report.updateTestLog("Validate folder in document library  "  ,  "Folder is not empty and the contents are not deleted successfully "  + foldervalue , Status.FAIL);
						}																
							
						
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
				
				public void verifyIncomingHasAlignmentViewDetailsPg(String content1) {
					
					try {
						String hasAlignText = ".//*[contains(@class,'relationships-element-type-table')]//a";
						boolean flag = false;
						UIHelper.waitForPageToLoad(driver);
						UIHelper.waitFor(driver);
						List<WebElement> childRefEleList = UIHelper.findListOfElementsbyXpath(hasAlignText,driver);
						String[] OGList = new String[childRefEleList.size()];
						int index = 0;
							for(WebElement OGElement : childRefEleList)
							{
								OGList[index]= OGElement.getText();	
								index++;
							}	
							for(int i=0; i<OGList.length;i++)
							{				
								 if(Arrays.asList(OGList[i]).contains(content1))		 
								 	 
									 flag = true; 
							}
							
							if(flag = true)
							{
								report.updateTestLog("Verify the skill code under Incoming /Has Alignment" ,
										"Skill code is present under Incoming /Has Alignment "  , Status.PASS);
							}
							else
							{
								report.updateTestLog("Verify the skill code under Incoming /Has Alignment" ,
									"Skill code is not present under Incoming /Has Alignment"  , Status.FAIL);	
							}
							
						} catch (Exception e) {
						e.printStackTrace();
					}		
				
				}
				
				public void addAlignmentinObjects() {
					
					try {
						UIHelper.waitFor(driver);
						String framexpath = "//*[@id='alignmentToolIframe']";
						WebElement framexpathEle = UIHelper.findAnElementbyXpath(driver, framexpath);	
						driver.switchTo().frame(framexpathEle);		
						UIHelper.waitFor(driver);
						String discipleXpath = ".//*[contains(@class,'placeholder-custom mat-button')]";
						WebElement discipleElement = UIHelper.findAnElementbyXpath(driver, discipleXpath);
						String MathXpath = "(//*[contains(text(),'Social Studies')])";				
						UIHelper.click(driver, discipleXpath);
						UIHelper.waitFor(driver);
						UIHelper.mouseOverandclickanElement(driver, discipleElement);			
						UIHelper.waitFor(driver);	
						UIHelper.click(driver, MathXpath);
						UIHelper.waitFor(driver);								
						//select the skill code
						String skillone = "//*[@id='skillsTable']/mat-row[1]/mat-cell[1]/button/img";
						String skilltwo = "//*[@id='skillsTable']/mat-row[2]/mat-cell[1]/button/img";
						String saveSkill = "//button[@class='mat-raised-button']//span[text()='Save']";
						WebElement skilloneElement = UIHelper.findAnElementbyXpath(driver, skillone);
						WebElement skilltwoElement = UIHelper.findAnElementbyXpath(driver, skilltwo);
						WebElement saveskillElement = UIHelper.findAnElementbyXpath(driver, saveSkill);
						UIHelper.mouseOverandclickanElement(driver, skilloneElement);	
						UIHelper.waitFor(driver);
						UIHelper.mouseOverandclickanElement(driver, skilltwoElement);	
						UIHelper.waitFor(driver);
						UIHelper.mouseOverandclickanElement(driver, saveskillElement);	
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);					
						report.updateTestLog("Skill code addition: " ,
										"Skill code Added successfully to the course object "  , Status.PASS);						
							
						} catch (Exception e) {
						e.printStackTrace();
					}		
				
				}
				
		//*************************** Confirm imported new metadata values have been applied**********************************//
	    //*************************** Added by Sangeetha.L ********************************************************************//
	   public void verifyMetadataValues(String name, String value) {
			try {
				
			boolean flag = false;
			
			List<WebElement> metadataSearchTitle = UIHelper
						.findListOfElementsbyXpath(metadataSearchTitleXpath, driver);
			for (WebElement ele : metadataSearchTitle) {	
					flag =false;
					if (ele.getText().equalsIgnoreCase(value)) {
						report.updateTestLog("Verify for the metadata : "+name+" the value is : "+value + "",
								" For the metadata : " + name+ " the value is : " +value, Status.PASS);
						flag=true;
						break;
					}
					
			}
			if(flag==false)
			{
				report.updateTestLog("Verify for the metadata : "+name+" the value is : "+value + "",
						" For the metadata : " + name+ " the value is not : " +value, Status.FAIL);
			}	
				
			} catch (Exception e) {
			
				e.printStackTrace();
				report.updateTestLog("Verify imported metadata values ",
						"Imported Metadata values not displayed.Error in method : verifyMetadataValues ", Status.FAIL);
			}

		}
	   
	   
	 //*************************** Confirm new metadata available in Alfresco property forms for dropdown fields**********************************//
	    //*************************** Added by Sangeetha.L ********************************************************************//
	   @SuppressWarnings("null")
	public void verifyMetadataValuesInEditAllProperties(String foldername, String metadata , String verifyPropertyVal) {
			try {
				
			String metadata1[] = metadata.split(":");
			String metadata2[] = metadata1[1].split(",");
			clickOnMouseOverMenu(foldername,"Edit Properties");
			UIHelper.click(driver, allProperties);
				UIHelper.waitForPageToLoad(driver);
				//Modified as part of NALS Removed genres and text features from the IF condition 3190_TC003
				if(metadata1[0].equalsIgnoreCase("Discipline")||metadata1[0].equalsIgnoreCase("MediaType")||metadata1[0].equalsIgnoreCase("contentType")||metadata1[0].equalsIgnoreCase("contentAreas"))
				{
 				int[] Discipline_Flag = new int[metadata2.length];
 				String finalPropertyXpath = tempPropertyXpath.replace("CRAFT", "prop_cpnals_"+metadata1[0]);
				Select oSelect = new Select(driver.findElement(By.xpath(finalPropertyXpath)));
				UIHelper.click(driver, finalPropertyXpath);
				UIHelper.waitFor(driver);
				List <WebElement> options = oSelect.getOptions();
 				for(int j=0;j<metadata2.length;j++)
				{
 					for (WebElement option : options) {
 						if(metadata2[j].equalsIgnoreCase(option.getText()))
 						{
 							Discipline_Flag[j] = 1;
 							break;
 						}
 					}
 				}
 				for (int i=0;i<Discipline_Flag.length;i++)
 				{
 					if(Discipline_Flag[i]==1)
 					{
 						report.updateTestLog("Verify that the value : "+  metadata2[i] +" is displayed in the property form for "+metadata1[0]+" property",
								 metadata2[i] + " is displayed in the property form  for "+metadata1[0]+" property ", Status.PASS);
	 					}
 					else
 					{
 						report.updateTestLog("Verify that the value : "+  metadata2[i] +" is displayed in the property form for "+metadata1[0]+" property",
								 metadata2[i] + " is not displayed in the property form  for "+metadata1[0]+" property ", Status.FAIL);
 					}
 				}
				}
				//Modified as part of NALS Included genres and text features in the IF condition 3190_TC003
				else if(metadata1[0].equalsIgnoreCase("Version")||metadata1[0].equalsIgnoreCase("genres")
						||metadata1[0].equalsIgnoreCase("textFeatures"))
				{
					int[] Discipline_Flag = new int[metadata2.length];
					//Modified as part of NALS Starts 3190_TC003 Starts
					String Xpath = ""; 
					if(metadata1[0].equalsIgnoreCase("Version")) {
						Xpath = versionstateProperty;
					}
					else {
						Xpath = tempPropertyXpath1.replace("CRAFT", "prop_cpnals_"+metadata1[0]+"-entry");
					}
					Select oSelect = new Select(driver.findElement(By.xpath(Xpath)));
					UIHelper.click(driver, Xpath);
					List <WebElement> options = oSelect.getOptions();
					UIHelper.waitFor(driver);
					
				for(int j=0;j<metadata2.length;j++)
				{
 					for (WebElement option : options) {
 						if(metadata2[j].equalsIgnoreCase(option.getText()))
 						{
 							Discipline_Flag[j] = 1;
 							break;
 						}
 					}
 				}
 				for (int i=0;i<Discipline_Flag.length;i++)
 				{
 					if(Discipline_Flag[i]==1)
 					{
 						report.updateTestLog("Verify that the value : "+  metadata2[i] +" is displayed in the property form for "+metadata1[0]+" property",
								 metadata2[i] + " is displayed in the property form  for "+metadata1[0]+" property ", Status.PASS);
	 					}
 					else
 					{
 						report.updateTestLog("Verify that the value : "+  metadata2[i] +" is displayed in the property form for "+metadata1[0]+" property",
								 metadata2[i] + " is not displayed in the property form  for "+metadata1[0]+" property ", Status.FAIL);
 					}
 				}
 				//Modified as part of NALS Ends 3190_TC003 Ends
				}
				if(verifyPropertyVal.equalsIgnoreCase("Yes"))
				{
					verifyPropertyValueAndSave(metadata1[0],metadata2[0]);
				}
				
			} catch (Exception e) {
			
				e.printStackTrace();
				report.updateTestLog(" Confirm new metadata available in Alfresco property forms ",
						"New metadata is not available in Alfresco property forms.Error in method : verifyMetadataValuesInEditAllProperties ", Status.FAIL);
			}

		}

	   
	 //*************************** Verify the value selected for a property in Alfresco property forms for dropdown fields**********************************//
	    //*************************** Added by Sangeetha.L ********************************************************************//
	public void verifyPropertyValueAndSave(String strPropertyName, String strPropertyValue) {
		try
		{
			
			String finalPropertyXpath = tempPropertyXpath.replace("CRAFT", "prop_cpnals_"+strPropertyName);
			System.out.println("finalPropertyXpath"+finalPropertyXpath);
			Select oSelect = new Select(driver.findElement(By.xpath(finalPropertyXpath)));
			UIHelper.highlightElement(driver, finalPropertyXpath);
			UIHelper.click(driver, finalPropertyXpath);
			String selectedVal = oSelect.getFirstSelectedOption().getText();
	

			if(selectedVal.equalsIgnoreCase(strPropertyValue))
			{
				report.updateTestLog("Verify that the value : "+ strPropertyValue +" is selected in the property form as the first option for "+ strPropertyName +" property",
						strPropertyValue  + " is selected in the property form as the first option for "+ strPropertyName +" property ", Status.PASS);	 
			}
			else
			{
				report.updateTestLog("Verify that the value : "+ strPropertyValue +" is selected in the property form as the first option for "+ strPropertyName +" property",
						strPropertyValue  + " is not selected in the property form as the first option for "+ strPropertyName +" property ", Status.FAIL );	 
			
			}
			
			// Select a different value and save 
			
			if(strPropertyName.equalsIgnoreCase("playerTarget"))
			{
				oSelect.selectByVisibleText("realize");
			}
			else if(strPropertyName.equalsIgnoreCase("language"))
			{
				oSelect.selectByVisibleText("English");
			}
			else
			{
				oSelect.selectByIndex(1);
			}
	
			Select oSelect_Discipline = new Select(driver.findElement(By.xpath(".//*[@name='prop_cpnals_discipline']")));
			oSelect_Discipline.selectByIndex(1);
			UIHelper.click(driver, saveButton);
			UIHelper.waitForLong(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver,documentLibraryXpath );
			WebElement WebEledocumentLibrary = driver.findElement(By.xpath(documentLibraryXpath)) ;
			if(UIHelper.isWebElementDisplayed(WebEledocumentLibrary))
			{
				report.updateTestLog("Verify " + strPropertyName +" allows to change the value",
						strPropertyName  + " allows to change the value in the property form", Status.PASS);	 
			}
			else
			{
				report.updateTestLog("Verify " + strPropertyName +" allows to change the value",
						strPropertyName  + " does not allow to change the value in the property form", Status.FAIL);	  
			
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog(" Confirm new metadata available in Alfresco property forms ",
					"New metadata is not available in Alfresco property forms.Error in method : verifyPropertyValueAndSave ", Status.FAIL);
		}
		
	}
	//************************************Confirm text fields was populated in import in edit all Property forms for text fields*************************************//
    //*************************** Added by Sangeetha.L ********************************************************************//
	public void confirmPropertyinImportAndSave(String foldername,String Metadata,String fieldData) {
	
		String[] fieldDataVal1 = null;
		try{
		
		String[] fieldDataVal = fieldData.split(":");
		String[] strProperty = Metadata.split(":");
		
		if(strProperty[0].equalsIgnoreCase("isbn"))
		{
			 fieldDataVal1 = fieldDataVal[1].split(",");
		}
		
		
		clickOnMouseOverMenu(foldername,"Edit Properties");
		UIHelper.click(driver, allProperties);
		
		String finalPropertyXpath = tempPropertyXpath.replace("CRAFT", "prop_cpnals_"+strProperty[0]);
		UIHelper.click(driver, finalPropertyXpath);
		UIHelper.highlightElement(driver, finalPropertyXpath);
		UIHelper.waitFor(driver);
		
		String strPropVal = driver.findElement(By.xpath(finalPropertyXpath)).getAttribute("value");

		if(!(strProperty[0].equalsIgnoreCase("englishId")||strProperty[0].equalsIgnoreCase("externalIdPrefixAndSeparator")))
		{	
			if(strPropVal.equalsIgnoreCase(strProperty[1]))
			{
				report.updateTestLog("Verify that the value : "+ strProperty[1] +" is displayed in the property form for "+ strProperty[0] +" property",
						strProperty[1]  + " is displayed in the property form as the first option for "+ strProperty[0] +" property ", Status.PASS);	 
			}
			else
			{
				report.updateTestLog("Verify that the value : "+ strProperty[1] +" is displayed in the property form  for "+ strProperty[0] +" property",
						strProperty[1]  + " is not displayed in the property form as the first option for "+ strProperty[0] +" property ", Status.FAIL);	 
			
			}
		}
		if(strProperty[0].equalsIgnoreCase("externalIdPrefixAndSeparator"))
		{
			UIHelper.sendKeysToAnElementByXpath(driver,skillsPath ," ");
			UIHelper.sendKeysToAnElementByXpath(driver,skillsPath,"test");
		}
		
		Select oSelect_Discipline = new Select(driver.findElement(By.xpath(".//*[@name='prop_cpnals_discipline']")));
		oSelect_Discipline.selectByIndex(1);
		
		if(strProperty[0].equalsIgnoreCase("isbn"))
		{
			// Send a invalid value for the property ISBN
			 fieldDataVal1 = fieldDataVal[1].split(",");
			 UIHelper.sendKeysToAnElementByXpath(driver, finalPropertyXpath," ");		 
			 UIHelper.sendKeysToAnElementByXpath(driver, finalPropertyXpath,fieldDataVal1[1]);
			 
			 	UIHelper.click(driver, saveButton);
				WebElement WebEledocumentLibrary = driver.findElement(By.xpath(editMetadataViewXpath)) ;
				if(UIHelper.isWebElementDisplayed(WebEledocumentLibrary))
				{
					report.updateTestLog("Verify " +fieldDataVal1[1]  +" is not a valid value for the property " + strProperty[0],
							fieldDataVal1[1]  +" is not a valid value for the property " + strProperty[0], Status.PASS);	 
				}
				else
				{
					report.updateTestLog("Verify " +fieldDataVal1[1]  +" is not a valid value for the property " + strProperty[0],
							fieldDataVal1[1]  +" is a valid value for the property " + strProperty[0], Status.FAIL);
				
				}	
				//send valid value to ISBN property
				UIHelper.sendKeysToAnElementByXpath(driver, finalPropertyXpath," ");
				UIHelper.sendKeysToAnElementByXpath(driver, finalPropertyXpath,fieldDataVal1[0]);
		}
		else
		{
		 UIHelper.sendKeysToAnElementByXpath(driver, finalPropertyXpath," ");
		 UIHelper.sendKeysToAnElementByXpath(driver, finalPropertyXpath,fieldDataVal[1]);
		}
		 UIHelper.click(driver, saveButton);
		 UIHelper.waitForLong(driver);
		 UIHelper.waitForVisibilityOfEleByXpath(driver, documentLibraryXpath );
		 WebElement WebEledocumentLibrary = driver.findElement(By.xpath(documentLibraryXpath)) ;
		 if(UIHelper.isWebElementDisplayed(WebEledocumentLibrary))
		{
				report.updateTestLog("Verify " + strProperty[0] +" allows to change the value",
						strProperty[0]  + " allows to change the value in the property form", Status.PASS);	 
		}
		else
		{
				report.updateTestLog("Verify " + strProperty[0] +" allows to change the value",
						strProperty[0]  + " does not allow to change the value in the property form", Status.FAIL);	  
		}	
		
	}catch(Exception e){
		e.printStackTrace();
		report.updateTestLog(" Confirm new metadata available in Alfresco property forms ",
				"New metadata is not available in Alfresco property forms.Error in method : confirmPropertyinImportAndSave ", Status.FAIL);
	}
	
	
}
	//*************************** Verify Parent and Child References in View Details**********************************//
    //*************************** Added by Sangeetha.L ********************************************************************//
   public void verifyObjectsInViewDetails(String xPath, String value) {
		try {
			
		WebElement webEle = driver.findElement(By.xpath(xPath));
		
		if(UIHelper.isWebElementDisplayed(webEle))
		{
			UIHelper.highlightElement(driver, webEle);
			if(UIHelper.getTextFromWebElement(driver, xPath).equalsIgnoreCase(value))
			{
				report.updateTestLog("Verify that " + value +" should be displayed in View Details",
						value+" is displayed in View Details", Status.PASS);
				
			}
			else
			{
				report.updateTestLog("Verify that " + value +" ia not displayed in View Details",
						value+" is displayed in View Details", Status.FAIL);	
			}
		}		
		
			
		} catch (Exception e) {
		
			e.printStackTrace();
			report.updateTestLog("Verify Parent and Child References in View Details ",
					"Error in method : verifyObjectsInViewDetails ", Status.FAIL);
		}

	}
 //*************************** Confirm Filter Highlights**********************************//
   //*************************** Added by Sangeetha.L ********************************************************************//
   
   public void verifyFiterHighlights(String Object, String value,String strCssValue,String color) {

	    String finalXpath = BQboarderxpath.replace("CRAFT",Object ).replace("COLOR", color);
	    WebElement BQboarderele = driver.findElement(By.xpath(finalXpath));
	try
	{
		if(UIHelper.checkForAnElementbyXpath(driver, finalXpath))
		{
			String BQboardercss = BQboarderele.getCssValue(strCssValue);
			
			String[] numbers = BQboardercss.replace("rgba(", "").replace(")", "").trim().split(", ");

			int r = Integer.parseInt(numbers[0].trim());
			int g = Integer.parseInt(numbers[1].trim());
			int b = Integer.parseInt(numbers[2].trim());
			int a = Integer.parseInt(numbers[3].trim());
		
			String bckcolorhex = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b)+Integer.toHexString(a);
			
			System.out.println("bckcolorhex : "+bckcolorhex);
			if(bckcolorhex.equalsIgnoreCase(value))
			{
				report.updateTestLog("Confirm that the object " + Object + " is highlighted with the colour: "+bckcolorhex + " : "+color,
						"The object "+ Object +" is highlighted with the colour: "+bckcolorhex+ " : "+color, Status.PASS);
				
			}
			else
			{
				report.updateTestLog("Confirm that the object is highlighted with the colour: "+bckcolorhex,
						"The object is not highlighted with the colour: "+bckcolorhex, Status.FAIL);
				
			}
		}
		
   }catch (Exception e) {
		
		e.printStackTrace();
		report.updateTestLog("Confirm Filter Highlights",
				"Error in method : verifyFiterHighlights ", Status.FAIL);
	}
  }
   
 //******************************Create alignment and confirm in View Details**********************************//
   //*************************** Added by Sangeetha.L ******************************//
   
   public void createAlignment(int noOfSkills,String foldername,String skillname) {
	
	   try
	   {
		   driver.switchTo().frame("alignmentToolIframe");
			UIHelper.highlightElement(driver,skillDescXpath );
			UIHelper.click(driver, skillDescXpath);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			String finalXpath = skillXpath.replace("CRAFT", skillname);
			UIHelper.highlightElement(driver,skillname );
			UIHelper.click(driver, finalXpath);
			UIHelper.waitFor(driver);					
			
			for (int i=0;i<noOfSkills;i++)
			{
				UIHelper.highlightElement(driver, plusSymbolXpath );
				UIHelper.click(driver, plusSymbolXpath );
				UIHelper.waitFor(driver);
			}
			
			List<WebElement> skillDesc = driver.findElements(By.xpath(alignedSkills));
			
			if(skillDesc.size()==noOfSkills)
			{

				report.updateTestLog("Confirm selected skills appear in the 'Aligned Skills' box to the right of the modal",
						"Number of selected skills for Alignment : "+noOfSkills, Status.PASS);
				
			}
			else
			{
				report.updateTestLog("Confirm selected skills appear in the 'Aligned Skills' box to the right of the modal",
						"Number of selected skills for Alignment : "+noOfSkills, Status.FAIL);
			}
				   
			UIHelper.click(driver, alignSkillsSave);
			UIHelper.waitForPageToLoad(driver);
			driver.switchTo().defaultContent();
			clickOnMouseOverMenu(foldername, "View Details");
			List<WebElement> skillConfirm = driver.findElements(By.xpath(viewDetailsSkills));	
			if(skillConfirm.size()==noOfSkills)
			{

				report.updateTestLog("Confirm in details page, under Course Planner Relationships, that the skills codes are aligned",
						"under Course Planner Relationships, the skills codes are aligned : "+noOfSkills, Status.PASS);
				
			}
			else
			{
				report.updateTestLog("Confirm in details page, under Course Planner Relationships, that the skills codes are aligned",
						"under Course Planner Relationships, the skills codes are not aligned : "+noOfSkills, Status.FAIL);
				
			}
			
			
			
	   }catch (Exception e) {
			
			e.printStackTrace();
			report.updateTestLog("Verify Create Alignment",
					"Error in method : createAlignment()", Status.FAIL);
		}
   }
	   
	   
	   
	 //******************************Remove alignment and confirm in View Details**********************************//
	   //*************************** Added by Sangeetha.L ******************************//
	   
	   public void removeAlignment(int noOfSkills,String foldername) {
		
		   try
		   {
			   driver.switchTo().frame("alignmentToolIframe");
				UIHelper.click(driver, removeSkills);	   
				UIHelper.click(driver, alignSkillsSave);
				UIHelper.waitForPageToLoad(driver);
				
				report.updateTestLog("Go to the right-hand 'Aligned Skills' box and click the X icon to remove one of the aligned skills",
						"One of the skills was removed", Status.DONE);
				
				driver.switchTo().defaultContent();
				clickOnMouseOverMenu(foldername, "View Details");
				
				List<WebElement> skillConfirm = driver.findElements(By.xpath(viewDetailsSkills));	
				if(skillConfirm.size()==noOfSkills)
				{

					report.updateTestLog("Confirm in details page, under Course Planner Relationships, that the skills codes are aligned",
							"under Course Planner Relationships, the skills codes are aligned : "+noOfSkills, Status.PASS);
					
				}
				else
				{
					report.updateTestLog("Confirm in details page, under Course Planner Relationships, that the skills codes are aligned",
							"under Course Planner Relationships, the skills codes are not aligned : "+noOfSkills, Status.FAIL);
					
				}
				
		   }catch (Exception e) {
				
				e.printStackTrace();
				report.updateTestLog("Verify Create Alignment",
						"Error in method : createAlignment()", Status.FAIL);
			}
		 
   }
	   
	   // Added for NALS - Active Preview pop up 
	   
		// Click on Create Button
			public String clickOnOKButtoninActivePreview() {
				
				String activePreviewURL="";
				try {
					UIHelper.waitFor(driver);
					/*String xpath ="//div[@id='prompt_h']";
					String	webElementText = driver.findElement(By.xpath(xpath)).getText();
					System.out.println("ACTIVE PREVIEW Heading :: " + webElementText );
				*/
					
					String xpathofUrl = "/html/body/div[2]/div[1]/div[2]";		
					activePreviewURL = driver.findElement(By.xpath(xpathofUrl)).getText();
					System.out.println("ACTIVE PREVIEW URL:: " + activePreviewURL );
					UIHelper.waitFor(driver);
					
					UIHelper.waitForVisibilityOfEleByXpath(driver, activePreviewOKXpath);
					UIHelper.click(driver, activePreviewOKXpath);
					UIHelper.waitFor(driver);
							
				} catch (Exception e) {
					e.printStackTrace();
				}return activePreviewURL;
			}

   
	   //******************************Added for NALS Job queue UI**********************************//
   
	   String csvFileNameXpath =	"//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td//a[contains(.,'CRAFT')]";
		String userNameXpath =	"//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td//div[contains(text(), 'CRAFT')]";
		String siteNameXpath =	"//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td//a[contains(.,'CRAFT')]";
		String jobTypeXpath ="//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td[2]//div[contains(text(),'Course Import')]";
		String jobTypeXpath1 ="//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td[2]//div[contains(text(),'Collection to Realize')]";
		String serverNameXpath = "//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td[3]//div[contains(text(),'CRAFT')]";
		String progressXpath = "//*[contains(@id,'ALF-CMS-3_x007e_dashboard_x0023_default-jobsList')]//table//tbody[2]//tr//td[3]//div[contains(text(), 'CRAFT')]";
			
		//dropdown values
		 String dropDownXpath =	".//div[@class='toolbar flat-button'] //span[contains(@id,'dashboard_x0023_default-status')]";
	   	String dropDownValueInProgress ="//div[@class='toolbar flat-button']//div[@id='yui-gen10']//ul//li//a[contains(.,'CRAFT')]";
		
		  public void selectInProgressDropDown (String Value) {
		   
		   String finaldropDownValueInProgress = dropDownValueInProgress.replace("CRAFT", dropDownValueInProgress);
		   UIHelper.highlightElement(driver, dropDownXpath);
		   UIHelper.click (driver,dropDownXpath);
		   UIHelper.highlightElement(driver, finaldropDownValueInProgress);
		   UIHelper.click (driver,finaldropDownValueInProgress);
		 	   
	   }
	 	   
	   public void isDataImportStatusDisplayed(String csvFileName,String userName,String reqSiteName,String jobType ,String serverName ,String progress ) {

			try {
				
				/*UIHelper.highlightElement(driver, dropDownXpath);
				
				String themeName = dataTable.getData("MyFiles", "BrowseActionName");
				UIHelper.selectDropdownList(driver, UIHelper.findAnElementbyXpath(driver,dropDownXpath), themeName);
				UIHelper.click (driver,dropDownXpath);
				*/
				
				String finalcsvFileNameXpath = csvFileNameXpath.replace("CRAFT", csvFileName);
				String finaluserNameXpath = userNameXpath.replace("CRAFT", userName);
				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", reqSiteName);
				String finaljobTypeXpath = jobTypeXpath.replace("CRAFT",jobType );
				String finalserverNameXpath = serverNameXpath.replace("CRAFT", serverName);
				String finalprogressXpath = progressXpath.replace("CRAFT", progress);
					
				UIHelper.highlightElement(driver, finalcsvFileNameXpath);
									
					if (UIHelper.checkForAnElementbyXpath(driver,finalcsvFileNameXpath))
					{
						UIHelper.highlightElement(driver, finalcsvFileNameXpath);
						report.updateTestLog("Confirm CSV file name displayed for IN PROGRESS status",
								" CSV filename is displayed ", Status.PASS);	
					}
					else
					{
						report.updateTestLog("Confirm CSV file name displayed for IN PROGRESS status",
								" CSV filename is not displayed ", Status.FAIL);	
					}
					
					UIHelper.highlightElement(driver, finaluserNameXpath);	
					
					if (UIHelper.checkForAnElementbyXpath(driver,finaluserNameXpath))
					{
						UIHelper.highlightElement(driver, finaluserNameXpath);
						report.updateTestLog("Confirm that Username-ALF-CMS-3 is listed as the requestor for IN PROGRESS status",
								" ALF-CMS-3 is listed as the requestor", Status.PASS);	
					}
					else
					{
						report.updateTestLog("Confirm that Username-ALF-CMS-3 is listed as the requestor for IN PROGRESS status",
								"ALF-CMS-3 is listed as the requestor ", Status.FAIL);	
					}
					
					UIHelper.highlightElement(driver, finalsiteNameXpath);	
					
					if (UIHelper.checkForAnElementbyXpath(driver,finalsiteNameXpath))
					{
						UIHelper.highlightElement(driver, finalsiteNameXpath);
						report.updateTestLog("Confirm that the Site name from which you made the request is listed for IN PROGRESS status",
								" Site name is listed", Status.PASS);	
					}
					else
					{
						report.updateTestLog("Confirm that the Site name from which you made the request is listed for IN PROGRESS status",
								"Site name is not listed ", Status.FAIL);	
					}
					
					UIHelper.highlightElement(driver,jobTypeXpath );	
					
					if (UIHelper.checkForAnElementbyXpath(driver,jobTypeXpath))
					{
						UIHelper.highlightElement(driver, jobTypeXpath);
					System.out.println(jobTypeXpath);
					
						report.updateTestLog("Confirm that job is listed as Course Import for IN PROGRESS status",
								" Course Import is listed", Status.PASS);	
					}
					else
					{
						report.updateTestLog("Confirm that the job type is listed for IN PROGRESS status",
								"Course Import is not listed ", Status.FAIL);	
					}
					
					UIHelper.highlightElement(driver,finalserverNameXpath );	
					
					if (UIHelper.checkForAnElementbyXpath(driver,finalserverNameXpath))
					{
						UIHelper.highlightElement(driver, finalserverNameXpath);
						report.updateTestLog("Confirm that the server is listed for IN PROGRESS status",
								" Server name is listed", Status.PASS);	
					}
					else
					{
						report.updateTestLog("Confirm that the server is listed for IN PROGRESS status",
								"Server name is not listed ", Status.FAIL);	
					}
					
					UIHelper.highlightElement(driver,finalprogressXpath );	
					
					if (UIHelper.checkForAnElementbyXpath(driver,finalprogressXpath))
					{
						UIHelper.highlightElement(driver, finalprogressXpath);
						report.updateTestLog("Confirm that the progress is listed as In Progress",
								" Site name is listed", Status.PASS);	
					}
					else
					{
						report.updateTestLog("Confirm that the progress is listed as In Progress ",
								"Site name is not listed ", Status.FAIL);	
					}
					
								
					
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Verify Relationship viewer",
						"Verify Relationship viewer Failed", Status.FAIL);
			}

			
		}
   
   
	   public void isGenerateRealizeStatusDisplayed(String csvFileName,String userName,String reqSiteName,String jobType ,String serverName ,String progress ) {
			
			try {
						
				String finalcsvFileNameXpath = csvFileNameXpath.replace("CRAFT", csvFileName);
				String finaluserNameXpath = userNameXpath.replace("CRAFT", userName);
				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", reqSiteName);
			//	String finaljobTypeXpath = jobTypeXpath.replace("CRAFT",jobType );
				String finalserverNameXpath = serverNameXpath.replace("CRAFT", serverName);
				String finalprogressXpath = progressXpath.replace("CRAFT", progress);
				
				UIHelper.highlightElement(driver, finalcsvFileNameXpath);
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalcsvFileNameXpath))
				{
					UIHelper.highlightElement(driver, finalcsvFileNameXpath);
					report.updateTestLog("Confirm for the export job that the Course CSV filename is listed as a link to the file in the Data Exports folder",
							" CSV filename is listed as a link ", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm for the export job that the Course CSV filename is listed as a link to the file in the Data Exports folder ",
							" CSV filename is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver, finaluserNameXpath);	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finaluserNameXpath))
				{
					UIHelper.highlightElement(driver, finaluserNameXpath);
					report.updateTestLog("Confirm that Username-ALF-CMS-3 is listed as the requestor",
							" ALF-CMS-3 is listed as the requestor", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that Username-ALF-CMS-3 is listed as the requestor",
							"ALF-CMS-3 is listed as the requestor ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver, finalsiteNameXpath);	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalsiteNameXpath))
				{
					UIHelper.highlightElement(driver, finalsiteNameXpath);
					report.updateTestLog("Confirm that the Site name from which you made the request is listed",
							" Site name is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that the Site name from which you made the request is listed ",
							"Site name is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver,jobTypeXpath1 );	
				
				if (UIHelper.checkForAnElementbyXpath(driver,jobTypeXpath1))
				{
					UIHelper.highlightElement(driver, jobTypeXpath1);
					report.updateTestLog("Confirm that job is listed as Collection to Realize ",
							" Collection to Realize is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that job is listed as Collection to Realize ",
							"Collection to Realize is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver,finalserverNameXpath );	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalserverNameXpath))
				{
					UIHelper.highlightElement(driver, finalserverNameXpath);
					report.updateTestLog("Confirm that the server name is listed",
							" Server name is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that the server name is listed",
							"Server name is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver,finalprogressXpath );	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalprogressXpath))
				{
					UIHelper.highlightElement(driver, finalprogressXpath);
					report.updateTestLog("Confirm that the progress is listed as In Progress",
							" Progress is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that the progress is listed as In Progress ",
							"Progress is not listed ", Status.FAIL);	
				}
				
				

			
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Verify Relationship viewer",
						"Verify Relationship viewer Failed", Status.FAIL);
			}

			
		}
  
   
	   public void isDuplicateAllStatusDisplayed(String csvFileName,String userName,String reqSiteName,String jobType ,String serverName ,String progress ) {
			
			try {
						
				String finalcsvFileNameXpath = csvFileNameXpath.replace("CRAFT", csvFileName);
				String finaluserNameXpath = userNameXpath.replace("CRAFT", userName);
				String finalsiteNameXpath = siteNameXpath.replace("CRAFT", reqSiteName);
			//	String finaljobTypeXpath = jobTypeXpath.replace("CRAFT",jobType );
				String finalserverNameXpath = serverNameXpath.replace("CRAFT", serverName);
				String finalprogressXpath = progressXpath.replace("CRAFT", progress);
				
				UIHelper.highlightElement(driver, finalcsvFileNameXpath);
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalcsvFileNameXpath))
				{
					UIHelper.highlightElement(driver, finalcsvFileNameXpath);
					report.updateTestLog("Confirm for the export job that the Course CSV filename is listed as a link to the file in the Data Exports folder",
							" CSV filename is listed as a link ", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm for the export job that the Course CSV filename is listed as a link to the file in the Data Exports folder ",
							" CSV filename is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver, finaluserNameXpath);	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finaluserNameXpath))
				{
					UIHelper.highlightElement(driver, finaluserNameXpath);
					report.updateTestLog("Confirm that Username-ALF-CMS-3 is listed as the requestor",
							" ALF-CMS-3 is listed as the requestor", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that Username-ALF-CMS-3 is listed as the requestor",
							"ALF-CMS-3 is listed as the requestor ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver, finalsiteNameXpath);	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalsiteNameXpath))
				{
					UIHelper.highlightElement(driver, finalsiteNameXpath);
					report.updateTestLog("Confirm that the Site name from which you made the request is listed",
							" Site name is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that the Site name from which you made the request is listed ",
							"Site name is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver,jobTypeXpath1 );	
				
				if (UIHelper.checkForAnElementbyXpath(driver,jobTypeXpath1))
				{
					UIHelper.highlightElement(driver, jobTypeXpath1);
					report.updateTestLog("Confirm that job is listed as Collection to Realize ",
							" Collection to Realize is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that job is listed as Collection to Realize ",
							"Collection to Realize is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver,finalserverNameXpath );	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalserverNameXpath))
				{
					UIHelper.highlightElement(driver, finalserverNameXpath);
					report.updateTestLog("Confirm that the server name is listed",
							" Server name is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that the server name is listed",
							"Server name is not listed ", Status.FAIL);	
				}
				
				UIHelper.highlightElement(driver,finalprogressXpath );	
				
				if (UIHelper.checkForAnElementbyXpath(driver,finalprogressXpath))
				{
					UIHelper.highlightElement(driver, finalprogressXpath);
					report.updateTestLog("Confirm that the progress is listed as In Progress",
							" Progress is listed", Status.PASS);	
				}
				else
				{
					report.updateTestLog("Confirm that the progress is listed as In Progress ",
							"Progress is not listed ", Status.FAIL);	
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				report.updateTestLog("Verify Relationship viewer",
						"Verify Relationship viewer Failed", Status.FAIL);
			}

			
		}
	   
	 //Click on Create Rules under Manage Rules Option NALS	      
	   
		public void clickOnCreateRules() {
			try {
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, createRulesLinkXpath);
				UIHelper.click(driver, createRulesLinkXpath);
				UIHelper.waitFor(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//Method Added as part of NALS
		public void createRulesBasicData(String ruleDetails,String performAction) {
			try {
				String name = "", mimetype = "";
				String splittedFileValues[] = ruleDetails.split(",");

				if (splittedFileValues != null) {
				name = getFieldValueFromExcelForCreateObjects(
							splittedFileValues, "name:");
				mimetype = getFieldValueFromExcelForCreateObjects(
							splittedFileValues, "mimetype:");
					enterBasicDataForCreateRules(name, mimetype,performAction);
					clickOnCreateBtnForRules();
				}
			} catch(Exception e) {
					
				}
			}
		
		//Method and Variables declared as part of NALS
		private String nameFieldXpathInCreateRules = "//*[@id=\"template_x002e_rule-edit_x002e_rule-edit_x0023_default-title\"]";
		private String mimeTypeDropdownXpathInCreateRules = ".//*[contains(@id,'alf-id5')]";
		private String mimeTypeOptionDropdownXpathInCreateRules = ".//*[contains(@id,'alf-id15')]";
		private String performActionDropdownXpathInCreateRules="//*[@id=\"alf-id11\"]";
		private String runInBackgroundXpath="//*[@id=\\\"template_x002e_rule-edit_x002e_rule-edit_x0023_default-executeAsynchronously\\\"]";
		public void enterBasicDataForCreateRules(String name, String mimetype,String performAction) {

			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						nameFieldXpathInCreateRules);

				if (!name.isEmpty()) {
					UIHelper.sendKeysToAnElementByXpath(driver,
							nameFieldXpathInCreateRules, name);
				}

				if (!mimetype.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(mimeTypeDropdownXpathInCreateRules)));
					selectBox.selectByVisibleText("Mimetype");
					Select selectBox1 = new Select(driver.findElement(By.xpath(mimeTypeOptionDropdownXpathInCreateRules)));
					selectBox1.selectByVisibleText("Microsoft Excel 2007");
				}
				

				
				if(!performAction.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(performActionDropdownXpathInCreateRules)));
					selectBox.selectByVisibleText(performAction);
				}
				
				driver.findElement(By.xpath(runInBackgroundXpath)).click();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private String saveBtnXpathInCreateRules = "//*[@id=\"template_x002e_rule-edit_x002e_rule-edit_x0023_default-create-button-button\"]";
		// Click on create Button
		public void clickOnCreateBtnForRules() {
			try {
				UIHelper.click(driver, saveBtnXpathInCreateRules);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click on 'Create' button",
						"User clicked the 'Create' button", Status.DONE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	// Create Content objects from Create menu items
		// Enter Data in fields
		public void enterBasicDataForDynamicContentObject(String name, String title,
				String description, String productType,String dynamiccontentType, String folioprefix,
				String foliostyle, String foliostart, String tocIncludeFrom, String tocIncludeTo,String aggregationtype) {
			try {
				UIHelper.waitForVisibilityOfEleByXpath(driver,
						nameFieldXpathInCreateObject);
				UIHelper.waitFor(driver);
				System.out.println("name inside enterBasicDataForDynamicContentObject"+name);
				if (!name.isEmpty()) {
					System.out.println("1");
					UIHelper.sendKeysToAnElementByXpath(driver,nameFieldXpathInCreateObject, name);
				}

				if (!title.isEmpty()) {
					UIHelper.sendKeysToAnElementByXpath(driver, titleXpath, title);
				}

				if (!description.isEmpty()) {
					UIHelper.sendKeysToAnElementByXpath(driver, descriptionXpath,
							description);
				}

				if (!productType.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(productTypeDropdownXpathInCreateObject)));
					selectBox.selectByIndex(6);
				}

				if (!dynamiccontentType.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(dynamiccontentTypeDropdownXpathInCreateObject)));
					selectBox.selectByIndex(3);
					}

				if (!folioprefix.isEmpty()) {
					UIHelper.sendKeysToAnElementByXpath(driver, folioprefixXpath,
							folioprefix);
				}
				
				if (!foliostyle.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(foliostyleXpath)));
					selectBox.selectByIndex(1);	
				}
				
				if (!foliostart.isEmpty()) {
					UIHelper.sendKeysToAnElementByXpath(driver, foliostartXpath,
							foliostart);
				}

				if (!tocIncludeFrom.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(tocIncludeFromXpath)));
					selectBox.selectByIndex(1);
				} 
				
				if (!tocIncludeTo.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(tocIncludeToXpath)));
					selectBox.selectByIndex(1);
				}
				
				if (!aggregationtype.isEmpty()) {
					Select selectBox = new Select(driver.findElement(By.xpath(aggregationtypeXpath)));
					selectBox.selectByIndex(1);
				}
				report.updateTestLog("Input data to create 'Content Object'",
						"User able to enter data for 'Content Object'"
								+ "<br>Name: " + name + ", " + "Title: " + title
								+ ", <br>" + "Description: " + description
								+ ", <br>" + "productType: " + productType
								+ "Dynamic Content Type: " + dynamiccontentType
								+ "<br>folio prefix: " + folioprefix
								+ "folio style: " + foliostyle
								+ "<br>folio start: " + foliostart
								+ "toc Include From:"+tocIncludeFrom
								+"toc Include To"+tocIncludeTo, Status.DONE);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public void clickonprintcsvrealizebox() {

			try {

				UIHelper.waitForVisibilityOfEleByXpath(driver, printcsvrealize);

				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(
						driver, printcsvrealize))) {
					report.updateTestLog("Print CSV filter options is available",
							"Print CSV filter options is available", Status.DONE);
					
					UIHelper.click(driver, realizecsvboxOKbutton);
					UIHelper.waitForPageToLoad(driver);
					if (UIHelper.isWebElementDisplayed(UIHelper
							.findAnElementbyXpath(driver, programscreenxpath))) {
						report.updateTestLog("Click on ok button", "clicked on ok",
								Status.PASS);
					} else {
						report.updateTestLog("Click on ok button",
								"clicked on ok Failed", Status.FAIL);
					}

				} else {
					report.updateTestLog(
							"Print CSV filter options is available?",
							"Print CSV filter options is not available",
							Status.FAIL);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		public void clickMoreSettingsOptionWithProductType(String fileOrfolderName,
				String moreSettingsOptName) {
			try {
				String finalXpathForCopyToFolderLink = tempXpathForMoreSettingsOptLink
						.replace("CRAFT", fileOrfolderName).replace("MORE_OPT",
								moreSettingsOptName);
				UIHelper.waitFor(driver);
				WebElement moreSettingsOptElement = UIHelper.findAnElementbyXpath(
						driver, finalXpathForCopyToFolderLink);
				UIHelper.highlightElement(driver, moreSettingsOptElement);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();",
						moreSettingsOptElement);
				UIHelper.waitFor(driver);
				UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-product-type-checkbox')]").click();	
				UIHelper.waitFor(driver);
				Select selectBox = new Select(driver.findElement(By.xpath("//*[@id=\"template_x002e_detailed-list-view_x002e_assembly-view_x0023_default-generate-manifest-product-type-select\"]")));
				selectBox.selectByIndex(19);
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver, summaryOKbutton);
				UIHelper.waitFor(driver);
				UIHelper.click(driver, summaryOKbutton);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				report.updateTestLog("Click on " + moreSettingsOptName,
						"User able to click the " + moreSettingsOptName,
						Status.PASS);	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Click on 'More Settings' option link for file or Folder
		public void clickOnMoreSettingWithProductType(String fileOrFolderName) {
			try {
				String finalSelectFolderChkboxXpath = tempSelectFolderChkboxXpath
						.replace("CRAFT", fileOrFolderName);
				String finalselectedFolderORFileItemXpath = selectedFolderORFileItemXpath
						.replace("CRAFT", fileOrFolderName);
				String finalXpathForMoreOptionLink = tempXpathForMoreOptionLink
						.replace("CRAFT", fileOrFolderName);

				

				List<WebElement> uploadedFileOrFolderTitleEleList = UIHelper
						.findListOfElementsbyXpath(uploadedFilesTitlesXpath, driver);

				for (WebElement ele : uploadedFileOrFolderTitleEleList) {
					if (ele.getText().equalsIgnoreCase(fileOrFolderName)) {
						// UIHelper.scrollToAnElement(ele);
						UIHelper.highlightElement(driver, ele);
						WebElement chkboxElement = UIHelper.findAnElementbyXpath(
								driver, finalSelectFolderChkboxXpath);

						UIHelper.highlightElement(driver, chkboxElement);
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();",
								chkboxElement);
						UIHelper.waitFor(driver);

						WebElement folderEle = UIHelper.findAnElementbyXpath(
								driver, finalselectedFolderORFileItemXpath);
						UIHelper.highlightElement(driver, folderEle);
						UIHelper.mouseOveranElement(driver, folderEle);

						WebElement moreSettingsEle = UIHelper.findAnElementbyXpath(
								driver, finalXpathForMoreOptionLink);
						UIHelper.highlightElement(driver, moreSettingsEle);
						report.updateTestLog(
								"Click on 'More Settings' Link Option",
								"User successfully clicked the <b> 'More Settings'</b> Option using "
										+ fileOrFolderName, Status.PASS);
						executor.executeScript("arguments[0].click();",
								moreSettingsEle);
						UIHelper.waitFor(driver);

						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Click on More settings option
		public void commonMethodForClickOnMoreSettingsOptionWithProductType(
				String fileOrfolderName, String moreSettingsOptName) {
			try {
				clickMoreSettingsOptionOnly(fileOrfolderName, moreSettingsOptName);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, messageXpath);
				UIHelper.waitForPageToLoad(driver);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private String siteListXpath = ".//*[@class='yui-g']//tbody[@class='yui-dt-data']//h3//a";
		public boolean addCrossSiteReferenceAsChild(String siteName) {
			boolean flag = false;
			try {
				UIHelper.waitFor(driver);
				List<WebElement> siteListXpathEle = driver.findElements(By
						.xpath(siteListXpath));
				for (WebElement ele : siteListXpathEle) {
					if (ele.getText().contains(siteName)) {
						UIHelper.highlightElement(driver, ele);
						UIHelper.scrollToAnElement(ele);
						flag = true;
						break;

					}
				}
			} catch (Exception e) {
				report.updateTestLog("Check site is available",
						"Check site is Failed", Status.FAIL);
			}
			return flag;
		}
			
		}


