package com.pearson.framework;

import java.io.File;
import java.util.Properties;

import com.pearson.automation.utils.FileUtil;

public class TimeStamp
{
  private static volatile String m_reportPathWithTimeStamp;
  private static String filePath = "src/test/resources/Framework/TimeStampDetails.txt";
  
  public static String getInstance()
  {
    if (m_reportPathWithTimeStamp == null) {
      synchronized (TimeStamp.class) {
        if (m_reportPathWithTimeStamp == null) {
          FrameworkParameters frameworkParameters = 
            FrameworkParameters.getInstance();

          if (frameworkParameters.getRelativePath() == null) {
            throw new FrameworkException("FrameworkParameters.relativePath is not set!");
          }
          if (frameworkParameters.getRunConfiguration() == null) {
            throw new FrameworkException("FrameworkParameters.runConfiguration is not set!");
          }

          Properties properties = Settings.getInstance();
          String timeStamp = 
            "Run_" + 
            Util.getCurrentFormattedTime(properties.getProperty("DateFormatString"))
            .replace(" ", "_").replace(":", "-");

          new FileUtil().writeTextToFile(timeStamp, filePath);
          m_reportPathWithTimeStamp = 
            frameworkParameters.getRelativePath() + 
            Util.getFileSeparator() + "Results" + 
            Util.getFileSeparator() + frameworkParameters.getRunConfiguration() + 
            Util.getFileSeparator() + timeStamp;

          new File(m_reportPathWithTimeStamp).mkdirs();
        }
      }
    }

    return m_reportPathWithTimeStamp;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    throw new CloneNotSupportedException();
  }
}