import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import greenfoot.Color;
/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */




public class Score extends Actor
{
    
    
    snakehead head;        
    
    Score(snakehead headneu)
    {
        head = headneu;
    }
    
    
    public void act() 
    {
        int score = head.ScoreGeben();
        setImage(new GreenfootImage("My Score: " + score, 24, Color.GREEN ,Color.BLACK)); // erstellt in ein Bild mit Schriftgröße, Schriftfarbe und Hintergrundfarbe
        ApfelNeusetzen();
    }   
    
    private void ApfelNeusetzen()
    {
        if(isTouching(Apple.class))// Wenn Score einen Apfel berührt
        {
          removeTouching(Apple.class);// Entfernen des Apfels 
           
          World world = getWorld();// Referenzattribut auf aktuelle Welt wird deklariert
          world.addObject(new Apple(), Greenfoot.getRandomNumber(30), Greenfoot.getRandomNumber(20));// Setzt neuen Apfel auf beliebigen Platz; Begrenzt auf Weltgröße
        }  
    }

}
