package clientapi;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import setup.BaseTest;
import setup.Methods;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.notNullValue;

@Tag("Get Booking Request")
public class RequestGetBooking extends BaseTest {
    List<Integer> bookingMapint;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("We Should obtain all BookingID available ")
    public void testBookingList() {

        String response = given()
                .get(BOOKING)
            .then()
                .assertThat()
                .header("Content-Type", "application/json; charset=utf-8")
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid", notNullValue())
                .extract().body().asString();

        int idBooking = from(response).get("[0].bookingid");
        bookingMapint = from(response).getList("bookingid");

        Assert.assertNotNull("the response should contain items ", response);
        Assert.assertTrue("El {id} no existe en la lista de bookings", bookingMapint.contains(idBooking));

        //System.out.println(response);
        //System.out.println(idBooking);

        //validate that all the booking id exist and should status code 200
        Methods.ifExistBooking(bookingMapint);
    }

}
