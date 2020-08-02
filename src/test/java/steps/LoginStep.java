package steps;

import io.qameta.allure.Step;
import baseEntity.BaseStep;
import core.BrowsersService;
import pages.LoginPage;

public class LoginStep extends BaseStep {

    public LoginStep(BrowsersService browsersService) {
        super(browsersService);
    }

    @Step
    public void login(String username, String password) {
        LoginPage loginPage = new LoginPage(browsersService);

        loginPage.getEmailField().sendKeys(username);
        loginPage.getPasswordField().sendKeys(password);
        loginPage.getLoginButton().click();
    }
}
