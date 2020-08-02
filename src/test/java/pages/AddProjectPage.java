package pages;

import org.openqa.selenium.By;
import baseEntity.BasePage;
import core.BrowsersService;
import elements.UIElement;

public class AddProjectPage extends BasePage {
    protected By addProjectButtonSelector = By.id("accept");
    protected By nameSelector = By.id("name");
    protected By suiteModeSingleSelector = By.id("suite_mode_single");
    protected By suiteModeSingleBaselineSelector = By.id("suite_mode_single_baseline");
    protected By suiteModeMultiSelector = By.id("suite_mode_multi");
    protected By errorMessage = By.className("message-error");

    public AddProjectPage(BrowsersService browsersService) {
        super(browsersService, false);
    }

    @Override
    protected void openPage() {

    }

    public boolean isPageOpened() {
        return new UIElement(browsersService, By.id("accept")).isDisplayed();
    }

    public UIElement getAddProjectButton() {
        return new UIElement(browsersService, addProjectButtonSelector);
    }

    public UIElement getNameField() {
        return new UIElement(browsersService, nameSelector);
    }

    public UIElement getSuiteModeSingleOption() {
        return new UIElement(browsersService, suiteModeSingleSelector);
    }

    public UIElement getSuiteModeSingleBaselineOption() {
        return new UIElement(browsersService, suiteModeSingleBaselineSelector);
    }

    public UIElement getSuiteModeMultiSelectorOption()  {
        return new UIElement(browsersService, suiteModeMultiSelector);
    }

    public String getError() {
        return new UIElement(browsersService, errorMessage).getText();
    }


}
