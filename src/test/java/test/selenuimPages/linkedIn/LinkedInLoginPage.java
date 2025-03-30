package test.selenuimPages.linkedIn;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.selenuimPages.general.AbstractPage;

/**
 * Linked In Login Page (https://www.linkedin.com)
 *
 * <br><img src="pics\LinkedInLoginPage.png" width="750" height="400"/>
 *
 * @author Anna
 */

public class LinkedInLoginPage extends AbstractPage {

    private final By mainSectionSelector = By.xpath("//section[@data-test-id=\"hero\"]");

    private final By signInWithGoogleButtonSelector = By.xpath("//iframe[@title=\"Sign in with Google Button\"]");

    @FindBy(xpath = "//a[@href=\"https://www.linkedin.com/login\"]")
    private WebElement signInWithEmailButton;

    @Step
    public LinkedInSignInPage clickSignInWithEmailButton() {
        signInWithEmailButton.click();
        System.out.println("Sign In Button click");
        return new LinkedInSignInPage(driver);
    }

    public LinkedInLoginPage(WebDriver driver) {
        super(driver);
        waitElement(mainSectionSelector);
        waitElement(signInWithGoogleButtonSelector);
        Assertions.assertTrue(signInWithEmailButton.isDisplayed());
    }
}
