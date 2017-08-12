/*
 * Created by Christos on 11/29/2014.
 */

public class RotationEngine
{
    private int nxt_position;
    private int[] positionCoordinates;

    public RotationEngine()
    {
        nxt_position = 0;
        positionCoordinates = new int[8];
    }

    public int[] getNextPosition(int shapeID, int curPosition)
    {
        //Calculate the next position id based on current position id
        switch(curPosition)
        {
            case 0:
                nxt_position = 1;
                break;
            case 1:
                nxt_position = 2;
                break;
            case 2:
                nxt_position = 3;
                break;
            case 3:
                nxt_position = 0;
                break;
        }
        //{"I", "J", "L", "O", "S", "T", "Z"};
        switch(shapeID)
        {
            case 0:
                getPositionI();
                break;
            case 1:
                getPositionJ();
                break;
            case 2:
                getPositionL();
                break;
            case 3:
                getPositionO();
                break;
            case 4:
                getPositionS();
                break;
            case 5:
                getPositionT();
                break;
            case 6:
                getPositionZ();
                break;
        }
        return positionCoordinates;
    }

    private void getPositionI()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{-1, -2, 0, -1, 1, 0, 2, 1};
                break;
            case 1:
                positionCoordinates = new int[]{2, -1, 1, 0, 0, 1, -1, 2};
                break;
            case 2:
                positionCoordinates = new int[]{1, 2, 0, 1, -1, 0, -2, -1};
                break;
            case 3:
                positionCoordinates = new int[]{-2, 1, -1, 0, 0, -1, 1, -2};
                break;
        }
    }

    private void getPositionL()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{-1,-1,0,0,1,1,2,0};
                break;
            case 1:
                positionCoordinates = new int[]{1,-1,0,0,-1,1,0,2};
                break;
            case 2:
                positionCoordinates = new int[]{1,1,0,0,-1,-1,-2,0};
                break;
            case 3:
                positionCoordinates = new int[]{-1,1,0,0,1,-1,0,-2};
                break;
        }
    }

    private void getPositionJ()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{0,-2,-1,-1,0,0,1,1};
                break;
            case 1:
                positionCoordinates = new int[]{2,0,1,-1,0,0,-1,1};
                break;
            case 2:
                positionCoordinates = new int[]{0,2,1,1,0,0,-1,-1};
                break;
            case 3:
                positionCoordinates = new int[]{-2,0,-1,1,0,0,1,-1};
                break;
        }
    }

    private void getPositionO()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{0,-1,1,0,0,1,-1,0};
                break;
            case 1:
                positionCoordinates = new int[]{1,0,0,1,-1,0,0,-1};
                break;
            case 2:
                positionCoordinates = new int[]{0,1,-1,0,0,-1,1,0};
                break;
            case 3:
                positionCoordinates = new int[]{-1,0,0,-1,1,0,0,1};
                break;
        }
    }

    private void getPositionS()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{-1,-1,0,0,1,-1,2,0};
                break;
            case 1:
                positionCoordinates = new int[]{1,-1,0,0,1,1,0,2};
                break;
            case 2:
                positionCoordinates = new int[]{1,1,0,0,-1,1,-2,0};
                break;
            case 3:
                positionCoordinates = new int[]{-1,1,0,0,-1,-1,0,-2};
                break;
        }
    }

    private void getPositionT()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{-1,-1,0,0,1,1,1,-1};
                break;
            case 1:
                positionCoordinates = new int[]{1,-1,0,0,-1,1,1,1};
                break;
            case 2:
                positionCoordinates = new int[]{1,1,0,0,-1,-1,-1,1};
                break;
            case 3:
                positionCoordinates = new int[]{-1,1,0,0,1,-1,-1,-1};
                break;
        }
    }

    private void getPositionZ()
    {
        switch(nxt_position)
        {
            case 0:
                positionCoordinates = new int[]{0,-2,1,-1,0,0,1,1};
                break;
            case 1:
                positionCoordinates = new int[]{2,0,1,1,0,0,-1,1};
                break;
            case 2:
                positionCoordinates = new int[]{0,2,-1,1,0,0,-1,-1};
                break;
            case 3:
                positionCoordinates = new int[]{-2,0,-1,-1,0,0,1,-1};
                break;
        }
    }
}
