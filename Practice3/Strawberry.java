// Strawberry class (Derive class)

public class Strawberry extends Fruit{
  final static double vitaminC = 8.06; // mg per unit
  final static int price = 40; // yen per unit
  static int howMany = 0; // initialize

  // call constructor from base class
  public Strawberry(){
    super(Strawberry.vitaminC, Strawberry.price, Strawberry.howMany);
  }

  // print function
  public void print(){
    System.out.print("<いちご>");
    // call from base function
    super.print();
  }

  // main function (unit test)
  public static void main(String[] args){
    Strawberry strawberry = new Strawberry();
    strawberry.putHowMany(3);
    strawberry.print();
  }
}
