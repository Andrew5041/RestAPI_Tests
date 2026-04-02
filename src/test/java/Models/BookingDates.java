package Models;

import Tests.BaseApiTest;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

@Data
@Builder
public class BookingDates {

    private String checkin;
    private String checkout;

    public BookingDates() {
    }

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
