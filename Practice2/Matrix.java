import java.util.*;
import java.io.*;
import java.text.Format;
import java.text.DateFormat;

public class Matrix{
  private int row;
  private int col;
  private double[][] m;

  public String[] myName = {
    "******************************",
    "作成者：二葉知泰：183364",
    "日付：",
    "入力ファイル名１：",
    "入力ファイル名２：",
    "ファイルから行列を読み込み行列クラスの積計算",
    "******************************"
  };

  public void myPrint(String nameA, String nameB){
    Date now = new Date();
    Format fmt= DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
    for (int i = 0 ; i < myName.length ; i++){
      System.out.print(myName[i]);
      switch (i) {
        case 2: System.out.print(fmt.format(now)); break;/* 日付 */
        case 3: System.out.print(nameA); break;/* データファイル名 */
        case 4: System.out.print(nameB); break;
      }
      System.out.print('\n');
    }
  }

  public Matrix(int row, int col){
    this.row = row;
    this.col = col;
    m = new double[row][col];
  }

  public Matrix(double[][] m){
    row = m.length;
    col = m[0].length;
    this.m = new double[row][col];
    for(int i=0; i<row; i++)
      for(int j=0; j<col; j++)
        this.m[i][j] = m[i][j];
  }

  private Matrix(Matrix A){this(A.m);}

  public Matrix multiply(Matrix B){
    Matrix A = this;
    if(A.col != B.row) throw new RuntimeException("illegal matrix dimensions.");
    Matrix C = new Matrix(A.row, B.col);
    for(int i=0; i<C.row; i++)
      for(int j=0; j<C.col; j++)
        for(int k=0; k<A.col; k++)
          C.m[i][j] += (A.m[i][k] * B.m[k][j]);
    return C;
  }

  public static Matrix read(String filename){
    double[][] m;
    List<String> temp = new ArrayList<String>();
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
      br.close();
      return new Matrix(m);
    }catch (IOException e){
      e.printStackTrace();
      return new Matrix(0,0);
    }
  }

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
    if(args.length > 0){
      String filenameA = args[0];
      String filenameB = args[1];

      Matrix A = Matrix.read(filenameA);
      Matrix B = Matrix.read(filenameB);

      A.myPrint(filenameA, filenameB);
      System.out.printf("入力行列A, サイズ:(%d,%d)\n", A.row, A.col);
      A.print();

      System.out.printf("入力行列B, サイズ:(%d,%d)\n", B.row, B.col);
      B.print();

      System.out.printf("出力の積行列 C=A×B, サイズ:(%d,%d)\n", A.row, B.col);
      A.multiply(B).print();
      System.out.println();
    } else {
      System.out.println("ファイル名を入力してください");
    }
  }
}
