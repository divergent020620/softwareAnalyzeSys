package com.demo.tools;

import com.demo.entity.Instance;
import com.demo.models.LogisticRegression.LogisticRegression;
import com.demo.models.bayes.Bayes;
import com.demo.models.bayes.TestBayes;
import com.demo.models.kmeans.KMeans;
import com.demo.models.knn.KNN;
import com.rf.real.RfUtil;
import com.rf.real.model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.demo.models.bayes.TestBayes.readTxt;

public class ModelUtil {

    public static int featureNumber=61;


    public static int logisticRegressionPredict(Double[] attrs) {
        double[] features=new double[featureNumber];
        for (int i = 0; i < attrs.length; i++) {
            features[i] = attrs[i];
        }
        LogisticRegression lr=new LogisticRegression();
        String modelPath="D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\LogisticRegression\\lr.txt";
        lr.readModel(modelPath);
        int result = lr.predict(features);
        return result;
    }

    public static int kmeansPredict(Double[] attrs) {
        KMeans kMeans = new KMeans(2,featureNumber);
        String modelPath="D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\kmeans\\kmeans.txt";
        kMeans.readModel(modelPath);

//        for (int i = 0; i < kMeans.clusterCenters.length; i++) {
//            for (int j = 0; j < kMeans.clusterCenters[i].length ; j++) {
//                if (j==kMeans.clusterCenters[i].length-1){
//                    System.out.println(kMeans.clusterCenters[i][j]);
//                }else System.out.print(kMeans.clusterCenters[i][j]+",");
//            }
//        }

        double[] features = new double[attrs.length];
        for (int i = 0; i < attrs.length; i++) {
            features[i] = attrs[i];
        }
        int result = kMeans.predict(features);
        return result;
    }

    public static int knnPredict(Double[] attrs) {
        String path="D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\datasets\\JDT.csv";
        try {
            List<Instance> instances = CsvReader.readCsv(path);
            instances=CsvReader.minMaxScaler(instances);
            KNN knn = new KNN(2, instances);
            double[] features = new double[attrs.length];
            for (int i = 0; i < attrs.length; i++) {
                features[i] = attrs[i];
            }
            int result = knn.predict(features);
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static int bayesPredict(Double[] attrs){
        int result = TestBayes.predict(attrs);
        return result;
    }

    public static int rfPredict(Double[] attrs, int numOfTree){
        model model = RfUtil.readObj(numOfTree);
        int predictResult = RfUtil.predict(model, attrs);
        return predictResult;
    }


    public static int multiPredict(Double[] attrs) {
        int lrScore=logisticRegressionPredict(attrs);
        int rfScore=rfPredict(attrs,1000);
        int bayesScore = bayesPredict(attrs);
        int knnScore = knnPredict(attrs);
        int kmeansScore = kmeansPredict(attrs);
        double temp=(lrScore*3+rfScore*2+bayesScore*2+knnScore+kmeansScore)/9.0;
        return temp>0.5?1:0;
    }
}
