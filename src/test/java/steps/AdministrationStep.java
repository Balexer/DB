package steps;

import io.qameta.allure.Step;
import baseEntity.BaseStep;
import core.BrowsersService;
import elements.UIElement;
import pages.administration.AdministrationPage;
import pages.administration.ProjectsPage;

public class AdministrationStep extends BaseStep {

    public AdministrationStep(BrowsersService browsersService) {
        super(browsersService);
    }

    @Step
    public void deleteProject(String projectName) {
        AdministrationPage administrationPage = new AdministrationPage(browsersService, true);
        UIElement projectLink = administrationPage.getProjectsLink();
        projectLink.click();

        ProjectsPage projectsPage = new ProjectsPage(browsersService, false);
        projectsPage.getDeleteIcon(projectName).click();
        projectsPage.getConfirmationYesCheckbox().click();
        projectsPage.getConfirmationOkButton().click();

        browsersService.getWaiters().waitForInvisibility(projectLink);
    }
}
