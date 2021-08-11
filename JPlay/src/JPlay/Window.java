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
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Window extends JFrame
{
    private Mouse mouse;
    private Keyboard keyboard;
    private BufferStrategy buffer;
    private Graphics graphics;
    static  Window instance;//It's used in the packet of the JFunGames
    private long currTime;
    private long lastTime;

    public Window(int width, int height)
    {
            mouse = new Mouse();
            keyboard = new Keyboard();

            addMouseListener(mouse);
            addMouseMotionListener(mouse);
            addKeyListener(keyboard);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(width,height);
            setLocationRelativeTo(null);
            setUndecorated(true);
            setResizable(false);
            setVisible(true);

            createBufferStrategy(2);
            buffer = getBufferStrategy();

            instance = this;
            graphics = buffer.getDrawGraphics();
            currTime = System.currentTimeMillis();
            lastTime = 0;
    }

    public Keyboard getKeyboard()
    {
            return keyboard;
    }

    public Mouse getMouse()
    {
            return mouse;
    }

    public Graphics getGameGraphics()
    {                        
            return graphics;
    }

    public void display()
    {
            graphics.dispose();
            buffer.show();            
            Toolkit.getDefaultToolkit().sync();
            graphics = buffer.getDrawGraphics();
            lastTime = currTime;            
            currTime = System.currentTimeMillis();            
    }

    public void delay(long time)
    {
            try
            {
                Thread.sleep(time);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public long timeElapsed()
    {
            return currTime - lastTime;
    }

    public void drawText(String message, int x, int y, Color color)
    {
            graphics.setColor(color);
            graphics.drawString(message, x, y);
    }

    public void drawText(String message, int x, int y, Color color, Font font)
    {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setFont(font);
            g2.setColor(color);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString(message, x, y);
    }
    
    public void exit()
    {
            dispose();
            System.exit(0);
    }

    public Cursor createCustomCursor(String imageName)
    {
            Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
                                    Toolkit.getDefaultToolkit().getImage(imageName),
                                    new java.awt.Point(),
                                    "cursor");
            return cursor;
    }

    public void clear()
    {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, getWidth(), getHeight());
    }
}
