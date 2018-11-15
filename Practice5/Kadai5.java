// Kadai5 class : main class
// Name : Tomoyasu Futaba
// ID : B183364
// Date : 2018/11/06
// Class that Performs Overall Control

import java.util.*;
import java.io.*;
import java.text.Format;     // for Format
import java.text.DateFormat; // for Date Format

public class Kadai5 {
  
  // function that prints Header
  public void myPrint(String filename, String alphabet) {
    // Header variable
    String[] myHeader = {
      "******************************",
      "課題5",
      "作成者:二葉知泰:B183364 ",
      "日付:",
      "入力ファイル名:",
      "内容:",
      "******************************"
    };

    // generate Data class
    Date now = new Date();
    // Format date by japanese local
    Format fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
    // output some data
    for(int i=0; i < myHeader.length; i++) {
      System.out.print(myHeader[i]);
      switch (i) {
        case 3: System.out.print(fmt.format(now)); break; // Date
        case 4: System.out.print(filename); break; // File name
        case 5: System.out.print("カロリーを");
        if(alphabet.equals("C"))
          System.out.print("炭水化物");
        if(alphabet.equals("P"))
          System.out.print("タンパク質");
        System.out.print("で単回帰した場合");      
      } 
      System.out.print('\n');
    }
  }

  // Object of BufferedReader class
  private BufferedReader br = null;

  // function that opens file
  public void openFile(String filename) {
    try {
      // generate BufferedReader Object from filename
      br = new BufferedReader(new FileReader (filename));
    }
    // when not found file
    catch (FileNotFoundException e){
      System.err.println("Error: miss opening" + filename);
      System.exit(1);
    }
    // IO Exception
    catch (Exception e) {
      System.err.println("IO Error :" + filename);
      System.exit(1);
    }
  }

  // function that closes file
  public void closeFile() {
    // If br is not null, filename will be closed.
    try {
      if (br != null) br.close();
    }
    catch (Exception e){ e.printStackTrace(); }
  }

  // Processing by line unit
  public ArrayList<Food> readCSV() {
    double carbon;
    double protein;
    double calorie;
    ArrayList<Food> foodList = new ArrayList<Food>();

    // Retain object of String class divided by split
    String [] resultLine = null;
    try {
      // read the first line
      String firstLine = br.readLine();

      // read the second and subsequent lines
      Food food = new Food();
      // Retain data from csv
      String line;
      while ((line = br.readLine()) != null) {
      // split string data using ,
        resultLine = line.split("\\s*,\\s*");
        carbon=Double.parseDouble(resultLine[2]);
        protein=Double.parseDouble(resultLine[5]);
        calorie=Double.parseDouble(resultLine[3]);
        food =new Food(resultLine[0],carbon,protein,calorie);
        foodList.add(food);
      }
    }
    catch (Exception e){ e.printStackTrace(); }
    return foodList;
  }

  // Kadai5 main function
  public static void main(String[] args) {

    // check the input error
    if((args.length != 2) || ((!args[1].equals("C")) && (!args[1].equals("P")))){
      System.out.println("please enter [javau Kadai5 food.csv {C(arbon) or P(rotein)}]");
      System.exit(0);
    }

    // input variables
    String filename = args[0];
    String alphabet = args[1];

    // Storage Array List
    ArrayList<Food> foodList = new ArrayList<Food>();
    // declare member variables
    double[] variables = new double[49];
    double[] labels = new double[49];
    double a,b,R2;

    // (R)peanuts (K)silk tofu (S)Shiitake
    // (c)arbon (p)rotein (g)GI (cal)orie 
    double Rc=19.6, Rp=26.5, Rg=28, Rcal=0;
    double Kc=2.0, Kp=4, Kg=42,Kcal=0;
    double Sc=4.9, Sp=3, Sg=28,Scal=0;

    // call the Kadai5 class
    Kadai5 mainClass = new Kadai5();
    // print header
    mainClass.myPrint(filename, alphabet);

    mainClass.openFile(filename);
    // Read csv and store it in array
    foodList = mainClass.readCSV();
    mainClass.closeFile();

    // read variables and labels and store them
    for (int i=0;i<foodList.size();i++){
      if(alphabet.equals("P"))
        variables[i]=foodList.get(i).getProtein();
      if(alphabet.equals("C"))
        variables[i]=foodList.get(i).getCarbon();            
      labels[i]=foodList.get(i).getCalorie();
    }


    FoodRegression foodCalculate= new FoodRegression(variables,labels);
    
    // calculate member variables and get paramater
    foodCalculate.compMean();
    foodCalculate.doRegression();
    R2=foodCalculate.computeR2();
    a=foodCalculate.getA();
    b=foodCalculate.getB();
        
        
    // change data by P or C
    if(alphabet.equals("P")){
      Rcal=a*Rp+b;
      Kcal=a*Kp+b;
      Scal=a*Sp+b;
    }
    else if(alphabet.equals("C")){  
      Rcal=a*Rc+b;
      Kcal=a*Kc+b;
      Scal=a*Sc+b;
    }
        
    // show results
    System.out.println("a(回帰係数) = "+ a);
    System.out.println("b(回帰切片) = "+ b);
    System.out.println("R2(寄与率) = "+ R2);
    System.out.println("「落花生」のカロリー予測 = "+ Rcal);
    System.out.println("「絹豆腐」のカロリー予測 = "+ Kcal);
    System.out.println("「しいたけ」のカロリー予測 = "+ Scal);
  }
}