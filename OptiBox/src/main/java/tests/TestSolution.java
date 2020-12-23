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
import modele.ContenuBox;
import modele.TypeBox;
import modele.TypeProduit;
import modele.ReqBDD;
import modele.Solution;


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
        this.produitTest = new HashSet<>();
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
    
    public Solution testSolution0() throws SQLException, ClassNotFoundException{
        long idInstance=1;
        int nbBox = 0;
        Solution s = new Solution();
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
        TypeProduit p;
        Object[] arrayItem = this.produitTest.toArray(); 
        
      while(this.produitTest!= null)
      {
          //Recupérer premier élément liste 
        
            p = (TypeProduit) arrayItem[0];
            //System.out.println(p.getLproduit());
          
         /* if (s.getListeBoxs() == null){
            ContenuBox cb1 = new ContenuBox();
            nbBox++;
            s.setNbBoxs(nbBox);
        }*/
        //On remplit nos box
        //if(p.getHproduit()<= boxMaxInstance.getHbox())
        
        //Des que la box est remplie
        //s.Ajouter(cb1); // Créer fonction dans solution qui ajoute dans la liste
         
        
        
        // A décommenter des qu'on a résolu : Recupérer premier élément liste 
        this.produitTest.remove(p);
          
      }
     
      return s;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestSolution te = new TestSolution();
        te.testSolution0(); 
    }
}
