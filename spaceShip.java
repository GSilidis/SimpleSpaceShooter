import java.util.*;
import greenfoot.*;
import java.awt.Color;

public class spaceShip extends Actor
{

    private short timer = 0;
    private int score = 0; // Points
    private byte health = 100;
    private short reloadTime = 10;
    public void act()
    {
        if (timer > 0)
        {
            timer--;
        }
        if (Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(), getY() - 4);
        }
        if (Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), getY() + 4);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            move(-4);
        }
        if (Greenfoot.isKeyDown("right"))
        {
            move(4);
        }
        if (Greenfoot.isKeyDown("space"))
        {
            shoot();
            timer = reloadTime;
        }
        /*if (Greenfoot.isKeyDown("t")) // For debuging purposes only - adds more score
        {
            MyWorld world;
            world = (MyWorld)getWorld();
            world.addPoints(1);
        }
        if (Greenfoot.isKeyDown("p")) // For debuging purposes only - restores health
        {
            health = 100;
        }*/
    }
    public void shoot()
    {
        if (timer == 0)
        {
            bullet bul = new bullet(true);
            getWorld().addObject(bul, getX(), getY());
        }
    }
    public byte decHealth(byte dec)
    {
        health-=dec;
        return health;
    }
    public void setReloadTime(short time)
    {
        reloadTime=time;
    }
    public void restoreHealth()
    {
        health = 100;
    }
    public void incscore()
    {
        score++;
    }
}
