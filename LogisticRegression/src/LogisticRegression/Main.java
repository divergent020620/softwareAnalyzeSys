package LogisticRegression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Enter the learning rate: ");
        Scanner scanner = new Scanner(System.in);
        double para1 = scanner.nextDouble();

        List<LogisticRegression.Instance> trainset = CsvReader.readCsv("src/dataset/JDT.csv");
        LogisticRegression lr = new LogisticRegression(para1, 3000, 61);
        lr.fit(trainset);

        double counter = 0.0;
        double acc;
        List<LogisticRegression.Instance> testset = CsvReader.readCsv("src/dataset/Lucene.csv");
        for (int i = 0; i < testset.size(); i++) {
            double[] x = testset.get(i).x;
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
        acc = counter/ 691.0;
        System.out.println("The accuracy is " + acc);
    }
}
