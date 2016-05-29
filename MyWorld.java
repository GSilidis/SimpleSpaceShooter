import java.util.*;
import greenfoot.*;
import java.awt.Color;

public class MyWorld extends World
{
    private short timer = 100;      // Respawn timer
    private short spawnTime = 100;  // Time to respawn
    private byte pointsCount = 0;   // For increasing speed of respawn
    private counter count;          // Score
    private lifeBar life;           // Lifebar
    private spaceShip player;       // Player
    private boolean isSpawning=true;// Enables enemies respawn 
    public MyWorld()
    {
        super(600, 400, 1);
        player = new spaceShip();
        addObject(player,62,220);
        enemy Enemy;
        Enemy = new enemy();
        addObject(Enemy,this.getWidth(),128);
        count = new counter();
        addObject(count, 20, 10);
        life = new lifeBar();
        addObject(life, 50, this.getHeight()-15); 
        setPaintOrder(counter.class, lifeBar.class, spaceShip.class, bullet.class, enemy.class); // Scorebar and lifebar are always on top
    }
    public void act()
    {
        if (timer > 0)
        {
            timer--;
        }
        else
        {
            spawn();
        }
        drawBackgroundImage(); // Move bg
    }
    private void drawBackgroundImage() 
    {
        GreenfootImage bg = new GreenfootImage(getBackground());
        getBackground().drawImage(bg, -1, 0);
        getBackground().drawImage(bg, getWidth()-5, 0);
    }
    public void spawn() // Spawn enemy
    {
        if (isSpawning == true)
        {
            enemy Enemy;
            Enemy = new enemy();
            addObject(Enemy,this.getWidth(),Greenfoot.getRandomNumber(this.getHeight()));
            timer = spawnTime;
            if (pointsCount >= 10)
            {
                if (spawnTime > 16) // Its almost imposible to play with less spawnTime values
                {
                    spawnTime -= 3;
                }
                pointsCount = 0;
            }
        }
    }
    public counter getCounter()
    {
        return count;
    }
    public void incCounter()
    {
        pointsCount++;
        count.inc();
        switch(count.getPoints())
        {
            case 100:
                spawnBoss(1);
                break;
            case 200:
                spawnBoss(2);
                break;
            case 300:
                spawnBoss(3);
                break;
            case 350:
                spawnBoss(4);
                break;
            default:
                break;
        }
    }
    private void spawnBoss(int level)
    {
        isSpawning = false;
        boss Boss = new boss(level);
        addObject(Boss,this.getWidth()+50,Greenfoot.getRandomNumber(this.getHeight()));
    }
    public void bossDead() // Called when boss is dead
    {
        if (player.decHealth((byte)0)>0)
        {
            if (count.getPoints() < 350)
            {
                isSpawning = true;
                life.setBar(100);
            }
            else
            {
                life.win();
                Greenfoot.stop();
                isSpawning = true;
                life.setBar(100);
            }
            player.restoreHealth();
        }
        //setBackground(new GreenfootImage("images/space1.jpg"));
    }
    public void damaged(boolean byBullet)
    {
        byte health;
        if (byBullet == true)
        {
            health = player.decHealth((byte)5);
        }
        else
        {
            health = player.decHealth((byte)10);
        }
        if (health > 0)
        {
            life.setBar(health);
        }
        else
        {
            life.over();               // Print "Game Over" message
            this.removeObject(player); // Remove player
            isSpawning = false;          // Stop enemies respawn
            Greenfoot.stop();
        }
    }
    public void addPoints(int q) // For debuging purposes only - adds more score
    {
        for (int i = 0; i<q; i++)
        {
            incCounter();
        }
    }
}
