package test;

/**
 * Test setup variables passed as environment variables
 *
 * @author Anna
 */

public class SystemPropertyConst {
    /**
     * Determines from which file variables are defined, by default testCreds
     */
    public static final String ENVIRONMENT_NAME ="environmentName";

    /**
     * Defines in which browser {@code BrowserName} to run tests, CHROME by default
     */
    public static final String TEST_BROWSER = "test_browser";
    /**
     * Defines the size of the window {@code DisplaySize}, default is none (full screen)
     */
    public static final String TEST_DISPLAY_SIZE = "test_display_size";
    /**
     * Defines the window visible or background (headless), default is true (full visible)
     */
    public static final String TEST_DISPLAY_VISIBLE = "test_display_visible";
}
