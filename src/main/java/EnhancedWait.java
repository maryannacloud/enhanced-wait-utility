import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Function;

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

public class EnhancedWait {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedWait.class);

    private final WebDriver driver;

    /**
     * Constructor for EnhancedWait.
     *
     * @param driver the WebDriver instance used to interact with the browser
     */
    public EnhancedWait(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for a WebElement to stop moving on the page.
     *
     * <p>This method periodically checks the element's location
     * and determines if it has stabilized. Useful for scenarios
     * involving animations or transitions.</p>
     *
     * @param element the WebElement to monitor for movement
     * @param timeoutInSeconds the maximum time to wait for the element to stabilize
     * @return {@code true} if the element stopped moving within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForElementToStopMoving(WebElement element, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> {
            int initialX = element.getLocation().getX();
            int initialY = element.getLocation().getY();

            boolean isStationary = new FluentWait<>(element)
                    .withTimeout(Duration.ofSeconds(2))
                    .pollingEvery(Duration.ofMillis(100))
                    .until(el -> {
                        int currentX = el.getLocation().getX();
                        int currentY = el.getLocation().getY();
                        return currentX == initialX && currentY == initialY;
                    });
            if (!isStationary) {
                logger.debug("Element is still moving. Initial: ({}, {}), Current: ({}, {})",
                        initialX, initialY, element.getLocation().getX(), element.getLocation().getY());
            }

            return isStationary;
        });
    }

    /**
     * Waits for a WebElement to have the specified text.
     *
     * <p>This method continuously checks the text content of the WebElement
     * until it matches the expected value or the timeout expires.</p>
     *
     * @param element the WebElement whose text is being monitored
     * @param expectedText the text to wait for
     * @param timeoutInSeconds the maximum time to wait for the text to match, in seconds
     * @return {@code true} if the element's text matches the expected value within the timeout,
     *         {@code false} otherwise
     */
    public boolean waitForElementSpecificText(WebElement element, String expectedText, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(webDriver -> element.getText().equals(expectedText));
    }

    public boolean waitForCondition(Function<WebDriver, Boolean> condition, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

            return wait.until(condition);
    }
}

