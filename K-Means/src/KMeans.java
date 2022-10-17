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
import java.util.HashSet;
import java.util.Random;

public class KMeans {
    int k;
    double[][] clusterCenters;

    public KMeans(int para1, int para2) {
        k = para1;
        clusterCenters = new double[k][para2];
    }

    public static class Instance {
        public int label;
        public double[] x;

        public Instance(int label, double[] x) {
            this.label = label;
            this.x = x;
        }
    }

    public double calcDistance(double[] a, double[] b) {
        if (a.length != b.length)
            return 0;

        int length = a.length;
        if (length == 0)
            return 0;

        double dis = 0.0;
        for (int i = 0; i < length; i++)
           dis += (a[i] - b[i]) * (a[i] - b[i]);

        return dis;
    }

    private int closestCluster(double[] x, double[][] clusterCenters) {
        int cluster = -1;
        double min = -1;

        for (int i = 0; i < k; i++) {
            double dis = calcDistance(x, clusterCenters[i]);
            if (min < 0 || dis < min) {
                cluster = i;
                min = dis;
            }
        }
        return cluster;
    }

    public void fit(List<Instance> instances) {
        int size = instances.size();
        int length = instances.get(0).x.length;
        int[] clusterIndex = new int[size];

        Random random = new Random();
        HashSet<Integer> checker = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            int center = 0;
            do {
                center = random.nextInt(size);
            } while (checker.contains(center));
            checker.add(center);
            clusterCenters[i] = instances.get(center).x.clone();
        }
        for (int i = 0; i < size; i++)
            clusterIndex[i] = -1;

        boolean flag;
        while(true) {
            flag = false;

            double[][] temp = new double[k][length];
            int[] counter = new int[k];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < length; j++)
                    temp[i][j] = 0;
                counter[i] = 0;
            }

            for (int i = 0; i < size; i++) {
                int index = closestCluster(instances.get(i).x, clusterCenters);
                counter[index]++;
                for (int j = 0; j < length; j++)
                    temp[index][j] += instances.get(i).x[j];
                if (index != clusterIndex[i]) {
                    flag = true;
                    clusterIndex[i] = index;
                }
            }

            if (flag) {
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < length; j++)
                        clusterCenters[i][j] = temp[i][j] / counter[i];
                }
            } else break;
        }
    }

    public int predict(double[] x) {
        int index;
        index = calcDistance(x, clusterCenters[0]) < calcDistance(x, clusterCenters[1]) ? 0 : 1;
        return index;
    }
}
