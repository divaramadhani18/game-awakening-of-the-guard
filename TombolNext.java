import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolNext here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolNext extends TombolDasar
{
 
    private HowToPlayPage halamanHowTo;

    public TombolNext(HowToPlayPage halamanHowTo) {
        this.halamanHowTo = halamanHowTo;
        setImage("b.next.png");
        ubahUkuran(200,100);
    }

    public void onClick() {
        halamanHowTo.nextHalaman();
    }

}
