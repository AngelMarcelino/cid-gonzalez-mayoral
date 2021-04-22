package slr;

import java.util.ArrayList;

public class SLR {
  private double alpha;
  private double beta;
  public void setCollection(ArrayList<DataPair> dataSet) {
    this.calculateEquation(dataSet);
  }

  public double getPrediction(double x) {
    return this.alpha + this.beta * x;
  }

  private void calculateEquation(ArrayList<DataPair> dataSet) {
    double xSummation = 0;
    double ySummation = 0;
    double xSquareSummation = 0;
    double xTimesYSummation = 0;
    for (int i = 0; i < dataSet.size(); i++) {
      double x = dataSet.get(i).getX();
      double y = dataSet.get(i).getY();
      xSummation += x;
      ySummation += y;
      xSquareSummation += x * x;
      xTimesYSummation += x * y;
    }
    this.alpha = this.calculateAlpha(dataSet.size(), xSummation, ySummation, xSquareSummation, xTimesYSummation);
    this.beta = this.calculateBeta(dataSet.size(), xSummation, ySummation, xSquareSummation, xTimesYSummation);
  }

  private double calculateAlpha(double n, double xSummation, double ySummation, double xSquareSummation,
      double xTimesYSummation) {
    return (ySummation * xSquareSummation - xSummation * xTimesYSummation)
        / (n * xSquareSummation - xSummation * xSummation);
  }

  private double calculateBeta(double n, double xSummation, double ySummation, double xSquareSummation,
      double xTimesYSummation) {
    return (n * xTimesYSummation - xSummation * ySummation) / (n * xSquareSummation - xSummation * xSummation);
  }

  public double getAlpha() {
    return this.alpha;
  }

  public double getBeta() {
    return this.beta;
  }
}
