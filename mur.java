import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mur extends Mouvement
{
    /**
     * Act - do whatever the mur wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void collision(MyWorld world) 
    {
        //mario ne pourra pas poussé une caisse qui se trouve devant un mur
        world.x -= world.dx;
        world.y -= world.dy;
    }    
}
