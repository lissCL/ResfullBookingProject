package data.factory;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AuthModel;
import org.apache.http.HttpStatus;
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
    public static Collection inputAuth(){
        return Arrays.asList(new Object[][] {
                {"user", "password", 400},
                {"user", "password", 400},
                {"user", "password", 200},
                {"user", "password", 200},
                {"user", "password", 200},
        });
    }

    @Test
    public void requestAuthwithData(){
        AuthModel auth=new AuthModel(username,password);

        int response = given()
                .contentType(ContentType.JSON).log().all()
                .header("Accept", "application/json")
                .body(auth)
                .post(AUTH)
                .then()
                .extract().statusCode();
        Assert.assertEquals("Status Code Shoul be: ",status,response);
    }


}
