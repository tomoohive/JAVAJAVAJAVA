// Apple class (Derive class)

public class Apple extends Fruit{
  final static double vitaminC = 6.0; // mg per unit
  final static int price = 127; // yen per unit
  static int howMany = 0; // initialize

  // call constructor from base class 
  public Apple(){
    super(Apple.vitaminC, Apple.price, Apple.howMany);
  }

  // print function
  public void print(){
    System.out.print("<りんご>");
    // call from base function
    super.print();
  }

  // main function (unit test)
  public static void main(String[] args){
    Apple apple = new Apple();
    apple.putHowMany(1);
    apple.print();
  }
}
