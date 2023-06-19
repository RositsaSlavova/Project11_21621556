package bg.tu_varna.sit.f21621556.cli_implementations;

import bg.tu_varna.sit.f21621556.contracts.Command;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.entities.Unavailability;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveCommand implements Command {
    private Hotel hotel;

    public SaveCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Room room : hotel.getRooms()) {
                String roomNumber = room.getNumber();
                List<Reservation> reservations = room.getReservations();

                Unavailability unavailability = room.getUnavailability();
                if (unavailability != null) {
                    String fromDate = unavailability.getUnavailableFromDate().toString();
                    String toDate = unavailability.getUnavailableToDate().toString();
                    String note = unavailability.getNote();
                    String unavailabilityLine = "u " + roomNumber + " " + fromDate + " " + toDate + " " + note;

                    writer.write(unavailabilityLine);
                    writer.newLine();
                }

                for (Reservation reservation : reservations) {
                    String checkInDate = reservation.getCheckInDate().toString();
                    String checkOutDate = reservation.getCheckOutDate().toString();
                    String guestName = reservation.getGuestName();
                    int guestNumber = reservation.getGuestNumber();

                    String line = roomNumber + " " + checkInDate + " " + checkOutDate + " " + guestName + " " + guestNumber;

                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("Successfully saved " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
