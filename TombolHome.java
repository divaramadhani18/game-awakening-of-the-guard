import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolHome here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolHome extends TombolDasar
{
    public TombolHome() {
        setImage("b.home.png");
        ubahUkuran(190,100);
    }

    public void onClick() {
        Greenfoot.setWorld(new Beranda());
    }

}
