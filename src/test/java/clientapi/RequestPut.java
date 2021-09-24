package clientapi;

import model.Booking;
import model.BookingDates;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;
import setup.Methods;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static setup.Methods.getToken;

@RunWith(Parameterized.class)
public class RequestPut extends BaseTest {
    String firstname;
    String lastname;
    int totalprice;
    Boolean depositpaid;
    BookingDates bookingdates;
    String additionalneeds;
    int status;

    public RequestPut(String firstname, String lastname, int totalprice, Boolean depositpaid, String bookingdates_checkin, String getBookingdates_checkout, String additionalneeds, int status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = new BookingDates(bookingdates_checkin, getBookingdates_checkout);
        this.additionalneeds = additionalneeds;
        this.status = status;
    }

    @Test
    public void updateBookingBasicAuth() {
        Booking bookigpost = new Booking(firstname, lastname, totalprice, depositpaid,
                this.bookingdates.getCheckin(), this.bookingdates.getCheckout(), additionalneeds);
//
        int response = given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .with()
                .body(bookigpost)
                .put(BOOKING + "2")
                .then()
                //.header("Content-Type", "application/json; charset=utf-8")
                //.body("$", hasKey("firstname"))
                .log().all()
//                .body(containsString("firstname"))
//                .body("$", hasKey("lastname"))
//                .body(containsString("lastname"))
//                .body("$", hasKey("totalprice"))
//                .body("$", hasKey("depositpaid"))
//                .body("$", hasKey("bookingdates"))
//                .body("firstname", Matchers.equalTo(firstname))
//                .body("lastname", Matchers.equalTo(lastname))
//                .body("totalprice", Matchers.equalTo(totalprice))
//                .body("depositpaid", Matchers.equalTo(depositpaid))
//                .body("bookingdates.checkin", Matchers.not(Matchers.isEmptyOrNullString()))
//                .body("bookingdates.checkout", Matchers.not(Matchers.isEmptyOrNullString()))
//                .body("additionalneeds", Matchers.equalTo(additionalneeds))
                .extract().statusCode();

        Assert.assertEquals("Status Code Should be: ", status, response);
    }

    //    Request Put update Booking by Id with TOKEN
    @Test
    public void updateBookingToken() {
        Booking bookigpost = new Booking(firstname, lastname, totalprice, depositpaid,
                this.bookingdates.getCheckin(), this.bookingdates.getCheckout(), additionalneeds);

        given()
                .header("Cookie", "token=" + getToken())
                .body(bookigpost)
                .log().all()
                .put(BOOKING + Methods.getValidIdBooking())
                .then()
                .assertThat();
                //.body("$", hasKey("firstname"))
                //.body(containsString("firstname"))
                //.body("$", hasKey("lastname"))
                //.body(containsString("lastname"))
                //.body("$", hasKey("totalprice"))
//                .body("$", hasKey("depositpaid"))
//                .body("$", hasKey("bookingdates"))
//                .body("firstname", Matchers.equalTo(firstname))
//                .body("lastname", Matchers.equalTo(lastname))
//                .body("totalprice", Matchers.equalTo(totalprice))
//                .body("depositpaid", Matchers.equalTo(depositpaid))
//                .body("bookingdates.checkin", Matchers.not(Matchers.isEmptyOrNullString()))
//                .body("bookingdates.checkout", Matchers.not(Matchers.isEmptyOrNullString()))
//                .body("additionalneeds", Matchers.equalTo(additionalneeds))
                //.statusCode(status);
    }

    @Parameterized.Parameters(name = "name: {0}, expected: {7}")
    public static Collection dates_To_Put() {
        return Arrays.asList(new Object[][]{
                {"Joe", "Juarez", 10, true, "2021-10-01", "2021-10-02", "Breakfast", 200},
                {"li", "juarez", -100, false, "2021-10-02", "2021-10-16", "all", 400},
                {"Joe", "Jua", 200, true, "2021-10-03", "2021-10-17", "meal", 200},
//                {"Juana", "la", 300, false, "2021-10-04", "2021-10-18", "drinks", 400},
//                {"", "perez", 200, true, "	2021-10-05", "2021-10-19", "", 400},
//                {"Juana", "	", 100, false, "2021-10-06", "2021-10-20", "", 400},
//                {"Alicia", "Juarez", 0, true, "2021-10-07", "2021-10-21", "", 400},
//                {"Juana", "mendoza", 1, false, "2021-10-08", "2021-10-22", "Breakfast", 200},
//                {"Julio", "Melendrez", 50, false, "2021-10-10", "2021-10-24", "meal", 200},
//                {"julian", "perez", 100, true, "2021-10-08", "2021-10-22", "Breakfast", 200},
//                {"Anahi", "banana", 100, false, "2021-10-09", "2021-10-23", "all", 200},
//                {"julian", "perez", 100, 0, "2021-10-10", "2021-10-24", "meal", 200},
//                {"Anahi", "banana", 100, 1, "2021-10-11", "2021-10-25", "drinks", 200},
//                {"julian", "perez", 100, true, "2020-10-11", "2021-10-26", "meal", 400},
//                {"Anahi", "banana", 100, false, "2021-10-11", "2020-10-11", "drinks", 400},
//                {"julian", "perez", 100, true, "2021-10-10", "2021-10-09", "	", 400},
//                {"Anahi", "banana", 100, false, "2021-10-09", "	", " ", 400},
//                {"julian", "perez", 100, true, "2021-10-09", "	", " ", 400},
//                {"Anahi", "banana", 100, false, "hi", "2021-10-09", "Breakfast", 400},
//                {"julian", "perez", 100, true, "2021-10-09", "hi", "all", 400},
//                {"Anahi", "banana", 100, false, "2021-10-09", "2021-10-25", "meal", 400},
//                {"julian", "perez", 100, true, "2021-10-02", "2021-10-09", "drinks", 400},
//                {"Anahi", "banana", 100, false, "2021-02", "2021-10-09", "	", 400},
//                {"julian", "perez", 100, true, "2021-10-09", "2021-05", "	", 400},
        });
    }
}
