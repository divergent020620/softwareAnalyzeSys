package com.demo.models.LogisticRegression;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogisticRegression {
    private int iterations;
    private double alpha;
    private double[] weights;

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public LogisticRegression() {
    }

    public LogisticRegression(double para1, int para2, int para3) {
        alpha = para1;
        iterations = para2;
        weights = new double[para3];
    }

    private static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public void fit(List<Instance> instances) {
        for (int i = 0; i < iterations; i++) {
            double lik = 0.0;
            for (int j = 0; j < instances.size(); j++) {
                double[] x = instances.get(j).x;
                double predicted = predict(x);
                int label = instances.get(j).label;
                for (int k = 0; k < weights.length; k++) {
                    weights[k] = weights[k] + alpha * (label - predicted) * x[k];
                }
                // not necessary for learning
                lik += label * Math.log(predict(x)) + (1-label) * Math.log(1- predict(x));
            }
            System.out.println("iteration: " + i + " " + Arrays.toString(weights) + " mle: " + lik);
        }
    }

    public static class Instance {
        public int label;
        public double[] x;

        public Instance(int label, double[] x) {
            this.label = label;
            this.x = x;
        }
    }

    public int predict(double[] x) {
        double logit = .0;
        for (int i = 0; i < weights.length; i++) {
            logit += weights[i] * x[i];
        }
        if(sigmoid(logit)<0.5)
            return 0;
        else
            return 1;
    }

    public void saveModel(){
        String modelPath="D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\LogisticRegression\\lr.txt";
        try {
            BufferedWriter br=new BufferedWriter(new FileWriter(modelPath));
            for (int i = 0; i < weights.length; i++) {
                br.write(weights[i]+"\n");
            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double[] readModel(String path) {
        BufferedReader bufferedReader=null;
        List<Double> weights=new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while (true){
                line=bufferedReader.readLine();
                if (line==null)
                    break;
                weights.add(Double.parseDouble(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] result=new double[weights.size()];
        for (int i = 0; i < result.length; i++) {
            result[i]=weights.get(i);
        }
        this.weights=result;
        return result;
    }

}
