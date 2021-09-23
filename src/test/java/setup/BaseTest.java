package setup;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static setup.Methods.getToken;

public abstract class BaseTest {

    public static final String BASIC_AUTHENTICATION = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    public static final String BASIC_AUTHENTICATION_HEADER = "Authorization";
    public static final String TOKEN_HEADER = "Token";
    public static final String BOOKING = "/booking/";
    public static final String URL = "https://restful-booker.herokuapp.com";
    public static final String AUTH = "/auth";

    private static final Logger logger = LogManager.getLogger("ReqResTests.class");

    @BeforeClass
    public static void setup() throws FileNotFoundException {
        logger.info("Iniciando la configuracion");
        RestAssured.requestSpecification = defaultRequestSpecification();
        logger.info("Configuration exitosa.");
    }

    private static RequestSpecification defaultRequestSpecification() throws FileNotFoundException {

        List<Filter> filters = new ArrayList<>();
        //filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        filters.add(new AllureRestAssured());

        return new RequestSpecBuilder().setBaseUri(URL)
                .addFilters(filters)
                .setContentType(ContentType.JSON).build();
    }

    public ResponseSpecification defaultResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static void getTokenBuild() {
        Cookie cookie2 = new Cookie.Builder(TOKEN_HEADER, getToken()).setComment("comment 2").build();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addCookie(cookie2)
                .build();
    }





}
