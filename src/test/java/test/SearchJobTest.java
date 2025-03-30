package test;

import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import test.selenuimPages.linkedIn.feedPage.LinkedInFeedPage;
import test.userScenarios.LoginScenario;

public class SearchJobTest extends BaseTest {

    @Test
    @AllureId("00")
    @Owner("Anna")
    @Tags({@Tag("User")})
    public void testLinkedInUserFeedHomePage() {
        LinkedInFeedPage feedPage = LoginScenario.loginLinkedInUser(driver);
    }

}
