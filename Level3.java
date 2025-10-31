import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends World
{
    private GreenfootSound musikLatar = new GreenfootSound("hompage.mp3");
    private int timerSpawn = 0;
    private int jedaSpawn = 80;
    private Pahlawan hero;
    private Skor skor;
    private Nyawa nyawa;
    private int timerPeti = 0;
    private int timerKolam = 0;
    

    public Level3(Pahlawan heroSebelumnya, Skor skorSebelumnya, Nyawa nyawaSebelumnya) {
        super(1125, 625, 1);
        setBackground("Level3.png");
    

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
        spawnKolam();
        cekPindahLevel();
    }

    private void spawnMusuh() {
        timerSpawn++;
        if (timerSpawn >= jedaSpawn) {
            timerSpawn = 0;
            int jenis = Greenfoot.getRandomNumber(6); // 0=Plant,1=Slime1,2=Slime2,, 3= slime 3, 4=Goblin
            Musuh musuh;

            switch (jenis) {
                case 0 -> musuh = new Plant();
                case 1 -> musuh = new Slime1();
                case 2 -> musuh = new Slime2();
                case 3 -> musuh = new Slime3();
                case 4 -> musuh = new Goblin();
                default -> musuh = new Vampire();
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
    
    private void spawnKolam() {
    timerKolam++;
    if (timerKolam >= 1000) { // colldown kolam
        timerKolam = 0;
        int y = Greenfoot.getRandomNumber(360) + 253;
        int x = Greenfoot.getRandomNumber(500) + 300;
        addObject(new Kolam(), x, y);
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
        if (getSkorValue() >= 800) {
            efekTransisi();
            Greenfoot.setWorld(new WinMenu());
            musikLatar.stop();
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

   

