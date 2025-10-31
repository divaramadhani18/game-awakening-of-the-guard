import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Level1 extends World
{

    private GreenfootSound musikLatar = new GreenfootSound("hompage.mp3");
    private int timerSpawn = 0;
    private int jedaSpawn = 90;
    private Pahlawan hero;
    private Skor skor;
    private Nyawa nyawa;

    public Level1() {
        super(1125, 625, 1);
        setBackground("latar1.png");

        hero = new Pahlawan();
        addObject(hero, 300, 450);
        hero.aturUkuran(130, 130);

        skor = new Skor();
        addObject(skor, 1000, 50);

        nyawa = new Nyawa();
        addObject(nyawa, 100, 50);
        
        setPaintOrder(Pahlawan.class, Musuh.class);
    }

    public void act() {
        if (!musikLatar.isPlaying())
            musikLatar.playLoop();
        spawnMusuh();
        cekPindahLevel();
    }

    private void spawnMusuh() {
        timerSpawn++;
        if (timerSpawn >= jedaSpawn) {
            timerSpawn = 0;
            int jenis = Greenfoot.getRandomNumber(2); // 0 = Plant, 1 = Slime1
            Musuh musuh;
            if (jenis == 0) musuh = new Plant();
            else musuh = new Slime1();

            int y = Greenfoot.getRandomNumber(360) + 250; // rentang 250–610
            addObject(musuh, getWidth() - 10, y);
        }
    }

    public void kurangiNyawa() {
        nyawa.kurangi();
        if (nyawa.getNyawa() <= 0)
        {
            musikLatar.stop();
            Greenfoot.setWorld(new GameOver());
        }
    }

    public void tambahSkor(int nilai) {
        skor.tambah(nilai);
    }

    public Pahlawan getHero() {
        return hero;
    }

    private void cekPindahLevel() {
    
        if (getSkorValue() >= 100) {
            efekTransisi();
            musikLatar.stop();
            Greenfoot.delay(50); // jeda singkat efek
            Greenfoot.playSound("complete level.wav");
            Greenfoot.setWorld(new Level2(hero, skor, nyawa)); 
        }
    }

    private int getSkorValue() {
        try {
            java.lang.reflect.Field field = Skor.class.getDeclaredField("skor");
            field.setAccessible(true);
            return field.getInt(skor);
        } catch (Exception e) {
            return 0;
        }
    }

    private void efekTransisi() {
        GreenfootImage fade = new GreenfootImage(getWidth(), getHeight());
        for (int i = 0; i < 255; i += 10) {
            fade.setColor(new Color(0, 0, 0, i));
            fade.fillRect(0, 0, getWidth(), getHeight());
            getBackground().drawImage(fade, 0, 0);
            Greenfoot.delay(1);
        }
    }

}
