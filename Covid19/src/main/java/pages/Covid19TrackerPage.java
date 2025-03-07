package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Covid19TrackerPage extends BasePage {

    // Constructor to initialize WebDriver and PageFactory, and call the parent constructor
    public Covid19TrackerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize page elements
    }

    // Method to load the COVID-19 Tracker page
    public void loadCovid19TrackerPage() {
        driver.get("https://inerg-test.web.app/");
    }

    // Method to get the page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Locate the 'select' element (dropdown) for states
    @FindBy(xpath = "//select[@class='data-filter-input']")
    WebElement stateDropdown;

    // Locate the "Zoom In" button
    @FindBy(xpath = "//span[normalize-space()='+']")
    WebElement zoomInButton;

    // Locate the "Zoom Out" button
    @FindBy(xpath = "//span[contains(text(),'−')]")
    WebElement zoomOutButton;

    // Method to click the "Zoom In" button
    public void clickZoomInButton() {
        zoomInButton.click();
    }

    // Method to click the "Zoom Out" button
    public void clickZoomOutButton() {
        zoomOutButton.click();
    }

    // Method to get the default selected option from the dropdown
    public String getDefaultDropdownOption() {
        Select select = new Select(stateDropdown);
        return select.getFirstSelectedOption().getText();
    }

    // Method to select a state in the dropdown
    public void selectState(String state) {
        Select select = new Select(stateDropdown);
        select.selectByVisibleText(state);
    }
   
    // Method to display all the data available in the result
    public void displayAllData() {
        // Create a WebDriverWait object with Duration (for newer versions of Selenium)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the data elements (the <p> tags) are visible
        List<WebElement> dataParagraphs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='resultCard']/div[@class='display-data']/p")));

        // Check if the list is empty
        if (dataParagraphs.isEmpty()) {
            System.out.println("No data elements found!");
        } else {
            // Loop through the <p> tags and print the label and its data
            for (WebElement paragraph : dataParagraphs) {
                String paragraphText = paragraph.getText();

                // Split the text based on the first colon to separate the label and the data
                String[] parts = paragraphText.split(":");

                if (parts.length > 1) {
                    String label = parts[0].trim();  // The label (e.g., "Active Cases")
                    String dataValue = parts[1].trim();  // The data value (e.g., "5,000")
                    System.out.println(label + ": " + dataValue);  // Print the label and its corresponding data value
                }
            }
        }
    }
 // Locate the element using the XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]")
    WebElement targetElement;

    public void hoverAndClickTargetElementTwice() {
        Actions actions = new Actions(driver);
        actions.moveToElement(targetElement).perform();  // Hover over the element
        actions.click().perform();  // Click the element
        try {
            Thread.sleep(1000);  // Adding some wait time before the second click
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.click().perform();  // Click the element again
    }
 // Locate the download button using the XPath
    @FindBy(xpath = "(//div[@class='modebar-group']/a[@data-title='Download plot as a png'])[1]")
    WebElement downloadButton;

    public void hoverAndDownloadImage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(downloadButton).perform();  // Hover over the element
        actions.click().perform();  // Click the element to trigger the download
    }
    
 // Locate the plotly button using the XPath
    @FindBy(xpath = "(//a[@href=\"https://plotly.com/\"])[1]")
    WebElement redirectButton;

    public void Redirecttoanotherpage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(redirectButton).perform();  // Hover over the element
        actions.click().perform(); 
        // Click the element to trigger the download
    }
    
 // Locate the element using the XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]")
    WebElement targetElement1;

    public void hoverAndClickTargetElementTwice1() {
        Actions actions = new Actions(driver);
        actions.moveToElement(targetElement1).perform();  // Hover over the element
        actions.click().perform();  // Click the element
        try {
            Thread.sleep(1000);  // Adding some wait time before the second click
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.click().perform();  // Click the element again
    }
 // Locate the total button using the XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]//*[name()='svg']//*[name()='g' and contains(@class,'infolayer')]//*[name()='g' and contains(@class,'legend')]//*[name()='g' and contains(@class,'scrollbox')]//*[name()='g' and contains(@class,'groups')]//*[name()='g'][1]/*[name()='rect'][1]")
    WebElement total;

    public void totalbtn() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(total).perform();  // Hover over the element
        actions.click().perform(); 
        Thread.sleep(1000);
        actions.click().perform(); 
// Click the element to trigger the download
    }
    
 // Locate the cactivecases button using the XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]//*[name()='svg']//*[name()='g' and contains(@class,'infolayer')]//*[name()='g' and contains(@class,'legend')]//*[name()='g' and contains(@class,'scrollbox')]//*[name()='g' and contains(@class,'groups')]//*[name()='g'][2]/*[name()='rect'][1]")
    WebElement activecase;

    public void activecasesbtn() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(activecase).perform();  // Hover over the element
        actions.click().perform(); 
        Thread.sleep(1000);
        actions.click().perform(); 
// Click the element to trigger the download
    }
    
 // Locate the recovered button using the XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]//*[name()='svg']//*[name()='g' and contains(@class,'infolayer')]//*[name()='g' and contains(@class,'legend')]//*[name()='g' and contains(@class,'scrollbox')]//*[name()='g' and contains(@class,'groups')]//*[name()='g'][3]/*[name()='rect'][1]")
    WebElement recovered;

    public void recoveredbtn() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(recovered).perform();  // Hover over the element
        actions.click().perform(); 
        Thread.sleep(1000);
        actions.click().perform(); 
// Click the element to trigger the download
    }
 // Locate the deaths button using the XPath
    @FindBy(xpath = "//body/div[@id='root']/div[@class='container']/div[2]/div[1]/div[1]/div[1]//*[name()='svg']//*[name()='g' and contains(@class,'infolayer')]//*[name()='g' and contains(@class,'legend')]//*[name()='g' and contains(@class,'scrollbox')]//*[name()='g' and contains(@class,'groups')]//*[name()='g'][4]/*[name()='rect'][1]")
    WebElement deaths;

    public void deathsbtn() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(deaths).perform();  // Hover over the element
        actions.click().perform(); 
        Thread.sleep(1000);
        actions.click().perform(); 
// Click the element to trigger the download
    }
 // Method to scroll down to the bottom of the page
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
  
}
