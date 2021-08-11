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
import java.awt.Dimension;

public class GameObject
{
        public double x;
        public double y;

        public int width;
        public int height;

        public GameObject()
        {
            this.x = 0;
            this.y = 0;
            this.width = 0;
            this.height = 0;
        }

        public Point getPosition()
        {
            return new Point(this.x, this.y);
        }

        public Dimension getDimension()
        {
            return new Dimension(this.width, this.height);
        }

        public void setPosition(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        public void setPosition(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public void setPosition(Point point)
        {
            this.x = point.x;
            this.y = point.y;
        }

        public void setPosition(java.awt.Point point)
        {
            this.x = point.x;
            this.y = point.y;
        }

        public void setDimension(int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        public void setDimension(Dimension dimension)
        {
            this.width = dimension.width;
            this.height = dimension.height;
        }
}
