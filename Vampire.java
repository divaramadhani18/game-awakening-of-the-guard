import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Vampire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vampire extends Musuh implements Serangable
{
    private GreenfootImage[] serang;
    private int delaySerang = 100;
    private int timerSerang = 200;
    private int hp = 2;
    private boolean sedangMenyerang = false;
    

    public static GreenfootSound musikSerang = new GreenfootSound("laser vampire.wav");
    public static GreenfootSound suaraMuncul = new GreenfootSound("suara goblin.wav");
    public static GreenfootSound suaraMati = new GreenfootSound("suara goblin.wav");

    public Vampire() {
        jalan = new GreenfootImage[8];
        for (int i = 1; i <= 8; i++) {
            jalan[i - 1] = new GreenfootImage("v.run" + i + ".png");
            jalan[i - 1].scale(170, 170);
        }

        mati = new GreenfootImage[11];
        for (int i = 1; i <= 11; i++) {
            mati[i - 1] = new GreenfootImage("v.death" + i + ".png");
            mati[i - 1].scale(170, 170);
        }

        serang = new GreenfootImage[12];
        for (int i = 1; i <= 12; i++) {
            serang[i - 1] = new GreenfootImage("v.attack" + i + ".png");
            serang[i - 1].scale(170, 170);
        }

        kecepatan = 2;
        nilaiSkor = 20;
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
            else if (w instanceof Level3) ((Level3)w).tambahSkor(nilaiSkor);
        }
    }
    
    public void serang(){
        if (getWorld() == null) return;
        Level3 dunia = (Level3) getWorld();
        Pahlawan hero = dunia.getHero();

        if (hero != null && Math.abs(getX() - hero.getX()) < 70  && Math.abs(getY() - hero.getY()) < 50) {
            sedangMenyerang = true;
            timerSerang = delaySerang;
            musikSerang.play();
            hero.terkenaSerangan();
            dunia.kurangiNyawa(2);
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
