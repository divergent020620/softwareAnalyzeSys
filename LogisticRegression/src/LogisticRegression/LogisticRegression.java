package LogisticRegression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LogisticRegression {
    int iterations;
    double alpha;
    double[] weights;

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

    public double predict(double[] x) {
        double logit = .0;
        for (int i = 0; i < weights.length; i++) {
            logit += weights[i] * x[i];
        }
        return sigmoid(logit);
    }


}
