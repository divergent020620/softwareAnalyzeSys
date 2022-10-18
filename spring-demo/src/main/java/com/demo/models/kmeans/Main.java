package com.demo.models.kmeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<KMeans.Instance> trainset = CsvReader.readCsv("src\\main\\java\\com\\demo\\models\\datasets\\PDE.csv");

//        trainset = CsvReader.minMaxScaler(trainset);

        KMeans km = new KMeans(2, 61);
        km.fit(trainset);

        km.saveModel();

        double counter = 0.0;
//        double num1 = 0.0;
        double acc;
        List<KMeans.Instance> testset = CsvReader.readCsv("src/main/java/com/demo/models/datasets/PDE.csv");

//        testset= CsvReader.minMaxScaler(testset);

        for (int i = 0; i < testset.size(); i++) {
            double[] x = testset.get(i).x;
            int predicted = km.predict(x);
            int label = testset.get(i).label;
            if (predicted == label)
                counter += 1.0;
//            if (predicted == 1)
//                num1 += 1.0;
            System.out.println(i + ": " + label + " & " + predicted);
        }
        acc = counter / testset.size();
        System.out.println("The accuracy is : " + acc);
//        System.out.println("There are 1 : " + num1);
//        for (int i = 0; i < 61; i++) {
//            System.out.println(km.clusterCenters[0][i] + " & " + km.clusterCenters[1][i]);
//        }
    }
}
