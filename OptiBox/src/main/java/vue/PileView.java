/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import modele.Algorithme;
import modele.Instance;
import modele.Solution;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author agpou
 */
public class PileView extends javax.swing.JFrame {

    /**
     * Creates new form PileView
     */
    public PileView() {
        this.initComponents();
        this.initialisationFenetre();
        drawSolution();
    }
    
    private void drawSolution(){
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
        
        int oldGroup=-1; int actualGroup=-1;
        Color couleur = null;
        for(TypeProduit p : ins.getSetProduits()){
            actualGroup = p.getGrpProduit();
            System.out.println("old : "+ oldGroup);
            System.out.println("actuel : "+ actualGroup);
            //si le groupe du produit n'est pas le même que celui d'avant
            if(oldGroup == -1 || oldGroup != actualGroup){
                couleur = RandomColor();
                System.out.println("iffffffffffffffffff");
            }
            oldGroup = actualGroup;
            p.setCouleur(couleur);
        }
        
        Solution sol_final = Algorithme.algorithme(ins);
        this.panelPile1.s=sol_final;
        this.panelPile1.repaint();
    }
    
     /**
     * Fonction prise de stack overflow pour avoir une couleur aléatoire
     * https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
     * @return Color
     */
    public Color RandomColor(){
       Random rand = new Random();
       float r = rand.nextFloat();
       float g = rand.nextFloat();
       float b = rand.nextFloat();
       
       return new Color(r, g, b);
    }
    
     private void initialisationFenetre() {
         this.setVisible(true);
        this.setTitle("Pile View");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        this.setLocation(0,0);
        this.setSize( width, height-35);
        this.setBackground(Color.BLUE);
        this.getContentPane();
    }

     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelPile1 = new vue.PanelPile();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelPile1Layout = new javax.swing.GroupLayout(panelPile1);
        panelPile1.setLayout(panelPile1Layout);
        panelPile1Layout.setHorizontalGroup(
            panelPile1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
        panelPile1Layout.setVerticalGroup(
            panelPile1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelPile1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PileView();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private vue.PanelPile panelPile1;
    // End of variables declaration//GEN-END:variables
}
