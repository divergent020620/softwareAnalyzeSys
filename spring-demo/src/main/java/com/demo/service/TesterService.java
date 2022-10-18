package com.demo.service;

import com.demo.models.LogisticRegression.LogisticRegression;
import com.demo.tools.ModelUtil;
import com.rf.real.RfUtil;
import com.rf.real.model;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static com.demo.tools.ModelUtil.rfPredict;

@Service
public class TesterService {

//    public String  analyze(HashMap attrs) throws FileNotFoundException {
//        TestExample testExample=new TestExample();
//        for (String attr:(Set<String >) attrs.keySet()) {
//            testExample.getAttrs().put(attr, (String) attrs.get(attr));
//        }
//        //调用模型
//        return "clean";
//    }

    public String analyze(MultipartFile file, String type) throws FileNotFoundException {

        Double[] attrs = new Double[61];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            for (int i = 0; i < 61; i++) {
                String attr = br.readLine();
                attrs[i] = Double.parseDouble(attr);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //将attrs传入模型，预测
        model model = null;
        int predictResult = -1;

        switch (type) {
            case "rf100":
                predictResult = rfPredict(attrs, 100);
                break;
            case "rf1000":
                predictResult = rfPredict(attrs, 1000);
                break;
            case "rf10000":
                predictResult = rfPredict(attrs, 10000);
                break;
            case "lr":
                predictResult = ModelUtil.logisticRegressionPredict(attrs);
                break;
            case "kmeans":
                predictResult = ModelUtil.kmeansPredict(attrs);
                break;
            case "knn":
                predictResult = ModelUtil.knnPredict(attrs);
                break;
            case "bayes":
                predictResult = ModelUtil.bayesPredict(attrs);
                break;
            case "multi":
                predictResult = ModelUtil.multiPredict(attrs);
                break;

        }


        String result = predictResult + "";

        System.out.println(result);

        //调用模型
        return result;
    }

    public Double[] readRecord() {
        BufferedReader br = null;
        Double[] attrs = new Double[61];
        try {
            String path = "D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\datasets\\JDT.csv";
            br = new BufferedReader(new FileReader(path));
            String record = br.readLine();
            record = br.readLine();
            System.out.println(record);
            String[] features = record.split(",");
            for (int i = 0; i < features.length - 1; i++) {
                attrs[i] = Double.parseDouble(features[i]);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return attrs;
    }
}
