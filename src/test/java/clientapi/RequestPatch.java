package clientapi;

import model.AuthModel;
import model.Booking;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class RequestPatch extends BaseTest {
    private String firstname;
    private String lastname;
    private int status;

    public RequestPatch(String firstname, String lastname, int status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.status = status;
    }
    @Parameterized.Parameters(name = "firstname: {0}, lastname: {1}")
    public static Collection bookingParameters() {
        return Arrays.asList(new Object[][] {
                {"Joe",	    "Juarez", 200},
                //no pueda editar datos incorrectos
                { "",       "perez", 400},
                {"Juana",   "	", 400},
                {"Juana132324",  "Lopez	", 400},
                {"Karen",   "28548254", 400},
                {"!@@#@%^%",   "/.,m[][*)(&%^", 400},

        });
    }

    @Test
    public void patchName(){
        Booking bookingPatch = new Booking(firstname,lastname);

        given()
                .header(BASIC_AUTHENTICATION_HEADER, BASIC_AUTHENTICATION)
                .body(bookingPatch)
                .when()
                .patch(BOOKING + "1")
                .then()
                .log().all()
                .body("isEmpty()", Matchers.is(false))
                .body("firstname", is(firstname))
                .body("lastname", is(lastname))
                .statusCode(200)
                .log().all()
                //validando respuesta
                .contentType(equalTo("text/html; charset=utf-8"));
        //Assert.assertEquals("Firstname: ",firstname);
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
                .when()
                .patch(BOOKING + "56")
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
                .patch(BOOKING + "1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .log().all()
                .contentType(equalTo("text/plain; charset=utf-8"));

    }
}
