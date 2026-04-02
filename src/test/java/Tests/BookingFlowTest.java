package Tests;

import Models.AuthCredentials;
import Models.Booking;
import Models.BookingDates;
import com.sun.net.httpserver.Request;
import org.junit.jupiter.api.Test;

import static Tests.BaseApiTest.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


public class BookingFlowTest extends BaseApiTest {


    @Test
    public void shouldCreateAndDeleteBookingUsingToken() {


        //1. Pobieranie tokena

        AuthCredentials authCredentials = new AuthCredentials("admin", "password123");

        String token = given()
                .spec(requestSpec)
                .body(authCredentials)
                .when()
                .post("/auth")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("token");


        //2. Tworzenie rezerwacji

        BookingDates dates = new BookingDates("2024-06-01", "2024-06-10");

        Booking newBooking = new Booking("Andrzej", "Pol", 150, true, dates, "Nope");

        int bookingId = given()
                .spec(requestSpec)
                .body(newBooking)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("bookingid");


        //3. Usunięcie rezerwacji

        given()
        .spec(requestSpec)
            .header("Cookie", "token=" + token)
            .when()
            .delete("/booking/" + bookingId)
            .then()
            .statusCode(201);


    }






}
