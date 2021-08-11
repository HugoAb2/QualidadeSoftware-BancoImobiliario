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

public class GameImage extends GameObject
{
    protected Image image;

    public GameImage(String fileName)
    {
            loadImage(fileName);
    }

    public void loadImage(String fileName)
    {
            ImageIcon icon = new ImageIcon(fileName);
            this.image = icon.getImage();
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
    }
    
    public void draw()
    {
            Window.instance.getGameGraphics().drawImage(image, (int)x, (int)y, width, height, null);
    }
   
}
