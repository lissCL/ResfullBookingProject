package clientapi;

import org.apache.http.HttpStatus;
import org.junit.Test;
import setup.BaseTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RequestPatch extends BaseTest {

    @Test
    public void patchUserTest() {
        given()
                .body("{\n" +
                        "    \"firstname\" : \"Karen\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
        .when()
                .patch(BOOKING + 1)
        .then()
                .assertThat()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                //validando respuesta
                .contentType(equalTo("application/json; charset=utf-8"));

    }
    @Test
    public void patchBug(){
        //not found
        given()
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                //.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .patch(BOOKING + 65)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all();

    }
    @Test
    public void patchUserTest1() {

        given()
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .header(BASIC_AUTHENTICATION_HEADER, "Basic YWRtaW46cGFzc3dvcmQxMjMa=")
        .when()
                .patch(BOOKING + 1)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .log().all()
                .contentType(equalTo("text/plain; charset=utf-8"));

    }
}
