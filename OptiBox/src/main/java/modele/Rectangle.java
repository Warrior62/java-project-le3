/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author jules
 */
public class Rectangle {
    
    private int xi,yi,xf,yf;
    private Color c;
    
    
     public Rectangle(int x1,int y1,int x2,int y2,Color couleur){
        this.xi=x1;
        this.yi=y1;
        this.xf=x2;
        this.yf=y2;
        this.c=couleur;
    }
    
    
    
    public void seDessiner(Graphics g) {
      g.setColor(this.c);
      g.drawRect(Math.min(xi, xf), Math.min(yi, yf), Math.abs(xi - xf), Math.abs(yi - yf));  
    }
  

    
}
