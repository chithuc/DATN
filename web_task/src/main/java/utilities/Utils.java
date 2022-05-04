package utilities;

import core.constants.Constants;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Utils {

    /**
     * Rounding a double number
     *
     * @param value  Double value
     * @param places The decimal number
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Check string is null or empty.
     */
    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }


    /**
     * Export data to text file.
     *
     * @param path  Path of txt file
     * @param value Value to write in
     */
    public static void writeTxtFile(String path, String value) throws IOException {
        Files.createDirectories(Paths.get(Constants.REPORT_FOLDER_NAME));
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Get current time
     *
     * @param formatPattern type of date time want to displayed.
     */
    public static String getTimeNow(String formatPattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatPattern);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String readFile(String filePath) throws Exception {
        File file = new File(filePath);
        return FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));

    }

    public static List<String> readRecordFromLine(int line, String dataFromFile) {
        List<String> values = new ArrayList<String>();
        String dataLine = dataFromFile.split("\n")[line];
        for (int i =0;i<dataLine.split(",").length;i++) {
            values.add(dataLine.split(",")[i]);
        }
        return values;
    }

    public static String getRandomFiveCharsString(int length) {
        StringBuilder rndString = new StringBuilder();

        while(rndString.length() < length) {
            int index = (new Random()).nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length());
            rndString.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(index));
        }

        return rndString.toString().toString();
    }
}
