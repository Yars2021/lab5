package ru.itmo.p3114.s312198.file;

import ru.itmo.p3114.s312198.collection.*;
import ru.itmo.p3114.s312198.exception.IncorrectLineFormat;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
   File Reader - reads {@link StudyGroup} set from CSV file
 */
public class DataFileReader {
    Pattern pattern = Pattern.compile("([\\w\\s]+),(-?\\d+?),(-?\\d+?\\.\\d+?),(\\d{4}-\\d{2}-\\d{2}),(\\d+?),(\\d+?),(\\d+?),([\\w\\s]+),([\\w\\s]+)," +
                                      "(\\d+?),([\\w\\s]+),([\\w\\s]+),(-?\\d+?\\.\\d+?),(-?\\d+?\\.\\d+?),(-?\\d+?\\.\\d+?),([\\w\\s]+);(\\d+);");

    private StudyGroup parseLine(String line) throws IncorrectLineFormat {
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            throw new IncorrectLineFormat(line);
        }

        try {
            StudyGroupBuilder studyGroupBuilder;
            PersonBuilder personBuilder;

            studyGroupBuilder = new StudyGroupBuilder()
                    .addName(FieldParser.parseName(matcher.group(1)))
                    .addCoordinates(new Coordinates(Long.parseLong(matcher.group(2)), Double.parseDouble(matcher.group(3))))
                    .addCreationDate(FieldParser.parseDate(matcher.group(4)))
                    .addStudentsCount(FieldParser.parseStudentsCount(matcher.group(5)))
                    .addShouldBeExpelled(FieldParser.parseShouldBeExpelled(matcher.group(6)))
                    .addTransferredStudents(FieldParser.parseTransferredStudents(matcher.group(7)))
                    .addFormOfEducation(FieldParser.parseFormOfEducation(matcher.group(8)))
                    .addGroupAdmin(null);

            if (!" ".equals(matcher.group(9))) {
                personBuilder = new PersonBuilder()
                        .addName(FieldParser.parseName(matcher.group(9)))
                        .addHeight(FieldParser.parseHeight(matcher.group(10)))
                        .addHairColor(FieldParser.parseHairColor(matcher.group(11)))
                        .addNationality(FieldParser.parseNationality(matcher.group(12)))
                        .addLocation(null);

                if (!("0.0".equals(matcher.group(13)) && "0.0".equals(matcher.group(14)) && "0.0".equals(matcher.group(15)) && " ".equals(matcher.group(16)))) {
                    personBuilder.addLocation(new Location(Float.parseFloat(matcher.group(13)), Float.parseFloat(matcher.group(14)),
                            Float.parseFloat(matcher.group(15)), matcher.group(16)));
                }

                studyGroupBuilder.addGroupAdmin(personBuilder.toPerson());
            }

            StudyGroup studyGroup = studyGroupBuilder.toStudyGroup();
            studyGroup.setId(Long.parseLong(matcher.group(17)));

            try {
                studyGroup.toCSVLine();
            } catch (Exception e) {
                throw new IncorrectLineFormat(line);
            }

            return studyGroup;
        } catch (ValueOutOfBoundsException | NumberFormatException fme) {
            fme.printStackTrace();
            throw new IncorrectLineFormat(line);
        }
    }

    /**
     * Reads data from a text file (filename)
     * @param filename File name
     * @return A collection of StudyGroups
     */
    public LinkedHashSet<StudyGroup> getData(String filename) {
        LinkedHashSet<StudyGroup> studyGroups = new LinkedHashSet<>();

        File file = new File(filename);

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    studyGroups.add(parseLine(line));
                } catch (IncorrectLineFormat ilf) {
                    // Bad line format. Trying with the next
                    System.out.println("ERROR: " + ilf.getMessage() + ": " + line);
                }
            }

        } catch (IOException ioe) {
            if (file.exists()) {
                System.out.println("Not enough rights to access file \"" + filename + "\"");
            } else {
                System.out.println("File \"" + filename + "\" does not exist");
            }
        }

        return studyGroups;
    }
}
