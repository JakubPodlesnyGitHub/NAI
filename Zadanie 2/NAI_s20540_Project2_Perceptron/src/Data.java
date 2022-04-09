public class Data {
    private double[] valueTab;
    private String classFromFile;
    private double label;

    public Data(double[] valueTab, String classFromFile) {
        this.valueTab = valueTab;
        this.classFromFile = classFromFile;
        if (classFromFile.equals("Iris-setosa")) {
            label = 1;
        } else {
            label = -1;
        }
    }

    public Data(double[] valueTab) {
        this.valueTab = valueTab;
    }

    public double[] getValueTab() {
        return valueTab;
    }

    public double getLabel() {
        return label;
    }

    public String createString() {
        String s = "< ";
        for (int i = 0; i < valueTab.length; i++) {
            s += valueTab[i] + "  ";
        }
        s += ">";
        return s;
    }

    @Override
    public String toString() {
        return createString() + " Klasa: " + classFromFile + " Etykieta: " + label;
    }


}
