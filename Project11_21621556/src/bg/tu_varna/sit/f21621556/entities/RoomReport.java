package bg.tu_varna.sit.f21621556.entities;

public class RoomReport {
    private String roomNumber;
    private int daysOccupied;

    public RoomReport(String roomNumber, int daysOccupied) {
        this.roomNumber = roomNumber;
        this.daysOccupied = daysOccupied;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getDaysOccupied() {
        return daysOccupied;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setDaysOccupied(int daysOccupied) {
        this.daysOccupied = daysOccupied;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Occupied Days: " + daysOccupied;
    }
}
