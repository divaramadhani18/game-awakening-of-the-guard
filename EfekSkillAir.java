import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EfekSkillAir here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfekSkillAir extends Actor
{
    private int timer = 60; // tampil selama 1 detik (60 frame)
    private int transparansi = 255;

    public EfekSkillAir() {
        GreenfootImage img = new GreenfootImage("Skill Air Aktif!", 36, Color.CYAN, new Color(0, 0, 0, 0));
        setImage(img);
        Greenfoot.playSound("skill.wav");
    }

    public void act() {
        timer--;
        transparansi -= 4; // efek memudar
        getImage().setTransparency(Math.max(0, transparansi));

        if (timer <= 0) {
            getWorld().removeObject(this);
        }
    }

}
