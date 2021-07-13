package mark.selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utilities.PropFileHandler;

/**
 * TestSessionInitiator class is responsible for loading the configuration from property files, initializing
 * the webdriver and implicit waits 
 *
 */


public class TestSessionInitiator {

	public WebDriver driver;
	private String browser;
	private String os;

	//Defining classes Variables
	public static HomePage homepage;

	public TestSessionInitiator(String browser) {
		setBrowserOS(browser);
		initialize(getBrowser());
		initClasses(driver);
	}

	public void setBrowserOS(String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void initialize(String browserName) {
		os = System.getProperty("os.name").toLowerCase();
		System.out.println("----------This OS:-----------" + os);
		osType = System.getProperty("osType").toLowerCase();
		System.out.println("----------This osType:-----------" + osType);
		if(os.contains("win")) {
			System.out.println("--------inside Windows ------");
			switch (browserName.toLowerCase()) {
			case "chrome":
			case "ch":
				System.setProperty("webdriver.chrome.driver", PropFileHandler.readProperty("driverpath") + "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				break;
			case "firefox":
			case "ff":
				System.setProperty("webdriver.gecko.driver", PropFileHandler.readProperty("driverpath") + "geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "internetexplorer":
			case "ie":
				System.setProperty("webdriver.ie.driver", PropFileHandler.readProperty("driverpath") + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", PropFileHandler.readProperty("driverpath") + "MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser passed in: " + browser);
				break;
			}
		}
		else {
			System.out.println("--------inside Linux ------");
			switch (browserName.toLowerCase()) {
			case "chrome":
			case "ch":
				System.setProperty("webdriver.chrome.driver", PropFileHandler.readProperty("driverpath") + "chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--no-sandbox"); // Bypass OS security model
				driver = new ChromeDriver(options);
				break;
			case "firefox":
			case "ff":
				System.setProperty("webdriver.gecko.driver", PropFileHandler.readProperty("driverpath") + "geckodriver");
				driver = new FirefoxDriver();
				break;
			case "internetexplorer":
			case "ie":
				System.setProperty("webdriver.ie.driver", PropFileHandler.readProperty("driverpath") + "IEDriverServer");
				driver = new InternetExplorerDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", PropFileHandler.readProperty("driverpath") + "MicrosoftWebDriver");
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser passed in: " + browser);
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropFileHandler.readProperty("timeout")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void initClasses(WebDriver driver) {
		homepage = new HomePage(driver);

	}

	public void quit() {
		driver.quit();
	}

}
