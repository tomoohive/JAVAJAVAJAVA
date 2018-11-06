// Program4-1 Shape2D class : abstract class
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: 2D Figure class

import java.io.PrintStream;

public abstract class Shape2D{

  // Color class
  private Color color;
  //  set the value of Color variable
  public void setColor(Color c){
    color = c;
  }
  // function of returning Color variable as return value
  public Color getColor(){
    return color;
  }

  // name of shape
  private String name;
  // return the name of shape
  private String getName(){ return name; }
  // setting the name of shape
  protected void setName(String name) { this.name = name; } 
  // PostScript Header
  void printHead(PrintStream cout){
    cout.printf( "%% %s Area = %5.3f\n", getName(), area());
    cout.printf( "%% %s Perimeter = %5.3f\n", getName(), perimeter());
  }

  // calculate area : abstract
  abstract double area();
  // calculate perimeter length : abstract
  abstract double perimeter();
  // print function : abstract
  abstract void psPrint(PrintStream cout);

}