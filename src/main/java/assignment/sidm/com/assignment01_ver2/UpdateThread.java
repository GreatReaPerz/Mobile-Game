package assignment.sidm.com.assignment01_ver2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.provider.Settings;
import android.view.SurfaceHolder;

public class UpdateThread extends Thread
{
    static final long targetFPS = 60;

    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning =false;

    public UpdateThread(GameView _view)
    {
        this.view = _view;
        holder = view.getHolder();

        SampleGame.Instance.Init(view);
    }

    public boolean IsRunning() { return isRunning;}

    public void Initialize()
    {
        isRunning = true;
    }

    public void Terminate()
    {
        isRunning = false;
    }

    public void run()
    {
        long framePerSecond = 1000 / targetFPS; // 1000 is milliseconds
        long startTime= 0;

        // We need another variable to calculate delta time
        long prevTime = System.nanoTime();

        while(isRunning)
        {
            //-----Update-----//
            // Here can be your state manager update!
            startTime = System.currentTimeMillis(); // THIS IS FOR FRC (LATER)

            // This part is to get delta time using prev time vs curr time
            long currTime = System.nanoTime();
            float deltaTime = (float)(currTime - prevTime) / 1000000000.0f;
            prevTime = currTime;

            //We wanna have this awesome update
            SampleGame.Instance.Update(deltaTime);
            //----------------//

            //-----Render-----//
            Canvas canvas = holder.lockCanvas(null);
            if (canvas != null)
            {
                // To prevent 2 threads from rendering at the same time
                synchronized (holder)
                {
                    // Render the whole screen black
                    canvas.drawColor(Color.BLACK);

                    //Insert stuff here
                     SampleGame.Instance.Render(canvas);
                }

                // Tis is teh part to do renda!
                holder.unlockCanvasAndPost((canvas));

            }
            //----------------//

            //Should have something to render the frame rate
            try
            {
                long sleepTime = framePerSecond - (System.currentTimeMillis() - startTime);

                if (sleepTime > 0)
                    sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                Terminate();
            }

            // End of the loop
        }
    }
}

