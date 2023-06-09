package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.entities.Unavailability;

import java.time.LocalDate;
import java.util.Arrays;

public class UnavailableCommand implements CommandHotel {
    private Hotel hotel;
    private String roomNumber;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String note;

    public UnavailableCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] command) {
        if (command.length < 5) {
            System.out.println("Invalid command. Usage: unavailable <room> <from> <to> <note>");
            return;
        }
        roomNumber = command[1];
        fromDate = LocalDate.parse(command[2]);
        toDate = LocalDate.parse(command[3]);
        note = String.join(" ", Arrays.copyOfRange(command, 4, command.length));

        Room room=hotel.getRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Invalid room number");
            return;
        }

        Unavailability unavailability = new Unavailability(fromDate, toDate, note);

        room.setUnavailability(unavailability);
        System.out.println("Room " + roomNumber + " is set unavailable for the range: " + fromDate + " to " + toDate);
    }
}
