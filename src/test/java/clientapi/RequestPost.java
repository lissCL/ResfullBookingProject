package clientapi;


import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.hamcrest.Matchers;
import org.junit.Test;
import setup.BaseTest;

import java.text.DateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RequestPost extends BaseTest {
    DateFormat formatter = null;
    Date convertedDate1 = null;
    Date convertedDate2 = null;

//    Data data = new Data();
//    Object[][] bookingParameters= data.bookingParameters();


    @Test//(testName = "Create Booking ")
    public void createBooking() {


        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 20,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2019-02-31\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .post(BOOKING)
                .then()

                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid", notNullValue());
    }


//    @Test//(dataProvider = "bookingParameters", dataProviderClass = Data.class)
//    public void setBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout,String additionalneeds, int status) throws ParseException {
//        //RestAssured.defaultParser = Parser.JSON;
//        //System.out.println("\nTest "+Thread.currentThread().getStackTrace()[1].getMethodName()+":\n");
//       // Booking bookigpost = new Booking(firstname,lastname,totalprice,depositpaid, bookingdates_checkin,bookingdates_checkout,additionalneeds);
//
//
//        given()
//                .contentType(ContentType.JSON).log().all()
//                .header("Accept", "application/json")
//                //.body(bookigpost)
//                .post(BOOKING)
//                .then()
//                .assertThat()
//                .log().all()
//                .body("isEmpty()", Matchers.is(false)).
//                body("booking.firstname", Matchers.equalTo(firstname)).
//                body("booking.lastname", Matchers.equalTo(lastname)).
//                body("booking.totalprice", Matchers.equalTo(totalprice)).
//                body("booking.depositpaid", Matchers.equalTo(depositpaid)).
//                ///body("booking.bookingdates.checkin", Matchers.not(Matchers.isEmptyOrNullString())).
//                //body("booking.bookingdates.checkout", Matchers.not(Matchers.isEmptyOrNullString())).
//                        body("booking.additionalneeds", Matchers.equalTo(additionalneeds))
//                .statusCode(status)
//                .body("bookingid", notNullValue());

	      /*  String yyyyMMdd1 = "1988-05-12";
	        String yyyyMMdd2 = "1754-01-25";
	        formatter =new SimpleDateFormat("yyyy-MM-dd");

	        convertedDate1 =(Date) formatter.parse(yyyyMMdd1);
	        convertedDate2 =(Date) formatter.parse(yyyyMMdd2);
	        System.out.println("Date from yyyyMMdd1 and yyyyMMdd2 Strings in Java : " + convertedDate1 + " "+convertedDate2);
	*/   // }
}
