/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jules
 */
public class Fenetre extends JFrame{
    
    
    private ZoneGraphique zg;
   
    
        public Fenetre(){
            this.setSize(800,600);
            this.setTitle("Tp Paint L2");
            this.setLocation(300,300);
    
            zg=new ZoneGraphique(this);
            
            BorderLayout BL= new BorderLayout();
            this.setLayout(BL);
           
            this.add(zg,BL.CENTER);
        }
}
