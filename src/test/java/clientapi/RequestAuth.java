package clientapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import setup.BaseTest;

public class RequestAuth extends BaseTest {

    Response response = RestAssured.given().body("{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}").contentType("application/json")
            .when().post("/auth");



}

