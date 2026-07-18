import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Abschluss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tail extends Actor
{
    snakehead head;
    body [] body;
    int counter = 0;
    
    
    tail(snakehead headneu)
    {
        head = headneu;
        body = new body[599];

    }

    public void act() 
    {
        counter++;
        KopfFolgen();
        SpielzugAuswerten();
        BodyKopfFolgen();
        BodyBodyFolgen();
        TailAufrücken();
        move(1);
    }
    

    
    public void KopfFolgen() // sorgt dafür, dass der tail die Koordinaten und Rotation des Kopfes erhält und sich ihnen anpasst
    {
        int rotation = head.getRotation();
        setRotation(rotation);
        
        int XPos = head.XPosGeben();
        int YPos = head.YPosGeben();
        
        setLocation(XPos - 2, YPos);
        
        if(head.getRotation() == 90)
        {
            setLocation(XPos, YPos - 2);
        }
        
        if(head.getRotation() == 180)
        {
            setLocation(XPos + 2, YPos);
        }
        
        if(head.getRotation() == 270)
        {
            setLocation(XPos, YPos + 2);
        }
    }
    
    void KörperteilHinzufügen()//fügt ein körperteil abhängig von der Rotation des Kopfes hinzu
    {
         int score = head.ScoreGeben();  
         World world = getWorld(); 
         body[score - 1] = new body(head);

          if (getRotation() == 0)
          {
              world.addObject(body[score - 1], head.getX()-1, head.getY()); 
          }
          
          if (getRotation() == 90)
          {
              world.addObject(body[score - 1], head.getX(), head.getY()-1); 
          }
          
          if (getRotation() == 180)
          {
              world.addObject(body[score - 1], head.getX()+1, head.getY()); 
          }
          
          if (getRotation() == 270)
          {
              world.addObject(body[score - 1], head.getX(), head.getY()+1);
              
          }
        
          
          

    }
                
    void SpielzugAuswerten()// wenn ein apfel vom Kopf berührt wurde, wird ein körperteil hinzugefügt
    {
        if(head.ApfelBerühren() == true)
        {
            KörperteilHinzufügen();
        }
    }
    
    public void BodyKopfFolgen()// regelt die Bewegungen des ersten Körperteils, welches dem kopf folgt
    {
        int score = head.ScoreGeben();

        if (score == 1)
        {
        int rotation1 = head.getRotation();
        int XPos1 = head.XPosGeben();
        int YPos1 = head.YPosGeben();
        
        body[0].setRotation(rotation1);
        
        
        if(head.getRotation() == 0)
        {
            body[0].setLocation(XPos1 - 2 , YPos1);

        }
        
        if(head.getRotation() == 90)
        {
            body[0].setLocation(XPos1, YPos1 - 2);

        }
        
        if(head.getRotation() == 180)
        {
            body[0].setLocation(XPos1 + 2, YPos1);

        }
        
        if(head.getRotation() == 270)
        {
            body[0].setLocation(XPos1, YPos1 + 2);

        }
        }
    

    }
    
    void BodyBodyFolgen() // regelt die Bewegung für alle Körperteile sobald mehr als ein körperteil existiert
    {
        int score = head.ScoreGeben();
       
        if(score > 1)
        {
                  
        
        int rotation1 = head.getRotation();
        int XPos1 = head.XPosGeben();
        int YPos1 = head.YPosGeben();
        
        body[0].setRotation(rotation1);//das erste körperteil folgt nach wie vor dem kopf
        
        
        if(head.getRotation() == 0)
        {
            body[0].setLocation(XPos1 - 2 , YPos1);
        }
        
        if(head.getRotation() == 90)
        {
            body[0].setLocation(XPos1, YPos1 - 2);
        }
        
        if(head.getRotation() == 180)
        {
            body[0].setLocation(XPos1 + 2, YPos1);
        }
        
        if(head.getRotation() == 270)
        {
            body[0].setLocation(XPos1, YPos1 + 2);
        }
        

        for(int i = 1; i <= score; i++)//die anderen körperteile folgen eig ihrem vorgänger, damit jedoch auch indirekt dem kopf, also können auch hier die kopfdaten direkt übergeben werden
        {
        int rotation = head.getRotation();
        int XPos = head.getX();
        int YPos = head.getY();
        
        
        body[i - 1].setRotation(rotation);
        
        if(rotation == 0)
        {
            body[i - 1].setLocation(XPos - (1+i), YPos);
        }
        
        if(rotation == 90)
        {
            body[i - 1].setLocation(XPos, YPos - (1+i));
        }
        
        if(rotation == 180)
        {
            body[i - 1].setLocation(XPos + (1+i), YPos);
        }
        
        if(rotation == 270)
        {
            body[i - 1].setLocation(XPos, YPos + (1+i));
        }
        
        }
    
        
        }
    }
    
    void TailAufrücken()//immer wenn die schlange um ein körperteil wächst, rückt der tail um eins nach hinten
    {
        int score = head.ScoreGeben();
        if(score > 0)
        {
        int rotation = head.getRotation();
        setRotation(rotation);
        
        int XPos = head.XPosGeben();
        int YPos = head.YPosGeben();
        
        setLocation(XPos - (2 + score), YPos);
        
        if(head.getRotation() == 90)
        {
            setLocation(XPos, YPos - (2 + score));
        }
        
        if(head.getRotation() == 180)
        {
            setLocation(XPos + (2 + score), YPos);
        }
        
        if(head.getRotation() == 270)
        {
            setLocation(XPos, YPos + (2 + score));
        }
        }
    }

}
    
    
    

