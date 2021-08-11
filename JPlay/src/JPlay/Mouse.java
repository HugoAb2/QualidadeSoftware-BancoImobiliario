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
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse extends InputBase implements MouseMotionListener, MouseListener {

    private Point   mousePosition;

    private InputAction leftButton;
    private InputAction middleButton;
    private InputAction rightButton;

    public static final int BUTTON_LEFT = 1;
    public static final int BUTTON_MIDDLE = 2;
    public static final int BUTTON_RIGHT = 3;

    public Mouse()
    {
            mousePosition = new Point(0, 0);

            //Add the default buttons
            leftButton   = new InputAction(InputBase.DETECT_INITIAL_PRESS_ONLY);
            middleButton = new InputAction(InputBase.DETECT_INITIAL_PRESS_ONLY);
            rightButton  = new InputAction(InputBase.DETECT_INITIAL_PRESS_ONLY);
    }

    //Get the position of the mouse
    public Point getPosition()
    {
            return (Point) mousePosition.clone();
    }

    public boolean isLeftButtonPressed()
    {
            return leftButton.isPressed();
    }

    public boolean isMiddleButtonPressed()
    {
            return middleButton.isPressed();
    }

    public boolean isRightButtonPressed()
    {
            return rightButton.isPressed();
    }

    public void setBehavior(int numberBotton, int behavior)
    {
            switch(numberBotton)
            {
                case BUTTON_LEFT:   leftButton.setBehavior(behavior);   break;
                case BUTTON_MIDDLE: middleButton.setBehavior(behavior); break;
                case BUTTON_RIGHT:  rightButton.setBehavior(behavior);  break;
            }
    }

    public void mouseClicked(MouseEvent e)
    {         
    }

    public void mousePressed(MouseEvent e)
    {
            switch (e.getButton())
            {
                case MouseEvent.BUTTON1: leftButton.press();   break;
                case MouseEvent.BUTTON2: middleButton.press(); break;
                case MouseEvent.BUTTON3: rightButton.press();  break;
            }
    }

    public void mouseReleased(MouseEvent e)
    {
            switch (e.getButton())
            {
                case MouseEvent.BUTTON1: leftButton.release();   break;
                case MouseEvent.BUTTON2: middleButton.release(); break;
                case MouseEvent.BUTTON3: rightButton.release();  break;
            }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) 
    {
            mousePosition = e.getPoint();
    }

    //Return if the mouse is over a object
    public boolean isOverObject(GameObject obj)
    {
            Point min = new Point( (int)obj.x, (int)obj.y);
            Point max = new Point( (int)(obj.x + obj.width), (int)(obj.y + obj.height));
            return isOverArea(min, max);
    }

    /*
    //Return if the mouse is over a area
    public boolean isOverArea( Point start, Point end )
    {
            //Verifica se está fora dos limites no eixo X para o mouse
            if ((mousePosition.x < start.x) || (mousePosition.x > end.x))
                return false;

            //Verifica se está fora dos limites no eixo Y para o mouse
            if ((mousePosition.y < start.y) || (mousePosition.y > end.y))
                return false;

            //Os outros testes falharam, então, o mouse está sobre a área do botão
            return true;
    }*/

    public boolean isOverArea( Point start, Point end )
    {
            return  isOverArea(start.x, start.y, end.x, end.y);
    }

    public boolean isOverArea( int minX, int minY, int maxX, int maxY )
    {
            //Verifica se está fora dos limites no eixo X para o mouse
            if ((mousePosition.x < minX) || (mousePosition.x > maxX))
                return false;

            //Verifica se está fora dos limites no eixo Y para o mouse
            if ((mousePosition.y < minY) || (mousePosition.y > maxY))
                return false;

            //Os outros testes falharam, então, o mouse está sobre a área do botão
            return true;
    }

}
