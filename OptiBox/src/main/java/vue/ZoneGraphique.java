/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

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

    public ZoneGraphique(Rectangle r) {
        Graphics g = this.getGraphics();
        r.seDessiner(g);
    }
    
}
