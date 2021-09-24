package data.factory;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AuthModel;
import org.apache.logging.log4j.core.config.Order;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class AuthParameterized extends BaseTest {
    private String username;
    private String password;
    private int status;
    private AuthModel authmodel;

    public AuthParameterized(String username, String password, int status) {
        super();
        this.username = username;
        this.password = password;
        this.status = status;
    }

    @Parameterized.Parameters(name = "user: {0}, password: {1}")
    public static Collection inputAuth() {
        return Arrays.asList(new Object[][]{
                {"admin", "password123", 200},
                {"user", "password", 400},
                {null, null, 400},
                {"user", null, 400},
                {null, "password", 400},
        });
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("We Should generate Token")
    public void requestAuthwithData() {
        AuthModel auth = new AuthModel(username, password);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(auth)
                .post(AUTH);

       // int code= response.getStatusCode();
       // String token=response.path("token");
        Assert.assertEquals("Status Code Should be: ", status, response.getStatusCode());

    }


}
