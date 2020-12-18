/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.JOptionPane;


/**
 *
 * @author tryla
 */
public class Solution {
    private Collection<ContenuBox> listeBoxs;
    private String nomSolution;
    private Integer prixFinal, nbBoxs;
    private ReqBDD requeteBDD;


    public Solution() throws ClassNotFoundException, SQLException {
        this.nomSolution = "DEFAULT_SOLUTION";
        this.listeBoxs = new HashSet<ContenuBox>();
        this.prixFinal = 0;
        this.nbBoxs = this.listeBoxs.size();
        initConnexion();
    }
    
    public Solution(Collection<ContenuBox> listeBoxs) throws ClassNotFoundException, SQLException {
        this();
        this.listeBoxs = listeBoxs;
    }
    
    private void initConnexion() throws ClassNotFoundException, SQLException{
        try
        {
           this.requeteBDD = ReqBDD.getInstance(); 
        } catch(Exception ex){
            System.err.println("Erreur connexion BDD : Solution");
        }
    }
    
    public void findSolution() throws SQLException, ClassNotFoundException{
        for(TypeBox tb : requeteBDD.findBoxByInstanceId(7)){
            System.out.println(tb.getPrixbox());
        }
        
    }

    public Collection<ContenuBox> getListeBoxs() {
        return listeBoxs;
    }

    public void setListeBoxs(Collection<ContenuBox> listeBoxs) {
        this.listeBoxs = listeBoxs;
    }

    public String getNomSolution() {
        return nomSolution;
    }

    public void setNomSolution(String nomSolution) {
        this.nomSolution = nomSolution;
    }

    public Integer getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(Integer prixFinal) {
        this.prixFinal = prixFinal;
    }

    public Integer getNbBoxs() {
        return nbBoxs;
    }

    public void setNbBoxs(Integer nbBoxs) {
        this.nbBoxs = nbBoxs;
    }
    
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
   /*     ReqBDD reqBDD = new ReqBDD();
        Solution sol1 = new Solution(reqBDD.findBoxByInstanceId(7));
        System.out.println("-------------- GET TB DATA --------------");
        for(TypeBox tb : reqBDD.findBoxByInstanceId(7)){
            System.out.println(tb.getHbox() + " " + tb.getLbox());
        }
        System.out.println("-------------- FIND SOLUTION --------------");
        sol1.findSolution();*/
    }
    
}
