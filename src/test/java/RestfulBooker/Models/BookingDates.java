package RestfulBooker.Models;

import lombok.Builder;
import lombok.Data;

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
