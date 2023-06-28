package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class AvailabilityCommand implements CommandHotel {
    private Hotel hotel;
    private LocalDate date;
    private Set<String> availableRooms;
    public AvailabilityCommand(Hotel hotel) {
        this.hotel = hotel;
        this.availableRooms=new TreeSet<>();
    }

    @Override
    public void execute(String[] command) {
        if (command.length >2) {
            System.out.println("Invalid command. Usage: availability [<date>]");
            return;
        }

        if(command.length>1) {
            date=LocalDate.parse(command[1]);
            System.out.println(findAvailableRooms());
        }
        else {
            date=LocalDate.now();
            System.out.println(findAvailableRoomsByCurrentDate());
        }
    }

    private Set<String> findAvailableRoomsByCurrentDate(){
        for (Room room:hotel.getRooms()) {
            if(room.isAvailable()) availableRooms.add(room.getNumber());
        }
        return availableRooms;
    }

    private Set<String> findAvailableRooms(){
        for (Room room:hotel.getRooms()) {
            availableRooms.add(room.getNumber());
        }
        for (Room room:hotel.getRooms()){
            for (Reservation reservation:room.getReservations()) {
                if ((date.isAfter(reservation.getCheckInDate()) && date.isBefore(reservation.getCheckOutDate())) || date.equals(reservation.getCheckInDate())) {
                    availableRooms.remove(room.getNumber());
                    break;
                }
                //Ако датата съвпада с check out-а, се води, че стаята е свободна
            }
        }
        return availableRooms;
    }
}
