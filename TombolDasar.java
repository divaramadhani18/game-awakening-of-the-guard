import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolDasar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public abstract class TombolDasar extends Actor implements Click
{
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
            Greenfoot.playSound("click.wav");
        }
    }

    public void ubahUkuran(int lebar, int tinggi) {
        GreenfootImage gambar = getImage();
        if (gambar != null) {
            gambar.scale(lebar, tinggi);
            setImage(gambar);
        }
    }
}

