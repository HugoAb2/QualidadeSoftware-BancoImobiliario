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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time
{
    javax.swing.Timer timer;
    Font font;
    Color color;
    int currentSecond;
    int hour;
    int minute;
    int second;
    int x;
    int y;
    boolean crescentTime;

    public Time(int x, int y, boolean crescentTime)
    {
            this(0, 0, 0, x, y, crescentTime);
    }

    public Time( int hour, int minute, int second, int x, int y, boolean crescentTime )
    {
            this(hour, minute, second, x, y, new Font("Arial",Font.TRUETYPE_FONT, 20),Color.YELLOW, crescentTime );
    }

    public Time( int hour, int minute, int second, int x, int y, Font font, Color color, boolean crescentTime )
    {
            this.x = x;
            this.y = y;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.color = color;
            this.font = font;
            this.crescentTime = crescentTime;
            this.crescentTime = crescentTime;
            calculateSeconds();
            createAction();
    }   

    private void createAction()
    {
                ActionListener action = new ActionListener()
                {
                            public void actionPerformed(ActionEvent e)
                            {
                                    if (currentSecond != 0 && (crescentTime == false) )
                                        currentSecond--;
                                    else
                                    {
                                        if (crescentTime)
                                            currentSecond++;
                                    }
                                    hour = currentSecond / 3600;
                                    minute = (currentSecond - hour * 3600) / 60;
                                    second = currentSecond - hour * 3600 - minute * 60;
                            }
                    };
                    this.timer = new javax.swing.Timer(1000, action);
                    this.timer.start();
    }

    @Override
    public String toString()
    {
            String str = "";

            if (hour < 10) 
                str = "0" + Integer.toString(hour) + ":";
            else 
                str = Integer.toString(hour) + ":";
            

            if (minute < 10) 
                str += "0" + Integer.toString(minute) + ":";
            else 
                str += Integer.toString(minute) + ":";
            

            if (second < 10) 
                str += "0" + Integer.toString(second);
            else 
                 str += Integer.toString(second);
            

            return str;
    }

    public void draw(String string)
    {
            Window.instance.drawText(string + toString(), x, y, color, font);
    }

    public void draw()
    {
            Window.instance.drawText(toString(), x, y, color, font);
    }

    public void setColor(Color color)
    {
            this.color = color;
    }

    public void setFont(Font font)
    {
            this.font = font;
    }

    private void calculateSeconds()
    {
        currentSecond = hour * 3600 + minute * 60 + second;
    }

    public boolean timeEnded()
    {
        return (currentSecond == 0);
    }

    public void setHour( int hour )
    {
        this.hour = hour;
        calculateSeconds();
    }

    public void setMinute( int minute )
    {
        this.minute = minute;
        calculateSeconds();
    }

    public void setSecond( int second )
    {
        this.second = second;
        calculateSeconds();
    }

    public long getHour()
    {
        return this.hour;
    }

    public long getMinute()
    {
        return this.minute;
    }

     public long getSecond()
    {
        return this.second;
    }

    public long getTotalSecond()
    {
        return this.currentSecond;
    }

    public void setTime(int hour, int minute, int seconds)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = seconds;
        calculateSeconds();
    }
}
