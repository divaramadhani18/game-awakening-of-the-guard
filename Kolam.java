import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kolam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kolam extends AktorAnimasi {
    private int cooldown = 0;
    private boolean tersedia = true;

    public Kolam() {
        setAnimasi("kolam", 24); 
        ubahUkuranAnimasi(120, 100);
    }

    public void act() {
        animasiBerjalan(gambarAnimasi);
        
        if (!tersedia) {
            cooldown--;
            if (cooldown <= 0) {
                tersedia = true;
                getWorld().addObject(new Kolam(), Greenfoot.getRandomNumber(600) + 300,
                        Greenfoot.getRandomNumber(300) + 250);
                getWorld().removeObject(this);
            }
            return;
        }

        // Jika hero menyentuh kolam
        Actor hero = getOneIntersectingObject(Pahlawan.class);
        if (hero != null) {
            Pahlawan p = (Pahlawan)hero;
            p.aktifkanSkillBolaAir();

            //  efek kilau
            EfekSkillAir efek = new EfekSkillAir();
            getWorld().addObject(efek, p.getX(), p.getY() - 80);

            tersedia = false;
            cooldown = 600; // 10 detik cooldown
            getWorld().removeObject(this);
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
