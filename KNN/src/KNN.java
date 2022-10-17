import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class KNN {
    int k;
    List<Instance> dataset;

    public KNN(int para1, List<Instance> instances) {
        k = para1;
        dataset = instances;
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

    public int[] getKNN(double[] x) {
        int size = dataset.size();
        int[] KNN = new int[k];
        double[] temp = new double[size];

        for (int i = 0; i < size; i++)
            temp[i] = calcDistance(x, dataset.get(i).x);
        HashMap map = new HashMap();
        for (int i = 0; i < size; i++)
            map.put(temp[i], i);
        Arrays.sort(temp);

        for (int i = 0; i < k; i++)
            KNN[i] = Integer.parseInt(map.get(temp[i]).toString());

        return KNN;
    }

    public int predict(double[] x) {
        int temp0 = 0, temp1 = 0;
        int[] KNN = new int[k];

        KNN = getKNN(x);
        for (int i = 0; i < k; i++) {
            if (dataset.get(KNN[i]).label == 0)
                temp0++;
            else
                temp1++;
        }

//        System.out.println(temp0 + " & " + temp1);

        return temp1 > temp0 ? 1 : 0;
    }
}
