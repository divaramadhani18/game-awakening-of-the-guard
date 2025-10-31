import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant extends Musuh
{
     public Plant()
    {
        jalan = new GreenfootImage[6];
        for (int i = 1; i <= 6; i++)
        {
            jalan[i-1] = new GreenfootImage("p.walk" + i + ".png");
            jalan[i-1].scale(80, 80);
        }

        mati = new GreenfootImage[10];
        for (int i = 1; i <= 10; i++)
        {
            mati[i-1] = new GreenfootImage("p.death" + i + ".png");
            mati[i-1].scale(80, 80);
        }

        kecepatan = 1;
        nilaiSkor = 5;
    }

}
