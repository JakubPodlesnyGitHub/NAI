public class Data {
    private double[] dataTab;
    private int label;
    private int numberClass;
    private String name;

    public Data(double[] dataTab, int numberClass) {
        this.dataTab = dataTab;
        this.numberClass = numberClass;
        if (numberClass == 2) {
            name = "benign";
            label = 1;
        } else if (numberClass == 4) {
            name = "malignant";
            label = -1;
        }
    }

    public Data(double[] dataTab) {
        this.dataTab = dataTab;
    }

    public double[] getDataTab() {
        return dataTab;
    }

    public int getLabel() {
        return label;
    }

    private String createStringTab() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataTab.length; i++) {
            stringBuilder.append(dataTab[i]);
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Wektor < " + createStringTab() + " >";
    }
}
