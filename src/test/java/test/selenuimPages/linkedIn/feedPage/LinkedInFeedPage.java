package test.selenuimPages.linkedIn.feedPage;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.selenuimPages.linkedIn.LinkedInAbstractPage;

/**
 * Linked In Feed Page (https://www.linkedin.com/feed/)
 *
 * <br><img src="pics\LinkedInFeedPage.png" width="750" height="400"/>
 *
 * @author Anna
 */

public class LinkedInFeedPage extends LinkedInAbstractPage {

    private final By mainSectionSelector = By.xpath("//main[@aria-label=\"Main Feed\"]");

    private final LinkedInFeedPageUserSideBarPanel userSideBarPanel;

    @Step
    public LinkedInFeedPageUserSideBarPanel getLinkedInFeedPageUserSideBarPanel() {
        return userSideBarPanel;
    }

    @FindBy(xpath = "//div[@class='share-box-feed-entry__top-bar']/parent::div")
    private WebElement shareBoxTop;

    //TODO


    public LinkedInFeedPage(WebDriver driver) {
        super(driver);
        waitElement(mainSectionSelector);
        userSideBarPanel = new LinkedInFeedPageUserSideBarPanel(driver, this);
        Assertions.assertTrue(shareBoxTop.isDisplayed());
        //TODO
    }
}
