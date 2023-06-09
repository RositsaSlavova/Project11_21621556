package bg.tu_varna.sit.f21621556.entities;

import java.time.LocalDate;

public class Unavailability {
    private LocalDate unavailableFromDate;
    private LocalDate unavailableToDate;
    private String note;

    public Unavailability(LocalDate unavailableFromDate, LocalDate unavailableToDate, String note) {
        this.unavailableFromDate = unavailableFromDate;
        this.unavailableToDate = unavailableToDate;
        this.note = note;
    }

    public LocalDate getUnavailableFromDate() {
        return unavailableFromDate;
    }

    public LocalDate getUnavailableToDate() {
        return unavailableToDate;
    }

    public String getNote() {
        return note;
    }

    public void setUnavailableFromDate(LocalDate unavailableFromDate) {
        this.unavailableFromDate = unavailableFromDate;
    }

    public void setUnavailableToDate(LocalDate unavailableToDate) {
        this.unavailableToDate = unavailableToDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "from Date: " + unavailableFromDate +
                ", to Date: " + unavailableToDate +
                ", note: " + note;
    }
}
