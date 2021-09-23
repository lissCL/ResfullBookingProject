package clientapi;

import org.apache.http.HttpStatus;
import org.junit.Test;
import setup.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



public class RequestGetFilterName extends BaseTest {
    @Test
    public void filterName(){

        given()
                .queryParam("firstname","Eric")
                .queryParam("lastname","Jackson")
                .when()
                .get(BOOKING)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].bookingid",notNullValue())
                .and()
                .body("[0].bookingid",equalTo(3))
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
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all()
                .contentType(equalTo("application/json; charset=utf-8"));


    }
}
