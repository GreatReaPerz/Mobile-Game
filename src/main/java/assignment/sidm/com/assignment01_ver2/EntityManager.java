package assignment.sidm.com.assignment01_ver2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import java.util.LinkedList;

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    private SurfaceView view = null;

    private LinkedList<Entitybase> m_EntityList = new LinkedList<>();

    private EntityManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    public void Update(float _dt)
    {
        LinkedList<Entitybase> removalList = new LinkedList<>();
        for (Entitybase currEntity : m_EntityList)
        {
            currEntity.Update(_dt);

            if (currEntity.IsDone())
            {
                removalList.add(currEntity);
            }
        }

        for (int i = 0; i < m_EntityList.size(); ++i)
        {
            Entitybase currEntity = m_EntityList.get(i);

            if (currEntity instanceof  Collidable)
            {
                Collidable first = (Collidable)currEntity;

                for (int j = i + 1; j < m_EntityList.size(); ++j)
                {
                    Entitybase  otherEntity = m_EntityList.get(j);

                    if (otherEntity instanceof Collidable)
                    {
                        Collidable second = (Collidable)otherEntity;

                        if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(), first.Radius(), second.GetPosX(),second.GetPosY(),second.Radius()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }

            }

            if (currEntity.IsDone())
                removalList.add(currEntity);
        }

        for (Entitybase currEntity : removalList)
        {
            m_EntityList.remove(currEntity);
        }

        removalList.clear();

    }

    public void Render(Canvas _canvas)
    {
        for (Entitybase currEntity : m_EntityList)
        {
            currEntity.Render(_canvas);
        }
    }

    public void AddEntity(Entitybase _newEntity)
    {
        _newEntity.Init(view);
        m_EntityList.add(_newEntity);
    }
}

