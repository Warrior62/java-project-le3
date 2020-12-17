/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author jules
 */
public class ZoneGraphique extends Canvas {
    
     private Fenetre saFenetre;
    public static List<Forme> ToutesLesFormes;
    /*
    Points initiaux et finaux sont présents dans l Ecouteur Souris
    
    */
    
    public ZoneGraphique(Fenetre f){
        ToutesLesFormes=new ArrayList<>();
        saFenetre=f;
        this.setBackground(Color.white);
       
    }

    @Override
    public void paint(Graphics g) {
        for(Forme f: ToutesLesFormes)
        {
            f.seDessiner(g);
        }
    }
    
    
  /*  private void dessin(){
        
    Graphics g = this.getGraphics();
    
    }*/
    
}
