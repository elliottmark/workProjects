package mark.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import utilities.PropFileHandler;

public class HomePage extends BasePage {

	public static By career = By.cssSelector("ul#top-menu a[href$='/careers/']");
	public static By viewJobs = By.cssSelector("div.tp-mask-wrap>div.rev-btn");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void launchFortressISApplication() {
		visit(PropFileHandler.readProperty("uiURL"));
		Reporter.log("Launched the Application URL");
	}

	public void selectCareerOption() {
		Assert.assertEquals(clickOnElement(career), true, "Not able to click on Career option");
		Reporter.log("Successfully clicked on Career option");
	}

	public void verifyCareerPageIsOpened() {
		waitToLoad(2000);
		Assert.assertEquals(driver.getCurrentUrl().contains("careers/"), true, "Not able to navigate to Career page");
		Reporter.log("Successfully navigated to career page");
	}
	
	public void clickOnViewJobs() {
		Assert.assertEquals(clickOnElement(viewJobs), true, "Not able to click on view jobs button");
		Reporter.log("Successfully clicked on view jobs button");
	}
	
	public void verifyJobsPageIsOpened() {
		waitToLoad(2000);
		Assert.assertEquals(driver.getCurrentUrl().contains("jobs/"), true, "Not able to navigate to Jobs page");
		Reporter.log("Successfully navigated to jobs page");
	}

}
