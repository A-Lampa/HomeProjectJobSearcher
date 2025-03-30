package test.selenuimPages.general;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;

public abstract class AbstractPanel<ParentPage extends AbstractPage> extends AbstractPage {
    /*** ParentPage - page where the panel is located */
    protected final ParentPage parentPage;

    protected ParentPage reInitParentPage() {
        try {
            Constructor constructor = parentPage.getClass().getConstructor(WebDriver.class);
            return (ParentPage) constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Panel - parent element, relative to which all other elements are found
     */
    protected final WebElement panel;

    @Step
    public void scrollToPanel(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", panel);
    }

    public AbstractPanel(WebDriver driver, @Nonnull ParentPage parentPage, By panelSelector) {
        this(driver, parentPage, waitElement(driver, panelSelector, 3 * DEFAULT_EXPLICIT_TIMEOUT));
    }

    public AbstractPanel(WebDriver driver, @Nonnull ParentPage parentPage, WebElement panel) {
        super(driver, panel);
        this.parentPage = parentPage;
        this.panel = panel;
    }
}