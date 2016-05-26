import java.util.*;
import greenfoot.*;
import java.awt.Color;

public class MyWorld extends World
{
    private int timer = 100;     
    private int spawnTimer = 100; // Timer for respawn
    private int spawnCount = 0;   // For increasing speed of respawn
    private counter count;        // Score
    private lifeBar life;         // Lifebar
    private spaceShip player;
    private boolean isPlaing=true;// For ending the game
    //private 
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
    }
    public void act()
    {
        if (timer > 0)
        {timer--;}
        else
        {
            spawn();
        }
        drawBackgroundImage(); // Move bg
    }
    public void drawBackgroundImage() 
    {
        GreenfootImage bg = new GreenfootImage(getBackground());
        getBackground().drawImage(bg, -1, 0);
        getBackground().drawImage(bg, getWidth()-5, 0);
    }
    public void spawn()
    {
        if (isPlaing == true)
        {
            enemy Enemy;
            Enemy = new enemy();
            addObject(Enemy,this.getWidth(),Greenfoot.getRandomNumber(this.getHeight()));
            timer = spawnTimer;
            spawnCount++;
            if (spawnCount >= 10)
            {
                spawnTimer -= 3;
                spawnCount = 0;
            }
        }
    }
    public counter getCounter()
    {
        return count;
    }
    public void damaged()
    {
        int health = player.decHealth();
        if (health > 0)
        {
            life.setBar(health);
        }
        else
        {
            life.over();               // Print "Game Over" message
            this.removeObject(player); // Remove player
            isPlaing = false;          // Stop enemies respawn
            Greenfoot.stop();
        }
    }
}
