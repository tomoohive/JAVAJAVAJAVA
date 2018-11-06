// Program4-3 Coord2 class
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: Class of holding 2D coordinate information

public class Coord2{

  // 2D coordinate variables
  private double x, y;

  public Coord2(){
        x= 0.0;
        y= 0.0;
    }

  // constructor
  public Coord2(double x, double y){
        this.x=x;
        this.y=y;
    }

  // return x
  public double getX(){
    return x;
  }

  // return y
  public double getY(){
    return y;
  }

  // set coordinate values(x,y)
  public void setCoord2(double x, double y){
    this.x = x;
    this.y = y;
  }

  // return the euclidean distance of two points
  public static double distance(Coord2 v1, Coord2 v2){
    return Math.sqrt((v2.x - v1.x)*(v2.x - v1.x)+(v2.y - v1.y)*(v2.y - v1.y));
  }
  
}