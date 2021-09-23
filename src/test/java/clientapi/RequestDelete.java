package clientapi;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import setup.BaseTest;

import static io.restassured.RestAssured.given;
import static setup.Methods.*;

public class RequestDelete extends BaseTest {
    @Test//(testName = "Delete Booking with Token")
    public void testDeleteBooking(){
        int testId = getValidIdBooking();
        given()
                .header("Cookie", "token="+ getToken())
                .when()
                .delete(BOOKING+testId)
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Assert.assertEquals(getResponse(testId), "Not Found");

    }

    @Test//(testName = "Delete Booking with Basic Auth")
    public void testDeleteBookingBasicAuth(){
        int testId = getValidIdBooking();
        given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .when()
                .delete(BOOKING+testId)
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Assert.assertEquals(getResponse(testId), "Not Found");
    }

    @Test//(testName = "Delete Booking with out Authorization")
    public void testDeleteBookingWithOutAuth(){
        int testId = getValidIdBooking();
        given()
                .when()
                .delete(BOOKING+testId)
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);

    }
}
