package bg.tu_varna.sit.f21621556.implementations;

import bg.tu_varna.sit.f21621556.contracts.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(String fileName) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
