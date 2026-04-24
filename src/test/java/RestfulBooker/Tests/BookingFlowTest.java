package RestfulBooker.Tests;

import RestfulBooker.Models.AuthCredentials;
import RestfulBooker.Models.Booking;
import RestfulBooker.Models.BookingDates;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;


public class BookingFlowTest extends BaseApiTest {

    @ParameterizedTest
    @CsvSource({
            "Jan, Kowalski, 150, true, Wi-Fi",
            "Jan, Nowak, 200, false, Early Breakfast",
            "John, Doe, 300,true, Nothing "
    })
    public void shouldCreateAndDeleteBookingUsingToken(String firstname, String lastname, int price, boolean deposit, String additionalneeds) {


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

        Booking newBooking = new Booking(firstname, lastname, price, deposit, dates, additionalneeds);

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
