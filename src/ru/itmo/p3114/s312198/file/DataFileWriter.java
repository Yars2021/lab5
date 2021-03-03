package ru.itmo.p3114.s312198.file;

import ru.itmo.p3114.s312198.collection.StudyGroup;

import java.io.IOException;
import java.util.LinkedHashSet;

/**
 File Writer - writes {@link StudyGroup} set into CSV file

 */
public class DataFileWriter {
    public void safeWrite(String filename, LinkedHashSet<StudyGroup> studyGroups) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filename)) {
            for (StudyGroup studyGroup : studyGroups) {
                fileWriter.write(studyGroup.toCSVLine() + '\n');
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void write(String filename, LinkedHashSet<StudyGroup> studyGroups) throws IOException {
        java.io.FileWriter fileWriter = new java.io.FileWriter(filename);
        for (StudyGroup studyGroup : studyGroups) {
            fileWriter.write(studyGroup.toCSVLine() + '\n');
        }
        fileWriter.close();
    }
}
