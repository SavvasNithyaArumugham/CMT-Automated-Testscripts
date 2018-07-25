package testscripts.amts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.FrameworkException;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class JReport
{
	Properties properties = new Properties();
	
	@Test
	public void runJReport() throws InterruptedException
	{
		publishJenkinsReport();
	}
	
	public void publishJenkinsReport()
	{
		try
	    {
	      properties.load(new FileInputStream("Global Settings.properties"));
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	      throw new FrameworkException("FileNotFoundException while loading the Global Settings file");
	    } catch (IOException e) {
	      e.printStackTrace();
	      throw new FrameworkException("IOException while loading the Global Settings file");
	    }
		
		try {
			String executionModeValue = System.getProperty("executionMode");
			
			if(executionModeValue.equalsIgnoreCase("Jenkins"))
			{
				String jenkinsReportPath = properties.getProperty("JenkinsReport");
				String screenshotPath = properties.getProperty("ScreenshotPath");
				String defaultJobsScreenshotPath = properties.getProperty("DefaultJobsScreenshotPath");
				String jenkinsBuildPath = properties.getProperty("JenkinsBuildPath");
				String jobNameValue = System.getProperty("jobName");
				String buildNumberValue = System.getProperty("buildNumber");
				String scenarioDetailsPath = properties.getProperty("ScenarioStorePath");
				String scenarioValue = new FileUtil().readDataFromFile(scenarioDetailsPath);
				String timeStampDetailsPath = properties.getProperty("TimeStampDetailsPath");
				String timeStampValue = new FileUtil().readDataFromFile(timeStampDetailsPath);
				String finalDefaultJobsScreenshotPath = defaultJobsScreenshotPath.replace("JOBNAME", jobNameValue);
				String finalJenkinsReportPath = jenkinsReportPath.replace("JOBNAME", jobNameValue).replace("SCENARIO", scenarioValue).replace("TIMESTAMP", timeStampValue);
				String finalJenkinsScreenshotPath = screenshotPath.replace("JOBNAME", jobNameValue).replace("SCENARIO", scenarioValue).replace("TIMESTAMP", timeStampValue);
				String finalJenkinsBuildPath = jenkinsBuildPath.replace("JOBNAME", jobNameValue).replace("BUILDNUMBER", buildNumberValue);
				
				File srcDir = new File(finalJenkinsReportPath);
				File destDir = new File(finalJenkinsBuildPath);
				
				FileUtils.copyDirectory(srcDir, destDir);
				
				File srcDirForScreenshots = new File(finalJenkinsScreenshotPath);
				File destDirForScreenshots = new File(finalDefaultJobsScreenshotPath);
				
				FileUtils.copyDirectory(srcDirForScreenshots, destDirForScreenshots);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}