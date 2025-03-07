package tests;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Covid19TrackerPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Covid19TrackerTest {
    WebDriver driver;
    Covid19TrackerPage covid19TrackerPage;

    // Setup ChromeDriver with the appropriate download directory
    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        
        // Set Chrome options to specify the download directory
        ChromeOptions options = new ChromeOptions();
        String downloadDir = "C:/path/to/download/directory"; // Change this to your download directory
        options.addArguments("download.default_directory=" + downloadDir);

        driver = new ChromeDriver(options);
        covid19TrackerPage = new Covid19TrackerPage(driver);
        driver.manage().window().maximize();
        covid19TrackerPage.loadCovid19TrackerPage();
        Thread.sleep(2000); // Initial wait for the page to load
    }

    // Test to verify page loading properly
    @Test(priority = 1)

    public void verifyCovid19TrackerPageLoadsSuccessfully() throws InterruptedException {
        String expectedTitle = "InerG Test App";
        String actualTitle = covid19TrackerPage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page did not load successfully.");
    }

    // Test to verify the Zoom In button click in map
    @Test(priority = 2)

    public void verifyZoomInButtonClick() throws InterruptedException {
        covid19TrackerPage.clickZoomInButton();
        Thread.sleep(2000); // Wait to observe zoom effect
    }

    // Test to verify the Zoom Out button click in map
    @Test(priority = 3,dependsOnMethods = {"verifyZoomInButtonClick"})
    public void verifyZoomOutButtonClick() throws InterruptedException {
        covid19TrackerPage.clickZoomOutButton();
        Thread.sleep(2000); // Wait to observe zoom effect
    }

    // Test to verify the default dropdown option
    @Test(priority = 4,dependsOnMethods = {"verifyZoomOutButtonClick"})
    public void verifyDefaultDropdownOption() throws InterruptedException {
        String expectedOption = "Select a State"; // Expecting the default option
        String actualOption = covid19TrackerPage.getDefaultDropdownOption();
        Assert.assertEquals(actualOption, expectedOption, "Default option in the dropdown is incorrect.");
    }

    // Test to verify user can able to select the state 
    @Test(priority = 5,dependsOnMethods = {"verifyZoomOutButtonClick"})
    public void verifyStateSelection() throws InterruptedException {
        String stateToSelect = "Tamil Nadu";
        covid19TrackerPage.selectState(stateToSelect);

        String selectedOption = covid19TrackerPage.getDefaultDropdownOption();
        Assert.assertEquals(selectedOption, stateToSelect, "State was not selected correctly.");
    }

    // Display all data like total cases , active ,recovered, deaths after selecting the state
    @Test(priority = 6,dependsOnMethods = {"verifyStateSelection"})
    public void verifyDisplayAllData() throws InterruptedException {
        covid19TrackerPage.displayAllData(); // Display the data for the selected state
        Thread.sleep(4000);
    }

    // Test to verify hover on the pie chart
    @Test(priority = 7,dependsOnMethods = {"verifyStateSelection"})
    public void verifyHoverAndClickTargetElementTwice() throws InterruptedException {
        covid19TrackerPage.hoverAndClickTargetElementTwice(); // Hover and click twice
        Thread.sleep(2000); // Wait to observe the effect
    }
 // Test to verify downloading the image of the particular pie chart
    @Test(priority = 8,dependsOnMethods = {"verifyStateSelection"})
    public void verifydownloadpiechartimage() throws InterruptedException {
        covid19TrackerPage.hoverAndDownloadImage(); // Hover and click twice
        Thread.sleep(2000); // Wait to observe the effect
        System.out.println("Image downloaded successfully");
    }

    //Test to verify user can able to click on plotly icon in pie chart and able to redirect to that website and comback to original window
    @Test(priority = 9,dependsOnMethods = {"verifyStateSelection"})
    public void verifyRedirectToAnotherPageAndSwitchBack() throws InterruptedException {
        // Store the current window handle (the original tab)
        String originalTab = driver.getWindowHandle();
        
        // Hover and click on the link to open it in a new tab
        covid19TrackerPage.Redirecttoanotherpage();
        Thread.sleep(5000); // Wait for the page to load in the new tab
        
        // Switch to the new tab 
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalTab)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        System.out.println("Redirected to new tab: " + driver.getTitle());

        // Wait to observe the new tab
        Thread.sleep(2000);
        
        // switch back to the original tab
        driver.switchTo().window(originalTab);
        System.out.println("Back to the original page: " + driver.getTitle());
    }
 // Test to verify hover on the pie chart
    @Test(priority = 10,dependsOnMethods = {"verifyStateSelection"})
    public void verifyHoverAndClickTargetElementTwice1() throws InterruptedException {
        covid19TrackerPage.hoverAndClickTargetElementTwice1(); // Hover and click twice
        Thread.sleep(2000); 
        
        // Wait to observe the effect
    }
 // Test to verify total button on pie chart if will disappear in chart,once again click it appear in pie chart
    @Test(priority = 11,dependsOnMethods = {"verifyStateSelection"})
    public void verifytotalbtn() throws InterruptedException {
        covid19TrackerPage.totalbtn(); // Hover and click twice
        Thread.sleep(2000); 
        // Wait to observe the effect
    }
    
 // Test to verify active cases button on pie chart if will disappear in chart,once again click it appear in pie chart
    @Test(priority = 12,dependsOnMethods = {"verifyStateSelection"})
    public void verifyactivecasesbtn() throws InterruptedException {
        covid19TrackerPage.activecasesbtn(); // Hover and click twice
        Thread.sleep(2000); 
        // Wait to observe the effect
    }
 // Test to verify recovered button on pie chart if will disappear in chart,once again click it appear in pie chart
    @Test(priority = 13,dependsOnMethods = {"verifyStateSelection"})
    public void verifyrecoveredbtn() throws InterruptedException {
        covid19TrackerPage.recoveredbtn(); // Hover and click twice
        Thread.sleep(2000); 
        // Wait to observe the effect
    }
 // Test to verify deaths button on pie chart if will disappear in chart,once again click it appear in pie chart
    @Test(priority = 14,dependsOnMethods = {"verifyStateSelection"})
    public void verifydeathsbtn() throws InterruptedException {
        covid19TrackerPage.deathsbtn(); // Hover and click twice
        Thread.sleep(2000); 
        // Wait to observe the effect
    }
        
 // Test to scroll down to the bottom of the page
    @Test(priority = 15)
    public void verifyScrollToBottom() throws InterruptedException {
        covid19TrackerPage.scrollToBottom();
        Thread.sleep(2000); // Add a delay to observe the scroll effect
        System.out.println("Scrolled to the bottom of the page successfully.");
    }

    /* // Close the browser after the test
    @AfterClass
    public void tearDown() {
        driver.quit();
    }*/
}
