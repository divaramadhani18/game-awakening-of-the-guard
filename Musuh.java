import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musuh here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Musuh extends Actor
{
    protected int kecepatan = 2;
    protected int nilaiSkor;
    protected GreenfootImage[] jalan;
    protected GreenfootImage[] mati;
    protected int frame = 0;
    protected boolean sedangMati = false;
    protected int penghitungMati = 0;

    protected int penghitungFrame = 0;
    protected int kecepatanAnimasi = 8; // makin besar makin lambat animasi

    public void act()
    {
        if (!sedangMati)
        {
            gerak();
            animasiJalan();
            cekTepi();
            cekTertabrakHero();
        }
        else
        {
            animasiMati();
        }
    }

    protected void gerak()
    {
        setLocation(getX() - kecepatan, getY());
    }

    protected void cekTepi()
    {
        if (getX() <= 0)
        {
            if (getWorld() instanceof Level1)
                ((Level1)getWorld()).kurangiNyawa();
            else if (getWorld() instanceof Level2)
                ((Level2)getWorld()).kurangiNyawa();
             else if (getWorld() instanceof Level3)
                ((Level3)getWorld()).kurangiNyawa();

            getWorld().removeObject(this);
        }
    }

    // Deteksi bila hero menyerang dan berada dekat ->  onHitByHero()
    protected void cekTertabrakHero()
    {
        if (getWorld() == null) return;

        Pahlawan hero = null;

        if (getWorld() instanceof Level1) {
            hero = ((Level1)getWorld()).getHero();
        } 
        else if (getWorld() instanceof Level2) {
            hero = ((Level2)getWorld()).getHero();
        }
         else if (getWorld() instanceof Level3) {
            hero = ((Level3)getWorld()).getHero();
        }

        if (hero == null) return;

       
        if (hero.sedangMenyerang && Math.abs(getX() - hero.getX()) < 80 && Math.abs(getY() - hero.getY()) < 60)
        {
            onHitByHero(hero);
        }
    }


    protected void onHitByHero(Pahlawan hero)
    {
        if (sedangMati) return;
        sedangMati = true;

        if (getWorld() instanceof Level1)
            ((Level1)getWorld()).tambahSkor(nilaiSkor);
        else if (getWorld() instanceof Level2)
            ((Level2)getWorld()).tambahSkor(nilaiSkor);
        else if (getWorld() instanceof Level3)
            ((Level3)getWorld()).tambahSkor(nilaiSkor);
    }

    protected void animasiJalan()
    {
      penghitungFrame++;
      if (penghitungFrame % kecepatanAnimasi == 0)
      {
        frame++;
        if (frame >= jalan.length) frame = 0;
        setImage(jalan[frame]);
      }
    }
    

    protected void animasiMati()
    {
        if (penghitungMati < mati.length)
        {
            setImage(mati[penghitungMati]);
            penghitungMati++;
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}
