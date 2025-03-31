package test.selenuimPages.linkedIn;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.selenuimPages.general.AbstractPage;
import test.selenuimPages.general.AbstractPanel;
import test.selenuimPages.linkedIn.feedPage.LinkedInFeedPage;
import test.selenuimPages.linkedIn.jobsPage.LinkedInJobsPage;

import javax.annotation.Nonnull;

/**
 * Linked In Feed Page, top panel (https://www.linkedin.com/feed/)
 *
 * <br><img src="pics\AbstractLinkedInTopPanel.png" width="750" height="40"/>
 *
 * @author Anna
 */

public class AbstractLinkedInTopPanel<ParentPage extends AbstractPage> extends AbstractPanel<ParentPage> {

    private static final By PANEL_SELECTOR = By.id("global-nav");

    @FindBy(xpath = "(.//a[contains(@href, 'https://www.linkedin.com/feed/')])[1]")
    private WebElement linkedInLogoButton;

    @Step
    public LinkedInFeedPage clickLinkedInLogoButton() {
        linkedInLogoButton.click();
        return new LinkedInFeedPage(driver);
    }

    @FindBy(xpath = ".//input[@data-view-name=\"search-global-typeahead-input\"]")
    private WebElement topSearch;

    @FindBy(xpath = ".//span[@title=\"Home\"]//ancestor::a")
    private WebElement homeButton;

    @Step
    public LinkedInFeedPage clickHomeButton() {
        homeButton.click();
        return new LinkedInFeedPage(driver);
    }

    //TODO

    @FindBy(xpath = ".//span[@title=\"Jobs\"]//ancestor::a")
    private WebElement jobsButton;

    @Step
    public LinkedInJobsPage clickJobsButton() {
        jobsButton.click();
        return new LinkedInJobsPage(driver);
    }

    //TODO

    public AbstractLinkedInTopPanel(WebDriver driver, @Nonnull ParentPage parentPage) {
        super(driver, parentPage, PANEL_SELECTOR);
        Assertions.assertTrue(linkedInLogoButton.isDisplayed());
        Assertions.assertTrue(topSearch.isDisplayed());
        Assertions.assertTrue(homeButton.isDisplayed());
        //TODO
    }
}
