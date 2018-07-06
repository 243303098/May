package com.d1m.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * *.
 *
 * @author d1m
 * @version V1.2
 */
public class DriverFactory {

    /**
     * The chromedriver.
     */
    private String chromedriver;

    /**
     * The fire bug.
     */
    private String fireBug;

    private String autoauth;

    /**
     * The firefoxdriver.
     */
    private String firefoxdriver;

    /**
     * The p.
     */
    private Properties p = null;

    /**
     * The IE driver server.
     */
    private String IEDriverServer;

    /**
     * The config.
     */
    private String config = System.getProperty("user.dir") + "/config.properties";

    /**
     * The log.
     */
    static Logger log = LoggerFactory.getLogger(DriverFactory.class);

    /**
     * The driver.
     */
    public WebDriver driver = null;

    /**
     * The driverfactory.
     */
    public static DriverFactory driverfactory;

    // public static WebDriver getHtmlUnit() {
    // HtmlUnitDriver driver = new HtmlUnitDriver();
    // log.info("Create HtmlUnitDrive ");
    // return driver;
    // }

    /**
     * Gets the chrome driver.
     *
     * @return the chrome driver
     * @author d1m
     */
    public WebDriver getChromeDriver() {

        chromedriver = ConfigUtil.getProperty("chromedriver", "common.properties");
        log.info("chrome driver path is " + chromedriver);
        System.setProperty("webdriver.chrome.driver", chromedriver);
        // ChromeDriverService.Builder builder=new
        // ChromeDriverService.Builder();
        // File file=new File(chromedriver);
        // int port=12643;
        // ChromeDriverService
        // service=builder.usingDriverExecutable(file).usingPort(port).build();
        // try {
        // service.start();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        ChromeOptions options = new ChromeOptions();
        // options.addExtensions(new File(""));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        options.addArguments("--ExcuteAllCase-type", "--start-maximized");
        driver = new ChromeDriver(options);
        log.info("Create ChromeDrive ");
        return driver;
    }

    /**
     * Gets the firefox driver.
     *
     * @return the firefox driver
     * @author d1m
     */
    public WebDriver getFirefoxDriver() {
        //fireBug = ConfigUtil.getProperty("fireBug", "common.properties");
        //autoauth = CommonUtils.getConfigValue("autoauth", CommonConfig.config);
        //File file = new File(fireBug);
        //File file1 = new File(autoauth);
        //FirefoxProfile profile = new FirefoxProfile();

        // profile.setPreference("network.proxy.type", 2);
        // profile.setPreference("network.proxy.autoconfig_url",
        // profile.setPreference("network.proxy.no_proxies_on", "localhost");

//        try {
//            profile.addExtension(file);
//            profile.setPreference("extensions.firebug.currentVersion", "2.0.17");
//            //profile.setPreference("extensions.autoauth.currentVersion", "2.1.1");
//        } catch (Exception e3) {
//            e3.printStackTrace();
//        }
        // profile.setPreference("browser.download.folderList", 2);
        // profile.setPreference("browser.download.dir", "C:\\selenium");
//		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//				"application/octet-stream, application/vnd.ms-excel, text/csv, application/zip,application/exe");
        // Upgrade in Selenium 3.11.0
        //FirefoxOptions option = new FirefoxOptions().setProfile(profile);
        driver = new FirefoxDriver();
        log.info("Create FirefoxDriver ");
        return driver;

    }

    /**
     * Gets the IE driver.
     *
     * @return the IE driver
     * @author d1m
     */
    @SuppressWarnings("deprecation")
    public synchronized WebDriver getIEDriver() {

        IEDriverServer = ConfigUtil.getProperty("IEDriverServer", "common.properties");
        System.setProperty("webdriver.ie.driver", IEDriverServer);
        String PROXY = "http://proxy:8083";
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability("ignoreProtectedModeSettings", true);
        caps.setCapability(CapabilityType.PROXY, proxy);
        //InternetExplorerOptions option = new InternetExplorerOptions(caps);
        driver = new InternetExplorerDriver(caps);
        return driver;
    }


    /**
     * Gets the single instance of DriverFactory.
     *
     * @return single instance of DriverFactory
     * @author d1m
     */
    public static DriverFactory getInstance() {
        if (driverfactory == null) {
            synchronized (DriverFactory.class) {
                driverfactory = new DriverFactory();
            }
        }
        return driverfactory;
    }

    /**
     * Instantiates a new driver factory.
     */
    public DriverFactory() {

    }

    /**
     * Close.
     *
     * @author d1m Call GC
     */
    public static void close() {
        if (driverfactory != null) {
            driverfactory = null;
        }
    }

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public WebDriver getDriver() {
        return driver;
    }

}
