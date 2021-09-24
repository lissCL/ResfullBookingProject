package data.factory;

import io.restassured.http.ContentType;
import model.AuthModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
                {"admin", "password123", 200},
                {"user", "password", 400},
                {null, null, 400},
                {"user", null, 400},
                {null, "password", 400},
        });
    }

    @Test
    public void requestAuthWithData(){
        AuthModel auth=new AuthModel(username,password);

        int response = given()
                            .contentType(ContentType.JSON).log().all()
                            .header("Accept", "application/json")
                            .body(auth)
                            .post(AUTH)
                      .then()
                            .assertThat()
                            .contentType(equalTo("application/json; charset=utf-8"))
                            .log().all()
                            .extract().statusCode();

            Assert.assertEquals("Status Code Shoul be: ",status,response);
            //Assert.assertTrue("The response should be BAD CREDENCIALS",status == 200);

    }


}
