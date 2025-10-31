import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Nyawa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nyawa extends Actor
{
    private int nyawa = 5;            // max 5 nyawa
    private int maxNyawa = 5;
    private GreenfootImage[] gambarNyawa;
    public static GreenfootSound musikNambah = new GreenfootSound("recharge heart.wav");

    
    public Nyawa()
    {
    gambarNyawa = new GreenfootImage[maxNyawa + 1]; 
    GreenfootImage kosong = new GreenfootImage(120, 40);
    kosong.setTransparency(0);
    gambarNyawa[0] = kosong;

    for (int i = 1; i <= maxNyawa; i++)
    {
        String file = "hati" + i + ".png"; 
        gambarNyawa[i] = new GreenfootImage(file);
        gambarNyawa[i].scale(130, 30);
    }
    perbaruiGambar();
    }

    public void kurangi()
    {
        if (nyawa > 0)
        {
            nyawa--;
            perbaruiGambar();
        }
    }

    // Tambah nyawa, tetapi tidak melebihi max
    public void tambah(int banyak)
    {
        if (banyak <= 0) return;
        nyawa += banyak;
        musikNambah.play();
        if (nyawa > maxNyawa) nyawa = maxNyawa;
        perbaruiGambar();
    }

    public int getNyawa()
    {
        return nyawa;
    }

    public void setMaxNyawa(int max)
    {
        this.maxNyawa = max;
    }

    private void perbaruiGambar()
    {
         if (nyawa < 0) nyawa = 0;
    if (nyawa > maxNyawa) nyawa = maxNyawa;
    setImage(gambarNyawa[nyawa]);
    }

   
}
