package RestfulBooker.Tests;

import RestfulBooker.Models.Booking;
import RestfulBooker.Models.BookingDates;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BookingTest extends BaseApiTest {

    @Test
    public void shouldCreateBooking(){

        BookingDates dates = new BookingDates("2024-06-01", "2024-06-10");

        Booking newBooking = new Booking(
                "Adam", "Nowak", 150, true, dates, "Nothing");


        given()
                .spec(requestSpec)
                .body(newBooking)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .spec(responseSpec)
                .statusCode(200);

    }
}
