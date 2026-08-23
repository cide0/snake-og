import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tail here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tail extends Actor
{
    snakehead head;
    body [] body;
    
    // Wir merken uns die Kopf-Position selbst, anstatt uns bei jedem Zug auf head.getX() usw. zu verlassen.
    // Grund: ob head.getX() beim Aufruf schon die neue oder noch die alte Position liefert, hängt davon ab,
    // ob Greenfoot das act() vom Kopf vor oder nach dem des Tails ausführt - das ist nicht garantiert.
    // Mit einem eigenen Zwischenspeicher ist die Kette immer korrekt um genau einen Zug versetzt, unabhängig von dieser Reihenfolge.
    int letzteKopfX;
    int letzteKopfY;
    int letzteKopfRotation;
    
    
    tail(snakehead headneu)
    {
        head = headneu;
        body = new body[599];
        
        letzteKopfX = head.getX();
        letzteKopfY = head.getY();
        letzteKopfRotation = head.getRotation();
    }

    public void act() 
    {
        int scoreVorher = head.ScoreGeben(); // score VOR dem Apfel-Check merken
        SpielzugAuswerten();
        int scoreNachher = head.ScoreGeben(); // score NACH dem Apfel-Check
        
        boolean neuesTeilHinzugefuegt = (scoreNachher > scoreVorher);
        
        KetteFolgen(scoreVorher, neuesTeilHinzugefuegt);
        
        // die Kopf-Position für den nächsten Zug merken - erst jetzt, nachdem sie in diesem Zug verwendet wurde
        letzteKopfX = head.getX();
        letzteKopfY = head.getY();
        letzteKopfRotation = head.getRotation();
    }
    
    
    void KörperteilHinzufügen()//fügt ein körperteil an der aktuellen Position des bisher letzten Kettenglieds hinzu (bzw. eine Zelle hinter dem Kopf, wenn es das erste Körperteil ist)
    {
         int score = head.ScoreGeben();  
         World world = getWorld(); 
         body[score - 1] = new body(head);
         
         int x;
         int y;
         int rotation;
         
         if(score == 1)
         {
             rotation = letzteKopfRotation;
             x = letzteKopfX;
             y = letzteKopfY;
             
             if(rotation == 0)
             {
                 x = x - 1;
             }
             
             if(rotation == 90)
             {
                 y = y - 1;
             }
             
             if(rotation == 180)
             {
                 x = x + 1;
             }
             
             if(rotation == 270)
             {
                 y = y + 1;
             }
         }
         else
         {
             x = body[score - 2].getX();
             y = body[score - 2].getY();
             rotation = body[score - 2].getRotation();
         }

         world.addObject(body[score - 1], x, y);
         body[score - 1].setRotation(rotation);
    }
                
    void SpielzugAuswerten()// wenn ein apfel vom Kopf berührt wurde, wird ein körperteil hinzugefügt
    {
        if(head.ApfelBerühren() == true)
        {
            KörperteilHinzufügen();
        }
    }
    
    // jedes Kettenglied (jedes Körperteil und der Tail) übernimmt die Position und Rotation, die sein Vorgänger VOR diesem Zug hatte.
    // score ist der Stand VOR dem Apfel-Check dieses Zuges, damit ein gerade erst hinzugefügtes Körperteil hier noch nicht mit einbezogen wird
    void KetteFolgen(int score, boolean neuesTeilHinzugefuegt)
                       
    {
        // zuerst die alten (noch unveränderten) Positionen/Rotationen von Kopf und allen (bereits vorher existierenden) Körperteilen sichern
        int[] alteX = new int[score + 1];
        int[] alteY = new int[score + 1];
        int[] alteRotation = new int[score + 1];
        
        alteX[0] = letzteKopfX;
        alteY[0] = letzteKopfY;
        alteRotation[0] = letzteKopfRotation;
        
        for(int i = 0; i < score; i++)
        {
            alteX[i + 1] = body[i].getX();
            alteY[i + 1] = body[i].getY();
            alteRotation[i + 1] = body[i].getRotation();
        }
        
        // jetzt jedes (bereits vorher existierende) Körperteil auf die gesicherte, alte Position seines direkten Vorgängers setzen
        for(int i = 0; i < score; i++)
        {
            body[i].setLocation(alteX[i], alteY[i]);
            body[i].setRotation(alteRotation[i]);
        }
        
        if(neuesTeilHinzugefuegt == false)
        {
            // der Tail übernimmt die alte Position des letzten Körperteils (bzw. des Kopfes, falls noch kein Körperteil existiert)
            setLocation(alteX[score], alteY[score]);
            setRotation(alteRotation[score]);
        }
        // wurde in diesem Zug ein neues Körperteil eingefügt, bleibt der Tail diesen einen Zug lang unverändert stehen:
        // sein neuer Vorgänger ist gerade erst entstanden und hat noch keine "alte" Position, der der Tail folgen könnte.
        // Ab dem nächsten Zug folgt der Tail dann ganz normal dem neuen Körperteil.
    }

}