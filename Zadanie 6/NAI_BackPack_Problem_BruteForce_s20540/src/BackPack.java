import java.util.List;

public class BackPack {
    private int capacity;
    private List<Integer> prices;
    private List<Integer> weights;
    private int[][] k;
    private int wSum = 0;

    public BackPack(int capacity, List<Integer> prices, List<Integer> weights) {
        this.capacity = capacity;
        this.prices = prices;
        this.weights = weights;
        k = new int[prices.size() + 1][capacity + 1];
    }

    public void makeAlgorithm() {
        for (int i = 0; i <= prices.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0)
                    k[i][j] = 0;
                else if (weights.get(i - 1) <= j) {
                    k[i][j]
                            = Math.max(prices.get(i - 1)
                                    + k[i - 1][j - weights.get(i - 1)],
                            k[i - 1][j]);
                } else
                    k[i][j] = k[i - 1][j];
            }
        }
        //displayTab();
        int res = k[prices.size()][capacity];
        int capacityTMP = capacity;
        for (int i = prices.size(); i > 0 ; i--) {
            if (res != k[i - 1][capacityTMP]){
                wSum+= weights.get(i - 1);
                res -= prices.get(i - 1);
                capacityTMP -= weights.get(i - 1);
            }
        }
        System.out.println("Weights sum( BAG ): " + wSum + " Prices sum ( BAG ): " + k[prices.size()][capacity] + " Capacity ( BAG ): " + capacity);
    }

    private void displayTab() {
        for (int i = 0; i <= prices.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print(k[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private void clearTab() {
        for (int i = 0; i <= prices.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                k[i][j] = 0;
            }
        }
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        wSum = 0;
        clearTab();
    }
}
