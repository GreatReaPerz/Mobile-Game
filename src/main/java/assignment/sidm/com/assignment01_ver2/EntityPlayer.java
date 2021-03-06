package assignment.sidm.com.assignment01_ver2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;


public class EntityPlayer implements Entitybase, Collidable
{
    private int health;
    private Bitmap bmp = null;
    private Vector3 position;
    private boolean b_IsDone;

    public final static EntityPlayer Instance = new EntityPlayer();
    private SurfaceView view = null;

    private EntityPlayer()
    {

    }

    @Override
    public boolean IsDone() {
        return b_IsDone;
    }

    @Override
    public void SetIsDone(boolean _IsDone) {
        b_IsDone = _IsDone;
    }

    @Override
    public void Init(SurfaceView _view)
    {
        b_IsDone = false;
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_4);

        Random ranGen = new Random();

        view = _view;
        position = new Vector3(30, 1000, 1.f);
        health = 1;
    }

    @Override
    public void Update(float _dt)
    {
        if(!b_IsDone) {
            if (TouchManager.Instance.IsDown()) {
                if (TouchManager.Instance.GetPosX() < (view.getWidth() / 2)) {
                    if (position.y > 20)
                        position.y -= _dt * 300;
                }

                if (TouchManager.Instance.GetPosX() > (view.getWidth() / 2)) {
                    //TODO shoot
                }
            } else {
                if (position.y < 1000)
                    position.y += _dt * 200;
            }
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        if(!b_IsDone)
            _canvas.drawBitmap(bmp, position.x - bmp.getWidth() * 0.5f, position.y - bmp.getHeight() * 0.5f, null);
    }

    @Override
    public String GetType() {
        return "PlayerEntity";
    }

    @Override
    public float GetPosX() {
        return position.x;
    }

    @Override
    public float GetPosY() {
        return position.y;
    }

    @Override
    public float Radius() {
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType().equals("SampleEntity"))
        {
            health -= 10;

            if(health <= 0)
             SetIsDone(true);
        }
    }

}
