import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.cli.CommandLineInterface;

public class Main {
    public static void main(String[] args){
        Hotel hotel=new Hotel("RSS",2);

        Room room1=new Room("100",3);
        Room room2=new Room("101",5);
        Room room3=new Room("102",2);
        Room room4=new Room("103",1);
        Room room5=new Room("104",4);
        Room room6=new Room("200",2);
        Room room7=new Room("201",3);
        Room room8=new Room("202",4);
        Room room9=new Room("203",2);
        Room room10=new Room("204",1);

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);
        hotel.addRoom(room4);
        hotel.addRoom(room5);
        hotel.addRoom(room6);
        hotel.addRoom(room7);
        hotel.addRoom(room8);
        hotel.addRoom(room9);
        hotel.addRoom(room10);

        CommandLineInterface commandLineInterface=new CommandLineInterface(hotel);
        commandLineInterface.run();
    }
}