package shared;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import utils.*;
import slr.*;

public class SLRAgent extends Agent {
  SLRGUI slrgui;
  SLR slr;
  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    slr = new SLR();
    slr.setCollection(this.getDataSet());
    slrgui = new SLRGUI(this);  
		slrgui.showGui();
  }

  public void makePrediction(double x) {
    addBehaviour(new OneShotBehaviour(){
      @Override
      public void action() {
        double predictedY = slr.getPrediction(x);
        double alpha = slr.getAlpha();
        double beta = slr.getBeta();
        System.out.println("y = B_0 + (B_1)(X_1)");
        System.out.println(String.valueOf(predictedY) + " = " + String.valueOf(alpha) + " + (" + String.valueOf(beta) + ")(" + String.valueOf(x) + ")");
      }
    });
  }

  private ArrayList<DataPair> getDataSet() {
    ArrayList<DataPair> dataSet = new ArrayList<DataPair>();
    dataSet.add(new DataPair(1, 3));
    dataSet.add(new DataPair(2, 6));
    dataSet.add(new DataPair(3, 9));
    dataSet.add(new DataPair(4, 12));
    dataSet.add(new DataPair(5, 15));
    dataSet.add(new DataPair(6, 18));
    dataSet.add(new DataPair(7, 21));
    dataSet.add(new DataPair(8, 24));
    dataSet.add(new DataPair(9, 27));
    return dataSet;
  }
}
