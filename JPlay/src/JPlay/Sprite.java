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

public class Sprite extends BaseSprite
{
    double velocityX = 1;
    double currVelociyX = velocityX;
    double currVelocityY = velocityY;

    public Sprite(String fileName)
    {
            this(fileName, 1);
    }

    public Sprite(String fileName, int numFrames)
    {
            super(fileName, numFrames);
    }

    public void moveX()
    {
            moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY);
    }
    
    public void moveX(int leftKey, int rightKey)
    {
            stateX = STOP;
            if (keyboard.keyDown(leftKey))
            {
                moveToLeft();
                stateX = LEFT;
            }

            if (keyboard.keyDown(rightKey))
            {
                moveToRight();
                stateX = RIGHT;
            }
    }

    public void moveToLeft()
    {
            if(this.x > 1)
            {
               this.x -= velocityX;

            }
    }

    public void moveToRight()
    {
            if (this.x + this.width < Window.instance.getWidth())
            {
                this.x += velocityX;
            }
    }

    public void moveY()
    {
        this.moveX(Keyboard.UP_KEY, Keyboard.DOWN_KEY);
    }
    
    public void moveY(int upKey, int downKey)
    {
            stateY = STOP;
            if (keyboard.keyDown(upKey))
            {
                stateY = UPWARD;
                moveToUp();
            }

            if (keyboard.keyDown(downKey))
            {
                moveToDown();
                stateY = DOWNWARD;
            }
    }

    public void moveToUp()
    {
            if(this.y > 1)
            {
                this.y -= velocityY;
            }
    }

    public void moveToDown()
    {
            if(this.y + this.height < Window.instance.getHeight())
            {
                this.y += velocityY;
            }
    }

    public void setVelocityX(double velocity)
    {
            this.velocityX = velocity;
    }

    public double getVelocityX()
    {
        return this.velocityX;
    }
              
    public void moveTo(double x, double y)
    {
            if (this.x < x && (this.x + this.width < Window.instance.getWidth()) )
            {
                this.x += velocityX;
            }
            else
            {
                if (this.x > x)
                    this.x -= velocityX;
            }

            if (this.y > y)
            {
                this.y -= velocityY;
            }
            else
            {
                if (this.y < y)
                    this.y += velocityY;
            }
    }

    public void setVelocityY(double velocity)
    {
            this.velocityY = velocity;
    }

    public double getVelocityY()
    {
            return this.velocityY;
    }


}
