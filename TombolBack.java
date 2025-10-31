import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolBack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolBack extends TombolDasar
{
    private HowToPlayPage halamanHowTo;

    public TombolBack(HowToPlayPage halamanHowTo) {
        this.halamanHowTo = halamanHowTo;
        setImage("b.back.png");
        ubahUkuran(200,100);
    }

    public void onClick() {
        halamanHowTo.prevHalaman();
    }

}
