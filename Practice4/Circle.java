// Program4-6 Circle class :derive class from Shape2D
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: Derived class of circle

import java.io.PrintStream;

public class Circle extends Shape2D{

  // center coordinate of circle
  private Coord2 v;
  // radius
  private double r;

  // constructor
  public Circle(double r, Coord2 v, Color c){
    // Set vertices
    this.v = v;
    this.r = r;
    // Set color class using base class function
    super.setColor(c);
  }

  // function that returns the perimeter of a circle : override
  public double perimeter(){
    return 2.0 * Math.PI * r;
  }

  // function that returns the area of a circle : override
  public double area(){
    return Math.PI * r * r;
  }

  // function that draws the figure of a circle : override
  public void psPrint(PrintStream cout){
    // Set the name
    setName("Circle");
    // Set the shape
    printHead(cout);
    // Set the color
    cout.println( getColor().getR()+" "+getColor().getG()+" "+getColor().getB()+" setrgbcolor" );
    // Start drawing
    cout.println( "newpath" );
    cout.println(v.getX()+" "+v.getY()+" "+r+" 0 360 arc");
    cout.println( "stroke" );
  }
}