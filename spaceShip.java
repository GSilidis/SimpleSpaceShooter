import java.util.*;
import greenfoot.*;
import java.awt.Color;

public class spaceShip extends Actor
{

    private int timer = 0;
    private int count = 0; // Points
    private int health = 100;
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
    }
    public void shoot()
    {
        if (timer == 0)
        {
            bullet bul = new bullet(true);
            getWorld().addObject(bul, getX(), getY());
        }
    }
    public int decHealth()
    {
        health-=5;
        return health;
    }
    public void incCount()
    {
        count++;
    }
}
