import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<KMeans.Instance> trainset = CsvReader.readCsv("src/dataset/JDT.csv");

        List<KMeans.Instance> trainset2;
        trainset2 = CsvReader.minMaxScaler(trainset);

        KMeans km = new KMeans(2, 61);
        km.fit(trainset2);

        double counter = 0.0;
//        double num1 = 0.0;
        double acc;
        List<KMeans.Instance> testset = CsvReader.readCsv("src/dataset/Lucene.csv");

        List<KMeans.Instance> testset2;
        testset2 = CsvReader.minMaxScaler(testset);

        for (int i = 0; i < testset2.size(); i++) {
            double[] x = testset2.get(i).x;
            int predicted = km.calcDistance(x, km.clusterCenters[0]) < km.calcDistance(x, km.clusterCenters[1]) ? 0 : 1;
            int label = testset2.get(i).label;
            if (predicted == label)
                counter += 1.0;
//            if (predicted == 1)
//                num1 += 1.0;
            System.out.println(i + ": " + label + " & " + predicted + " & " + km.calcDistance(x, km.clusterCenters[0]) + " & " + km.calcDistance(x, km.clusterCenters[1]));
        }
        acc = counter / testset.size();
        if (acc < 0.5)
            acc = 1 - acc;
        System.out.println("The accuracy is : " + acc);
//        System.out.println(km.clusterCenters[0].length + " " + testset2.get(0).x.length);
//        System.out.println(km.clusterCenters[0] + "\n" + km.clusterCenters[1]);
//        System.out.println("There are 1 : " + num1);
//        for (int i = 0; i < 61; i++) {
//            System.out.println(km.clusterCenters[0][i] + " & " + km.clusterCenters[1][i]);
//        }
    }
}
