package com.demo.models.bayes;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBayes {

    static String res;
    //读取测试元组
    public ArrayList<String> readTestData(String string) throws IOException {
        ArrayList<String> candAttr = new ArrayList<String>();

        String str = "";
        str = string;
        //string分析器
        String[] tokenizer = str.split(",");
        for(int i=0;i<tokenizer.length-1;i++){

            candAttr.add(tokenizer[i]);

        }
        res=tokenizer[tokenizer.length-1];
        return candAttr;
    }

    //读取训练集
    public ArrayList<ArrayList<String>> readData() throws IOException {
        ArrayList<ArrayList<String>> list=new ArrayList<>();
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */

            File filename = new File("D:\\coding\\ideaWorkspace\\spring-demo\\src\\main\\java\\com\\demo\\models\\datasets\\JDT.csv"); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            br.readLine();
            line = br.readLine();
            while (line != null) {

                String[] temp=line.split(",");
                ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(temp));
                list.add(arrayList);
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<String> readTxt(String fileName){
        List<String> list=new ArrayList<>();
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */

            File filename = new File(fileName); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            br.readLine();
            line = br.readLine();
            while (line != null) {
                if(line.length()>0){

                    list.add(line);}
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    public String doubleToString(Double[] bufferD){
        String m="";
        for(Double i:bufferD){
            m+=i.toString()+",";
        }
        m=m.substring(0,m.length()-1);
        return m;
    }

    public static int predict(Double[] attrs) {
        TestBayes tb = new TestBayes();
        int righr = 0, total;
        ArrayList<ArrayList<String>> datas = null;
        ArrayList<String> testT = null;
        Bayes bayes = new Bayes();
        try {
            datas = tb.readData();

            testT = tb.readTestData(tb.doubleToString(attrs));
            String k = bayes.predictClass(datas, testT);
            System.out.println(k);
            if ("buggy".equals(k))
                return 1;
            else return 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) {
        TestBayes tb = new TestBayes();
        int righr=0,total;
        ArrayList<ArrayList<String>> datas = null;
        ArrayList<String> testT = null;
        Bayes bayes = new Bayes();
        try {
            datas = tb.readData();

//            List <String> l=  readTxt("C:\\Users\\my\\Desktop\\Java\\byerse\\src\\dataset\\Lucene.csv");
//            for(String c:l) {

//                testT = tb.readTestData(c);
                String k = bayes.predictClass(datas, testT);
                System.out.println(k);
                if(k.equals(res))
                    righr++;
//            }
//            double re=(double) righr/(double)l.size();
//            System.out.println("测试集的数量："+ (new Double(l.size())).intValue());
            System.out.println("分类正确的数量："+(new Double(righr)).intValue());
//            System.out.println("算法的分类正确率为："+ re);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
