package SwaggerPetstore;

import io.qameta.allure.restassured.AllureRestAssured;
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
    private static String uri = "https://petstore.swagger.io/v2";

    @BeforeAll
    public static void setUp() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(uri)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();


        responseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .build();

    }
}
