// Fruit class (Base class)

public class Fruit{

  private double vitaminC;
  private int price;
  private int howMany;
  
  // constructor
  public Fruit(double vitaminC, int price, int howMany){
    this.vitaminC = vitaminC;
    this.price = price;
    this.howMany = howMany;
  }

  // print function
  public void print(){
    System.out.println("は"+howMany+"個ですね。");
  }

  // input howMany value
  public void putHowMany(int howMany){
    this.howMany = howMany;
  }

  // return vitaminC function
  public double getVitaminC(){
    return vitaminC;
  }
  // return price function
  public int getPrice(){
    return price;
  }
  // return howMany function
  public int getHowMany(){
    return howMany;
  }
}
