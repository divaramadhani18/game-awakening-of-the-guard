import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HowToPlayPage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HowToPlayPage extends World
{
    private int halaman = 1; // mulai dari halaman pertama
    private GreenfootImage[] bg; // background untuk tiap halaman

    public HowToPlayPage() {
        super(1125, 625, 1);
        siapkanGambar();
        tampilkanHalaman();
        tambahTombol();
    }

    private void siapkanGambar() {
        bg = new GreenfootImage[3];
        for (int i = 0; i < 3; i++) {
            bg[i] = new GreenfootImage("htp" + (i + 1) + ".png"); 
            bg[i].scale(getWidth(), getHeight()); 
        }
    }

    private void tampilkanHalaman() {
        setBackground(bg[halaman - 1]);
    }

    private void tambahTombol() {
        addObject(new TombolNext(this), 1000, 564);
        addObject(new TombolBack(this), 100, 564);
        addObject(new TombolHome(), getWidth()/2, getHeight()/2+250);
    }
    


    public void nextHalaman() {
        if (halaman < 3) {
            halaman++;
            tampilkanHalaman();
        }
    }

    public void prevHalaman() {
        if (halaman > 1) {
            halaman--;
            tampilkanHalaman();
        }
    }
}

