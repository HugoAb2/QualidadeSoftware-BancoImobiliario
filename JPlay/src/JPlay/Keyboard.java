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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

public class Keyboard extends InputBase implements KeyListener
{
    public static final int UP_KEY = 38;
    public static final int LEFT_KEY = 37;
    public static final int RIGHT_KEY = 39;
    public static final int DOWN_KEY = 40;
    public static final int ESCAPE_KEY = 27;
    public static final int SPACE_KEY = 32;
    public static final int ENTER_KEY = 10;    

    private Hashtable keysPressed;

    public Keyboard()
    {
            keysPressed = new Hashtable();

            //Add the defaults keys 
            addKey(UP_KEY,     Keyboard.DETECT_EVERY_PRESS);
            addKey(LEFT_KEY,   Keyboard.DETECT_EVERY_PRESS);
            addKey(RIGHT_KEY,  Keyboard.DETECT_EVERY_PRESS);
            addKey(DOWN_KEY,   Keyboard.DETECT_EVERY_PRESS);
            addKey(ESCAPE_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
            addKey(SPACE_KEY,  Keyboard.DETECT_INITIAL_PRESS_ONLY);
            addKey(ENTER_KEY,  Keyboard.DETECT_INITIAL_PRESS_ONLY);
    }

    //Return if the specific key is pressed
    //The key have to be passed for parameter
    public boolean keyDown(int key)
    {
            if ( keysPressed.containsKey(key) )
            {
                InputAction temp = (InputAction) keysPressed.get(key);
                return temp.isPressed();
            }
            return false;
    }


    //Add a key and its behavior is DETECT_INITAL_PRESS_ONLY
    public void addKey(int key)
    {
            addKey(key, Keyboard.DETECT_INITIAL_PRESS_ONLY);
    }

    //Add a key and its behavior
    public void addKey(int key, int behavior)
    {
            removeKey(key);
            keysPressed.put(key, new InputAction(behavior));            
    }

    //Remove a key from the keyboard
    public void removeKey(int key)
    {
            keysPressed.remove(key);
    }

    //Set a new behavior for a key
    public void setBehavior(int key, int behavior)
    {
            if (keysPressed.containsKey(key))            
                addKey(key, behavior);            
    }

    public void keyTyped(KeyEvent e)
    {
            //e.consume();
    }

    public void keyPressed(KeyEvent e)
    {
            int key = e.getKeyCode();
            if ( keysPressed.containsKey(key) )
            {
                InputAction temp = (InputAction) keysPressed.get(key);
                temp.press();
            }
            //e.consume();
    }

    public void keyReleased(KeyEvent e)
    {
            int key = e.getKeyCode();
            if ( keysPressed.containsKey(key) )
            {
                InputAction temp = (InputAction) keysPressed.get(key);
                temp.release();
            }
            //e.consume();
    }
   
}
