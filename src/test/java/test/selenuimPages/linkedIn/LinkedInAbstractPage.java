package test.selenuimPages.linkedIn;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import test.selenuimPages.general.AbstractPage;

public class LinkedInAbstractPage extends AbstractPage {

    @Step
    public AbstractLinkedInTopPanel getTopPanel() {
        return topPanel;
    }

    protected AbstractLinkedInTopPanel topPanel;

    protected LinkedInAbstractPage(WebDriver driver) {
        super(driver);
        topPanel = new AbstractLinkedInTopPanel(driver, this);
    }
}
