import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class border here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class border extends Actor
{
    /**
     * Act - do whatever the border wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public void act() 
    {
        ApfelNeusetzen();

    }
    

    
    private void ApfelNeusetzen()
    {
        if(isTouching(Apple.class))// Wenn Weltbegrenzung einen Apfel berührt
        {
          removeTouching(Apple.class);// Entfernen des Apfels 
           
          World world = getWorld();// Referenzattribut auf aktuelle Welt wird deklariert
          world.addObject(new Apple(), Greenfoot.getRandomNumber(30), Greenfoot.getRandomNumber(20));// Setzt neuen Apfel auf beliebigen Platz; Begrenzt auf Weltgröße
        }  
    }
}
