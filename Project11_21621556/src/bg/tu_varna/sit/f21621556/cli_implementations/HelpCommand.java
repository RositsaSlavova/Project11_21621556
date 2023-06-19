package bg.tu_varna.sit.f21621556.cli_implementations;

import bg.tu_varna.sit.f21621556.contracts.Command;

public class HelpCommand implements Command {
    @Override
    public void execute(String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("The following commands are supported:\n");
        sb.append("open <file>\t      opens <file>\n");
        sb.append("close\t          closes currently opened file\n");
        sb.append("save\t          saves the currently open file\n");
        sb.append("saveAs <file>\t  saves the currently open file in <file>\n");
        sb.append("help\t          prints this information\n");
        sb.append("exit\t          exists the program\n");

        String helpText = sb.toString();
        System.out.println(helpText);
    }
}
