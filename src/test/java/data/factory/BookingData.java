package data.factory;

import com.github.javafaker.Bool;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import model.Booking;
import model.BookingDates;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class BookingData extends BaseTest {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;

    private String bookingdates_checkin;
    private String bookingdates_checkout;
    private String additionalneeds;
    private int status;


    public BookingData(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout, String additionalneeds, int status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        //this.bookingDates = new BookingDates(bookingdates_checkin, bookingdates_checkout);
        this.bookingdates_checkin = bookingdates_checkin;
        this.bookingdates_checkout = bookingdates_checkout;
        this.additionalneeds = additionalneeds;
        this.status = status;
    }


    @Test
    public void requestwithData() {
        Booking bookigpost = new Booking(firstname, lastname, totalprice, depositpaid, bookingdates_checkin, bookingdates_checkout, additionalneeds);

        Response response = (Response) given()
                .contentType(ContentType.JSON).log().all()
                .header("Accept", "application/json")
                .body(bookigpost)
                .post(BOOKING);


        String actualFirstName= response.jsonPath().getString("booking.firstname");
        Assert.assertEquals("firstname in response is not as expected",actualFirstName,firstname);
        String actualLastName= response.jsonPath().getString("booking.lastname");
        Assert.assertEquals("lastname in response is not as expected",actualLastName,lastname);
        String actualCheckIn= response.jsonPath().getString("booking.bookingdates.checkin");
        Assert.assertEquals("CheckIn in response is not as expected",bookingdates_checkin,actualCheckIn);
        String actualCheckOut= response.jsonPath().getString("booking.bookingdates.checkout");
        Assert.assertEquals("CheckOut in response is not as expected",bookingdates_checkout,actualCheckOut);
        String actualAdditionalNeeds= response.jsonPath().getString("booking.additionalneeds");
        Assert.assertEquals("Additional need is different",additionalneeds,actualAdditionalNeeds);

        Assert.assertNotNull("name should exist", (response.path("booking.firstname")));
        Assert.assertNotNull("lastname should exist", (response.path("booking.lastname")));
        Assert.assertNotNull("checkin should exist", (response.path("booking.bookingdates.checkin")));




        Integer price=response.path("booking.totalprice");
        Assert.assertTrue("the total Price should be positive and more than zero: actualResultis: "+price,price>0);;
        Assert.assertNotNull("Id should exist", (response.path("bookingid")));
        Assert.assertEquals(firstname,response.path("booking.firstname"));
        Assert.assertEquals("Status Code Should be: ", status, response.statusCode());

    }


    @Parameterized.Parameters(name = "{0}, {1}, statusCode Expected {7}")
    public static Collection inputBooking() {
        return Arrays.asList(new Object[][]{
                {"Joe", "Juarez", -10, true, "2021-10-01", "2021-10-15", "Breakfast", 400},
                {"li", "juarez", 100, false, "2021-10-02", "2021-10-16", "all", 400},
                {"Joe", "Jua", 200, true, "2021-10-03", "2021-10-17", "meal", 200},
                {"Juana", "la", 300, false, "2021-10-04", "2021-10-18", "drinks", 400},
                {"", "perez", 200, true, "2021-10-05", "2021-10-19", "", 400},
                {"Juana", "", 100, false, "2021-10-06", "2021-10-20", "", 400},
                {"Alicia", "Juarez", 0, true, "2021-10-07", "2021-10-21", "", 400},
                {"Juana", "mendoza", 1, false, "2021-10-08", "2021-10-22", "Breakfast", 200},
                {"Julio", "Melendrez", 50, false, "2021-10-10", "2021-10-24", "meal", 200},
                {"julian", "perez", 100, true, "2021-10-08", "2021-10-22", "Breakfast", 200},
                {"Anahi", "banana", 100, false, "2021-10-09", "2021-10-23", "all", 200},
                {"julian", "perez", 100, true, "2020-10-11", "2021-10-26", "meal", 400},
                {"Anahi", "banana", 100, false, "2021-10-11", "2020-10-11", "drinks", 400},
                {"julian", "perez", 100, true, "2021-10-10", "2021-10-09", "", 400},
                {"Anahi", "banana", 100, false, "2021-10-09", "", "", 400},
                {"julian", "perez", 100, true, "2021-10-09", "", "", 400},
                {"Anahi", "banana", 100, false, "2021-10-09", "2021-10-25", "meal", 400},
                {"julian", "perez", 100, true, "2021-10-02", "2021-10-09", "drinks", 400},
                {"Anahi", "banana", 100, false, "2021-02", "2021-10-09", "", 400},
                {"julian", "perez", 100, true, "2021-10-09", "2021-05", "", 400},
                {"Anahi", "banana", 100, false, "hi", "2021-10-09", "Breakfast", 400},
                {"julian", "perez", 100, true, "2021-10-09", "hi", "all", 400},

                {null, "perez", 200, true, "	2021-10-05", "2021-10-19", null, 400},
                {"Juana", null, 100, false, "2021-10-06", "2021-10-20", null, 400},
                {"Joe", "Juarez", -10, true, null, null, "Breakfast", 400},

                /* {"julian",	"perez",	100,	0,	    "2021-10-10",	"2021-10-24",	"meal",	    200},
                 {"Anahi",	"banana",	100,	1,	    "2021-10-11",	"2021-10-25",	"drinks",	200},
                 {"Anahi",	"banana",	100,	5,	    "2021-10-07",	"2021-10-21",	""	,       400},
                 {"Anahi",	"banana",	100,    "",  "2021-10-05",	"2021-10-19",	    ""	,       400},
                 {"julian",	"perez",	100,	"no",	"2021-10-06",	"2021-10-20",	""	,       400},
                 {"Gabriela","Pancha",  "veinte",false,	"2021-10-11",	"2021-10-25",	"drinks",	400},
                 {"Anahi",	"Cardenaz",	"",     true,	"2021-10-09",	"2021-10-23",	"all",	    400},*/
        });
    }


}
