package bg.tu_varna.sit.f21621556.implementations;

import bg.tu_varna.sit.f21621556.contracts.Command;
import bg.tu_varna.sit.f21621556.entities.Hotel;

public class CloseCommand implements Command {
    private Hotel hotel;

    public CloseCommand(Hotel hotel) {
        this.hotel = hotel;
    }
    @Override
    public void execute(String fileName) {
        if (fileName == null) {
            System.out.println("No file is currently open.");
            return;
        }
        System.out.println("Successfully closed " + fileName);
        hotel.getRooms().forEach(room -> room.getReservations().clear());
        hotel.getRooms().forEach(room -> room.setAvailable(true));
        hotel.getRooms().forEach(room -> room.setUnavailability(null));
    }
}
