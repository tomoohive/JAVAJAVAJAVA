import java.io.*;
import java.util.*;

public class MySample{
  public static void main(String[] args){
    Super s = new Sub(100);
    System.out.println("Main: "+s.d);
    s.print();
  }
}
