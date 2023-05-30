package bg.tu_varna.sit.f21621556.entities;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private int floors;
    private List<Room> rooms;

    public Hotel(String name, int floors) {
        this.name = name;
        this.floors = floors;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getFloors() {
        return floors;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }
    public Room getRoomByNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
