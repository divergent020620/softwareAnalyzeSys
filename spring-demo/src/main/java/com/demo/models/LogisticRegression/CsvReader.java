package com.demo.models.LogisticRegression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    public static List<LogisticRegression.Instance> readCsv(String filename) throws FileNotFoundException {
        List<LogisticRegression.Instance> dataset = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("c"))
                    continue;
                String[] columns = line.split(",");

                int i;
                double[] data = new double[columns.length - 1];
                for (i = 0; i < columns.length - 1; i++) {
                    data[i] = Double.parseDouble(columns[i]);
                }
                int label;
                if (columns[i].equals("clean"))
                    label = 0;
                else
                    label = 1;
                LogisticRegression.Instance instance = new LogisticRegression.Instance(label, data);
                dataset.add(instance);
            }
        } finally {
            if (scanner != null)
                scanner.close();
        }
        return dataset;
    }
}
//    public static List<Instance> readDataSet(String file) throws FileNotFoundException {
//        List<Instance> dataset = new ArrayList<Instance>();
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new File(file));
//            while(scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (line.startsWith("#")) {
//                    continue;
//                }
//                String[] columns = line.split("\\s+");
//
//                // skip first column and last column is the label
//                int i = 1;
//                int[] data = new int[columns.length-2];
//                for (i=1; i<columns.length-1; i++) {
//                    data[i-1] = Integer.parseInt(columns[i]);
//                }
//                int label = Integer.parseInt(columns[i]);
//                Instance instance = new Instance(label, data);
//                dataset.add(instance);
//            }
//        } finally {
//            if (scanner != null)
//                scanner.close();
//        }
//        return dataset;
//    }