
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);
    private static final String CITY_COMMAND = "CITY";
    private static final String ID_COMMAND = "ID";

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Wrong number of parameters. Command should be: {FILE} [CITY|ID] [CITY_NAME|ID_VALUE]");
            return;
        }

        String path = args[0];
        String command = args[1];
        File file = new File(path);
        try {
            switch (command) {
                case CITY_COMMAND:
                    cityCommand(file, args[2]);
                    break;
                case ID_COMMAND:
                    idCommand(file, args[2]);
                    break;
                default:
                    System.out.println("Invalid Command. Command should be: [CITY|ID]");
            }
        } catch (Exception e) {
            log.error("An error occurred while processing command " + command, e);
        }

    }

    private static void idCommand(File file, String id) {
        new Ingestor(file).getCitiesVisitedBy(id)
                .forEach(System.out::println);
    }

    private static void cityCommand(File file, String city) {
        new Ingestor(file).getPersonsFrom(city)
                .forEach(System.out::println);
    }

}
