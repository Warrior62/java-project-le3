/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Random;
import modele.Algorithme;
import modele.ContenuBox;
import modele.Instance;
import modele.PileProduit;
import modele.Solution;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author agpou
 */
public class PanelPile extends javax.swing.JPanel {
        private int echelle=3;
        public Collection<TypeProduit> listP = new HashSet<>();
        public Collection<TypeBox> listB = new HashSet<>();
        public Collection<TypeProduit> listBis= new HashSet<>();

    
    /**
     * Creates new form PanelPile
     */
    public PanelPile() {
        initComponents();
    }

    public Color RandomColor(){
       Random rand = new Random();
       float r = rand.nextFloat();
       float g = rand.nextFloat();
       float b = rand.nextFloat();
       
       return new Color(r, g, b);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        int x = 50;
        int y = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        
        this.setSize(width-35, height-35);
        int sizeScreen = this.getWidth();

        System.out.println("size screen :"+sizeScreen);
        
       
        
        TypeProduit p1 = new TypeProduit("P001_0",200,300,4);    
        TypeProduit p2 = new TypeProduit("P001_1",200,300,4);    
        TypeProduit p3 = new TypeProduit("P001_2",200,300,4);  
        
        TypeProduit p4 = new TypeProduit("P002_0",23,50,2);
        
        TypeProduit p5 = new TypeProduit("P003_0",90,290,2);
        
        TypeProduit p6 = new TypeProduit("P004_0",300,160,1);
        
        TypeProduit p7 = new TypeProduit("P005_0",250,100,1);
        
        TypeProduit p8 = new TypeProduit("P006_0",100,100,4);    
        TypeProduit p9 = new TypeProduit("P006_1",100,100,4);    
        TypeProduit p10 = new TypeProduit("P006_2",100,100,4);  
        TypeProduit p11 = new TypeProduit("P006_3",100,100,4);
        
        TypeProduit p12 = new TypeProduit("P007_1",90,230,1);
        
        TypeProduit p13 = new TypeProduit("P008_0",80,150,3);
        TypeProduit p14 = new TypeProduit("P008_1",80,150,3);
        TypeProduit p15 = new TypeProduit("P008_2",80,150,3);
        
        TypeProduit p16 = new TypeProduit("P009_0",120,170,2);
        TypeProduit p17 = new TypeProduit("P009_1",120,170,2);
        
        TypeProduit p18 = new TypeProduit("P0010_0",160,160,1);
        
        TypeProduit p19 = new TypeProduit("P0011_0",330,220,2);
        TypeProduit p20 = new TypeProduit("P0012_0",330,220,2);

        //création d'une TypeBox
        TypeBox b1 = new TypeBox("B001",40,20,8);
        TypeBox b2 = new TypeBox("B002",300,200,8);
        TypeBox b3 = new TypeBox("B003",400,300,10);


        //on crée une instance dans laquelle on ajoute les TypeBox et TypeProduits
        Instance ins = new Instance("I1000");
        //liste de box dans instance
        b1.setInstance(ins);
        b2.setInstance(ins);
        b3.setInstance(ins);
        //liste de produits dans l'instance, liste qui n'est pas triée
        p1.setInstance(ins);
        p2.setInstance(ins);
        p3.setInstance(ins);
        p4.setInstance(ins);
        p5.setInstance(ins);
        p6.setInstance(ins);
        p7.setInstance(ins);
        p8.setInstance(ins);
        p9.setInstance(ins);
        p10.setInstance(ins);
        p11.setInstance(ins);
        p12.setInstance(ins);
        p13.setInstance(ins);
        p14.setInstance(ins);
        p15.setInstance(ins);
        p16.setInstance(ins);
        p17.setInstance(ins);
        p18.setInstance(ins);
        p19.setInstance(ins);
        p20.setInstance(ins);
        
        Solution sol_final = Algorithme.algorithme(ins);
        
        
        

        /*g.setColor(Color.blue);
        g.drawRect(x,y,b1.getLbox(),b1.getHbox());
        y+=b1.getHbox();*/
        int maxW = 0,maxHeight =0, positionRef=0, y2=0, x2=0;
        for(ContenuBox cb : sol_final.getListeContenuBox()){
            //System.out.println("ContenuBox");
            g.setColor(Color.blue);
            //si la box dépasse en largeur de l'écran du pc 
            if(x+cb.getMaTypeBox().getLbox() > sizeScreen){
                y = positionRef + maxHeight+10;
                x=50;
                //System.out.println("passer la ");
            }
            g.drawRect(x,y-1,cb.getMaTypeBox().getLbox(),cb.getMaTypeBox().getHbox()+1);
            if(maxHeight < cb.getMaTypeBox().getHbox())  maxHeight = cb.getMaTypeBox().getHbox();

            positionRef = y;
            y = positionRef+cb.getMaTypeBox().getHbox();
            y2 = y;
            x2=x;
            for(PileProduit pileProd : cb.getMaListeProduits()){
                //System.out.println("PileProduit");
                for(TypeProduit p : pileProd.getPileProduits()){
                    //System.out.println("TypeProduit");
                    if(p.getLproduit() > maxW) maxW = p.getLproduit();
                    //System.out.println(p.getId());
                    g.setColor(RandomColor());
                    y-=p.getHproduit();
                    g.fillRect(x+1, y,p.getLproduit(), p.getHproduit());
                    
                }
                y= y2;
                x+=maxW;
                maxW =0;
            }
            x = x2+cb.getMaTypeBox().getLbox()+10;
            y=positionRef;
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
