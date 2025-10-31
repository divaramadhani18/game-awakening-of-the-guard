import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skor extends Actor
{
    private int skor = 0;
    private boolean sudahPindahLevel = false;
    public static int skorTerakhir = 0;

    public Skor()
    {
        perbaruiGambar();
    }

    public void tambah(int nilai)
    {
        skor += nilai;
        skorTerakhir= skor;
        perbaruiGambar();
        
    }

    public int getSkor()
    {
        return skor;
    }

    private void perbaruiGambar()
    {
        setImage(new GreenfootImage("Skor: " + skor, 30, Color.YELLOW, new Color(0,0,0,0)));
    }

}
