import java.util.*;

public class K_Means {
    private CreateListOfVectors createListOfVectors;
    private List<Data> listOfVectors;
    private List<Cluster> clusters;
    private int k;

    public K_Means(String fileName, int k) {
        this.k = k;
        createListOfVectors = new CreateListOfVectors(fileName);
        listOfVectors = createListOfVectors.getListOfVectors();
        clusters = new ArrayList<>();
        initClustersCentroids();
        computeDistanceFromCentroidsToVectors();
    }

    private void initClustersCentroids() {
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int numberOfCentroid = random.nextInt(listOfVectors.size());
            Data centroid = listOfVectors.get(numberOfCentroid);
            Cluster cluster = new Cluster(i);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
    }

    private void computeDistanceFromCentroidsToVectors() {
        List<Data> currCentroids = getCentroids();
        for (Data vector : listOfVectors) {
            double min = 0;
            Data nearestCentroid = null;
            for (Data centroid : currCentroids) {
                centroid.countDistance(vector.getValues());
                if (min == 0) min = centroid.getDistance();
                if (min >= centroid.getDistance()) {
                    min = centroid.getDistance();
                    nearestCentroid = centroid;
                }
            }
            getCluster(nearestCentroid).addVectorToList(vector);
        }
    }

    public double[] countAvgOfCentroids(Cluster cluster) {
        double[] newCentroidVal = new double[cluster.getCentroid().getValues().length];
        for (Data vector : cluster.getListOfVectors()) {
            for (int i = 0; i < vector.getValues().length; i++) {
                newCentroidVal[i] += vector.getValues()[i];
            }
        }

        for (int i = 0; i < newCentroidVal.length; i++) {
            newCentroidVal[i] = newCentroidVal[i] / (double) (cluster.getListOfVectors().size());
        }
        //displayNewCentroidValues(newCentroidVal)
        return newCentroidVal;
    }

    public void updateCentroids() {
        boolean ifChanged = true;
        while (ifChanged) {
            int counter = 0;
            for (Cluster cluster : clusters) {
                double[] newCentroidVal = countAvgOfCentroids(cluster);
                if (!Arrays.equals(cluster.getCentroid().getValues(), newCentroidVal)) {
                    cluster.getCentroid().setValues(newCentroidVal);
                    break;
                } else {
                    counter++;
                }
            }
            if (counter == clusters.size()) {
                ifChanged = false;
            } else {
                for (Cluster c : clusters) {
                    c.getListOfVectors().clear();
                }
                computeDistanceFromCentroidsToVectors();
            }
            displayClusters(false);
        }
    }

    private List<Data> getCentroids() {
        List<Data> centroids = new ArrayList<>();
        for (Cluster cluster : clusters) {
            centroids.add(cluster.getCentroid());
        }
        return centroids;
    }

    private Cluster getCluster(Data centroid) {
        for (Cluster c : clusters) {
            if (Arrays.equals(c.getCentroid().getValues(), centroid.getValues()))
                return c;
        }
        return null;
    }

    public void setK(int k) {
        this.k = k;
        for (Cluster cluster : clusters) {
            cluster.getListOfVectors().clear();
        }
        clusters.clear();
        initClustersCentroids();
        computeDistanceFromCentroidsToVectors();
    }

    public void displayClusters(boolean ifValuesDisplayed) {
        for (Cluster c : clusters) {
            if (ifValuesDisplayed)
                System.out.println(c + " " + c.getListOfVectors());
            else
                System.out.println(c);
        }
    }

    public void displayNewCentroidValues(double[] newCentroidVal) {
        for (int i = 0; i < newCentroidVal.length; i++) {
            System.out.print(newCentroidVal[i] + " ");
        }
        System.out.println();
    }
}
