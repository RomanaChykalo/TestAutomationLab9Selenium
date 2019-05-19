package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVParser {
    public static ArrayList<Object> parseCSV(String path){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter(",");
        ArrayList<Object> dataArr = new ArrayList<>();
        while (scanner.hasNext())
        {
            dataArr.add(scanner.next());
        }
        scanner.close();
        return dataArr;
    }
}
