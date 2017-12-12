package assignment.sidm.com.assignment01_ver2;

public interface Collidable
{
    String GetType();
    float GetPosX();
    float GetPosY();
    float Radius();

    void OnHit(Collidable _other);

}
