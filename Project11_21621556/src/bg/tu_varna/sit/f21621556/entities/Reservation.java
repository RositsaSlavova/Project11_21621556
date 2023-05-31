package bg.tu_varna.sit.f21621556.entities;

import java.time.LocalDate;

public class Reservation {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestName;
    private int guestNumber;

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, String guestName, int guestNumber) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestName = guestName;
        this.guestNumber = guestNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Check-in Date: " + checkInDate +
                ", Check-out Date: " + checkOutDate +
                ", Guest Name: " + guestName +
                ", Guest Number: " + guestNumber;
    }
}
