package clientapi;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import setup.BaseTest;
import static io.restassured.RestAssured.given;
import static setup.Methods.*;

public class RequestDelete extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("We Should Delete Booking successfully with Valid Token ")
    public void testDeleteBooking(){
        int testId = getValidIdBooking();
        given()
                .header("Cookie", "token="+ getToken())
        .when()
                .delete(BOOKING+testId)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        //Verify that the id should deleted
        Assert.assertEquals(getResponse(testId), "Not Found");

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("We Should Delete Booking successfully with Basic Auth ")
    public void testDeleteBookingBasicAuth(){
        int testId = getValidIdBooking();
        given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
        .when()
                .delete(BOOKING+testId)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        //Verify that the id should deleted
        Assert.assertEquals(getResponse(testId), "Not Found");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("We Should not Delete Booking without Authorization")
    public void testDeleteBookingWithOutAuth(){
        int testId = getValidIdBooking();
        given()
                .delete(BOOKING+testId)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("We Should not Delete Booking successfully with Invalid Token ")
    public void testDeleteBookingInvalidToken(){
        int testId = getValidIdBooking();
        given()
                .header("Cookie", "token=6809ef23ce1f8a2")
        .when()
                .delete(BOOKING+testId)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);

        //Verify that the id should not deleted
        Assert.assertEquals("Status code Should be 200 : ",getStatusResponse(testId), HttpStatus.SC_OK);
    }
}
