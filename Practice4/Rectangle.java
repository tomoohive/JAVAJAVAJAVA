// Program4-5 Rectangle class :derive class from Shape2D
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: Derived class of rectangle

import java.io.PrintStream;

public class Rectangle extends Shape2D{

  private Coord2 v1, v2;

  // constructor
  public Rectangle(Coord2 v1, Coord2 v2, Color c){
    // Set vertices
    this.v1 = v1;
    this.v2 = v2;
    // Set color class using base class function
    super.setColor(c);
  }

  // Return 3 vertices (in order of v1 and v2)
  public Coord2[] getV(){
    // Generate Coord2 class array
    Coord2[] tmp = new Coord2[2];
    for(int i=0; i<2; i++)
      tmp[i] = new Coord2();

    // add 3 vertices in tmp array
    tmp[0] = v1;
    tmp[1] = v2;
    return tmp;
  }

  // function that returns the perimeter of a rectangle : override
  public double perimeter(){
    double x1 = v1.getX();
    double x2 = v2.getX();

    double y1 = v1.getY();
    double y2 = v2.getY();
    return Math.abs(x1 - x2) * 2 + Math.abs(y1 - y2) * 2;
  }

  // function that return the area of a rectangle : override
  public double area(){
    double x1 = v1.getX();
    double x2 = v2.getX();

    double y1 = v1.getY();
    double y2 = v2.getY();

    return Math.abs(x1 -x2) * Math.abs(y1 - y2);
  }

  // function that draws the figure of a rectanglr : override
  public void psPrint(PrintStream cout){
    // Set the name
    setName("Rectangle");
    // Set the shape
    printHead(cout);
    // Set the color
    cout.println( getColor().getR()+" "+getColor().getG()+" "+getColor().getB()+" setrgbcolor" );
    // Start drawing
    cout.println( "newpath" );
    cout.println(v1.getX()+" "+v1.getY()+" moveto");
    cout.println(v2.getX()+" "+v1.getY()+" lineto");
    cout.println(v2.getX()+" "+v2.getY()+" lineto");
    cout.println(v1.getX()+" "+v2.getY()+" lineto");
    cout.println( "closepath" );
    cout.println( "stroke" );
  }
}