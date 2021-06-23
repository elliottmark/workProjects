package mark.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utilities.PropFileHandler;


public class TestSessionInitiator {
	
	public WebDriver driver;
	private String browser;
	
	//Defining classes Variables
	public static HomePage homepage;

	public TestSessionInitiator(String browser) {
		setBrowser(browser);
		initialize(getBrowser());
		initClasses(driver);
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void initialize(String browserName) {
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
