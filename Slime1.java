import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slime1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slime1 extends Musuh
{
    public Slime1()
    {
        jalan = new GreenfootImage[8];
        for (int i = 1; i <= 8; i++)
        {
            jalan[i-1] = new GreenfootImage("s.walk" + i + ".png");
            jalan[i-1].scale(90, 90);
        }

        mati = new GreenfootImage[10];
        for (int i = 1; i <= 10; i++)
        {
            mati[i-1] = new GreenfootImage("s.death" + i + ".png");
            mati[i-1].scale(90, 90);
        }

        kecepatan = 1;
        nilaiSkor = 5;
    }

}
