import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goblin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goblin extends Musuh implements Serangable
{
    private GreenfootImage[] serang;
    private int delaySerang = 100;
    private int timerSerang = 200;
    private int hp = 2;
    private boolean sedangMenyerang = false;
    

    public static GreenfootSound musikSerang = new GreenfootSound("serang.wav");
    public static GreenfootSound suaraMuncul = new GreenfootSound("suara goblin.wav");
    public static GreenfootSound suaraMati = new GreenfootSound("suara goblin.wav");

    public Goblin() {
        jalan = new GreenfootImage[6];
        for (int i = 1; i <= 6; i++) {
            jalan[i - 1] = new GreenfootImage("g.walk" + i + ".png");
            jalan[i - 1].scale(120, 120);
        }

        mati = new GreenfootImage[8];
        for (int i = 1; i <= 8; i++) {
            mati[i - 1] = new GreenfootImage("g.death" + i + ".png");
            mati[i - 1].scale(120, 120);
        }

        serang = new GreenfootImage[8];
        for (int i = 1; i <= 8; i++) {
            serang[i - 1] = new GreenfootImage("g.attack" + i + ".png");
            serang[i - 1].scale(150, 150);
        }

        kecepatan = 1;
        nilaiSkor = 10;
        suaraMuncul.play();
    }

    @Override
    public void act() {
        

        if (sedangMati) {
            animasiMati();
            return;
        }

        if (sedangMenyerang) {
            animasiSerang();
            return;
        }

        serang();

        gerak();
        animasiJalan();
        cekTepi();
    }


    public void terkenaSerangan() {
        if (sedangMati) return;

        hp--;
        if (hp <= 0) {
            sedangMati = true;
            suaraMati.play();

            World w = getWorld();
            if (w instanceof Level1) ((Level1)w).tambahSkor(nilaiSkor);
            else if (w instanceof Level2) ((Level2)w).tambahSkor(nilaiSkor);
        }
    }
    public void serang(){
        World w = getWorld();
        Pahlawan hero = null;
        if (w instanceof Level2 lvl2) {
            hero = lvl2.getHero();
        } else if (w instanceof Level3 lvl3) {
            hero = lvl3.getHero();
        } else {
            return; // world bukan Level2 atau Level3
        }
       
        if (hero != null && Math.abs(getX() - hero.getX()) < 70 && Math.abs(getY() - hero.getY()) < 50) {
            sedangMenyerang = true;
            timerSerang = delaySerang;
            musikSerang.play();
            hero.terkenaSerangan();
            if (w instanceof Level2 lvl2) {
                lvl2.kurangiNyawa(1);
            } else if (w instanceof Level3 lvl3) {
                lvl3.kurangiNyawa(1);
            }
            return;
        }
    }

    private void animasiSerang() {
        if (timerSerang > 0) timerSerang--;
        int indeks = (timerSerang / 15);
        indeks = Math.max(0, Math.min(indeks, serang.length - 1));
        setImage(serang[indeks]);
        if (timerSerang <= 0) sedangMenyerang = false;
    }

}
