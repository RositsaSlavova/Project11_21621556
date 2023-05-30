package bg.tu_varna.sit.f21621556.cli;

import bg.tu_varna.sit.f21621556.contracts.Command;
import bg.tu_varna.sit.f21621556.entities.Hotel;
import bg.tu_varna.sit.f21621556.entities.Room;
import bg.tu_varna.sit.f21621556.implementations.*;

import java.util.*;

public class CommandLineInterface {
    protected static Map<String, Command> commands;
    private Hotel hotel;
    private String currentFile=null;
    public CommandLineInterface(Hotel hotel) {
        this.hotel = hotel;

        commands = new HashMap<>();
        commands.put("open", new OpenCommand(hotel));
        commands.put("close", new CloseCommand(hotel));
        commands.put("save", new SaveCommand(hotel));
        commands.put("saveas", new SaveAsCommand(hotel));
        commands.put("help",new HelpCommand());
        commands.put("exit",new ExitCommand());
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(">");
            input = scanner.nextLine();
            String[] commandArguments = input.split(" ");
            String commandName = commandArguments[0];

            switch (commandName) {
                case "open": {
                    if (commandArguments.length < 2) {
                        System.out.println("You have to write: open <file>");
                        continue;
                    }
                    String fileName = commandArguments[1];
                    commands.get("open").execute(fileName);
                    currentFile=fileName;
                }
                break;
                case "close": {
                    if (currentFile == null) {
                        System.out.println("No file is currently open.");
                        continue;
                    }
                    commands.get("close").execute(currentFile);
                    currentFile = null;
                }
                break;
                case "save":
                {
                    if (currentFile == null) {
                        System.out.println("No file is currently open");
                        continue;
                    }

                    commands.get("save").execute(currentFile);
                }
                break;
                case "saveas":
                {
                    if (currentFile == null) {
                        System.out.println("No file is currently open");
                        continue;
                    }
                    if (commandArguments.length < 2) {
                        System.out.println("You have to write: saveas <file>");
                        continue;
                    }
                    String fileName = commandArguments[1];
                    commands.get("saveas").execute(fileName);
                }
                break;
                case "help":
                {
                    commands.get("help").execute(null);
                    /*Не съм я направила да се изпълнява, само когато вече сме дали open на файл, защото помощ може
                     * да е нужна по всяко време*/
                }
                break;
                case "exit":
                {
                    commands.get("exit").execute(null);
                }
                break;
                case "print":
                {
                    //Помощна - докато пиша кода
                    List<Room> rooms = hotel.getRooms();
                    for (Room room : rooms) {
                        System.out.println(room);
                    }
                }
                break;
            }
        }
    }
}
