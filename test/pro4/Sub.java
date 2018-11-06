import java.io.*;
import java.util.*;

public class Sub extends Super{
  double d = 1.0;
  public Sub(int value){ d *= value; }
  public void print() { System.out.println("Sub: "+d);}
}
