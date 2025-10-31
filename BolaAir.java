import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BolaAir here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BolaAir extends AktorAnimasi {
    private int kecepatan = 7;
    private boolean arahKanan;

    public BolaAir(boolean arahKanan) {
        this.arahKanan = arahKanan;
        setAnimasi("water", 20); 
        ubahUkuranAnimasi(100, 100);

        // Kalau arah kiri, gambar dibalik (mirror)
        if (!arahKanan && gambarAnimasi != null) {
            for (GreenfootImage img : gambarAnimasi) {
                img.mirrorHorizontally();
            }
        }
    }

    public void act() {
        if (getWorld() == null) return;
        animasiBerjalan(gambarAnimasi);
        gerak();
        if (getWorld() == null) return;
        cekTabrakan();
    }

    private void gerak() {
        int dx = arahKanan ? kecepatan : -kecepatan;
        setLocation(getX() + dx, getY());

        // Hilang jika keluar batas map
        if (getX() < 5 || getX() > getWorld().getWidth() - 5) {
            getWorld().removeObject(this);
        }
    }

    private void cekTabrakan() {
         if (getWorld() == null) return;
    Actor musuh = getOneIntersectingObject(Musuh.class);
    if (musuh != null) {
        
        int nilai = 0;
        if (musuh instanceof Goblin) nilai = 10;
        else if (musuh instanceof Slime1) nilai = 5;
        else if (musuh instanceof Plant) nilai = 5;
        else if (musuh instanceof Slime2) nilai = 5;
        else if (musuh instanceof Slime3) nilai = 5;
        else if (musuh instanceof Vampire) nilai = 20;
        

        
        if (getWorld() instanceof Level3) {
            Level3 world = (Level3) getWorld();
            world.tambahSkor(nilai);
        }

        
        getWorld().removeObject(musuh);

        //  efek percikan air
        getWorld().addObject(new EfekPercikan(), getX(), getY());
    }

    }

    private void ubahUkuranAnimasi(int w, int h) {
        if (gambarAnimasi != null) {
            for (GreenfootImage img : gambarAnimasi) {
                img.scale(w, h);
            }
        }
    }


    
}