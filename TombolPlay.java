import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TombolPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TombolPlay extends TombolDasar
{
    public TombolPlay()
    {
        setImage("b.play.png");
        ubahUkuran(130,80);
    }

    public void onClick(){
            // Hentikan musik beranda sebelum berpindah
            Beranda.berhentiMusik();

            // Pindah ke world Level 1
            Greenfoot.setWorld(new Level1());
        }
    }

    
