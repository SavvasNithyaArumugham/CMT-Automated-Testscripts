package com.pearson.framework.selenium;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.opera.core.systems.OperaDriver;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.Settings;
import org.testng.ITestContext;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
	private static Properties properties;
	static ITestContext testContext;
	public static WebDriver getDriver(Browser browser) {
		WebDriver driver = null;
		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties
				.getProperty("ProxyRequired"));

		switch (browser) {
		case Chrome:
			System.setProperty("webdriver.chrome.driver",
					properties.getProperty("ChromeDriverPath"));
			driver = new ChromeDriver(setChromeDownloadPath());
			break;
		case Firefox:
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("plugin.state.java", 2);
			driver = new FirefoxDriver(profile);
			break;
		case HtmlUnit:
			driver = new HtmlUnitDriver();

			if (proxyRequired) {
				boolean proxyAuthenticationRequired = Boolean
						.parseBoolean(properties
								.getProperty("ProxyAuthenticationRequired"));
				if (proxyAuthenticationRequired) {
					driver = new HtmlUnitDriver() {
						protected WebClient modifyWebClient(WebClient client) {
							DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
							credentialsProvider
									.addNTLMCredentials(
											WebDriverFactory.properties
													.getProperty("Username"),
											WebDriverFactory.properties
													.getProperty("Password"),
											WebDriverFactory.properties
													.getProperty("ProxyHost"),
											Integer.parseInt(WebDriverFactory.properties
													.getProperty("ProxyPort")),
											"", WebDriverFactory.properties
													.getProperty("Domain"));
							client.setCredentialsProvider(credentialsProvider);
							return client;
						}
					};
				}

				((HtmlUnitDriver) driver).setProxy(
						properties.getProperty("ProxyHost"),
						Integer.parseInt(properties.getProperty("ProxyPort")));
			}

			break;
		case InternetExplorer:
			System.setProperty("webdriver.ie.driver",
					properties.getProperty("InternetExplorerDriverPath"));
			driver = new InternetExplorerDriver();
			break;
		case Opera:
			if (proxyRequired) {
				DesiredCapabilities desiredCapabilities = getProxyCapabilities();
				driver = new OperaDriver(desiredCapabilities);
			} else {
				driver = new OperaDriver();
			}

			break;
		case Safari:
			driver = new SafariDriver();
			break;
		default:
			throw new FrameworkException("Unhandled browser!");
		}

		return driver;
	}

	private static DesiredCapabilities getProxyCapabilities() {
		Proxy proxy = new Proxy();
		proxy.setProxyType(Proxy.ProxyType.MANUAL);

		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":"
				+ properties.getProperty("ProxyPort");

		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("proxy", proxy);

		return desiredCapabilities;
	}

	public static WebDriver getDriver(Browser browser, String remoteUrl) {
		return getDriver(browser, null, null, remoteUrl);
	}

	public static WebDriver getDriver(Browser browser, String browserVersion,
			Platform platform, String remoteUrl) {
		properties = Settings.getInstance();
		URL url;
		boolean proxyRequired = Boolean.parseBoolean(properties
				.getProperty("ProxyRequired"));

		DesiredCapabilities desiredCapabilities = null;
		if (((browser.equals(Browser.HtmlUnit)) || (browser
				.equals(Browser.Opera))) && (proxyRequired))
			desiredCapabilities = getProxyCapabilities();
		else {
			desiredCapabilities = new DesiredCapabilities();
		}

		desiredCapabilities.setBrowserName(browser.getValue());

		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}

		desiredCapabilities.setJavascriptEnabled(true);
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"The specified remote URL is malformed");
		}
		return new RemoteWebDriver(url, desiredCapabilities);
	}

	private static DesiredCapabilities setChromeDownloadPath() {
		String name = "NA";
		String downloadFilepath = properties.getProperty("DefaultDownloadPath");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
	
		if(properties.getProperty("HeadLess").equals("True")) {
			
			options.addArguments("--headless");
			options.addArguments("window-size=1200x600");
			options.addArguments("--enable-javascript");
		}
		 try {
			name = System.getProperty("suite.fileName");
			if(name.equals("testng_translationgerman.xml")) {
				options.addArguments("--lang=de-de"); //To open the chrome browser in German (Germany) - Deutsch (Deutschland) 
			}else if(name.equals("testng_translationfrench.xml")){
				options.addArguments("--lang=fr-ca");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//options.addArguments("--test-type");
		ArrayList<String> chromeArguments = new ArrayList<String>();
		chromeArguments.add("--disable-extensions");		
		chromeArguments.add("start-maximized");
		options.addArguments(chromeArguments);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		return cap;
	}
}