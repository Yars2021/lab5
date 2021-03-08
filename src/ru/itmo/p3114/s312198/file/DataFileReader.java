package ru.itmo.p3114.s312198.file;

import ru.itmo.p3114.s312198.collection.*;
import ru.itmo.p3114.s312198.exception.IncorrectLineFormat;
import ru.itmo.p3114.s312198.exception.ValueOutOfBoundsException;
import ru.itmo.p3114.s312198.util.FieldParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
   File Reader - reads {@link StudyGroup} set from CSV file
 */
public class DataFileReader {
    Pattern pattern = Pattern.compile("(\\w+?),(-?\\d+?),(-?\\d+?\\.\\d+?),(\\d{4}-\\d{2}-\\d{2}),(\\d+?),(\\d+?),(\\d+?),(\\w+?),(\\w+?)," +
                                      "(\\d+?),(\\w+?),(\\w+?),(-?\\d+?\\.\\d+?),(-?\\d+?\\.\\d+?),(-?\\d+?\\.\\d+?),(\\w+?)");

    private StudyGroup parseLine(String line) throws IncorrectLineFormat {
        StudyGroup studyGroup = new StudyGroup();
        Person admin = new Person();

        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            throw new IncorrectLineFormat(line);
        }

        try {
            studyGroup.setName(FieldParser.parseName(matcher.group(1)));
            studyGroup.setCoordinates(new Coordinates(Long.parseLong(matcher.group(2)), Double.parseDouble(matcher.group(3))));
            studyGroup.setCreationDate(FieldParser.parseDate(matcher.group(4)));
            studyGroup.setStudentsCount(FieldParser.parseStudentsCount(matcher.group(5)));
            studyGroup.setShouldBeExpelled(FieldParser.parseShouldBeExpelled(matcher.group(6)));
            studyGroup.setTransferredStudents(FieldParser.parseTransferredStudents(matcher.group(7)));

            studyGroup.setFormOfEducation(FieldParser.parseFormOfEducation(matcher.group(8)));

            admin.setName(FieldParser.parseName(matcher.group(9)));
            admin.setHeight(FieldParser.parseHeight(matcher.group(10)));
            admin.setHairColor(FieldParser.parseHairColor(matcher.group(11)));
            admin.setNationality(Country.valueOf(matcher.group(12).toUpperCase(Locale.ROOT)));
            admin.setLocation(new Location(Float.parseFloat(matcher.group(13)), Float.parseFloat(matcher.group(14)),
                    Float.parseFloat(matcher.group(15)), matcher.group(16)));

            studyGroup.setGroupAdmin(admin);

            return studyGroup;
        } catch (ValueOutOfBoundsException voob) {
            voob.printStackTrace();
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

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    studyGroups.add(parseLine(line));
                } catch (IncorrectLineFormat ilf) {
                    // Bad line format. Trying with the next
                    System.out.println("ERROR: " + ilf.getMessage() + ": " + line);
                }

            }

        } catch (IOException ioe) {
            System.out.println("File does not exist or you do not have enough access rights");
        }

        return studyGroups;
    }
}
