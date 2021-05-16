package ru.itmo.p3114.s312198.file;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

/**
 File Writer - writes {@link StudyGroup} set into CSV fileW
 */
public class DataFileWriter {
    /**
     * Writes StudyGroup collection into a file catching all the exceptions
     * @param filename File name
     * @param studyGroups Collection
     */
    public boolean safeWrite(String filename, LinkedHashSet<StudyGroup> studyGroups) {
        File file = new File(filename);

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filename)) {
            for (StudyGroup studyGroup : studyGroups) {
                fileWriter.write(studyGroup.toCSVLine() + '\n');
            }
        } catch (IOException ioe) {
            if (file.exists()) {
                System.out.println("Not enough rights to access file \"" + filename + "\"");
            } else {
                System.out.println("File \"" + filename + "\" does not exist");
            }
            return false;
        }
        return true;
    }

    /**
     * Writes a StudyGroup collection into a file
     * @param filename File name
     * @param studyGroups Collection
     * @throws IOException Failed to write
     */
    public void write(String filename, LinkedHashSet<StudyGroup> studyGroups) throws IOException {
        java.io.FileWriter fileWriter = new java.io.FileWriter(filename);
        for (StudyGroup studyGroup : studyGroups) {
            fileWriter.write(studyGroup.toCSVLine() + '\n');
        }
        fileWriter.close();
    }
}
