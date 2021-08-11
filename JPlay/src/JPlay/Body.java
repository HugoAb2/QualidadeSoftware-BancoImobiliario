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

public class Body extends BaseSprite
{        
    private double deceleration  = 0.015;
    private double maxVelocityX  = 2.5;
    private double acceleration  = 0.4;
    private double velocityX     = 0;
    private double maxVelocityY  = 2.5;

    public Body(String fileName)
    {
            this(fileName, 1);
    }

    public Body(String fileName, int numFrames)
    {
            super(fileName, numFrames);
            super.velocityY = 0;              
    }

    public void moveX()
    {
            moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY);
    }

    public void moveX(int leftKey, int rightKey)
    {
            if ( this.x < 1 || this.x + this.width > Window.instance.getWidth() )
            {
                    velocityX = 0;
                    stateX = STOP;
            }

            if (keyboard.keyDown(leftKey) && this.x > 1 )
            {
                    accelerateToLeft();
                    stateX = LEFT;
            }
            else
                if (keyboard.keyDown(rightKey) && this.x + this.width < Window.instance.getWidth())
                {
                        accelerateToRight();
                        stateX = RIGHT;
                }
                else
                {
                        if ( velocityX > deceleration)
                        {
                            velocityX -= deceleration;
                        }
                        else
                            if ( velocityX < -deceleration)
                            {
                                velocityX += deceleration;
                            }
                            else
                            {
                                velocityX = 0;
                                stateX = STOP;
                            }
                }
           
            this.x += velocityX;
    }

    public void walkX()
    {
            this.x += velocityX;
    }
    
    public void accelerateToLeft()
    {        
            if (velocityX > -maxVelocityX) velocityX -= acceleration;
    }

    public void accelerateToRight()
    {
            if (velocityX < maxVelocityX) velocityX += acceleration;
    }

    public void decelerateX()
    {
            if ( velocityX > deceleration)
            {
                velocityX -= deceleration;
            }
            else
            {
                    if ( velocityX < -deceleration)
                    {
                        velocityX += deceleration;
                    }
                    else
                    {
                        velocityX = 0;
                    }
            }
    }

    public void moveY()
    {
            moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY);
    }
    
    public void moveY(int upKey, int downKey)
    {
            if ( this.y < 1 || (this.y + this.height) > Window.instance.getHeight() )
            {
                velocityY = 0;
                stateY = STOP;
            }

            if (keyboard.keyDown(upKey) && this.y >= 1)
            {
                if (velocityY > -maxVelocityY) velocityY -= acceleration;
                stateY = UPWARD;
            }
            else
                if (keyboard.keyDown(downKey) && this.y + this.height <= Window.instance.getHeight())
                {
                    if (velocityY < maxVelocityY) velocityY += acceleration;
                    stateY = DOWNWARD;
                }
                else
                {
                    if (velocityY > deceleration)
                    {
                        velocityY -= deceleration;
                    }
                    else
                        if (velocityY < -deceleration)
                        {
                            velocityY += deceleration;
                        }
                        else
                        {
                            velocityY = 0;
                            stateY = STOP;
                        }
                }
            this.y += velocityY;
    }

    public void walkY()
    {
            this.y += velocityY;
    }

    public void acelerateToUp()
    {
            if (velocityY > -maxVelocityY) velocityY -= acceleration;
    }

    public void acelerateToDown()
    {
            if (velocityY < maxVelocityY) velocityY += acceleration;
    }

    public void decelerateY()
    {
            if (velocityY > deceleration)
            {
                velocityY -= deceleration;
            }
            else
            {
                    if (velocityY < -deceleration)
                    {
                        velocityY += deceleration;
                    }
                    else
                    {
                        velocityY = 0;
                    }
            }
    }

    public void setAcceleration(double acceleration)
    {
            this.acceleration = acceleration;
    }

    public double getAcceleration()
    {
            return acceleration;
    }

    public void setDeceleration(double deceleration)
    {
            this.deceleration = deceleration;
    }

    public double getDeceleration()
    {
            return deceleration;
    }

    public void setMaxVelocityX(double maxVelocity)
    {
            this.maxVelocityX = maxVelocity;
    }

    public double getMaxVelocityX()
    {
            return maxVelocityX;
    }

    public void setMaxVelocityY(double maxVelocity)
    {
            this.maxVelocityY = maxVelocity;
    }

    public double getMaxVelocityY()
    {
            return maxVelocityY;
    }
}
