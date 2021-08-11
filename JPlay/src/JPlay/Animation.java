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

public class Animation extends GameImage
{              
    private int initialFrame;
    private int finalFrame;
    private int currAnimFrame;
    private boolean animationFinished;

    private boolean repeatAnimation;

    private boolean canDraw;

    //Each frame has its own time
    private long timeEachFrame[];

    //It keeps the time when a frame was changed
    private long lastTime;

    public Animation(String fileName, int numFrames)
    {
            super(fileName);
            this.width = image.getWidth(null) / numFrames;
            this.height = image.getHeight(null);
           
            this.canDraw = true;
            this.repeatAnimation = true;
            this.finalFrame = numFrames;
            this.initialFrame = 0;
            this.currAnimFrame = 0;
            timeEachFrame = new long[numFrames];
            setTimeChangeFrame(70);
            lastTime = System.currentTimeMillis();
            this.animationFinished = false;
    }

    public Animation(String fileName)
    {
            this(fileName, 1);
    }

    public void setTimeOfFrame(int frame, long time)
    {
            timeEachFrame[frame] = time;
    }

    public long getTimeOfFrame(int frame)
    {
            return timeEachFrame[frame];
    }

    public void setRangeOfFrames(int initialFrame, int finalFrame)
    {
            this.initialFrame = initialFrame;
            this.currAnimFrame = initialFrame;
            this.finalFrame = finalFrame;
    }

    public void setRepeatAnimation(boolean value)
    {
            this.repeatAnimation = value;
    }

    public boolean getRepeatAnimation()
    {
            return repeatAnimation;
    }

    public void setTimeChangeFrame(long timeChangeFrame)
    {
            for(int i=0; i < timeEachFrame.length; i++)
                timeEachFrame[i] = timeChangeFrame;
    }

    public long getTimeChangeFrame()
    {
           return timeEachFrame[0];
    }

    public void runAnimation()
    {
            long time = System.currentTimeMillis();
            if ( time - lastTime > timeEachFrame[currAnimFrame] && finalFrame != 0)
            {
                currAnimFrame++;
                lastTime = time;
            }

            animationFinished = true;
            if (currAnimFrame == finalFrame && repeatAnimation)
            {
                currAnimFrame = initialFrame;
            }
            else
                if( (!repeatAnimation) && (currAnimFrame+1 >= finalFrame) )
                {
                    currAnimFrame = finalFrame - 1;
                }
                else
                    animationFinished = false;
        }
   
    public void reset()
    {
            this.currAnimFrame = initialFrame;
    }

    public void setInitialFrame(int frame)
    {
        this.initialFrame = frame;
    }

    public int getInitalFrame()
    {
        return initialFrame;
    }

    public void setFinalFrame(int frame)
    {
        this.finalFrame = frame;
    }

    public int getFinalFrame()
    {
        return finalFrame;
    }

    public void setCurrFrame(int frame)
    {
            currAnimFrame = frame;
    }

    public int getCurrFrame()
    {
            return currAnimFrame;
    }
    
    public boolean isAnimationFinished()
    {   
        return animationFinished;
            //return currAnimFrame == finalFrame;
    }

    public void hide()
    {
            this.canDraw = false;
    }

    public void unhide()
    {
            this.canDraw = true;
    }

    @Override
    public void draw()
    {
            if (canDraw)
            {
                Window.instance.getGameGraphics()
                    .drawImage(image, (int)x, (int)y, (int)x + width, (int)y + height,
                        currAnimFrame * width, 0, (currAnimFrame +1) * width, height, null);
            }
    }
}
