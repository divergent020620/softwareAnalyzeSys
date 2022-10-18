package com.demo.tools;

import java.io.*;

public class TestingTool {
    public static void main(String[] args) {
        new TestingTool().generateFile();

    }

    public void generateFile(){
        BufferedReader br = null;
        BufferedWriter bw=null;
        Double[] attrs=new Double[61];
        try {
            String path="src/main/java/com/demo/models/datasets/JDT.csv";
            String testFile="C:\\Users\\2146\\Desktop\\buggy.txt";
            br = new BufferedReader(new FileReader(path));
            String record=br.readLine();

            for (int i = 0; i < 5; i++) {
                record=br.readLine();
            }

            record=br.readLine();
            System.out.println(record);
            String[] features = record.split(",");
            for (int i = 0; i < features.length-1; i++) {
                attrs[i] = Double.parseDouble(features[i]);
            }

            bw=new BufferedWriter(new FileWriter(testFile));
            for (int i = 0; i < attrs.length; i++) {
                bw.write(attrs[i].toString()+"\n");
            }
            bw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
