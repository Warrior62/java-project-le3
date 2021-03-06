/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modele.Algorithme;
import modele.Instance;
import modele.ReqBDD;
import modele.Solution;

/**
 *
 * @author agpou
 */
public class GestionInstances extends javax.swing.JFrame {
    private ReqBDD requeteBDD;

    /**
     * Creates new form GestionInstances
     * @throws java.lang.Exception
     */
    public GestionInstances() throws Exception {
        initComponents();
        initialisationFenetre();
        initConnexion();
        initListe();
    }
    
    private void initConnexion() throws Exception{
           this.requeteBDD= ReqBDD.getInstance(); 
    }
    
    private void initialisationFenetre() {
        this.setTitle("Gestion des instances");
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.resolution1.setSize(20,20);
        this.jShowInstanceButton.setSize(20,20);
    }
    
    
    private void initListe() throws Exception{
            DefaultListModel defm = new DefaultListModel();
            this.jListInstance.setModel(defm);
            ArrayList<Instance> listToIterateOn = (ArrayList<Instance>) this.requeteBDD.findAllInstances();

            for (Instance cli : listToIterateOn) {
                defm.addElement(cli);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jListInstance = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jShowInstanceButton = new javax.swing.JButton();
        resolution1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jListInstance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jListInstance.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jListInstance.setForeground(new java.awt.Color(51, 0, 51));
        jListInstance.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "No Instances" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListInstance);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("OptiBox");

        jShowInstanceButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.selectionBackground"));
        jShowInstanceButton.setText("Affiche les Instances");
        jShowInstanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jShowInstanceButtonMouseClicked(evt);
            }
        });

        resolution1.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        resolution1.setText("Resolution 1");
        resolution1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resolution1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resolution1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jShowInstanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(246, 246, 246))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(111, 111, 111)
                .addComponent(jShowInstanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(resolution1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jShowInstanceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jShowInstanceButtonMouseClicked
        // TODO add your handling code here:

        if (jListInstance.getSelectedIndex()==-1){
            //System.out.println("RIEN DE CLIQUE");
            JOptionPane.showMessageDialog(this,"Veuillez sélectionner une instance", "Erreur", HEIGHT);
        }
        else {
                //System.out.println("C BON " + jListInstance.getSelectedIndex());
                Object obj = this.jListInstance.getModel().getElementAt(jListInstance.getSelectedIndex());
                Instance instance = (Instance) obj;
                InstanceView view = new InstanceView(instance);
        }
    }//GEN-LAST:event_jShowInstanceButtonMouseClicked

    //Bouton Resolution Algo 1
    private void resolution1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resolution1MouseClicked
        // TODO add your handling code here:
        if (jListInstance.getSelectedIndex()==-1){
            //System.out.println("RIEN DE CLIQUE");
            JOptionPane.showMessageDialog(this,"Veuillez sélectionner une instance pour la résolution", "Erreur", HEIGHT);
        }
        else {
                //On récupère l'instance sélectionnée
                Object obj = this.jListInstance.getModel().getElementAt(jListInstance.getSelectedIndex());
                Instance instance = (Instance) obj;
                System.out.println("eeeeeeeeeeee "+instance.getNomInstance());
                //On lance l'algo de résolution 1 
                //fonction qui permet de calculer le temps écoulé pendant la résolution
                //https://www.codeflow.site/fr/article/java__how-do-calculate-elapsed-execute-time-in-java
                long lStartTime = System.currentTimeMillis();
                Solution solution = Algorithme.algorithme(instance);
            try {
                //on vérifie maintenant si elle existe en BDD
                boolean verif = requeteBDD.isSolutionExist(instance);
                //si c'est true la solution existe déjà et o affiche un message
                if(verif){
                    JOptionPane.showMessageDialog(this,"La solution existe déjà en BDD, cependant nous allons la recalculer", "Erreur", HEIGHT);
                }
                //sinon on l'ajoute en bdd
                else{
                    Algorithme.ajoutSolutionBDD(solution);
                }
                long lEndTime = System.currentTimeMillis();
                long tpsFinal = lEndTime - lStartTime;
                //on envoie la solution dans la page PileView pour l'affichage
                PileView view2 = new PileView(solution,tpsFinal);
            } catch (SQLException ex) {
                Logger.getLogger(GestionInstances.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
        
    }//GEN-LAST:event_resolution1MouseClicked
    
    
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
            java.util.logging.Logger.getLogger(GestionInstances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionInstances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionInstances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionInstances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    new GestionInstances();
                } catch (Exception ex) {
                    Logger.getLogger(GestionInstances.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListInstance;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jShowInstanceButton;
    private javax.swing.JButton resolution1;
    // End of variables declaration//GEN-END:variables
}
