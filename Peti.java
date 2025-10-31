import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Peti here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Peti extends Actor
{
    private int hp = 3;
    private boolean aktif = true;
    private int cooldown = 0;
    private boolean baruKena = false;

    public Peti() {
        setImage(new GreenfootImage("peti.png"));
        getImage().scale(80, 80);

    }

    public void act() {
        if (getWorld() == null) return;

        if (!aktif) {
            cooldown--;
            if (cooldown <= 0) respawn();
            return;
        }

        World w = getWorld();
        Pahlawan hero = null;
        if (w instanceof Level2 lvl2) {
            hero = lvl2.getHero();
        } else if (w instanceof Level3 lvl3) {
            hero = lvl3.getHero();
        } else {
            return;
        }

        // Reset bila hero sudah berhenti menyerang
        if (!hero.sedangMenyerang) {
            baruKena = false;
        }

        // Jika hero menyerang dan belum dihitung untuk serangan ini
        if (hero.sedangMenyerang && !baruKena && Math.abs(getX() - hero.getX()) < 60 && Math.abs(getY() - hero.getY()) < 50) {
            baruKena = true;
            hp--;

            if (hp <= 0) {
                aktif = false;
                if (w instanceof Level2 lvl2) {
                    lvl2.tambahNyawa(2);
                } else if (w instanceof Level3 lvl3) {
                    lvl3.tambahNyawa(2);
                }
                getImage().setTransparency(0);
                cooldown = 600; // 10 detik
            }
        }
    }

    private void respawn() {
        hp = 3;
        aktif = true;
        getImage().setTransparency(255);
        int y = Greenfoot.getRandomNumber(370) + 233;
        int x = Greenfoot.getRandomNumber(600) + 400;
        setLocation(x, y);
    }
}
