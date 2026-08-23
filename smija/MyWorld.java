import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(30, 20, 30);// Breite und Weite der Welt; Größe der Pixel
        snakehead s1 = new snakehead();
        addObject(s1, 3, 2);// Erstellt Schlangenkopf; xPos: 3; yPos: 2
        addObject(new tail(s1), 2, 2);
        addObject(new Score(s1), 2, 0);
        addObject(new Apple(), Greenfoot.getRandomNumber(30), Greenfoot.getRandomNumber(20));// Setzt neuen Apfel auf beliebigen Platz; Begrenzt auf Weltgröße
       
        for(int i = 5; i<30; i++)// Setzt die Weltbegrenzung an der oberen x-Leiste
        {
         addObject(new border(), i, 0);
        }
        
        for (int i = 1; i<29; i++)
        {
            addObject(new border(), i, 19);
        }
        
        for(int i = 1; i<20; i++)// Setzt die Weltbegrenzng an der y-Leiste
        {
         addObject(new border(), 0, i);
         addObject(new border(), 29, i);
        }
        

    }   
}

