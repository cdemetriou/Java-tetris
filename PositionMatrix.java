import java.awt.*;
/*
 * Created by Christos on 11/29/2014.
 */

//Holds positions of current blocks
public class PositionMatrix {

    //matrix of rows x columns
    SingleBlock[][] block_matrix;
    private int blocksInShape;

    public PositionMatrix()
    {
       blocksInShape = 4;
       block_matrix = new SingleBlock[20][10];
    }

    public boolean isPositionFree(int w, int h)
    {
        if (w > 9 || w < 0 || h <0  || h >19)
            return false;

        return block_matrix[h][w]!=null;
    }

    public int addShape(Shape shape)
    {
        SingleBlock[] temp_shape = shape.getBlocks();

        for(int i=0; i<blocksInShape; i++)
        {
            block_matrix[temp_shape[i].getYpos()][temp_shape[i].getXpos()] = temp_shape[i];
        }
        return checkLines();

    }

    //Checks all lines for full lines
    public int checkLines()
    {
        int lines = 0;
        int[] linesLoc = new int[blocksInShape];

        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                //if a block is missing, break
                if (block_matrix[i][j] == null) {
                    break;

                }
                //if row is full
                else if (j == 9)
                {
                    linesLoc[lines++] = i;
                }
            }
         }

        if (lines > 0)
        {
            for (int i = 0; i < lines; i++)
            {
                for (int z = linesLoc[i]; z > 0; z--)
                {
                    //For last line, iterate in all row and shift to previous line
                    for (int j = 0; j < 10; j++)
                    {
                        block_matrix[z][j] = block_matrix[z - 1][j];
                        if (block_matrix[z][j] != null)
                            block_matrix[z][j].move(0, 1); // move blocks drawing position down one
                    }
                }
                //add new empty line on top
                for (int x = 0; x < 10; x++)
                    block_matrix[0][x] = null;
            }
        }
        return lines;
    }

    public void draw(Graphics g)
    {
        for (int i = 0; i < 20; i++)
            for(int j = 0; j < 10; j++)
                if (block_matrix[i][j] != null)
                    block_matrix[i][j].draw(g);
    }
}
