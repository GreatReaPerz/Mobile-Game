package assignment.sidm.com.assignment01_ver2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class SampleEntity implements Entitybase, Collidable
{
    private Bitmap bmp = null;
    private boolean b_IsDone = false;

    private float xPos, yPos, xDir, yDir, lifeTime;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.asteroid_2);

        lifeTime = 10.f;

        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();

        xDir = ranGen.nextFloat() * 100.0f + 50;
    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;

        if (lifeTime < 0.0f)
            SetIsDone(true);

        xPos -= xDir * _dt;

            float imgRadius = bmp.getHeight() * 0.5f;

            if (Collision.SphereToSphere(EntityPlayer.Instance.GetPosX(), EntityPlayer.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius)) {
                SetIsDone(true);
                EntityPlayer.Instance.OnHit(this);
            }

    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }

    public static SampleEntity Create()
    {
        SampleEntity result = new SampleEntity();
        EntityManager.Instance.AddEntity(result);

        return result;
    }

    @Override
    public String GetType() {
        return "SampleEntity";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float Radius() {
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType().equals("SampleEntity"))
        {
            SetIsDone(true);
        }
    }
}
