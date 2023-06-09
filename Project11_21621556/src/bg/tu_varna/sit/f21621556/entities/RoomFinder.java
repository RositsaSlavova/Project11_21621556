package bg.tu_varna.sit.f21621556.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class RoomFinder {
    private Hotel hotel;
    private int numberOfBeds;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Set<Room> availableRooms;

    public RoomFinder(Hotel hotel, int numberOfBeds, LocalDate fromDate, LocalDate toDate) {
        this.hotel = hotel;
        this.numberOfBeds = numberOfBeds;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.availableRooms=new HashSet<>();
    }

    public Set<Room> findAvailableRooms(){
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

    public Room findRoomWithFewestBeds() {
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
