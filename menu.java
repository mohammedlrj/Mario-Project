import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menu extends MARIOC
{

    /**
     * Constructor for objects of class menu.
     * 
     */
    public menu()
    {            
        
    }
    public void act(){
        if(Greenfoot.isKeyDown("F1") ) //boutton F1 
        Greenfoot.setWorld(new MyWorld()); //le joueur va jouer
        
        if(Greenfoot.isKeyDown("F2") )  //boutton F2
        Greenfoot.setWorld(new editor()); //le joueur va editer le niveau

    }
}
