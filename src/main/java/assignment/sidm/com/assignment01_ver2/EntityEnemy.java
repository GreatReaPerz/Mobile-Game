package assignment.sidm.com.assignment01_ver2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;


public class EntityEnemy implements Entitybase, Collidable
{
    private int health;
    private Bitmap bmp = null;
    private Vector3 position;
    private boolean b_IsDone;

    //public final static EntityEnemy Instance = new EntityEnemy();
    private SurfaceView view = null;

    private EntityEnemy()
    {

    }
    public static SampleEntity Create()
    {
        SampleEntity result = new SampleEntity();
        EntityManager.Instance.AddEntity(result);

        return result;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_4);

        Random ranGen = new Random();

        view = _view;
        position = new Vector3(ranGen.nextFloat() * (_view.getWidth() / 2), ranGen.nextFloat() * (_view.getWidth() / 2), 0.f);
        health = 100;
    }

    @Override
    public void Update(float _dt)
    {
        float imgRadius = bmp.getHeight() * 0.5f;

        if (Collision.SphereToSphere(EntityPlayer.Instance.GetPosX(), EntityPlayer.Instance.GetPosY(), 0.0f, this.position.x, this.position.y, imgRadius))
            SetIsDone(true);

        if(this.position.y < EntityPlayer.Instance.GetPosY())
         position.y += _dt * 100;

    }

    @Override
    public void Render(Canvas _canvas)
    {
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
