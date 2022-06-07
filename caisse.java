import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class caisse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class caisse extends Mouvement
{
    /**
     * Act - do whatever the caisse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static boolean b = false;
    public void collision(MyWorld world) 
    {
        boolean a = true ;
        //traitement pour que mario ne pourra pas poussé 2 caisse ou un caisse devant un mur
        int posX = world.x+(world.dx) , posY = world.y+(world.dy);
        List<Mouvement> inc = world.getObjectsAt(posX,posY,Mouvement.class);
        for(Mouvement H : inc){
        if(H instanceof caisse || H instanceof mur){
        world.x -= world.dx;
        world.y -= world.dy;
        a = false;
         }                   
        }
        /*
         * traitement pour que mario ne pourra pas prendre la position d'une caisse qui se trouve 
         * au bordures
         */ 
        if( posX == -1 || posY == -1 || posX == 12 || posY == 12){
        world.x -= world.dx;
        world.y -= world.dy;
        a = false;
         } 
        
        /*
         * a = true signifie que la caisse qui est en mouvement n'est pas devant une autre caisse ou
         * un mur ou au bordures, donc on peut poussé la caisse
         * 
         */
        if(a){
        int x = this.getX();
        int y = this.getY();
        world.x = x;
        world.y = y;
        x+=world.dx;
        y+=world.dy;
        this.setLocation(x,y);
        Greenfoot.playSound("moveBox.wav");
        List<Mouvement> win = world.getObjectsAt(x,y,Mouvement.class);
        if(win.size() == 2){ // si la case contient 2 objets, alors nous serons sûrs que le 2eme objet est une objectif         
        this.setImage("caisse_ok.jpg");
        Greenfoot.playSound("validBox.wav");
        b=true;
        for(Mouvement H : win){
            if(H instanceof objectif && x==4 && y==8)
               world.removeObject(H);
            }
        }
        else
        this.setImage("caisse.jpg"); //si mario a changé la position d'une caisse qui était à la position d'une objectif
        
        }
       
    }
}
