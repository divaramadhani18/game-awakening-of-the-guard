import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EfekPercikan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfekPercikan extends Actor
{
    private GreenfootImage[] efek;
    private int frame = 0;

    public EfekPercikan() {
        efek = new GreenfootImage[13];
        for (int i = 0; i < 13; i++) {
            efek[i] = new GreenfootImage("Png_0" + i + ".png");
            efek[i].scale(60, 60);
        }
        setImage(efek[8]);
        Greenfoot.playSound("percikan air.wav");
        
    }

    public void act() {
        animasi();
    }

    private void animasi() {
        if (frame < efek.length) {
            setImage(efek[frame]);
            frame++;
        } else {
            getWorld().removeObject(this);
        }
    }

}
