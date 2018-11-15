// Regression class : abstract class
// Name : Tomoyasu Futaba
// ID : B183364
// Date : 2018/11/06
// Abstract regression class

public abstract class Regression {

  // declare member variables
  // coefficient
  protected double a,b;
  // contribution rate
  protected double R2;
  // average value of explanatory variable (for calculation)
  protected double xmean;
  // avarage value of objective variable (for calculation)
  protected double ymean;
  // sample number of data
  protected int samples;
  // calculation target explanatory variable only
  protected double[] variables;
  // objective variable (calorie)
  protected double[] labels;
  // predictive value of object variable (several samples)
  protected double[] predicted;

  // constructor
  public Regression(double[] variables, double[] labels) {
    this.a = 0.0;
    this.b = 0.0;
    this.R2 = 0.0;
    this.xmean = 0.0;
    this.ymean = 0.0;
    this.samples = 49; // from csv
    this.variables = variables;
    this.labels = labels;
  }

  // abstract function
  // calculation xmean and ymean from variables and labels
  public abstract void compMean();
  // set predicted, a, b and R2 after calculate single regression
  public abstract void doRegression();
  // return the value of R2
  public abstract double computeR2();

  // method function
  // return value of a
  public double getA() {
    return a;
  } 
  // return value of b
  public double getB() {
    return b;
  }

}
