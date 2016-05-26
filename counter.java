import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; // Color

public class counter extends Actor
{
    private int points;
    public counter()
    {
        setImage(new GreenfootImage("0", 20, Color.WHITE, Color.BLACK));
    } 
    public int getPoints()
    {
        return points;
    }
    public void inc()
    {
        points++;
        setImage(new GreenfootImage("" + points, 20, Color.WHITE, Color.BLACK));
    }
    public void toZero()
    {
        points=0;
        setImage(new GreenfootImage("" + points, 20, Color.WHITE, Color.BLACK));
    }
}
