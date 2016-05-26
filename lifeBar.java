import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; // Color
public class lifeBar extends Actor
{
    public lifeBar()
    {
        setImage(new GreenfootImage("Life: 100%", 20, Color.WHITE, Color.BLACK));
    }
    public void setBar(int health)
    {
        setImage(new GreenfootImage("Life: "+health+"%", 20, Color.WHITE, Color.BLACK));
    }
    public void over()
    {
        setImage(new GreenfootImage("Game Over. Press Reset to play again", 20, Color.WHITE, Color.BLACK));
        setLocation(getImage().getWidth(), getY());
    }
}