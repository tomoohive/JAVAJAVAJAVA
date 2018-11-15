// Food class : base class
// Name : Tomoyasu Futaba
// ID : B183364
// Date : 2018/11/06
// Class to hold Food Data

public class Food {

  // declare member variables
  // name of food
  private String name;
  // carbohydrate (content)
  private double carbon;
  // protein (content)
  private double protein;
  // calorie value
  private double calorie;

  // constructor
  public Food () {

  }

  public Food (String name, double carbon, double protein, double calorie){
    this.name = name;
    this.carbon = carbon;
    this.protein = protein;
    this.calorie = calorie;
  }

  // method (function)
  // return the name
  public String getName() {
    return name;
  }

  // return the carbon
  public double getCarbon() {
    return carbon;
  }

  // return the protein
  public double getProtein() {
    return protein;
  }

  // return the calorie
  public double getCalorie() {
    return calorie;
  }
}