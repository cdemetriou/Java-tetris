import java.awt.*;
import java.util.Random;

/*
 * Created by Christos on 11/29/2014.
 */
public class Shape {

    private SingleBlock[] shape;
    static String[] shapeList =
            {"I", "J", "L", "O", "Z", "T", "S"};

    private int min_Color = 0;
    private int max_Color = 7;
    private int blocksInShape = 4;

    public int shapeId;
    public int position;

    public RotationEngine rotEngine;

    public Shape() {
        //Get a random new shape
        shape = new SingleBlock[4];
        createShape();
        rotEngine = new RotationEngine();
    }

    public void createShape()
    {
        Random rand_shape = new Random();
        shapeId = rand_shape.nextInt(shapeList.length);
        //with a random color
        int newColor = rand_shape.nextInt((max_Color - min_Color) + 1) + min_Color;
        //Based on random shape, the position of 4 blocks is generated
        //As this is initialization, position is 0
        position = 0;
        switch(shapeId)
        {
            case 0: //I
                shape[0] = new SingleBlock(newColor, 3,0);
                shape[1] = new SingleBlock(newColor, 4,0);
                shape[2] = new SingleBlock(newColor, 5,0);
                shape[3] = new SingleBlock(newColor, 6,0);
                break;

            case 1: //J
                shape[0] = new SingleBlock(newColor, 3,-1);
                shape[1] = new SingleBlock(newColor, 3,0);
                shape[2] = new SingleBlock(newColor, 4,0);
                shape[3] = new SingleBlock(newColor, 5,0);
                break;

            case 2: //L
                shape[0] = new SingleBlock(newColor, 3,0);
                shape[1] = new SingleBlock(newColor, 4,0);
                shape[2] = new SingleBlock(newColor, 5,0);
                shape[3] = new SingleBlock(newColor, 5,-1);
                break;

            case 3: //O
                shape[0] = new SingleBlock(newColor, 4,-1);
                shape[1] = new SingleBlock(newColor, 5,-1);
                shape[2] = new SingleBlock(newColor, 5,0);
                shape[3] = new SingleBlock(newColor, 4,0);
                break;

            case 4: //Z
                shape[0] = new SingleBlock(newColor, 3,0);
                shape[1] = new SingleBlock(newColor, 4,0);
                shape[2] = new SingleBlock(newColor, 4,-1);
                shape[3] = new SingleBlock(newColor, 5,-1);
                break;

            case 5: //T
                shape[0] = new SingleBlock(newColor, 3,0);
                shape[1] = new SingleBlock(newColor, 4,0);
                shape[2] = new SingleBlock(newColor, 5,0);
                shape[3] = new SingleBlock(newColor, 4,-1);
                break;

            case 6: //S
                shape[0] = new SingleBlock(newColor, 3,-1);
                shape[1] = new SingleBlock(newColor, 4,-1);
                shape[2] = new SingleBlock(newColor, 4,0);
                shape[3] = new SingleBlock(newColor, 5,0);
                break;
        }
    }

public void goDown()
{
    //GoDown for all blocks of that shape
    for(int i=0; i < blocksInShape; i++)
    {
        shape[i].goDown();
    }
}
    //if any of the blocks of the shape is out of bounds
    //return false
    public boolean checkPosition(PositionMatrix matrix)
    {
        for (int i=0; i < blocksInShape; i++)
        {
            if (shape[i].checkPos(matrix) == false)
                return false;
        }
        return true;
    }

    //Check next position before moving
    //Invoking same method for every block in shape
    public boolean checkNextLeftPosition(PositionMatrix matrix)
    {
        for (int i=0; i<blocksInShape; i++)
        {
            if (shape[i].checkNextLeftPosition(matrix) == false)
                return false;
        }
        return true;
    }

    //Check next position before moving
    //Invoking same method for every block in shape
    public boolean checkNextRightPosition(PositionMatrix matrix)
    {
        for (int i=0; i<blocksInShape; i++)
        {
            if (shape[i].checkNextRightPosition(matrix) == false)
                return false;
        }
        return true;
    }

    //Check next position before moving
    //Invoking same method for every block in shape
    public boolean checkNextDownPosition(PositionMatrix matrix)
    {
        for (int i=0; i<blocksInShape;i++)
        {
            if (shape[i].checkNextDownPosition(matrix) == false)
                return false;
        }
        return true;
    }
    //If can move, move all blocks of shape
    public void moveLeft(PositionMatrix matrix)
    {
        if (checkNextLeftPosition(matrix))
        {
            for (int i=0; i<blocksInShape;i++)
                shape[i].moveLeft();
        }
    }

    //If can move, move all blocks of shape
    public void moveRight(PositionMatrix matrix)
    {
        if (checkNextRightPosition(matrix))
        {
            for (int i=0; i<blocksInShape;i++)
                shape[i].moveRight();
        }
    }

    // Move the tetris piece to an arbitrary location.
    public void move(int x, int y)
    {
        for (int i = 0; i < blocksInShape; i++)
            shape[i].move(x, y);
    }

    // Return the array of tetris blocks making up this piece.
    public SingleBlock[] getBlocks ()
    {
        return shape;
    }

// drop method to drop shape all way down
    public void drop(PositionMatrix matrix)
    {
        while (checkNextDownPosition( matrix))
        {
            goDown();
        }
    }

    //Implementation of rotation based on RotationEngine output
    //Set the new coordinates to a dummy object and check if out of bounds
    //If not, then assign to real object
    public void rotate(PositionMatrix matrix)
    {
        //get next position
        int[] tempPosition = rotEngine.getNextPosition(shapeId, position);

        //apply coordinates to a dummy shape
        SingleBlock[] blk = new SingleBlock[4];
        for (int p=0; p<4; p++)
        {
            blk[p] = new SingleBlock(0,shape[p].getXpos()+tempPosition[p*2], shape[p].getYpos()+tempPosition[(p*2)+1]);
        }

        //check if dummy shape is valid when rotated
        for (int j = 0; j < blocksInShape; j++)
            if (blk[j].checkPosForRotation(matrix) == false)
            {
                return;
            }

        shape[0].move(tempPosition[0], tempPosition[1]);
        shape[1].move(tempPosition[2], tempPosition[3]);
        shape[2].move(tempPosition[4], tempPosition[5]);
        shape[3].move(tempPosition[6], tempPosition[7]);

        calcPosition();
    }

    //save next position of shape
    public void calcPosition()
    {
        switch(position)
        {
            case 0:
                position = 1;
                break;
            case 1:
                position = 2;
                break;
            case 2:
                position = 3;
                break;
            case 3:
                position = 0;
                break;
        }
    }

    //Draw the shape
    public void draw(Graphics g)
    {
        for (int i = 0; i < blocksInShape; i++)
            shape[i].draw(g);
    }

}
