package setup;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class Methods extends BaseTest{


    public static int getValidIdBooking() {
        String bookingId = RestAssured.given().get(BOOKING).path("[0].bookingid").toString();
        return Integer.parseInt(bookingId);
    }
    public static String getResponse(int id) {
        return  RestAssured.given().get(BOOKING + id).asString();

    }
    public static int getStatusResponse(int id) {
        return RestAssured.given().get(BOOKING + id).statusCode();

    }
    public static void ifExistBooking(List<Integer> bookingMapint){
        //List<Integer> bookingMapint= from(responseBooking).getList("bookingid");

        for(Integer element : bookingMapint){
            Assert.assertEquals("Status code Should be 200 : ",getStatusResponse(element), HttpStatus.SC_OK);
        }
    }

    public static String getToken() {
        Response response = RestAssured.given().body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}").contentType("application/json")
                .when().post("/auth");
        String token = response.path("token");
        return token;
    }
}
