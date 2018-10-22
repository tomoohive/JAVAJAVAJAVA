// general package
import java.util.*;
import java.io.*;

// to use for header 
import java.text.Format;
import java.text.DateFormat;

public class Matrix{
  private int row;  // number of rows
  private int col;  // number of colums
  private double[][] m;  // row-by-col array
  
  // Setting Header
  public String[] myName = {
    "******************************",
    "作成者：二葉知泰：183364",
    "日付：",
    "入力ファイル名１：",
    "入力ファイル名２：",
    "ファイルから行列を読み込み行列クラスの積計算",
    "******************************"
  };

  // Print Header
  public void myPrint(String nameA, String nameB){
    Date now = new Date();
    Format fmt= DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG); //To get Date
    for (int i = 0 ; i < myName.length ; i++){
      System.out.print(myName[i]);
      switch (i) {
        case 2: System.out.print(fmt.format(now)); break; //Date
        case 3: System.out.print(nameA); break; //FileNameA
        case 4: System.out.print(nameB); break; //FileNameB
      }
      System.out.print('\n');
    }
  }

  // create row-by-col matrix of 0's 
  public Matrix(int row, int col){
    this.row = row;
    this.col = col;
    m = new double[row][col];
  }

  // create matrix based on 2d array
  public Matrix(double[][] m){
    row = m.length;
    col = m[0].length;
    this.m = new double[row][col];
    for(int i=0; i<row; i++)
      for(int j=0; j<col; j++)
        this.m[i][j] = m[i][j];
  }

  // constructor
  private Matrix(Matrix A){this(A.m);}

  // calculate C = A * B
  public Matrix multiply(Matrix B){
    Matrix A = this;
    // return error
    if(A.col != B.row) throw new RuntimeException("illegal matrix dimensions.");
    Matrix C = new Matrix(A.row, B.col);
    for(int i=0; i<C.row; i++)
      for(int j=0; j<C.col; j++)
        for(int k=0; k<A.col; k++)
          C.m[i][j] += (A.m[i][k] * B.m[k][j]);
    return C;
  }

  // read matrix text file and create new Instance
  public static Matrix read(String filename){
    double[][] m;

    // temporary list
    List<String> temp = new ArrayList<String>();

    // Loading Reader and Store Matrix
    try(BufferedReader br = new BufferedReader(new FileReader(filename))){
      String line;
      while((line = br.readLine()) != null){
        temp.add(line);
      }
      String[] row_col = temp.get(0).split(" ");
      int row = Integer.parseInt(row_col[0]);
      int col = Integer.parseInt(row_col[1]);
      m = new double[row][col];
      for(int i=0; i<row; i++){
        String[] tmp_s = temp.get(i+1).split(" ");
        for(int j=0; j<col; j++){
          m[i][j] = Double.parseDouble(tmp_s[j]);
        }
      }
      // close Reader Buffer and return new Instance
      br.close();
      return new Matrix(m);

    // catch Error and return Zero matrix
    }catch (IOException e){
      e.printStackTrace();
      return new Matrix(0,0);
    }
  }

  // To show Instance on console
  public void print(){
    for(int i=0; i<row; i++){
      System.out.printf("|");
      for(int  j=0; j<col; j++){
        System.out.printf(" %9.6f",m[i][j]);
      }
      System.out.println(" |");
    }
  }

  public static void main(String[] args){
    // when files' name are input
    if(args.length > 0){
      // store filenames
      String filenameA = args[0];
      String filenameB = args[1];
      
      // create new matrix from filename
      Matrix A = Matrix.read(filenameA);
      Matrix B = Matrix.read(filenameB);
      
      // print header on console
      A.myPrint(filenameA, filenameB);

      // print matrixA
      System.out.printf("入力行列A, サイズ:(%d,%d)\n", A.row, A.col);
      A.print();

      // print matrixB
      System.out.printf("入力行列B, サイズ:(%d,%d)\n", B.row, B.col);
      B.print();
      
      // print matrix C = A * B
      System.out.printf("出力の積行列 C=A×B, サイズ:(%d,%d)\n", A.row, B.col);
      A.multiply(B).print();
      System.out.println();

    // when files name are not input
    } else {
      System.out.println("ファイル名を入力してください");
    }
  }
}
