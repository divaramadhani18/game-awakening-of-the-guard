import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    public GameOver()
    {
        super(1125, 625, 1);
        setBackground("l.gameover.png");
        Greenfoot.playSound("GameOver.wav");
        addObject(new TombolHome(), 80,50);
        tampilkanSkor();
    }
    
    private void tampilkanSkor(){
        int nilaiAkhir = Skor.skorTerakhir;
        GreenfootImage teksSkor = new GreenfootImage("Skor: " +nilaiAkhir,50, Color.YELLOW, new Color(0,0,0,0));
        getBackground().drawImage(teksSkor,485, getHeight()/2 + 200);
        
        
    }

}
