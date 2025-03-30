package test.selenuimPages.general;

import java.io.File;

/**
 * Interface for general methods for working with the page
 *
 * @author Anna
 */

public interface InterfaceAbstractPage {
    /**
     * Take a screenshot of an element
     * The screenshot is folded into folders to correspond of the stack trace
     *
     * @return screenshot file
     * @throws Exception
     */
    public void screen();
}
