// Food Regression class : derive class
// Name : Tomoyasu Futaba
// ID : B183364
// Date : 2018/11/06
// Inheritance class of Regression class

public class FoodRegression extends Regression {

  // constructor
  public FoodRegression(double[] variables, double[] labels) {
    super(variables, labels);
  }

  // calculation xmean and ymean from variables and labels
  public void compMean() {
    double xsum = 0.0;
    double ysum = 0.0;

    for(int i=0; i < variables.length; i++) {
      xsum += variables[i];
      ysum += labels[i];
    }

    xmean = xsum / variables.length;
    ymean = ysum / labels.length;
  }

  // set predicted, a, b and R2 after calculate single regression
  public void doRegression() {
    double Sxx = 0.0;
    double Syy = 0.0;
    double Sxy = 0.0;

    for(int i=0; i < variables.length; i++) {
      Sxx += (variables[i] - xmean) * (variables[i] - xmean);
      Syy += (labels[i] - ymean) * (labels[i] - ymean);
      Sxy += (variables[i] - xmean) * (labels[i] - ymean);
    }

    a = Sxy / Sxx;
    b = ymean - a * xmean;

    predicted = new double[49];
    for(int i=0; i < variables.length; i++) {
      predicted[i] = a * variables[i] + b;
    }

    double R_numerator = 0.0;
    double R_denominator_Syy = Syy;
    double R_denominator_P = 0.0;

    double pmean = 0.0;
    double tmp_sum = 0.0;

    for(int i=0; i < predicted.length; i++){
      tmp_sum += predicted[i];
    }
    pmean = tmp_sum / predicted.length;

    for(int i=0; i < predicted.length; i++){
      R_numerator += (labels[i] - ymean) * (predicted[i] - pmean);
      R_denominator_P += (predicted[i] - pmean) * (predicted[i] - pmean);
    }
    R2 = (R_numerator * R_numerator) / (R_denominator_Syy * R_denominator_P);

  }

  // return the value of R2
  public double computeR2(){
    return R2;
  }

}