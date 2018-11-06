// Program4-4 Triangle class :derive class from Shape2D
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: Derived class of triangle

import java.io.PrintStream;

public class Triangle extends Shape2D{

  private Coord2 v1, v2, v3;

  // constructor
  public Triangle(Coord2 v1, Coord2 v2, Coord2 v3, Color c){
    // Set vertices
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    // Set color class using base class function
    super.setColor(c);
  }

  // Return 3 vertices (in order of v1, v2 and v3)
  public Coord2[] getV(){
    // Generate Coord2 class array
    Coord2[] tmp = new Coord2[3];
    for(int i=0; i<3; i++)
      tmp[i] = new Coord2();

    // add 3 vertices in tmp array
    tmp[0] = v1;
    tmp[1] = v2;
    tmp[2] = v3;
    return tmp;
  }

  // function that returns the perimeter of a triangle : override
  public double perimeter(){
    // get v1-v2, v2-v3, v3-v1 's distances 
    double side1 = Coord2.distance(v1, v2);
    double side2 = Coord2.distance(v2, v3);
    double side3 = Coord2.distance(v3, v1);
    // return sum of distances
    return side1 + side2 + side3;
  }

  // function that returns the area of a triangle : override
  public double area(){
    // get (x,y) coordinates
    double x1 = v1.getX();
    double x2 = v2.getX();
    double x3 = v3.getX();

    double y1 = v1.getY();
    double y2 = v2.getY();
    double y3 = v3.getY();

    // array calculate
    double Ax = x2 - x1;
    double Ay = y2 - y1;
    double Bx = x3 - x1;
    double By = y3 - y1;

    double result = Math.abs(Ax * By - Bx * Ay) * 0.5;
    return result;
  }

  // function that draws figure of a triangle : override
  public void psPrint(PrintStream cout){
    // Set the name
    setName("Triangle");
    // Set the shape
    printHead(cout);
    // Set the color
    cout.println( getColor().getR()+" "+getColor().getG()+" "+getColor().getB()+" setrgbcolor" );
    // Start Drawing
    cout.println( "newpath" );
    cout.println(v1.getX()+" "+v1.getY()+" moveto");
    cout.println(v2.getX()+" "+v2.getY()+" lineto");
    cout.println(v3.getX()+" "+v3.getY()+" lineto");
    cout.println( "closepath" );
    cout.println( "stroke" );
  }
}