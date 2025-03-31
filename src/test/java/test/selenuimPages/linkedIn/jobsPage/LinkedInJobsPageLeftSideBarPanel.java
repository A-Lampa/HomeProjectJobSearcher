package test.selenuimPages.linkedIn.jobsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.selenuimPages.general.AbstractPanel;
import test.selenuimPages.linkedIn.LinkedInAbstractPage;

import javax.annotation.Nonnull;

/**
 * Linked In Jobs Page, left side bar panel (https://www.linkedin.com/jobs/)
 *
 * <br><img src="LinkedInJobsPageLeftSideBarPanel.png" width="250" height="250"/>
 *
 * @author Anna
 */

public class LinkedInJobsPageLeftSideBarPanel extends AbstractPanel<LinkedInAbstractPage> {

    public static final By PANEL_SELECTOR = By.xpath("(//nav[@class=\"jobs-home-scalable-nav\"]/parent::div)[1]");

    //TODO

    public LinkedInJobsPageLeftSideBarPanel(WebDriver driver, @Nonnull LinkedInAbstractPage parentPage) {
        super(driver, parentPage, PANEL_SELECTOR);
        //TODO
    }
}
