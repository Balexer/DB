package baseEntity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import core.BrowsersService;
import core.ReadProperties;
import services.JDBCService;
import utils.Listener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.given;

@Listeners(Listener.class)
public abstract class BaseApiTest {
    public BrowsersService browsersService;
    public ReadProperties properties;
    public JDBCService jdbcService;

    @BeforeMethod
    public void setup() {
        jdbcService = new JDBCService();
        jdbcService.connectionDB();
        jdbcService.createTableProject();
        jdbcService.insertTestData();
        properties = new ReadProperties();
        RestAssured.baseURI = properties.getURL();
        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic("balexer@mail.ru", "Ezr9AuV4OkTVegpS1iI.");
    }
}