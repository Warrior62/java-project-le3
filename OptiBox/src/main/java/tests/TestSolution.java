/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import modele.TypeBox;
import modele.TypeProduit;

/**
 *
 * @author jules
 */
public class TestSolution {
 
    private ReqBDD re;
    private Set<TypeBox> boxTest;
    private Set<TypeProduit> produitTest;
    
    private TypeBox boxMaxInstance;

    public TestSolution() {
        this.boxTest = new HashSet();
        this.produitTest = new HashSet();
        this.boxMaxInstance= new TypeBox();
        
         initConnexion();
    }
    
    private void initConnexion(){
        try
        {
           this.re= ReqBDD.getInstance(); 
        } catch(Exception ex){
            System.out.println("Connexion à la bdd impossible, vérifiez que vous êtes connecté");
            
        }
    }
    
    public void testSolution0() throws SQLException{
        long idInstance=1;
        
        this.produitTest= re.findProdByInstanceId(idInstance);
        this.boxTest= re.findBoxByInstanceId(idInstance);
        
        //On récupère la box la plus grande
        for (TypeBox b : boxTest)
        {
            if(b.getLbox()*b.getHbox()>boxMaxInstance.getHbox()*boxMaxInstance.getLbox())
            {
                boxMaxInstance = b;
            }
        }
        
        // On va tout mettre dans cette box
      
    }
    
    public static void main(String[] args) throws SQLException {
        TestSolution te = new TestSolution();
        te.testSolution0(); 
    }
}
