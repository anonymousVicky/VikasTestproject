package runThrghTestNG;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.openqa.selenium.Dimension;

@Listeners({runThrghTestNG.TestNGCustomReport.class})

public class BaseClass {

    public static WebDriver driver;
    public static String environment;
    public static String browser;
    public static String test;
    public static String url;
    public static String os;
    public static String currentURL;
    public static File directory = new File(".");
    //public IsPresent ip = new IsPresent();
    public static Map<String, Integer> browsers = null;
    public static Map<String, Integer> oss = null;
    public static Properties CONFIGOBJ = null;
    public static Properties CONFIGPROP = null;
    public static Properties CONFIGCRED = null;
    public static String text = null;

    public void initMaps() {
        if (BaseClass.browsers == null) {
            BaseClass.browsers = new HashMap<String, Integer>();
            BaseClass.browsers.put("chrome", 1);
            BaseClass.browsers.put("ie", 2);
            BaseClass.browsers.put("ff", 3);
            BaseClass.browsers.put("android", 4);

        }

        if (BaseClass.oss == null) {
            BaseClass.oss = new HashMap<String, Integer>();
            BaseClass.oss.put("linux32", 1);
            BaseClass.oss.put("linux64", 1);
            BaseClass.oss.put("mac", 1);
            BaseClass.oss.put("win", 2);

        }
    }

    /**
     * @param url
     * @param program
     * @param environment
     * @param browser
     * @param os
     * @param test
     * @throws Exception
     */
    @BeforeTest(groups = {"prerequisite"})
    @Parameters({"url", "environment", "browser", "os", "test"})
    public void setUp(String url, String environment, String browser, String os, String test) throws Exception {

        Logger.getRootLogger().setLevel(Level.OFF);
        BaseClass.environment = environment;
        BaseClass.browser = browser;
        BaseClass.test = test;
        BaseClass.url = url;
        BaseClass.os = os;

        System.out.println("url: " + url);
        System.out.println("environment: " + BaseClass.environment);
        System.out.println("browser: " + BaseClass.browser);
        System.out.println("os: " + os);
        System.out.println("test: " + BaseClass.test);

        this.initMaps();
        int browserId = BaseClass.browsers.get(browser);
        int osId = BaseClass.oss.get(os);

        switch (browserId) {
            case 1: {
                String chromDrvrPath;
                chromDrvrPath = directory.getCanonicalPath() + File.separator + "lib" + File.separator;

                os:
                switch (osId) {
                    case 1:
                        System.out.println("initialize OS: " + os);
                        System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver");
                        break os;
                    case 2:
                        System.out.println("initialize  OS: " + os);
                        System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver.exe");
                        break os;
                    default:
                    //Utility.illegalStateException("Invalid OS paramter, expected values 'linux32||linux64||mac||win'");
                }

                //ChromeOptions feature does not work on 'MAC' OS
                if (os.equalsIgnoreCase("mac")) {
                    System.out.println("Opening chrome on MAC");

                    System.setProperty("webdriver.chrome.driver", chromDrvrPath + "chromedriver_" + os + File.separator + "chromedriver");
                      //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    //capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("test-type");
                    options.addArguments("--disable-extensions");

                    driver = new ChromeDriver(options);
                    Dimension dd = new Dimension(1280, 800);
                    driver.manage().window().setSize(dd);

                    //((JavascriptExecutor) driver).executeScript("window.open('','chromeBrowser','width=1280,height=800,top=0,left=0')");
                    //driver.close();
                    //driver.switchTo().window("chromeBrowser");
                } else {
                    System.out.println("Opening chrome");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-extensions");
                    options.addArguments("test-type");
                    driver = new ChromeDriver(options);
                }
                Reporter.log("Browser: " + browser);
                Reporter.log("OS: " + os);
                break;

            }
            case 2: {
                String IEDrvrPath;
                IEDrvrPath = directory.getCanonicalPath() + File.separator + "lib" + File.separator;

                os:
                switch (osId) {
                    case 1:
                        System.out.println("initialize OS: " + os);
                        System.setProperty("webdriver.ie.driver", IEDrvrPath + "IEDriverServer_" + os + File.separator + "IEDriverServer");
                        break os;
                    case 2:
                        System.out.println("initialize  OS: " + os);
                        System.setProperty("webdriver.ie.driver", IEDrvrPath + "IEDriverServer_" + os + File.separator + "IEDriverServer.exe");
                        break os;
                    default:
                    //  Utility.illegalStateException("Invalid OS paramter, expected values 'linux32||linux64||mac||win'");
                }
                if (os.equalsIgnoreCase("mac")) {
                    System.out.println("IE not supported on MAC");
                } else {
                    System.out.println("Opening IE on windows");
                    DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                    caps.setCapability("nativeEvents", false);
                    driver = new InternetExplorerDriver(caps);
                    driver.manage().window().maximize();
                }
                break;
            }
            case 3: {
                os:
                switch (osId) {
                    case 1:
                        System.out.println("initialize OS: " + os);
                        break os;
                    case 2:
                        System.out.println("initialize  OS: " + os);
                        break os;
                    default:
                    // Utility.illegalStateException("Invalid OS paramter, expected values 'linux32||linux64||mac||win'");
                }
                System.out.println("Opening firefox");
                driver = new FirefoxDriver();

                Reporter.log("Browser: firefox");
                driver.manage().window().maximize();

                break;
            }

            case 4: {
                os:
                switch (osId) {
                    case 1:
                        System.out.println("initialize OS: " + os);
                        break os;
                    case 2:
                        System.out.println("initialize  OS: " + os);
                        break os;
                    default:
                    // Utility.illegalStateException("Invalid OS paramter, expected values 'linux32||linux64||mac||win'");
                }
                System.out.println("Opening Android Browser");
             //   driver = new AndroidDriver("http://192.168.1.3:8080/wd/hub");  //change the ip of android device

                Reporter.log("Browser: android");
                    //driver.manage().window().maximize();  //not compatible with android

                break;
            }

            default: {
                System.out.println("Bad choice of Browser-OS comatibility");
            }

        }

        driver.get(BaseClass.url);
    }

    /**
     * The annotated method will be run after all the test methods belonging to
     * the classes inside the <test> tag have run.
     *
     * @throws Exception
     */
    @BeforeTest
    public void initObjects() {
        if (CONFIGOBJ == null) {

            CONFIGOBJ = new Properties();
            try {
                FileInputStream fs = new FileInputStream(
                        System.getProperty("user.dir")
                        + "//src//Configuration//object.properties");
                CONFIGOBJ.load(fs);

            } catch (Exception e) {
            }
        }

    }

    @BeforeTest
    public void initConfigurations() {

        if (CONFIGPROP == null) {

            CONFIGPROP = new Properties();
            try {
                FileInputStream fs = new FileInputStream(
                        System.getProperty("user.dir")
                        + "//src//Configuration//config.properties");
                CONFIGPROP.load(fs);

            } catch (Exception e) {
            }
        }
    }

    @BeforeTest
    public void initCredentials() {

        if (CONFIGCRED == null) {

            CONFIGCRED = new Properties();
            try {
                FileInputStream fs = new FileInputStream(
                        System.getProperty("user.dir")
                        + "//src//Configuration//credentials.properties");
                CONFIGCRED.load(fs);

            } catch (Exception e) {
            }
        }
        text = CONFIGCRED.getProperty("TextEntity");
    }

    @AfterTest(alwaysRun = true, groups = {"prerequisite"})
    public void tearDown() throws Exception {
        /*
         System.out.println("Sending mail");
         SendMailUsingAuthentication mail = new SendMailUsingAuthentication();
         mail.postMail(SendMailUsingAuthentication.emailList, SendMailUsingAuthentication.emailSubjectTxt, SendMailUsingAuthentication.emailMsgTxt, SendMailUsingAuthentication.emailFromAddress);
         System.out.println("Stopping Selenium WebDriver");
         */
        driver.quit();

    }

}
