package test.selenuimPages.linkedIn.feedPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.selenuimPages.general.AbstractPanel;
import test.selenuimPages.linkedIn.LinkedInAbstractPage;

import javax.annotation.Nonnull;

/**
 * Linked In Page, side bar panel, user info (https://www.linkedin.com/feed/)
 *
 * <br><img src="LinkedInFeedPageUserSideBarPanel.png" width="250" height="250"/>
 *
 * @author Anna
 */

public class LinkedInFeedPageUserSideBarPanel extends AbstractPanel<LinkedInAbstractPage> {

    public static final By PANEL_SELECTOR = By.xpath("(.//div[@aria-label=\"Side Bar\"]/div)[1]");

    //TODO

    public LinkedInFeedPageUserSideBarPanel(WebDriver driver, @Nonnull LinkedInAbstractPage parentPage) {
        super(driver, parentPage, PANEL_SELECTOR);
        //TODO
    }
}
