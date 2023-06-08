package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;

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

        availableRooms=findAvailableRooms();
        Room room=findRoomWithFewestBeds();
        if(room==null) {
            System.out.println("No available rooms.");
            return;
        }
        System.out.println(room);
        availableRooms.clear();
    }

    private Set<Room> findAvailableRooms(){
        for (Room room:hotel.getRooms()) {
            if (room.getBedsNumber() >= numberOfBeds) {
                boolean isAvailable = true;
                for (Reservation reservation : room.getReservations()) {
                    if ((reservation.getCheckOutDate().isBefore(fromDate) && reservation.getCheckInDate().isAfter(toDate)) || reservation.getCheckOutDate().equals(fromDate)) {
                        isAvailable = false;
                        break;
                    }
                    if (isAvailable) {
                        availableRooms.add(room);
                    }
                }
            }
        }
        return availableRooms;
    }
    private Room findRoomWithFewestBeds() {
        if (availableRooms.isEmpty()) return null;
        if (availableRooms.size() == 1) return availableRooms.iterator().next();
        Room roomWithFewestBeds = availableRooms.iterator().next();

        for (Room room : availableRooms) {
            if (room.getBedsNumber() < roomWithFewestBeds.getBedsNumber()) {
                roomWithFewestBeds = room;
            }
        }
        return roomWithFewestBeds;
    }

}
