import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * Created by Christos on 11/29/2014.
 */
public class Tetris extends JApplet implements Runnable, KeyEventDispatcher, MouseListener {

    //Declaration for Shape elements
    private Shape cur_shape;
    private Shape nxt_shape;

    private PositionMatrix pos;
    private int score;

    private int move_delay = 600; //in ms
    private int new_block_Delay = 100;
    private boolean isGameOver;

    private Thread thr;

    //Initialisation of thread, listeners, components, size and tittle
    public void init()
    {
        isGameOver = false;
        thr = new Thread(this);
        initializeComponents();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
        addMouseListener(this);
        this.setSize(400,410);
    }

    // For leaving the browser.
    public void destroy () {}

    //Initialize current and next shape as well as their position and paints them
    public void initializeComponents()
    {
        pos = new PositionMatrix();
        score = 0;

        //Initialize shapes
        cur_shape = newShape();
        nxt_shape = newShape();

        nxt_shape.move(10,10);
        repaint();
    }

    //Start the thread
    public void start()
    {
        thr.start();
    }

    //Run method of the thread
    public void run()
    {
        //Initialize the game
        while (true)
        {
            repaint();
            //try and sleep the thread to simulate a delay in moving the elements
            try
            {
                Thread.sleep(move_delay);
            }
            catch(InterruptedException e){}

            if(!isGameOver) {

                if (cur_shape.checkNextDownPosition(pos))
                {
                    cur_shape.goDown();
                }
                else if (cur_shape.checkPosition(pos))
                {
                    //For shape, we add all blocks to matrix
                    score += pos.addShape(cur_shape) * 10;

                    //get the next shape
                    cur_shape = nxt_shape;
                    //hide the current shape
                    cur_shape.move(-10, -10);
                    //create a new next shape and add it to side for player to see
                    nxt_shape = newShape();
                    nxt_shape.move(10, 10);
                    try
                    {
                        Thread.sleep(new_block_Delay);
                    }
                    catch (InterruptedException e) {}

                }
                //if out of bounds then game is over
                else {
                    isGameOver = true;
                }
            }
            repaint();
        }
    }

    //Create new shape from ShapeFactory Class
    private Shape newShape()
    {
        Shape new_shape= new Shape();
        return new_shape;
    }

    public void paint (Graphics g)
    {
        //paints a 10 * 20 squares
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor(Color.white);
                g.fill3DRect(i * 20, j * 20,
                        20, 20, true);
            }
        }
        //If is a refresh of GUI
        //draw all elements
        if (!isGameOver) {
            cur_shape.draw(g);
            pos.draw(g);
            g.setColor(Color.BLACK);
            g.drawString("Next Shape",265,175);
            nxt_shape.draw(g);
        }
        //else show game over and score
        else
        {
            pos.draw(g);
            g.setColor(Color.BLACK);
            g.drawString("Game is over",260,100);
            g.drawString("Total Score " + score, 260, 70);
        }
    }

    //Actions performed when one of the three mouse buttons is pressed
    public void mouseClicked (MouseEvent e)
    {
        //key listener for move left
        if (SwingUtilities.isLeftMouseButton(e))
        {
            if (cur_shape.checkNextLeftPosition(pos))
                cur_shape.moveLeft(pos);
            repaint();
        }

        //key listener for move right
        if (SwingUtilities.isRightMouseButton(e))
        {
            if (cur_shape.checkNextRightPosition(pos))
                cur_shape.moveRight(pos);
            repaint();
        }

        //key listener for rotation
        if (SwingUtilities.isMiddleMouseButton(e))
        {
            cur_shape.rotate(pos);
            repaint();
        }
    }

    //Actions performed when the space is pressed
    public boolean dispatchKeyEvent(java.awt.event.KeyEvent evt)
    {
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_SPACE)
        {
            cur_shape.drop(pos);
            repaint();
        }
        return true;
    }

    //Empty overriden methods for Mouse Listener
    public void mouseExited(MouseEvent e) { }
    public void mousePressed( MouseEvent e ) { }
    public void mouseReleased( MouseEvent e ) { }
    public void mouseEntered(MouseEvent e) { }
}
