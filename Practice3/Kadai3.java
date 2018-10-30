// Kadai3 class (main class of all)

import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

// To use for header
import java.text.Format;
import java.text.DateFormat;

public class Kadai3{

  // Setting Header
  public String[] myName = {
    "******************************",
    "作成者：二葉知泰：183364",
    "日付：",
    "入力パラメータ：",
    "クラス継承 対話型",
    "******************************"
  };

  // Print Header
  public void myPrint(int x, int y, int z){
    Date now = new Date();
    // To get Date
    Format fmt= DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG); 
    for (int i = 0 ; i < myName.length ; i++){
      System.out.print(myName[i]);
      switch (i) {
        case 2: System.out.print(fmt.format(now)); break; //Date
        case 3: System.out.printf("%d %d %d",x,y,z); break; //Input
      }
      System.out.print('\n');
    }
  }

  public static void main(String[] args){
    // run program when input 3 numbers for fruit
    if(args.length == 3){
      Kadai3 Header = new Kadai3();
      Scanner input = new Scanner( System.in ); // from terminal

      int apple_number = Integer.parseInt(args[0]);
      int strawberry_number = Integer.parseInt(args[1]);
      int grapefruit_number = Integer.parseInt(args[2]);

      Header.myPrint(apple_number, strawberry_number, grapefruit_number);
      
      // generate ArrayList to store Fruit
      ArrayList<Fruit> FruitList = new ArrayList<Fruit>();
      
      // declare Fluits instances
      Apple apple = new Apple();
      Strawberry strawberry = new Strawberry();
      Grapefruit grapefruit = new Grapefruit();

      // ask and answer the fruit number
      // Apple
      System.out.print("<りんご> は何個買いますか？ > ");
      System.out.println(apple_number);
      apple.putHowMany(apple_number);
      apple.print();
      FruitList.add(apple);

      // Strawberry
      System.out.print("<いちご> は何個買いますか？ > ");
      System.out.println(strawberry_number);
      strawberry.putHowMany(strawberry_number);
      strawberry.print();
      FruitList.add(strawberry);

      // Grapefruit
      System.out.print("<グレープフルーツ> は何個買いますか？ > ");
      System.out.println(grapefruit_number);
      grapefruit.putHowMany(apple_number);
      grapefruit.print();
      FruitList.add(grapefruit);
      
      // Calculate sum of vitaminC and price
      double vitaminC = 0.0;
      int price = 0;
      for (int i = 0 ; i < FruitList.size() ; i++){
        Fruit tmp = FruitList.get(i);
        int number = tmp.getHowMany();
        vitaminC += number * tmp.getVitaminC();
        price += number * tmp.getPrice();
      }

      // to show on console
      System.out.println();
      System.out.printf("ビタミンＣ総量： %6.3f (mg)\n", vitaminC);
      System.out.printf("値段： %d (円)\n", price);
      System.out.println();

     // error message
    } else {
      System.out.println("果物の数値を3つ入力してください");
    }
  }
}
