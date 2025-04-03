package test.selenuimPages.linkedIn.feedPage;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.selenuimPages.general.AbstractPanel;
import test.selenuimPages.linkedIn.LinkedInAbstractPage;
import test.selenuimPages.linkedIn.jobsPage.LinkedInSavedJobsPage;

import javax.annotation.Nonnull;

/**
 * Linked In Feed Page, side bar panel, user info (https://www.linkedin.com/feed/)
 *
 * <br><img src="LinkedInFeedPageUserSideBarPanel.png" width="250" height="250"/>
 *
 * @author Anna
 */

public class LinkedInFeedPageUserSideBarPanel extends AbstractPanel<LinkedInAbstractPage> {

    public static final By PANEL_SELECTOR = By.xpath("(.//div[@aria-label=\"Side Bar\"]/div)[1]");

    @FindBy(xpath = ".//a[contains(@href, \"https://www.linkedin.com/jobs/preferences\")]")
    private WebElement preferencesButton;

    //TODO click

    @FindBy(xpath = ".//a[contains(@href, \"https://www.linkedin.com/my-items/saved-jobs\")]")
    private WebElement myJobsButton;

    @Step
    public LinkedInSavedJobsPage clickMyJobsButton() {
        myJobsButton.click();
        return new LinkedInSavedJobsPage(driver);
    }

    @FindBy(xpath = ".//a[contains(@href, \"https://www.linkedin.com/explore-career-insights\")]")
    private WebElement myCareerInsightsButton;

    //TODO click

    public LinkedInFeedPageUserSideBarPanel(WebDriver driver, @Nonnull LinkedInAbstractPage parentPage) {
        super(driver, parentPage, PANEL_SELECTOR);
        Assertions.assertTrue(preferencesButton.isDisplayed());
        Assertions.assertTrue(myJobsButton.isDisplayed());
        Assertions.assertTrue(myCareerInsightsButton.isDisplayed());
    }
}
