package pages;

import org.openqa.selenium.By;
import baseEntity.BasePage;
import core.BrowsersService;
import elements.UIElement;

public class DashboardPage extends BasePage {
    protected By addProjectButtonSelector = By.id("sidebar-projects-add");

    public DashboardPage(BrowsersService browsersService) {
        super(browsersService, false);
    }

    @Override
    protected void openPage() {

    }

    public boolean isPageOpened() {
        return browsersService.getDriver().getTitle().equalsIgnoreCase("All Projects - TestRail");
    }

    public UIElement getAddProjectButton() {
        return new UIElement(browsersService, addProjectButtonSelector);
    }
}
