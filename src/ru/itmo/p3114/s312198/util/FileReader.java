package ru.itmo.p3114.s312198.util;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    private BufferedReader bufferedReader;

    public FileReader(String filename) {
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(filename));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return bufferedReader.readLine().trim();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        return "";
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
