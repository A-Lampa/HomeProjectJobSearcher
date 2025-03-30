package test.selenuimPages.linkedIn.feedPage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import test.selenuimPages.linkedIn.LinkedInAbstractPage;

/**
 * Linked In Feed Page (https://www.linkedin.com/feed/)
 *
 * <br><img src="pics\LinkedInFeedPage.png" width="750" height="400"/>
 *
 * @author Anna
 */

public class LinkedInFeedPage extends LinkedInAbstractPage {

    private final LinkedInFeedPageUserSideBarPanel userSideBarPanel;

    @Step
    public LinkedInFeedPageUserSideBarPanel getLinkedInFeedPageUserSideBarPanel() {
        return userSideBarPanel;
    }

    //TODO


    public LinkedInFeedPage(WebDriver driver) {
        super(driver);
        userSideBarPanel = new LinkedInFeedPageUserSideBarPanel(driver, this);
        //TODO
    }
}
