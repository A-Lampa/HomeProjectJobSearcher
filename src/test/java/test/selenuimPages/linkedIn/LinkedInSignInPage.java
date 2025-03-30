package test.selenuimPages.linkedIn;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.selenuimPages.general.AbstractPage;
import test.selenuimPages.linkedIn.feedPage.LinkedInFeedPage;

/**
 * Linked In Sign In Page (https://www.linkedin.com/login)
 *
 * <br><img src="pics\LinkedInSignInPage.png" width="750" height="400"/>
 *
 * @author Anna
 */

public class LinkedInSignInPage extends AbstractPage {

    private final By signInFormSelector = By.xpath("//form[@class=\"login__form\"]/parent::div");

    @FindBy(xpath = "//form[@class=\"login__form\"]//input[@id=\"username\"]")
    private WebElement emailInput;

    @Step
    public void setEmail(String userEmail) {
        emailInput.clear();
        emailInput.sendKeys(userEmail);
    }

    @FindBy(xpath = "//form[@class=\"login__form\"]//input[@id=\"password\"]")
    private WebElement passwordInput;

    @Step
    public void setPassword(String userPassword) {
        passwordInput.clear();
        passwordInput.sendKeys(userPassword);
    }

    @FindBy(xpath = "//form[@class=\"login__form\"]//button")
    private WebElement signInButton;

    @Step
    public LinkedInFeedPage clickSignInButton() {
        signInButton.click();
        return new LinkedInFeedPage(driver);
    }
    protected LinkedInSignInPage(WebDriver driver) {
        super(driver);
        waitElement(signInFormSelector);
        Assertions.assertTrue(emailInput.isDisplayed());
        Assertions.assertTrue(passwordInput.isDisplayed());
        Assertions.assertTrue(signInButton.isDisplayed());
    }
}
