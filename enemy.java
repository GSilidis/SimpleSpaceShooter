import java.util.*;
import greenfoot.*;
import java.awt.Color;

public class enemy extends Actor
{
    protected short shootTimer = 0;// Timer for shooting
    protected int moveTimer = 0;   // Timer for movement in single direction
    protected boolean isUp;        // If enemy goes up
    public enemy()
    {
        if (Greenfoot.getRandomNumber(2) == 1) // For random movement
        {
            isUp = true;
        }
    }
    public void act()
    {
        move(-3);
        if (shootTimer > 0)
        {
            shootTimer--;
        }
        else
        {
            shoot();
            shootTimer = 100;
        }
        if (getX() <= 1) // If reaches right border
        {
           getWorld().removeObject(this);
           return;
        }
        if (isUp) // Go up
        {
            setLocation(getX(), getY()-3);
            moveTimer--;
        }
        if (!isUp) // Go down
        {
            setLocation(getX(), getY()+3);
            moveTimer--;
        }
        if (moveTimer <= 0) // Change direction
        {
            isUp = !isUp;
            moveTimer = 50+Greenfoot.getRandomNumber(25);
        }
        if (isUp && getY()<=3) // If reaches border
        {
            isUp = false;
            moveTimer = 50+Greenfoot.getRandomNumber(25);
        }
        if (!isUp && getY()>=getWorld().getHeight()-3)
        {
            isUp = true;
            moveTimer = 50+Greenfoot.getRandomNumber(25);
        }
        if (isTouching(spaceShip.class) == true) // If touches player
        {
            MyWorld world;
            world = (MyWorld)getWorld();
            world.damaged(false); // Damages player
            world.removeObject(this); // And deletes itself
        }
    }
    public void shoot()
    {
            bullet bul = new bullet(false);
            bul.setRotation(180);
            getWorld().addObject(bul, getX()-15, getY());
    }
    public void hit()
    {
        MyWorld world;
        world = (MyWorld)getWorld();
        world.incCounter();
        getWorld().removeObject(this);
    }
}
