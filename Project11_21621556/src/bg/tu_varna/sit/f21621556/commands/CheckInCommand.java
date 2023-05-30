package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;

import java.time.LocalDate;

public class CheckInCommand implements CommandHotel {
    private Hotel hotel;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String note;
    private int guestNumber;
    public CheckInCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] command) {
        if (command.length < 5) {
            System.out.println("You have to write: checkin <room> <from> <to> <note> [<guests>]");
            return;
        }
        roomNumber=command[1];
        checkInDate=LocalDate.parse(command[2]);
        checkOutDate=LocalDate.parse(command[3]);
        note=command[4];

        Room room=hotel.getRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Invalid room number");
            return;
        }
        if(command.length>5) guestNumber=Integer.parseInt(command[5]);
        else guestNumber=room.getBedsNumber();

        if(!isRoomAvailable()){
            System.out.println("The room is already checked in for the specified dates");
            return;
        }

        if(addReservation()){
            System.out.println("Check in successful");
        }
        else {
            System.out.println("Failed to add reservation");
        }

    }
    public boolean isRoomAvailable(){
        for (Room room: hotel.getRooms()) {
            if(room.getNumber().equals(roomNumber)){
                for (Reservation reservation:room.getReservations()) {
                    if (!(checkOutDate.isBefore(reservation.getCheckInDate()) || checkInDate.isAfter(reservation.getCheckOutDate()))){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean addReservation(){
        Reservation reservation=new Reservation(checkInDate,checkOutDate,note,guestNumber);
        for (Room room: hotel.getRooms()) {
            if(room.getNumber().equals(roomNumber)){
                room.addReservation(reservation);
                return true;
            }
        }
        return false;
    }
}
