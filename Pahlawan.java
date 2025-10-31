import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pahlawan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Pahlawan extends AktorAnimasi implements Serangable
{
    private int delaySerang = 20;   // jumlah frame delay antar serangan
    private int timerSerang = 0;    // penghitung sisa cooldown   
    
    private int kecepatan = 4;
    private String arah = "kanan";
    public boolean sedangMenyerang = false;
    private boolean arahKanan=true;

    // Semua animasi hero
    private GreenfootImage[] jalanKanan;
    private GreenfootImage[] jalanKiri;
    private GreenfootImage[] serangKanan;
    private GreenfootImage[] serangKiri;
    private GreenfootImage[] idleKanan;
    private GreenfootImage[] idleKiri;

    private int penghitungSerang = 0;
    private int penghitungIdle = 0;

    // Ukuran untuk semua frame
    private int lebarGambar = 80;
    private int tinggiGambar = 80;
    
    //sound
    public static GreenfootSound musikSerang = new GreenfootSound("serang.wav");
    private boolean suaraSudahDiputar = false;
    
    //terkena serang
    private boolean sedangTerkena = false;
    private int penghitungTerkena = 0;
    private int durasiTerkena = 0; // frame durasi animasi kena
    private boolean sedangMati = false;
    private int penghitungMati = 0;
    private int durasiAnimMati = 60; // jumlah frame animasi mati

    //terkena serang
    private GreenfootImage[] kenaKanan;
    private GreenfootImage[] kenaKiri;
    private boolean sedangKena = false;
    private int penghitungKena = 0;
    public static GreenfootSound suaraKena = new GreenfootSound("darah.mp3");
    
    //air
    private int sisaTembakanAir =0;
    private boolean tombolPTerlepas= true;//agar tidak spam tembakan
    

    public Pahlawan()
    {
        //  animasi hero
        
        jalanKanan = muatAnimasi("hr.run", 8);
        jalanKiri  = muatAnimasi("hl.run", 8);
        serangKanan = muatAnimasi("hr.run.attack", 8);
        serangKiri  = muatAnimasi("hl.run.attack", 8);
        idleKanan = muatAnimasi("hr.idle", 12);
        idleKiri  = muatAnimasi("hl.idle", 12);
        kenaKanan= muatAnimasi("hr.hurt",5);
        kenaKiri= muatAnimasi("hl.hurt",5);
        
    
        // agar animasi tidak berkedip
        samakanUkuranSemuaFrame();

        // Gambar awal
        setImage(idleKanan[1]);
    }

    private GreenfootImage[] muatAnimasi(String namaFile, int jumlah)
    {
         GreenfootImage[] temp = new GreenfootImage[jumlah];
    for (int i = 1; i <= jumlah; i++)  // mulai dari 1
    {
        String file = namaFile + i + ".png";
        temp[i-1] = new GreenfootImage(file);

        if (temp[i-1] == null)
        {
            System.out.println("Gagal load: " + file);
        }
    }
    return temp;
    }

    public void act()
    {   
        cekAmbilKolam();
       
        if (sedangMenyerang)
        {
            animasiSerang();
            
        }
        else 
        {
            gerak();
        }
    }
   

    private void gerak()
    {
        int x = getX();
    int y = getY();

    boolean bergerak = false;

    // ===== GERAK KE KIRI =====
    if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))
    {
        x -= kecepatan;
        arahKanan = false;
        arah= "kiri";
        animasiBerjalan(jalanKiri);
        bergerak = true;
    }

    // ===== GERAK KE KANAN =====
    if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))
    {
        x += kecepatan;
        arahKanan = true;
        arah= "kanan";
        animasiBerjalan(jalanKanan);
        bergerak = true;
    }

    // ===== GERAK KE ATAS =====
    if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up"))
    {
        if (y > 253) y -= kecepatan; // batas atas
        // gunakan animasi sesuai arah terakhir hero
        if (arahKanan)
            animasiBerjalan(jalanKanan);
        else
            animasiBerjalan(jalanKiri);
        bergerak = true;
    }

    // ===== GERAK KE BAWAH =====
    if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down"))
    {
        if (y < 622) y += kecepatan; // batas bawah
        if (arahKanan)
            animasiBerjalan(jalanKanan);
        else
            animasiBerjalan(jalanKiri);
        bergerak = true;
    }

    // ===== ATUR POSISI HERO =====
    setLocation(x, y);

    // ===== JIKA TIDAK BERGERAK (IDLE) =====
    if (!bergerak && !sedangMenyerang)
    {
        if (arahKanan)
            animasiBerjalan(idleKanan);
        else
            animasiBerjalan(idleKiri);
    }

    // ===== CEK SERANGAN =====
    if (Greenfoot.isKeyDown("space") && timerSerang <= 0)
    {
        serang();
    }
    
    if (Greenfoot.isKeyDown("z")) {
        if (tombolPTerlepas && sisaTembakanAir > 0) {
            tombolPTerlepas = false;
            boolean arah = arahKanan;
            int spawnX = getX() + (arah ? 50 : -50);
            int spawnY = getY(); 
            getWorld().addObject(new BolaAir(arah), spawnX, spawnY);
            sisaTembakanAir--;
           
        }
    } else {
        tombolPTerlepas = true;
    }

    // ===== TIMER SERANG =====
    if (timerSerang > 0) timerSerang--;

    }

    private void animasiIdle()
    {
        penghitungIdle++;
        if (arah.equals("kanan"))
        {
            if (penghitungIdle >= delayAnimasi)
            {
                penghitungIdle = 0;
                indeksAnimasi = (indeksAnimasi + 1) % idleKanan.length;
                setImage(idleKanan[indeksAnimasi]);
            }
        }
        else
        {
            if (penghitungIdle >= delayAnimasi)
            {
                penghitungIdle = 0;
                indeksAnimasi = (indeksAnimasi + 1) % idleKiri.length;
                setImage(idleKiri[indeksAnimasi]);
            }
        }
    }

    private void animasiSerang()
    {
        penghitungSerang++;

        if (arah.equals("kanan"))
        {
            int indeks = (penghitungSerang / 6) % serangKanan.length;
            setImage(serangKanan[indeks]);
        }
        else
        {
            int indeks = (penghitungSerang / 6) % serangKiri.length;
            setImage(serangKiri[indeks]);
        }
        
         if (penghitungSerang % 10 == 0) // setiap beberapa frame serangan
        {
        Actor goblin = getOneObjectAtOffset(arahKanan ? 50 : -50, 0, Goblin.class);
        if (goblin != null)
        {
            ((Goblin)goblin).terkenaSerangan(); // method baru yang kita buat di Goblin
        }
       }
       
       if (penghitungSerang % 10 == 0) // setiap beberapa frame serangan
        {
        Actor vampire = getOneObjectAtOffset(arahKanan ? 50 : -50, 0, Vampire.class);
        if (vampire != null)
        {
            ((Vampire)vampire).terkenaSerangan(); // method baru yang kita buat di vampire
        }
       }

        if (penghitungSerang >= serangKanan.length * 6)
        {
            sedangMenyerang = false;
            penghitungSerang = 0;
        }
    }
    
    @Override
    public void serang() {
    if (timerSerang <= 0) {
        sedangMenyerang = true;
        penghitungSerang = 0;
        timerSerang = delaySerang;
        musikSerang.play();
    }
}

    public void terkenaSerangan() {
    if (!sedangKena) {
        sedangKena = true;
        penghitungKena = 0;
        suaraKena.play();
    }
   }
   
   public void aktifkanSkillBolaAir() {
     sisaTembakanAir = 3;
   
   }
   
   private void cekAmbilKolam() {
    Kolam kolam = (Kolam)getOneIntersectingObject(Kolam.class);
    if (kolam != null) {
        getWorld().removeObject(kolam);
        sisaTembakanAir = 3; // isi ulang 3 peluru
        Greenfoot.playSound("airskill.wav");
    }
}

    // 🧭 Set posisi awal
    public void aturPosisiAwal(int x, int y)
    {
        setLocation(x, y);
    }

    //Ubah ukuran hero 
    public void aturUkuran(int lebar, int tinggi)
    {
        this.lebarGambar = lebar;
        this.tinggiGambar = tinggi;
        samakanUkuranSemuaFrame();
    }


    private void samakanUkuranSemuaFrame()
    {
        ubahUkuranArray(jalanKanan);
        ubahUkuranArray(jalanKiri);
        ubahUkuranArray(serangKanan);
        ubahUkuranArray(serangKiri);
        ubahUkuranArray(idleKanan);
        ubahUkuranArray(idleKiri);
    }

    private void ubahUkuranArray(GreenfootImage[] gambarArray)
    {
        for (GreenfootImage img : gambarArray)
        {
            img.scale(130, 130);
        }
    }
  
}

