// Program4-2 Color class
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: Color class

public class Color{

  // (r)ed, (g)reen, (b)lue, 0.0 <= r,g,b <= 1.0
  private double r,g,b;

  // constructor
  public Color(){
    r=0; g=0; b=0;
  }

  // function of setting color
  public Color(double r, double g, double b){
    this.r = r;
    this.g = g;
    this.b = b;
  }

  // return red value
  public double getR(){
    return r;
  }

  // return green value
  public double getG(){
    return g;
  }

  // return blue value
  public double getB(){
    return b;
  }
  
}
