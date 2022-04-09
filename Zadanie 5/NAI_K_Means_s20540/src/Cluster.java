import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Data> listOfVectors;
    private int numberOFCluster;
    private Data centroid;

    public Cluster(int numberOFCluster) {
        this.numberOFCluster = numberOFCluster;
        listOfVectors = new ArrayList<>();
    }

    public void addVectorToList(Data vector) {
        listOfVectors.add(vector);
    }

    public List<Data> getListOfVectors() {
        return listOfVectors;
    }

    public boolean checkCentroid(double[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < centroid.getValues().length; j++) {
                if (values[i] != centroid.getValues()[i])
                    return false;
            }
        }
        return true;
    }

    public Data getCentroid() {
        return centroid;
    }

    public void setCentroid(Data centroid) {
        this.centroid = centroid;
    }

    @Override
    public String toString() {
        return "Cluster: " + numberOFCluster + " VectorList Size: " + listOfVectors.size();
    }
}
