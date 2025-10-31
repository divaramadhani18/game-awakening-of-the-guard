import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beranda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beranda extends World {
    // Musik latar belakang
    private static GreenfootSound musikBeranda = new GreenfootSound("hompage.mp3");

    public Beranda() {
        super(1125, 625, 1);
        setBackground("l.homepage.png");
        persiapan();
    }

    /**
     * Menambahkan tombol dan dekorasi awal ke dalam world.
     */
    private void persiapan() {
        // Tambahkan tombol 
        TombolPlay tombolMulai = new TombolPlay();
        addObject(tombolMulai, getWidth() / 2, getHeight() / 2 + 200);
        addObject(new TombolAbout(), getWidth() / 2 +130, getHeight() / 2 + 200);
        addObject(new TombolHowToPlay(), getWidth() / 2 -130, getHeight() / 2 + 200);
        addObject(new TombolQuit(), getWidth() / 2, getHeight() / 2 + 270);


        // Tambahkan hero, hanya hiasan
        Pahlawan hero = new Pahlawan();
        addObject(hero, 0, 0);
        hero.aturPosisiAwal(300, 450);
        hero.aturUkuran(130, 130);
    }

    // musik saat tombol RUN ditekan
    public void started() {
        if (!musikBeranda.isPlaying()) {
            musikBeranda.playLoop();
        }
    }

    // musik berhenti saat game dijeda atau direset
    public void stopped() {
        musikBeranda.stop();
    }

    //berpindah world untuk menghentikan musik
    public static void berhentiMusik() {
        musikBeranda.stop();
    }
}
