import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolQuit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolQuit extends TombolDasar
{
    public TombolQuit() {
        setImage("b.quit.png"); 
        ubahUkuran(80, 40); 
    }

    @Override
    public void onClick() {
        Greenfoot.stop(); 
    }

}
