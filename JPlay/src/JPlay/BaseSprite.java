/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Universidade Federal Fluminense - UFF - Brasil - 2010
 * Ciência da Computação
 */

package JPlay;

import java.awt.Image;

import javax.swing.ImageIcon;

class BaseSprite
{
    double velocityY     = 1;
    double jumpVelocity  = 5.3; //It's used for the jump,

    double gravity     = 0.098;
    boolean onFloor    = false;
    int floor;

    public static char STOP = 'S';
    public static char LEFT = 'L';
    public static char RIGHT = 'R';
    public static char UPWARD = 'U';
    public static char DOWNWARD = 'D';
    
    private int initialFrame;
    private int finalFrame;
    private int currAnimFrame;
    private boolean animationFinished;
    public int width;
    public int height;
    protected Image image;
    public double x;
    public double y;

    private boolean repeatAnimation;

    private boolean canDraw;

    //Each frame has its own time
    private long timeEachFrame[];

    //It keeps the time when a frame was changed
    private long lastTime;

    char stateX;
    char stateY;

    protected static Keyboard keyboard = Window.instance.getKeyboard();

    public BaseSprite(String fileName)
    {
    		loadImage(fileName);
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
           
            this.canDraw = true;
            this.repeatAnimation = true;
            this.finalFrame = 1;
            this.initialFrame = 0;
            this.currAnimFrame = 0;
            timeEachFrame = new long[1];
            setTimeChangeFrame(70);
            lastTime = System.currentTimeMillis();
            this.animationFinished = false;
    }

    public void setFloor(int floor)
    {
            this.floor = floor;
    }

    public int getFloor()
    {
            return floor;
    }
    
    public void jump(int jumpKey)
    {
            if (keyboard.keyDown(jumpKey) && onFloor == true)
            {
                onFloor = false;
                velocityY = -jumpVelocity;
            }

            velocityY += gravity;
            this.y += velocityY;

            if ( this.y + this.height - floor > 0.0001 )
            {
                velocityY = 0;
                this.y = floor - this.height;
                onFloor = true;
            }
    }
    
    public void jump()
    {
            jump(Keyboard.SPACE_KEY);
    }

    public boolean isJumping()
    {
            return onFloor;
    }

    public void fall()
    {
            if ( Math.abs(this.y + this.height - floor) < 1 )
            {
                velocityY = 0;
                this.y = floor - this.height;
            }
            else
                velocityY += gravity;
            
            this.y += velocityY;
    }

    public boolean isOnFloor()
    {
             if (this.y + this.height < floor)
                 return false;
             return true;
    }

    public void setJumpVelocity(double velocity)
    {
            this.jumpVelocity = velocity;
    }

    public double getJumpVelocity()
    {
            return this.jumpVelocity;
    }

    public double getGravity()
    {
            return this.gravity;
    }

    public void setGravity(double gravity)
    {
            this.gravity = gravity;
    } 

    public boolean collided(GameObject obj)
    {
            return Collision.collided(this, obj);
    }

    public char getStateOfX()
    {
            return stateX;
    }

    public void setStateOfX(char state)
    {
            this.stateX = state;
    }

    public char getStateOfY()
    {
            return stateY;
    }

    public void setStateOfY(char state)
    {
            this.stateY = state;
    }
    
    public void setTimeChangeFrame(long timeChangeFrame)
    {
            for(int i=0; i < timeEachFrame.length; i++)
                timeEachFrame[i] = timeChangeFrame;
    }

    public void loadImage(String fileName)
    {
            ImageIcon icon = new ImageIcon(fileName);
            this.image = icon.getImage();
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
    }
}
