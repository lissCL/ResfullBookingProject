package clientapi;

import org.apache.http.HttpStatus;
import org.junit.Test;
import setup.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;

public class RequestGetBooking extends BaseTest {

    @Test
    public void testBookingList(){
        //logger.info("test 1");
        given()
                .when()
                .get(BOOKING)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",notNullValue());
    }
    @Test
    public void testBookingListID(){
        given()
                .when()
                .get(BOOKING)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0]", hasKey("bookingid"))
                .body("[0].bookingid", instanceOf(Integer.class))
        ;
    }

}
