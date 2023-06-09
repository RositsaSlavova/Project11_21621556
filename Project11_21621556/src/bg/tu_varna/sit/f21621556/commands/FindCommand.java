package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.entities.RoomFinder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FindCommand implements CommandHotel {
    private Hotel hotel;
    private int numberOfBeds;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Set<Room> availableRooms;
    public FindCommand(Hotel hotel) {
        this.hotel = hotel;
        this.availableRooms=new HashSet<>();
    }

    @Override
    public void execute(String[] command) {
        if (command.length <4) {
            System.out.println("Invalid command. Usage: find <beds> <from> <to>");
            return;
        }
        numberOfBeds=Integer.parseInt(command[1]);
        fromDate=LocalDate.parse(command[2]);
        toDate=LocalDate.parse(command[3]);

        RoomFinder roomFinder=new RoomFinder(hotel,numberOfBeds,fromDate,toDate);
        availableRooms=roomFinder.findAvailableRooms();
        Room room=roomFinder.findRoomWithFewestBeds();
        if(room==null) {
            System.out.println("No available rooms.");
            return;
        }
        System.out.println(room);
        availableRooms.clear();
    }
}
