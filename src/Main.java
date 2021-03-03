import ru.itmo.p3114.s312198.collection.StudyGroup;
import ru.itmo.p3114.s312198.file.DataFileReader;
import ru.itmo.p3114.s312198.util.ConsoleReader;
import ru.itmo.p3114.s312198.util.command.CommandLineProcessor;

import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        LinkedHashSet<StudyGroup> studyGroups;
        DataFileReader reader = new DataFileReader();

        if (args == null || args.length == 0) {
            studyGroups = new LinkedHashSet<>();
        } else {
            studyGroups = reader.getData(args[0]);
        }

        CommandLineProcessor commandLineProcessor = new CommandLineProcessor(studyGroups);
        while (true) {
            System.out.print("> ");
            commandLineProcessor.parseInput(ConsoleReader.readLine(), false);
        }
    }
}
