import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AktorAnimasi here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AktorAnimasi extends Actor
{
    protected GreenfootImage[] gambarAnimasi;
    protected int indeksAnimasi = 0;
    protected int delayAnimasi = 6;
    protected int hitungDelay = 0;
    
    
    //animasi
    protected void setAnimasi(String namaFile, int jumlahFrame)
    {
        gambarAnimasi = new GreenfootImage[jumlahFrame];
        for (int i = 0; i < jumlahFrame; i++)
        {
            gambarAnimasi[i] = new GreenfootImage(namaFile + i + ".png");
        }
        setImage(gambarAnimasi[0]);
    }
    
    public void animasiBerjalan(GreenfootImage[] gambarAnimasi) {
        
      if (gambarAnimasi == null || gambarAnimasi.length == 0) return; // Cegah null
    if (hitungDelay % delayAnimasi == 0)
    {
        indeksAnimasi++;
        if (indeksAnimasi >= gambarAnimasi.length)
            indeksAnimasi = 0;
        setImage(gambarAnimasi[indeksAnimasi]);
    }
    hitungDelay++;
}
}