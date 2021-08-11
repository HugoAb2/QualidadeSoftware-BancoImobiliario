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

public class Collision
{
        //Retorna true se duas áreas estão em contato uma com a outra não levando em consideração
        //possíveis transparências existentes nelas, logo, toda a dimensão das áreas são usadas
        public static boolean collided( Point min1, Point max1, Point min2, Point max2 )
        {            
                if( min1.x >= max2.x || max1.x <= min2.x)
                    return false;
                if( min1.y >= max2.y || max1.y <= min2.y)
                    return false;
                return true;
        }

        //Retorna true se dois objetos colidiram, o método não leva em consideração a transparência existente ou
        //não no objeto parametrizado, logo, toda a dimensão do objeto é usada.
        public static boolean collided( GameObject obj1, GameObject obj2 )
        {            
                Point minObj1 = new Point ( obj1.x, obj1.y ) ;
                Point maxObj1 = new Point ( (obj1.x + obj1.width), (int) (obj1.y + obj1.height) );

                Point minObj2 = new Point ( (int)obj2.x, (int)obj2.y ) ;
                Point maxObj2 = new Point ( (int)(obj2.x + obj2.width), (int) (obj2.y + obj2.height) );

                return( Collision.collided(minObj1, maxObj1, minObj2, maxObj2) );
        }
        
}
