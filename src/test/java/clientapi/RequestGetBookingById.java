package clientapi;

import org.apache.http.HttpStatus;
import org.junit.Test;
import setup.BaseTest;
import setup.Methods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;

public class RequestGetBookingById extends BaseTest {
    //Request Get
    @Test
    public void get_Booking_By_Id() {
        given()
                .when()
                .get(BOOKING + Methods.getValidIdBooking())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstname"))
                .body(containsString("firstname"))
                .body("$", hasKey("lastname"))
                .body(containsString("lastname"))
                .body("$", hasKey("totalprice"))
                .body("$", hasKey("depositpaid"))
                .body("$", hasKey("bookingdates"));
    }

    //Request get booking by Id doesnt exist
    @Test
    public void getBookingsId2() {
        given()
                .when()
                .get(BOOKING + "100")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
