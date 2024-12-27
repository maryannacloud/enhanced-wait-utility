import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class EnhancedWait {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedWait.class);

    private final WebDriver driver;

    public EnhancedWait(WebDriver driver) {
        this.driver = driver;
    }

    // this method is used for web elements that are moving on the page
    // examples: loading spinner, progress indicator, animation buttons or modals
    // drag and drop operations, slideshow elements, dynamic menues and dropdowns
    // tooltip or notification animations
    public boolean waitForElementToStopMoving(WebElement element, int timeoutInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        return wait.until(driver -> {
            int initialX = element.getLocation().getX();
            int initialY = element.getLocation().getY();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error("Thread sleep was interrupted", e);
            }

            int finalX = element.getLocation().getX();
            int finalY = element.getLocation().getY();

            boolean isStationary = (initialX == finalX && initialY == finalY);

            if (!isStationary) {
                logger.debug("Element is still moving. Initial: ({}, {}), Final: ({}, {})",
                        initialX, initialY, finalX, finalX);
            }
            return isStationary;
        });
    }
}

