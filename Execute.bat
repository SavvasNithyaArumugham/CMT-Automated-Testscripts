@ECHO OFF

Echo "Enter Number any one of Environment from the below List"
Echo.
Echo 1- qawip.cms.pearson.com
Echo 2- qaarc.pearsoncms.com
Echo 3- usppewip.pearsoncms.com
Echo 4- ukppewip.pearsoncms.com
Echo 5- apppewip.pearsoncms.com
Echo 6- usppearc.pearsoncms.com
Echo 7- ukppearc.pearsoncms.com
Echo 8- apppearc.pearsoncms.com

Echo.

javac src\main\java\com\pearson\automation\utils\ENV_NAME.java
cd /d src\main\java
java com.pearson.automation.utils.ENV_NAME
Echo.


Echo Suite files available.Please refer carefully before execution.
DEL /F /S /Q /A %~dp0\src\test\resources\suite_files\testng_fail.xml
Echo.
set /p
cd /d %~dp0\src\test\resources\suite_files
dir /b
Echo.
Echo.
	::-------------------------------------------------------------------------

Echo "Enter no of files need to be executed. Between one to Nine"
Echo.
Echo.
set /p SuiteNo="No of Suite : "
Echo.
Echo.
If "%SuiteNo%"=="1" goto ONE
If "%SuiteNo%"=="2" goto TWO
If "%SuiteNo%"=="3" goto THREE
If "%SuiteNo%"=="4" goto FOUR
If "%SuiteNo%"=="5" goto FIVE
If "%SuiteNo%"=="6" goto SIX  
If "%SuiteNo%"=="7" goto SEVEN
If "%SuiteNo%"=="8" goto EIGHT
If "%SuiteNo%"=="9" goto NINE

Echo.
Echo.	
	
:ONE
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local"
goto END

:TWO
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local"
goto END

:THREE
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local"
goto END

:FOUR
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
set /p fourSuite="Enter Suite name 4 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fourSuite% -DexecutionMode=Local"
goto END


:FIVE
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
set /p fourSuite="Enter Suite name 4 : "
set /p fiveSuite="Enter Suite name 5 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fourSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fiveSuite% -DexecutionMode=Local"
goto END

:SIX
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
set /p fourSuite="Enter Suite name 4 : "
set /p fiveSuite="Enter Suite name 5 : "
set /p SixSuite="Enter Suite name 6 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fourSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fiveSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%SixSuite% -DexecutionMode=Local"
goto END


:SEVEN
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
set /p fourSuite="Enter Suite name 4 : "
set /p fiveSuite="Enter Suite name 5 : "
set /p SixSuite="Enter Suite name 6 : "
set /p sevenSuite="Enter Suite name 7 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fourSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fiveSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%SixSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%sevenSuite% -DexecutionMode=Local"
goto END

:EIGHT
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
set /p fourSuite="Enter Suite name 4 : "
set /p fiveSuite="Enter Suite name 5 : "
set /p SixSuite="Enter Suite name 6 : "
set /p sevenSuite="Enter Suite name 7 : "
set /p eightSuite="Enter Suite name 8 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fourSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fiveSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%SixSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%sevenSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%eightSuite% -DexecutionMode=Local"
goto END

:NINE
Echo "Enter the full suite name without spelling mistake, Press enter in they is no value for Any suite"
Echo.
Echo.
set /p firstSuite="Enter Suite name 1 : "
set /p secondSuite="Enter Suite name 2 : "
set /p thirdSuite="Enter Suite name 3 : "
set /p fourSuite="Enter Suite name 4 : "
set /p fiveSuite="Enter Suite name 5 : "
set /p SixSuite="Enter Suite name 6 : "
set /p sevenSuite="Enter Suite name 7 : "
set /p eightSuite="Enter Suite name 8 : "
set /p nineSuite="Enter Suite name 9 : "
cmd.exe /K "cd /d %~dp0 && mvn clean verify -Dsuite.fileName=%firstSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%secondSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%thirdSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fourSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%fiveSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%SixSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%sevenSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%eightSuite% -DexecutionMode=Local && mvn clean verify -Dsuite.fileName=%nineSuite% -DexecutionMode=Local"
goto END



:END
Echo.
Echo.
Echo "Execution Completed for %SuiteNo% Suite Files."

:ERROR
Echo "Please Enter the Correct no of files to be executed between 1 to 9."
