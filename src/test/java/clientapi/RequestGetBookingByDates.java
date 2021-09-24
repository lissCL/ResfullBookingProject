package clientapi;

import model.GetDatesModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class RequestGetBookingByDates extends BaseTest {
    private final String checkin;
    private final String checkout;
    private final int status;

    public RequestGetBookingByDates(String checkin, String checkout, int status) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
    }

    //Method get filter by Dates
    @Test
    public void getFiltersByDates() {
        GetDatesModel getDatesModel = new GetDatesModel(checkin, checkout);
//        ResponseItem response = new ResponseItem();
        int response = given()
                .log().all()
                .when()
                .get(BOOKING + "?" + "checkin=" + getDatesModel.getCheckin() + "&" + "checkout=" + getDatesModel.getCheckout()) //.param("id",getFirstIdBooking())
                .then()
                .body("[0]", hasKey("bookingid"))
                .body("[0]", notNullValue())
                .body("bookingid", hasSize(greaterThan(10))) //review
                .log().all()
                .extract().statusCode();
        Assert.assertEquals("Status Code Shoul be: ", status, response);
    }

    @Parameterized.Parameters
    public static Collection dates() {
        return Arrays.asList(new Object[][]{
                {"2010-02-06", "2022-02-02", 200}, //boundary values
                {"02-06-2015", "06-07-2030", 400}, // boundary values
                {"00-00-00", "00-00-00", 400}, // error
                {"02-dec-2010", "03-feb-2022", 400}, //incorrect dates
                {"02", "02", 400} // incorrect dates
        });
    }
}
