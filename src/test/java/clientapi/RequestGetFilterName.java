package clientapi;
import org.apache.http.HttpStatus;
import org.junit.Test;
import setup.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class RequestGetFilterName extends BaseTest{
    private String name = "Sally";
    private String lastname = "Brown";

    @Test
    public void filterName(){

        given()
                .queryParam("firstname",name)
                .queryParam("lastname",lastname)
                .when()
                .get(BOOKING)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].bookingid",notNullValue())
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    @Test
    public void filterNameError(){

        given()
                .queryParam("firstname","Erick")
                .queryParam("lastname","Brown")
                .when()
                .get(BOOKING)
                .then()
                .assertThat()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .body("[0].bookingid",isEmptyOrNullString())
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    @Test
    public void filterNameBug(){

        given()
                .queryParam("firstname","Erick")
                .queryParam("lastname","Brown")
                .log().all()
                .when()
                .get(BOOKING)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"));
    }
}
