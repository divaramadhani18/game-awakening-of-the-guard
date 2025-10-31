import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slime3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slime3 extends Musuh
{
     public Slime3() {
        jalan = new GreenfootImage[6];
        for (int i = 1; i <= 6; i++) {
            jalan[i - 1] = new GreenfootImage("s3.walk" + i + ".png");
            jalan[i - 1].scale(90, 90);
        }

        mati = new GreenfootImage[8];
        for (int i = 1; i <= 8; i++) {
            mati[i - 1] = new GreenfootImage("s3.death" + i + ".png");
            mati[i - 1].scale(90, 90);
        }

        kecepatan = 2; // lebih cepat dari slime 1 dan 2
        nilaiSkor = 5; 
    }

}
