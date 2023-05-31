package bg.tu_varna.sit.f21621556.commands;

import bg.tu_varna.sit.f21621556.contracts.CommandHotel;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Reservation;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.entities.RoomReport;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReportCommand implements CommandHotel {
    private Hotel hotel;
    private List<RoomReport> reportList;
    private LocalDate fromDate;
    private LocalDate toDate;

    public ReportCommand(Hotel hotel) {
        this.hotel = hotel;
        this.reportList=new ArrayList<>();
    }

    @Override
    public void execute(String[] command) {
        if (command.length <3) {
            System.out.println("Invalid command. Usage: report <from> <to>");
            return;
        }
        fromDate=LocalDate.parse(command[1]);
        toDate=LocalDate.parse(command[2]);
        reportList=createReport();
        printReportList(reportList);
    }

    private List<RoomReport> createReport(){
        int occupiedDays;
        for (Room room:hotel.getRooms()) {
            occupiedDays=0;
            for (Reservation reservation:room.getReservations()) {
                if ((reservation.getCheckOutDate().isAfter(fromDate) && reservation.getCheckInDate().isBefore(toDate)) ||
                        (reservation.getCheckInDate().isBefore(fromDate) && reservation.getCheckOutDate().isAfter(fromDate) && reservation.getCheckOutDate().isBefore(toDate)) ||
                        (reservation.getCheckInDate().isAfter(fromDate) && reservation.getCheckInDate().isBefore(toDate) && reservation.getCheckOutDate().isAfter(toDate)) ||
                        (reservation.getCheckInDate().isBefore(fromDate) && reservation.getCheckOutDate().isAfter(toDate))) {
                    LocalDate startDate = reservation.getCheckInDate().isBefore(fromDate) ? fromDate : reservation.getCheckInDate();
                    LocalDate endDate = reservation.getCheckOutDate().isAfter(toDate) ? toDate : reservation.getCheckOutDate();
                    occupiedDays += (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
                }
            }
            RoomReport roomReport = new RoomReport(room.getNumber(), occupiedDays);
            reportList.add(roomReport);
        }
        return reportList;
    }

    private void printReportList(List<RoomReport> reportList) {
        for (RoomReport roomReport : reportList) {
            System.out.println(roomReport);
        }
    }
}
//Резервацията започва и приключва между дадените дати
//Резервациятра започва преди дадената дата (fromDate) и завършва до дадената дата(toDate)
//Резервацията започва на дадената дата (fromDate) и завършва след дадената дата (toDate)
//Резервацията започва преди дадената дата (fromDate) и завършва след дадената дата (toDate)