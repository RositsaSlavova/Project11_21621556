package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;

import java.time.LocalDate;


public class CheckOutCommand implements CommandHotel {
    private Hotel hotel;
    private String roomNumber;
    public CheckOutCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] command) {
        if (command.length !=2) {
            System.out.println("Invalid command. Usage: checkout <room>");
            return;
        }
        roomNumber=command[1];

        if(!doesRoomExist()) {
            System.out.println("Invalid room number");
            return;
        }

        Room room=hotel.getRoomByNumber(roomNumber);
        if(room.isAvailable()){
            System.out.println("The room is currently available, so it cannot be checked out.");
        }
        else{
            checkOutRoom();
            System.out.println("Check out successful");
        }
    }

    private boolean doesRoomExist(){
        for (Room room:hotel.getRooms()) {
            if(room.getNumber().equals(roomNumber)) return true;
        }
        return false;
    }

    private boolean checkOutRoom(){
        LocalDate currentDate=LocalDate.now();
        for (Room room:hotel.getRooms()) {
            if(room.getNumber().equals(roomNumber)){
                room.setAvailable(true);
                for (Reservation reservation:room.getReservations()) {
                    if(reservation.getCheckInDate().isBefore(currentDate) && reservation.getCheckOutDate().isAfter(currentDate)){
                        reservation.setCheckOutDate(currentDate);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
