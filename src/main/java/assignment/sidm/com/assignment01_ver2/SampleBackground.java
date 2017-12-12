package assignment.sidm.com.assignment01_ver2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleBackground implements Entitybase
{

    private Bitmap bmp = null;
    private boolean b_IsDone = false;

    private float xPos, yPos, offset;
    private SurfaceView view = null;

    @Override
    public boolean IsDone() {
        return b_IsDone;
    }

    @Override
    public void SetIsDone(boolean _IsDone) {
        b_IsDone = _IsDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gamescene);
        view = _view;
        offset = 0.f;
    }

    @Override
    public void Update(float _dt) {
        //offset += _dt * 0.1f;
    }

    @Override
    public void Render(Canvas _canvas) {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        float xOffset = (float)Math.sin(offset) * bmp.getWidth() * 0.3f;

        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f + xOffset, yPos - bmp.getHeight() * 0.5f, null);
    }

    public static SampleBackground Create()
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result);

        return result;
    }
}
