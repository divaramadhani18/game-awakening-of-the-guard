import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolAbout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolAbout extends TombolDasar
{
    public TombolAbout() {
        setImage("b.about.png");
        
    }

    public void onClick() {
        Greenfoot.setWorld(new AboutPage());
    }

}
