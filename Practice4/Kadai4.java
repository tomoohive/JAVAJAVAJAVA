// Program4-7 Main class : Kadai4
// Name: Tomoyasu Futaba
// ID: B183364
// Date: Oct 30th 2018
// About: This main class is to draw PostScript

import java.util.Random; // random number
import java.util.Date; // Date
import java.io.PrintStream; // output stream
import java.io.FileNotFoundException; // not find the file
import java.io.UnsupportedEncodingException; // not find second argument
import java.util.ArrayList; // dynamic array list
import java.text.Format; // for format
import java.text.DateFormat; // Date Format

public class Kadai4 {
  // canvas size in x direction
  final static double XRANGE = 600.0;
  // canvas size in y direction
  final static double YRANGE = 800.0;
  // maximum radius
  final static double RADIUS = 200.0;

  // PrintScript Header
  public String[] myHeader = {
    "%%!PS-Adobe-3.0",
    "%% Author: Tomoyasu Futaba : B183364",
    "%% File Name: ",
    "%% Date: ",
  };

  // output PrintScript Header
  public void myPrint(PrintStream cout, String name){
    Date now = new Date(); // generate Date class
    // Format date by japanese local 
    Format fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
    for (int i = 0 ; i < myHeader.length ; i++){
      cout.print(myHeader[i]);
      switch (i){
        case 2: cout.print(name); break; // File name
        case 3: cout.print(fmt.format(now)); break; // Date
      }
      cout.print('\n');
    }
  }

  // main function
  public static void main(String[] args){
    int min = 10; //minimum number
    int max = 50; //maximum number
    if(args.length != 2){
      System.err.println("java Kadai4 Kadai4.ps total_shapes");
      System.out.println("Please give a value between 10 and 50");
      System.exit(-1);
    }
    // generate dynamc array Shape2D class list
    ArrayList<Shape2D> list = new ArrayList<Shape2D>();
    // call abstract base class
    Shape2D shape = null;
    // variable of total area
    double total_area = 0;
    // variable of total length
    double total_length = 0;
    Date date = new Date(); // initial random number
    Random random = new Random(); // Random instance
    try {
      int n = Integer.valueOf(args[1]).intValue(); // total figure value

      // error handling
      if(!(min <= n && n <= max)){
        System.err.println("Please give a value between 10 and 50");
        System.exit(1);
      }

      for(int j=0; j<n; j++){
        // Set the color using random variables
        Color color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        // Set the shape number between 0 and 2
        int shapeIndex = random.nextInt(3);
        // generate XRANGE,YRANGE by random number
        Coord2 v1 = new Coord2(XRANGE*random.nextDouble(),YRANGE*random.nextDouble());

        switch(shapeIndex) {
          case 0: //generate triangle
            Coord2 v2 = new Coord2(XRANGE*random.nextDouble(),YRANGE*random.nextDouble());
            Coord2 v3 = new Coord2(XRANGE*random.nextDouble(),YRANGE*random.nextDouble());
            // make instance of derive class : triangle
            shape = new Triangle(v1,v2,v3,color);
            break;

          case 1: // generate circle
            // make instance of derive class : circle
            shape =  new Circle(RADIUS*random.nextDouble(),v1,color);
            break;

          case 2: // generate rectangle
            Coord2 v4 = new Coord2(XRANGE*random.nextDouble(),YRANGE*random.nextDouble());
            // make instance of derive class : rectangle
            shape =  new Rectangle(v1,v4,color);
            break;
        }
        // add Shape2D class instance data to ArratList
        list.add(shape);
      }
      // generate Kadai4 class
      Kadai4 resultOutput = new Kadai4();
      // file name to put out 
      PrintStream cout = new PrintStream(args[0], "UTF-8");
      // Print Header
      resultOutput.myPrint(cout, args[0]);
      // loop ArraList 
      for (int i=0; i<list.size(); i++){
        // what number?
        int count = i + 1;
        cout.println("%% " + count + "番目の図形" );
        // using psPrint method of derive class
        list.get(i).psPrint(cout);
        total_area += list.get(i).area();
        total_length += list.get(i).perimeter();
      }
      cout.println( "showpage" );
      cout.println( "%%総面積 = " + total_area );
      cout.println( "%%総長 = " + total_length );
      cout.println( "%%図形総数 = " + n);
    }
    // various of error handling
    // 1. miss number input
    catch (NumberFormatException e){
      System.err.println("Please give a integer between 10 and 50");
      System.exit(1);
    }
    // 2. not found file
    catch (FileNotFoundException e){
      System.err.println("File not found: "+args[1]);
      System.exit(1);
    }
    // 3. encoding is not suppoted
    catch (UnsupportedEncodingException e){
      System.err.println("UTF-8 code is not supported");
      System.exit(1);
    }
    // 4. Unexpection
    catch (Exception e){
      System.err.println("Unexpected error");
      e.printStackTrace();
      System.exit(1);
    }
  }
}