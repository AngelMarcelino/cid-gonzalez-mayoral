package knn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class KNN {
  private ArrayList<DataWithLabel> collection;
  private DistanceCalculator distanceCalculator;
  private int k;

  public KNN() {
    this.distanceCalculator = new DistanceCalculator();
  }

  public void setCollection(ArrayList<DataWithLabel> collection) {
    this.collection = collection;
  }

  public void setK(int k) {
    this.k = k;
  }

  public String makePrediction(double x1, double x2) {
    ArrayList<double[]> distances = new ArrayList<>();
    DataWithLabel observation = new DataWithLabel(x1, x2);
    for (int i = 0; i < collection.size(); i++) {
      DataWithLabel current = collection.get(i);
      double distance = this.distanceCalculator.euclideanDistance(observation, current);
      double[] measurements = new double[] { distance, i, -1 };
      distances.add(measurements);
    }
    HashMap<String, Integer> classesCount = new HashMap<>();
    System.out.println("Before ordering: \n\n");
    for (double[] e : distances) {
      System.out.println("Distance: " + e[0]);
    }
    distances.sort((a, b) -> {
      double diff = (a[0] - b[0]);
      if (diff < 0) {
        return -1;
      }
      if (diff == 0) {
        return 0;
      }
      return 1;
    });
    System.out.println("After ordering: \n\n");
    for (double[] e : distances) {
      System.out.println("Distance: " + e[0]);
    }
    for (int i = 0; i < k; i++) {
      int currentIndex = (int) distances.get(i)[1];
      String foundClass = collection.get(currentIndex).getLabel();
      if (classesCount.containsKey(foundClass)) {
        int num = classesCount.get(foundClass);
        classesCount.put(foundClass, num + 1);
      } else {
        classesCount.put(foundClass, 1);
      }
    }
    ArrayList<Entry<String, Integer>> entriesArray = new ArrayList<>(classesCount.entrySet());

    entriesArray.sort((a, b) -> {
      return b.getValue() - a.getValue();
    });
    
    System.out.println("Hash map entries: ");
    for (Entry<String, Integer> a : classesCount.entrySet()) {
      System.out.println("Class: " + a.getKey() + ", Value: " + a.getValue());
    }

    System.out.println("Distances sorted");
    for (double[] e : distances) {
      int index = (int) e[1];
      DataWithLabel currentElement = collection.get(index);
      System.out.println("X1: " + currentElement.getX() + ", X2: " + currentElement.getY() + ", Class: "
          + currentElement.getLabel() + ", Distance: " + e[0]);
    }
    return entriesArray.get(0).getKey();
  }
}
