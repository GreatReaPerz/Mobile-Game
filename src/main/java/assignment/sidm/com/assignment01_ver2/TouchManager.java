package assignment.sidm.com.assignment01_ver2;

import android.view.MotionEvent;

public class TouchManager
{
    public final static TouchManager Instance = new TouchManager(){};

    public enum TouchState
    {
        NONE,
        DOWN,
        MOVE
    }

    private TouchState status = TouchState.NONE;
    private int posX, posY;

    private TouchManager()
    {
        posX = 0;
        posY = 0;
    }

    public boolean HasTouch()
    {
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }

    public boolean IsDown()
    {
        return status == TouchState.DOWN;
    }

    public int GetPosX()
    {
        return posX;
    }

    public int GetPosY()
    {
        return posY;
    }

    public void Update(int _posX, int _posY, int motionEventStatus)
    {
        posX = _posX;
        posY = _posY;

        switch(motionEventStatus)
        {
            case MotionEvent.ACTION_DOWN:
                status = TouchState.DOWN;
                break;
            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;
            case MotionEvent.ACTION_UP:
                status = TouchState.NONE;
                break;
        }
    }
}
