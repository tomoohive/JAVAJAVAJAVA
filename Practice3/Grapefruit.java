// Grapefruit class (Derive class)

public class Grapefruit extends Fruit{

  final static double vitaminC = 75.6; // mg per unit
  final static int price = 97; // yen per unit
  static int howMany = 0; // initialize

  // call constructor from base class
  public Grapefruit(){
    super(Grapefruit.vitaminC, Grapefruit.price, Grapefruit.howMany);
  }

  // print function
  public void print(){
    System.out.print("<グレープフルーツ>");
    // call from base function
    super.print();
  }

  // main function (unit test)
  public static void main(String[] args){
    Grapefruit grapefruit = new Grapefruit();
    grapefruit.putHowMany(2);
    grapefruit.print();
  }
}
