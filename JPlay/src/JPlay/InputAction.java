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

class InputAction extends InputBase
{
            private static final int stateReleased = 0;
            private static final int statePressed = 1;
            private static final int stateWaitingForRelease = 2;

            private int behavior;
            private int quantity;
            private int state;

            //Create a new InputAction with specific behavior
            public InputAction(int behavior)
            {
                    this.behavior = behavior;
                    state = stateReleased;
                    quantity = 0;
            }

            //Set the bebavior of the key or the button
            public synchronized void setBehavior(int behavior)
            {
                this.behavior = behavior;
            }

            //Take the state of the button like pressed
            public synchronized void press()
            {
                    press(1);
            }
            
            //Take the state of the button like pressed
            public synchronized  void press(int amount)
            {
                    if (state != stateWaitingForRelease)
                    {
                        this.quantity += amount;
                        state = statePressed;
                    }
            }

            //Release the button
            public synchronized void release()
            {
                    state = stateReleased;
            }

            //Rerturn if the button of the mouse or the keyboard is pressed
            public synchronized boolean isPressed() {
                    return (getAmount() != 0);
            }


            //Return a quantity of clicks for the mouse 
            //and for the keyboard the quantity of times button have been pressed.
            //If the behavior is DETECT_INITAL_PRESS_ONLY
            //this method wil return only the initial click.
            //For return that the mouse clicked again the user needs release
            //the button.
            public synchronized int getAmount()
            {
                    int quant = quantity;
                    if (quant != 0)
                    {
                            if (state == stateReleased)
                            {
                                quantity = 0;
                            }
                            else if (behavior == DETECT_INITIAL_PRESS_ONLY)
                            {
                                state = stateWaitingForRelease;
                                quantity = 0;
                            }
                    }
                    return quant;
            }
}
