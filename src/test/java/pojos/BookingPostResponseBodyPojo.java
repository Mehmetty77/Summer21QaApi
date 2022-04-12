package pojos;

public class BookingPostResponseBodyPojo {

    private Integer bookingid;
    private BookingPojo booking;

    public BookingPostResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }
    public BookingPostResponseBodyPojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }
    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }
    public BookingPojo getBooking() {
        return booking;
    }
    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingPostResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}