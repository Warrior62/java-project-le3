/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.JPanel;
import modele.Rectangle;

/**
 *
 * @author tryla
 */
public class ZoneGraphique extends JPanel{
    
    private Rectangle rect;

    public ZoneGraphique(Rectangle r) {
        this.rect = r;
        this.setBackground(Color.GREEN);
        Graphics g = this.getGraphics();
        r.paintComponents(g);
=======
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author jules
 */
public class ZoneGraphique extends JPanel {
    
    
    private void dessin(){
        
    Graphics g = this.getGraphics();
    
>>>>>>> origin/agathe
    }
    
}