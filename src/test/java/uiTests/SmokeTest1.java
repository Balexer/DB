package uiTests;

import baseEntity.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.administration.ProjectsPage;
import steps.AdministrationStep;
import steps.LoginStep;
import steps.ProjectStep;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmokeTest1 extends BaseTest {
    @Test
    public void login() {
        LoginStep loginStep = new LoginStep(browsersService);
        loginStep.login("balexer@mail.ru", "Ezr9AuV4OkTVegpS1iI.");
    }

    @Test(dependsOnMethods = "login")
    public void createProject() {
        String projectName = "";
        String projectType = "";
        String query = "SELECT * FROM public.\"project\" where id = 1 ";
        ResultSet rs = jdbcService.executeQuery(query);
        try {
            while (rs.next()){
                projectName = rs.getString("projectname");
                projectType = rs.getString("projecttype");


            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ProjectStep projectStep = new ProjectStep(browsersService);
        projectStep.createNewProject(projectName, projectType);

        Assert.assertTrue(browsersService.getDriver().getTitle().equalsIgnoreCase(projectName + " - TestRail")
                || browsersService.getDriver().getTitle().equalsIgnoreCase("Projects - TestRail"));
    }

    @Test(dependsOnMethods = "createProject")
    public void deleteProject() {
        String projectName = "";
        String query = "SELECT * FROM public.\"project\" where id = 1 ";
        ResultSet rs = jdbcService.executeQuery(query);
        try {
            while (rs.next()){
                projectName = rs.getString("projectname");


            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AdministrationStep administrationStep = new AdministrationStep(browsersService);
        administrationStep.deleteProject(projectName);

        ProjectsPage projectsPage = new ProjectsPage(browsersService, false);
        String newProjectName = projectName;
        Assert.assertThrows(TimeoutException.class, () -> projectsPage.getProjectItemLink(newProjectName));
    }
}
