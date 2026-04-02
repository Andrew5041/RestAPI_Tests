package Tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.Matchers.lessThan;

public class BaseApiTest {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;



    private static String username = "admin";
    private static String password = "password123";
    private static String uri = "https://restful-booker.herokuapp.com";

    protected final String contentType = "application/json";


    @BeforeAll
    public static void setUp() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(uri)
                .setContentType(ContentType.JSON)
                .build();


        responseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(3000L)) // Każdy test ma trwać < 3s
                .build();


    }

}
