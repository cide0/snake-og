import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class body here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class body extends Actor
{
    /**
     * Act - do whatever the body wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    snakehead head;
    
    
    body(snakehead headneu)
    {
       head = headneu;
    }
    
    public void act() 
    { 
        move(1);
        ApfelNeusetzen();

    }
    

    
    private void ApfelNeusetzen()
    {
        if(isTouching(Apple.class))// Wenn ein Körperteil einen Apfel berührt 
        {
          removeTouching(Apple.class);// Entfernen des Apfels
           
          World world = getWorld();// Referenzattribut auf aktuelle Welt wird deklariert
          world.addObject(new Apple(), Greenfoot.getRandomNumber(29), Greenfoot.getRandomNumber(19));// Setzt neuen Apfel auf beliebigen Platz; Begrenzt auf Weltgröße
        }
    }
    

}
