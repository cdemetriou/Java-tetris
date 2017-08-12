import java.awt.*;

/**
 * Created by Christos on 11/29/2014.
**/

//This is a single block class
//Defining color and movement of one block
public class SingleBlock
{
    private final int rows = 20;
    private final int columns = 10;
    Color color;
    int position_x;
    int position_y;
    //variables for block outline
    int size = 20;

    public SingleBlock(int color, int x, int y)
    {
        position_x = x;
        position_y = y;

        switch(color)
        {
            case 0:
                this.color = Color.black;
                break;

            case 1:
                this.color = Color.green;
                break;

            case 2:
                this.color = Color.blue;
                break;

            case 3:
                this.color = Color.red;
                break;

            case 4:
                this.color = Color.yellow;
                break;

            case 5:
                this.color = Color.magenta;
                break;

            case 6:
                this.color = Color.pink;
                break;

            case 7:
                this.color = Color.cyan;
        }

    }

    //move right method
    public void  moveRight()
    {
        position_x++;
    }

    //move left method
    public void  moveLeft()
    {
        position_x--;
    }

    //method for moving down the shape
    public void goDown() {position_y++;}

    public void drop(PositionMatrix matr)
    {
        while (checkNextDownPosition( matr))
        {
            goDown();
        }
    }

    public void move(int x, int y)
    {
        position_x = position_x + x;
        position_y = position_y + y;
    }

    //get x-axis method
    public int getXpos()
    {
        return position_x;
    }

    //get y-axis method
    public int getYpos()
    {
        return position_y;
    }

    //get color method
    public Color getColor()
    {
        return this.color;
    }

    public int getColorCode(Color col)
    {
        int res = 0;
        if (col.toString().equals("black"))
            res = 0;

        if (col.toString().equals("green"))
            res = 1;

        if (col.toString().equals("blue"))
            res = 2;

        if (col.toString().equals("red"))
            res = 3;

        if (col.toString().equals("yellow"))
            res = 4;

        if (col.toString().equals("magenta"))
            res = 5;

        if (col.toString().equals("pink"))
            res = 6;

        if (col.toString().equals("cyan"))
            res = 7;
     return res;
    }

    //Methods for checking position before moving block
    public boolean checkNextDownPosition(PositionMatrix matr)
    {
       return (position_y < rows -1 && (!matr.isPositionFree(position_x,position_y+1)));
    }

    public boolean checkNextLeftPosition(PositionMatrix matr)
    {
        return (position_x > 0 && (!matr.isPositionFree(position_x-1,position_y)));
    }

    public boolean checkNextRightPosition(PositionMatrix matr)
    {
        return (position_x < columns - 1 && (!matr.isPositionFree(position_x+1,position_y)));
    }

    public boolean checkPos(PositionMatrix matr)
    {
        return (position_y < rows && position_y >= 0 && position_x >= 0 && position_x < columns && !(matr.isPositionFree(position_x, position_y)));
    }

    public boolean checkPosForRotation(PositionMatrix matr)
    {
        return (position_y < rows && position_x >= 0 && position_x < columns && !(matr.isPositionFree(position_x, position_y)));
    }

    //draw shapes
    public void draw(Graphics  g)
    {
        g.setColor(color);
        g.fillRect(position_x*size + 3, position_y*size + 3, size -5 ,size -5);}
    }
