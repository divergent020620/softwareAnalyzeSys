package com.demo.models.kmeans;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.nio.channels.ReadPendingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;

public class KMeans {
    int k;
    public double[][] clusterCenters;

    public KMeans(){
    }

    public KMeans(int k, int featureNum) {
        this.k = k;
        clusterCenters = new double[this.k][featureNum];
    }

    public static class Instance {
        public int label;
        public double[] x;

        public Instance(int label, double[] x) {
            this.label = label;
            this.x = x;
        }
    }

    private double calcDistance(double[] a, double[] b) {
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
        while (true) {
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

        int index = closestCluster(x, clusterCenters);
        System.out.println(index);
        return index;
    }

    public void saveModel() {
        String modelPath = "D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\kmeans\\kmeans.txt";
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(modelPath));
            for (int i = 0; i < clusterCenters.length; i++) {
                for (int j = 0; j < clusterCenters[i].length; j++) {
                    if (j == clusterCenters[i].length - 1)
                        br.write(clusterCenters[i][j] + "\n");
                    else
                        br.write(clusterCenters[i][j] + ",");
                }
            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double[][] readModel(String modelPath) {
        BufferedReader bufferedReader = null;
        List<List<Double>> weights = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(modelPath));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null)
                    break;

                String[] split = line.split(",");
                List<Double> temp=new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    temp.add(Double.parseDouble(split[i]));
                }
                weights.add(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        clusterCenters = new double[weights.size()][weights.get(0).size()];
        for (int i = 0; i < clusterCenters.length; i++) {
            for (int j = 0; j < clusterCenters[i].length; j++) {
                clusterCenters[i][j]=weights.get(i).get(j);
            }
        }
        return clusterCenters;
    }

}
