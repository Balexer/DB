package apiTests;

import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;
import baseEntity.BaseApiTest;
import models.Project;
import models.ProjectSimple;
import models.ProjectTypes;
import org.testng.annotations.Test;


import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;


public class apiTest2 extends BaseApiTest {
    int projectID;


    @Test
    public void addProject() {
        String endpoint = "/index.php?/api/v2/add_project";
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

        Integer type = null;
        switch (projectType) {
            case "1":
                type = 1;
                break;
            case "2":
                type = 2;
                break;
            case "3":
                type = 3;
                break;

        }

        Project project = new Project.Builder()
                .withName(projectName)
                .withType(type)
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
