import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends World
{
    private GreenfootSound musikLatar = new GreenfootSound("hompage.mp3");
    private int timerSpawn = 0;
    private int jedaSpawn = 85;
    private Pahlawan hero;
    private Skor skor;
    private Nyawa nyawa;
    private int timerPeti = 0;

    public Level2(Pahlawan heroSebelumnya, Skor skorSebelumnya, Nyawa nyawaSebelumnya) {
        super(1125, 625, 1);
        setBackground("latar2.png");

        hero = heroSebelumnya;
        skor = skorSebelumnya;
        nyawa = nyawaSebelumnya;

        addObject(hero, 300, 450);
        addObject(skor, 1000, 50);
        addObject(nyawa, 100, 50);
        
         setPaintOrder(Pahlawan.class, Musuh.class);
    }

    public void act() {
        if (!musikLatar.isPlaying())
            musikLatar.playLoop();
        spawnMusuh();
        spawnPeti();
        cekPindahLevel();
    }

    private void spawnMusuh() {
        timerSpawn++;
        if (timerSpawn >= jedaSpawn) {
            timerSpawn = 0;
            int jenis = Greenfoot.getRandomNumber(4); // 0=Plant,1=Slime1,2=Slime2,3=Goblin
            Musuh musuh;

            switch (jenis) {
                case 0 -> musuh = new Plant();
                case 1 -> musuh = new Slime1();
                case 2 -> musuh = new Slime2();
                default -> musuh = new Goblin();
            }

            int y = Greenfoot.getRandomNumber(360) + 253;
            addObject(musuh, getWidth() - 10, y);
        }
    }

    private void spawnPeti() {
        timerPeti++;
        if (timerPeti == 400) { // setelah beberapa waktu
            int y = Greenfoot.getRandomNumber(360) + 253;
            int x = Greenfoot.getRandomNumber(600) + 400;
            addObject(new Peti(), x, y);
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

    public void kurangiNyawa(int jumlah) {
        for (int i = 0; i < jumlah; i++) kurangiNyawa();
    }

    public void tambahSkor(int nilai) {
        skor.tambah(nilai);
    }
    
    public void tambahNyawa(int jumlah) {
    if (jumlah <= 0) return;
    if (nyawa != null) nyawa.tambah(jumlah);
    }


    public Pahlawan getHero() {
        return hero;
    }
    
    private void cekPindahLevel() {
        // Saat skor mencapai 100, pindah ke Level2 dengan efek transisi
        if (getSkorValue() >= 300) {
            efekTransisi();
            musikLatar.stop();
            Greenfoot.delay(50); // jeda singkat efek
            Greenfoot.playSound("complete level.wav");
            Greenfoot.setWorld(new Level3(hero, skor, nyawa)); 
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

