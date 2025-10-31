import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinMenu extends World
{

    private GreenfootSound musikMenang = new GreenfootSound("SoundWin.wav");
    private int skorAkhir;

    public WinMenu() {    
        super(1125, 625, 1); 
        setBackground(new GreenfootImage("l.win.png"));
        addObject(new TombolHome(), getWidth()/2, getHeight()/2 +200);
     
        musikMenang.play();
    }
    

}
