/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import modele.Rectangle;

/**
 *
 * @author tryla
 */
public class InstanceView1 extends JFrame{
    private String titre;
    private int largeur, hauteur;
    private ZoneGraphique zg;
    private List<Rectangle> listeRect;

    public InstanceView1(String titre, int largeur, int hauteur, ZoneGraphique zg, Rectangle listeRect) {
        this.titre = titre;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.zg = zg;
        this.listeRect.add(listeRect);
        this.add(zg);
        this.initialisationFenetre(titre, largeur, hauteur);
    }
    
    private void initialisationFenetre(String titre, int largeur, int hauteur) {
        this.setVisible(true);
        this.setTitle(titre);
        this.setSize(largeur, hauteur);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLUE);
        this.getContentPane(); 
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    public static void main(String[] args) {
        Rectangle rect = new Rectangle(10, 20, 50, 30, Color.blue);
        ZoneGraphique zg = new ZoneGraphique(rect);
        InstanceView1 fenetre = new InstanceView1("InstanceView1_Titre", 500, 500, zg, rect);
    }
}
