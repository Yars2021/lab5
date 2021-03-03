package ru.itmo.p3114.s312198.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
    private final static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            return bufferedReader.readLine().trim();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        return "";
    }
}
