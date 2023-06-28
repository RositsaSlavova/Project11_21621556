package bg.tu_varna.sit.f21621556.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String number;
    private int bedsNumber;
    private List<Reservation> reservations;
    private boolean isAvailable;
    private Unavailability unavailability;

    public Room(String number, int bedsNumber) {
        this.number = number;
        this.bedsNumber = bedsNumber;
        this.reservations = new ArrayList<>();
        this.isAvailable = true;
        this.unavailability=null;
    }

    public String getNumber() {
        return number;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Unavailability getUnavailability() {
        return unavailability;
    }

    public void setUnavailability(Unavailability unavailability) {
        this.unavailability = unavailability;
        if (unavailability != null) {
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isEqual(unavailability.getUnavailableFromDate()) || currentDate.isEqual(unavailability.getUnavailableToDate()) ||
                    (currentDate.isAfter(unavailability.getUnavailableFromDate()) && currentDate.isBefore(unavailability.getUnavailableToDate()))) {
                this.isAvailable = false;
            }
        }
    }//Ако е недостъпна стаята на текущата дата, то се променя статуса ѝ на заета

    @Override
    public String toString() {
        return "Room number: " + number +
                ", Bed number: " + bedsNumber +
                ", Reservations: " + reservations+
                ", Available: " + isAvailable +
                (unavailability != null ? ", Unavailability: " + unavailability : "");
    }
}
