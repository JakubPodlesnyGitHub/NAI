import java.util.Arrays;

public class Data {
    private double[] values;
    private double distance;

    public Data(double[] values) {
        this.values = values;
        distance = 0;
    }

    public double[] getValues() {
        return values;
    }

    public double getDistance() {
        return distance;
    }

    public void countDistance(double[] centroidValues) {
        double d = 0;
        for (int i = 0; i < values.length; i++) {
            d += Math.pow(centroidValues[i] - values[i], 2);
        }
        d = Math.sqrt(d);
        distance = d;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    private String createStringVector() {
        StringBuilder vectorS = new StringBuilder("< ");
        for (double value : values) {
            vectorS.append(value).append(" ");
        }
        vectorS.append(" >");
        return vectorS.toString();
    }

    @Override
    public String toString() {
        return createStringVector();
    }
}
