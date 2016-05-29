import java.util.*;
import greenfoot.*;
import java.awt.Color;

public class spaceShip extends Actor
{

    private int timer = 0;
    private int count = 0; // Points
    private byte health = 100;
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
            timer = 10;
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
    public void restoreHealth()
    {
        health = 100;
    }
    public void incCount()
    {
        count++;
    }
}
