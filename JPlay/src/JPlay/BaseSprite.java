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

class BaseSprite extends Animation
{
    double velocityY     = 1;
    double jumpVelocity  = 5.3;//It's used for the jump,

    double gravity     = 0.098;
    boolean onFloor    = false;
    int floor;

    public static char STOP = 'S';
    public static char LEFT = 'L';
    public static char RIGHT = 'R';
    public static char UPWARD = 'U';
    public static char DOWNWARD = 'D';

    char stateX;
    char stateY;

    protected static Keyboard keyboard = Window.instance.getKeyboard();

    public BaseSprite(String fileName)
    {
            this(fileName, 1);
    }

    public BaseSprite(String fileName, int numFrames)
    {
            super(fileName, numFrames);
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
}
