package pages;

import org.openqa.selenium.By;
import baseEntity.BasePage;
import core.BrowsersService;
import elements.UIElement;

public class ProjectOverviewPage extends BasePage {

    public ProjectOverviewPage(BrowsersService browsersService) {
        super(browsersService, false);
    }

    @Override
    protected void openPage() {

    }

    public boolean isPageOpened() {
        return new UIElement(browsersService, By.className("header-menu-item-selected")).getText().equalsIgnoreCase("Overview");
    }
}
