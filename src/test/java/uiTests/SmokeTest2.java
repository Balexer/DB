package uiTests;

import baseEntity.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.administration.ProjectsPage;
import steps.LoginStep;
import steps.ProjectStep;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmokeTest2 extends BaseTest {
    @Test
    public void login() {
        LoginStep loginStep = new LoginStep(browsersService);
        loginStep.login("balexer@mail.ru", "Ezr9AuV4OkTVegpS1iI.");
    }

    @Test(dependsOnMethods = "login")
    public void createProject() {
        String projectName = "";
        String projectType = "";
        String query = "SELECT * FROM public.\"project\" where id = 2 ";
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

        AddProjectPage addProjectPage = new AddProjectPage(browsersService);
        Assert.assertEquals(addProjectPage.getError(), "Field Name is a required field.");
    }


}

