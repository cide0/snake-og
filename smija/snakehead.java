import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class snakehead extends Actor
{
    /**
     * Act - do whatever the snake wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int score = 0;// Attribut score zählt, wie viele Äpfel gegessen wurden
    Datenbankanbindung d;

    public void act() 
    {
        // Bewegt sich automatisch um ein Kästchen
        Steuerung();
        RandBerühren();
        ApfelBerühren();
        KörperBerühren();
        SchwanzBerühren();
        move(1);
    }
    
    private void Steuerung()
    {
        if(Greenfoot.isKeyDown("down"))
        {
            if(getRotation()!=270)
            {
            setRotation(90);// Schlangenkopf dreht sich auf 90 Grad 
           }
        }
        
        if(Greenfoot.isKeyDown("right"))
        {
            if(getRotation()!=180)
            {
            setRotation(0);// Schlangenkopf dreht sich auf 0 Grad
           }
        }
        
        if(Greenfoot.isKeyDown("left"))
        {
            if(getRotation()!=0)
            {
            setRotation(180);// Schlangenkopf dreht sich auf 180 Grad
           }
        }
        
        if(Greenfoot.isKeyDown("up"))
        {
            if(getRotation()!=90)
            {
              setRotation(270);// Schlangenkopf dreht sich auf 270 Grad
            }
        }
    }
    
    public boolean ApfelBerühren()
    {
       if(isTouching(Apple.class))// Wenn Schlangenkopf Apfel berührt
        {
          removeTouching(Apple.class);// Berührten Apfel entfernen
          score++;// score wird um eins erhöht

          World world = getWorld();// Referenzattribut auf aktuelle Welt wird deklariert
          world.addObject(new Apple(), Greenfoot.getRandomNumber(30), Greenfoot.getRandomNumber(20));// Setzt neuen Apfel auf beliebigen Platz; Begrenzt auf Weltgröße
          return true;
          
        } 
        return false;
    }
    
    private void RandBerühren()
    {
        if(isAtEdge() == true)// Wenn Schlangenkopf die Weltgrenze berührt
        {
            Greenfoot.stop();// Beenden des Spiels
            System.out.println("Game Over! Apples eaten: " + score);// Ausgabe des scores
            d = new Datenbankanbindung(this);
            score = 0;// Score wird zurückgesetzt
        } 
    }
    
    private void KörperBerühren()
    {
        if(isTouching(body.class))// Wenn Schlangenkopf ein Körperteil berührt
        {
            Greenfoot.stop();// Beenden des Spiels
            System.out.println("Game Over! Apples eaten: " + score);// Ausgabe des scores
            d = new Datenbankanbindung(this);
            score = 0;// Score wird zurückgesetzt
        }
    }
    
    private void SchwanzBerühren()
    {
        if(isTouching(tail.class))// Wenn Schlangenkopf den Schwanz berührt
        {
            Greenfoot.stop();// Beenden des Spiels
            System.out.println("Game Over! Apples eaten: " + score);// Ausgabe des scores
            d = new Datenbankanbindung(this);
            score = 0;// Score wird zurückgesetzt
        }
    
    }
    
    public int XPosGeben()
    {
        int xPos = getX();
        return xPos;
    }
    
    public int YPosGeben()
    {
        int yPos = getY();
        return yPos;
    }
    
    int ScoreGeben()
    {
       return score; 
    }

   
}
