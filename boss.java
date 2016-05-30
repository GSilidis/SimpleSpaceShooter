import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class boss extends enemy
{
    private byte health;        
    private short shootTime;    // Timer for shooting
    private byte outTimer = 50;
    public boss(int level)
    {
        switch(level)
        {
            case 1:
                health = 30;
                shootTime = 80;
                break;
            case 2:
                health = 50;
                shootTime = 60;
                break;
            case 3:
                health = 80;
                shootTime = 40;
                break;
            case 4:
                health = 100;
                shootTime = 20;
                break;
        }
        if (Greenfoot.getRandomNumber(2) == 1) // For random movement
        {
            isUp = true;
        }
    }
    public void act()
    {
        if (outTimer > 0)
        {
            move(-1);
            outTimer--;
        }
        if (shootTimer > 0)
        {
            shootTimer--;
        }
        else
        {
            shoot();
            shootTimer = shootTime;
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
            moveTimer = 90+Greenfoot.getRandomNumber(25);
        }
        if (isUp && getY()<=3) // If reaches border
        {
            isUp = false;
            moveTimer = 90+Greenfoot.getRandomNumber(25);
        }
        if (!isUp && getY()>=getWorld().getHeight()-3)
        {
            isUp = true;
            moveTimer = 90+Greenfoot.getRandomNumber(25);
        }
        if (isTouching(spaceShip.class) == true) // If touches player
        {
            MyWorld world;
            world = (MyWorld)getWorld();
            world.damaged(false); // Damages player
            hit();
        }
    }
    public void hit()
    {
        health-=5;
        if (health<=0)
        {
            MyWorld world;
            world = (MyWorld)getWorld();
            world.bossDead(shootTime);
            world.removeObject(this);
        }
    }
}
