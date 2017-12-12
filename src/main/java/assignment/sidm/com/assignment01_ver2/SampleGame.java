package assignment.sidm.com.assignment01_ver2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleGame
{
    public final static SampleGame Instance = new SampleGame();

    private Bitmap bmp;
    float offset = 0.f;
    private float timer = 0.f;

    private SampleGame()
    {

    }

    public void Init(SurfaceView _view)
    {
        EntityPlayer.Instance.Init(_view);
        EntityManager.Instance.Init(_view);
        SampleBackground.Create();

    }

    public void Update(float _deltaTime)
    {
        timer += _deltaTime;
        if (timer > 1.0f)
        {
            SampleEntity.Create();

            timer = 0.f;
        }

        EntityPlayer.Instance.Update(_deltaTime);
        EntityManager.Instance.Update(_deltaTime);
    }

    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
        EntityPlayer.Instance.Render(_canvas);
    }
}

