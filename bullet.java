import java.util.*;
import greenfoot.*;
import java.awt.Color;
public class bullet extends Actor
{
    private boolean isPlayers;  // For checking if this bullet shot by player or not
    public bullet(boolean param)
    {
        isPlayers = param;
    }
    public void act()
    {
        move(4);
        if (getX() > (getWorld().getWidth()-5) || getY() > (getWorld().getHeight()) || getX()<=1)
        {
           getWorld().removeObject(this);
           return;
        }
        if (isPlayers == true) // If shot by player - check if it touches enemies
        {
            Actor enmy;
            enmy = getOneObjectAtOffset(0, 0, enemy.class);
            if (enmy!=null)
            {
                counter count;
                MyWorld world;
                world = (MyWorld)getWorld();
                count = world.getCounter();
                count.inc();
                getWorld().removeObject(enmy);
                getWorld().removeObject(this);          
            }
            Actor bull;
            try // smth wrong going on here
            {
                bull = getOneObjectAtOffset(0, 0, bullet.class);
            }
            catch(IllegalStateException e) // Sometimes this method throws this exceptcion (Actor not in world.)
            {
                return; // If it so, just skip
            }
            if (bull!=null)
            {
                counter count;
                MyWorld world;
                world = (MyWorld)getWorld();
                count = world.getCounter();
                count.inc();
                getWorld().removeObject(bull);
                getWorld().removeObject(this);          
            }
        }
        else // If shot by enemy - check if it touches player
        {
            if (isTouching(spaceShip.class) == true)
            {
                MyWorld world;
                world = (MyWorld)getWorld();
                world.damaged();
                world.removeObject(this);
            }
        }
    }
}

