package setup;

import io.restassured.RestAssured;

public class Methods extends BaseTest{

    public static int getValidIdBooking() {
        String bookingId = RestAssured.given().get(BOOKING).path("[0].bookingid").toString();
        return Integer.parseInt(bookingId);
    }
    public static String getResponse(int id) {
        String response = RestAssured.given().get(BOOKING + id).asString();
        return response;
    }
    public static int getStatusResponse(int id) {
        int response = RestAssured.given().get(BOOKING + id).statusCode();
        return response;
    }
}
