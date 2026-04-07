package Tests;

import Models.Booking;
import Models.BookingDates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeserializationTest extends BaseApiTest{


    @Test
    public void FirstNamesShouldBeTheSame(){


    BookingDates dates = new BookingDates("2024-06-01", "2024-06-10");

    Booking sentBooking = new Booking("Andrzej", "Nowak", 100, true, dates, "Nothing");

    int bookingId = given()
            .spec(requestSpec)
            .body(sentBooking)
            .when()
            .post("/booking")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .path("bookingid");

    Booking receivedBooking = given()
                    .spec(requestSpec)
                    .when()
                    .get("/booking/" + bookingId)
                    .as(Booking.class);


    Assertions.assertEquals(sentBooking.getFirstname(), receivedBooking.getFirstname());

    }
}
