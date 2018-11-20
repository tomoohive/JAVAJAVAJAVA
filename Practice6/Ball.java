// Ball class
// Name : Tomoyasu Futaba
// ID : B183364
// Date : 2018/11/13
// Ball Setting Class

import java.util.Random; //random number
import java.util.Date;   // Date

public class Ball {
  // radius (1 ~ 80)
  double r;
  // coordinate (0 ~ 400)
  double x,y;
  // direction vector (-40.0 ~ 40.0)
  double dx, dy;
  // previous position
  double lastx, lasty;
  // RGB color variables (0.0 ~ 1.0)
  float R,G,B;

  // constructor
  public Ball(int i) {
    // get the random value
    Date date = new Date();
    Random random = new Random(date.getTime() * i);

    // set each value within the range
    this.r = random.nextDouble()*80;
    this.x = random.nextDouble()*400;
    this.y = random.nextDouble()*400;
    // positive and negative random numbers
    this.dx = random.nextDouble()*80.0 - 40.0;
    this.dy = random.nextDouble()*80.0 - 40.0;
    // set the initialize values
    this.lastx = x;
    this.lasty = y;
    // set the random color
    this.R = random.nextFloat();
    this.G = random.nextFloat();
    this.B = random.nextFloat();
  }

  // change the position of ball
  public void move(int width, int height) {
    // judgement of frame
    if ((x - r + dx < 0 || x + r + dx > width))
      dx = -dx;
    if ((y - r + dy < 0 || y + r + dy > height))
      dy = -dy;
    // calculate the position
    x = lastx + dx;
    y = lasty + dy;
    // set the previous position
    lastx = x;
    lasty = y;
  }

  // prevent ball from sinking into the wall
  public void adjustBallPosition(java.awt.Rectangle bounds) {
    // adjust the position of the ball
    if((x-r) <= 0)
      x += r;
    if((x+r) >= bounds.width)
      x -= r;
    if((y-r) <= 0)
      y += r;
    if((y+r) >= bounds.height)
      y -= r;

    lastx = x;
    lasty = y;
  }
}