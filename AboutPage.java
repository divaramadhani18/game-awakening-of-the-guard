import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LAbout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AboutPage extends World
{

    public AboutPage() {
        super(1125, 625, 1);
        setBackground("l.about.png");
        addObject(new TombolHome(), getWidth()/2, getHeight()/2 +200);
    }

}
