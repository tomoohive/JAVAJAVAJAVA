// Main class
// Name : Tomoyasu Futaba
// ID : B183364
// Date : 2018/11/13
// Main Class of Drawing Ball with Animation

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
// Event Listener from Keyboard
import java.awt.event.KeyListener;
// Event from Keyboard
import java.awt.event.KeyEvent;
// Date Format for Header
import java.text.Format;
import java.text.DateFormat;
import java.util.Date;

// Main class
public class Kadai6 extends JFrame implements Runnable, KeyListener {

  int sleepTime = 30;    // sleep time (ms)
  int qtyBall;           // number of Balls
  Ball [] balls = null;      // Ball Data
  // Data for off screen image
  Thread thread = null;     // for thread data
  Thread saveThread = null;  // for saving thread data
  Image offScreenImage = null;  // foreground offscreen data
  Image backGroundImage = null; // background offscreen data
  Graphics backG = null;    // background graphics
  Graphics offG = null;     // foreground graphics
  Graphics saveOffG = null; // save data of foreground graphics
  Graphics2D offG2 = null;  // foreground graphics 2D
  private int width;  // width of window
  private int height; // height of window
  // background color
  java.awt.Color backColor = new java.awt.Color(1.0f, 1.0f, 1.0f);
  float R = 1.0f, G = 0.0f, B = 0.0f;
  // Ball Color is RED
  java.awt.Color ballColor = new java.awt.Color(R,G,B);
  // Ball Color 2
  java.awt.Color ballColor2 = new java.awt.Color(
    Math.max((int)(1.25*R),255),
    Math.max((int)(1.25*G),255),
    Math.max((int)(1.25*B),255));

  public Kadai6(String name) {
    super(name);
  }

  public void quit() {
    System.exit(0);
  }

  // Event when press the key
  public void keyPressed(KeyEvent e) {
    // VK_ESCAPE is esc key
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE) quit();
    else if (e.getKeyCode() == KeyEvent.VK_S){
      stop();   // S key
    }
    else if (e.getKeyCode() == KeyEvent.VK_R) {
      resume(); // R key
    }
  }

  public void keyReleased(KeyEvent e) {} // key is released
  public void keyTyped(KeyEvent e) {}    // key is typed

  // generate Balls by qtyBall
  public void init(int qtyBall) {
    // get the window size
    Dimension dimension = getSize();
    width = dimension.width;    // set width
    height = dimension.height;  // set height
    // data dimension of offScreen (foregraound)
    // generate dimension of foreground offScreen 
    offScreenImage = createImage(width,height);
    // get drawing Object for foreground offScreen
    offG = saveOffG = offScreenImage.getGraphics();
    offG2 = (Graphics2D) offG;
    // data dimension of offScreen (background)
    // generate dimension of background offScreen
    backGroundImage = createImage(width,height);
    // get drawing Object for background offscreen
    backG = backGroundImage.getGraphics();
    // drawing background with white
    backG.setColor(backColor);
    backG.fillRect(0,0,width,height);
    // Setting Keyboard Listener
    addKeyListener(this);
    // generate Ball class array
    balls = new Ball[qtyBall];
    for(int i=0; i<qtyBall; i++) {
      java.awt.Rectangle bounds = getBounds();
      balls[i] = new Ball(i);
      balls[i].adjustBallPosition(bounds);
    }
  }

  public void paint(Graphics g) {
    // do nothing when not get ready canvas
    if(offG == null || backGroundImage == null) return;
    // drawing background offscreen
    offG.drawImage(backGroundImage,0,0,this);
    Point2D center; // center of ball
    // 0.15(color2), 0.8(centerColor) complement[0.15-0.8]
    float [] dist = {0.15f, 0.8f};
    // 2 colors for complement
    Color [] colors = new Color[2];
    RadialGradientPaint rgp;

    // painting balls
    for(int i=0; i<qtyBall; i++) {
      ballColor = new java.awt.Color(balls[i].R, balls[i].G, balls[i].B);
      center = new Point2D.Double(balls[i].x, balls[i].y);

      colors[0] = ballColor2;
      colors[1] = ballColor;

      rgp = new RadialGradientPaint(center, (float)balls[i].r, dist, colors);
      // setting ball's color
      offG2.setPaint(rgp);
      offG.fillOval((int)(balls[i].x - balls[i].r),
                    (int)(balls[i].y - balls[i].r),
                    (int)(balls[i].r*2),
                    (int)(balls[i].r*2)); 
    }
    g.drawImage(offScreenImage, 0, 0, this);
  }
  // change position of balls
  public void animate() {
    if(thread != null) {
      // moving circle
      for(int i=0; i<qtyBall; i++) {
        balls[i].move(width, height);
      }
      // call paint method (using repaint() indirectly)
      repaint();
    }
  }

  /**
  * This method is from the Runnable interface.  It is the body of the
  * thread that performs the animation.  The thread itself is created
  * and started in the start() method.
  **/
  public void run() {
    while (true){
    animate();  // reload position
    try {
      Thread.sleep(sleepTime); // wait
    }
    catch (InterruptedException e){} //nothing
    }
  }

  /** Start animating when the browser starts the applet */
  public void start() {
    if (thread == null){
      thread = saveThread = new Thread(this); // generate thread
      thread.start(); // start thread
    }
  }

   /** Stop animating when the browser stops the applet */
  public void stop() {
    if (thread != null){
      offG = null; // stop paint using paint function
      thread = null;
    }
  }

  public void resume(){  // resume thread
    if (offG == null && thread == null){
      // back to offScreen Graphics was saved
      offG = saveOffG;
      // back to Thread was saved
      thread = saveThread;
    }
  }

  // Kadai6 main function
  public static void main(String[] args) {
    if(args.length!=1){
      System.out.println("make 1 argument");
      System.exit(0);
    }
    else if(Integer.parseInt(args[0])<10||Integer.parseInt(args[0])>30){
      System.out.println("argument 10 from 30");
      System.exit(0);
    }
    Date now = new Date(); // generate Object of data class
    // format date with japanese format
    Format fmt=DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
    Kadai6 kadai6 = new Kadai6("ボールぴょんぴょん 二葉知泰：B183364 日付 "+fmt.format(now));
    kadai6.setSize(600, 600);  // set display size
    kadai6.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    kadai6.setVisible(true);
    kadai6.qtyBall=Integer.parseInt(args[0]); // set the ball number
    kadai6.init(kadai6.qtyBall); // Initialize
    kadai6.start();
  }
}
