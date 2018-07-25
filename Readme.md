# Alfresco - Content Management System#

# Prerequisites #

1. JDK 6+
2. Maven 2+
3. Firefox 10+

# Windows configuration #

Once all the required tools are installed:

create two new system variables:

JAVA_HOME e.g.: JAVA_HOME=C:\Program Files\Java\jdk1.7.0_10

M2_HOME e.g.: M2_HOME=C:\Program Files\apache-maven-3.3.3

Add paths to the java and maven folders containing corresponding binaries to the Path variable,

e.g.:
Path=WHATEVER_WAS_THERE_BEFORE;C:\Program Files\Java\jdk1.7.0_10\bin\;C:\Program Files\apache-maven-3.3.3\bin\;C:\Program Files (x86)\Mozilla Firefox\

Maintain InternetExploreDriver, ChromeDriver using below path
C:/Javalibs/Selenium/Browser Drivers/

# Project Structure #
	
	src/test/java
		com.cognizant.automation.alfresco.tests
			Tests.java
		testscripts.sanity
			Test Cases classes
			
	src/main/java
		com.cognizant.automation.alfresco.allocator
			Allocator.java
			PaeallelRunner.java
			QcTestRunner.java
		com.cognizant.automation.alfresco.functionallibs
			FunctionalLibrary.java
		com.cognizant.automation.alfresco.pages
			Pages.java
		com.cognizant.framework
			Framework related classes
		com.cognizant.framework.selenium
			Framework+Selenium related classes
		com.home.automation.util
			Common methods classes
			
	src/test/resources
		suit_file
			testng_sanity.xml
			
	Datatables
		Sanity.xls
		
	Results
	
	Global Settings.properties
				
Dependencies Install command:
=============================
mvn clean package

Automation Script execution command:
====================================
mvn clean verify -Dsuite.fileName=SuitFileName

Ex:
mvn clean verify -Dsuite.fileName=testng_sanity.xml -DexecutionMode=Local