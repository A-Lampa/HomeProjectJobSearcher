package test.selenuimPages.general;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * General methods for working with the page
 *
 * @author Anna
 */

public abstract class AbstractPage implements InterfaceAbstractPage {
    /**
     * Timeout (30 seconds), used by default in methods with an explicit wait
     */
    public static final int DEFAULT_EXPLICIT_TIMEOUT = 30000;

    /**
     * Selenium web driver
     */
    protected static WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected AbstractPage(@Nonnull WebDriver driver, WebElement panel) {
        this.driver = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(panel), this);
    }

    private static final String SCREENSHOT_FOLDER = "screenshots/";

    public static void screen(WebDriver driver) {
        String testName = getTestName();
        String className = getClassName();

        File directory = new File(SCREENSHOT_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String filePath = String.format("%s%s_%s_%s.png", SCREENSHOT_FOLDER, className, testName, timestamp);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("📸 Screenshot has been saved: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Screenshot hasn't been saved: " + e.getMessage());
        }
    }

    /**
     * Take a screenshot of an element
     * The screenshot is folded into folders to correspond of the stack trace
     *
     * @return screenshot file
     * @throws Exception
     */
    @Override
    public void screen() {
        screen(driver);
    }

    public static String getTestName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[3].getClassName().substring(
                Thread.currentThread().getStackTrace()[3].getClassName().lastIndexOf(".") + 1);
    }

    public static void deleteOldScreenshots(String testName, String className) {
        File folder = new File(SCREENSHOT_FOLDER);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("No screenshot folder: " + SCREENSHOT_FOLDER);
            return;
        }

        File[] oldScreenshots = folder.listFiles((dir, name) ->
                name.contains(testName) && name.contains(className) && name.endsWith(".png")
        );

        if (oldScreenshots != null) {
            for (File file : oldScreenshots) {
                if (file.delete()) {
                    System.out.println("The old screenshot is deleted: " + file.getName());
                } else {
                    System.out.println("The old screenshot is not deleted: " + file.getName());
                }
            }
        }
    }

    public static void deleteOldTodayScreenshots() {
        String timestampToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        File folder = new File(SCREENSHOT_FOLDER);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("No screenshot folder: " + SCREENSHOT_FOLDER);
            return;
        }

        File[] oldScreenshots = folder.listFiles((dir, name) ->
                name.contains(timestampToday) && name.contains(timestampToday) && name.endsWith(".png")
        );

        if (oldScreenshots != null) {
            for (File file : oldScreenshots) {
                if (file.delete()) {
                    System.out.println("The old screenshot is deleted: " + file.getName());
                } else {
                    System.out.println("The old screenshot is not deleted: " + file.getName());
                }
            }
        }
    }

    /**
     * The method waits for the element to appear on the page
     *
     * @param by selector of identifying element
     * @return element
     * @throws TimeoutException if the element was not found within timeOut
     */
    protected WebElement waitElement(By by) {
        return waitElement(driver, by, DEFAULT_EXPLICIT_TIMEOUT);
    }

    /**
     * The method waits for the element to appear on the page
     *
     * @param parent  The parent relative to which to search
     * @param by      selector of identifying element
     * @param timeOut maximum waiting time (ms)
     * @return element
     * @throws TimeoutException if the element was not found within timeOut
     */
    public static WebElement waitElement(SearchContext parent, By by, long timeOut) {
        return waitElement(parent, by, timeOut, true);
    }

    /**
     * The method waits for the element to appear on the page
     *
     * @param parent      The parent relative to which to search
     * @param by          selector of identifying element
     * @param timeOut     maximum waiting time (ms)
     * @param checkEnable to perform the check isEnabled or not
     * @return element
     * @throws TimeoutException if the element was not found within timeOut
     */
    public static WebElement waitElement(SearchContext parent, By by, long timeOut, boolean checkEnable) {
        return waitElement(parent, by, timeOut, checkEnable, true);
    }

    /**
     * The method waits for the element to appear on the page
     *
     * @param parent         The parent relative to which to search
     * @param by             selector of identifying element
     * @param timeOut        maximum waiting time (ms)
     * @param checkEnable    to perform the check isEnabled or not
     * @param checkDisplayed to perform the check isDisplayed or not (sometimes an element is visible, but selenium considers it invisible)
     * @return element
     * @throws TimeoutException if the element was not found within timeOut
     */
    public static WebElement waitElement(SearchContext parent, By by, long timeOut, boolean checkEnable, boolean checkDisplayed) {
        WebElement result = null;
        long time = System.currentTimeMillis();
        int matchFound = 0;
        while (result == null && System.currentTimeMillis() - time < timeOut) {
            try {
                List<WebElement> elements = parent.findElements(by);
                matchFound = elements.size();
                for (WebElement element : elements) {
                    if ((!checkEnable || element.isEnabled()) && (!checkDisplayed || element.isDisplayed())) {
                        if (result == null) {
                            result = element;
                        } else {
                            // more than one match found
                            result = null;
                            break;
                        }
                    } else {
                        matchFound--;
                    }
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignore) {
                }
            }
        }
        if (result == null) {
            if (matchFound > 0) {
                throw new TimeoutException("More than one element found (" + matchFound + "):" + by);
            } else {
                throw new TimeoutException("Element not found by:" + by);
            }
        }
        return result;
    }

}
