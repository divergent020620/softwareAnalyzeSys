package com.demo.models.kmeans;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.ReadPendingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    public static List<KMeans.Instance> readCsv(String filename) throws FileNotFoundException {
        List<KMeans.Instance> dataset = new ArrayList<>();
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
                KMeans.Instance instance = new KMeans.Instance(label, data);
                dataset.add(instance);
            }
        } finally {
            if (scanner != null)
                scanner.close();
        }
        return dataset;
    }

    public static List<KMeans.Instance> minMaxScaler(List<KMeans.Instance> dataset) {
        int size = dataset.size();
        int length = dataset.get(0).x.length;
        double max, min;
        for (int i = 0; i < length; i++) {
            max = 0;
            min = 0;
            for (int j = 0; j < size - 1; j++) {
                if (dataset.get(j).x[i] < dataset.get(j + 1).x[i])
                    max = dataset.get(j + 1).x[i];
                if (dataset.get(j).x[i] > dataset.get(j + 1).x[i])
                    min = dataset.get(j + 1).x[i];
            }
            for (int j = 0; j < size; j++)
                dataset.get(j).x[i] = (dataset.get(j).x[i] - min) / (max - min);
        }

        return dataset;
    }
}
