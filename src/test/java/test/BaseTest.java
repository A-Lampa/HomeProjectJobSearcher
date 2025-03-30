package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import test.selenuimPages.general.AbstractPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class BaseTest {
    protected WebDriver driver;
    public static final Duration DEFAULT_IMPLICITLY_TIMEOUT = Duration.ofSeconds(10);

    @BeforeEach
    public void beforeTests() {
        driver = setUpDriver();
    }

    public static WebDriver setUpDriver() {
        final WebDriver result;

        String testDisplaySize = System.getProperty(SystemPropertyConst.TEST_DISPLAY_SIZE);
        DisplaySize displaySize = (testDisplaySize != null) ? DisplaySize.valueOf(testDisplaySize) : null;
        boolean displayVisible = "true".equalsIgnoreCase(System.getProperty(SystemPropertyConst.TEST_DISPLAY_VISIBLE, "true"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-US");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        if (!displayVisible) {
            options.addArguments("--headless");
            if (displaySize == null) {
                displaySize = DisplaySize.XGA;
            }
            options.addArguments("--window-size=" + displaySize.getWidth() + "," + displaySize.getHeight());
        }
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        options.addArguments("--remote-allow-origins=*");
        //disable notifications:
        chromePrefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", chromePrefs);

        // Open browser
        result = new ChromeDriver(options);

        if (displayVisible) {
            // set size of the browser window
            if (displaySize != null) {
                result.manage().window().setSize(displaySize.getDimension());
            } else {
                // set size of the browser window in full screen
                result.manage().window().maximize();
            }
        }
        // setting the default timeout
        result.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY_TIMEOUT);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (result != null) {
                    result.quit();
                }
            }
        });
        return result;
    }

    /**
     * Will be executed after each test function in the class
     *
     */

    @AfterEach
    public void teardownWithTheFinalScreen() {
        if (driver != null) {
            AbstractPage.screen(driver);
            // close window
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error close WebDriver after test");
            }
        }
    }
}
