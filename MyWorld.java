import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends MARIOC
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
      public int dx = 0;
      public int dy = 0;
      public int x;
      public int y;
      
    public MyWorld() 
    {    
        
        Greenfoot.setWorld(new menu()); //pour que le menu s'affiche au début
        
        RemplirEcran(); //si le joueur veut jouer, on doit remplir le niveau par les objets mur ...
      
    }
    
    public void changeDirection(){ //changement de l'état du mario
    if(Greenfoot.isKeyDown("left")){
        dx = -1;
        dy = 0;
        m.setImage("mario_gauche.gif");
    }
    if(Greenfoot.isKeyDown("right") ){
        dx = 1;
        dy = 0;
        m.setImage("mario_droite.gif");       
    }
    if(Greenfoot.isKeyDown("down") ){
        dx=0;
        dy=1;
        m.setImage("mario_bas.gif");
    }
    if(Greenfoot.isKeyDown("up") ){
        dx = 0;
        dy=-1;
        m.setImage("mario_haut.gif");
    }
    
}

public void act()  {
    
    if(Greenfoot.isKeyDown("escape") ) //si le joueur a cliqué sur échape à l'intérieur du jeu, il va retourner au menu
     Greenfoot.setWorld(new menu());
        
    changeDirection();
    
    //éxtraire de la position actuel du mario
    x = m.getX();
    y = m.getY();
    
    //changement de la position du mario en fonction du choix du joueur
    x+=dx;
    y+=dy;
   
    //vérifier si mario a rencontré un autre objet dans sa route
    List<Mouvement> acteur= getObjectsAt(x,y,Mouvement.class);
    for(Mouvement H : acteur)
    H.collision(this);
    
    //rendre mario dans la nouvelle position
    m.setLocation(x,y);
    
    //on arréte mario de bouger
    dx=0;
    dy=0;
}

}
