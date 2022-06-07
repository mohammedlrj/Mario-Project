import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
/**
 * Write a description of class editor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class editor extends MARIOC
{

    /**
     * Constructor for objects of class editor.
     * 
     */
    
    private int objetActuel=1;
   
    public editor()
    {    
        //Remplir l'écran par l'ancien niveau
        RemplirEcran();
         
    }
    
    public void act(){
        
        chooseObject(); //choisir l'objet actuel
        
        int mx=1;
        int my=1;
       MouseInfo mouse = Greenfoot.getMouseInfo();
       if(mouse!=null){ 
         if(mouse.getButton() == 1 )  { //button gauche de sourie
               mx = mouse.getX();
               my = mouse.getY();   
               if(objetActuel == 1) { //mur 
                   //on supprime l'objet qui se trouve dans la position choisie
                List<Mouvement> b = getObjectsAt( mx,  my, Mouvement.class);
                removeObjects(b);
                   //on ajoute l'objet mur dans cette position
                addObject(new mur(),mx,my);
            }
                if(objetActuel == 2)  { //caisse
                List<Mouvement> b = getObjectsAt( mx,  my, Mouvement.class);
                removeObjects(b);
                addObject(new caisse(),mx,my);
            }
                if(objetActuel == 3) { //objectif                    
                List<Mouvement> b = getObjectsAt( mx,  my, Mouvement.class);
                removeObjects(b);
                addObject(new objectif(),mx,my);
            }
                if(objetActuel == 4) { //mario 
                List<Mouvement> bb = getObjectsAt( mx,  my, Mouvement.class);
                removeObjects(bb);
                //il va falloir d'avoir un seul mario dans lecran
                for(int i=0;i<12;i++){
                  for(int j=0;j<12;j++){
                boolean exist = false;
                List<Mouvement> b = getObjectsAt( i,  j, Mouvement.class);
                for(Mouvement H : b){
                    if(H instanceof mario)
                    removeObject(H);
                }
              }
             }  
                addObject(new mario(),mx,my);
            }
        }
         if(mouse.getButton() == 3 ){ //boutton droit de la sourie
             //on extraire la position de la sourie dans maCarte
             mx = mouse.getX();
             my = mouse.getY();
             //on supprime l'objet choisie
             List<Mouvement> b = getObjectsAt(mx,  my, Mouvement.class);
             removeObjects(b);
        }
       }

    }
    
    public void chooseObject(){
        //on change l'objet actuel
        if(Greenfoot.isKeyDown("1")) //mur
           objetActuel = 1;
        if(Greenfoot.isKeyDown("2")) //caisse
           objetActuel = 2;
        if(Greenfoot.isKeyDown("3")) //objectif
           objetActuel = 3;
        if(Greenfoot.isKeyDown("4")) //mario
           objetActuel = 4;
        if(Greenfoot.isKeyDown("escape") ) //boutton échap
        Greenfoot.setWorld(new menu());
        if(Greenfoot.isKeyDown("s") ){
        /* si le joueur a cliqué sur "s" , on écrasera l'ancien niveau par le nouveau niveau dans 
         * le fichier Indice.txt 
         */
            for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                boolean exist = false;
                List<Mouvement> b = getObjectsAt( i,  j, Mouvement.class);
                for(Mouvement H : b){
                  if(H instanceof mur)
                  obj = '1';
                  if(H instanceof caisse)
                  obj = '2';
                  if(H instanceof objectif)
                  obj = '3';
                  if(H instanceof mario)
                  obj = '4';
                  exist = true;
                  }
                  if(!exist)
                  obj = '0';
                maCarte[i][j] = obj;
            }
        }
        try{
        File ff = new File("Indice.txt");
        boolean a = ff.createNewFile();                
        FileWriter ffw = new FileWriter(ff);
         for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
            ffw.write(String.format("%c",maCarte[i][j]));  
                 }
           }
            ffw.close();
        }       
       catch (Exception e) {}
        
        }
        
        
    }
}
