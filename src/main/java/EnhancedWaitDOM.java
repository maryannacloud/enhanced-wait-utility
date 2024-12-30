import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;

/**
 * The EnhancedWait class provides custom waiting utilities
 * to extend Selenium WebDriver's default capabilities.
 *
 * <p>This utility is designed to handle dynamic conditions
 * such as waiting for elements to stop moving or other
 * custom non-UI conditions.</p>
 *
 * @author Maryna Nesterenko
 * @version 1.0
 */

public class EnhancedWaitDOM {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedWaitDOM.class);

    private final WebDriver driver;

    /**
     * Constructor for EnhancedWait.
     *
     * @param driver the WebDriver instance used to interact with the browser
     */
    public EnhancedWaitDOM(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for an attribute of a WebElement to have a specific value.
     *
     * @param element the WebElement to monitor
     * @param attribute the attribute to check
     * @param expectedValue the expected value of the attribute
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the attribute value matches the expected value within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForAttributeValue(WebElement element, String attribute, String expectedValue, int timeoutInSeconds){

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(500))
                .ignoring(Exception.class);

        return wait.until(driver -> element.getDomAttribute(attribute).equals(expectedValue));
    }

    /**
     * Waits for the count of elements matching a locator to change.
     *
     * @param locator the By locator for the elements
     * @param expectedCount the expected number of elements
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the count matches the expected value within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForElementCount(By locator, int expectedCount, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> driver.findElements(locator).size() == expectedCount);
    }

    /**
     * Waits for a WebElement to become enabled.
     *
     * @param element the WebElement to monitor
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the element is enabled within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForElementToBeEnabled(WebElement element, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> element.isEnabled());
    }

    /**
     * Waits for the page title to contain a specific text.
     *
     * @param titleSubstring the substring to check in the title
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the title contains the substring within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForTitleToContain(String titleSubstring, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> driver.getTitle().contains(titleSubstring));
    }

    /**
     * Waits for a JavaScript condition to evaluate to {@code true}.
     *
     * @param script the JavaScript code to execute
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the script evaluates to true within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForJavaScriptCondition(String script, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> (Boolean) ((JavascriptExecutor) driver).executeScript(script));
    }

    /**
     * Waits for the URL to contain a specific substring.
     *
     * @param urlSubstring the substring to check in the current URL
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the URL contains the substring within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForUrlToContain(String urlSubstring, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> driver.getCurrentUrl().contains(urlSubstring));
    }

    /**
     * Waits for a file to appear in a specific directory.
     *
     * @param downloadDirectory the directory to monitor
     * @param fileName the name of the file to wait for
     * @param timeoutInSeconds the maximum time to wait
     * @return {@code true} if the file appears within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForFileToDownload(String downloadDirectory, String fileName, int timeoutInSeconds) {
        File file = new File(downloadDirectory, fileName);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> file.exists());
    }





}
