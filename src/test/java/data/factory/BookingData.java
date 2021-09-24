package data.factory;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.http.ContentType;
import model.AuthModel;
import model.Booking;
import model.Bookingdates;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class BookingData extends BaseTest {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;
    private String bookingdates_checkin;
    private String bookingdates_checkout;
    private int status;


    public BookingData(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout, String additionalneeds, int status ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = new Bookingdates(bookingdates_checkin, bookingdates_checkout);
        this.bookingdates_checkin = bookingdates_checkin;
        this.bookingdates_checkout = bookingdates_checkout;
        this.additionalneeds = additionalneeds;
        this.status = status;
    }


    @Parameterized.Parameters(name = "{0}, {1}, statusCode Expected {7}")
    public static Collection inputBooking(){
        return Arrays.asList(new Object[][] {
                {"Joe",	    "Juarez",   -10,    true,	"2021-10-01",	"2021-10-15",	"Breakfast",400},
                {"li",	    "juarez",   100,    false,	"2021-10-02",	"2021-10-16",	"all",	    400},
                {"Joe",	    "Jua",	    200,	true,	"2021-10-03",	"2021-10-17",	"meal",	    200},
                {"Juana",	"la",	    300,	false,	"2021-10-04",	"2021-10-18",	"drinks",	400},
                { "",       "perez",	200,	true,"	2021-10-05",	"2021-10-19",	""	,       400},
                {"Juana",   "	"	,   100,	false,	"2021-10-06",	"2021-10-20",		"",     400},
                {"Alicia",	"Juarez",	0,	    true,	"2021-10-07",	"2021-10-21",		"",     400},
                {"Juana",	"mendoza",	1,	    false,	"2021-10-08",	"2021-10-22",	"Breakfast",200},
                {"Julio",	"Melendrez",50,	    false,	"2021-10-10",	"2021-10-24",	"meal",	    200},
                {"julian",	"perez",	100,	true,	"2021-10-08",	"2021-10-22",	"Breakfast",200},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"2021-10-23",	"all",	    200},
                {"julian",	"perez",	100,	true,	"2020-10-11",	"2021-10-26",	"meal",	    400},
                {"Anahi",	"banana",	100,	false,	"2021-10-11",	"2020-10-11",	"drinks",	400},
                {"julian",	"perez",	100,	true,	"2021-10-10",	"2021-10-09",	""	,       400},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"",             ""	,       400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	""	,	    "",             400},
                {"Anahi",	"banana",	100,	false,	"2021-10-09",	"2021-10-25",	"meal",	    400},
                {"julian",	"perez",	100,	true,	"2021-10-02",	"2021-10-09",	"drinks",	400},
                {"Anahi",	"banana",	100,	false,	"2021-02",	    "2021-10-09",	    "",     400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"2021-05",	    ""	,       400},
                {"Anahi",	"banana",	100,	false,	"hi",	        "2021-10-09",	"Breakfast",400},
                {"julian",	"perez",	100,	true,	"2021-10-09",	"hi",	        "all",	    400},

                { null,      "perez",	200,	true,"	2021-10-05",	"2021-10-19",	null	,   400},
                {"Juana",   null	,   100,	false,	"2021-10-06",	"2021-10-20",	null,       400},
                {"Joe",	    "Juarez",   -10,    true,	null,	        null,	        "Breakfast",400},

                {"julian",	"perez",	100,	0,	    "2021-10-10",	"2021-10-24",	"meal",	    200},
                {"Anahi",	"banana",	100,	1,	    "2021-10-11",	"2021-10-25",	"drinks",	200},
                {"Anahi",	"banana",	100,	5,	    "2021-10-07",	"2021-10-21",	""	,       400},
                {"Anahi",	"banana",	100,    "",  "2021-10-05",	"2021-10-19",	    ""	,       400},
                {"julian",	"perez",	100,	"no",	"2021-10-06",	"2021-10-20",	""	,       400},
                {"Gabriela","Pancha",  "veinte",false,	"2021-10-11",	"2021-10-25",	"drinks",	400},
                {"Anahi",	"Cardenaz",	"",     true,	"2021-10-09",	"2021-10-23",	"all",	    400},
        });
    }

    @Test
    public void requestAuthwithData(){
        Booking bookigpost = new Booking(firstname,lastname,totalprice,depositpaid, bookingdates_checkin,bookingdates_checkout,additionalneeds);

        int response = given()
                .contentType(ContentType.JSON).log().all()
                .header("Accept", "application/json")
                .body(bookigpost)
                .post(BOOKING)
                .then()
                .assertThat()
                .body("booking.firstname", Matchers.equalTo(firstname))
                .body("bookingid", notNullValue())
                .log().all().extract().statusCode();

        String bodyResponse = given()
                .body(bookigpost)
                .post(BOOKING)
                .then()
                .extract().body().asString();
        Assert.assertEquals("Status Code Should be: ",status,response);

        //Assert.assertNotNull("Id should exist", from(bodyResponse).get("bookingid"));
        //Assert.assertTrue( from(bodyResponse).get("bookingid"));
        //Assert.assertFalse( from(bodyResponse).get("bookingid"),Matchers.lessThan(from(bodyResponse).get("bookingid")));
//        List<Integer> bookingMapint = from(bodyResponse).getList("bookingid");
//        Assert.assertNotNull("the response should contain items ", response);
//        Assert.assertTrue("El {id} no existe en la lista de bookings", bookingMapint.contains(idBooking));
    }



}
