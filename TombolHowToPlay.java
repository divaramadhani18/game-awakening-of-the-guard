import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolHowToPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolHowToPlay extends TombolDasar
{
     public TombolHowToPlay() {
        setImage("b.howtoplay.png");
    }

    public void onClick() {
        Greenfoot.setWorld(new HowToPlayPage());
    }

}
