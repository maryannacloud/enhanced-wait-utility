import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasicWait {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedWait.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Constructor for EnhancedWait.
     *
     * @param driver the WebDriver instance used to interact with the browser
     */
    public BasicWait(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        ;
    }

    /**
     * Waits for a WebElement to be clickable.
     *
     * @param element the WebElement to monitor
     */
    public void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.error("Element was not clickable within the timeout: " + element, e);
        }
    }

    /**
     * Waits for a WebElement to be visible on the page.
     *
     * @param element the WebElement to monitor
     */
    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element was not visible within the timeout: " + element, e);
        }
    }

    /**
     * Waits for a WebElement to become invisible.
     *
     * @param locator the By locator for the element
     */
    public void waitForElementToBeInvisible(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element did not become invisible within the timeout: " + locator, e);
        }
    }

    /**
     * Waits for a WebElement to be present in the DOM.
     *
     * @param locator the By locator for the element
     */
    public void waitForElementPresence(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element was not present in the DOM within the timeout: " + locator, e);
        }
    }

    /**
     * Waits for specific text to be present in a WebElement.
     *
     * @param locator the By locator for the element
     * @param text    the expected text to be present
     */
    public void waitForTextToBePresentInElement(By locator, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            logger.error("Text '" + text + "' was not present in the element within the timeout: " + locator, e);
        }
    }

    /**
     * Waits for the page title to contain specific text.
     *
     * @param titleSubstring the expected substring in the title
     */
    public void waitForTitleToContain(String titleSubstring) {
        try {
            wait.until(ExpectedConditions.titleContains(titleSubstring));
        } catch (Exception e) {
            logger.error("Title did not contain '" + titleSubstring + "' within the timeout.", e);
        }
    }

    /**
     * Waits for the current URL to contain specific text.
     *
     * @param urlSubstring the expected substring in the URL
     */
    public void waitForUrlToContain(String urlSubstring) {
        try {
            wait.until(ExpectedConditions.urlContains(urlSubstring));
        } catch (Exception e) {
            logger.error("URL did not contain '" + urlSubstring + "' within the timeout.", e);
        }
    }

    /**
     * Waits for a WebElement to be selected (e.g., a checkbox).
     *
     * @param element the WebElement to monitor
     */
    public void waitForElementToBeSelected(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Exception e) {
            logger.error("Element was not selected within the timeout: " + element, e);
        }
    }

    /**
     * Waits for a specific attribute of a WebElement to contain a specific value.
     *
     * @param element   the WebElement to monitor
     * @param attribute the attribute to check
     * @param value     the expected value to be present in the attribute
     */
    public void waitForAttributeToContain(WebElement element, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (Exception e) {
            logger.error("Attribute '" + attribute + "' did not contain value '" + value + "' within the timeout: " + element, e);
        }
    }
}
