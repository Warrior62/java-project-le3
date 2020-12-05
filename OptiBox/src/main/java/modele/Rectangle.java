/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
=======
import java.awt.Graphics;
>>>>>>> origin/agathe
import javax.swing.JPanel;

/**
 *
 * @author jules
 */
<<<<<<< HEAD
public class Rectangle extends JPanel{

    private int xi,yi,xf,yf;
    private Color c;


=======
public class Rectangle {
    
    private int xi,yi,xf,yf;
    private Color c;
    
    
>>>>>>> origin/agathe
     public Rectangle(int x1,int y1,int x2,int y2,Color couleur){
        this.xi=x1;
        this.yi=y1;
        this.xf=x2;
        this.yf=y2;
        this.c=couleur;
<<<<<<< HEAD
        setPreferredSize(new Dimension(200, 100));
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Rectangle2D.Double(10, 10,20, 25));
    }



}
=======
    }
    
    
    
    public void seDessiner(Graphics g) {
      g.setColor(this.c);
      g.drawRect(Math.min(xi, xf), Math.min(yi, yf), Math.abs(xi - xf), Math.abs(yi - yf));  
    }
  

    
}
>>>>>>> origin/agathe
