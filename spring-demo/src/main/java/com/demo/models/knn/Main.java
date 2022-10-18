package com.demo.models.knn;

import com.demo.entity.Instance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Instance> trainset = CsvReader.readCsv("src/main/java/com/demo/models/datasets/JDT.csv");

//        List<Instance> trainset2 = CsvReader.minMaxScaler((trainset));
        for (int j = 0; j < 150; j++) {
            int k = 3;
            KNN knn = new KNN(k, trainset);

            List<Instance> testset = CsvReader.readCsv("src/main/java/com/demo/models/datasets/PDE.csv");

//            List<Instance> testset2 = CsvReader.minMaxScaler(testset);

            double counter = 0.0;
            double acc;

            for (int i = 0; i < testset.size(); i++) {
                double[] x = testset.get(i).x;
                int label = testset.get(i).label;
                int predicted = knn.predict(x);
                if (predicted == label)
                    counter++;
//            System.out.println(i + " & " + label + " & " + predicted);
            }
            acc = counter / testset.size();

            System.out.println("The accuracy of k = " + k + " is : " + acc);
            System.out.println("The number of accurate outcomes is : " + counter);
            System.out.println("The size of testset is : " + testset.size());

        }
    }
}
