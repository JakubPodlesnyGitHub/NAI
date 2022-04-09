public class Data {
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private double distance;
    private double normalizedSepalLength;
    private double normalizedsepalWidth;
    private double normalizedpetalLength;
    private double normalizedpetalWidth;
    private String name;
    private String classifiedName ;

    public Data(double sepalLength, double sepalWidth, double petalLength, double petalWidth,String name) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.name = name;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }


    public double getPetalWidth() {
        return petalWidth;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getClassifiedName() {
        return classifiedName;
    }

    public void setClassifiedName(String classifiedName) {
        this.classifiedName = classifiedName;
    }

    public double getNormalizedSepalLength() {
        return normalizedSepalLength;
    }

    public void setNormalizedSepalLength(double normalizedSepalLength) {
        this.normalizedSepalLength = normalizedSepalLength;
    }

    public double getNormalizedsepalWidth() {
        return normalizedsepalWidth;
    }

    public void setNormalizedsepalWidth(double normalizedsepalWidth) {
        this.normalizedsepalWidth = normalizedsepalWidth;
    }

    public double getNormalizedpetalLength() {
        return normalizedpetalLength;
    }

    public void setNormalizedpetalLength(double normalizedpetalLength) {
        this.normalizedpetalLength = normalizedpetalLength;
    }

    public double getNormalizedpetalWidth() {
        return normalizedpetalWidth;
    }

    public void setNormalizedpetalWidth(double normalizedpetalWidth) {
        this.normalizedpetalWidth = normalizedpetalWidth;
    }

    @Override
    public String toString() {
        return "Dlugosc Dzialki Kwiatu: " + normalizedSepalLength + " Szerokosc Dzialki Kwiatu: " + normalizedsepalWidth + " Dlugosc Platka: " + normalizedpetalLength + " Szerokosc Platka: " + normalizedpetalWidth;
    }

}
