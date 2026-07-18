import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.sql.*;
/**
 * Write a description of class Datenbankanbindung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Datenbankanbindung extends Actor
{
    Connection verbindung; //Deklarieren eines Attributs der Klasse Connection
    snakehead head;//Deklarieren eines Attributs der Klasse snakehead: Kopf der Schlange
    int score;//Deklarieren eines Attributs des Datentyps int 
    public Datenbankanbindung(snakehead headneu)
    {
        head = headneu; //Wertzuweisung
        score = head.ScoreGeben();//Wertzuweisung
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //Vorarbeit für die Verbindung zur Datenbank
            verbindung = DriverManager.getConnection("host","pw","user"); //Link, Benutzername und Passwort werden an Verbindung übergeben
        }
        catch (Exception ex)
        {
             System.out.println("Exception: " +ex.getMessage());
            
        }
        neuerScore(score); //Methodenaufruf
        Highscores(); //Methodenaufruf
    }  

    public void Highscores()//gibt die tabelle highscores aus
    {
      
        try
         {
            Statement s  = verbindung.createStatement();
            ResultSet results = s.executeQuery("SELECT * FROM Highscores ORDER BY SCORE DESC LIMIT 10"); //Hier ist die SQL-Abfrage 
            int platzMomentan = 1;
            while(results.next())
            {
                int bnr=results.getInt("Platz");
                int bnv=results.getInt("Score");
                System.out.println("Platz: "+platzMomentan+" Score: "+bnv);
                platzMomentan++;
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Exception: " +ex.getMessage());
            System.out.println("SQLState: " +ex.getSQLState());
            System.out.println("VendorError: " +ex.getErrorCode());
        }
    }
    
    public void neuerScore(int score) //Hier wird der Score des Spielers in die Tabelle eingefügt
     {
     
        try
        {
            Statement s = verbindung.createStatement(); // Eine Verbindung zur Datenbank wird hergestellt
            s.executeUpdate("INSERT INTO Highscores (Platz,Score) VALUES (1,"+score+")"); // Der Score und immer der Platz 1 wird in die Datenbanktabelle eingefügt 
        }
         catch (SQLException ex)
        {
            System.out.println("Exception: " +ex.getMessage());
            System.out.println("SQLState: " +ex.getSQLState());
            System.out.println("VendorError: " +ex.getErrorCode());
        }
     }
    
}

