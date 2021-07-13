package mark.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import utilities.PropFileHandler;

public class HomePage extends BasePage {

	
	/** this is how to find the cssSelector for the careers tab:
	 * ie: 1. open fortressinfosec.com
	 * 2. right-click on "careers" tab and select inspect
	 * 3. find the href and double-click, copy the contents
	 * 4. cntr-F, add square brackets to href contents: [href="https://fortressinfosec.com/careers/"]
	 * 5. try to find preceding element (ul top menu)
	 * 6. copied the id "" and pasted before the href (ie: [id="top-menu"] [href="https://fortressinfosec.com/careers/"])
	 * 7. replace id= with the hash variable (ie: #top-menu [href="https://fortressinfosec.com/careers/"])
	 * 8. add "$" and remove address: (ie: #top-menu [href$="/careers/"]) ($ means path should end with "/careers/")
	 * 9. add "*" to select element from anywhere on page (ie: #top-menu [href*="/careers"])
	 * 10. add "^" which symbolizes the beginning of the string (ie: href^="https://.....)
	 * 11. add ul (the tag name, not always necessary)
	 */
	
//	public static By career = By.cssSelector("ul#top-menu a[href$='/careers/']");
	
	/**
	 * this is how to find the cssSelector for the getJobs tab:
	 * ie: 1. open fortressinfosec.com, select careers tab
	 * 2. right-click on "View Jobs" tab and select inspect
	 * 3. cntr-F on highlighted area and select class (ie: [class="tp-caption rev-btn  tp-withaction rs-hover-ready"])
	 * 4. replace spaces with dots (ie: .tp-caption.rev-btn.tp-withaction.rs-hover-ready)
	 * 5. remove from the front all the dots and see the smallest name that will select element (ie: .rs-hover-ready)
	 * 6. combine with class above (ie: tp-mask-wrap)
	 */
	
//	public static By viewJobs = By.cssSelector("div.tp-mask-wrap>div.rev-btn");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void launchFortressISApplication() {
		visit(PropFileHandler.readProperty("uiURL"));
		Reporter.log("Launched the Application URL");
	}

	public void selectCareerOption() {
		Assert.assertEquals(clickOnElementViaJS("ul#top-menu a[href$='/careers/']"), true, "Not able to click on Career option");
		Reporter.log("Successfully clicked on Career option");
	}

	public void verifyCareerPageIsOpened() {
		waitToLoad(6000);
		Assert.assertEquals(driver.getCurrentUrl().contains("careers/"), true, "Not able to navigate to Career page");
		Reporter.log("Successfully navigated to career page");
	}
	
	public void clickOnViewJobs() {
		waitToLoad(6000);
		Assert.assertEquals(clickOnElementViaJS("div.tp-mask-wrap>div.rev-btn"), true, "Not able to click on view jobs button");
		Reporter.log("Successfully clicked on view jobs button");
	}
	
	public void verifyJobsPageIsOpened() {
		waitToLoad(6000);
		Assert.assertEquals(driver.getCurrentUrl().contains("jobs/"), true, "Not able to navigate to Jobs page");
		Reporter.log("Successfully navigated to jobs page");
	}

}
