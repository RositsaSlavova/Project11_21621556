package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.entities.RoomFinder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FindUrgentCommand implements CommandHotel {
    private Hotel hotel;
    public FindUrgentCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] command) {
        System.out.println("The \"find!\" command is still in progress and not yet available.");
    }
}
