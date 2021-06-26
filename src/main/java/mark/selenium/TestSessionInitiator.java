package mark.selenium;

import java.util.concurrent.TimeUnit;

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

	public TestSessionInitiator(String browser, String os) {
		setBrowserOS(browser,os);
		initialize(getBrowser());
		initClasses(driver);
	}

	public void setBrowserOS(String browser) {
		this.browser = browser;
		this.os = os;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void initialize(String browserName) {
		if(this.os=="windows") {
			System.out.println("--------inside Windows ------");
			switch (browserName.toLowerCase()) {
			case "chrome":
			case "ch":
				System.setProperty("webdriver.chrome.driver", PropFileHandler.readProperty("driverpath") + "chromedriver.exe");
				driver = new ChromeDriver();
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
			switch (browserName.toLowerCase()) {
			case "chrome":
			case "ch":
				System.setProperty("webdriver.chrome.driver", PropFileHandler.readProperty("driverpath") + "chromedriver");
				driver = new ChromeDriver();
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
