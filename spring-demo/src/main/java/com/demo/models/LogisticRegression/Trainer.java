package com.demo.models.LogisticRegression;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Trainer {
    public static void main(String[] args) throws FileNotFoundException {

        double learningRate = 100;
        int iteration=3000;
        int featureNum=61;
        String trainDataPath="D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\datasets\\PDE.csv";

        List<LogisticRegression.Instance> trainset = CsvReader.readCsv(trainDataPath);
        LogisticRegression lr = new LogisticRegression(learningRate, iteration, featureNum);
        lr.fit(trainset);
        lr.saveModel();

        double counter = 0.0;
        double acc;
        List<LogisticRegression.Instance> testset = CsvReader.readCsv(trainDataPath);
        for (int i = 0; i < testset.size(); i++) {
            double[] x= testset.get(i).x;
            double predicted = lr.predict(x);
            if (predicted > 0.5)
                predicted = 1;
            else
                predicted = 0;
            int label = testset.get(i).label;
            if (predicted == label)
                counter = counter + 1.0;
            System.out.println(i + ": " + label + " & " + predicted);
        }
        acc = counter/ 997;
        System.out.println("The accuracy is " + acc);
    }
}
