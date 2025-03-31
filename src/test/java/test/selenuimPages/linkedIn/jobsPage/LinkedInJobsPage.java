package test.selenuimPages.linkedIn.jobsPage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import test.selenuimPages.linkedIn.LinkedInAbstractPage;

/**
 * Linked In Jobs Page (https://www.linkedin.com/jobs/)
 *
 * <br><img src="pics\LinkedInJobsPage.png" width="750" height="400"/>
 *
 * @author Anna
 */

public class LinkedInJobsPage extends LinkedInAbstractPage {

    private final LinkedInJobsPageLeftSideBarPanel leftSideBarPanel;

    @Step
    public LinkedInJobsPageLeftSideBarPanel getLinkedInJobsPageLeftSideBarPanel() {
        return leftSideBarPanel;
    }

    public LinkedInJobsPage(WebDriver driver) {
        super(driver);
        leftSideBarPanel = new LinkedInJobsPageLeftSideBarPanel(driver, this);
    }
}
