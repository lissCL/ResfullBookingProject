package setup;

import io.restassured.RestAssured;
import io.restassured.response.Response;

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
