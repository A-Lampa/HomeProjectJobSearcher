package test.userScenarios;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import test.EnvironmentVariables;
import test.selenuimPages.linkedIn.feedPage.LinkedInFeedPage;
import test.selenuimPages.linkedIn.LinkedInLoginPage;
import test.selenuimPages.linkedIn.LinkedInSignInPage;

public class LoginScenario {
    public final static String URL_LINKEDIN = EnvironmentVariables.getString("linkedIn_url");
    public final static String USER_LINKEDIN_EMAIL = EnvironmentVariables.getString("linkedIn_email");
    public final static String USER_LINKEDIN_PASSWORD = EnvironmentVariables.getString("linkedIn_password");

    @Step
    public static LinkedInSignInPage openLinkedInLoginPage(WebDriver driver) {
        Allure.step("Open web page " + URL_LINKEDIN, () -> {
            driver.get(URL_LINKEDIN);
        });
        LinkedInLoginPage loginPage = new LinkedInLoginPage(driver);
        LinkedInSignInPage signInPage = loginPage.clickSignInWithEmailButton();
        signInPage.screen();
        return signInPage;
    }

    @Step
    public static LinkedInFeedPage loginLinkedInUser(WebDriver driver) {
        LinkedInSignInPage signInPage = openLinkedInLoginPage(driver);
        signInPage.setEmail(USER_LINKEDIN_EMAIL);
        signInPage.setPassword(USER_LINKEDIN_PASSWORD);
        LinkedInFeedPage feedPage = signInPage.clickSignInButton();
        feedPage.screen();
        return feedPage;
    }
}
