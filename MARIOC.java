import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
/**
 * Write a description of class MARIOC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MARIOC extends World
{

    /**
     * Constructor for objects of class MARIOC.
     * 
     */
      protected char[][] maCarte = new char[12][12];
      private char[] maCarteChar = new char[144];
      protected   char obj = '0';
      protected mario m = new mario();
    public MARIOC()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(12, 12, 34); 
    }
    
    public void RemplirMan(){
     //Remplir l'écran à zéro mannuellement en cas ou le fichier Mouvement.txt n'existe pas 
     for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                addObject(new mur(),i,j);                
                 if(i==5 && j<3 || i==6 && j<7 || j==1 && (i>4 && i<11) || j==4 && (i>=0 && i<8) 
                   || j==8 && (i>=4 && i<11) || j==7 && (i>8 && i<11) || i==9 && (j>0 && j<9)
                   || i==4 && (j>1 && j<7) || j==5 && (i>6 && i<9) || j==2 && i==10 || j==6 && i==5
                   || j==3 && i<3 || j==5 && (i>0 && i<3)
                ){
                List<mur> b = getObjectsAt( i,  j, mur.class);
                removeObjects(b);  
            }
            
            if(i==4 && j==5 || i==4 && j==9 || i==6 && j==9)
                addObject(new caisse(),j,i);
                        
            if(i==3 && j>0 && j<3 || i==8 && j==4 )
               addObject(new objectif(),j,i);
                
            if(i==1 && j==5)
               addObject(m,j,i);          
            }
        }
        //Remplir la matrice maCarte
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
        //enregistrer la matrice dans le fichier Mouvement.txt
        try{
        File ff = new File("Mouvement.txt");
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
    
    public void RemplirEcran()  {
        /*récupérer les positions des objets apartir du fichier Mouvement.txt et les enregistrer dans
          le tableau maCarteChar
        */
        try{
          File ff = new File("Mouvement.txt");
          FileReader fichieralire;    
          fichieralire = new FileReader(ff); 
          try{
          fichieralire.read(maCarteChar);
          fichieralire.close();
            }
        catch(IOException e){} 
         } 
          catch (FileNotFoundException e) {
            //en cas ou le fichier Mouvement.txt n'existe pas
            RemplirMan();
        }
        
        //copier les valeurs du tableau maCarteChar vers la matrice maCarte
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){                
                maCarte[i][j] = maCarteChar[i * 12 + j];
             }
            }
        
        //Remplir l'écran apartir de la matrice maCarte
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                
                  if(maCarte[i][j]=='1')
                  addObject(new mur(),i,j);
                  if(maCarte[i][j]=='2')
                  addObject(new caisse(),i,j);
                  if(maCarte[i][j]=='3')
                  addObject(new objectif(),i,j);
                  if(maCarte[i][j]=='4')
                  addObject(m,i,j);
                 
            }
        }
    }
}
