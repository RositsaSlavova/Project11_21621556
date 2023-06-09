package bg.tu_varna.sit.f21621556.implementations;
import bg.tu_varna.sit.f21621556.contracts.Command;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.entities.Unavailability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenCommand implements Command {
    private List<String> fileContent;
    private Hotel hotel;

    public OpenCommand(Hotel hotel) {
        this.hotel = hotel;
        fileContent = new ArrayList<>();
    }
    @Override
    public void execute(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created: " + fileName);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                fileContent.clear();
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent.add(line);
                }
                System.out.println("Successfully opened " + fileName);
                updateHotelRooms(fileContent);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }
    private void updateHotelRooms(List<String> fileContents) {
        for (String line : fileContents) {
            String[] data = line.split(" ");
            if (data.length >= 5) {
                if (data[0].equalsIgnoreCase("u")) { // Проверява дали реда отговаря на unavailability
                    String roomNumber = data[1];
                    LocalDate fromDate = LocalDate.parse(data[2]);
                    LocalDate toDate = LocalDate.parse(data[3]);
                    String note = String.join(" ", Arrays.copyOfRange(data, 4, data.length));

                    Room room = hotel.getRoomByNumber(roomNumber);
                    if (room != null) {
                        Unavailability unavailability = new Unavailability(fromDate, toDate, note);
                        room.setUnavailability(unavailability);
                    }
                }
                else {
                    String roomNumber = data[0];
                    LocalDate checkInDate = LocalDate.parse(data[1]);
                    LocalDate checkOutDate = LocalDate.parse(data[2]);
                    String guestName = data[3];
                    int guestNumber = Integer.parseInt(data[4]);

                    Room room = hotel.getRoomByNumber(roomNumber);
                    if (room != null) {
                        Reservation reservation = new Reservation(checkInDate, checkOutDate, guestName, guestNumber);
                        room.addReservation(reservation);
                        LocalDate currentDate = LocalDate.now();
                        if (room.isAvailable()) {
                            if (currentDate.equals(checkInDate) || (currentDate.isAfter(checkInDate) && currentDate.isBefore(checkOutDate))) {
                                room.setAvailable(false);
                            } else room.setAvailable(true);
                        }
                        // Ако стаята е заета вече, няма нужда да се гледат другите резервации, защото може да са стари и да объркат системата
                        // В деня на check out-а стаята вече се води свободна
                    }
                }
            }
        }
        System.out.println("The file is read.");
    }
    public List<String> getFileContent() {
        return fileContent;
    }
}
